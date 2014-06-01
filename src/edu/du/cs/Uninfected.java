package edu.du.cs;

public abstract class Uninfected extends Human
{

	public Uninfected(int xIn, int yIn) {
		super(xIn, yIn);
		walkway = Simulate.hWalkway;
		vel = 1 + (int) Math.round( Math.random() * 2);
	}

	public void die() 
	{
		changeType('z');
	}

}
