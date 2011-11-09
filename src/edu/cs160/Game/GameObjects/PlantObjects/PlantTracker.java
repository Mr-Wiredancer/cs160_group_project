package edu.cs160.Game.GameObjects.PlantObjects;

import java.util.HashMap;
import java.util.LinkedList;

import edu.cs160.DatabaseDataHelper;
import edu.cs160.PlantData;
import edu.cs160.Game.GameObjects.DataInitializer;
import edu.cs160.Game.GameObjects.Sprites.Sprite;

public class PlantTracker {
public static HashMap<Integer,Plant> plantTracker = new HashMap<Integer,Plant>();
public static HashMap<Integer,Plant> Inventory = new HashMap<Integer,Plant>();
public static Object trackerLock = new Object();
public static Object inventoryLock = new Object();
public static int key = 0;
public static int location = DataInitializer.gardenID.MAIN;
public static DatabaseDataHelper dbh;
	public static void populate(int key){
		/*
		 * Save Plants
		 */
		
		/*
		 * Load Plants
		 */
		
		LinkedList<PlantData> pd =dbh.getAllPlants();		
		if(key == DataInitializer.gardenID.MAIN){
			
		}
	}
	
	public static void addPlant(Plant p){
		plantTracker.put(key, p);
		p.id = key;
		key= findNextUnoccupied(key);
	}
	
	public static void setPlant(int id,Plant p){
		plantTracker.put(key, p);
		p.id = id;
	}
	
	public static int findNextUnoccupied(int i){
		while(plantTracker.containsKey(i)){
			i++;
		}		
		return i;
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
		if(plantTracker.size()<1){
			return null;
		}
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
	
	public static Plant getClosestInventoryPlant(int x, int y){
		int dx,dy,distance=1000,tempDistance;
		Sprite s = null;
		Plant selected = null;
		if(Inventory.size()<1){
			return null;
		}
		for(int key:Inventory.keySet()){			
			s=Inventory.get(key).sprite;
			dx = x-s.x;
			dy = y-s.y;
			
			if(dx<0 || dx> s.imgWidth){
				continue;
			}else if(dy<0||dy>s.imgHeight){
				continue;
			}else{
				tempDistance = Math.abs(dx-s.imgWidth/2)+Math.abs(dy-s.imgHeight/2);
				if(tempDistance<distance){
					selected = Inventory.get(key);
				}
			}
			
		}
		return selected;
	}
}
