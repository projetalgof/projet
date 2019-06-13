public class CarteRouge extends Carte
{
	public CarteRouge(String declencheur, String nom, String type, int cout,int piece)
	{
		super(declencheur, nom, type, cout,piece);
	}

	public void action(Joueur propietaire,Joueur joueurActif,Controleur ctrl)
	{
		if (joueurActif.getPiece() - this.piece >= 0) 
		{
			joueurActif.setPiece(-this.piece) ;
			propietaire.setPiece( this.piece) ;
		} 
		else 
		{
			propietaire.setPiece( joueurActif.getPiece()) ;
			joueurActif.setPiece(-joueurActif.getPiece()) ;
		}
	}
}