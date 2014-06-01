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
	
	public void attack(Infected zombie )
	{
		StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
		StdDraw.filledCircle(x, x, 4);
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledCircle(zombie.getX(), zombie.getY(), 2);
	}
	
	public Infected getInSight()
	{
		//if(Infected.)
		return null;
	}
	
}
