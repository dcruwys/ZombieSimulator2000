package edu.du.cs;

public class Human implements CharacterInterface 
{
	private int hp;
	private int myX;
	private int myY;
	private String type;
	private int vel;
	
	public Human(int x, int y) {
		hp = 10;
		this.generate();
		type = "Human";
		
		myX = x;
		myY = y;
	}
	
	@Override
	public void generate() 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void move() 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void changeType(String newType) 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void die() 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public int getHP() 
	{
		return hp;
	}

	@Override
	public String getType() 
	{
		return type;
	}

	@Override
	public int getX() 
	{
		return myX;
	}

	@Override
	public int getY() 
	{
		return myY;
	}

	@Override
	public int getVel() 
	{
		return vel;
	}

	public void starve()
	{
		
	}
}
