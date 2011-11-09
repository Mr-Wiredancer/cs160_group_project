package edu.cs160.Game;

import edu.cs160.DEBUGGER;
import edu.cs160.R;
import edu.cs160.mainActivity;
import edu.cs160.viewTasksActivity;
import edu.cs160.Game.GameObjects.DataInitializer;
import edu.cs160.Game.Screens.Game_Grower_Screen;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;

public class Game_Seed_Market extends Activity{
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		DataInitializer.mainActivity = this;
		DataInitializer.init(this);
		DEBUGGER.debug();
//		DEBUGGER.debug();
		setContentView(R.layout.garden_seed_market);
		//setContentView(new Game_Grower_Screen(this));
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
    		Game_Seed_Market.this.startActivity(new Intent(Game_Seed_Market.this, viewTasksActivity.class));
    		return true;
//    	case R.id.g:
////          new AlertDialog.Builder(this).setMessage("go task pressed").show();
//  		Game_Grower.this.startActivity(new Intent(Game_Grower.this, viewTasksActivity.class));
//  		return true;
    	case R.id.quit:
            new AlertDialog.Builder(this).setMessage("quit pressed").show();
    		return true;
    	}
    	
    	return super.onOptionsItemSelected(item);
    }
}
