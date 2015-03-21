
public class Medic extends Uninfected  
{
	private int medicine;
	/*
	 * @param xIn
	 * @param yIn
	 * takes in x and y and creates a medic human
	 */
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
