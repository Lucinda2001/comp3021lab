package base;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NoteBook {
	private ArrayList<Folder> folders;

	public NoteBook(){
		folders = new ArrayList<Folder>();
	}

	public boolean insertNote(String folderName, Note note){
		 for(Folder f:folders){
			 if(f.equals(folderName)){
				 for(Note n:f.getNotes()){
					 if(n.equals(note))
					 {
						 System.out.println("Creating note " + note.getTitle() + " under folder " + folderName + " failed");
						 return false;
					 }

				 }
				 f.addNote(note);
				 return true;
			 }

		 }
		 Folder nf = new Folder(folderName);
		 nf.addNote(note);
		 folders.add(nf);
		 return true;

	}

	public boolean createTextNote(String folderName, String title){
		TextNote note = new TextNote(title);
		return insertNote(folderName, note);
	}

	public boolean createTextNote(String folderName, String title, String content){
		TextNote note = new TextNote(title, content);
		return insertNote(folderName, note);
	}

	public boolean createImageNote(String folderName, String title){
		ImageNote note = new ImageNote(title);
		return insertNote(folderName, note);
	}

	public ArrayList<Folder> getFolders(){
		return folders;
	}

	public void sortFolders(){
		Collections.sort(folders);
		for(Folder f:folders){
			f.sortNotes();

		}
	}

	public List<Note> searchNotes(String keywords){
		ArrayList<Note> searchNotes = new ArrayList<Note>();
		for(Folder f:folders){
			searchNotes.addAll(f.searchNotes(keywords));
		}
		return searchNotes;
	}
}
