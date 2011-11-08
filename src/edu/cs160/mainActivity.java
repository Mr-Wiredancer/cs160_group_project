package edu.cs160;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.LinkedList;
import java.util.Locale;

import edu.cs160.Game.Game_Grower;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu; import android.view.MenuInflater; import android.view.MenuItem;
import android.view.View;
public class mainActivity extends Activity {
    /** Called when the activity is first created. */

	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
//        new AlertDialog.Builder(this).setMessage("test dialog").show();
    }    
	
	public void go(View v){
//        new AlertDialog.Builder(this).setMessage("pressed").show();
		switch (v.getId()){
		case R.id.zen_garden_button:
//            new AlertDialog.Builder(this).setMessage("zen garden pressed").show();
			startActivity(new Intent(mainActivity.this, Game_Grower.class));
			break;
		case R.id.genchi_garden_button:
//            new AlertDialog.Builder(this).setMessage("Genchi garden pressed").show();
			startActivity(new Intent(mainActivity.this, Game_Grower.class));

			break;
		case R.id.help_button:
            new AlertDialog.Builder(this).setMessage("help pressed").show();
            break;
		case R.id.seed_market_button:
            new AlertDialog.Builder(this).setMessage("seed market pressed").show();
            break;
		}
	}
}
