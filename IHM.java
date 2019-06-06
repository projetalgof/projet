import java.util.*;

public class IHM {
	private Controleur ctrl;

	public IHM(Controleur ctrl) {
		this.ctrl = ctrl;
	}

	public int debut() {
		Scanner sc = new Scanner(System.in);
		int nbjoueur = 0;
		System.out.println("Bonjour est bienvenue sur le jeu MiniVille");
		do {
			System.out.println("Combien de joueur vont jouer ?");
			nbjoueur = sc.nextInt();
			if (nbjoueur < 2 || nbjoueur > 4) {
				System.out.println("Le nombre de joueur est entre 2 et 4 ");
			}
		} while (nbjoueur < 2 || nbjoueur > 4);
		return nbjoueur;
	}

	public String creeJoueur() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Entrer nom du joueur : ");
		return sc.nextLine();
	}

	public String commence() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Entrer nom du joueur qui commence");
		return sc.nextLine();
	}

	public char afficherMenu() {
		return 'a';
	}

	public void afficherBanque() {
		String s = "";
		String l = "+--------------------+-----+\n";
		EnumCarte[] tabEnum = EnumCarte.values();

		int nbTypeCarte = tabEnum.length;

		ArrayList<Carte> cartes = this.ctrl.getBanque().getListCartes();
		Collections.sort(cartes);
		s += "+--------------------------+\n";
		s += "|" + StringUtils.center("Banque", 26) + "|\n";
		s += l;
		for (int i = 0; i < nbTypeCarte; i++) {
			int cpt = 0;

			s += String.format("|%-20s|", tabEnum[i].getNom());
			for (Carte carte : cartes) {
				if (tabEnum[i].getNom().equals(carte.getNom()))
					cpt++;
			}
			s += String.format("%5d|", cpt) + "\n";
		}
		s += l;
		System.out.println(s);
	}

	public void afficherEtatJoueur(Joueur joueur) {
		ArrayList<Carte> cartes = joueur.getListCartes();
		String s = "";
		String l = "+--------------------+-----+\n";

		s += "+--------------------------+\n";
		s += "|" + StringUtils.center(joueur.getNom(), 26) + "|\n";
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
			s += String.format("|%-20s|", carte.getNom());
			s += String.format("%5d|", cpt) + "\n";
		}
		s += l;
		s += String.format("|%-20s|", "PIECES :");

		s += String.format("%5s|", getNbPieceJoueur(joueur)) + "\n";

		s += l;

		System.out.println(s);
	}

	public int getNbPieceJoueur(Joueur joueur) {
		int cpt = 0;

		int[] pieces = this.ctrl.getMetier().getPiece();
		for (int i = 0; i < pieces.length; i++) {
			cpt += pieces[i];
		}
		return cpt;
	}

	public void afficherEtat() {
		System.out.println("__________________________________________");
		afficherBanque();

		for (Joueur j : this.ctrl.getMetier().getJoueurs()) {
			afficherEtatJoueur(j);
		}
		System.out.println("__________________________________________");
	}
}
