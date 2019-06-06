public class Monument extends Carte {
	private boolean isBuild;

	public Monument(String declencheur, String nom, String type, Effet effet, int cout, char destination,
			boolean effetEstActive) {
		super(declencheur, nom, type, effet, cout, destination, effetEstActive);
		this.isBuild = false;
	}
}