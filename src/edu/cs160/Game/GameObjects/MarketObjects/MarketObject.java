package edu.cs160.Game.GameObjects.MarketObjects;

public class MarketObject {
	public String name, resName;
	public int flowerId;	
	public int cost;
	
	public MarketObject(String name, String resName, int flowerId, int cost){
		this.name = name;
		this.resName = resName;
		this.flowerId = flowerId;
		this.cost = cost;
	}
}

