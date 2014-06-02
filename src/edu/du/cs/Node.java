package edu.du.cs;

import java.util.ArrayList;
import java.util.List;

public class Node {
	protected Node topNode;
	protected Node bottomNode;
	protected Node leftNode;
	protected Node rightNode;
	protected int x;
	protected int y;
	protected boolean walkable;
	
	//A* variables
	protected List<Node> adjacentNodes = new ArrayList<Node>();
	protected int hcost;
	public int zcost;
	protected int f;
    protected int g;
    protected int h;
	protected Node parent;


	public Node(int myX, int myY, boolean w){
		x = myX;
		y = myY;
		
		hcost = 10;
		zcost = 10;
		walkable = w;
	}
	public Node(Node n){
		
		topNode = n.topNode;
		leftNode = n.leftNode;
		rightNode = n.rightNode;
		bottomNode = n.bottomNode;
		x = n.x;
		y = n.y;
		walkable = n.walkable;
		
		adjacentNodes = n.adjacentNodes;
		hcost = 10;
		zcost = 10;
			
	}
	
	public void setAdjacent(){
		Node temp = null;
		temp = findNode(x+10, y);
		if(temp != null && temp.isWalkable()==true){
			rightNode = temp;
			adjacentNodes.add(rightNode);
		}
		//Left adjacent node
		temp = findNode(x-10, y);
		if(temp != null && temp.isWalkable()==true) {
			leftNode = temp;
			adjacentNodes.add(leftNode);
		}
		//above node
		temp = findNode(x, y+10);
		if(temp != null && temp.isWalkable()==true){
			topNode = temp;
			adjacentNodes.add(topNode);
		}
		//Bottom node
		temp = findNode(x, y-10);
		if(temp != null && temp.isWalkable()==true){
			bottomNode = temp;
			adjacentNodes.add(bottomNode);
		}
	}
	public int getX(){
		return x;
	}
	public int getY(){
		return y;
	}
	public boolean isWalkable(){
		return walkable;
	}
	public Node findNode(int x, int y){
		for(Node n : Simulate.walkway){
			if((n.getX() == x) && (n.getY() == y)){
				return n;
			}
		}
		return null;
	}
	
	public void setX(int myX){
		x = myX;
	}
	public void setY(int myY){
		y = myY;
	}
	public void setHCost(int c){
		hcost = c;
	}
	public void setzCost(int c){
		zcost = c;
	}
	public Node getTopNode(){
		return topNode;
	}
	public Node getBottomNode(){
		return bottomNode;
	}
	public Node getRightNode(){
		return rightNode;
	}
	public Node getLeftNode(){
		return leftNode;
	}
	public int getZCost(){
		return zcost;
	}
	public int gethCost(){
		return hcost;
	}
	public List<Node> getAdjacentNodes(){
		return adjacentNodes;
	}
	public Node setParent(Node aNode){
		return parent = aNode;
	}

	
	public String toString(){
		return "X: " + x + " Y: " + y;
	}
	
}
