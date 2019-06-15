import java.util.ArrayList;
import java.io.FileReader;
import java.util.Scanner;

public class Metier 
{
  private Controleur ctrl;
  private boolean isEnd; // pour la savoir la fin de parti
  private ArrayList<Joueur> joueurs; // list des joueur dans la parti
  private Joueur joueurActif; // tour du joueur
  private Banque banque; // banque du jeu

  public Metier(Controleur ctrl, int nbJoueur) 
  {
    boolean chargement = nbJoueur >1 ;
    //initialise
    this.ctrl    = ctrl;
    this.isEnd   = false;
    this.joueurs = new ArrayList<Joueur>();
    this.banque  = new Banque();

    if(chargement)
    {
      //creation des joueurs
      for (int i = 0; i < nbJoueur; i++) 
      {
        this.joueurs.add(new Joueur(ctrl.creeJoueur()));
        Regle.initialisation(this.joueurs, this.banque);     //creation de la banque et distribution des carte au joueurs
      }
    }
    else
    {
      this.chargement(this.ctrl.nomFichier());
    }
  }
  //methode qui fait tourner le jeu
  public void jouer() 
  {
    char choix ;
    //premiere fois , on initialise le joueur a celui qui commence
    if (joueurActif == null) 
    {
      String joueurCommence = this.ctrl.commence();
      for (Joueur joueur : this.joueurs) 
      {
        if (joueur.getNom().equals(joueurCommence))
          joueurActif = joueur;
      }
    }
    this.ctrl.transition(this.joueurActif);
    this.lanceDe();

    do
    {
      this.ctrl.afficherEtatJoueur(this.joueurActif);
      choix=this.ctrl.choix();
      switch(choix)
      {
        case 'A' : this.achatEtablissement();
          break;
        case 'B' : this.ctrl.afficherBanque();
          break;
        case 'M' : this.achatMonument();
          break;
        case 'R' : this.deuxJet();
          break;
      }
    }
    while(choix != 'P' && !this.joueurActif.getAcheter());
    this.ctrl.afficherEtatJoueur(this.joueurActif);
    //on reinitialise l'achat du joueur et on change de joueur
    this.joueurActif.setAcheter(false);
    this.joueurActif.setDeuxJet(false);
    //on verifie si le joueur a gagner
    if(!this.joueurActif.aGagner())
    {
      //on verifie si le joueur a le parc d'atraction et si il a fait un double
      if(this.joueurActif.monumentActif("parc d'atraction") && this.joueurActif.estUnDouble())
      {

      }
      else this.joueurActif=joueurs.get((joueurs.indexOf(joueurActif)+1)%joueurs.size());
    }
    else
    {
      this.isEnd = true ;
      this.ctrl.gagner(this.joueurActif);
    }

  }
  //private meethode
  //chargemement d'un parti a partir du nom de fichier
  private void chargement(String nomFichier)
  {
    try
    {
      Scanner sc = new Scanner ( new FileReader ( nomFichier ) );
      String[] chaine ;

      while ( sc.hasNextLine() )
      {
        chaine = sc.nextLine().split(":");
        if(this.joueurs.isEmpty())
        {
          this.joueurs.add(new Joueur(chaine[0]));
        }
        else
        {
          Joueur tmp = this.rechercherJoueur(chaine[0]);
          if(tmp == null)
            this.joueurs.add(new Joueur(chaine[0]));
        }
      }
      sc.close();
    }
    catch(Exception e) { e.printStackTrace(); }

    Regle.initialisation(this.joueurs, this.banque);  //creation de la banque et distribution des carte au joueurs

    try
    {
      Scanner sc = new Scanner ( new FileReader (nomFichier) );
      String[] chaine ;
      while ( sc.hasNextLine() )
      {
        chaine = sc.nextLine().split(":");
        if(chaine[1].equals("carte"))
        {
          for(int i = 2 ; i<chaine.length;i=i+2)
          {
            for(int j = 0; j < new Integer(chaine[i+1]) ;j++)
            {
              Carte tmp = this.banque.retirer(chaine[i]);
              if(tmp != null )this.getJoueur(chaine[0]).ajouterCarte(tmp);
            }
          }
        }
        if(chaine[1].equals("piece"))
        {
          this.getJoueur(chaine[0]).setPiece(-Regle.PIECE_DEPART + new Integer(chaine[2]));
        }
        if(chaine[1].equals("monument"))
        {
          this.getJoueur(chaine[0]).activeMonument(chaine[2]);
        }
        if(chaine[0].equals("tour"))
        {
          this.joueurActif=this.getJoueur(chaine[1]);
        }
      }
      sc.close();
    }
    catch(Exception e) { e.printStackTrace(); }
  }

  private void deuxJet()
  {
    if(this.joueurActif.monumentActif("tour de radio") && !this.joueurActif.getDeuxJet())
    {
      this.lanceDe();
      this.joueurActif.setDeuxJet(true);
    }
    else this.ctrl.erreurLanceDe();
  }
  //lance les de
  private void lanceDe()
  {
    char choix ;
    //regarde si le monument gare est actif
    if(this.joueurActif.monumentActif("gare"))
    {
      choix=this.ctrl.choixDe();
      if(choix== '1') this.joueurActif.jetDe(1);
      else            this.joueurActif.jetDe(2);
    }
    else this.joueurActif.jetDe(1);
    this.ctrl.jetDe(this.joueurActif);
    //les perte et gain de piece
    this.payer();
    this.gain();
  }
  //gere l'achat de nouvelle etablissement
  private void achatEtablissement()
  {
    String achat = this.ctrl.achatEtablissement();
    if(!achat.equals(""))
    {
      if(this.joueurActif.getPiece() >= banque.consulter(achat).getCout())
      {
        Carte tmp = this.banque.retirer(achat);
        if(tmp != null )
        {
          this.joueurActif.ajouterCarte(tmp);
          this.joueurActif.setPiece(-tmp.getCout());
          this.joueurActif.setAcheter(true);
          this.ctrl.achatValide(this.joueurActif,tmp);
        } 
      }
      else this.ctrl.achatErreur();
    }
  }
  //gere l'achat des monument
  private void achatMonument()
  {
    String achat = this.ctrl.achatMonument();
    if(!achat.equals(""))
    {
      if(this.joueurActif.getPiece() >= this.joueurActif.getMonument(achat).getCout())
        if(!this.joueurActif.monumentActif(achat))
        {
          this.joueurActif.activeMonument(achat);
          this.joueurActif.setPiece(-this.joueurActif.getMonument(achat).getCout());
          this.joueurActif.setAcheter(true);
          this.ctrl.achatValide(this.joueurActif,this.joueurActif.getMonument(achat));
        }
        else this.ctrl.achatMonumentErreur();
      else this.ctrl.achatErreur();
    }
  }

  //recherche un joueur specifique
  public Joueur rechercherJoueur(String nom) 
  {
    for (Joueur joueur : this.joueurs) 
    {
      if (joueur.getNom().equals(nom))
        return joueur;
    }
    return null;
  }
  //gere les du au autre joueur
  private void payer()
  {
    int j = joueurs.indexOf(joueurActif);
    for(int i = 1 ; i <this.joueurs.size();i++)
    {
      if(j-1 <0) j=this.joueurs.size()-1;
      else j--;
      this.joueurs.get(j).payer(this.joueurActif,this.ctrl);
    }
  }
  //gere les gain lier au etablisemment
  private void gain()
  {
    for (Joueur joueur : this.joueurs)
    {
      joueur.gain(this.joueurActif,this.ctrl);
    }
  }
  //-------------------------------------------------------------------------------------------------------------------
  //----------------------------------------------------------GET------------------------------------------------------
  public boolean getIsEnd      () { return this.isEnd;       }
  public Joueur  getJoueurActif() { return this.joueurActif; }
  public Banque  getBanque     () { return this.banque;      }

  public int[] getPiece() 
  {
    int[] pieces = new int[this.joueurs.size()];
    for (Joueur joueur : joueurs) 
    {
      pieces[joueur.getNum()] = joueur.getPiece();
    }
    return pieces;
  }
  // retourn un copie de la list de joueur
  public ArrayList<Joueur> getJoueurs() 
  {
    ArrayList<Joueur> tmp = new ArrayList<Joueur>();
    for (Joueur joueur : this.joueurs) 
    {
      tmp.add(joueur);
    }
    return tmp;
  }

  public Joueur getJoueur(String nom) 
  {
    for (Joueur joueur : this.joueurs) 
    {
      if(joueur.getNom().equals(nom)) return joueur ; 
    }
    return null ;
  }
}