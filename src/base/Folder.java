package base;

import java.util.ArrayList;

public class Folder extends NoteBook{
	private ArrayList<Note> notes;
	private String name;

	public Folder(String name){
		this.name = name;
		notes = new ArrayList<Note>();

	}

	public void addNote(Note arg0){
		notes.add(arg0);
	}

	public String getName(){
		return this.name;
	}

	public ArrayList<Note> getNotes(){
		return notes;
	}

	public boolean equals(Object another){
		if(this.name.equals(another))
			return true;
		return false;
	}

	public String toString(){
		int nText = 0;
		int nImage = 0;
		for(Note n:notes){
			if(n instanceof TextNote){
				nText++;
			}
			if(n instanceof ImageNote)
				nImage++;
		}

		return name + ":" + nText + ":" + nImage;
	}
}
