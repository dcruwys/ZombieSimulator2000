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
		for(Node n: Simulate.hWalkway){
			if(Math.abs(n.getX() - this.x) < 60 && Math.abs(n.getY() - this.y) < 60 && !hNodes.contains(n)){
        		n.setAlpha(0);
				n.cost = 10;	
				hNodes.add(n);
			}
        	else {
				n.setAlpha(1);
				n.cost = 10;
				hNodes.remove(n);
			}
        }
		super.move();
	}

}
