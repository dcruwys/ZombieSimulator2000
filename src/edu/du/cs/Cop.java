package edu.du.cs;

public class Cop extends Uninfected
{
	private int ammo;
	
	public Cop(int xIn, int yIn) {
		super(xIn, yIn);
		type = 'c';
		ammo = 25;
	}
	
	public int getAmmo()
	{
		return ammo;
	}
	
	public void findSupply( Supplies someSupply )
	{
		ammo += (someSupply.getAmount() * 10);
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
