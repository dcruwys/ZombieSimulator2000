package edu.du.cs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Human implements CharacterInterface 
{
	protected int hp;
	protected int x;
	protected int y;
	protected String type;
	protected int vel;
	private int random;
	protected boolean panic;
	protected List<Node> path;
	protected Node currentNode;
	protected Node nextNode;
	private Node rNode;

	public Human(int xIn, int yIn) {
		hp = 10;
		type = "Human";
		vel = 1;
		x = xIn;
		y = yIn;
		panic = false;
		rNode = this.randomNode();
		currentNode = this.getNode(Simulate.walkway, x, y);
		path = new ArrayList<Node>();
		this.aStar(currentNode, rNode);
		System.out.println("START OF THE LINE...");
		System.out.println("Current Node: "+currentNode);
		System.out.println("Random Node: "+rNode);
		System.out.println("Path: "+path);
		System.out.println();
		
		
	}
	
	@Override
	public void move() {
		if(path.size() > 1){
			currentNode = path.get(0);
			nextNode = path.get(1);
			if(currentNode.getRightNode() == nextNode){
				x += vel;
			}
			else if(currentNode.getLeftNode() == nextNode){
				x -= vel;
			}
			else if(currentNode.getTopNode() == nextNode){
				y += vel;
			}
			else if(currentNode.getBottomNode() == nextNode){
				y -= vel;
			}
			
			if(x == nextNode.getX() && y == nextNode.getY()){
				path.remove(0);
			}
		}	
		else{
			path.clear();
			rNode = this.randomNode();
			this.aStar(currentNode, rNode);
			
			System.out.println("END OF THE LINE...");
			System.out.println("Current Node: "+currentNode);
			System.out.println("Random Node: "+rNode);
			System.out.println("Path: "+path);
		}
	}

	@Override
	public void changeType(String newType) 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public void die() 
	{
		// TODO Auto-generated method stub

	}

	@Override
	public int getHP() 
	{
		return hp;
	}

	@Override
	public String getType() 
	{
		return type;
	}

	@Override
	public int getX() 
	{
		return x;
	}

	@Override
	public int getY() 
	{
		return y;
	}
	
	public void setX(int myX){
		x = myX;
	}
	
	public void setY(int myY){
		y = myY;
	}

	@Override
	public int getVel() 
	{
		return vel;
	}
	
	public boolean getPanic(){
		return panic;
	}

	public void starve()
	{
		
	}
	
	public void checkCollisions(){
		
	}
	
	public void aStar(Node start, Node goal) {
	    Set<Node> open = new HashSet<Node>();
	    Set<Node> closed = new HashSet<Node>();
	    
	    start.g = 0;

	    start.h = estimateDistance(start, goal);
	    start.f = start.h;

	    open.add(start);

	    while (true) {
	        Node current = null;

	        if (open.size() == 0) {
	            throw new RuntimeException("no route");
	        }

	        for (Node node : open) {
	            if (current == null || node.f < current.f) {
	                current = node;
	            }
	        }

	        if (current == goal) {
	            break;
	        }

	        open.remove(current);
	        closed.add(current);

	        for (Node neighbor : current.getAdjacentNodes()) {
	            if (neighbor == null) {
	                continue;
	            }

	            int nextG = current.g + neighbor.cost;

	            if (nextG < neighbor.g) {
	                open.remove(neighbor);
	                closed.remove(neighbor);
	            }

	            if (!open.contains(neighbor) && !closed.contains(neighbor)) {
	                neighbor.g = nextG;
	                neighbor.h = estimateDistance(neighbor, goal);
	                neighbor.f = neighbor.g + neighbor.h;
	                neighbor.parent = current;
	                open.add(neighbor);
	            }
	        }
	    }
	    Node current = goal;
	    while (current.parent != null && !path.contains(current.parent)) {
	        path.add(current);
	        current = current.parent;
	    }
	    path.add(start);
	    Collections.reverse(path);
	}
	
	public List<Node> getPath(){
		return path;
	}
	
	public Node getNode(ArrayList<Node> nodeList, int x, int y){
		Node tempNode = null;
		for(Node n : nodeList){
			if((n.getX() == x) && (n.getY() == y)){
				tempNode = n;
			}
		}
		return tempNode;
	}
	
	public int estimateDistance(Node node1, Node node2) {
	    return Math.abs(node1.getX() - node2.getX()) + Math.abs(node1.getY() - node2.getY());
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
