package edu.du.cs;

import java.util.ArrayList;
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
	private int direction;
	private boolean panic;
	private int xVel;
	private int yVel;
	private List<Node> path;

	public Human(int xIn, int yIn) {
		hp = 10;
		type = "Human";
		direction = (int) (Math.random() * 360) % 360;
		vel = (int) (Math.ceil(Math.random()*2) + 3);
		x = xIn;
		y = yIn;
		//factor = vel / Math.sqrt(xSpeed * xSpeed + ySpeed * ySpeed);
		
		panic = false;
	}
	
	@Override
	public void move() {

//		if(!panic){
//			xVel = (int) (vel * Math.sin(direction));
//			yVel = (int) (vel * Math.cos(direction));
//			x += xVel;
//			y += yVel;
//		}
//		this.checkCollisions();
//		Iterator<Node> itr = path.iterator();
//		System.out.println("Path Size: " + path.size());
//		while(itr.hasNext()){
//			x = itr.next().getX();
//			y = itr.next().getY();
//		}
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
		if(x > 500){
			x = 500;
			direction = (int) (Math.random() * 360) % 360;
		}
		else if(y > 500){
			y = 500;
			direction = (int) (Math.random() * 360) % 360;
		}
		else if(x < -10){
			x = -10;
			direction = (int) (Math.random() * 360) % 360;
		}
		else if(y < -10){
			y = -10;
			direction = (int) (Math.random() * 360) % 360;	
		}
		//GraphicsEngine.grid[][]
		
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

	    List<Node> nodes = new ArrayList<Node>();
	    Node current = goal;
	    while (current.parent != null) {
	        nodes.add(current);
	        current = current.parent;
	    }
	    nodes.add(start);

	   path = nodes;
	   System.out.println("Path Size: " + path.size());
	}
	public List<Node> getPath(){
		return path;
	}

	public int estimateDistance(Node node1, Node node2) {
	    return Math.abs(node1.getX() - node2.getX()) + Math.abs(node1.getY() - node2.getY());
	}
}
