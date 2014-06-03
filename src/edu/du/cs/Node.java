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
	/*
	 * sets adjacent nodes
	 */
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
	/*
	 * @return x
	 * return x
	 */
	public int getX(){
		return x;
	}
	/*
	 * @return y
	 * return y
	 */
	public int getY(){
		return y;
	}
	/*
	 * @return walkable
	 * return walkable
	 */
	public boolean isWalkable(){
		return walkable;
	}
	/*
	 * @return n
	 * @param x
	 * @param y
	 * return node
	 */
	public Node findNode(int x, int y){
		for(Node n : Simulate.walkway){
			if((n.getX() == x) && (n.getY() == y)){
				return n;
			}
		}
		return null;
	}
	/*
	 * @param x
	 * sets myX to x
	 */
	
	public void setX(int myX){
		x = myX;
	}
	/*
	 * @param y
	 * sets myY to Y
	 */
	public void setY(int myY){
		y = myY;
	}
	/*
	 * @param c
	 * sets hcost to c
	 */
	public void setHCost(int c){
		hcost = c;
	} 
	/*
	 * @param c
	 * sets zcost to c
	 */
	public void setzCost(int c){
		zcost = c;
	}
	/*
	 * @return topNode
	 * return topNode
	 */
	public Node getTopNode(){
		return topNode;
	}
	/*
	 * @return bottomNode
	 * return bottomNode
	 */
	public Node getBottomNode(){
		return bottomNode;
	}
	/*
	 * @return rightNode
	 * return rightNode
	 */
	public Node getRightNode(){
		return rightNode;
	}
	/*
	 * @return leftNode
	 * return leftNode
	 */
	public Node getLeftNode(){
		return leftNode;
	}
	/*
	 * @return zcost
	 * return zcost
	 */
	public int getZCost(){
		return zcost;
	}
	public int gethCost(){
		return hcost;
	}
	/*
	 * @return adjacentNode
	 * return adjacentNode
	 */
	public List<Node> getAdjacentNodes(){
		return adjacentNodes;
	}
	/*
	 * @param Anode
	 * sets parent to aNode
	 */
	public Node setParent(Node aNode){
		return parent = aNode;
	}

	
	public String toString(){
		return "X: " + x + " Y: " + y;
	}
	
}
