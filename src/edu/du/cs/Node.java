package edu.du.cs;

import java.util.ArrayList;

public class Node {
	Node topNode;
	Node bottomNode;
	Node leftNode;
	Node rightNode;
	private int myX;
	private int myY;
	private boolean walkable;
	private static ArrayList<Node> walkway;
	
	//A* variables
	private ArrayList<Node> adjacentNodes = new ArrayList<Node>();
	private int cost;
	Node Parent;

	public Node(int x, int y, boolean w){
		myX = x;
		myY = y;
		cost = 0;
		walkable = w;

	}
	public void setAdjacent(){
		walkway = GraphicsEngine.walkwayNodes;
		Node temp = null;
		temp = findNode(myX+10, myY);
		if(temp != null && temp.isWalkable()==true){
			rightNode = temp;
			rightNode.setMyCost(10);
			adjacentNodes.add(rightNode);
		}
		//Left adjacent node
		temp = findNode(myX-10, myY);
		if(temp != null && temp.isWalkable()==true) {
			leftNode = temp;
			leftNode.setMyCost(10);
			adjacentNodes.add(leftNode);
		}
		//above node
		temp = findNode(myX, myY+10);
		if(temp != null && temp.isWalkable()==true){
			topNode = temp;
			topNode.setMyCost(10);
			adjacentNodes.add(topNode);
		}
		//Bottom node
		temp = findNode(myX, myY-10);
		if(temp != null && temp.isWalkable()==true){
			bottomNode = temp;
			bottomNode.setMyCost(10);
			adjacentNodes.add(bottomNode);
		}
	}
	public int getMyX(){
		return myX;
	}
	public int getMyY(){
		return myY;
	}
	public boolean isWalkable(){
		return walkable;
	}
	public Node findNode(int x, int y){
		//System.out.println("Node size in Node Class " + walkway.size() );
		for(Node n : walkway){
			if((n.getMyX() == x) && (n.getMyY() == y)){
				return n;
			}
		}
		return null;
		
	}
	
	public void setMyX(int x){
		myX = x;
	}
	public void setMyY(int y){
		myY = y;
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
	public ArrayList<Node> getAdjacentNodes(){
		return adjacentNodes;
	}
	public Node setParent(Node aNode){
		return Parent = aNode;
	}
	
	
	public String toString(){
		return "X: " + myX + " Y: " + myY;
	}
	
}
