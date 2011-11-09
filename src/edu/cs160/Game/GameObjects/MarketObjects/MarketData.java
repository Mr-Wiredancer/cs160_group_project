package edu.cs160.Game.GameObjects.MarketObjects;

import java.util.LinkedList;

import edu.cs160.Game.GameObjects.DataInitializer;

public class MarketData {

	public static LinkedList<MarketObject> populate(){
		LinkedList<MarketObject> ll = new LinkedList<MarketObject>();
		
		ll.add(new MarketObject("Rose",Integer.toString(DataInitializer.flowerID.ROSE),DataInitializer.flowerID.ROSE,25));
		ll.add(new MarketObject("Blue Rose",Integer.toString(DataInitializer.flowerID.BLUE_ROSE),DataInitializer.flowerID.BLUE_ROSE,50));
		ll.add(new MarketObject("Camellia",Integer.toString(DataInitializer.flowerID.CAMELLIA),DataInitializer.flowerID.CAMELLIA,40));
		ll.add(new MarketObject("Forget Me Not",Integer.toString(DataInitializer.flowerID.FORGETMENOT),DataInitializer.flowerID.FORGETMENOT,75));
		
		
		return ll;
	}
}
