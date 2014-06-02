package edu.du.cs;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Human
{
    protected int x;
    protected int y;
    protected int vel;
    private int random;
    protected List<Node> path;
    protected Node currentNode;
    protected Node nextNode;
    private Node rNode;
    private Node myNode;
    protected ArrayList<Node> walkway = Simulate.walkway;
    protected boolean isDead;
    protected char type;

    public Human(int xIn, int yIn) {
        x = xIn;
        y = yIn; 
        myNode = new Node(x, y, true);

        currentNode = getNode(walkway, x, y);
        rNode = randomNode();
        path = new ArrayList<Node>();
        isDead = false;
        //aStar(currentNode, rNode);
    }
    
    public void move(){
    	if(path.size() <= 1){
    		rNode = this.randomNode();
			currentNode = this.getNode(Simulate.walkway, x, y);
			this.aStar(currentNode, rNode);
			return;
    	}
    	if(path.size() > 1){
    		currentNode = path.get(0);
        	nextNode = path.get(1);
			
			if(currentNode == nextNode){
				path.remove(0);
				currentNode = path.get(0);
	        	nextNode = path.get(1);
			}
			else{
				if(currentNode.getRightNode() == nextNode && currentNode.getRightNode() != null){
					x += vel;
				}
				else if(currentNode.getLeftNode() == nextNode && currentNode.getLeftNode() != null){
					x -= vel;
				}
				else if(currentNode.getTopNode() == nextNode &&  currentNode.getTopNode() != null){
					y += vel;
				}
				else if(currentNode.getBottomNode() == nextNode  && currentNode.getBottomNode() != null){
					y -= vel;
				}
				else {
					rNode = this.randomNode();
					currentNode = this.getNode(Simulate.walkway, x, y);
					this.aStar(currentNode, rNode);
					return;
				}
				if(x == nextNode.getX() && y == nextNode.getY()){
					path.remove(0);
				}
			}
    	}
    }

    public abstract void die();


    public int getX() 
    {
        return x;
    }

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
    public void setType(char t){
    	type = t;
    }
    public int getVel() 
    {
        return vel;
    }
    public boolean isDead(){
    	return isDead;
    }
    public Node getNode(){
    	return myNode;
    }

    public void aStar(Node start, Node goal) {
    	path.clear();
        Set<Node> open = new HashSet<Node>();
        Set<Node> closed = new HashSet<Node>();
        
        start.g = 0;
        if(this.type == 'i' )
        	start.h = estimateDistance(start, goal) + goal.zcost;
        else if(this.type != 'i')
        	start.h = estimateDistance(start, goal) + goal.hcost;
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
                int nextG = 0;      	
                if(this.type == 'i')
                	nextG = current.g + neighbor.zcost;
                else if(this.type != 'i')
                	nextG = current.g + neighbor.hcost;

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
        if(path.get(0) == path.get(1)){
            path.remove(0);
        }
    }
    
    public List<Node> getPath(){
        return path;
    }
    
    public Node getNode(ArrayList<Node> nodeList, int x, int y){
        for(Node n : nodeList){
            if((n.getX() == x) && (n.getY() == y)){
                return n;
            }
        }
        return null;
        
    }
    public int estimateDistance(Node node1, Node node2) {
        return Math.abs(node1.getX() - node2.getX()) + Math.abs(node1.getY() - node2.getY());
    }
    
    public Node randomNode() {
        Node tempNode = null;
        ArrayList<Node> radiusList = new ArrayList<Node>();
        int radius = 70; //Random Node Radius
        for(Node n : walkway){
        	if(this.type == 'i' && (this.estimateDistance(n, currentNode)<=(radius)) && (this.estimateDistance(n, currentNode) >= 30)){
                radiusList.add(n);
            }
        	else if(this.type == 'c' && (this.estimateDistance(n, currentNode)<=(radius+10)) && (this.estimateDistance(n, currentNode) >= 30)){
        		radiusList.add(n);
        	}
        	else if(this.type != 'i' && this.type != 'c' && (this.estimateDistance(n, currentNode)<=(radius-30)) && (this.estimateDistance(n, currentNode) >= 20)){
                radiusList.add(n);
            }
            
            
        }
        random = (int)(Math.random() * (radiusList.size()));
        tempNode = radiusList.get(random);
        
		    for(Node n: radiusList){
		    	if(this.type == 'i' && n.isWalkable() && n.zcost < tempNode.zcost){
		    		tempNode = n;
		    	}
		    	else if(this.type != 'i' && this.type != 'c' && n.isWalkable() && n.hcost < tempNode.hcost){
		    		tempNode = n;
		    	}
		    	else if(this.type != 'c'  && tempNode.isWalkable() && tempNode != currentNode){
		    		radiusList.clear();
		    		return tempNode;
		    	}
		    }
		    if(tempNode.isWalkable() && tempNode != currentNode){
		    	radiusList.clear();
		    	return tempNode;
		    }
		    radiusList.clear();
		    return randomNode();
    }
}
