public class Monument extends Carte {
	private boolean isBuild;

	public Monument(String declencheur, String nom, String type,int cout) {
		super(declencheur, nom, type,cout,0);
		this.isBuild = false;
	}
}