package edu.cs160.Game.GameObjects.PlantObjects;

import edu.cs160.Game.GameObjects.Sprites.Sprite;

public class PlantMaker {
public static Sprite makePlant(int type, int growthStage){
	return new Sprite("Sapling",3,1,3,true);
}
}
