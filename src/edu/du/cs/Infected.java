package edu.du.cs;

import java.util.ArrayList;

public class Infected extends Human 
{
    private boolean isDead;
    
    private int hp;
    private ArrayList<Node> zNodes;
    public Infected(int xIn, int yIn) {
        super(xIn, yIn);
        
        walkway = Simulate.zWalkway;
        
        isDead = false;
        changeType('z');
        hp = 10;
        vel = 2 + (int) Math.floor(Math.random() * 2);
        zNodes = new ArrayList<Node>();
    }

    public void die() 
    {
        isDead = true;
    }
    
    public int getHP()
    {
        return hp;
    }
    
    @Override
    public void move()
    {
        if(!isDead)
        {
	        for(Node n: Simulate.hWalkway){
	        	if(Math.abs(n.getX() - this.x) <= 0 && Math.abs(n.getY() - this.y) <= 0 && !zNodes.contains(n)){
	        		n.cost = 14;	
					zNodes.add(n);
				}
				else if(Math.abs(n.getX() - this.x) <= 10 && Math.abs(n.getY() - this.y) <= 10 && n.cost != 14){
					n.cost = 13;	
					if(!zNodes.contains(n))
						zNodes.add(n);
				}
				else if(Math.abs(n.getX() - this.x) <= 20 && Math.abs(n.getY() - this.y) <= 20 && n.cost != 13){
					n.cost = 12;	
					if(!zNodes.contains(n))
						zNodes.add(n);
				}
				else if(Math.abs(n.getX() - this.x) <= 30 && Math.abs(n.getY() - this.y) <= 30 && n.cost != 12){
					n.cost = 11;	
					if(!zNodes.contains(n))
						zNodes.add(n);
				}
	        	else {
	        		if(zNodes.contains(n)){
						n.cost = 10;
						zNodes.remove(n);
					}
				}	        }
	        super.move();
	           
        }
    }
    
    public void attack( Uninfected human )
    {
        human.die();
    }
    
    public void attack( Medic doc )
    {
        doc.cure( this );
    }
   
}