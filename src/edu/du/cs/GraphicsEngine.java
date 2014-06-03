package edu.du.cs;

import java.io.FileInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.LockSupport;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;


public class GraphicsEngine
{	
	public static ArrayList<Node> walkwayNodes;
	public static ArrayList<Human> humans;
	public ArrayList<Infected> infected;
	public ArrayList<Uninfected> uninfected;

	public int random;
	public Node startNode;
	public Node randomNode;
	
	public GraphicsEngine(){
		Simulate.generateBuildings();
		infected = new ArrayList<Infected>();
		uninfected = new ArrayList<Uninfected>();
		humans = new ArrayList<Human>(); //Creat an array list of humans.
		
		StdDraw.setCanvasSize(700, 700); //Set Canvas size is set to 500, 500
		StdDraw.setXscale(0.0, Simulate.mySize*10); //Set scale to 500
		StdDraw.setYscale(0.0, Simulate.mySize*10); //Set scale to 500
		
		for(int i = 0; i < Simulate.mySize+(Simulate.mySize/2); i++){
			Node randomN = this.randomNode();
			Human aHuman = new Normal(randomN.getX(), randomN.getY());
			uninfected.add((Uninfected) aHuman);
			humans.add(aHuman);
			if(i%20 == 0){
				randomN = this.randomNode();
				Human cop = new Cop(randomN.getX(), randomN.getY());
				uninfected.add((Uninfected) cop);
				humans.add(cop);
			}
			if(i%10 == 0){
				randomN = this.randomNode();
				Human doc = new Medic(randomN.getX(), randomN.getY());
				uninfected.add((Uninfected) doc);
				humans.add(doc);
			}
			if(i%25==0){
				randomN = this.randomNode();
				Human someHuman = new Infected(randomN.getX(), randomN.getY());
				infected.add((Infected) someHuman);
				humans.add(someHuman);
			}
		}
		
	}
    /*
     * @param ahuman
     * draws the humans based on type
     * Orange is a medic
     * Blue is a cop
     * Magenta is a normal human
     * Green is a infected
     */
	public void drawHuman( Human aHuman ) {
		if( aHuman.type == 'm') //if human is medic, make it red
			StdDraw.setPenColor( StdDraw.ORANGE );
		else if( aHuman.type == 'c') //if human is cop make it blue
			StdDraw.setPenColor( StdDraw.BLUE );
		else if( aHuman.type == 'n')//if human is normal make it pink
			StdDraw.setPenColor( StdDraw.MAGENTA );
		else if( aHuman.type == 'i')
			StdDraw.setPenColor( StdDraw.GREEN );
		
		StdDraw.filledSquare( aHuman.getX(), aHuman.getY(), 4); //draw human
	}

    /*
     * @param grid
     * draws the grid
     * Black is buildings
     * Light gray is walkable
     * Gray is blockades
     */
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
	/*
     * @return tempNode
     * recursively returns a randomNode
     */
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
		try {
			g.splash(g);
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
		g.draw(g);
	}
	/*
     * @param g
     * creates a splash screen and plays funky music
     */
	public void splash(GraphicsEngine g) throws JavaLayerException{
		while(true){
			String file = "splash/splashscreen.jpg";
			String shoot = "Portal2_sfx_portal_gun_fire_blue.mp3";
			FileInputStream fileIn = null;
			FileInputStream welcome = null;
			
			try {
				welcome = new FileInputStream("splash/welcome.mp3");
				fileIn = new FileInputStream(file);
				
				
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			StdDraw.picture(305.0, 305.0, file, 650.0, 650.0);
		
			final AtomicBoolean pause = new AtomicBoolean(false);
			final  Player player = new Player(welcome);
	        Thread playerThread = new Thread() {
	            @Override
	            public void run() {
	                try {
	                	if(player.isComplete()){
	                		player.play(1);
	                	}
	                	while (player.play(1)) {
	            			if(pause.get()) {
	            				LockSupport.park();
	            			}
	                    }
	                }
	                catch (Exception e) {
	                    System.err.printf("%s\n", e.getMessage());
	                }
	            }
	        };
	   
	        playerThread.start();
	        while(true){
	        	pause.set(!pause.get());
	            if (!pause.get()) {
	                LockSupport.unpark(playerThread);
	            }
	            if(StdDraw.hasNextKeyTyped()){
	            	return;
	            }
	        }
		}
	}
	/*
     * @param g
     * draws and runs the game
     */
	public void draw(GraphicsEngine g){
		ArrayList<Human> deadList = new ArrayList<Human>();
		ArrayList<Human> zdeadList = new ArrayList<Human>();
		ArrayList<Infected> cured = new ArrayList<Infected>();
		while(true){
		    StdDraw.clear();
			g.drawMap(Simulate.grid);
		    for(Human h : uninfected)
		    {
		    	for(Human z: infected)
		    	{
		    		if( Math.abs(z.getX() - h.getX()) + Math.abs(z.getY() - h.getY()) < 10 && !zdeadList.contains(z))
		    		{
		    			if(h.type == 'm')
		    			{
		    				double cureWorks = Math.random();
		    				if(cureWorks < .5)
		    				{
		    					((Infected) z).setCured(true);
		    				}
		    				else
		    				{
		    					((Infected) z).attack((Uninfected) h);
		    					
		    				}
		    			}
		    			else
		    				((Infected) z).attack((Uninfected) h);
		    			
		    		}
		    		if(h.type == 'c'){
		    			if( Math.abs(z.getX() - h.getX()) + Math.abs(z.getY() - h.getY()) < 30 ){
		    				((Cop) h).attack((Infected) z);	    			
		    			}
		    		}
		    		if( h.isDead && !deadList.contains(h)){
		    			deadList.add(h);	
		    		}
		    		if(z.isDead && !zdeadList.contains(z)){
		    			zdeadList.add(z);
		    		}
		    		if(((Infected) z).isCured() && !cured.contains(z)){
		    			cured.add((Infected) z);
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
		    for(Human z: cured)
		    {
		    	infected.remove(z);
		    	humans.remove(z);
		    	Human temp = new Normal(z.currentNode.getX(), z.currentNode.getY());
		    	uninfected.add((Uninfected) temp);
		    	humans.add(temp);
		    	z = null;
		    }
		    deadList.clear();
		    zdeadList.clear();
		    cured.clear();
		    
		    for(Human h: humans){
		    	g.drawHuman(h);
		    	h.move();
		    }
		    StdDraw.show(2);
		}
	}
}