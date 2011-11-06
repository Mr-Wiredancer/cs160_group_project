package edu.cs160.Game.Screens;

import edu.cs160.Game.GameObjects.DataInitializer;
import edu.cs160.Game.GameObjects.PlantObjects.Plant;
import edu.cs160.Game.GameObjects.PlantObjects.PlantTracker;
import edu.cs160.Game.GameObjects.PlantObjects.Updater_Grower;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;



public class Game_Garden_Screen extends Screen {
	Plant selection;
	public Game_Garden_Screen(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public Game_Garden_Screen(Context context,AttributeSet a) {
		super(context);
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
			}else if(e.getAction() == MotionEvent.ACTION_MOVE){
				if(selection!=null)
					selection.sprite.setPosCenter((int)x,(int) y);
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
