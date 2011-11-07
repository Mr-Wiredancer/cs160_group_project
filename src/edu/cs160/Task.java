package edu.cs160;

import java.util.Date;
import java.util.LinkedList;

public class Task{
	int id;
	String title;
	String description;
	Date date_created;
	Date date_started;
	Date date_finished;
	Repeat repeat;
	Reminder reminder;
	LinkedList<Tag> tags;
	boolean isCompleted;
	int resource_id;
	
	public Task(int id, String title, String description, Date date_created, Date date_started, Date date_finished, Repeat repeat, Reminder reminder, LinkedList<Tag> tags, int resource_id, int completed){
		this.id=id;
		this.title=title;
		this.description=description;
		this.date_created=date_created;
		this.date_started = date_started;
		this.date_finished=date_finished;
		this.repeat = repeat;
		this.reminder = reminder;
		this.tags = tags;
		this.resource_id=resource_id;
		this.isCompleted = (completed==1);
	}
	
}