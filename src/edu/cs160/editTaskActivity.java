package edu.cs160;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Intent;
import android.database.Cursor;
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
	Task currentTask;
	
	public void sync(View v){
        new AlertDialog.Builder(this).setMessage("your task has been synced to Google Calender").show();

	}
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.edit_task);
        Bundle bundle = getIntent().getExtras();

        db = new DatabaseHelper(this);
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
    	((Button)findViewById(R.id.Okay)).setOnClickListener((OnClickListener)this);
    	//if task id exists, fill the fields
    	if (bundle!=null && bundle.containsKey("task_id")){
    		currentTask=dbh.getTask(bundle.getInt("task_id"));
    		fillFields();
    	}                        
    }
    
    public void fillFields(){
    	int task_id = currentTask.id;
    	Date dateCreated = currentTask.date_created;
    	System.out.println(dateCreated);
    	Date dateFinished = currentTask.date_finished;
    	
    	((EditText)findViewById(R.id.InputText)).setText(currentTask.title);
    	if (dateCreated!=null){
    	((DatePicker)findViewById(R.id.datePicker)).updateDate(dateCreated.getYear()+1900, dateCreated.getMonth(), dateCreated.getDay()+6);

    	((TimePicker)findViewById(R.id.timePicker_Start)).setCurrentHour(dateCreated.getHours());
    	((TimePicker)findViewById(R.id.timePicker_Start)).setCurrentMinute(dateCreated.getMinutes());
    	}
    	if (dateFinished!=null){
	    	((TimePicker)findViewById(R.id.timePIcker_End)).setCurrentHour(dateFinished.getHours());
	    	((TimePicker)findViewById(R.id.timePIcker_End)).setCurrentMinute(dateFinished.getMinutes());
    	}
    	((EditText)findViewById(R.id.Description)).setText(currentTask.description);
//    	
    	Repeat repeatData = currentTask.repeat;
    	Reminder reminderData = currentTask.reminder;
    	if(repeatData.dontRepeat())
    		((Spinner)findViewById(R.id.Spinner_Repeat)).setSelection(0);
    	else if(repeatData.isEveryday())
    		((Spinner)findViewById(R.id.Spinner_Repeat)).setSelection(1);
    	else if(repeatData.isWeekday())
    		((Spinner)findViewById(R.id.Spinner_Repeat)).setSelection(2);
    	else if(repeatData.isWeekend())
    		((Spinner)findViewById(R.id.Spinner_Repeat)).setSelection(3);
    	else{
    		((Spinner)findViewById(R.id.Spinner_Repeat)).setSelection(4);
    		((CheckBox)findViewById(R.id.Repeat_M)).setChecked(repeatData.mon);
    		((CheckBox)findViewById(R.id.Repeat_Tu)).setChecked(repeatData.tue);
    		((CheckBox)findViewById(R.id.Repeat_W)).setChecked(repeatData.wed);
    		((CheckBox)findViewById(R.id.Repeat_Th)).setChecked(repeatData.thr);
    		((CheckBox)findViewById(R.id.Repeat_F)).setChecked(repeatData.fri);
    		((CheckBox)findViewById(R.id.Repeat_Sa)).setChecked(repeatData.sat);
    		((CheckBox)findViewById(R.id.Repeat_Su)).setChecked(repeatData.sun);
    	}
    	
    	if(reminderData.minutes ==-1 && reminderData.hours==-1 && reminderData.days == -1)
    		reminder.setSelection(0);    	
    	else if(reminderData.minutes==5 && reminderData.hours==0 && reminderData.days==0)
    		reminder.setSelection(1);
    	else if(reminderData.minutes==15 && reminderData.hours==0 && reminderData.days==0)
    		reminder.setSelection(2);
    	else if(reminderData.minutes==30 && reminderData.hours==0 && reminderData.days==0)
    		reminder.setSelection(3);
    	else if(reminderData.minutes==0 && reminderData.hours==1 && reminderData.days==0)
    		reminder.setSelection(4);
    	else if(reminderData.minutes==0 && reminderData.hours==0 && reminderData.days==1)
    		reminder.setSelection(5);
    	else if(reminderData.minutes==0 && reminderData.hours==0 && reminderData.days==3)
    		reminder.setSelection(6);
    	else if(reminderData.minutes==0 && reminderData.hours==1 && reminderData.days==7)
    		reminder.setSelection(7);
    	else{
    		reminder.setSelection(8);
    		//keep error
//    		((EditText)findViewById(R.id.Reminder_Days)).setText(reminderData.days);
//    		((EditText)findViewById(R.id.Reminder_Hours)).setText(reminderData.hours);
//    		((EditText)findViewById(R.id.Reminder_Minutes)).setText(reminderData.minutes);
    	}
//    	
    	
    	String tagField="";
    	for(Tag t: dbh.getTagsForTask(task_id)){
    		tagField+=t.name+",";
    		
    	}
    	((EditText)findViewById(R.id.Tags)).setText(tagField);
    }

    public void createTask(){
    	String eventName = ((EditText)findViewById(R.id.InputText)).getText().toString(), eventDesc = ((EditText)findViewById(R.id.Description)).getText().toString();
		DatePicker startDate = (DatePicker)findViewById(R.id.datePicker);
//        new AlertDialog.Builder(this).setMessage("year "+startDate.getYear()+", month "+startDate.getMonth()+", day "+startDate.getDayOfMonth()).show();
		TimePicker startTime = (TimePicker)findViewById(R.id.timePicker_Start), endTime = (TimePicker)findViewById(R.id.timePIcker_End);
		
		//Ignore reminder and repeat now
		
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss",Locale.US);
		int start_month = startDate.getMonth()+1;
		int start_year = startDate.getYear();
		int start_day = startDate.getDayOfMonth();
		//Have to get these from time picker
		int start_hours;
		int start_minutes;
		int start_seconds;
		int end_hours;
		int end_minutes;
		int end_seconds;
		
		Date date_started;
		Date date_finished;
//		try{
//			date_started = formatter.parse((start_month>9?start_month:("0"+start_month))+"-"+((start_day>9?start_day:("0"+start_day)))+"-"+start_year+" "+(start_hours>9?start_hours:("0"+start_hours))+":"+(start_minutes>9?start_minutes:("0"+start_minutes))+":"+(start_seconds>9?start_seconds:("0"+start_seconds)));
//		}catch(Exception e){
//			date_started = null;
//		}
//		try{
//			date_finished = formatter.parse((start_month>9?start_month:("0"+start_month))+"-"+((start_day>9?start_day:("0"+start_day)))+"-"+start_year+" "+(end_hours>9?end_hours:("0"+end_hours))+":"+(end_minutes>9?end_minutes:("0"+end_minutes))+":"+(end_seconds>9?end_seconds:("0"+end_seconds)));
//		}catch(Exception e){
//			date_finished = null;
//		}
		
		dbh.addNewTask(eventName, eventDesc);
	}
    
    public void updateTask(){
    	String eventName = ((EditText)findViewById(R.id.InputText)).getText().toString(), eventDesc = ((EditText)findViewById(R.id.Description)).getText().toString();
		DatePicker startDate = (DatePicker)findViewById(R.id.datePicker);
//        new AlertDialog.Builder(this).setMessage("year "+startDate.getYear()+", month "+startDate.getMonth()+", day "+startDate.getDayOfMonth()).show();
		TimePicker startTime = (TimePicker)findViewById(R.id.timePicker_Start), endTime = (TimePicker)findViewById(R.id.timePIcker_End);
		
		//Ignore reminder and repeat now
		
		SimpleDateFormat formatter = new SimpleDateFormat("MM-dd-yyyy hh:mm:ss",Locale.US);
		int start_month = startDate.getMonth()+1;
		int start_year = startDate.getYear();
		int start_day = startDate.getDayOfMonth();
		Date newDateCreated = currentTask.date_created==null?new Date():currentTask.date_created;
		Date newDateFinished = currentTask.date_finished==null?new Date():currentTask.date_finished;
		//Date cannot change day, have to change data type later
		newDateCreated.setYear(start_year);
		newDateCreated.setMonth(start_month);
		newDateFinished.setYear(start_year);
		newDateFinished.setMonth(start_month);
		dbh.updateTask(eventName, eventDesc, newDateCreated, newDateFinished, currentTask);
    }
    
	@Override
	public void onClick(View v) {
		if(v.getId() == R.id.Okay){
			if (currentTask==null){
				createTask();
			}else{
				updateTask();
			}
			startActivity(new Intent(editTaskActivity.this, viewTasksActivity.class));
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