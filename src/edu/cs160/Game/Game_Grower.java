package edu.cs160.Game;

import edu.cs160.R;
import edu.cs160.Game.GameObjects.DataInitializer;
import edu.cs160.Game.Screens.Game_Grower_Screen;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

public class Game_Grower extends Activity{
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		DataInitializer.mainActivity = this;		
		setContentView(R.layout.garden_grower);
		//setContentView(new Game_Grower_Screen(this));
	}
}
