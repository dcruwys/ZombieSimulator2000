package edu.du.cs;

public class GraphicsEngine implements GraphicsInterface 
{

	@Override
	public void drawHuman( Human aHuman ) {
		
		if( aHuman.getType().equalsIgnoreCase( "medic" ))
			StdDraw.setPenColor( StdDraw.RED );
		else if( aHuman.getType().equalsIgnoreCase( "cop" ))
			StdDraw.setPenColor( StdDraw.BLUE );
		else if( aHuman.getType().equalsIgnoreCase( "human" ))
			StdDraw.setPenColor( StdDraw.PINK );
		
		StdDraw.filledSquare( aHuman.getX(), aHuman.getY(), 2);
	}

	@Override
	public void drawZombie(Infected aZombie) {
		
		StdDraw.setPenColor( StdDraw.GREEN );
		
		if( aZombie.getType().equalsIgnoreCase( "tank" ))
			StdDraw.filledSquare( aZombie.getX(), aZombie.getY(), 4);
		else
			StdDraw.filledSquare( aZombie.getX(), aZombie.getY(), 2);
	}

	@Override
	public void drawSupply(Supplies someSupply) {
		
		StdDraw.setPenColor( StdDraw.ORANGE );
		
		StdDraw.filledCircle( someSupply.getX(), someSupply.getY(), 1);
	}

	@Override
	public void drawBuilding( int[][] grid ) {
		
		StdDraw.setPenColor( StdDraw.BLACK );
	
	}
}