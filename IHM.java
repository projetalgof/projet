import java.util.*;
import java.util.regex.*;

public class IHM {
	private Controleur ctrl;

	public IHM(Controleur ctrl) {
		this.ctrl = ctrl;
	}

	// ----------------------------------------------------------------------------------------------------------------
	// GERE LES SCANNERS
	// affichage du debut de jeu et envois le nombre de joueur choisie
	public char debut() {
		Scanner sc = new Scanner(System.in);
		char choix;
		System.out.println("Bonjour est bienvenue sur le jeu MiniVille\n");
		if(Controleur.DEBUG) System.out.println("DEBUG ACTIF");
		System.out.println("[N] Nouvelle Partie");
		System.out.println("[C] Charger");
		do
			choix = sc.next().charAt(0);
		while (choix != 'N' && choix != 'C');
		return choix;
	}

	public int nombreDeJoueur() {
		Scanner sc = new Scanner(System.in);
		String nbjoueur = "";
		System.out.println("Combien de joueur vont jouer ?");
		nbjoueur = sc.next();
		while (!Pattern.matches("[2-4]", nbjoueur)) {
			System.out.println("Le nombre de joueur est entre 2 et 4 ");
			nbjoueur = sc.next();
		}
		return Integer.valueOf(nbjoueur);
	}

	public String nomFichier() {
		System.out.println("Entrer le nom du fichier de sauvegarde");
		Scanner sc = new Scanner(System.in);
		return sc.nextLine();
	}

	// affiche le menu pour les choix des action
	public char choix() 
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("Que voulais vous faire");
		System.out.println("[A] Acheter un etablissement");
		System.out.println("[M] Acheter un monument");
		System.out.println("[B] Afficher la banque");
		System.out.println("[R] Relancer les dé");
		System.out.println("[P] Passer");
		if(Controleur.DEBUG)System.out.println("[Z] DEBUG donner 30 piece");
		return sc.next().charAt(0);
	}

	// affiche le menu choix pour le jet de
	public char choixDe() 
	{
		Scanner sc = new Scanner(System.in);
		char choix;
		System.out.println("Combien de dé voulais vous lancer");
		do {
			System.out.println("[1] ou [2]");
			choix = sc.next().charAt(0);
		} while (choix != '1' && choix != '2');
		return choix;

	}

	// affiche et renvois le nom du joueur a crée
	public String creeJoueur() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Entrer nom du joueur : ");
		return sc.nextLine();
	}

	// affiche et verifie le joueur qui commenceras dans le jeu
	public String commence() {
		Scanner sc = new Scanner(System.in);
		String joueur = "";
		System.out.println("Entrer nom du joueur qui commence");
		joueur = sc.nextLine();
		while (this.ctrl.getMetier().rechercherJoueur(joueur) == null) {
			System.out.println("Erreur le joueur " + joueur + " n'existe pas");
			joueur = sc.nextLine();
		}
		return joueur;
	}

	// ----------------------------------------------------------------------------------------------------------------
	// GERE LES ACHATS
	// affiche le choix de carte a acheter
	public String achatEtablissement() {
		boolean inconnue = true;
		Scanner sc = new Scanner(System.in);
		System.out.println("Quelle etablissement souhaiter vous acheter ?");
		String nomCarte = sc.nextLine();

		for (EnumCarte carte : EnumCarte.values()) {
			if (carte.getNom().equals(nomCarte))
				inconnue = false;
		}

		if (inconnue) {
			System.out.println("Nom de la carte inconnue");
			return "";
		}

		if (!this.ctrl.getBanque().contains(nomCarte)) {
			System.out.println("Cartes déja toutes achetées");
			return "";
		}
		return nomCarte;
	}

	public String achatMonument() {
		Scanner sc = new Scanner(System.in);
		String nomMonument = "";
		System.out.println("Quelle monuments souhaiter vous acheter ?");
		System.out.println("[G] Gare");
		System.out.println("[C] Centre commercial");
		System.out.println("[P] Parc d'atraction");
		System.out.println("[T] Tour radio");
		char choix = sc.next().charAt(0);
		if (choix != 'G' && choix != 'C' && choix != 'P' && choix != 'T') {
			System.out.println("erreur de choix");
			return "";
		}
		switch (choix) {
		case 'G':
			nomMonument = "gare";
			break;
		case 'C':
			nomMonument = "centre commercial";
			break;
		case 'P':
			nomMonument = "parc d'atraction";
			break;
		case 'T':
			nomMonument = "tour de radio";
			break;
		}

		return nomMonument;
	}

	// en cas d'achat valide
	public void achatValide(Joueur joueur, Carte carte) {
		String l = "+---------------+--------------------+------+\n";
		String s = "+-------------------------------------------+\n";
		s += "|" + StringUtils.center("Achat", 43) + "|" + "\n";
		s += l;
		s += String.format("|%-15s|%-20s|%5d€|", joueur.getNom(), carte.getNom(), carte.getCout()) + "\n";
		s += l;

		System.out.println(s);
	}

	// en cas d'erreur
	public void achatErreur() {

		String l = "+-------------------------------------------+\n";
		String s = l;
		s += "|" + StringUtils.center("Erreur Achat", 43) + "|" + "\n";
		s += l;
		s += "|" + StringUtils.center("Nombre de pieces insuffisante", 43) + "|" + "\n";
		s += l;
		System.out.println(s);
	}

	public void achatMonumentErreur() {
		String l = "+-------------------------------------------+\n";
		String s = l;
		s += "|" + StringUtils.center("Erreur Construction", 43) + "|" + "\n";
		s += l;
		s += "|" + StringUtils.center("Le monument choisi est deja construis", 43) + "|" + "\n";
		s += l;
		System.out.println(s);
	}

	// ----------------------------------------------------------------------------------------------------------------
	// GERE LES CARTES
	// affiche leffet d'une carte quand activer
	public void effetCarte(String nomJoueur, String nomCarte, int piece) {
		String l = "+---------------+--------------------+------+\n";
		String s = "+-------------------------------------------+\n";
		s += "|" + StringUtils.center("Effect Obtennue", 43) + "|" + "\n";
		s += l;
		s += String.format("|%-15s|%-20s|%5d€|", nomJoueur, nomCarte, piece) + "\n";
		s += l;

		System.out.println(s);
	}

	public void effetCartePaimment(String joueurActif, String joueurProprietaire, String nomCarte, int nbPiece) {
		String l = "+---------------+--------------------+-------+----------------+\n";
		String s = "+-------------------------------------------------------------+\n";
		s += "|" + StringUtils.center("Effect Subie", 61) + "|" + "\n";
		s += l;
		s += String.format("|%-15s|%-20s|%7s|%16s|", "Joueur Perdant", "nomCarte", "nbPiece", "joueur Gagnant") + "\n";
		s += l;
		s += String.format("|%-15s|%-20s|%6d€|->%14s|", joueurActif, nomCarte, nbPiece, joueurProprietaire) + "\n";
		s += l;

		System.out.println(s);

	}

	// ----------------------------------------------------------------------------------------------------------------
	// GERE LES AFFICHAGE BRUT
	public void rejouer(Joueur joueur)
	{
		System.out.println("Le joueur : " + joueur.getNom() +"a fait un double , il rejoue");
	}
	// affiche une erreur dans le lancer de de
	public void gagner(Joueur joueur) {
		System.out.println("Le joueur : " + joueur.getNom() + " a ganer . BRAVO !!!");
	}

	public void erreurLanceDe() {
		System.out.println("Vous n'avais pas la tour radio ou avais deja lancer vos deux ce tour si");
	}

	// affiche le jet de dé obtenue
	public void jetDe(Joueur joueur) {
		System.out.println("1er de --> "+ joueur.getDe(0) + (joueur.getDe(1) > 0 ? "  2eme de --> : " + joueur.getDe(1) : ""));
		System.out.println("\njet de dé : " + joueur.getSommeDe() + "");
	}

	// affiche une transition au debut du tour d'un joueur
	public void transition(Joueur joueur) {
		System.out.println(
				"-------------------------------------------------------------------------------------------------------");
		System.out.println("A " + joueur.getNom() + " DE JOUER");

	}

	// affiche l'etat general des joueurs
	public void afficherEtat() {
		System.out.println("__________________________________________");
		afficherBanque();

		for (Joueur j : this.ctrl.getMetier().getJoueurs()) {
			afficherEtatJoueur(j);
		}
		System.out.println("__________________________________________");
	}

	// affiche le contenue de la banque actuelle
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
			String tmp = "";
			int cpt = 0;
			tmp += String.format("|%-5s", tabEnum[i].getDeclencheur());
			tmp += String.format("|%-20s|", tabEnum[i].getNom());
			for (Carte carte : cartes) {

				if (tabEnum[i].getNom().equals(carte.getNom()))
					cpt++;
			}
			tmp += String.format("%5dx|", cpt);
			tmp += String.format("%3d€|", tabEnum[i].getCout()) + "\n";
			if (cpt > 0)
				s += tmp;

		}
		s += l;
		System.out.println(s);
	}

	// affiche les differente information concernant un joueur
	public void afficherEtatJoueur(Joueur joueur) {
		ArrayList<Carte> cartes = joueur.getListCartes();
		ArrayList<Monument> ensMonument = new ArrayList<Monument>();

		String s = "";
		String l = "+-----+--------------------+------+\n";

		s += "+---------------------------------+\n";
		s += "|" + StringUtils.center(joueur.getNom(), 33) + "|\n";
		s += l;

		while (!cartes.isEmpty()) {
			Carte carte = cartes.get(0);
			int cpt = 0;

			if (carte instanceof Monument) {
				ensMonument.add((Monument) carte);
				cartes.remove(carte);
			} else {

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
		}
		s += l;

		for (Monument monument : ensMonument) {
			s += String.format("|%-5s", monument.getIsBuild() ? "Oui" : "Non");
			s += String.format("|%-20s|", monument.getNom());
			s += String.format("%5s€|", monument.getCout()) + "\n";

		}
		s += l;
		s += String.format("|%-26s|", "PIECES :");

		s += String.format("%6s|", joueur.getPiece()) + "\n";
		;

		s += "+---------------------------------+\n";

		System.out.println(s);
	}
	//----------------------------------------------------------------------------------------------------------------
	//                                             DEBUG
	public int debugJetDe()
	{
		Scanner sc = new Scanner(System.in);
		System.out.println("DEBUG : choisir le jet de dé");
		return sc.nextInt();
	}

	/*// --------------------------------------------------violet
	// choisit un joueur
	public String choisitUnJoueur() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Choisir un Joueur");
		return sc.nextLine();
	}
	public void erreurSaisrNomJoueur() {
		System.out.println("Saisir un nom correct");
	}
	public String choisitUnCarte() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Choisir un Carte");
		return sc.nextLine();
	}
	public void erreurSaisrNomCarte() {
		System.out.println("Saisir un nom carte");
	}
	public void donnerLeCarte(Joueur joueur) {
		System.out.println("Choisir un Carte et donner à" + joueur.getNom());
	}*/
}