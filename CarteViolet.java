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
				
				ctrl.getMetier().choisitUnJoueur(joueurActif).setPiece(-this.piece);
				joueurActif.setPiece(this.piece);
			}else if("centre d'affaire".equals(this.nom))
			{
				Carte carteChoisit = null;
				Joueur joueurChoisit = ctrl.getMetier().choisitUnJoueur(joueurActif);
				
				ctrl.donnerLeCarteAQu(joueurActif);
				carteChoisit = ctrl.getMetier().choisitUnCarte(joueurChoisit);
				joueurActif.ajouterCarte(carteChoisit);
				joueurChoisit.getListCartesReel().remove(carteChoisit);
				
				ctrl.donnerLeCarteAQu(joueurChoisit);
				carteChoisit =  ctrl.getMetier().choisitUnCarte(joueurActif);
				joueurChoisit.ajouterCarte(carteChoisit);
				joueurActif.getListCartesReel().remove(carteChoisit);
				
			}
				
		}
	}
	

}