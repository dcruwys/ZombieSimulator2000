package edu.du.cs;
import java.awt.Color;

import edu.du.cs.StdDraw;



public class GraphicsEngine {

	public GraphicsEngine(){
		
	}
	
//  ____        _ _     _ _                 
// | __ ) _   _(_) | __| (_)_ __   __ _ ___ 
// |  _ \| | | | | |/ _` | | '_ \ / _` / __|
// | |_) | |_| | | | (_| | | | | | (_| \__ \
// |____/ \__,_|_|_|\__,_|_|_| |_|\__, |___/
//                                |___/     
	
	public void drawBuildings(Building aBuilding){
		int type = aBuilding.getMyType();
		//Create any building type modifications here, size and width are controlled in the constructor.
		
		if(type == 1){
			StdDraw.setPenColor(StdDraw.RED);
		}
//		
		if(type == 2){
			StdDraw.setPenColor(StdDraw.BLUE);
		}
		StdDraw.filledRectangle(aBuilding.getMyXCoord(), aBuilding.getMyYCoord(), aBuilding.getMySize()/2, aBuilding.getMySize()/4);
	//	StdDraw.filledRectangle(aBuilding.getMyXCoord(), aBuilding.getMyYCoord(), aBuilding.getMyWidth(), aBuilding.getMyHeight());

		//building types shit here
	}
	
	
//  ____ _                          _                
// / ___| |__   __ _ _ __ __ _  ___| |_ ___ _ __ ___ 
//| |   | '_ \ / _` | '__/ _` |/ __| __/ _ \ '__/ __|
//| |___| | | | (_| | | | (_| | (__| ||  __/ |  \__ \
// \____|_| |_|\__,_|_|  \__,_|\___|\__\___|_|  |___/
//                                                                         
	
	//Humans
	public void drawHumans(Human aHuman){
		StdDraw.setPenColor(new Color(247, 235, 202));
		StdDraw.filledSquare(aHuman.getX(), aHuman.getY(), 4);
		
//		StdDraw.picture(x, y, s);
	}
	//Medics
	public void drawMedics(Medic aMedic){
		StdDraw.setPenColor(new Color(252, 58, 58));
		StdDraw.filledSquare(aMedic.getX(), aMedic.getY(), 4);
//		StdDraw.picture(x, y, s);
	}
	
	//Cops
	public void drawCops(Cop aCop){
		StdDraw.setPenColor(new Color(59, 150, 247));
		StdDraw.filledSquare(aCop.getX(), aCop.getY(), 4);
		
//		StdDraw.picture(x, y, s);
	}
	
	//Infected
	public void drawInfected(Infected anInfected){
		StdDraw.setPenColor(new Color(129, 255, 61));
		StdDraw.filledSquare(anInfected.getX(), anInfected.getY(), 4);
		
//		StdDraw.picture(x, y, s);
	}
	//TAAAAAAANNNK
	public void drawTank(Tank aTank){
		StdDraw.setPenColor(new Color(37, 158, 73));
		StdDraw.filledSquare(aTank.getX(), aTank.getY(), 6);
		
//		StdDraw.picture(x, y, s);
	}
	
	
	


	public static void main(String[] args){
		StdDraw.setCanvasSize(500, 500);
		StdDraw.setXscale(0.0, 1000.0);
		StdDraw.setYscale(0.0, 500.0);
		GraphicsEngine g = new GraphicsEngine();
		Building b = new Building(100, 100, 2);
		Human h = new Human(300, 300);
		
		g.drawBuildings(b);
		g.drawHumans(h);
//		StdDraw.setPenColor(StdDraw.BLUE);
//		StdDraw.rectangle(, 25, 12, 12);
	}
}