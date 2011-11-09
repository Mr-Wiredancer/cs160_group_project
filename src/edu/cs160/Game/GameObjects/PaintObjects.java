package edu.cs160.Game.GameObjects;

import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;

public class PaintObjects {
	public static final Paint addPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	public static final Paint maskPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
	public static final Paint clearPaint = new Paint();
	public static final Paint hiLite = new Paint();
	private static boolean initialized = false;
	public static void init(){
		if(initialized)
			return;
		maskPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.MULTIPLY));
		addPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.LIGHTEN));
		clearPaint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.CLEAR));
		hiLite.setColor(0x5000ff00);
		initialized = true;
	}
	
	
}
