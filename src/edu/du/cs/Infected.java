package edu.du.cs;

import java.util.ArrayList;

public class Infected extends Human {
	private int random;
	private Node rNode;

	public Infected(int xIn, int yIn) {
		super(xIn, yIn);
		rNode = this.randomNode();
		
	}
	public void lineOfSight(ArrayList<Human> humans){
		if(path.size() < 1){
			for(Human h: humans){
				if(Math.abs(h.getX() - this.x) < 100 && Math.abs(h.getY() - this.y) < 100){
					this.aStar(this.getNode(Simulate.walkway, x, y), h.currentNode);
				}
			}
		}
	}
	public Node randomNode(){
		Node tempNode = null;
		random = (int )(Math.random() * Simulate.walkway.size());
		if(Simulate.walkway.get(random).isWalkable() == true){
			tempNode = Simulate.walkway.get(random);
		} else if((Simulate.walkway.get(random).isWalkable() == false)){
			return randomNode();
		}
		if(tempNode != null){
			return tempNode;
		}else {
			return randomNode();
		}
	}
}
