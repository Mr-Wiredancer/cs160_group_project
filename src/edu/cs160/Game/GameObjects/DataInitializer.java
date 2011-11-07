package edu.cs160.Game.GameObjects;

import edu.cs160.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Handler;

public class DataInitializer {
	public static Boolean mainGarden = true;
	public static Boolean GardenGrower = true;
	public static Activity mainActivity;
	public static int invHeight = 200;
public static void init(Context c){
	PaintObjects.init();
	GameObjects.addData("Pot", BitmapFactory.decodeResource(c.getResources(), R.drawable.misc_pot),R.drawable.misc_pot);
	GameObjects.addData("Pot_Small", BitmapFactory.decodeResource(c.getResources(), R.drawable.misc_pot_small), R.drawable.misc_pot_small);
	GameObjects.addData("Sapling1", BitmapFactory.decodeResource(c.getResources(), R.drawable.plant_sapling1),R.drawable.plant_sapling1);
	GameObjects.addData("Sapling2", BitmapFactory.decodeResource(c.getResources(), R.drawable.plant_sapling2),R.drawable.plant_sapling2);
	GameObjects.addData("Sapling1_anim", BitmapFactory.decodeResource(c.getResources(), R.drawable.plant_sapling1_anim),R.drawable.plant_sapling1_anim);
	GameObjects.addData("Sapling2_anim", BitmapFactory.decodeResource(c.getResources(), R.drawable.plant_sapling2_anim),R.drawable.plant_sapling2_anim);
	
	/*
	 * Flowers
	 */
	GameObjects.addData(Integer.toString(flowerID.ROSE), BitmapFactory.decodeResource(c.getResources(), R.drawable.rose),R.drawable.rose);
	GameObjects.addData(Integer.toString(flowerID.BLUE_ROSE), BitmapFactory.decodeResource(c.getResources(), R.drawable.blue_rose),R.drawable.blue_rose);
	GameObjects.addData(Integer.toString(flowerID.CAMELLIA), BitmapFactory.decodeResource(c.getResources(), R.drawable.camellia),R.drawable.camellia);
	GameObjects.addData(Integer.toString(flowerID.FORGETMENOT), BitmapFactory.decodeResource(c.getResources(), R.drawable.forgetmenot),R.drawable.forgetmenot);

	//GameObjects.addData("Background", BitmapFactory.decodeResource(c.getResources(), R.drawable.dirt),R.drawable.dirt);
}

public static final class flowerID{
	public static final int ROSE = 0;
	public static final int BLUE_ROSE = 1;
	public static final int CAMELLIA = 2;
	public static final int FORGETMENOT = 3;
}
}
