import java.util.*;

public class Carte implements Comparable<Carte> {
	private String declencheur; // String pour gerais les declencheur multiple
	private String nom;
	private String type;
	private boolean effetEstActive;
	private char destination;
	private Effet effet;
	private int cout;

	public Carte(String declencheur, String nom, String type, Effet effet, int cout, char destination,
			boolean effetEstActive) {
		this.declencheur = declencheur;
		this.nom = nom;
		this.type = type;
		this.effet = effet;
		this.cout = cout;
		this.destination = destination;
		this.effetEstActive = effetEstActive;
	}

	// constructeur par recopie
	public Carte(Carte autreCarte) {
		this.declencheur = autreCarte.declencheur;
		this.nom = autreCarte.nom;
		this.type = autreCarte.type;
		this.effet = autreCarte.effet;
		this.cout = autreCarte.cout;
	}

	// get
	public String getDeclencheur() {
		return this.declencheur;
	}

	public String getNom() {
		return this.nom;
	}

	public String getType() {
		return this.type;
	}

	public Effet getEffet() {
		return this.effet;
	}

	public int getCout() {
		return this.cout;
	}

	public char getDestination() {
		return this.destination;
	}

	public int compareTo(Carte c) {
		return this.nom.compareTo(c.nom);
	}

}
