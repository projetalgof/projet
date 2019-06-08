import java.util.*;
import java.util.regex.*;

public class IHM {
	private Controleur ctrl;

	public IHM(Controleur ctrl) {
		this.ctrl = ctrl;
	}

	public int debut() {
		Scanner sc = new Scanner(System.in);
		String nbjoueur = "";
		System.out.println("Bonjour est bienvenue sur le jeu MiniVille\n");
		System.out.println("Combien de joueur vont jouer ?");
		nbjoueur = sc.next();
		while (!Pattern.matches("[2-4]", nbjoueur)) {
			System.out.println("Le nombre de joueur est entre 2 et 4 ");
			nbjoueur = sc.next();
		}
		return Integer.valueOf(nbjoueur);
	}

	public String creeJoueur() {
		Scanner sc = new Scanner(System.in);
		System.out.println("Entrer nom du joueur : ");
		return sc.next();
	}

	public String commence() {
		Scanner sc = new Scanner(System.in);
		String joueur = "";
		System.out.println("Entrer nom du joueur qui commence");
		joueur = sc.next();
		while (this.ctrl.getMetier().rechercherJoueur(joueur) == null) {
			System.out.println("Erreur le joueur " + joueur + " n'existe pas");
			joueur = sc.next();
		}
		return joueur;
	}

	public char afficherMenu() {
		return 'a';
	}

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

		s += String.format("%6s|", getNbPieceJoueur(joueur)) + "\n";

		s += "+---------------------------------+\n";

		System.out.println(s);
	}

	private int getNbPieceJoueur(Joueur joueur) {
		return joueur.getPiece();
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
