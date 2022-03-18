package base;

import java.io.FileOutputStream;
import java.io.ObjectOutputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NoteBook implements Serializable{
	private ArrayList<Folder> folders;
	private static final long serialVersionUID = 1L;

	public NoteBook(){
		folders = new ArrayList<Folder>();
	}

	public NoteBook(String file){
		FileInputStream fis = null;
		ObjectInputStream in = null;
		try{
			fis = new FileInputStream("file.ser");
			in = new ObjectInputStream(fis);
			NoteBook n = (NoteBook)in.readObject();
			this.folders = n.folders;
			in.close();
		}catch(Exception e){
			e.printStackTrace();
		}

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

	public boolean save(String file){
		FileOutputStream fos = null;
		ObjectOutputStream out = null;
		try{
			fos = new FileOutputStream("file.ser");
			out = new ObjectOutputStream(fos);
			out.writeObject(this);
			out.close();

		}catch(Exception e){
			e.printStackTrace();
			return false;
		}
		return true;


	}
}
