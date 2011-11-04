package edu.cs160;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteDatabase.CursorFactory;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.*;
import android.database.*;
import android.hardware.*;

public class DatabaseHelper extends SQLiteOpenHelper{
	private static final String DATABASE_NAME="db";
//	static final String TITLE="title";
//	static final String VALUE="value";
	static final String NAME="name";
	static final String TAGS="tags";
	
	public DatabaseHelper(Context context){
		super(context,DATABASE_NAME, null, 1);
	}
	
	@Override
	public void onCreate(SQLiteDatabase db){
		
		//For now the data in the database are hard-coded;
		db.execSQL("CREATE TABLE tags (id INTEGER PRIMARY KEY AUTOINCREMENT, name Text );");
		db.execSQL("CREATE TABLE tag_task (id INTEGER PRIMARY KEY AUTOINCREMENT, tag_id INTEGER, task_id INTEGER);");
		db.execSQL("CREATE TABLE tasks (id INTEGER PRIMARY KEY AUTOINCREMENT, title TEXT, description TEXT, date_created REAL, date_started REAL, date_due REAL);");
		db.execSQL("CREATE TABLE plants (id INTEGER PRIMARY KEY AUTOINCREMENT, name TEXT);");
		
		ContentValues cv = new ContentValues();
		
		cv.put("name", "tag1");
		db.insert("tags", null, cv);
		cv.clear();
		
		cv.put("name", "tag2");
		db.insert("tags", null, cv);
		cv.clear();
		
		cv.put("name", "tag3");
		db.insert("tags", null, cv);
		cv.clear();
		
		cv.put("name", "tag4");
		db.insert("tags", null, cv);
		cv.clear();
		
		cv.put("title", "task1");
		cv.put("description", "This is task 1");
		cv.put("date_created", "09012011");
		cv.put("date_started", "09022011");
		cv.put("date_due", "09072011");
		db.insert("tasks", null, cv);
		cv.clear();
		
		cv.put("tag_id", 2);
		cv.put("task_id", 1);
		db.insert("tag_task", null, cv);
		cv.clear();
		
		cv.put("tag_id", 3);
		cv.put("task_id", 1);
		db.insert("tag_task", null, cv);
		cv.clear();
		
		cv.put("name", "Mary");
		db.insert("plants", null, cv);
		cv.clear();
		
		cv.put("name", "Rose");
		db.insert("plants", null, cv);
		cv.clear();
	}

	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		// TODO Auto-generated method stub
		android.util.Log.w(TAGS, "Upgrading databse, which will destroy all old data");
		db.execSQL("DROP TABLE IF EXISTS constants");
		onCreate(db);
	}
	
	
}
