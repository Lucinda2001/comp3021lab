package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Folder extends NoteBook implements Comparable<Folder>{
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

	public int compareTo(Folder O){
		return this.name.compareTo(O.name);
	}

	public void sortNotes(){
		Collections.sort(notes);
	}

	public List<Note> searchNotes(String keywords){
		List<Note> backNotes = new LinkedList<>();
		String[] keys = keywords.split(" ");
		for(int i = 0; i<keys.length; i++){
			if(keys[i].equalsIgnoreCase("or")){
				for(Note n:this.notes){
					if(n instanceof ImageNote){

						if(n.getTitle().toLowerCase().contains(keys[i-1].toLowerCase()) || n.getTitle().toLowerCase().contains(keys[i+1].toLowerCase())){
							if(backNotes == null)
								backNotes.add(n);
							else
							{
								for(Note j: backNotes){
									if(j.equals(n));
									else{
										
									}
									
								}
							}
						}
					}
					else{
							TextNote tn = (TextNote)n;
							if(n.getTitle().toLowerCase().contains(keys[i-1].toLowerCase()) || n.getTitle().toLowerCase().contains(keys[i+1].toLowerCase()) || tn.getContent().toLowerCase().contains(keys[i-1].toLowerCase()) || tn.getContent().toLowerCase().contains(keys[i+1].toLowerCase())){
								backNotes.add(n);
							}


					}
				}
			}
		}
		return backNotes;
	}
}
