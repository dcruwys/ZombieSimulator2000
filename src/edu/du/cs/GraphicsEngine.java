package edu.du.cs;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.List;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;


public class GraphicsEngine
{	
	public static ArrayList<Node> walkwayNodes;
	public static ArrayList<Human> humans;
	public static ArrayList<Infected> infected;
	public static ArrayList<Uninfected> uninfected;

	public int random;
	public Node startNode;
	public Node randomNode;
	
	public GraphicsEngine(){
		Simulate.generateBuildings();
		infected = new ArrayList<Infected>();
		uninfected = new ArrayList<Uninfected>();
		humans = new ArrayList<Human>(); //Creat an array list of humans.
		
		StdDraw.setCanvasSize(500, 500); //Set Canvas size is set to 500, 500
		StdDraw.setXscale(0.0, Simulate.mySize*10); //Set scale to 500
		StdDraw.setYscale(0.0, Simulate.mySize*10); //Set scale to 500
		
		for(int i = 0; i < Simulate.mySize+(Simulate.mySize/2); i++){
			Node randomN = this.randomNode();
			Human aHuman = new Normal(randomN.getX(), randomN.getY());
			uninfected.add((Uninfected) aHuman);
			humans.add(aHuman);
		}
		
		for( int i =0; i < 5; i++)
		{
			Node randomN = this.randomNode();
			Human someHuman = new Infected(randomN.getX(), randomN.getY());
			infected.add((Infected) someHuman);
			humans.add(someHuman);
		}
		Node randomN = this.randomNode();
		Human aHuman = new Infected(randomN.getX(), randomN.getY());
		infected.add((Infected)aHuman);
		humans.add(aHuman);
		for(int i = 0; i < 10; i++){
			randomN = this.randomNode();
			Human aCop = new Cop(randomN.getX(), randomN.getY());
			uninfected.add((Uninfected) aCop);
			humans.add(aCop);
		}
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
		if( aHuman.type == 'm') //if human is medic, make it red
			StdDraw.setPenColor( StdDraw.RED );
		else if( aHuman.type == 'c') //if human is cop make it blue
			StdDraw.setPenColor( StdDraw.BLUE );
		else if( aHuman.type == 'n')//if human is normal make it pink
			StdDraw.setPenColor( StdDraw.MAGENTA );
		else if( aHuman.type == 'i')
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
			      case 8:StdDraw.setPenColor( StdDraw.GRAY ); //if the tile is 1, draw Gray
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
		while(true){
		g.draw(g);
		}
	}
	
	public void draw(GraphicsEngine g){
		ArrayList<Human> deadList = new ArrayList<Human>();
		ArrayList<Human> zdeadList = new ArrayList<Human>();
		
		while(true){
		    
			StdDraw.clear();
			g.drawMap(Simulate.grid);
		    for(Human h : uninfected){
		    	for(Human z: infected){
		    		if( Math.abs(z.getX() - h.getX()) + Math.abs(z.getY() - h.getY()) < 10 ){
		    			if(h.type == 'c'){
		    				if(((Cop) h).getAmmo() == 0 )
		    					((Infected) z).attack((Uninfected) h);
		    				else
		    					((Cop) h).attack((Infected) z);
		    			} else {
		    				((Infected) z).attack((Uninfected) h);
		    			}
		    			
		    		}
		    		if( h.isDead && !deadList.contains(h)){
		    			deadList.add(h);	
		    		}
		    		if(z.isDead && !zdeadList.contains(z)){
		    			zdeadList.add(z);
		    		}
		    	}
		    }			    
		    for(Human h: deadList){
		    	uninfected.remove(h);
		    	humans.remove(h);
		    	Human temp = new Infected(h.currentNode.getX(), h.currentNode.getY());
		    	infected.add((Infected) temp);
		    	humans.add(temp);
		    	h = null;
		    }
		    for(Human z: zdeadList){
		    	uninfected.remove(z);
		    	humans.remove(z);
		    	z = null;
		    }
		    deadList.clear();
		    
		    for(Human h : humans){
		    	if(h.type == 'c'){
		    		for(Infected z : infected){
		    			if( Math.abs(z.getX() - h.getX()) + Math.abs(z.getY() - h.getY()) < 30 ){
		    				((Cop) h).attack((Infected) z);
		    				break;
		    			
		    			}
		    		}
		    	}
		    	Node target = null;
		    	g.drawHuman(h);
		    	h.move();
		    	if(h.type == 'i'){
		    		List<Node> path = h.getPath();
		    		if(path.size() > 0)
		    			target = path.get(path.size()-1);
		    	}
		    	if(target != null)	{
		    		StdDraw.filledCircle(target.getX(), target.getY(), 2);
		    	}

		    }
		    StdDraw.show(3);
		}
	}
}