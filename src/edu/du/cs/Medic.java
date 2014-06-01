package edu.du.cs;

public class Medic extends Uninfected  
{
	private int medicine;
	
	public Medic(int xIn, int yIn) {
		super(xIn, yIn);
		medicine = 3;
		type = 'm';
	}
	
	public void cure( Infected zombie )
	{
		if(medicine > 0)
		{
			medicine--;
//			if(Math.random() > .5)
//				zombie.changeType('n');
//			else
				die();
		}
		else
			die();
	}
	
	public int getMedicine()
	{
		return medicine;
	}
	
	public void findSupply( Supplies someSupply )
	{
		medicine += someSupply.getAmount();
	}
	
}
