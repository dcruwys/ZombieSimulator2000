package edu.du.cs;

public class Normal extends Uninfected
{

	public Normal(int xIn, int yIn) {
		super(xIn, yIn);
		type = 'n';
	}
	
	public void findSupply( Supplies someSupply )
	{
		double random = Math.random();
		if( random < .5 )
			return; //does nothing
//		else if( (random < .7) && (random >= .5) )
//			changeType('m');
//		else if ((random >= .7))
//			changeType('c');
	}
}
