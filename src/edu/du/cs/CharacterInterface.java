package edu.du.cs;

import java.util.List;

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
	public void checkCollisions();
	public int estimateDistance(Node node1, Node node2);
	public void aStar(Node start, Node goal);
}
