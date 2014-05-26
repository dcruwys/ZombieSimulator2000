
public class test { 
	//DRUNKARD map generation
	//Test idea for map generation
	//Super slow unfortionatly
		public static void main(String[] args){
	        int N = 150;
	        StdDraw.setXscale(-N, +N);
	        StdDraw.setYscale(-N, +N);
	        StdDraw.clear(StdDraw.GRAY);

	        int x = 0, y = 0;
	        int steps = 0;
	        while (steps < 400) {
	            StdDraw.setPenColor(StdDraw.WHITE);
	            StdDraw.filledSquare(x, y, 4);
	            double r = Math.random();
	            if      (r < 0.25) {
	            	//x--;
	            	x = x - 8;
	            }
	            else if (r < 0.50) {
	            	//x++;
	            	x = x + 8;
	            }
	            else if (r < 0.75) {
	            	//y--;
	            	y = y - 8;
	            }
	            else if (r < 1.00) {
	            	//y++;
	            	y = y + 8;
	            }
	            System.out.println("X: " + x + "Y: " + y);
	            steps++;
	            StdDraw.setPenColor(StdDraw.BLUE);
	            StdDraw.filledSquare(x, y, 4);
	            StdDraw.show(40);
	        }
	        System.out.println("Total steps = " + steps);
	    }

	}


