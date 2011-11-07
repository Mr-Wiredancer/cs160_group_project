package edu.cs160;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu; import android.view.MenuInflater; import android.view.MenuItem;
public class mainActivity extends Activity {
    /** Called when the activity is first created. */
	DatabaseHelper db;
	DatabaseDataHelper dbh;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        db = new DatabaseHelper(this);
//        new AlertDialog.Builder(this).setMessage("test dialog").show();
        dbh = new DatabaseDataHelper(db);
        
        //this should be done when the database is created. It is now here for testing
        populateDatabase();
    }
    
    public void populateDatabase(){
    	dbh.addNewRepeat(true, true, false, false, false, false, false);
    	dbh.addNewTag("tag1");
    	dbh.addNewReminder(1, 1, 1);
    	dbh.addNewResource("test path");
    	dbh.addNewTagTask(1, 1);
    	dbh.addNewTask("task1", "This is task 1",new Date(), new Date(), 1, 1, 1);
    	dbh.addNewTask("task2", "this is task 2", new Date(), new Date(),1,1,1);
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu); 
    	MenuInflater inflater = getMenuInflater(); 
    	inflater.inflate(R.menu.main_menu, menu); 
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    	switch(item.getItemId()){
    	case R.id.go_task_view:
//            new AlertDialog.Builder(this).setMessage("go task pressed").show();
    		mainActivity.this.startActivity(new Intent(mainActivity.this, viewTasksActivity.class));
    		return true;
    	case R.id.quit:
            new AlertDialog.Builder(this).setMessage("quit pressed").show();
    		return true;
    	}
    	
    	return super.onOptionsItemSelected(item);
    }
    
    
}
