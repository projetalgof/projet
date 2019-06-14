public class Controleur 
{
	private Metier metier;
	private IHM    ihm;

	public Controleur() 
	{
		this.ihm    = new IHM(this);
		this.metier = new Metier(this, this.ihm.debut());
		//boucle du jeu
		do
			metier.jouer();
		while (!this.metier.getIsEnd());
	}

	//----------------------------------------------------------------------------------------------------------------
	//                                             LIEN SCANNERS
	public String creeJoueur() { return this.ihm.creeJoueur(); }
	public String commence  () { return this.ihm.commence();   }
	public char   choix     () { return this.ihm.choix();      }

	//----------------------------------------------------------------------------------------------------------------
	//                                             LIEN ACHAT
	public String achatEtablissement ()                               { return this.ihm.achatEtablissement(); }
	public String achatMonument      ()                               { return this.ihm.achatMonument();      }
	public void   achatValide        ( Joueur joueur , Carte carte )  { this.ihm.achatValide(joueur,carte) ;  }
	public void   achatErreur        ()                               { this.ihm.achatErreur() ;              }

	//----------------------------------------------------------------------------------------------------------------
	//                                             LIEN CARTE
	public void effetCarte(String nomJoueur,String nomCarte , int piece)
	{
		this.ihm.effetCarte(nomJoueur,nomCarte,piece);
	}
	public void effetCartePaimment(String joueurActif , String joueurProprietaire,String nomCarte ,int nbPiece)
	{
		this.ihm.effetCartePaimment(joueurActif , joueurProprietaire,nomCarte ,nbPiece);
	}
	//----------------------------------------------------------------------------------------------------------------
	//                                             LIEN AFFICHAGE

	public void transition        (Joueur joueur) { this.ihm.transition(joueur);        }
	public void afficherBanque    ()              { this.ihm.afficherBanque();          }
	public void jetDe             (Joueur joueur) { this.ihm.jetDe(joueur);             }
	public void afficherEtatJoueur(Joueur joueur) { this.ihm.afficherEtatJoueur(joueur);}
	public void afficherEtat      ()              { this.ihm.afficherEtat();            }
	//----------------------------------------------------------------------------------------------------------------
	//                                             GET
	public Banque getBanque() { return this.metier.getBanque(); }
	public Metier getMetier() { return this.metier;             }

	//----------------------------------------------------------------------------------------------------------------
	//                                             MAIN
	public static void main(String agrs[]) {
		new Controleur();
	}
}
