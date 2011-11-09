package edu.cs160.Game.GameObjects;

import edu.cs160.Game.Screens.Screen;
import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class DrawThread extends Thread{
private SurfaceHolder sh;
private Screen screen;
private boolean running= false;
public int typeID = 0;

	public DrawThread(SurfaceHolder sh,Screen sc){
		this.sh = sh;
		this.screen = sc;
	}
	
	public void setRunning(boolean running){
		this.running = running;
		
	}
	
	public SurfaceHolder getSurfaceHolder(){return sh;}
	public Screen getScreen(){return screen;}
	
	@Override
	public void run(){
		Canvas c = null;
		while(running){
			c= null;
			try{
				c = sh.lockCanvas();
				synchronized(sh){
					screen.update(System.currentTimeMillis());
					screen.onDraw(c);
				}
			}finally{
				if(c!=null){
					sh.unlockCanvasAndPost(c);
				}
			}
		}
	}
	
	

}
