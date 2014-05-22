package edu.du.cs;

public interface CharacterInterface 
{	
	public void move();
	public void changeType( String newType );
	public void die();
	public int getHP();
	public String getType();
	public int getX();
	public int getY();
	public int getVel();
}
