package edu.du.cs;

public class Supplies {
	
	private int quantity;
	private int myX;
	private int myY;

	public Supplies(int x, int y)
	{
		myX = x;
		myY = y;
		quantity = (int) Math.ceil( Math.random() * 5);
	}
	
	public int getAmount()
	{
		return quantity;
	}
	
	public int getX()
	{
		return myX;
	}
	
	public int getY()
	{
		return myY;
	}
}
