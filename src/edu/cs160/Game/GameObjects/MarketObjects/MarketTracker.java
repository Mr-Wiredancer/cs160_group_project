package edu.cs160.Game.GameObjects.MarketObjects;

import java.util.Vector;

public class MarketTracker {
	public Vector<MarketObject> goods = new Vector<MarketObject>();
	public final int itemsPerRow = 5;
	public final int rowAmt = 5;	
	public MarketTracker(){		
		for(Object o:MarketData.populate().toArray()){
			goods.add((MarketObject)o);
		}
	}
}
