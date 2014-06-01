package edu.du.cs;

import java.util.ArrayList;

public class Simulate {
	protected static int mySize = 51;
	protected static int[][] grid = new int[mySize][mySize];
	private static ArrayList<Node> walkway = new ArrayList<Node>();
	protected static ArrayList<Node> hWalkway;
	protected static ArrayList<Node> zWalkway;

	
	public static void generateBuildings(){
		for (int row=0; row < mySize; row ++){
			for (int col=0; col < mySize; col ++){
				grid[row][col] = 1;
			}
		}
		//first pass
		for (int row=0; row < mySize; row += 5){
			for (int col=0; col < mySize; col ++){
				   grid[row][col] = 0;
			}
		}
		//second pass
		for (int row=0; row < mySize; row ++){
			for (int col=0; col < mySize; col += 5){
				   grid[row][col] = 0;
			}
		}
		//generation
		for (int row=0; row < mySize; row ++){
			for (int col=0; col < mySize; col ++){
				if(grid[row][col] != 0 && (int) Math.ceil(Math.random()*100) > 10){
				   grid[row][col] = 1;
				}
			}
		}
//		for (int row=0; row < mySize; row ++){
//			System.out.println();
//			for (int col=0; col < mySize; col ++){
//				System.out.print(grid[row][col]);
//			}
//		}
		for (int row=0; row < mySize; row ++){
			for (int col=0; col < mySize; col ++){
				if(grid[row][col] == 0)
					walkway.add(new Node((row*10-5), (col*10-5), true));
				else if(grid[row][col] == 1 || grid[row][col] == 8)
					walkway.add(new Node((row*10-5), (col*10-5), false));
			}
		}
		zWalkway = new ArrayList<Node>(walkway);
		hWalkway = new ArrayList<Node>(walkway);
	}

	
}
