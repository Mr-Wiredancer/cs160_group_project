package edu.cs160;

public class Repeat {
	boolean sun,mon,tue,wed,thr,fri,sat;
	int id;
	
	public Repeat(int id, String input){
		this.id = id;
		sun = (input.substring(0,1)).equals("0")?false:true;
		mon = (input.substring(1,2)).equals("0")?false:true;
		tue = (input.substring(2,3)).equals("0")?false:true;
		wed = (input.substring(3,4)).equals("0")?false:true;
		thr = (input.substring(4,5)).equals("0")?false:true;
		fri = (input.substring(5,6)).equals("0")?false:true;
		sat = (input.substring(6,7)).equals("0")?false:true;
	}
	
	public boolean isEveryday(){
		return sun && mon && tue && wed && thr && fri && sat;
	}
	
	public boolean isWeekend(){
		return sat && sun;
	}
	
	public boolean dontRepeat(){
		return !(sun || mon || tue || wed || thr || fri || sat);
	}
	
	public boolean isWeekday(){
		return mon && tue && wed && thr && fri;
	}
	
}
