package edu.du.cs;

public class Medic extends Uninfected  
{
	private int medicine;
	
	public Medic(int xIn, int yIn) {
		super(xIn, yIn);
		medicine = 3;
		type = 'm';
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
