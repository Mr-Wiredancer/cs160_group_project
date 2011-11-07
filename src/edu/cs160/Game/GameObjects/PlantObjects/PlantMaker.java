package edu.cs160.Game.GameObjects.PlantObjects;

import edu.cs160.Game.GameObjects.DataInitializer;
import edu.cs160.Game.GameObjects.Sprites.Sprite;

public class PlantMaker {
public static Sprite makePlant(int type, int growthStage){
	if (growthStage ==0){
		return new Sprite("Sapling1_anim",3,1,3,true);
	}else if(growthStage==1){
		return new Sprite("Sapling2_anim",3,1,3,true);
	}else if (type==DataInitializer.flowerID.ROSE){
		return new Sprite(Integer.toString(DataInitializer.flowerID.ROSE),3,1,3,true);
	}else if (type==DataInitializer.flowerID.BLUE_ROSE){
		return new Sprite(Integer.toString(DataInitializer.flowerID.BLUE_ROSE),3,1,3,true);
	}else if (type==DataInitializer.flowerID.CAMELLIA){
		return new Sprite(Integer.toString(DataInitializer.flowerID.CAMELLIA),1,1,1,true);
	}else if (type==DataInitializer.flowerID.FORGETMENOT){
		return new Sprite(Integer.toString(DataInitializer.flowerID.FORGETMENOT),1,1,1,true);
	}
	
	return new Sprite("Sapling2_anim",3,1,3,true);
	
}
}
