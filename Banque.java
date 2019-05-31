import java.util.ArrayList;
public class Banque
{
	private ArrayList<Carte> cartes ;
	public Banque()
	{
		this.cartes = new ArrayList<Carte>();
	}

	public void ajouter(Carte carte)
	{
		this.cartes.add(carte);
	}

	public void supprimer(Carte carte)
	{
		this.cartes.remove(carte);
	}

	public ArrayList<Carte> getListCartes() 
	{
		ArrayList<Carte> tmp = new ArrayList<Carte>();

    	for(Carte carte : this.cartes)
    	{
    		tmp.add(carte);
    	}
    	return tmp ; 
	}

}