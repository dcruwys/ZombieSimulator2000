package edu.du.cs;

import java.util.ArrayList;

public abstract class Uninfected extends Human
{
	private ArrayList<Node> hNodes;
	public Uninfected(int xIn, int yIn) {
		super(xIn, yIn);
		
		walkway = Simulate.hWalkway;
        
		vel = 1 + (int) Math.round( Math.random() * 2);
		hNodes = new ArrayList<Node>();
	}

	public void die() 
	{
		changeType('z');
	}
	public void move(){
		for(Node n: Simulate.zWalkway){
			if(n.cost == 12){
				System.out.println("DONE FUCKED");
			}
			if(Math.abs(n.getX() - this.x) <= 0 && Math.abs(n.getY() - this.y) <= 0 && !hNodes.contains(n)){
				n.cost = 0;	
				hNodes.add(n);
			}
			else if(Math.abs(n.getX() - this.x) <= 10 && Math.abs(n.getY() - this.y) <= 10 && n.cost != 0){
				n.cost = 1;	
				if(!hNodes.contains(n))
					hNodes.add(n);
			}
			else if(Math.abs(n.getX() - this.x) <= 20 && Math.abs(n.getY() - this.y) <= 20 && n.cost != 1){
				n.cost = 2;	
				if(!hNodes.contains(n))
					hNodes.add(n);
			}
			else if(Math.abs(n.getX() - this.x) <= 30 && Math.abs(n.getY() - this.y) <= 30 && n.cost != 2){
				n.cost = 3;	
				if(!hNodes.contains(n))
					hNodes.add(n);
			}
			else if(Math.abs(n.getX() - this.x) <= 40 && Math.abs(n.getY() - this.y) <= 40 && n.cost != 3){
				n.cost = 4;	
				if(!hNodes.contains(n))
					hNodes.add(n);
			}
			else if(Math.abs(n.getX() - this.x) <= 50 && Math.abs(n.getY() - this.y) <= 50 && n.cost != 4){
				n.cost = 5;	
				if(!hNodes.contains(n))
					hNodes.add(n);
			}
        	else {
        		if(hNodes.contains(n)){
					n.cost = 10;
					hNodes.remove(n);
				}
			}
        }
		super.move();
	}

}
