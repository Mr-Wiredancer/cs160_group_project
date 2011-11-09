package edu.cs160.Game.GameObjects.Sprites;

import edu.cs160.Game.GameObjects.GameObjects;
import edu.cs160.Game.GameObjects.PaintObjects;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

public class Sprite{
private String name;
public int x,y;
public int imgWidth,imgHeight;
public int currentFrame;
public int currentSequence;
private int frameMax, sequenceMax;
private int fps;
private long timeStamp;
private boolean reversable, reverse = false;
public boolean highlight = false;
public Paint hiLiter = PaintObjects.hiLite; 
	public Sprite(String name){
		
	}
	
	public Sprite(String name, int frameMax, int sequenceMax, int fps, boolean reversable){	
		Bitmap bm = GameObjects.Data.get(name).image;
		this.name  = name;		
		this.frameMax = frameMax;
		this.fps = 1000/fps;
		this.reversable = reversable;
		this.currentFrame=0;
		this.sequenceMax=sequenceMax;
		imgWidth = bm.getWidth()/frameMax;
		imgHeight = bm.getHeight()/sequenceMax;
		x=0;y=0;
		timeStamp = System.currentTimeMillis();		
	}
	
	public void setPos(int x, int y){
		this.x=x;
		this.y=y;
	}
	
	public void setPosCenter(int x, int y){
		this.x = x-imgWidth/2;
		this.y = y-imgHeight/2;
	}
	
	public void Update(long gameTime){
		if(frameMax <=1){
			return;
		}
		if(gameTime>timeStamp+fps){
			timeStamp = gameTime;
			if(reverse){
				currentFrame--;
				if(currentFrame<1){
					reverse=false;
				}
			}else{
				currentFrame++;
				if(currentFrame>=frameMax){
					if(reversable){
						reverse=true;
						currentFrame=frameMax-1;
					}else{
						currentFrame=0;
					}
				}
			}
			//srcRect.set(imgWidth*currentFrame*0,0,imgWidth*(currentFrame*0+1),imgHeight);					
		}else{
			return;
		}
	}
	
	public void draw(Canvas c, Resources res){
		if(highlight){
			c.drawRect(x, y, x+imgWidth, y+imgHeight, PaintObjects.hiLite);
		}
		GameObjects.drawSprite(res,c,name,this);	
		
	}
}
