package edu.cs160;

import java.util.LinkedList;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu; import android.view.MenuInflater; import android.view.MenuItem;
public class mainActivity extends Activity {
    /** Called when the activity is first created. */
	DatabaseHelper db;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        db = new DatabaseHelper(this);
        
        DatabaseDataHelper dbh = new DatabaseDataHelper(db);
        
        LinkedList<Task> tasks = dbh.getTasks();
        for (Task task:tasks){
        	System.out.println("title is "+task.title+"\ndescription:"+task.description);
        }
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu); 
    	MenuInflater inflater = getMenuInflater(); 
    	inflater.inflate(R.menu.main_menu, menu); 
    	return true;
    }
}
