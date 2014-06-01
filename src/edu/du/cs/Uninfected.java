package edu.du.cs;

import java.util.ArrayList;

public abstract class Uninfected extends Human
{
	private ArrayList<Node> hNodes;
	public Uninfected(int xIn, int yIn) {
		super(xIn, yIn);
		vel = 1;
		hNodes = new ArrayList<Node>();
	}

	public void die()	{
		for(Node n: Simulate.walkway){
			if(hNodes.contains(n)){
			n.zcost = 10;
			hNodes.remove(n);
			}
		}
		isDead = true;
		
	}
	public void move(){
		for(Node n: Simulate.walkway){
			if(n.zcost == 12){
			}
			if(Math.abs(n.getX() - this.x) <= 0 && Math.abs(n.getY() - this.y) <= 0 && !hNodes.contains(n)){
				n.zcost = 1;	
				hNodes.add(n);
			}
			else if(Math.abs(n.getX() - this.x) <= 10 && Math.abs(n.getY() - this.y) <= 10 && n.zcost != 1){
				n.zcost = 2;	
				if(!hNodes.contains(n))
					hNodes.add(n);
			}
			else if(Math.abs(n.getX() - this.x) <= 20 && Math.abs(n.getY() - this.y) <= 20 && n.zcost != 2){
				n.zcost = 3;	
				if(!hNodes.contains(n))
					hNodes.add(n);
			}
			else if(Math.abs(n.getX() - this.x) <= 30 && Math.abs(n.getY() - this.y) <= 30 && n.zcost != 3){
				n.zcost = 4;	
				if(!hNodes.contains(n))
					hNodes.add(n);
			}
			else if(Math.abs(n.getX() - this.x) <= 40 && Math.abs(n.getY() - this.y) <= 40 && n.zcost != 4){
				n.zcost = 5;	
				if(!hNodes.contains(n))
					hNodes.add(n);
			}
			else if(Math.abs(n.getX() - this.x) <= 50 && Math.abs(n.getY() - this.y) <= 50 && n.zcost != 5){
				n.zcost = 6;	
				if(!hNodes.contains(n))
					hNodes.add(n);
			}
        	else {
        		if(hNodes.contains(n)){
					n.zcost = 10;
					hNodes.remove(n);
				}
			}
			if(this.type == 'c'){
				
			}
        }
		super.move();
	}

}
