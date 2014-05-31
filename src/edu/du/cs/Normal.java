package edu.du.cs;

public class Normal extends Human
{

	public Normal(int xIn, int yIn) {
		super(xIn, yIn);
		changeType('n');
		vel = 1 + (int) Math.round( Math.random() * 2);
	}

	@Override
	public void die() {
		changeType('z');	
		GraphicsEngine.drawHuman( this );
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
