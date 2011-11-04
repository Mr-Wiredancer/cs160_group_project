package edu.cs160;

import java.util.LinkedList;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

public class DatabaseDataHelper {
	DatabaseHelper db;
	SQLiteDatabase dbr;
	SQLiteDatabase dbw;

	public DatabaseDataHelper(DatabaseHelper database){
		db=database;
		dbr=db.getReadableDatabase();
		dbw=db.getWritableDatabase();
	}
	
	//get all the tasks in the database;
	public LinkedList<Task> getTasks(){
		Cursor c=dbr.rawQuery("SELECT * FROM tasks;", null);
		LinkedList<Task> tasks = new LinkedList<Task>();
		while(c.moveToNext()){
			tasks.add(new Task(c.getInt(0),c.getString(1),c.getString(2),c.getString(3),c.getString(4),c.getString(5)));
		}
		c.close();
		return tasks;
	}
	
	//get all the tags for a certain task
	public LinkedList<Tag> getTagsForTask(int task_id){
		LinkedList<Tag> tags = new LinkedList<Tag>();
		Cursor c=dbr.rawQuery("SELECT * FROM tag_task WHERE task_id="+task_id+";", null);
		while (c.moveToNext()){
			int tag_id=c.getInt(1);
			Cursor tagcursor = dbr.rawQuery("SELECT * FROM tags where id="+tag_id+";", null);
			String tag_name = tagcursor.getString(1);
			tags.add(new Tag(tag_id,tag_name));
			tagcursor.close();
		}
		c.close();
		return tags;
	}
	
	
	
}
