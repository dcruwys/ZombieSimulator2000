package edu.du.cs;

public class Infected implements CharacterInterface 
{
	private int hp;
	private int x;
	private int y;
	private String type;
	private int vel;
	
	public Infected()
	{
		hp = 10;
		this.generate();
		type = "Infected";
	}
	
	@Override
	public void move() 
	{
		
	}

	@Override
	public void changeType( String newType ) 
	{
		
	}

	@Override
	public void die() 
	{
		
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
		return x;
	}

	@Override
	public int getY() 
	{ 
		return y;
	}

	
	@Override
	public void generate() 
	{
		// Ensures not in building
		
	}
	
	public void decay()
	{
		
	}
	
	public void infect( Human aHuman )
	{
		
	}


	@Override
	public int getVel() 
	{
		return vel;
	}
}
