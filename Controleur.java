public class Controleur {
	private Metier metier;
	private IHM ihm;

	public Controleur() {
		this.ihm = new IHM(this);
		this.metier = new Metier(this, this.ihm.debut());

		do
			metier.jouer();

		while (this.metier.getIsEnd());
	}

	// methode lien
	public String creeJoueur() {
		return this.ihm.creeJoueur();
	}

	public String commence() {
		return this.ihm.commence();
	}

	public void afficherBanque() {
		this.ihm.afficherBanque();
	}

	public void afficherEtatJoueur(Joueur joueur) {
		this.ihm.afficherEtatJoueur(joueur);
	}

	public void afficherEtat() {
		this.ihm.afficherEtat();
	}

	public Metier getMetier() {
		return this.metier;
	}

	public IHM getIhm() {
		return this.ihm;
	}

	public Banque getBanque() {
		return this.metier.getBanque();
	}

	// main
	public static void main(String agrs[]) {
		new Controleur();
	}
}
