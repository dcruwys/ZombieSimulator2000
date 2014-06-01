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
    protected String type;
    protected int vel;
    private int random;
    protected List<Node> path;
    protected Node currentNode;
    protected Node nextNode;
    private Node rNode;
    protected ArrayList<Node> walkway;

    public Human(int xIn, int yIn) {
        x = xIn;
        y = yIn;
        walkway = Simulate.walkway;
        currentNode = getNode(walkway, x, y);
        rNode = randomNode();
        path = new ArrayList<Node>();
        aStar(currentNode, rNode);
        
        System.out.println("START OF THE LINE...");
        System.out.println("Current Node: "+currentNode);
        System.out.println("Random Node: "+rNode);
        System.out.println("Path: "+path);
        System.out.println();
    }
    
    public void move()
    {
        for(Node n: walkway){
            if(Math.abs(n.getX() - this.x) < 100 && Math.abs(n.getY() - this.y) < 100){
                
            }
        }
        if(path.size() <= 1){
            path.clear();
            rNode = this.randomNode();
            currentNode = this.getNode(walkway, x, y);
            this.aStar(currentNode, rNode);
        }
        else if(path.size() > 1){
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
    }

    public void changeType(char newType) 
    {
        switch(newType)
        {
        case 'z':
            type = "Infected"; 
            break;
        case 'm':
            type = "Medic"; break;
        case 'c':
            type = "Cop"; break;
        case 'n':
            type = "Normal"; break;
        }
        GraphicsEngine.drawHuman( this );
    }

    public abstract void die();

    public String getType() 
    {
        return type;
    }

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
        Set<Node> open = new HashSet<Node>();
        Set<Node> closed = new HashSet<Node>();
        
        start.g = 0;

        start.h = estimateDistance(start, goal) * goal.getAlpha() + goal.cost;
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
    
    public abstract Node randomNode();
    
//    {
//        Node tempNode = null;
//        ArrayList<Node> radiusList = new ArrayList<Node>();
//        int radius = 100; //Random Node Radius
//        for(Node n : walkway){
//            if((this.estimateDistance(n, currentNode)<radius) && this.estimateDistance(n, currentNode) > 50){
//                radiusList.add(n);
//            }
//        }
//        random = (int )(Math.random() * (radiusList.size()));
//
//        if(radiusList.get(random).isWalkable() == true && tempNode != currentNode){
//            return tempNode = radiusList.get(random);
//        } 
//        return randomNode();
//    }
}
