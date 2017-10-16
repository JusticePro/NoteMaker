package me.justicepro.notemaker;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

public class NoteFile implements Serializable {

	private String body;
	
	// ID, Body
	private HashMap<String, String> notes;
	
	public NoteFile(String body) {
		this.body = body;
		this.notes = new HashMap<>();
	}
	
	public NoteFile(String body, HashMap<String, String> notes) {
		this.body = body;
		this.notes = notes;
	}
	
	public HashMap<String,String> getNotes() {
		return notes;
	}
	
	public void setNotes(HashMap<String, String> notes) {
		this.notes = notes;
	}
	
	public String getBody() {
		return body;
	}
	
	public void setBody(String body) {
		this.body = body;
	}
	
	public static void saveToFile(NoteFile design, File file) throws IOException {
		ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file));
		output.writeObject(design);
		output.close();
	}
	
	public static NoteFile loadFromFile(File file) throws IOException, ClassNotFoundException {
		ObjectInputStream input = new ObjectInputStream(new FileInputStream(file));
		NoteFile design = (NoteFile) input.readObject();
		input.close();
		return design;
	}
	
}