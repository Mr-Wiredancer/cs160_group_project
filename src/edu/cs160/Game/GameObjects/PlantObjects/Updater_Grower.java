package edu.cs160.Game.GameObjects.PlantObjects;

import edu.cs160.Game.GameObjects.DataInitializer;
import edu.cs160.*;
import android.app.Activity;
import android.widget.*;

public class Updater_Grower implements Runnable{
	Plant p;		
	public Updater_Grower(Plant p){
		this.p = p;
	}
	@Override
	public void run() {
		Activity a = DataInitializer.mainActivity;
		p.Update((TextView)a.findViewById(R.id.Description_Garden_GrowerPlant),
				 (TextView)a.findViewById(R.id.Description_Garden_Grower_Progress_Value),
				 (ProgressBar)a.findViewById(R.id.Description_Garden_Grower_Progress));
		//p.Update((TextView)DataInitializer.mainActivity.findViewById(R.id.Description_Plant), (ProgressBar)DataInitializer.mainActivity.findViewById(R.id.Description_Progress));
		
	}

}
