package edu.cs160;

public class Task{
	int id;
	String title;
	String description;
	String date_created;
	String date_finished;
	int repeat_id;
	int reminder_id;
	int tag_task_id;
	int resource_id;
	
	public Task(int id, String title, String description, String date_started, String date_finished, int repeat_id, int reminder_id, int tag_task_id, int resource_id){
		this.id=id;
		this.title=title;
		this.description=description;
		this.date_created=date_started;
		this.date_finished=date_finished;
		this.repeat_id=repeat_id;
		this.reminder_id=reminder_id;
		this.tag_task_id=tag_task_id;
		this.resource_id=resource_id;
	}
	
}