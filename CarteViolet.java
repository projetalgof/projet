import java.util.Scanner;

public class CarteViolet extends Carte
{
	private String typeMultiple;

	public CarteViolet(String declencheur, String nom, String type, int cout,int piece,String typeMultiple) 
	{
		super(declencheur, nom, type, cout,piece);
		this.typeMultiple = typeMultiple;

	}
	public CarteViolet(CarteViolet carte)
	{
		super(carte);
		this.typeMultiple = carte.typeMultiple;
	}

	public void action(Joueur propietaire,Joueur joueurActif,Controleur ctrl)
	{
		if(propietaire == joueurActif)
		{
			//verifie si l'effet a un multiple a faire
			if("state".equals(this.nom))
				for(Joueur tmp : ctrl.getMetier().getJoueurs())
				{
					if(!tmp.equals(joueurActif))
					{
						tmp.setPiece(-this.piece);
						joueurActif.setPiece(this.piece);
					}
				}
			else if("chaine TV".equals(this.nom))
			{
				
				this.choisitUnJoueur(joueurActif,ctrl).setPiece(-this.piece);
				joueurActif.setPiece(this.piece);
			}else if("centre d'affaire".equals(this.nom))
			{
				Carte carteChoisit = null;
				Joueur joueurChoisit = this.choisitUnJoueur(joueurActif, ctrl);
				carteChoisit = this.choisitUnCarte(joueurChoisit);
				joueurActif.ajouterCarte(carteChoisit);
				
				carteChoisit = this.choisitUnCarte(joueurActif);
				joueurChoisit.ajouterCarte(carteChoisit);
				
				
			}
				
		}
	}
	//choisit un joueur
	private Joueur choisitUnJoueur(Joueur joueurActif,Controleur ctrl)
	{
		Joueur joueurChoisi = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("Choisir un Joueur");
		do
		{
			joueurChoisi = this.rechercherJoueur(sc.nextLine(),ctrl);
			if(null == joueurChoisi || joueurActif.equals(joueurChoisi))
				System.out.println("Saisir un nom correct");
		}
		while(null == joueurChoisi || joueurActif.equals(joueurChoisi));
		
		return joueurChoisi;
	}
	
	private Joueur rechercherJoueur (String nom,Controleur ctrl)
	{
		for(Joueur tmp :ctrl.getMetier().getJoueurs())
			if(nom.equals(tmp.getNom()))
				return tmp;
		return null;
	}
	
	private Carte choisitUnCarte(Joueur joueurChoisit)
	{
		Carte carteChoisit = null;
		Scanner sc = new Scanner(System.in);
		System.out.println("Choisir un Carte");
		do
		{
			
			carteChoisit = this.rechercherCarte(sc.nextLine(),joueurChoisit);
			if(null == carteChoisit )
				System.out.println("Saisir un nom correct");
		}
		while(null == carteChoisit);
		
		//remove cette carte
		joueurChoisit.getListCartes().remove(carteChoisit);
		
		return carteChoisit;
	}
	
	private Carte rechercherCarte (String nom,Joueur joueurChoisit)
	{
		for(Carte tmp :joueurChoisit.getListCartes())
			if(nom.equals(tmp.getNom()))
				return tmp;
		return null;
	}
	
}