package edu.du.cs;

import java.util.ArrayList;

public class AStarPathFinding {
	private ArrayList<Node> openList = new ArrayList<Node>();
	private ArrayList<Node> processed = new ArrayList<Node>();
	
	private ArrayList<Node> nodePath = new ArrayList<Node>();
	Node Parent;
	Node Goal;
	Node BestNode;
	int G = 0; // movement cost [Start Node, to some Node B]
	int H; //Estimated cost from some Node B to Goal
	int F = (int)Double.POSITIVE_INFINITY; //Total move cost from G + H
	
	public AStarPathFinding(Node Start, Node Goal) {
		//Note to Team, create kill class in case the AI becomes self-aware
		openList.add(Start);
		this.Parent = Start;
		this.Goal = Goal;
	}
	public ArrayList<Node> findPath(){
		//For each adjacent node, set the parent to current parent
		//Then add it to be processed
		System.out.println("Adjacent Node Size: " + Parent.getAdjacentNodes().size());
		for(Node adj : Parent.getAdjacentNodes()){
			if(!(processed.contains(adj))){
				adj.setParent(Parent);
				openList.add(adj);
			}

		}
		openList.remove(Parent);processed.add(Parent);
		System.out.println(openList.size());
		while(openList.size() != 0){
			
			int Fmin;
			System.out.println(openList.size());
			for(Node aNode : openList){
				
				//Estimate distance H, if we add diagnals the formula will be sqrt((aNode.x-Goal.x)^2 + (aNode.y-Goal.y)^2)
				H = (Math.abs((aNode.getMyX() - Goal.getMyX())) + Math.abs((aNode.getMyY() - Goal.getMyY())));
				Fmin = (aNode.getCost()+G) + H;
				if(Fmin < F){
					F = Fmin;
					BestNode = aNode;
				}
				openList.remove(aNode);
				processed.add(aNode);
			}
		}
		if(BestNode != Goal){
			nodePath.add(BestNode);
			findPath();
		}
		return nodePath;
	}
	
	//It's ALIVE!!!
	
	public Node setStartNode(Node aNode){
		return Parent = aNode;
	}
	public Node setGoalNode(Node aNode){
		return Goal = aNode;
	}
	public ArrayList<Node> getPath(){
		return nodePath;
	}

}
