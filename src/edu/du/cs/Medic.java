package edu.du.cs;

public class Medic extends Human 
{
	public int cure;
	
	public Medic(int x, int y){
		super(x, y);
		cure = 0;
		this.changeType("Medic");
	}
	
	public void findSupplies( Supplies someSupply )
	{
		//increases cure count
	}
	
	public void useCure( Infected zombie )
	{
		cure--;
		//Cures an infected
	}

	public int getCure()
	{
		return cure;
	}
}
