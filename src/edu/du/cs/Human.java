package edu.du.cs;

public class Human implements CharacterInterface 
{
	private int hp;
	private int myX;
	private int myY;
	private String type;
	private int vel;
	private int direction;
	private boolean panic;
	
	public Human(int x, int y) {
		hp = 10;
		type = "Human";
		direction = (int) (Math.random() * 360) % 360;
		vel = (int) (Math.ceil(Math.random()*2) + 2)/2;
		myX = x;
		myY = y;
		panic = false;
	}
	
	@Override
	public void move() 
	{
		if(!panic){
			myX += vel * Math.sin(direction);
			myY += vel * Math.cos(direction);
		}
		this.checkCollisions();
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
	
	public boolean getPanic(){
		return panic;
	}

	public void starve()
	{
		
	}
	public void checkCollisions(){
		if(myX > 500){
			myX = 500;
			direction = (int) (Math.random() * 360) % 360;
		}
		if(myY > 500){
			myY = 500;
			direction = (int) (Math.random() * 360) % 360;
		}
		if(myX < -10){
			myX = -10;
			direction = (int) (Math.random() * 360) % 360;
		}
		if(myY < -10){
			myY = -10;
			direction = (int) (Math.random() * 360) % 360;	
		}
	}
}
