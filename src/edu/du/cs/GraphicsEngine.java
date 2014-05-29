package edu.du.cs;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Random;

public class GraphicsEngine implements GraphicsInterface 
{
	//Nodes, Nodes, Nodes
	
	public static int[][] grid;
	public static ArrayList<Node> walkwayNodes;
	public Simulate test;
	public int random;
	public Node startNode;
	public Node randomNode;
	
	public GraphicsEngine(){
		test = new Simulate();
		test.generateBuildings();
		walkwayNodes = test.getWalkwayNodes();
		grid = test.getGrid();
		for(Node n: walkwayNodes)
			n.setAdjacent();
	}
	
	@Override
	public void drawHuman( Human aHuman ) {
		if( aHuman.getType().equalsIgnoreCase( "medic" ))
			StdDraw.setPenColor( StdDraw.RED );
		else if( aHuman.getType().equalsIgnoreCase( "cop" ))
			StdDraw.setPenColor( StdDraw.BLUE );
		else if( aHuman.getType().equalsIgnoreCase( "human" ))
			StdDraw.setPenColor( StdDraw.PINK );
		
		StdDraw.filledSquare( aHuman.getX(), aHuman.getY(), 4);
		StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
		StdDraw.filledSquare(aHuman.getX()-1, aHuman.getY()-1, 2);
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
		
		StdDraw.filledCircle( someSupply.getX(), someSupply.getY(), 4);
		
	}

	@Override
	public void drawMap( int[][] grid ) {
		for(int row=0;row<grid.length; row++){
			   for(int col=0;col<grid.length;col++){
			     switch(grid[row][col]){
			      case 0:StdDraw.setPenColor( StdDraw.LIGHT_GRAY );
			      		StdDraw.filledSquare(row*10, col*10, 10);
			      		
//			      		StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
//						StdDraw.setPenRadius(0.005);
//						StdDraw.point((row*10-5), (col*10-5));
//						System.out.println("X: " + (row*10-5) + " Y: " + (col*10-5));
			       break;
			      case 1:StdDraw.setPenColor( StdDraw.BLACK );
		      		StdDraw.filledSquare(row*10, col*10, 10);
			       break;
			      case 9:StdDraw.setPenColor( StdDraw.LIGHT_GRAY );
		      		StdDraw.filledSquare(row*10, col*10, 10);
		      		
//		      		StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
//					StdDraw.setPenRadius(0.005);
//					StdDraw.point((row*10-5), (col*10-5));
			       break;
			     }
			   }  
		}
		//printWalkwayNodes();

	}
	public void randomNode(){
		random = (int )(Math.random() * test.getWalkwayNodes().size());
		System.out.println("random: " + random);
		if(walkwayNodes.get(random).isWalkable() == true){
			randomNode = walkwayNodes.get(random);
			System.out.println("random: " + random);
			System.out.println("walkway size: " + test.getWalkwayNodes().size());
		} else if(walkwayNodes.get(random).isWalkable() == false){
			System.out.println("Doh!");
			randomNode();
		}
	}
	
	public static void main(String[] args){
		GraphicsEngine g = new GraphicsEngine();
		g.draw(g);
	}
	
	public void draw(GraphicsEngine g){

		StdDraw.setCanvasSize(500, 500);
		StdDraw.setXscale(0.0, 500.0);
		StdDraw.setYscale(0.0, 500.0);
	
		
		System.out.println("random: " + random);
		System.out.println("walkway size: " + test.getWalkwayNodes().size());
		g.randomNode();
		startNode = randomNode;
		//start node doesnt have proper adjacent, but why?
		g.randomNode();
		Node aRandomNode = randomNode;
		System.out.println(startNode.getAdjacentNodes().size());
		Human paul = new Human((startNode.getX()), (startNode.getY()));
		System.out.println("Paul X and Y:" + paul.getX() + " " + paul.getY());
		paul.aStar(startNode, aRandomNode);
		System.out.println("A Star Path Size: " + paul.getPath().size());
		Iterator<Node> itr = paul.getPath().iterator();
		while(true){
			StdDraw.clear();
		    g.drawMap(grid);
		    if(paul.getPath().size() != 0){
		    	paul.move();
		    }
			for(Node n : test.getWalkwayNodes()){
				if(n.isWalkable()){
					StdDraw.setPenColor(StdDraw.GREEN);
					StdDraw.setPenRadius(0.005);
					StdDraw.point(n.getX(), n.getY());
				}
				if(n.getX() == startNode.getX() && n.getY() == startNode.getY())
				{
					StdDraw.setPenColor(StdDraw.BLUE);
					StdDraw.setPenRadius(0.01);
					StdDraw.point(n.getX(), n.getY());
				}
				if(n.getX() == aRandomNode.getX() && n.getY() == aRandomNode.getY())
				{
					StdDraw.setPenColor(StdDraw.CYAN);
					StdDraw.setPenRadius(0.01);
					StdDraw.point(n.getX(), n.getY());
				}
//				while(itr.hasNext()){
//					Node temp = itr.next();
//					System.out.println("Iter X & Y: " + temp.getX() + " " + temp.getY());
//					System.out.println("Iter X: " + temp.getX());
//					System.out.println("Iter Y: " + temp.getY());
//					paul.setX(temp.getX());
//					paul.setY(temp.getY());
//					StdDraw.setPenColor(StdDraw.BOOK_RED);
//					StdDraw.filledSquare(paul.getX(), paul.getY(), 4);
//					
//				}
			}
		    g.drawHuman(paul);
		    StdDraw.show(100);
			
		}
		
	}
}