package edu.cs160.Game.GameObjects.PlantObjects;

import java.util.HashMap;

import edu.cs160.Game.GameObjects.Sprites.Sprite;


public class PlantTracker {
public static HashMap<Integer,Plant> plantTracker = new HashMap<Integer,Plant>();
public static HashMap<Integer,Plant> Inventory = new HashMap<Integer,Plant>();
public static int key = 0;
	public static void populate(String key){
		
	}
	
	public static void addPlant(Plant p){
		plantTracker.put(key, p);
		p.id = key;
		key+=1;
	}
	
	public static void setPlant(int id,Plant p){
		plantTracker.put(key, p);
		p.id = id;
	}
	
	public static Plant get(int id){	
		try{
			return plantTracker.get(id);
		}catch(Exception e){
			return null;
		}
		
	}
	
	public static void flushAll(){
		plantTracker.clear();
		key=0;
	}
	
	public static Plant getClosestPlant(int x, int y){
		int dx,dy,distance=1000,tempDistance;
		Sprite s = null;
		Plant selected = null;
		for(int key:plantTracker.keySet()){			
			s=plantTracker.get(key).sprite;
			dx = x-s.x;
			dy = y-s.y;
			
			if(dx<0 || dx> s.imgWidth){
				continue;
			}else if(dy<0||dy>s.imgHeight){
				continue;
			}else{
				tempDistance = Math.abs(dx-s.imgWidth/2)+Math.abs(dy-s.imgHeight/2);
				if(tempDistance<distance){
					selected = plantTracker.get(key);
				}
			}
			
		}
		return selected;
	}
}
