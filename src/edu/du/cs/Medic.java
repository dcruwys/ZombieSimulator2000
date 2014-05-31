package edu.du.cs;

public class Medic extends Human 
{
	private int medicine;
	
	public Medic(int xIn, int yIn) {
		super(xIn, yIn);
		changeType('m');
		vel = 1 + (int) Math.round( Math.random() * 2);
		medicine = 3;
	}

	@Override
	public void die() 
	{
		changeType('z');
		GraphicsEngine.drawHuman( this );
	}
	
	public void cure( Infected zombie )
	{
		if(medicine > 0)
		{
			medicine--;
			zombie.changeType('n');
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
	
	public void move()
	{
		if(panic)
		{
			//run away from zombie
		}
		else
			super.move();
	}
	
}
