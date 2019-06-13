public class CarteRouge extends Carte
{
	public CarteRouge(String declencheur, String nom, String type, int cout,int piece)
	{
		super(declencheur, nom, type, cout,piece);
	}
	public CarteRouge(CarteRouge carte)
	{
		super(carte);
	}
	//effet de la carte
	public void action(Joueur propietaire,Joueur joueurActif,Controleur ctrl)
	{
		int nbPiece=0;
		//si le joueur actif a sufisament de piece
		if (joueurActif.getPiece() - this.piece >= 0) 
		{
			joueurActif.setPiece(-this.piece) ;
			propietaire.setPiece( this.piece) ;
			nbPiece=this.piece;
		} 
		else 
		{
			propietaire.setPiece( joueurActif.getPiece()) ;
			joueurActif.setPiece(-joueurActif.getPiece()) ;
			nbPiece=joueurActif.getPiece();
		}
		ctrl.effetCartePaimment(joueurActif.getNom(),propietaire.getNom(),this.getNom(),nbPiece);
	}
}