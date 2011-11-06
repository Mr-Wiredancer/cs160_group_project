package edu.cs160.Game.Screens;
import java.util.concurrent.ScheduledFuture;

import edu.cs160.Game.GameObjects.DataInitializer;
import edu.cs160.Game.GameObjects.GameObjects;
import edu.cs160.Game.GameObjects.PaintObjects;
import edu.cs160.Game.GameObjects.PlantObjects.Plant;
import edu.cs160.Game.GameObjects.PlantObjects.PlantTracker;
import edu.cs160.Game.GameObjects.PlantObjects.Updater_Grower;
import edu.cs160.Game.GameObjects.Sprites.Sprite;

import edu.cs160.*;
import android.content.Context;
import android.graphics.*;
import android.graphics.Bitmap.Config;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.*;

public class Game_Grower_Screen extends Screen{		
	long timeStamp;
	Plant selection;	
	
	public Game_Grower_Screen(Context context) {
		super(context);
		DataInitializer.init(context);		
			
		// TODO Auto-generated constructor stub		
	}
	public Game_Grower_Screen(Context context,AttributeSet attrs) {		
		super(context);		
		DataInitializer.init(context);	
		DEBUGGER.debug();
		
		// TODO Auto-generated constructor stub		
	}
	
	@Override
	public void onDraw(Canvas c){
		//Bitmap background = Bitmap.createBitmap(GameObjects.Data.get("Background").image);//GameObjects.Data.get("Background").image.copy(Config.ARGB_8888, true);
		//Canvas c = new Canvas(background);		
		//c.drawRect(0, 0, getWidth(), getHeight(), PaintObjects.clearPaint);
		c.drawColor(0xff964319);
		//GameObjects.draw(getResources(), c, "Pot", getWidth()/4, getHeight()/2,0.5f,0.5f);	
		for(int id: PlantTracker.plantTracker.keySet()){
			PlantTracker.get(id).draw( getResources(),c);
		}
//		canvas.drawBitmap(background, 0, 0, null);
//		background.recycle();
	}
	public boolean onTouchEvent(MotionEvent e){
		
		synchronized(dThread.getSurfaceHolder()){			
			float x = e.getX(),y = e.getY();
			if(e.getAction()== MotionEvent.ACTION_DOWN){
				selection = PlantTracker.getClosestPlant((int)x, (int)y);
				if(selection!=null)
				DataInitializer.mainActivity.runOnUiThread(new Updater_Grower(selection));
			}else if(e.getAction() == MotionEvent.ACTION_MOVE){
				if(selection!=null)
					if(selection.grounded){
						selection.p.move((int)x,(int) y);
					}else{
						selection.sprite.setPosCenter((int)x,(int) y);
					}
			}else if (e.getAction() == MotionEvent.ACTION_UP){
				selection = null;
			}
		}
		return true;
	}
	
	public void update(long gameTime){
		for (int id: PlantTracker.plantTracker.keySet()){
			PlantTracker.get(id).sprite.Update(gameTime);
		}
	}
	

}
