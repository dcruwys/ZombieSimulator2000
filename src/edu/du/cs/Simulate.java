package edu.du.cs;

import java.util.ArrayList;

public class Simulate {
	protected static int mySize = 11;
	protected static int[][] grid = new int[mySize][mySize];
	public static ArrayList<Node> walkway = new ArrayList<Node>();
	/*
	 * generates buildings data
	 */
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
				   if(row == mySize/2){
					   grid[row+1][col] = 0;
					   grid[row-1][col] = 0;
				   }
			}
		}
		//second pass
		for (int row=0; row < mySize; row ++){
			for (int col=0; col < mySize; col += 5){
				   grid[row][col] = 0;
				   if(col == mySize/2){
					   grid[row][col-1] = 0;
				   	   grid[row][col+1] = 0;
				   }
			}
		}
		//generation
		for (int row=1; row < mySize-5; row ++){
			for (int col=1; col < mySize-5; col ++){
				if(grid[row+1][col] == 0 && grid[row][col] == 1  && (grid[row][col+1] == 1 && grid[row][col-1] == 1) && (int) Math.ceil(Math.random()*100) < 8){
					grid[row][col] = 0;
					grid[row-1][col] = 0;
					grid[row-2][col] = 0;
					grid[row-3][col] = 0;
				}
				if(grid[row][col+1] == 0 && grid[row][col] == 1  && (grid[row+1][col] == 1 && grid[row-1][col] == 1) && (int) Math.ceil(Math.random()*100) < 8){
					grid[row][col] = 0;
					grid[row][col-1] = 0;
					grid[row][col-2] = 0;
					grid[row][col-3] = 0;
				}
				if(grid[row][col] == 0 && ((grid[row+1][col] == 1 && grid[row-1][col] == 1 ) || (grid[row][col-1] == 1 && grid[row][col-1] == 1 )) && (int) Math.ceil(Math.random()*100) == 1)
					grid[row][col]  = 8;
			}
		}
		
		for (int row=0; row < mySize; row ++){
			for (int col=0; col < mySize; col ++){
				if(grid[row][col] == 0){
					walkway.add(new Node((row*10-5), (col*10-5), true));
				}
				else if(grid[row][col] == 1 || grid[row][col] == 8){
					walkway.add(new Node((row*10-5), (col*10-5), false));
				}
			}
		}
		
		for(Node n: walkway){
			n.setAdjacent();
		}	
	}
}
