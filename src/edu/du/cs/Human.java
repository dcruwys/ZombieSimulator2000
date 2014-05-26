package edu.du.cs;

public class Human implements CharacterInterface 
{
	private int hp;
	private int x;
	private int y;
	private String type;
	private int vel;
	private int direction;
	private boolean panic;
	private int xVel;
	private int yVel;
	
	public Human(int xIn, int yIn) {
		hp = 10;
		type = "Human";
		direction = (int) (Math.random() * 360) % 360;
		vel = (int) (Math.ceil(Math.random()*2) + 3);
		x = xIn;
		y = yIn;
		
		panic = false;
	}
	
	@Override
	public void move() 
	{
		xVel = (int) (vel * Math.sin(direction));
		yVel = (int) (vel * Math.cos(direction));
		if(!panic){
			x += xVel;
			y += yVel;
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
		return x;
	}

	@Override
	public int getY() 
	{
		return y;
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
		if(x > 500){
			x = 500;
			direction = (int) (Math.random() * 360) % 360;
		}
		else if(y > 500){
			y = 500;
			direction = (int) (Math.random() * 360) % 360;
		}
		else if(x < -10){
			x = -10;
			direction = (int) (Math.random() * 360) % 360;
		}
		else if(y < -10){
			y = -10;
			direction = (int) (Math.random() * 360) % 360;	
		}
		//GraphicsEngine.grid[][]
		
	}
}
