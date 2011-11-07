package edu.cs160;

public class Plant {
	int id;
	String name;
	int plant_type_id;
	int location_id;
//	int stage_id;
	int current_xp;
	int max_xp;
	int level;
	int resource_id;
	
	public Plant(int id, String name, int plant_type_id, int location_id, int current_xp, int max_xp, int level, int resource_id){
		this.id = id;
		this.name = name;
		this.plant_type_id = plant_type_id;
		this.location_id = location_id;
		this.current_xp = current_xp;
		this.max_xp = max_xp;
		this.level = level;
		this.resource_id = resource_id;
	}
	
}
