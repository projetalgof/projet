public class Monument extends Carte 
{
	private boolean isBuild;

	public Monument(String nom,int cout) 
	{
		super("",nom, "monument",cout,0);
		this.isBuild = false;
	}

  //-------------------------------------------------------------------------------------------------------------------
  //----------------------------------------------------------GET------------------------------------------------------
	public boolean getIsBuild() { return this.isBuild ; }
}