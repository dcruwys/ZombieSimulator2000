
import java.util.ArrayList;

public class Cop extends Uninfected
{
	private int ammo;
	private ArrayList<Node> hNodes;
	/*
	 * @param xIn
	 * @param yIn
	 * takes in x and y and creates a cop human
	 */
	public Cop(int xIn, int yIn) {
		super(xIn, yIn);
		type = 'c';
		ammo = 25;
		hNodes = new ArrayList<Node>();
	}
    /*
     * @return ammo
     * returns the current Ammo
     */
	public int getAmmo()
	{
		return ammo;
	}
    /*
     * @param zombie
     * attacks a zombie
     */
	public void attack(Infected zombie )
	{
		int bulletSX = x; int bulletSY = y;
		int bulletEX = zombie.getX(); int bulletEY = zombie.getY();

		while((ammo != 0) && (zombie.getHP() >= 0)){
				StdDraw.setPenRadius(0.005);
				StdDraw.setPenColor(StdDraw.DARK_GRAY);
				StdDraw.line(bulletSX, bulletSY, bulletEX, bulletEY);
				ammo--;
				zombie.attacked();
		}
		move();
	}
	
    /*
     * moves the human object
     */
	public void move(){
		for(Node n: Simulate.walkway){
			if(Math.abs(n.getX() - this.x) <= 0 && Math.abs(n.getY() - this.y) <= 0 && !hNodes.contains(n)){
				n.hcost = 1;	
				n.zcost = 1;
				hNodes.add(n);
			}
			else if(Math.abs(n.getX() - this.x) <= 10 && Math.abs(n.getY() - this.y) <= 10 && n.zcost != 1 && n.hcost != 1){
				n.hcost = 2;
				n.zcost = 2;
				if(!hNodes.contains(n) && hNodes.contains(n))
					hNodes.add(n);
			}
			else if(Math.abs(n.getX() - this.x) <= 20 && Math.abs(n.getY() - this.y) <= 20 && n.zcost != 2){
				n.zcost = 3;
				if(!hNodes.contains(n))
					hNodes.add(n);
				if(n.hcost <= 2 && hNodes.contains(n))
					n.hcost = 10;
			}
			else if(Math.abs(n.getX() - this.x) <= 30 && Math.abs(n.getY() - this.y) <= 30 && n.zcost != 3){
				n.zcost = 4;	
				if(!hNodes.contains(n))
					hNodes.add(n);
				if(n.hcost <= 2 && hNodes.contains(n))
					n.hcost = 10;
			}
			else if(Math.abs(n.getX() - this.x) <= 40 && Math.abs(n.getY() - this.y) <= 40 && n.zcost != 4){
				n.zcost = 5;	
				if(!hNodes.contains(n))
					hNodes.add(n);
				if(n.hcost <= 2 && hNodes.contains(n))
					n.hcost = 10;
			}
			else if(Math.abs(n.getX() - this.x) <= 50 && Math.abs(n.getY() - this.y) <= 50 && n.zcost != 5){
				n.zcost = 6;	
				if(!hNodes.contains(n))
					hNodes.add(n);
				if(n.hcost <= 2 && hNodes.contains(n))
					n.hcost = 10;
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
