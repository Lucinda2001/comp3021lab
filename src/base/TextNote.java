package base;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;

public class TextNote extends Note implements java.io.Serializable {
	public String content;
	private static final long serialVersionUID = 1L;

	public TextNote(String title){
		super(title);
	}

	public TextNote(String title, String content){
		super(title);
		this.content = content;
	}

	public TextNote(File f){
		super(f.getName());
		this.content = getTextFromFile(f.getAbsolutePath());
	}

	public String getContent(){
		return this.content;
	}

	private String getTextFromFile(String absolutePath){
		String result = "";
		FileInputStream fis = null;
		InputStreamReader isr = null;
		BufferedReader in = null;
		try{
			fis = new FileInputStream(absolutePath);
			isr = new InputStreamReader(fis);
			in = new BufferedReader(isr);
			String temString = "";
			while((temString = in.readLine())!= null){
				result = result + temString;
			}
			in.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();

		}catch(IOException e){
			e.printStackTrace();
		}
		return result;
	}

	public void exportTextToFile(String pathFolder){
		if(pathFolder == ""){
			pathFolder = ".";
		}
		File file = new File(pathFolder + File.separator + this.getTitle().replaceAll(" ", "_")+".txt");
		FileWriter fw = null;
		BufferedWriter bw = null;
		try{

			fw = new FileWriter(file.getAbsoluteFile());
			bw = new BufferedWriter(fw);
			bw.write(this.content);
			bw.close();
		}catch(FileNotFoundException e){
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
	}

}
