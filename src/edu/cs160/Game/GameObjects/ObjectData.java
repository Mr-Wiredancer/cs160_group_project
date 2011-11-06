package edu.cs160.Game.GameObjects;

import android.graphics.Bitmap;

public class ObjectData {
	public Bitmap image;
	public Bitmap imageMask;
	public String name;
	public boolean masked = true;
	
	public ObjectData(String name, Bitmap im, Bitmap imm){
		this.image = im;
		this.imageMask = imm;
		this.name = name;
	}
	
	public ObjectData(String name, Bitmap im){
		this.name = name;
		this.image = im;
		masked = false;
		
	}
}
