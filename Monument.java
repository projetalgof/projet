public class Monument extends Carte 
{
	private boolean isBuild; 
	
	public Monument(String declencheur,String nom,String type,Effet effet,int cout)
	{
		super(declencheur,nom,type,effet,cout);
		this.isBuild=false ;
	}
}