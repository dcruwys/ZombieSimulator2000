package edu.du.cs;
public class Building {
	
	private int mySize;
	private int[][] grid;
	private int myXCoord;
	private int myYCoord;
	private int myType;
	
	

	public Building(int x, int y, int t){
		mySize = 100;
		grid = new int[mySize][mySize];
		//Configure this shit Dan
		myXCoord = x;
		myYCoord = y;
		myType = t;
	}
	
	public int getMySize() {
		return mySize;
	}
	public void setMySize(int mySize) {
		this.mySize = mySize;
	}
	public int getMyXCoord() {
		return myXCoord;
	}
	public void setMyXCoord(int myXCoord) {
		this.myXCoord = myXCoord;
	}
	public int getMyYCoord() {
		return myYCoord;
	}
	public void setMyYCoord(int myYCoord) {
		this.myYCoord = myYCoord;
	}
	public int getMyType() {
		return myType;
	}
	public void setMyType(int myType) {
		this.myType = myType;
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
