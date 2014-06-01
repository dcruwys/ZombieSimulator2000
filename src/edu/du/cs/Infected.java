package edu.du.cs;

public class Infected extends Human 
{
    private boolean isDead;
    
    private int hp;

    public Infected(int xIn, int yIn) {
        super(xIn, yIn);
        isDead = false;
        changeType('z');
        walkway = Simulate.zWalkway;
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
   
}