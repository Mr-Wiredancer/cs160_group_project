package edu.cs160;

import edu.cs160.Game.Game_Grower;
import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.provider.BaseColumns;
import android.view.Menu; import android.view.MenuInflater; import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.ViewGroup.LayoutParams;
import android.view.ViewGroup.MarginLayoutParams;
import android.widget.LinearLayout;
import android.widget.TextView;

public class viewTasksActivity extends ListActivity {
    /** Called when the activity is first created. */

	private DatabaseHelper db;
	private DatabaseDataHelper dbh;
	private Cursor data;
	private SimpleCursorAdapter dataSource;
    
	@Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.tasks_view);
        db = new DatabaseHelper(this);
        dbh = new DatabaseDataHelper(db);
        data = dbh.getTasksCursor();
        startManagingCursor(data);
    	String fields[] = {"title", "description", "date_time_finished", BaseColumns._ID};
        int[] to = new int[] { R.id.title, R.id.description, R.id.date_finished };
        dataSource = new SimpleCursorAdapter(this, R.layout.task_row, data, fields, to);
        ListView view = getListView();
        view.setHeaderDividersEnabled(true);
        setListAdapter(dataSource);
      //updateView();
	}
    
//    public void updateView(){
//    	LinearLayout v = (LinearLayout)findViewById(R.id.task_view_root);
//    	for(Task task:database.getTasks()){
////    		TextView child = new TextView(this);
//    		TextView child = (TextView)getLayoutInflater().inflate(R.layout.task_frame, null);
//    		child.setText(task.title);
//    		child.setId(task.id);
//    		child.setTextSize(20);
//    		
//    		child.setOnClickListener(new OnClickListener(){
//    			
//				public void onClick(View v) {
//					// TODO Auto-generated method stub
//					int task_id = v.getId();
//					Intent i= new Intent(viewTasksActivity.this, editTaskActivity.class);
//					i.putExtra("task_id", task_id);
//					startActivity(i);
//				}
//    			
//    		});
//        	v.addView(child);
//    	}
//    }
	
	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		super.onListItemClick(l, v, position, id);
		// Get the item that was clicked
//		Object o = this.getListAdapter().getItem(position);
//		String keyword = o.toString();
//		Toast.makeText(this, "You selected: " + keyword, Toast.LENGTH_LONG)
//				.show();
		//int task_id = v.getId();
//		Toast.makeText(this, "You selected: " + id, Toast.LENGTH_LONG).show();
		Intent i= new Intent(this, editTaskActivity.class);
		i.putExtra("task_id", (int)id);
		startActivity(i);
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
    	int id = item.getItemId();
    	if(id==R.id.add_new_task) {
    		startActivity(new Intent(viewTasksActivity.this, editTaskActivity.class));
    		return true;
    	} else if (id==R.id.go_garden_view) {
			startActivity(new Intent(viewTasksActivity.this, Game_Grower.class));
    		return true;
    	} else if (id==R.id.quit) {
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