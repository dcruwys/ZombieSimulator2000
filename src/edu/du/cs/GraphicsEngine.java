package edu.du.cs;

import java.util.ArrayList;


public class GraphicsEngine implements GraphicsInterface 
{	
	public static int[][] grid;
	public static ArrayList<Node> walkwayNodes;
	public ArrayList<Human> humans;

	public int random;
	public Node startNode;
	public Node randomNode;
	
	public GraphicsEngine(){
		Simulate.generateBuildings();
		walkwayNodes = Simulate.walkway;
		grid = Simulate.grid;
		humans = new ArrayList<Human>(); //Create an array list of humans.
		for(Node n: walkwayNodes)
			n.setAdjacent();
	}
	
	@Override
	public void drawHuman( Human aHuman ) {
		if( aHuman.getType().equalsIgnoreCase( "medic" )) //if human is medic, make it red
			StdDraw.setPenColor( StdDraw.RED );
		else if( aHuman.getType().equalsIgnoreCase( "cop" )) //if human is cop make it blue
			StdDraw.setPenColor( StdDraw.BLUE );
		else if( aHuman.getType().equalsIgnoreCase( "human" ))//if human is normal make it pink
			StdDraw.setPenColor( StdDraw.MAGENTA );
		
		StdDraw.filledSquare( aHuman.getX(), aHuman.getY(), 4); //draw human
	}

	@Override
	public void drawZombie(Infected aZombie) {
		
		StdDraw.setPenColor( StdDraw.GREEN ); //make all zombies green
		
		if( aZombie.getType().equalsIgnoreCase( "tank" )) //if tank make it bigger
			StdDraw.filledSquare( aZombie.getX(), aZombie.getY(), 4); //draw tank
		else
			StdDraw.filledSquare( aZombie.getX(), aZombie.getY(), 2); // draw zombie
	}

	@Override
	public void drawSupply(Supplies someSupply) {
		
		StdDraw.setPenColor( StdDraw.ORANGE ); //make all supplies orange
		
		StdDraw.filledCircle( someSupply.getX(), someSupply.getY(), 4); //draw supplies
		
	}

	@Override
	public void drawMap( int[][] grid ) {
		for(int row=0;row<grid.length; row++){
			   for(int col=0;col<grid.length;col++){
			     switch(grid[row][col]){
			      case 0:StdDraw.setPenColor( StdDraw.LIGHT_GRAY ); //if the tile is 0, draw white
			      		StdDraw.filledSquare(row*10-4, col*10-4, 6); //draw tile
			       break;
			      case 1:StdDraw.setPenColor( StdDraw.BLACK ); //if the tile is 1, draw Gray
		      		    StdDraw.filledSquare(row*10-4, col*10-4, 6); //draw tile
			       break;
			      case 8:StdDraw.setPenColor( StdDraw.BLACK ); //if the tile is 1, draw Gray
	      		    StdDraw.filledSquare(row*10-4, col*10-4, 6); //draw tile
	      		   break;
			     }
			   }  
		}
	}
	
	public Node randomNode(){
		Node tempNode = null;
		random = (int )(Math.random() * walkwayNodes.size());
		if(walkwayNodes.get(random).isWalkable() == true){
			tempNode = walkwayNodes.get(random);
		} else if((walkwayNodes.get(random).isWalkable() == false)){
			return randomNode();
		}
		if(tempNode != null){
			return tempNode;
		}else {
			return randomNode();
		}
	}
	
	public static void main(String[] args){
		GraphicsEngine g = new GraphicsEngine();
		g.draw(g);
	}
	
	public void draw(GraphicsEngine g){
		StdDraw.setCanvasSize(500, 500); //Set Canvas size is set to 500, 500
		StdDraw.setXscale(0.0, 500.0); //Set scale to 500
		StdDraw.setYscale(0.0, 500.0); //Set scale to 500

		for(int i = 0; i < 5; i++){
			Node randomN = g.randomNode();
			Human aHuman = new Human(randomN.getX(), randomN.getY());
			humans.add(aHuman);
	    	aHuman.aStar(randomN, g.randomNode());
 		}
		
		while(true){
			StdDraw.clear();
		    g.drawMap(grid);
		    for(Human h : humans){
		    	g.drawHuman(h);
		    	if(h.getPath().size() != 0){
		    		h.move();
		    	}
		    }
	   }
	}
}