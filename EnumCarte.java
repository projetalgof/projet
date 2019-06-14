public enum EnumCarte
{
	champs(         "1"     ,"champs de ble"      ,"grain"      ,1,"bleu"     ,1,null),
	ferme (         "2"     ,"ferme"              ,"production" ,1,"bleu"     ,1,null),
	boulangerie(    "2-3"   ,"boulangerie"        ,"produit"    ,1,"vert"     ,1,null),
	cafe(           "3"     ,"cafe"               ,"tasse"      ,1,"rouge"    ,2,null),
	superette(      "4"     ,"superette"          ,"produit"    ,3,"vert"     ,2,null),
	foret(          "5"     ,"forêt"              ,"rouage"     ,1,"bleu"     ,3,null),
	stade(          "6"     ,"state"              ,"antenne"    ,2,"violet"   ,6,null),
	chaineTV(       "6"     ,"chaine TV"          ,"antenne"    ,5,"violet"   ,7,null),
	centreAffaire(  "6"     ,"centre d'affaire"   ,"antenne"    ,0,"violet"   ,8,null),
	fromagerie(     "7"     ,"fromagerie"         ,"usine"      ,3,"vert"     ,5,"production"),
	fabriqueMeuble( "8"     ,"fabrique de meuble" ,"usine"      ,3,"vert"     ,3,"rouage"),
	mine(           "9"     ,"mine"               ,"rouage"     ,5,"bleu"     ,6,null),
	restaurant(     "9-10"  ,"restaurant"         ,"tasse"      ,2,"rouge"    ,3,null),
	verger(         "10"    ,"verger"             ,"grain"      ,3,"bleu"     ,3,null),
	gare(           ""      ,"gare"               ,"monument"   ,0,"monument" ,4,null),
	centreCom(      ""      ,"centre commercial"  ,"monument"   ,0,"monument" ,10,null),
	parcAtraction(  ""      ,"parc d'atraction"   ,"monument"   ,0,"monument" ,16,null),
	tourRadio(      ""      ,"tour de radio"      ,"monument"   ,0,"monument" ,22,null),
	marcherLegume(  ""      ,"marcher au legume"  ,"pomme"      ,2,"vert"     ,2,"grain");

	private final String declencheur ;
	private final String nom         ;
	private final String type        ;
	private final int    gainEffet   ;
	private final String couleur     ;
	private final int    cout        ;
	private final String multiple    ;

	private EnumCarte(String declencheur,String nom,String type,int gainEffet,String couleur,int cout,String multiple)
	{
		this.declencheur = declencheur;
		this.nom         = nom;
		this.type        = type;
		this.gainEffet   = gainEffet;
		this.couleur     = couleur;
		this.cout        = cout;
		this.multiple    = multiple ;
	}

	public Carte creeCarte()
	{	
		Carte tmp = null ; 
		switch(this.couleur)
		{
			case "vert"    : tmp = new CarteVerte(this.declencheur,this.nom,this.type,this.cout,this.gainEffet , this.multiple);
				break ;
			case "rouge"   : tmp = new CarteRouge(this.declencheur,this.nom,this.type,this.cout,this.gainEffet); 
				break ;
			case "bleu"    : tmp = new CarteBleu(this.declencheur,this.nom,this.type,this.cout,this.gainEffet); 
				break ;
			case"monument" : tmp = new Monument(this.nom,this.cout);
				break;
		}
		return tmp ;
	}
	//get
	public String getDeclencheur() { return this.declencheur ;}
	public String getNom        () { return this.nom ;        }
	public String getType       () { return this.type ;       }
	public int    getGainEffet  () { return this.gainEffet ;  }
	public String getCouleur    () { return this.couleur ;    }
	public int    getCout       () { return this.cout ;       }
	public String getMultiple   () { return this.multiple;    }
}
