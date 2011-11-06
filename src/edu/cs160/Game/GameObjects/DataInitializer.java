package edu.cs160.Game.GameObjects;

import edu.cs160.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.BitmapFactory;
import android.os.Handler;

public class DataInitializer {
	public static Activity mainActivity;
public static void init(Context c){
	PaintObjects.init();
	GameObjects.addData("Pot", BitmapFactory.decodeResource(c.getResources(), R.drawable.misc_pot));
	GameObjects.addData("Pot_Small", BitmapFactory.decodeResource(c.getResources(), R.drawable.misc_pot_small));
	GameObjects.addData("Sapling1", BitmapFactory.decodeResource(c.getResources(), R.drawable.plant_sapling1));
	GameObjects.addData("Sapling2", BitmapFactory.decodeResource(c.getResources(), R.drawable.plant_sapling2));
	GameObjects.addData("Sapling1_anim", BitmapFactory.decodeResource(c.getResources(), R.drawable.plant_sapling1_anim));
	GameObjects.addData("Sapling2_anim", BitmapFactory.decodeResource(c.getResources(), R.drawable.plant_sapling2_anim));
	
	GameObjects.addData("Background", BitmapFactory.decodeResource(c.getResources(), R.drawable.dirt));
}
}
