package base;

import java.util.Date;

public class Note implements Comparable<Note>{
	private Date date;
	private String title;

	public Note(String title){
		this.title = title;
		date = new Date(System.currentTimeMillis());
	}

	public String getTitle(){
		return this.title;
	}

	public String toString(){
		return date.toString() + "\t" + title;
	}

	public boolean equals(Note another){
		if(this.title.equals(another.title))
			return true;
		return false;
	}

	public int compareTo(Note O){
		return (0-this.date.compareTo(O.date));
	}
}
