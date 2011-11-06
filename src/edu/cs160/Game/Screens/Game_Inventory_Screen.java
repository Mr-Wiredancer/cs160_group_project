package edu.cs160.Game.Screens;

import edu.cs160.Game.GameObjects.DataInitializer;
import edu.cs160.Game.GameObjects.PlantObjects.Plant;
import edu.cs160.Game.GameObjects.PlantObjects.PlantTracker;
import edu.cs160.Game.GameObjects.PlantObjects.Updater_Grower;
import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;

public class Game_Inventory_Screen extends Screen{
	boolean visible = false;
	Plant selection;
	public Game_Inventory_Screen(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	public Game_Inventory_Screen(Context context,AttributeSet a) {
		super(context);
		// TODO Auto-generated constructor stub
	}
	
	public void onDraw(Canvas c){
		if (!visible)
			return;
		c.drawColor(0xff000000);
		Plant p;
		for(int id: PlantTracker.Inventory.keySet()){
			p = PlantTracker.get(id);
			p.sprite.x=Math.min(Math.max(p.sprite.x, 0), getWidth()-p.sprite.imgWidth/2);
			p.sprite.y=Math.min(Math.max(p.sprite.y, 0), getHeight()-p.sprite.imgHeight/2);
			p.draw( getResources(),c);
		}
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

}
