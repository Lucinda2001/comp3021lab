package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

public class Folder extends NoteBook implements Comparable<Folder>, java.io.Serializable{
	private ArrayList<Note> notes;
	private String name;
	private static final long serialVersionUID = 1L;

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
		for(Note n:this.notes){
			if(n instanceof ImageNote){
				if(keys.length <= 1){
					if(n.getTitle().toLowerCase().contains(keys[0].toLowerCase()))
						backNotes.add(n);
						break;
				}
				for(int i=1; i<keys.length-1;i++){
					if(keys[i].equalsIgnoreCase("or")){
						if(n.getTitle().toLowerCase().contains(keys[i-1].toLowerCase()) || n.getTitle().toLowerCase().contains(keys[i+1].toLowerCase())){

						}
						else{
							break;
						}
					}
					else if((!keys[i-1].equalsIgnoreCase("or")) && (!keys[i+1].equalsIgnoreCase("or")) ){
						if(n.getTitle().toLowerCase().contains(keys[i])){

						}
						else{
							break;
						}
					}
					if(i == keys.length-2){
						backNotes.add(n);
					}
				}

			}
			else if(n instanceof TextNote){
				TextNote tn = (TextNote)n;
				if(keys.length <= 1){
					if(n.getTitle().toLowerCase().contains(keys[0].toLowerCase()) || tn.getContent().toLowerCase().contains(keys[0].toLowerCase())){
						backNotes.add(n);

					}
				}
				for(int i=1; i<keys.length-1;i++){
					if(keys[i].equalsIgnoreCase("or")){

						if(n.getTitle().toLowerCase().contains(keys[i-1].toLowerCase()) || n.getTitle().toLowerCase().contains(keys[i+1].toLowerCase()) || tn.getContent().toLowerCase().contains(keys[i-1].toLowerCase()) || tn.getContent().toLowerCase().contains(keys[i+1].toLowerCase())){

						}
						else{
							break;
						}
					}
					else if((!keys[i-1].equalsIgnoreCase("or")) && (!keys[i+1].equalsIgnoreCase("or")) ){
						if(n.getTitle().toLowerCase().contains(keys[i]) || tn.getContent().contains(keys[i].toLowerCase())){

						}
						else{
							break;
						}
					}
					if(i == keys.length-2){
						backNotes.add(n);
					}
				}
			}
		}

		return backNotes;
	}
}
