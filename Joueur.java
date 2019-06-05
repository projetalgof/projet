import java.util.ArrayList;
public class Joueur
{
	private static int jetDe ;

	private        String nom ;
	private static int    nbJoueur;
	private        int    numJoueur;
	private        int    piece ;
	//liste de carte posseder par le joueur
	private ArrayList<Carte> listCartes;
	//tableau de 4 monument
	private Monument[] monuments ;

	public Joueur (String nom)
	{
		this.nom=nom;
		this.listCartes = new ArrayList<Carte>();
		this.monuments= new Monument[4];
		this.numJoueur = Joueur.nbJoueur++;
	}
	
	public void maj(Joueur joueurActif)
	{
		for(Carte carte : this.listCartes)
		{
			if(((Joueur.jetDe+"").indexOf​(carte.getDeclencheur()) >= 0))
			{
				if      (carte.getDestination() == 'B') piece+= carte.getEffet().getPiece(this.listCartes);
				else if (carte.getDestination() == 'J')
				{
					if (joueurActif.getPiece()-carte.getEffet().getPiece(this.listCartes) >=0 )
					{
						joueurActif.setPiece(-carte.getEffet().getPiece(this.listCartes))
						this.pieces+=carte.getEffet().getPiece(this.listCartes);
					}
					else
					{
						this.pieces+=joueurActif.getPiece();
						joueurActif.setPiece(-joueurActif.getPiece());
					}
				}
			}
		}
	}

	public void jetDe(int nbDe)
	{
		Joueur.jetDe=0;
		for (int i=0;i<nbDe;i++)
			Joueur.jetDe+=Math.random() * 6 +1;
	}

	public void ajouterCarte(Carte carte)
	{
		this.listCartes.add(carte);
	}

	//get
	public String           getNom        () { return this.nom ;        }
	public int              getPiece      () { return this.piece ;      }
	public Monument[]       getMonuments  () { return this.monuments ;  }
	public int              getNum        () { return this.numJoueur;   }

	public ArrayList<Carte> getListCartes ()
	{
		ArrayList<Carte> tmp = new ArrayList<Carte>();

    	for(Carte carte : this.listCartes)
    	{
    		tmp.add(carte);
    	}
    	return tmp ;
	}
	//set
	public void setPiece(int piece) {this.piece+=piece ;}
}
