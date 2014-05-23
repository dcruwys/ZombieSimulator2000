package edu.du.cs;

public interface GraphicsInterface
{
	public void drawHuman( Human aHuman );
	
	public void drawZombie( Infected aZombie );
	
	public void drawBuilding(int[][] grid);
	
	public void drawSupply( Supplies someSupply );
}
