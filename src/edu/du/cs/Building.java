package edu.du.cs;

public class Building {
	
	private int mySize;
	private int[][] grid;

	public Building(){
		mySize = 100;
		grid = new int[mySize][mySize];
	}
	
	public int getMySize() {
		return mySize;
	}
	public void setMySize(int mySize) {
		this.mySize = mySize;
	}
	
	public int[][] getGrid(){
		return grid;
	}
	
	public void generate(){
		for (int row=0; row < mySize; row ++){
			for (int col=0; col < mySize; col ++){
				grid[row][col] = 3;
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
		for (int row=0; row < mySize; row ++){
			System.out.println();
			for (int col=0; col < mySize; col ++){
				System.out.print(grid[row][col]);
			}
		}
	}
}
