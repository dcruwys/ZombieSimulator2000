package edu.du.cs;

public class Infected extends Human 
{
	private int hp;

	public Infected(int xIn, int yIn) {
		super(xIn, yIn);
		changeType('z');
		hp = 10;
		vel = 1 + (int) Math.floor(Math.random() * 6);
	}

	@Override
	public void die() 
	{
		//removes zombie from map
	}
	
	public int getHP()
	{
		return hp;
	}
	
	public void move()
	{
		if(panic)
		{
			//chases human
		}
		else 
			super.move();
	}

}
