package edu.cs160;

import edu.cs160.Game.GameObjects.PlantObjects.Plant;
import edu.cs160.Game.GameObjects.PlantObjects.PlantTracker;
import edu.cs160.Game.GameObjects.PlantObjects.Pot;

public class DEBUGGER {
	public static void debug(){
		Plant test = new Plant("Rose",7,10,0,1);
		Pot p = new Pot(test,200,200);
		PlantTracker.addPlant(test);		
	}
}
