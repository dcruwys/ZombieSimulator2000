package edu.du.cs;

import java.util.ArrayList;

public class Cop extends Uninfected
{
	private int ammo;
	private int gun;
	private ArrayList<Node> hNodes;
	private int targetX; private int targetY;
	public Cop(int xIn, int yIn) {
		super(xIn, yIn);
		type = 'c';
		ammo = 25;
		gun = 1;
		
		hNodes = new ArrayList<Node>();
	}
	
	public int getAmmo()
	{
		return ammo;
	}
	
	public void findSupply( Supplies someSupply )
	{
		ammo += (someSupply.getAmount() * 10);
	}
	
	public void attack(Infected zombie )
	{
		StdDraw.setPenColor(StdDraw.BOOK_LIGHT_BLUE);
		StdDraw.filledCircle(x, y, 4);
		StdDraw.setPenColor(StdDraw.RED);
		StdDraw.filledCircle(zombie.getX(), zombie.getY(), 5);
		targetX = zombie.getX(); targetY = zombie.getY();
		
		int recoil = 14;
		int bulletSX = x; int bulletSY = y;int bulletEX;
		int bulletEY;
//		if(x == zombie.getX()){
//				bulletEX = zombie.getX()-10; 
//				bulletEY = y; 
//		} else if (y == zombie.getY()){
//				bulletEX = x; 
//				bulletEY = zombie.getY()-10; 
//		} else {
			int dirX; int dirY;
			if((x == zombie.getX()) && !(y > zombie.getY() || (y < zombie.getY()))){
				dirX = 1;
				dirY = 0;
				System.out.println("PEWPEW on the X");
			} else if((y == zombie.getY()) && !(x > zombie.getX() || (x < zombie.getX()))){
				dirY = 1;
				dirX = 0;
				System.out.println("PEWPEW on the Y");

			} else {
				dirX = 0; dirY = 0;
				System.out.println("No Pew");

			}
			bulletEX =  x + x*dirX; 
			bulletEY = y + y*dirY; 
//		}
		//if(gun == 1){
			while(ammo != 0){
				if(recoil == 14){
					StdDraw.setPenColor(StdDraw.ORANGE);
					StdDraw.line(bulletSX, bulletSY, bulletEX, bulletEY);
					bulletSX++; bulletSY++; bulletEX++; bulletEY++;
					ammo--;
					System.out.println("Ammo: " + ammo);
					recoil = 0;
				} else{
					recoil++;
					if(ammo == 0){
						ammo = 20;
					}
					System.out.println("Recoil cooldown: " + recoil);
					System.out.println("Ammo remaining: " + ammo);
				}
			}
		//}
	}
	
	public Infected getInSight()
	{
		//if(Infected.)
		return null;
	}
	
	public void move(){
 
		for(Node n: Simulate.walkway){
			if(n.hcost == 12){
			}
			if(Math.abs(n.getX() - this.x) <= 0 && Math.abs(n.getY() - this.y) <= 0 && !hNodes.contains(n)){
				n.hcost = 1;	
				n.zcost = 1;
				hNodes.add(n);
			}
			else if(Math.abs(n.getX() - this.x) <= 10 && Math.abs(n.getY() - this.y) <= 10 && n.zcost != 1){
				n.hcost = 2;
				n.zcost = 1;
				if(!hNodes.contains(n))
					hNodes.add(n);
			}
			else if(Math.abs(n.getX() - this.x) <= 20 && Math.abs(n.getY() - this.y) <= 20 && n.zcost != 2){
				n.hcost = 3;
				n.zcost = 1;
				if(!hNodes.contains(n))
					hNodes.add(n);
			}
			else if(Math.abs(n.getX() - this.x) <= 30 && Math.abs(n.getY() - this.y) <= 30 && n.zcost != 3){
				n.zcost = 4;	
				if(!hNodes.contains(n))
					hNodes.add(n);
			}
			else if(Math.abs(n.getX() - this.x) <= 40 && Math.abs(n.getY() - this.y) <= 40 && n.zcost != 4){
				n.zcost = 5;	
				if(!hNodes.contains(n))
					hNodes.add(n);
			}
			else if(Math.abs(n.getX() - this.x) <= 50 && Math.abs(n.getY() - this.y) <= 50 && n.zcost != 5){
				n.zcost = 6;	
				if(!hNodes.contains(n))
					hNodes.add(n);
			}
        	else {
        		if(hNodes.contains(n)){
					n.hcost = 10;
					n.zcost = 10;
					hNodes.remove(n);
				}
			}
        }
		super.move();
	}
	
}
