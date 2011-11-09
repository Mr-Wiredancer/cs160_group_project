package edu.cs160;

import edu.cs160.Game.GameObjects.DataInitializer;
import edu.cs160.Game.GameObjects.PlantObjects.Plant;
import edu.cs160.Game.GameObjects.PlantObjects.PlantTracker;
import edu.cs160.Game.GameObjects.PlantObjects.Pot;

public class DEBUGGER {
	public static boolean debugged = false;
	public static void debug(){
		if(debugged){
			return;
		}
		debugged = true;
//		Plant test = new Plant("Rose",7,10,0,1);
////		Pot p = new Pot(test,200,200);
//		Plant test2 = new Plant("Rose2",5,10,0,1);
		PlantTracker.addPlant(new Plant("Rose Seedling",3,10,0,DataInitializer.flowerID.ROSE));
		PlantTracker.addPlant(new Plant("Rose Bud",6,10,1,DataInitializer.flowerID.ROSE));
		PlantTracker.addPlant(new Plant("Rose",10,10,2,DataInitializer.flowerID.ROSE));
		PlantTracker.addPlant(new Plant("Blue Rose",10,10,2,DataInitializer.flowerID.BLUE_ROSE));
		PlantTracker.addPlant(new Plant("Camellia",10,10,2,DataInitializer.flowerID.CAMELLIA));
		PlantTracker.addPlant(new Plant("Forget Me Not",10,10,2,DataInitializer.flowerID.FORGETMENOT));
		
		
	}
}
