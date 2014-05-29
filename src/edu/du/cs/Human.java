package edu.du.cs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class Human implements CharacterInterface 
{
	private int hp;
	private int x;
	private int y;
	private String type;
	private int vel;
	private boolean panic;
	private List<Node> path;

	public Human(int xIn, int yIn) {
		hp = 10;
		type = "Human";
		vel = (int) (Math.ceil(Math.random()*2) + 3);
		x = xIn;
		y = yIn;
		panic = false;
		path = new ArrayList<Node>();
	}
	
	@Override
	public void move() {
		System.out.println("Path Size: " + path.size());
		Collections.reverse(path);
		Iterator<Node> itr = path.iterator();
	
		while(itr.hasNext()){
			Node temp = itr.next();

			this.setX(temp.getX());
			this.setY(temp.getY());
			StdDraw.setPenColor(StdDraw.BOOK_RED);
			StdDraw.filledSquare(temp.getX(), temp.getY(), 4);
			itr.remove();
			StdDraw.show(20);
		}
		System.out.println("DONE!");
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
	}
	
	public List<Node> getPath(){
		return path;
	}
	
//	public Node getNode(ArrayList<Node> nodeList, int x, int y){
//		Node tempNode = null;
//		for(Node n : nodeList){
//			if((n.getX() == x) && (n.getY() == y)){
//				tempNode = n;
//			}
//		}
//		return tempNode;
//	}
	public int estimateDistance(Node node1, Node node2) {
	    return Math.abs(node1.getX() - node2.getX()) + Math.abs(node1.getY() - node2.getY());
	}
}
