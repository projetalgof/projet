public enum EnumCarte 
{
	champs(         "1"     ,"champs de ble"      ,"grain"      ,1,"bleu"   ,1),
	ferme (         "2"     ,"ferme"              ,"production" ,1,"bleu"   ,1),
	boulangerie(    "2-3"   ,"boulangerie"        ,"produit"    ,1,"vert"   ,1),
	cafe(           "3"     ,"cafe"               ,"tasse"      ,1,"rouge"  ,2),
	superette(      "4"     ,"superette"          ,"produit"    ,3,"vert"   ,2),
	foret(          "5"     ,"forêt"              ,"rouage"     ,1,"bleu"   ,3),
	stade(          "6"     ,"state"              ,"antenne"    ,2,"violet" ,6),
	chaineTV(       "6"     ,"chaine TV"          ,"antenne"    ,5,"violet" ,7),
	centreAffaire(  "6"     ,"centre d'affaire"   ,"antenne"    ,0,"violet" ,8),
	fromagerie(     "7"     ,"fromagerie"         ,"usine"      ,3,"vert"   ,5),
	fabriqueMeuble( "8"     ,"fabrique de meuble" ,"usine"      ,3,"vert"   ,3),
	mine(           "9"     ,"mine"               ,"rouage"     ,5,"bleu"   ,6),
	restaurant(     "9-10"  ,"restaurant"         ,"tasse"      ,2,"rouge"  ,3),
	verger(         "10"    ,"verger"             ,"grain"      ,3,"bleu"   ,3),
	marcherLegume(  "11-12" ,"marcher au legume"  ,"pomme"      ,2,"vert"   ,2);

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
	//get
	public String getDeclencheur() { return this.declencheur ;}
	public String getNom        () { return this.nom ;        }
	public String getType       () { return this.type ;       }
	public int    getGainEffet  () { return this.gainEffet ;  }
	public String getTypeEffet  () { return this.typeEffet ;  }
	public int    getCout       () { return this.cout ;       }
}