package edu.cs160.Game.Screens;

import edu.cs160.Game.GameObjects.DrawThread;
import edu.cs160.Game.GameObjects.PaintObjects;
import android.content.Context;
import android.graphics.*;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class Screen extends SurfaceView implements SurfaceHolder.Callback{
	public DrawThread dThread;
	public Screen(Context context) {
		super(context);
		setFocusable(true);
		getHolder().addCallback(this);
		dThread = new DrawThread(getHolder(),this);
		
	}
	
	public void startNewThread(){
		boolean retry = true;
		while(retry){
			try{
				dThread.join();
				retry=false;
			}catch(Exception e){}
		}
		dThread = new DrawThread(getHolder(),this);
	}
	public void draw(Canvas c){
		
	}

	public void onDraw(Canvas c){		
	}
	
	public void update(long gameTime){
		//Will be overloaded
	}

	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		dThread.setRunning(true);
		dThread.start();

		//Log.d("SurfaceView", "Thread created, Surface Created");
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		//Log.d("SurfaceView", "Surface Destroyed Invoked");
		if(dThread.isAlive()){
			dThread.setRunning(false);
			boolean retry = true;
			while(retry){
				try{
					dThread.join();
					retry=false;
				}catch(Exception e){}
			}
		}
		
	}	
}