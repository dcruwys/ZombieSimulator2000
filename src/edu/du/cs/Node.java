package edu.du.cs;

public class Node {
	private int myX;
	private int myY;

	public Node(int x, int y){
		myX = x;
		myY = y;
	}
	
	public int getMyX(){
		return myX;
	}
	public int getMyY(){
		return myY;
	}
	
	public void setMyX(int x){
		myX = x;
	}
	public void setMyY(int y){
		myY = y;
	}
	
	
	public String toString(){
		return "Node X: " + myX + " Node Y: " + myY;
	}
	
}
