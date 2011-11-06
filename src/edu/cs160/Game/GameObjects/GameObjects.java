package edu.cs160.Game.GameObjects;

import java.util.HashMap;

import edu.cs160.Game.GameObjects.Sprites.Sprite;

import edu.cs160.*;
import android.content.res.Resources;
import android.graphics.*;
/************************************************
 * GameObjects
 * 
 *A class that contains the data for all images to be used in the game.
 *IT uses a HashMap<String,ObjectData> to keep track of images and its masks
 *Through the draw method, it will draw the object by applying mask and then overlaying the image above it.
 */
public class GameObjects {
	
	public static HashMap<String,ObjectData> Data = new HashMap<String,ObjectData>();
	
	/*
	 * Add Data - Adds a new game "Object" and stores its image and the corresponding mask
	 */
	public static void addData(String name, Bitmap image, Bitmap imageMask){
		Data.put(name, new ObjectData(name,image,imageMask));
	}
	public static void addData(String name, Bitmap image){
		Data.put(name, new ObjectData(name,image));
	}
	
	public static void draw(Resources res,Canvas c, String name, int x, int y, float scaleX, float scaleY){
		if(!Data.containsKey(name)){	//If the hashmap doesn't contain the key, then there is nothing to draw
			return;
		}
			
		Rect dst = new Rect(),src = new Rect();	//Create new rect for image overlays
		Bitmap bm;								//The basic bitmap handle that will be used
		if(Data.get(name).masked){
			bm = Data.get(name).imageMask;													//First get the image from the resource files
			dst.set(x,y ,x+(int)(bm.getWidth()*scaleX), y+(int)(bm.getHeight()*scaleY));	//Determine where the picture will be placed on the destination file	
			src.set(0,0,bm.getWidth(),bm.getHeight());										//Get the entirety of the image
			
			c.drawBitmap(bm, src, dst, PaintObjects.maskPaint);								//Draw the mask using the maskPaint paint Object
			
			bm = Data.get(name).image;														//Next get the actual image with black background
			c.drawBitmap(bm, src, dst, PaintObjects.addPaint);								//Overlay the image using the addPaint paint Object
		}else{
			bm = Data.get(name).image;
			dst.set(x,y ,x+(int)(bm.getWidth()*scaleX), y+(int)(bm.getHeight()*scaleY));	//Determine where the picture will be placed on the destination file	
			src.set(0,0,bm.getWidth(),bm.getHeight());										//Get the entirety of the image
			c.drawBitmap(bm, src, dst, null);
		}
	}
	
	public static void draw(Resources res, Canvas c, String name){
		draw(res,c,name,0,0,1,1);
	}
	
	public static void draw(Resources res, Canvas c, String name,int x, int y){
		draw(res,c,name,x,y,1,1);
	}
	
	public static void draw(Resources res, Canvas c, String name, float scale){
		draw(res,c,name,0,0,scale,scale);
	}
	
	public static void draw(Resources res, Canvas c, String name, int x, int y,float scale){
		draw(res,c,name,x,y,scale,scale);
	}
	
	
	public static void drawSprite(Resources res,Canvas c, String name, Sprite data,float scaleX,float scaleY){
		if(!Data.containsKey(name)){	//If the hashmap doesn't contain the key, then there is nothing to draw
			return;
		}
		Rect dst = new Rect(),src = new Rect();	//Create new rect for image overlays
		Bitmap bm;								//The basic bitmap handle that will be used
		if(Data.get(name).masked){						
			bm = Data.get(name).imageMask;				//First get the image from the resource files
			dst.set(data.x,data.y ,data.x+(int)(data.imgWidth*scaleX), data.y+(int)(data.imgHeight*scaleY));	//Determine where the picture will be placed on the destination file
			src.set(data.imgWidth*data.currentFrame,data.imgHeight*data.currentSequence,data.imgWidth*(1+data.currentFrame),data.imgHeight*(data.currentSequence+1));
			c.drawBitmap(bm, src, dst, PaintObjects.maskPaint);								//Draw the mask using the maskPaint paint Object
			
			bm = Data.get(name).image;					//Next get the actual image with black background
			c.drawBitmap(bm, src, dst, PaintObjects.addPaint);								//Overlay the image using the addPaint paint Object
		}else{
			bm = Data.get(name).image;
			dst.set(data.x,data.y ,data.x+(int)(data.imgWidth*scaleX), data.y+(int)(data.imgHeight*scaleY));	//Determine where the picture will be placed on the destination file
			src.set(data.imgWidth*data.currentFrame,data.imgHeight*data.currentSequence,data.imgWidth*(1+data.currentFrame),data.imgHeight*(data.currentSequence+1));
			c.drawBitmap(bm, src, dst,null);
		}
	}
	
	public static void drawSprite(Resources res, Canvas c, String name, Sprite data){
		drawSprite(res,c,name,data,1,1);
	}
}
