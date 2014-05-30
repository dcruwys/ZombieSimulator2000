package edu.du.cs;

import java.util.ArrayList;

public class Infected extends Human {

	public Infected(int xIn, int yIn) {
		super(xIn, yIn);
	}
	public void lineOfSight(ArrayList<Human> humans){
			for(Human h: humans){
				if(Math.abs(h.getX() - this.x) < 100 && Math.abs(h.getY() - this.y) < 100){
					this.aStar(this.getNode(Simulate.walkway, x, y), h.currentNode);
				}
			}
	}
}
