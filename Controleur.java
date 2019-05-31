public class Controleur
{
    private Metier metier ;
    private IHM    ihm ;
    
    public Controleur()
    {
        this.metier = new Metier(this,2);
        this.ihm    = new IHM(this) ;

        do
        	metier.jouer();

        while(this.metier.getIsEnd());
    }

    //methode lien
	public String creeJoueur()
	{
		return this.ihm.creeJoueur();
	}
	public String commence()
	{
		return this.ihm.commence();
	}


    public static void main (String agrs[])
    {
    	new Controleur();
    }
}
