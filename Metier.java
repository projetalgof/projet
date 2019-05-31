import java.util.ArrayList;
public class Metier
{
    private Controleur        ctrl ;
    private boolean           isEnd ; // pour la savoir la fin de parti
    private ArrayList<Joueur> joueurs ; // list des joueur dans la parti
    private Joueur            joueurActif ; // tour du joueur
    private Banque            banque ; // banque du jeu
    
    public Metier(Controleur ctrl,int nbJoueur)
    {
        this.ctrl=ctrl;
        this.isEnd=false;
        this.joueurs = new ArrayList<Joueur>();
        this.banque = new Banque() ;

        for(int i=0;i<nbJoueur;i++)
        {
        	this.joueurs.add(new Joueur(ctrl.creeJoueur()));
        }

        Regle.initialisation(joueurs,banque);

    }

    public void jouer()
    {
    	if(joueurActif== null)
    	{
    		for(Joueur joueur : this.joueurs)
    		{
    			if(joueur.getNom().equals(this.ctrl.commence())) joueurActif=joueur;
    		}
    	}

    }

    //get
    public boolean getIsEnd       () { return this.isEnd ;       }
    public Joueur  getJoueurActif () { return this.joueurActif ; }
    //retourn un copie de la list de joueur
    public ArrayList<Joueur> getJoueurs()
    {
    	ArrayList<Joueur> tmp = new ArrayList<Joueur>();

    	for(Joueur joueur : this.joueurs)
    	{
    		tmp.add(joueur);
    	}
    	return tmp ;
    }
}
