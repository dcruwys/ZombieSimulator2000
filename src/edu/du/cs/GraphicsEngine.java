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
	public void drawMap( int[][] grid ) {
		for(int row=0;row<grid.length; row++){
			   for(int col=0;col<grid.length;col++){
			     switch(grid[row][col]){
			      case 0:StdDraw.setPenColor( StdDraw.LIGHT_GRAY );
			      		StdDraw.filledSquare(row*10, col*10, 10);
			       break;
			      case 1:StdDraw.setPenColor( StdDraw.BLACK );
		      		StdDraw.filledSquare(row*10, col*10, 10);;
			       break;
			      case 9:StdDraw.setPenColor( StdDraw.LIGHT_GRAY );
		      		StdDraw.filledSquare(row*10, col*10, 10);;
			       break;
			     }
			   }  
		}
	}
	public static void main(String[] args){
<<<<<<< HEAD
		StdDraw.setCanvasSize(500, 500);
		StdDraw.setXscale(0.0, 1000.0);
		StdDraw.setYscale(0.0, 1000.0);
		System.out.println("error");
		GraphicsEngine g = new GraphicsEngine();

	//	Building b = new Building();
		Human h = new Human(300, 300);
		
		//g.drawBuildings(b);
		
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.filledSquare(100, 100, 20);
		g.drawHumans(h);
		//StdDraw.rectangle(, 25, 12, 12);
=======
		StdDraw.setCanvasSize(500,500);
		StdDraw.setXscale(0.0, 500.0);
		StdDraw.setYscale(0.0, 500.0);
		Simulate test = new Simulate();
		test.generateBuildings();
		
		GraphicsEngine g = new GraphicsEngine();
		g.drawMap(test.getGrid());
		Human paul = new Human(10, 10);
		while(true){
			paul.move();
			g.drawHuman(paul);
		}
>>>>>>> FETCH_HEAD
	}
	
}