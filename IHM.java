import java.util.Scanner ;
import java.util.ArrayList;

public class IHM
{
	private Controleur ctrl ;
    
    public IHM(Controleur ctrl)
    {
        this.ctrl=ctrl;
    }

    public int debut()
    {
    	Scanner sc = new Scanner(System.in);
    	int nbjoueur=0;
    	System.out.println("Bonjour est bienvenue sur le jeu MiniVille");
    	do
    	{
    		System.out.println("Combien de joueur vont joueur ?");
    		nbjoueur= sc.nextInt();
    		if(nbjoueur <2 || nbjoueur >4)
    		{
    			System.out.println("Le nombre de joueur est entre 2 et 4 ");
    		}
    	}
    	while(nbjoueur < 2 || nbjoueur >4);
    	return nbjoueur ;
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
    	ArrayList<Carte> cartes = this.ctrl.getBanque().getListCartes();
 		for(Carte carte : cartes)
 		{
 			System.out.println(carte.getNom());
 		}
    }
    public void afficherCarteJoueur(Joueur joueur)
    {
    	ArrayList<Carte> cartes = joueur.getListCartes();
    	for(Carte carte : cartes)
 		{
 			System.out.println(carte.getNom());
 		}
    }
}