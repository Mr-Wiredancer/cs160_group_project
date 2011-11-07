package edu.cs160.Game.Screens;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.concurrent.ScheduledFuture;

import edu.cs160.DEBUGGER;
import edu.cs160.R;
import edu.cs160.Game.GameObjects.DataInitializer;
import edu.cs160.Game.GameObjects.GameObjects;
import edu.cs160.Game.GameObjects.PaintObjects;
import edu.cs160.Game.GameObjects.PlantObjects.Plant;
import edu.cs160.Game.GameObjects.PlantObjects.PlantTracker;
import edu.cs160.Game.GameObjects.PlantObjects.Updater_Grower;
import edu.cs160.Game.GameObjects.Sprites.Sprite;
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
	boolean showInv = true;
	public Game_Grower_Screen(Context context) {
		super(context);		
			
		// TODO Auto-generated constructor stub		
	}
	public Game_Grower_Screen(Context context,AttributeSet attrs) {		
		super(context);		
		
		
		// TODO Auto-generated constructor stub		
	}
	
	@Override
	public void onDraw(Canvas c){
		DataInitializer.invHeight=(int)(0.35*getHeight());
		//Bitmap background = Bitmap.createBitmap(GameObjects.Data.get("Background").image);//GameObjects.Data.get("Background").image.copy(Config.ARGB_8888, true);
		//Canvas c = new Canvas(background);		
		//c.drawRect(0, 0, getWidth(), getHeight(), PaintObjects.clearPaint);
		c.drawColor(0xff964319);
		//GameObjects.draw(getResources(), c, "Pot", getWidth()/4, getHeight()/2,0.5f,0.5f);	
		Plant p;
		SortedSet<Plant> ss = new TreeSet<Plant>();
		for(int id: PlantTracker.plantTracker.keySet()){
			p =PlantTracker.get(id);			
			p.sprite.x=Math.min(Math.max(p.sprite.x, 0), getWidth()-p.sprite.imgWidth/2);
			p.sprite.y=Math.min(Math.max(p.sprite.y, 0), getHeight()-DataInitializer.invHeight-p.sprite.imgHeight/2);			
			ss.add(p);			
		}
		for(Object o: ss.toArray()){
			((Plant)o).draw( getResources(),c);
		}
		
		Paint pnt = new Paint(0xff009000);
		c.drawRect(0, getHeight()-DataInitializer.invHeight, getWidth(), getHeight(), pnt);
		if(showInv){
			ss.clear();
			for(int id: PlantTracker.Inventory.keySet()){
				p =PlantTracker.Inventory.get(id);
				p.sprite.x=Math.min(Math.max(p.sprite.x, 0), getWidth()-p.sprite.imgWidth/2);
				p.sprite.y=Math.min(Math.max(p.sprite.y, getHeight()-DataInitializer.invHeight), getHeight()-p.sprite.imgHeight/2);			
				ss.add(p);
			}
			for(Object o: ss.toArray()){
				((Plant)o).draw( getResources(),c);
			}
		}
//		canvas.drawBitmap(background, 0, 0, null);
//		background.recycle();
	}
	public boolean onTouchEvent(MotionEvent e){
		
		synchronized(dThread.getSurfaceHolder()){			
			float x = e.getX(),y = e.getY();
			if(e.getAction()== MotionEvent.ACTION_DOWN){
				if(y<getHeight()-DataInitializer.invHeight)
					selection = PlantTracker.getClosestPlant((int)x, (int)y);
				else
					selection = PlantTracker.getClosestInventoryPlant((int)x, (int)y);
				System.out.println(selection);
				if(selection!=null)
				DataInitializer.mainActivity.runOnUiThread(new Updater_Grower(selection));
			}else if(e.getAction() == MotionEvent.ACTION_MOVE){
				if(selection!=null){
					selection.sprite.setPosCenter((int)x,(int) y);
					if(!selection.grounded &&PlantTracker.plantTracker.containsKey(selection.id) &&selection.sprite.y > getHeight()-DataInitializer.invHeight+5){						
						PlantTracker.plantTracker.remove(selection.id);
						PlantTracker.Inventory.put(selection.id, selection);
					}
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
