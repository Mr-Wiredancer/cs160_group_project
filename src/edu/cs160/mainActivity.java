package edu.cs160;

import java.util.LinkedList;

import android.app.Activity;
import android.app.AlertDialog;
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
//        new AlertDialog.Builder(this).setMessage("test dialog").show();
        DatabaseDataHelper dbh = new DatabaseDataHelper(db);
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
            new AlertDialog.Builder(this).setMessage("go task pressed").show();
    		return true;
    	case R.id.quit:
            new AlertDialog.Builder(this).setMessage("quit pressed").show();
    		return true;
    	}
    	
    	return super.onOptionsItemSelected(item);
    }
    
    
}
