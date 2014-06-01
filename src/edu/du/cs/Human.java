package edu.du.cs;

import java.io.FileInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

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
    protected ArrayList<Node> walkway = Simulate.walkway;

    public Human(int xIn, int yIn) {
        x = xIn;
        y = yIn;
    
        currentNode = getNode(walkway, x, y);
        rNode = randomNode();
        path = new ArrayList<Node>();
        //aStar(currentNode, rNode);
    }
    
    public void move() {
			if(path.size() <= 1){
				path.clear();
				rNode = this.randomNode();
				currentNode = this.getNode(walkway, x, y);
				if((x > 500) || (y > 500) || (x < 0) || (y < 0)){
					System.out.println("Aw shit nigga, we got a runner ONE");
					System.out.println("Runner X: " +  x + " Y: " + y);
					System.out.println("Current Node X: " + currentNode.getX() + " Y: " + currentNode.getY());
					System.out.println("Next Node X: " + nextNode.getX() + " Y: " + nextNode.getY());
					System.out.println("Path" + path);
					int tempVel = vel;
					vel = 0;
					Node fixNode = randomNode();
					x = fixNode.getX();
					y = fixNode.getY();
					
					rNode = randomNode();
					this.aStar(currentNode, rNode);
					move();
					vel = tempVel;
					Player playPoof = null;
					
					try{
						FileInputStream playPoofIN = new FileInputStream("Portal2_sfx_portal_gun_fire_blue.mp3");
						playPoof = new Player(playPoofIN);
						playPoof.play();
					} catch(Exception exc){
						exc.printStackTrace();
						System.out.println("FAILED TO DO A BARREL ROLL");
					}
					

				}
				this.aStar(currentNode, rNode);
			
			}
			else if(path.size() > 1){
				currentNode = path.get(0);
				nextNode = path.get(1);
									
					
					
					if(nextNode == currentNode)
						path.remove(nextNode);
					if(currentNode.getRightNode() == nextNode){
						x = x + 1;
					}
					else if(currentNode.getLeftNode() == nextNode){
						x = x - 1;
					}
					else if(currentNode.getTopNode() == nextNode){
						y = y + 1;
					}
					else if(currentNode.getBottomNode() == nextNode){
						y = y - 1;
					}
					else{
						path.clear();
						rNode = this.randomNode();
						
						
						currentNode = this.getNode(Simulate.walkway, x, y);
						
						this.aStar(currentNode, rNode);
					}
					
				if((x > 500) || (y > 500) || (x < 0) || (y < 0)){
					System.out.println("Aw shit nigga, we got a runner TWO");
					System.out.println("Runner X: " +  x + " Y: " + y);
					System.out.println("Current Node X: " + currentNode.getX() + " Y: " + currentNode.getY());
					System.out.println("Next Node X: " + nextNode.getX() + " Y: " + nextNode.getY());
					System.out.println("Path" + path);
					Node fixNode = randomNode();
					x = fixNode.getX();
					y = fixNode.getY();
					int tempVel = vel;
					vel = 0;
					
					rNode = randomNode();
					this.aStar(currentNode, rNode);
					move();
					vel = tempVel;
					Player playPoof = null;
					
					try{
						FileInputStream playPoofIN = new FileInputStream("Portal2_sfx_portal_gun_fire_blue.mp3");
						playPoof = new Player(playPoofIN);
						playPoof.play();
					} catch(Exception exc){
						exc.printStackTrace();
						System.out.println("FAILED TO DO A BARREL ROLL");
					}

					
				}
				if(x == nextNode.getX() && y == nextNode.getY()){
					path.remove(0);
					if(path.size() <= 1){
						path.clear();
						rNode = this.randomNode();
						//currentNode = this.getNode(walkway, x, y);
						this.aStar(currentNode, rNode);
					}
					if((x > 500) || (y > 500) || (x < 0) || (y < 0)){
						vel *= -1;
						
					}
				}
			}
	}

    public void changeType(char newType) 
    {
       
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

    public int getVel() 
    {
        return vel;
    }

    public void aStar(Node start, Node goal) {
    	System.out.println(start);
    	System.out.println(currentNode);
        Set<Node> open = new HashSet<Node>();
        Set<Node> closed = new HashSet<Node>();
        
        start.g = 0;
        if(this instanceof Infected )
        	start.h = estimateDistance(start, goal) + goal.zcost;
        if(this instanceof Uninfected )
        	start.h = estimateDistance(start, goal) + goal.hcost;
        start.f = start.h;

        open.add(start);

        while (true) {
            Node current = null;
            System.out.println("Open list size: " + open.size());
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
                if(this instanceof Infected)
                	nextG = current.g + neighbor.zcost;
                if(this instanceof Uninfected)
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
            if((this.estimateDistance(n, currentNode)<radius) && (this.estimateDistance(n, currentNode)>=20)){
                radiusList.add(n);
            }
        }
        random = (int)(Math.random() * (radiusList.size()));
        tempNode = radiusList.get(random);
        for(Node n: radiusList){
        	if(this instanceof Infected && n.isWalkable() && n.zcost < tempNode.zcost)
        		tempNode = n;
        	else if(this instanceof Uninfected && n.isWalkable() && n.hcost < tempNode.hcost)
        		tempNode = n;
        }
        if((radiusList.get(random).isWalkable() == true) && (tempNode != currentNode)){
            return tempNode;
        }
        return randomNode();
    }
}
