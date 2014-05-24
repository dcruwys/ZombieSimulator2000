package edu.du.cs;

public interface GraphicsInterface
{
	public void drawHuman( Human aHuman );
	
	public void drawZombie( Infected aZombie );
	
	public void drawMap(int[][] grid);
	
	public void drawSupply( Supplies someSupply );
}
