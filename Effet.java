public class Effet
{
	private int piece ;
	private String type; //  * pour tous , + que pour le joueur

	public Effet(int piece, String type)
	{
		this.piece=piece;
		this.type=type;
	}

	public int    getPiece  () { return this.piece ; }
	public String getType   () { return this.type ;  }
}