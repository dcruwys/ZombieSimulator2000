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
	private ArrayList<Node> walkway = GraphicsEngine.walkwayNodes;
	
	//A* variables
	private ArrayList<Node> adjacentNodes = new ArrayList<Node>();
	private int cost;
	Node Parent;

	public Node(int x, int y, boolean w){
		myX = x;
		myY = y;
		cost = 0;
		walkable = w;
		
		//A bit overwhelming but first check if the arraylist of nodes contains an adjacent node, then check if it's walkable
		//if so, we need to keep track of this for path finding. Any Node above/below/left/right costs 10, while diagnals cost 14
		
		
		//Right adjacent node
		if((walkway.contains((this.findNode(myX+10, myY)))) && (this.findNode(myX+10, myY).isWalkable()==true)){
			rightNode = this.findNode(myX+10, myY);
			rightNode.setMyCost(10);
			adjacentNodes.add(rightNode);
		}
		//Left adjacent node
		if((walkway.contains((this.findNode(myX-10, myY)))) && (this.findNode(myX-10, myY).isWalkable()==true)){
			leftNode = this.findNode(myX-10, myY);
			leftNode.setMyCost(10);
			adjacentNodes.add(leftNode);
		}
		//above node
		if((walkway.contains((this.findNode(myX, myY+10)))) && (this.findNode(myX, myY+10).isWalkable()==true)){
			topNode = this.findNode(myX, myY+10);
			topNode.setMyCost(10);
			adjacentNodes.add(topNode);
		}
		//Bottom node
		if((walkway.contains((this.findNode(myX, myY-10)))) && (this.findNode(myX, myY-10).isWalkable()==true)){
			bottomNode = this.findNode(myX, myY-10);
			bottomNode.setMyCost(10);
			adjacentNodes.add(bottomNode);
		}
		
		//Todo, add diagnals
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
		return "Node X: " + myX + " Node Y: " + myY;
	}
	
}
