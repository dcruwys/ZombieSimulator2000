package edu.du.cs;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;


public class GraphicsEngine
{	
	public static ArrayList<Node> walkwayNodes;
	public ArrayList<Human> humans;

	public int random;
	public Node startNode;
	public Node randomNode;
	
	public GraphicsEngine(){
		Simulate.generateBuildings();
		
		humans = new ArrayList<Human>(); //Create an array list of humans.
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
	
	public void drawHuman( Human aHuman ) {
		if( aHuman instanceof Medic) //if human is medic, make it red
			StdDraw.setPenColor( StdDraw.RED );
		else if( aHuman instanceof Cop) //if human is cop make it blue
			StdDraw.setPenColor( StdDraw.BLUE );
		else if( aHuman instanceof Normal)//if human is normal make it pink
			StdDraw.setPenColor( StdDraw.MAGENTA );
		else if( aHuman instanceof Infected)
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
		random = (int )(Math.random() * Simulate.walkway.size());
		tempNode = Simulate.walkway.get(random);
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
		for(int i = 0; i < (int)Simulate.mySize/5; i++){
			Node randomN = g.randomNode();
<<<<<<< HEAD
			if(i % 4 == 0){
				Human aHuman = new Infected(randomN.getX(), randomN.getY());
				humans.add(aHuman);
			}
			else{
				Uninfected aHuman = new Normal(randomN.getX(), randomN.getY());
				humans.add(aHuman);
			}
			
 		}
		//Infected z = new Infected(g.randomNode().getX(), g.randomNode().getY());
		//humans.add(z);
=======
				Human aHuman = new Normal(randomN.getX(), randomN.getY());
				System.out.println("A human was spawned");
				humans.add(aHuman);
			
 		}
		Human z = new Infected(g.randomNode().getX(), g.randomNode().getY());
		System.out.println("A zombie was spawned");
		humans.add(z);
>>>>>>> FETCH_HEAD
		while(true){
			StdDraw.clear();
			g.drawMap(Simulate.grid);
			//g.drawTalkBox("Hello World", "meh.gif");
		    for(Human h : humans){
		    	Node target = null;
		    	g.drawHuman(h);
		    	h.move();
		    	if(h instanceof Infected){
		    		List<Node> path = h.getPath();
		    		target = path.get(path.size()-1);
		    	}
		    	if(target != null)	{
		    		StdDraw.filledCircle(target.getX(), target.getY(), 2);
		    	}
		    }	
		    
		    //g.drawZombie(z);
			//z.move();
			//z.lineOfSight(humans);
		    StdDraw.show(30);
	   }
	}
}