package edu.du.cs;

import java.util.List;

public class Infected implements CharacterInterface 
{
	private int hp;
	private int myX;
	private int myY;
	private String type;
	private int vel;
	
	public Infected()
	{
		hp = 10;
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
		return myX;
	}

	@Override
	public int getY() 
	{ 
		return myY;
	}
	
	public void checkCollisions(){
//		if(myX >= 500){
//			myX = 500;
//			direction = (int) (Math.random() * 360) % 360;
//		}
//		if(myY >= 500){
//			myY = 500;
//			direction = (int) (Math.random() * 360) % 360;
//		}
//		if(myX <= 0){
//			myX = 0;
//			direction = (int) (Math.random() * 360) % 360;
//		}
//		if(myX <= 0){
//			myY = 0;
//			direction = (int) (Math.random() * 360) % 360;	
//		}
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


	@Override
	public int estimateDistance(Node node1, Node node2) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void aStar(Node start, Node goal) {
		// TODO Auto-generated method stub
	}
}
