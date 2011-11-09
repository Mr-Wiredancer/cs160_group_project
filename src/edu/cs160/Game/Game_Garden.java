package edu.cs160.Game;

import java.util.Date;

import edu.cs160.DEBUGGER;
import edu.cs160.DatabaseDataHelper;
import edu.cs160.DatabaseHelper;
import edu.cs160.R;
import edu.cs160.viewTasksActivity;
import edu.cs160.Game.GameObjects.DataInitializer;
import edu.cs160.Game.Screens.Game_Garden_Screen;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;



public class Game_Garden extends Activity{
	DatabaseHelper db;
	DatabaseDataHelper dbh;
	
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);	
		DataInitializer.mainActivity= this;
		DataInitializer.init(this);			
		DEBUGGER.debug();
		DataInitializer.mainGarden = true;
		setContentView(R.layout.garden_main);
		
		db = new DatabaseHelper(this);
//      new AlertDialog.Builder(this).setMessage("test dialog").show();
		dbh = new DatabaseDataHelper(db);
      
      //this should be done when the database is created. It is now here for testing
		//populateDatabase();

	}

	public void populateDatabase(){
    	dbh.addNewRepeat(true, true, false, false, false, false, false);
    	dbh.addNewTag("tag1");
    	dbh.addNewReminder(1, 1, 1);
    	dbh.addNewResource("test path");
    	dbh.addNewTagTask(1, 1);
    	Date date1 = new Date();
    	date1.setYear(1991);date1.setMonth(6);date1.setDate(6);date1.setHours(6);date1.setMinutes(6);
    	//dbh.addNewTask("task1", "This is task 1",date1, new Date(), 1, 1, 1);
    	//dbh.addNewTask("task2", "this is task 2", new Date(), date1,1,1,1);
    }
	
//	@Override
//	public void onPause(){
//		synchronized(DataInitializer.mainGarden){
//			DataInitializer.mainGarden = false;
//		}
//		super.onPause();
//	}
	
//	public void onResume(){
//		((Game_Garden_Screen)findViewById(R.id.gameMainScreen)).startNewThread();
//		synchronized(DataInitializer.mainGarden){
//			DataInitializer.mainGarden = true;
//		}
//		super.onResume();
//	}


	
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
    	case R.id.seed_market:
    		this.startActivity(new Intent(this,Game_Seed_Market.class));
    		return true;
    		
    	case R.id.go_garden_grower:
    		this.startActivity(new Intent(this,Game_Grower.class));
    		return true;
    	case R.id.go_task_view:
//            new AlertDialog.Builder(this).setMessage("go task pressed").show();
    		Game_Garden.this.startActivity(new Intent(Game_Garden.this, viewTasksActivity.class));
    		return true;
    		
    	case R.id.quit:
            new AlertDialog.Builder(this).setMessage("quit pressed").show();
    		return true;
    	}
    	
    	return super.onOptionsItemSelected(item);
    }
}
