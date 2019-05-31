import java.util.ArrayList;
public class Regle
{
	private static final int PIECE_DEPART = 3 ;

	public static void initialisation(ArrayList<Joueur> joueurs,Banque banque)
	{
		//Carte champs      = new Carte("1"  ,"champs de ble","grain"  ,new Effet(1,"*"),1);
		//Carte boulangerie = new Carte("2-3","boulangerie"  ,"produit",new Effet(1,"+"),1);
		Carte champs = EnumCarte.champs.creeCarte();
		Carte boulangerie = EnumCarte.boulangerie.creeCarte();
		for(Joueur joueur : joueurs)
		{
			joueur.setPiece(PIECE_DEPART);
			joueur.ajouterCarte(new Carte(champs));
			joueur.ajouterCarte(new Carte(boulangerie));
		}
	}

	public static void tourDeJeu()
	{
		
	}
}