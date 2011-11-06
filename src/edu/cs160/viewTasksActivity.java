package edu.cs160;

import java.util.LinkedList;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.Menu; import android.view.MenuInflater; import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;
public class viewTasksActivity extends Activity {
    /** Called when the activity is first created. */
	DatabaseHelper db;
	DatabaseDataHelper dbh;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks_view);
        db = new DatabaseHelper(this);
//        new AlertDialog.Builder(this).setMessage("test dialog").show();
        dbh = new DatabaseDataHelper(db);
        updateView();
    }
    
    public void updateView(){
    	LinearLayout v = (LinearLayout)findViewById(R.id.task_view_root);
    	for(Task task:dbh.getTasks()){
//    		TextView child = new TextView(this);
    		TextView child = (TextView)getLayoutInflater().inflate(R.layout.task_frame, null);
    		child.setText(task.title);
    		child.setId(task.id);
    		child.setTextSize(20);
    		
    		child.setOnClickListener(new OnClickListener(){
    			
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					int task_id = v.getId();
					Intent i= new Intent(viewTasksActivity.this, editTaskActivity.class);
					i.putExtra("task_id", task_id);
					startActivity(i);
				}
    			
    		});
        	v.addView(child);
    	}
    }
    
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
    	super.onCreateOptionsMenu(menu); 
    	MenuInflater inflater = getMenuInflater(); 
    	inflater.inflate(R.menu.tasks_view_menu, menu); 
    	return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item){
    	switch(item.getItemId()){
    	case R.id.add_new_task:
    		startActivity(new Intent(viewTasksActivity.this, editTaskActivity.class));
    		return true;
    	case R.id.go_garden_view:
            new AlertDialog.Builder(this).setMessage("go garden pressed").show();
    		return true;
    	case R.id.quit:
            new AlertDialog.Builder(this).setMessage("quit pressed").show();
    		return true;
    	}
    	
    	return super.onOptionsItemSelected(item);
    }
    
    public void clickTask(View v){
    	switch (v.getId()){
//    	case R.id.task_1:
//            new AlertDialog.Builder(this).setMessage("task 1 pressed").show();
//    		break;
//    	case R.id.task_2:
//            new AlertDialog.Builder(this).setMessage("task 2 pressed").show();
//    		
//    		break;
//    	case R.id.task_3:
//            new AlertDialog.Builder(this).setMessage("task 3 pressed").show();
//    		
//    		break;
//    	case R.id.task_4:
//            new AlertDialog.Builder(this).setMessage("task 4 pressed").show();
//    		
//    		break;
    	}
    }
    
    
}
