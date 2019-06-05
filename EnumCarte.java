public enum EnumCarte
{
	champs(         "1"     ,"champs de ble"      ,"grain"      ,1,"bleu"   ,1,'B',null),
	ferme (         "2"     ,"ferme"              ,"production" ,1,"bleu"   ,1,'B',null),
	boulangerie(    "2-3"   ,"boulangerie"        ,"produit"    ,1,"vert"   ,1,'B',null),
	cafe(           "3"     ,"cafe"               ,"tasse"      ,1,"rouge"  ,2,'J',null),
	superette(      "4"     ,"superette"          ,"produit"    ,3,"vert"   ,2,'B',null),
	foret(          "5"     ,"forêt"              ,"rouage"     ,1,"bleu"   ,3,'B',null),
	stade(          "6"     ,"state"              ,"antenne"    ,2,"violet" ,6,'J',null),
	chaineTV(       "6"     ,"chaine TV"          ,"antenne"    ,5,"violet" ,7,'J',null),
	centreAffaire(  "6"     ,"centre d'affaire"   ,"antenne"    ,0,"violet" ,8,'J',null),
	fromagerie(     "7"     ,"fromagerie"         ,"usine"      ,3,"vert"   ,5,'B',"production"),
	fabriqueMeuble( "8"     ,"fabrique de meuble" ,"usine"      ,3,"vert"   ,3,'B',"rouage"),
	mine(           "9"     ,"mine"               ,"rouage"     ,5,"bleu"   ,6,'B',null),
	restaurant(     "9-10"  ,"restaurant"         ,"tasse"      ,2,"rouge"  ,3,'J',null),
	verger(         "10"    ,"verger"             ,"grain"      ,3,"bleu"   ,3,'B',null),
	marcherLegume(  "11-12" ,"marcher au legume"  ,"pomme"      ,2,"vert"   ,2,'B',"grain");

	private final String declencheur ;
	private final String nom         ;
	private final String type        ;
	private final int    gainEffet   ;
	private final String typeEffet   ;
	private final int    cout        ;
	private final char   destination ;
	private final String multiple    ;

	private EnumCarte(String declencheur,String nom,String type,int gainEffet,String typeEffet,int cout,char destination,String multiple)
	{
		this.declencheur = declencheur;
		this.nom         = nom;
		this.type        = type;
		this.gainEffet   = gainEffet;
		this.typeEffet   = typeEffet;
		this.cout        = cout;
		this.destination = destination;
		this.multiple    = multiple ;
	}

	public Carte creeCarte()
	{
		return new Carte(this.declencheur,this.nom,	this.type,
				   	new Effet(this.gainEffet,this.multiple),
				   	this.cout,this.destination,"vert".equals(this.type));
	}
	//get
	public String getDeclencheur() { return this.declencheur ;}
	public String getNom        () { return this.nom ;        }
	public String getType       () { return this.type ;       }
	public int    getGainEffet  () { return this.gainEffet ;  }
	public String getTypeEffet  () { return this.typeEffet ;  }
	public int    getCout       () { return this.cout ;       }
	public char   getDestination() { return this.destination ;}
	public String getMultiple   () { return this.multiple;    }
}
