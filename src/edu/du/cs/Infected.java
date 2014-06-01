package edu.du.cs;

import java.util.ArrayList;

public class Infected extends Human 
{
	private boolean isDead;
	
	private int hp;

	public Infected(int xIn, int yIn) {
		super(xIn, yIn);
		isDead = false;
		changeType('z');
		hp = 10;
		vel = 1 + (int) Math.floor(Math.random() * 6);
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
		while(!isDead)
		{
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
	
	public Node randomNode()
	{
		Node tempNode = null;
		ArrayList<Node> radiusList = new ArrayList<Node>();
		int radius = 50; //Random Node Radius
		for(Node n : Simulate.walkway){
			if((this.estimateDistance(n, currentNode)<radius)){
				radiusList.add(n);
			}
		}
		
		for(Node n : radiusList)
		{
			n.setAlpha(0);
			int distance = estimateDistance(n, currentNode);
			if(distance < 10)
				n.setMyCost(60);
			else if((distance >= 10) && (distance < 20))
				n.setMyCost(50);
			else if((distance >= 20) && (distance < 30))
				n.setMyCost(40);
			else if((distance >= 30) && (distance < 40))
				n.setMyCost(30);
			else if((distance >= 40) && (distance < 50))
				n.setMyCost(20);
		}
	}

}
