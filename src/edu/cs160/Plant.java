package edu.cs160;

public class Plant {
	int id;
	String name;
	int plant_type_id;
	int location_id;
	int stage_id;
	int resource_id;
	
	public Plant(int id, String name, int plant_type_id, int location_id, int stage_id, int resource_id){
		this.id = id;
		this.name = name;
		this.plant_type_id = plant_type_id;
		this.location_id = location_id;
		this.stage_id = stage_id;
		this.resource_id = resource_id;
	}
	
}
