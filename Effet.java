import java.util.ArrayList;
public class Effet
{
	private int     piece ;
	private String  typeMultiple;
	
	public Effet(int piece,String typeMultiple)
	{
		this.piece=piece;
		this.typeMultiple=typeMultiple;
	}
	
	public Effet(int piece)
	{
		this(piece,null);
	}
	
	public int    getPiece  (ArrayList<Carte> listCartes) 
	{ 
		if(this.typeMultiple == null)
		{
			return this.piece;
		}
		else
		{
			int i =0;
			for(Carte carte : listCartes)
			{
				if(carte.getType().equals(typeMultiple)) i++;
			}
			return this.piece * i ;
		} 
	}
}
