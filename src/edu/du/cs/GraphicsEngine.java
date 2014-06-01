package edu.du.cs;

import java.io.FileInputStream;
import java.util.ArrayList;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;


public class GraphicsEngine
{	
	public static int[][] grid;
	public static ArrayList<Node> walkwayNodes;
	public ArrayList<Human> humans;

	public int random;
	public Node startNode;
	public Node randomNode;
	
	public GraphicsEngine(){
		Simulate.generateBuildings();
		
		grid = Simulate.grid;
		humans = new ArrayList<Human>(); //Create an array list of humans.
		for(Node n: walkwayNodes)
			n.setAdjacent();
	}
	public void drawTalkBox(String msg, String gifSrc){
		boolean hasPlayedStartSound = false;
		Player playRadioStart = null;
		Player playRadioStop = null;
		
		try{
			FileInputStream radioStartIN = new FileInputStream("RadioTransmissionStart.mp3");
			FileInputStream radioEndIN = new FileInputStream("RadioTransmissionEnd.mp3");
			playRadioStart = new Player(radioStartIN);
			playRadioStop = new Player(radioEndIN);
		} catch(Exception exc){
			exc.printStackTrace();
			System.out.println("FAILED TO DO A BARREL ROLL");
		}
		StdDraw.setPenColor(StdDraw.BLUE);
		StdDraw.filledRectangle(150, 50, 150, 60);
		StdDraw.setPenColor(StdDraw.BLACK);
		StdDraw.text(170, 40, msg);
		if(hasPlayedStartSound == false){
			try { playRadioStart.play(); } catch(JavaLayerException e){System.out.println(e.getMessage());}
			hasPlayedStartSound = true;
		}
	}
	
	public static void drawHuman( Human aHuman ) {
		if( aHuman.getType().equalsIgnoreCase( "medic" )) //if human is medic, make it red
			StdDraw.setPenColor( StdDraw.RED );
		else if( aHuman.getType().equalsIgnoreCase( "cop" )) //if human is cop make it blue
			StdDraw.setPenColor( StdDraw.BLUE );
		else if( aHuman.getType().equalsIgnoreCase( "normal" ))//if human is normal make it pink
			StdDraw.setPenColor( StdDraw.MAGENTA );
		else if( aHuman.getType().equalsIgnoreCase("infected"))
			StdDraw.setPenColor( StdDraw.GREEN );
		
		StdDraw.filledSquare( aHuman.getX(), aHuman.getY(), 4); //draw human
	}

	public void drawSupply(Supplies someSupply) {
		
		StdDraw.setPenColor( StdDraw.ORANGE ); //make all supplies orange
		
		StdDraw.filledCircle( someSupply.getX(), someSupply.getY(), 4); //draw supplies
		
	}

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
		tempNode = walkwayNodes.get(random);
		if(tempNode.isWalkable() && tempNode.getBottomNode() != null && tempNode.getTopNode() != null && tempNode.getLeftNode() != null && tempNode.getRightNode() != null){
			return tempNode;
		} 
		return randomNode();
		
	}
	
	public static void main(String[] args){
		GraphicsEngine g = new GraphicsEngine();
		g.draw(g);
	}
	
	public void draw(GraphicsEngine g){
		StdDraw.setCanvasSize(500, 500); //Set Canvas size is set to 500, 500
		StdDraw.setXscale(0.0, 500.0); //Set scale to 500
		StdDraw.setYscale(0.0, 500.0); //Set scale to 500
		for(int i = 0; i < 1; i++){
			Node randomN = g.randomNode();
			Human aHuman = new Normal(randomN.getX(), randomN.getY());
			humans.add(aHuman);
 		}
		//Infected z = new Infected(g.randomNode().getX(), g.randomNode().getY());
		
		while(true){
			StdDraw.clear();
			g.drawMap(grid);
			//g.drawTalkBox("Hello World", "meh.gif");

		    for(Human h : humans){
		    	g.drawHuman(h);
		    	h.move();
		    }
		    //g.drawZombie(z);
			//z.move();
			//z.lineOfSight(humans);
		    StdDraw.show(30);
	   }
	}
}