package edu.cs160;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseDataHelper {
	DatabaseHelper db;
	SQLiteDatabase dbr;
	SQLiteDatabase dbw;

	/**
	 * Get a cursor of tasks for list adapter
	 * @return
	 */
	public Cursor getTasksCursor(){
		return dbr.query("tasks", new String[] {"title", "description", "date_time_finished", "id as _id"}, null, null, null, null, null);
	}
	
	
	public DatabaseDataHelper(DatabaseHelper database){
		db=database;
		dbr=db.getReadableDatabase();
		dbw=db.getWritableDatabase();
	}
	
	/**
	 * Add a new entry to repeats table
	 * @param sun
	 * @param mon
	 * @param tue
	 * @param wed
	 * @param thr
	 * @param fri
	 * @param sat
	 */
	public void addNewRepeat(boolean sun, boolean mon, boolean tue, boolean wed, boolean thr, boolean fri, boolean sat){
		String result="";
		result=result+(sun?"1":"0")+(mon?"1":"0")+(tue?"1":"0")+(wed?"1":"0")+(thr?"1":"0")+(fri?"1":"0")+(sat?"1":"0");
		ContentValues cv = new ContentValues();
		cv.put("repeat_days", result);
		dbw.insert("repeats", null, cv);
	}
	
	/**
	 * add new entry to reminders table
	 * @param days
	 * @param hours
	 * @param minutes
	 */
	public void addNewReminder(int days, int hours, int minutes){
		ContentValues cv = new ContentValues();
		cv.put("days", days);
		cv.put("hours", hours);
		cv.put("minutes", minutes);
		dbw.insert("reminders", null,cv);
	}
	
	/**
	 * Add a new entry to plant_types table
	 * @param type_name
	 */
	public void addNewPlantType(String type_name){
		ContentValues cv = new ContentValues();
		cv.put("type_name", type_name);
		dbw.insert("plant_types", null,cv);
	}
	
	/**
	 * Add a new entry to locations table
	 * @param x
	 * @param y
	 */
	public void addNewLocation(int x, int y){
		ContentValues cv = new ContentValues();
		cv.put("x", x);
		cv.put("y", y);
		dbw.insert("locations", null,cv);
	}
	
	/**
	 * Add a new entry to tag_task table 
	 * @param tag_id
	 * @param task_id
	 */
	public void addNewTagTask(int tag_id, int task_id){
		ContentValues cv = new ContentValues();
		cv.put("tag_id", tag_id);
		cv.put("task_id", task_id);
		dbw.insert("tag_task", null,cv);
	}
	
	/**
	 * Add a new entry to stages table
	 * @param current
	 * @param max
	 */
	public void addNewStage(int current, int max){
		ContentValues cv = new ContentValues();
		cv.put("current", current);
		cv.put("max", max);
		dbw.insert("stages", null,cv);
	}
	
	/**
	 * Add a new entry to tasks table
	 * @param title
	 * @param description
	 * @param repeat_id
	 * @param reminder_id
	 * @param tag_task_id
	 * @param resource_id
	 */
	public void addNewTask(String title, String description, int repeat_id, int reminder_id, int resource_id){
		ContentValues cv=new ContentValues();
		cv.put("title", title);
		cv.put("description", description);
		cv.put("repeat_id", repeat_id);
		cv.put("reminder_id" , reminder_id);
		cv.put("resource_id", resource_id);
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss",Locale.US);
		cv.put("date_time_created", formatter.format(new Date()));
		System.out.println(formatter.format(new Date()));
		cv.putNull("date_time_finished");
		dbw.insert("tasks", null, cv);
	}
	
	/**
	 * add new entry to resrouces table
	 * @param path
	 */
	public void addNewResource(String path){
		ContentValues cv = new ContentValues();
		cv.put("path", path);
		dbw.insert("resources", null, cv);
	}
	
	/**
	 * Add new entry to tags table
	 * @param name
	 */
	public void addNewTag(String name){
		ContentValues cv = new ContentValues();
		cv.put("name", name);
		dbw.insert("tags", null,cv);
	}
	
	/**
	 * Add new entry to tasks table
	 * @param tag_id
	 * @param task_id
	 */
	public void addTagForTask(int tag_id, int task_id){
		ContentValues cv = new ContentValues();
		cv.put("task_id", task_id);
		cv.put("tag_id", tag_id);
		dbw.insert("tag_task", null, cv);
	}
	
	/**
	 * Add new entry to plants table
	 * @param name
	 * @param plant_type_id
	 * @param location_id
	 * @param stage_id
	 */
	public void addNewPlant(String name, int plant_type_id, int location_id, int max_xp){
		addNewPlant(name, plant_type_id, location_id, 0, max_xp, 1 , 0);
	}
	
	/**
	 * Add new entry to plants table
	 * @param name
	 * @param plant_type_id
	 * @param location_id
	 * @param stage_id
	 * @param resource_id
	 */
	public void addNewPlant(String name, int plant_type_id, int location_id, int current_xp, int max_xp, int level, int resource_id){
		ContentValues cv=new ContentValues();
		cv.put("name", name);
		cv.put("plant_type_id", plant_type_id);
		cv.put("location_id", location_id);
		cv.put("current_xp", current_xp);
		cv.put("max_hp", max_xp);
		cv.put("level", level);
		if (resource_id==0){
			cv.putNull("resource_id");
		}else{
			cv.put("resource_id", resource_id);
		}
		dbw.insert("plants", null,cv);
	}
	
	/**
	 * Get all the tasks
	 * @return a list of tasks
	 */
	public LinkedList<Task> getTasks(){
		Cursor c=dbr.rawQuery("SELECT * FROM tasks;", null);
		LinkedList<Task> tasks = new LinkedList<Task>();
		while(c.moveToNext()){
			int task_id = c.getInt(0);	
			tasks.add(getTask(task_id));
		}
		c.close();
		return tasks;
	}
	
	/**
	 * get Tag object from tag_id
	 * @param tag_id
	 * @return a Tag object
	 */
	public Tag getTag(int tag_id){
		Cursor tagcursor = dbr.rawQuery("SELECT * FROM tags where id="+tag_id+";", null);
		tagcursor.moveToNext();
		String tag_name = tagcursor.getString(1);
		return new Tag(tag_id,tag_name);
	}
	
	/**
	 * get all the tags for a certain task
	 * @param task_id
	 * @return a list of tags
	 */
	public LinkedList<Tag> getTagsForTask(int task_id){
		LinkedList<Tag> tags = new LinkedList<Tag>();
		Cursor c=dbr.rawQuery("SELECT * FROM tag_task WHERE task_id="+task_id+";", null);
		while (c.moveToNext()){
			int tag_id=c.getInt(1);
			tags.add(getTag(tag_id));
		}
		c.close();
		return tags;
	}
	
	/**
	 * get Task object from a task_id
	 * @param task_id
	 * @return a Task object
	 */
	public Task getTask(int task_id){
		Cursor c = dbr.rawQuery("SELECT * FROM tasks WHERE id="+task_id+";", null);
		c.moveToNext();
		String title = c.getString(1);
		String description = c.getString(2);
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss",Locale.US);
		
		Date date_time_created = null;
		Date date_time_started = null;
		Date date_time_finished = null;
		try{
			date_time_created = formatter.parse(c.getString(3));
		}catch(ParseException e){
			System.out.println("date_created parse error");
			date_time_created = null;
		}
		try{
			date_time_started = formatter.parse(c.getString(4));
		}catch(ParseException e){
			System.out.println("date_created parse error");
			date_time_started = null;
		}
		try{
			if (c.getString(4)!=null){
				date_time_finished = formatter.parse(c.getString(5));
			}
		}catch(ParseException e){
			System.out.println("date_finished parse error");
			date_time_finished = null;
		}
			
		int repeat_id = c.getInt(6);
		int reminder_id = c.getInt(7);
		int resource_id = c.getInt(8);	
		int completed = c.getInt(9);
		return new Task(task_id, title, description, date_time_created, date_time_started, date_time_finished, getRepeat(repeat_id), getReminder(reminder_id), getTagsForTask(task_id), resource_id, completed);
	}
	
	/**
	 * get Repeat object from a Repeat id
	 * @param repeat_id
	 * @return
	 */
	public Repeat getRepeat(int repeat_id){
		Cursor c = dbr.rawQuery("SELECT * FROM repeats WHERE id="+repeat_id+";", null);
		c.moveToNext();
		return new Repeat(repeat_id, c.getString(1));
	}
	
	/**
	 * get Reminder object from a reminder_id
	 * @param reminder_id
	 * @return a reminder object
	 */
	public Reminder getReminder(int reminder_id){
		Cursor c = dbr.rawQuery("SELECT * FROM reminders WHERE id="+reminder_id+";", null);
		c.moveToNext();
		return new Reminder(reminder_id, c.getInt(1), c.getInt(2), c.getInt(3));
	}
	
	
	/**
	 * Get all the plants
	 * @return a list of plants
	 */
	public LinkedList<Plant> getAllPlants(){
		Cursor c=dbr.rawQuery("SELECT * FROM plants;", null);
		LinkedList<Plant> plants = new LinkedList<Plant>();
		while(c.moveToNext()){
			plants.add(new Plant(c.getInt(0),c.getString(1), c.getInt(2), c.getInt(3), c.getInt(4), c.getInt(5), c.getInt(6), c.getInt(7)));
		}
		c.close();
		return plants;
	}
	
	
	
	
}
