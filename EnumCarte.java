public enum EnumCarte 
{
	champs(     "1"  ,"champs de ble","grain"     ,1,"bleu"  ,1),
	ferme (     "2"  ,"ferme"        ,"production",1,"bleu"  ,1),
	boulangerie("2-3","boulangerie"  ,"produit"   ,1,"vert"  ,1),
	cafe(       "3"  ,"cafe"         ,"tasse"     ,1,"rouge" ,2),
	superette(  "4"  ,"superette"    ,"produit"   ,3,"vert"  ,2),
	foret(      "5"  ,"forêt"        ,"rouage"    ,1,"bleu"  ,3),
	stade(      "6"  ,"state"        ,"antenne"   ,2,"violet",6);

	private final String declencheur ;
	private final String nom         ;
	private final String type        ;
	private final int    gainEffet   ;
	private final String typeEffet   ;
	private final int    cout        ;

	private EnumCarte(String declencheur,String nom,String type,int gainEffet,String typeEffet,int cout)
	{
		this.declencheur = declencheur;
		this.nom         = nom;
		this.type        = type;
		this.gainEffet   = gainEffet;
		this.typeEffet   = typeEffet;
		this.cout        = cout;
	}

	public Carte creeCarte()
	{
		return new Carte(this.declencheur,this.nom,	this.type,new Effet(this.gainEffet ,this.typeEffet),this.cout);
	}

	public String getDeclencheur() { return this.declencheur ;}
	public String getNom        () { return this.nom ;        }
	public String getType       () { return this.type ;       }
	public int    getGainEffet  () { return this.gainEffet ;  }
	public String getTypeEffet  () { return this.typeEffet ;  }
	public int    getCout       () { return this.cout ;       }
}