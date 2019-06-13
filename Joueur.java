import java.util.ArrayList;

public class Joueur 
{
	private static int[] jetDe;
	private static int   sommeDe;

	private String nom;
	private static int nbJoueur;
	private int numJoueur;
	private int piece;
	// liste de carte posseder par le joueur
	private ArrayList<Carte> listCartes;
	// tableau de 4 monument
	private Monument[] monuments;

	public Joueur(String nom) 
	{
		if(this.jetDe == null) this.jetDe = new int[2];
		this.nom = nom;
		this.listCartes = new ArrayList<Carte>();
		this.monuments = new Monument[4];
		this.numJoueur = Joueur.nbJoueur++;
	}

	public void gain(Joueur joueurActif,Controleur ctrl) 
	{
		for(Carte carte : this.listCartes) 
		{
			if(!carte.getClass().getName().equals("rouge"))
			{
				if ((carte.getDeclencheur().indexOf((Joueur.sommeDe + ""))) >= 0) carte.action(this,joueurActif,ctrl);
			}
		}
	}

	public void payer(Joueur joueurActif,Controleur ctrl)
	{
		for(Carte carte : this.listCartes) 
		{
			if(carte.getClass().getName().equals("rouge"))
			{
				if ((carte.getDeclencheur().indexOf((Joueur.sommeDe + ""))) >= 0)  carte.action(this,joueurActif,ctrl);
			}
		}
	}
	public void jetDe(int nbDe) {
		int resultat = 0;
		for (int i = 0; i < nbDe; i++)
		{
			this.jetDe[i] = (int)(Math.random() * 6 + 1);
			resultat += this.jetDe[i];
		}
		this.sommeDe=resultat;
	}

	public void ajouterCarte(Carte carte) 
	{
		this.listCartes.add(carte);
	}

	// get
	public String getNom() {
		return this.nom;
	}
	public int getSommeDe()
	{
		return this.sommeDe ;
	}

	public int getPiece() {
		return this.piece;
	}

	public Monument[] getMonuments() {
		return this.monuments;
	}

	public int getNum() {
		return this.numJoueur;
	}

	public ArrayList<Carte> getListCartes() {
		ArrayList<Carte> tmp = new ArrayList<Carte>();

		for (Carte carte : this.listCartes) {
			tmp.add(carte);
		}
		return tmp;
	}

	public int getNbCarte(String type)
	{
		int i = 0 ;
		for (Carte carte : this.listCartes)
		{
			if(carte.getType().equals(type)) i++ ;
		}
		return i ; 
	}

	// set
	public void setPiece(int piece) {
		this.piece += piece;
	}
}
