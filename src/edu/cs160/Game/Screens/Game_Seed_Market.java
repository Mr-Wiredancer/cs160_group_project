package edu.cs160.Game.Screens;

import edu.cs160.Game.GameObjects.GameObjects;
import edu.cs160.Game.GameObjects.PaintObjects;
import edu.cs160.Game.GameObjects.MarketObjects.MarketObject;
import edu.cs160.Game.GameObjects.MarketObjects.MarketTracker;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;

public class Game_Seed_Market extends Screen{
	final int maxDisplay = 7;
	final int delta = 50;
	long timeStamp;
	int money=300;
	int offset = 0;
	int itemSize=150;
	int xTouch= 0, yTouch = 0;
	MarketTracker mt = new MarketTracker();
	public Game_Seed_Market(Context context) {
		super(context);		
		// TODO Auto-generated constructor stub
	}
	
	public Game_Seed_Market(Context context,AttributeSet attrs) {
		super(context);		
		// TODO Auto-generated constructor stub
	}
	
	@Override
	public void onDraw(Canvas c){
		//Log.d("Number of Items", mt.goods.get(0).size()+"");
		c.drawColor(0xff000000);
		
		Paint textPaint = new Paint();
		textPaint.setColor(0xff0000ff);
		textPaint.setTextSize(40);
		textPaint.setStyle(Style.FILL);
		textPaint.setStrikeThruText(false);
		c.drawText("Seed Market", 0, 50, textPaint);
		c.drawText("Current Currency: "+this.money, 0, 100, textPaint);
		
		textPaint.setColor(0xff00ff00);
		textPaint.setTextSize(20);
		int x=0, y=100;
		Rect r = new Rect();
		int displayed = 0;
		MarketObject marketObject;		
		for(int i = this.offset ; i < mt.goods.size() && i < offset+this.maxDisplay;i++){
			if(displayed>=this.maxDisplay){
				break;
			}
			marketObject = mt.goods.get(i);
			r.set(x, y, itemSize+x, itemSize+y);
			GameObjects.draw(getResources(), c, marketObject.resName,x,y,r);
			c.drawText(marketObject.name, x+itemSize+25, y+20, textPaint);
			c.drawText("$"+marketObject.cost, x+itemSize+25,y+50, textPaint);
			c.drawRect(r, PaintObjects.hiLite);
			y+=itemSize+itemSize*.2;
		}						
	}
	

	public boolean onTouchEvent(MotionEvent e){
		if(e.getAction() == e.ACTION_DOWN){
			this.xTouch = (int)e.getX();
			this.yTouch = (int)e.getY();
		}else if (e.getAction() == e.ACTION_MOVE){
			if((int)e.getY() - this.yTouch > this.delta){
				this.xTouch = (int)e.getX();
				this.yTouch = (int)e.getY();
				offset = Math.max(Math.min(offset+1, mt.goods.size()), 0);
			}else if(this.yTouch - (int)e.getY() > this.delta){
				this.xTouch = (int)e.getX();
				this.yTouch = (int)e.getY();
				offset = Math.max(Math.min(offset-1, mt.goods.size()), 0);
			}
		}
		return true;
	}
}
