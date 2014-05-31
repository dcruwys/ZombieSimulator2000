package edu.du.cs;

public class Cop extends Human 
{
	private int ammo;
	
	public Cop(int xIn, int yIn) {
		super(xIn, yIn);
		changeType('c');
		vel = 1 + (int) Math.round( Math.random() * 2);
		ammo = 25;
	}

	@Override
	public void die() {
		changeType('z');	
		GraphicsEngine.drawHuman( this );
	}
	
	public int getAmmo()
	{
		return ammo;
	}
	
	public void findSupply( Supplies someSupply )
	{
		ammo += (someSupply.getAmount() * 10);
	}
	
	public void move()
	{
		if(panic)
		{
			attack( getInSight() );
		}
		else
			super.move();
	}
	
	public void attack( Infected zombie )
	{
		//attack zombie
	}
	
	public Infected getInSight()
	{
		return null;
	}
}
