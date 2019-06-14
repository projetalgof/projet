import java.util.ArrayList;

public class Joueur 
{
	private static int[] jetDe;
	private static int   sommeDe;
	private static int   nbJoueur;

	private String  nom;
	private int     numJoueur;
	private int     piece;
	private boolean aAcheter ; //assure que l'on peu effectuer un seul achat a la fois
	// liste de carte posseder par le joueur
	private ArrayList<Carte> listCartes;

	public Joueur(String nom) 
	{
		if(this.jetDe == null) this.jetDe = new int[2];
		this.nom        = nom;
		this.listCartes = new ArrayList<Carte>();
		this.numJoueur  = Joueur.nbJoueur++;
	}
	//active les action de carte qui raporte des piece de la banque
	public void gain(Joueur joueurActif,Controleur ctrl) 
	{
		for(Carte carte : this.listCartes) 
		{
			if( !(carte instanceof CarteRouge) && !(carte instanceof Monument))
			{
				if ((carte.getDeclencheur().indexOf((Joueur.sommeDe + ""))) >= 0) carte.action(this,joueurActif,ctrl);
			}
		}
	}
	//active les action de carte qui rapporte des piece des autre joueur
	public void payer(Joueur joueurActif,Controleur ctrl)
	{
		for(Carte carte : this.listCartes) 
		{
			if(carte instanceof CarteRouge  && !(carte instanceof Monument))
			{
				if ((carte.getDeclencheur().indexOf((Joueur.sommeDe + ""))) >= 0)  carte.action(this,joueurActif,ctrl);
			}
		}
	}
	//jet un nombre de dé
	public void jetDe(int nbDe) 
	{
		int resultat = 0;
		for (int i = 0; i < nbDe; i++)
		{
			this.jetDe[i] = (int)(Math.random() * 6 + 1);
			resultat += this.jetDe[i];
		}
		this.sommeDe=resultat;
	}
	//ajoute une carte au joueur
	public void ajouterCarte(Carte carte) 
	{
		this.listCartes.add(carte);
	}
	//verifie si le monument donner est actif
	public boolean MonumentActif(String nom)
	{
		for(Carte carte : this.listCartes)
		{
			if(carte instanceof Monument && carte.getNom().equals(nom))
				return ((Monument)carte).getIsBuild();
		}
		return false; 
	}
	//----------------------------------------------------------------------------------------------------------------
	//                                             GET
	public String     getNom       () { return this.nom;      }
	public boolean    getAcheter   () { return this.aAcheter; }
	public int        getSommeDe   () { return this.sommeDe ; }
	public int        getPiece     () {	return this.piece;    }
	public int        getNum       () {	return this.numJoueur;}

	//retourne une copie profonde de la list de carte
	public ArrayList<Carte> getListCartes() 
	{
		ArrayList<Carte> tmp = new ArrayList<Carte>();
		for (Carte carte : this.listCartes) 
		{
			tmp.add(new Carte (carte));
		}
		return tmp;
	}
	//retourne le nombre de carte d'un mem type
	public int getNbCarte(String type)
	{
		int i = 0 ;
		for (Carte carte : this.listCartes)
		{
			if(carte.getType().equals(type)) i++ ;
		}
		return i ; 
	}
	//----------------------------------------------------------------------------------------------------------------
	//                                             SET
	//ajoute les piece a piece existente
	public void setPiece(int piece) 
	{
		this.piece += piece;
	}
	//en cas d'achat modifie le boolean
	public void setAcheter(boolean b)
	{
		this.aAcheter=b;
	}
}
