package edu.cs160.Game.GameObjects;

import android.graphics.Bitmap;

public class ObjectData {
	public Bitmap image;
	public int resid;
	public Bitmap imageMask;
	public int mresid;
	public String name;
	public boolean masked = true;
	
	public ObjectData(String name, Bitmap im, int imid, Bitmap imm, int immid){
		this.image = im;
		this.imageMask = imm;
		this.name = name;
		this.resid=imid;
		this.mresid = immid;
	}
	
	public ObjectData(String name, Bitmap im, int id){
		this.name = name;
		this.image = im;
		masked = false;
		this.resid=id;
		
	}
}
