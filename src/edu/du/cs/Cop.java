package edu.du.cs;

public class Cop extends Human 
{
	private int ammo;
	
	public Cop(){
		super();
		ammo = 0;
		this.changeType("Cop");
	}
	public int getAmmo()
	{
		return ammo;
	}

	public void findSupplies( Supplies someSupply )
	{
		//increases ammo
		
	}
	
	public void attack( Infected zombie )
	{
		ammo--;
		//Attacks zombie
	}
}
