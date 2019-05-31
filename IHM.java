import java.util.Scanner ;

public class IHM
{
	private Controleur ctrl ;
    
    public IHM(Controleur ctrl)
    {
        this.ctrl=ctrl;
    }

    public String creeJoueur()
    {
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Entrer nom du joueur : ") ;
    	return sc.nextLine();
    }
    public String commence()
    {
    	Scanner sc = new Scanner(System.in);
    	System.out.println("Entrer nom du joueur qui commence") ;
    	return sc.nextLine();
    }

    public char afficherMenu()
    {
    	return 'a';
    }
    public void afficherBanque()
    {

    }
    public void afficherCarteJoueur()
    {

    }
}