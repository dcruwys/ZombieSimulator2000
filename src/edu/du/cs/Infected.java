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
        vel = 1 + (int) Math.floor(Math.random() * 3);
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
	        for(Node n: Simulate.zWalkway){
	        	if(Math.abs(n.getX() - this.x) < 30 && Math.abs(n.getY() - this.y) < 30 && !zNodes.contains(n)){
	        		n.setAlpha(0);
					n.cost = 10;	
					zNodes.add(n);
				}
	        	else {
					n.setAlpha(1);
					n.cost = 10;
					zNodes.remove(n);
				}
	        }
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