package edu.du.cs;

public class Medic extends Uninfected  
{
	private int medicine;
	
	public Medic(int xIn, int yIn) {
		super(xIn, yIn);
		medicine = 3;
		type = 'm';
	}
    /*
     * @return medicine
     * returns current medicine
     */
	public int getMedicine()
	{
		return medicine;
	}
	
}
