import java.util.ArrayList;
public class Joueur
{
	private static int jetDe ;

	private String nom ; 
	private int    piece ;
	//liste de carte posseder par le joueur
	private ArrayList<Carte> listCartes;
	//tableau de 4 monument
	private Monument[] monuments ;

	public Joueur (String nom)
	{
		this.nom=nom;
		this.listCartes = new ArrayList<Carte>();
		this.monuments= new Monument[4];
	}

	public void jetDe(int nbDe)
	{
		this.jetDe=0;
		for (int i=0;i<nbDe;i++)
			this.jetDe+=Math.random() * 6 +1;
	}

	public void ajouterCarte(Carte carte)
	{
		this.listCartes.add(carte);
	}

	//get
	public String           getNom        () { return this.nom ;        }
	public int              getPiece      () { return this.piece ;      }
	public Monument[]       getMonuments  () { return this.monuments ;  }

	public ArrayList<Carte> getListCartes () 
	{
		ArrayList<Carte> tmp = new ArrayList<Carte>();

    	for(Carte carte : this.listCartes)
    	{
    		tmp.add(carte);
    	}
    	return tmp ; 
	}
	//set
	public void setPiece(int piece) {this.piece+=piece ;}
}