package edu.du.cs;

import java.util.ArrayList;
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
		
		walkwayNodes = test.getWalkwayNodes();
		
		test.generateBuildings();
		grid = test.getGrid();
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
		Human paul = new Human((startNode.getMyX()), (startNode.getMyY()));
//		System.out.println("Paul X and Y:" + paul.getX() + " " + paul.getY());
//		ArrayList<Node> path = new AStarPathFinding(startNode, aRandomNode).findPath();
//		System.out.println("Path Size: " + path.size());
//		for(int i = 0; i < path.size(); i++){
//			System.out.println("Node: " + i + " X " + path.get(i).getMyX() + " Y " + path.get(i).getMyY());
//		}
		System.out.println("Paul X and Y:" + paul.getX() + " " + paul.getY());
		System.out.println("Right Adjacent Node: X " + startNode.findNode(startNode.getMyX()+10, startNode.getMyY()).getMyX() + " Y " + startNode.findNode(startNode.getMyX()+10, startNode.getMyY()).getMyY()); 
		System.out.println("Left Adjacent Node: X " + startNode.findNode(startNode.getMyX()-10, startNode.getMyY()).getMyX() + " Y " + startNode.findNode(startNode.getMyX()-10, startNode.getMyY()).getMyY()); 
		System.out.println("Top Adjacent Node: X " + startNode.findNode(startNode.getMyX(), startNode.getMyY()+10).getMyX() + " Y " + startNode.findNode(startNode.getMyX(), startNode.getMyY()+10).getMyY()); 
		System.out.println("Bottom Adjacent Node: X " + startNode.findNode(startNode.getMyX(), startNode.getMyY()-10).getMyX() + " Y " + startNode.findNode(startNode.getMyX(), startNode.getMyY()-10).getMyY()); 

		while(true){
			StdDraw.clear();
			
			paul.move();
		    g.drawMap(grid);
			for(Node n : test.getWalkwayNodes()){
				StdDraw.setPenColor(StdDraw.GREEN);
				StdDraw.setPenRadius(0.005);
				StdDraw.point(n.getMyX(), n.getMyY());
			}
		    g.drawHuman(paul);
		    StdDraw.show(30);
			
		}
		
	}
}