package edu.cs160;

public class Task{
	int id;
	String title;
	String description;
	String date_created;
	String date_started;
	String date_due;
	
	public Task(int id, String title, String descrip, String created, String started, String due){
		this.id=id;
		this.title=title;
		this.description=descrip;
		date_created=created;
		date_started=started;
		date_due=due;
	}
	
}