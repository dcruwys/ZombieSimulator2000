
import java.util.ArrayList;

public class Infected extends Human 
{   
    private int hp;
    private boolean isCured;
    private ArrayList<Node> zNodes;
    /*
	 * @param xIn
	 * @param yIn
	 * takes in x, y and w and creates an infected zombie
	 */
    public Infected(int xIn, int yIn) {
        super(xIn, yIn);
        hp = 5;
        vel = 1;

        zNodes = new ArrayList<Node>();
        isDead = false;
        type = 'i';
    }

    public void die() 
    {
        isDead = true;
    }
    
    public int getHP()
    {
        return hp;
    }
    
    public boolean isCured()
    {
    	return isCured;
    }
    
    public void setCured( boolean answer )
    {
    	isCured = answer;
    }
    
    @Override
    /*
	 * moves the object
	 */
    public void move()
    {
        if(!isDead)
        {
	        for(Node n: Simulate.walkway){
	        	if(Math.abs(n.getX() - this.x) <= 0 && Math.abs(n.getY() - this.y) <= 0 && !zNodes.contains(n)){
	        		n.hcost = 14;	
					zNodes.add(n);
				}
				else if(Math.abs(n.getX() - this.x) <= 10 && Math.abs(n.getY() - this.y) <= 10 && n.hcost != 14){
					n.hcost = 13;	
					if(!zNodes.contains(n))
						zNodes.add(n);
				}
				else if(Math.abs(n.getX() - this.x) <= 20 && Math.abs(n.getY() - this.y) <= 20 && n.hcost != 13){
					n.hcost = 12;	
					if(!zNodes.contains(n))
						zNodes.add(n);
				}
				else if(Math.abs(n.getX() - this.x) <= 30 && Math.abs(n.getY() - this.y) <= 30 && n.hcost != 12){
					n.hcost = 11;	
					if(!zNodes.contains(n))
						zNodes.add(n);
				}
	        	else {
	        		if(zNodes.contains(n)){
						n.hcost = 10;
						zNodes.remove(n);
					}
				}	       
	        }
	        super.move();   
        }
    }
    /*
	 * attack the human
	 */
    public void attack( Uninfected human )
    {
        human.die();
    }
    /*
	 * sets if attacked
	 */
    public void attacked()
    {
    	hp = hp-1;
    	if( hp <= 0 )
    		die();
    }
   
}