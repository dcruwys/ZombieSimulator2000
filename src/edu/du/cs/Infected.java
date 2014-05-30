package edu.du.cs;

import java.util.ArrayList;

public class Infected extends Human {

	private ArrayList<Node> infected;
	public Infected(int xIn, int yIn) {
		super(xIn, yIn);
		infected = new ArrayList<Node>();
	}
	public void lineOfSight(ArrayList<Human> humans){
			for(Human h: humans){
				if(Math.abs(h.getX() - this.x) < 100 && Math.abs(h.getY() - this.y) < 100){
					this.aStar(this.getNode(Simulate.walkway, x, y), h.currentNode);
				}
		}
	}
	
	public void move(){
		for(Node n: Simulate.walkway){
			if(Math.abs(n.getX() - this.x) < 30 && Math.abs(n.getY() - this.y) < 30){
				n.cost += 10;	
				infected.add(n);
			}
			if((Math.abs(n.getX() - this.x) >= 30) && (Math.abs(n.getY() - this.y) >= 30) && infected.contains(n)){
				n.cost -= 10;
			}
		}
		super.move();
	}
}
