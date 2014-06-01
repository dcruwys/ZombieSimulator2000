package edu.du.cs;

import java.util.ArrayList;
import java.util.List;

public class Node {
	Node topNode;
	Node bottomNode;
	Node leftNode;
	Node rightNode;
	private int x;
	private int y;
	private boolean walkable;
	private int alpha;
	
	//A* variables
	private List<Node> adjacentNodes = new ArrayList<Node>();
	public int cost;
	public int f;
    public int g;
    public int h;
	Node parent;
	
	public int getAlpha()
	{
		return alpha;
	}
	
	public void setAlpha( int x )
	{
		alpha = x;
	}

	public Node(int myX, int myY, boolean w){
		x = myX;
		y = myY;
		
		cost = 10;
		alpha = 1;
		walkable = w;
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
	public void setMyCost(int c){
		cost = c;
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
	public int getCost(){
		return cost;
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
