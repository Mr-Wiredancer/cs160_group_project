package edu.cs160;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.*;
import android.database.*;
import android.hardware.*;

public class DatabaseHelper extends SQLiteOpenHelper{
	private static final String DATABASE_NAME="db_test_1";
//	static final String TITLE="title";
//	static final String VALUE="value";
	static final String NAME="name";
	static final String TAGS="tags";
	
	public DatabaseHelper(Context context){
		super(context,DATABASE_NAME, null, 1);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db){
		
		/*
		 * Main table: tasks and plants
		 * Helper table: reminders, repeats, resources, tags, tag_tasks, stages, locations 
		 */
		//task has title, description, date_time_created, date_time_finished, repeat_id, reminder_id, resource_id
		db.execSQL("CREATE TABLE tasks (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT, date_time_created TEXT, date_time_started TEXT, date_time_finished TEXT, repeat_id INTEGER, reminder_id INTEGER, resource_id INTEGER, completed INTEGER);");
		//a plant has name, plant_type_id, location_id, stage_id, resource_id
		db.execSQL("CREATE TABLE plants (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT, plant_type_id INTEGER, location_id INTEGER, current_xp INTEGER, max_xp INTEGER, level INTEGER, resource_id INTEGER);");
		
		//a repeat has a string of length 7, such as 1100000, 0101010.......
		db.execSQL("CREATE TABLE repeats (id INTEGER PRIMARY KEY AUTOINCREMENT,  repeat_days TEXT);");
		//a reminder has numbers of days, hours, and minutes
		db.execSQL("CREATE TABLE reminders (id INTEGER PRIMARY KEY AUTOINCREMENT, days INTEGER, hours INTEGER, minutes INTEGER);");
		//a resource has the path
		db.execSQL("CREATE TABLE resources (id INTEGER PRIMARY KEY AUTOINCREMENT, path TEXT);");
		//a tag has a name
		db.execSQL("CREATE TABLE tags (id INTEGER PRIMARY KEY AUTOINCREMENT, name Text );");
		//this saves mappings of tasks and tags
		db.execSQL("CREATE TABLE tag_task (id INTEGER PRIMARY KEY AUTOINCREMENT, tag_id INTEGER, task_id INTEGER);");
		//a plan type has type_name
		db.execSQL("CREATE TABLE plant_types (id INTEGER PRIMARY KEY AUTOINCREMENT, type_name TEXT);");
		//a location has x,y
		db.execSQL("CREATE TABLE locaions (id INTEGER PRIMARY KEY AUTOINCREMENT, x INTEGER, y INTEGER);");
		//a stage has current, max
		db.execSQL("CREATE TABLE stages (id INTEGER PRIMARY KEY AUTOINCREMENT, current INTEGER, max INTEGER);");	
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		android.util.Log.w(TAGS, "Upgrading databse, dont do anything now");
		db.execSQL("DROP TABLE IF EXISTS tasks"); 
		db.execSQL("DROP TABLE IF EXISTS tags");  
		db.execSQL("DROP TABLE IF EXISTS locations");  
		db.execSQL("DROP TABLE IF EXISTS stages");  
		db.execSQL("DROP TABLE IF EXISTS plant_types");  
		db.execSQL("DROP TABLE IF EXISTS tag_task");  
		db.execSQL("DROP TABLE IF EXISTS resources");
		db.execSQL("DROP TABLE IF EXISTS reminders");  
		db.execSQL("DROP TABLE IF EXISTS repeats");
		db.execSQL("DROP TABLE IF EXISTS plants");  
        onCreate(db);  
	}
	
	
}
