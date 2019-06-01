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

	public Carte retirer(String nomCarte)
	{
		Carte tmp =null;
		for(Carte carte : cartes)
		{
			if(carte.getNom().equals(nomCarte))
			{
				tmp=carte ;
				break;
			}
		}
		if(tmp !=null)
		{
			this.cartes.remove(tmp);
			return tmp ;
		}
		return null ;
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