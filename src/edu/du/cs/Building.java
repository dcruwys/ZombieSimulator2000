package edu.du.cs;

//Create a simulation class to handle all generation stuff.
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
}
