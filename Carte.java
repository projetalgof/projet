public class Carte 
{
	private String declencheur ; // String pour gerais les declencheur multiple
	private String nom ;
	private String type ; 
	private Effet effet ;
	private int    cout; 

	public Carte(String declencheur,String nom,String type,Effet effet,int cout)
	{
		this.declencheur= declencheur;
		this.nom        = nom;
		this.type       = type;
		this.effet      = effet;
		this.cout       = cout;
	}
	//constructeur par recopie
	public Carte(Carte autreCarte)
	{
		this.declencheur = autreCarte.declencheur;
		this.nom         = autreCarte.nom;
		this.type        = autreCarte.type;
		this.effet       = autreCarte.effet;
		this.cout        = autreCarte.cout;
	}

	//get
	public String getdeclencheur() { return this.declencheur;}
	public String getNom        () { return this.nom;        }
	public String getType       () { return this.type;       }
	public Effet  getEffet      () { return this.effet;      }
	public int    getCout       () { return this.cout;       }

}