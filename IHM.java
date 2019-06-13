import java.util.*;
import java.util.regex.*;

public class IHM 
{
	private Controleur ctrl;

	public IHM(Controleur ctrl) 
	{
		this.ctrl = ctrl;
	}
	//----------------------------------------------------------------------------------------------------------------
	//                                             GERE LES SCANNERS
	//affichage du debut de jeu et envois le nombre de joueur choisie
	public int debut() 
	{
		Scanner sc = new Scanner(System.in);
		String nbjoueur = "";
		System.out.println("Bonjour est bienvenue sur le jeu MiniVille\n");
		System.out.println("Combien de joueur vont jouer ?");
		nbjoueur = sc.next();
		while (!Pattern.matches("[2-4]", nbjoueur)) 
		{
			System.out.println("Le nombre de joueur est entre 2 et 4 ");
			nbjoueur = sc.next();
		}
		return Integer.valueOf(nbjoueur);
	}

	//affiche le menu pour les choix des action
	public char choix()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Que voulais vous faire");
		System.out.println("[A] Acheter un etablissement");
		System.out.println("[M] Acheter un monument");
		System.out.println("[B] Afficher la banque");
		System.out.println("[P] Passer");
		return sc.next().charAt(0);
	}
	//affiche et renvois le nom du joueur a crée
	public String creeJoueur() 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Entrer nom du joueur : ");
		return sc.next();
	}
	//affiche et verifie le joueur qui commenceras dans le jeu 
	public String commence() 
	{
		Scanner sc = new Scanner(System.in);
		String joueur = "";
		System.out.println("Entrer nom du joueur qui commence");
		joueur = sc.next();
		while (this.ctrl.getMetier().rechercherJoueur(joueur) == null) 
		{
			System.out.println("Erreur le joueur " + joueur + " n'existe pas");
			joueur = sc.next();
		}
		return joueur;
	}
	//----------------------------------------------------------------------------------------------------------------
	//                                             GERE LES ACHATS
	//affiche le choix de carte a acheter
	public String achat()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Quelle carte souhaiter vous acheter ?");
		String nomCarte = sc.next();
		if(!this.ctrl.getBanque().contains(nomCarte))
		{
			System.out.println("Nom de la carte inconue");
			return "" ; 
		}
		return nomCarte ;
	}
	//en cas d'achat valide
	public void achatValide(Joueur joueur , Carte carte)
	{
		System.out.println("le joueur "+ joueur.getNom() + " a acheter la carte : " + carte.getNom() +" pour "+carte.getCout()+" piece");
	}
	//en cas d'erreur
	public void achatErreur()
	{
		System.out.println("Vous ne disposer pas sufisament de piece");
	}
	//----------------------------------------------------------------------------------------------------------------
	//                                             GERE LES CARTES
	//affiche leffet d'une carte quand activer
	public void effetCarte(String nomJoueur,String nomCarte ,int piece)
	{
		System.out.println(nomJoueur + " carte : " + nomCarte +" gain de piece : " + piece ) ;
	}
	public void effetCartePaimment(String joueurActif , String joueurProprietaire,String nomCarte ,int nbPiece)
	{
		System.out.println(joueurActif + " à payer a : " + joueurProprietaire + " : " + nbPiece +" piece pour " + nomCarte);
	}
	//----------------------------------------------------------------------------------------------------------------
	//                                             GERE LES AFFICHAGE BRUT

	//affiche le jet de dé obtenue
	public void jetDe(Joueur joueur)
	{
		System.out.println("\njet de dé : " + joueur.getSommeDe()+"");
	}
	//affiche une transition au debut du tour d'un joueur
	public void transition(Joueur joueur)
	{
		System.out.println("-------------------------------------------------------------------------------------------------------");
		System.out.println("A "+joueur.getNom()+" DE JOUER");
		
	}
	//affiche l'etat general des joueurs
	public void afficherEtat() {
		System.out.println("__________________________________________");
		afficherBanque();

		for (Joueur j : this.ctrl.getMetier().getJoueurs()) {
			afficherEtatJoueur(j);
		}
		System.out.println("__________________________________________");
	}

	//affiche le contenue de la banque actuelle
	public void afficherBanque() {
		String s = "";
		String l = "+-----+--------------------+------+----+\n";
		EnumCarte[] tabEnum = EnumCarte.values();

		int nbTypeCarte = tabEnum.length;

		ArrayList<Carte> cartes = this.ctrl.getBanque().getListCartes();
		Collections.sort(cartes);
		s += "+--------------------------------------+\n";
		s += "|" + StringUtils.center("Banque", 38) + "|\n";
		s += l;
		for (int i = 0; i < nbTypeCarte; i++) {
			int cpt = 0;
			s += String.format("|%-5s", tabEnum[i].getDeclencheur());
			s += String.format("|%-20s|", tabEnum[i].getNom());
			for (Carte carte : cartes) {
				if (tabEnum[i].getNom().equals(carte.getNom()))
					cpt++;
			}
			s += String.format("%5dx|", cpt);
			s += String.format("%3d€|", tabEnum[i].getCout()) + "\n";

		}
		s += l;
		System.out.println(s);
	}
	//affiche les differente information concernant un joueur
	public void afficherEtatJoueur(Joueur joueur) {
		ArrayList<Carte> cartes = joueur.getListCartes();
		String s = "";
		String l = "+-----+--------------------+------+\n";

		s += "+---------------------------------+\n";
		s += "|" + StringUtils.center(joueur.getNom(), 33) + "|\n";
		s += l;

		while (!cartes.isEmpty()) {
			Carte carte = cartes.get(0);
			int cpt = 0;
			for (int i = 0; i < cartes.size(); i++) {
				if (carte.getNom().equals(cartes.get(i).getNom())) {
					cpt++;
					cartes.remove(i);
				}
			}
			s += String.format("|%-5s", carte.getDeclencheur());
			s += String.format("|%-20s|", carte.getNom());
			s += String.format("%5dx|", cpt) + "\n";
		}
		s += l;
		s += String.format("|%-26s|", "PIECES :");

		s += String.format("%6s|", joueur.getPiece() + "\n");

		s += "+---------------------------------+\n";

		System.out.println(s);
	}
}