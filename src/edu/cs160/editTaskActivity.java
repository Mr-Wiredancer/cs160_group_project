package edu.cs160;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.AdapterView.OnItemSelectedListener;

public class editTaskActivity extends Activity implements OnItemSelectedListener, OnClickListener{
    /** Called when the activity is first created. */
	private int repeat, remind;
	Spinner reminder, repeater;
	DatabaseHelper db;
	DatabaseDataHelper dbh;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_task);
        Bundle bundle = getIntent().getExtras();

        db = new DatabaseHelper(this);
//      new AlertDialog.Builder(this).setMessage("test dialog").show();
        dbh = new DatabaseDataHelper(db);
        
    	//Setting up the spinners
    	repeater = (Spinner)findViewById(R.id.Spinner_Repeat);
    	ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.Repeat_Values, android.R.layout.simple_spinner_item);
    	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	repeater.setAdapter(adapter);
    	repeater.setOnItemSelectedListener(this);
    	reminder = (Spinner)findViewById(R.id.Spinner_Reminder);
    	adapter = ArrayAdapter.createFromResource(this, R.array.Remind_Values, android.R.layout.simple_spinner_item);
    	adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
    	reminder.setAdapter(adapter);
    	reminder.setOnItemSelectedListener(this);
    
    	//if task id exists, fill the fields
    	if (bundle.containsKey("task_id")){
    		fillFields(bundle.getInt("task_id"));
    	}
    	
        try{
	        if(savedInstanceState.containsKey("task_id")){
	        	//If it's not new, then fill in values
	        	

	       }
        }catch(Exception e){
        	
        }
                        
    }
    
    public void fillFields(int task_id){
    	Task task = dbh.getTask(task_id);

    }

	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.Okay){
			//
			//
			//The code that will be used to export the data
			//
			//
			String eventName = ((EditText)findViewById(R.id.InputText)).getText().toString(), eventDesc = ((EditText)findViewById(R.id.Description)).toString();
			DatePicker startDate = (DatePicker)findViewById(R.id.datePicker);		
			TimePicker startTime = (TimePicker)findViewById(R.id.timePicker_Start), endTime = (TimePicker)findViewById(R.id.timePIcker_End);
			
			
			
		}
		
	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {		
		if(parent.getId()== R.id.Spinner_Repeat){
			this.repeat = pos;
			if(parent.getItemAtPosition(pos).toString().equals("Custom...")){
				findViewById(R.id.Repeat_M).setVisibility(0);
				findViewById(R.id.Repeat_Tu).setVisibility(0);
				findViewById(R.id.Repeat_W).setVisibility(0);
				findViewById(R.id.Repeat_Th).setVisibility(0);
				findViewById(R.id.Repeat_F).setVisibility(0);
				findViewById(R.id.Repeat_Sa).setVisibility(0);
				findViewById(R.id.Repeat_Su).setVisibility(0);
			}else{
				findViewById(R.id.Repeat_M).setVisibility(8);
				findViewById(R.id.Repeat_Tu).setVisibility(8);
				findViewById(R.id.Repeat_W).setVisibility(8);
				findViewById(R.id.Repeat_Th).setVisibility(8);
				findViewById(R.id.Repeat_F).setVisibility(8);
				findViewById(R.id.Repeat_Sa).setVisibility(8);
				findViewById(R.id.Repeat_Su).setVisibility(8);
			}
			
		}else if (parent.getId()==R.id.Spinner_Reminder){			
			this.remind = pos;
			if(parent.getItemAtPosition(pos).toString().equals("Custom...")){
				findViewById(R.id.Reminder_Days).setVisibility(0);
				findViewById(R.id.Description_Reminder_Days).setVisibility(0);
				findViewById(R.id.Reminder_Hours).setVisibility(0);
				findViewById(R.id.Description_Reminder_Hours).setVisibility(0);
				findViewById(R.id.Reminder_Minutes).setVisibility(0);
				findViewById(R.id.Description_Reminder_Minutes).setVisibility(0);
				
			}else{
				findViewById(R.id.Reminder_Days).setVisibility(8);
				findViewById(R.id.Description_Reminder_Days).setVisibility(8);
				findViewById(R.id.Reminder_Hours).setVisibility(8);
				findViewById(R.id.Description_Reminder_Hours).setVisibility(8);
				findViewById(R.id.Reminder_Minutes).setVisibility(8);
				findViewById(R.id.Description_Reminder_Minutes).setVisibility(8);
			}
		}
		
		
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}
    
}