public class CarteBleu extends Carte
{
	public CarteBleu(String declencheur, String nom, String type, int cout,int piece)
	{
		super(declencheur, nom, type, cout,piece);
	}

	public void action(Joueur propietaire,Joueur joueurActif,Controleur ctrl)
	{
		propietaire.setPiece(this.piece);
		ctrl.effetCarte(propietaire.getNom(),this.nom,this.piece);
	}
}