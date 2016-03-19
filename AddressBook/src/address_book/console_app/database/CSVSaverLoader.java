package address_book.console_app.database;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

import address_book.console_app.application.AddressBookEntry;
import address_book.console_app.ui.ConsoleUserInterface;

public class CSVSaverLoader {
	private final String FILENAME = "\\entries.txt";
	private FileWriter writer;
	private Scanner reader;
	private File entryFile;
	private ConsoleUserInterface userInterface;

	public CSVSaverLoader(ConsoleUserInterface ui) {
		userInterface = ui;
		setupDirectoryForReading();
	}

	private void setupDirectoryForReading() {
		File folder = setupFolder();

		createNewFileIfDoesntExist(folder);
		setupReader();
	}

	private void setupDirectoryForWriting() {
		File folder = setupFolder();
	
		entryFile = new File(folder.getPath() + FILENAME);
		if (entryFile.exists()) {
			entryFile.delete();
		}
		
		createNewFileIfDoesntExist(folder);
		setupWriter();
	}

	private void createNewFileIfDoesntExist(File folder) {
		entryFile = new File(folder.getPath() + FILENAME);
		if (!entryFile.exists()) {
			try {
				entryFile.createNewFile();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	private File setupFolder() {
		File folder = new File("application_data");
		if (!folder.exists()) {
			folder.mkdir();
		}
		return folder;
	}

	private void setupWriter() {
		try {
			writer = new FileWriter(entryFile);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	private void setupReader(){
		try {
			reader = new Scanner(new BufferedReader(new FileReader(entryFile)));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public void saveEntries(ArrayList<AddressBookEntry> entries) {
		setupDirectoryForWriting();

		StringBuffer entryBuffer = new StringBuffer();

		for (AddressBookEntry entry : entries) {
			entryBuffer.append(entry.getEntryAsOneLineString() + "\n");
		}
		
		try {
			writer.write(entryBuffer.toString());
			writer.flush();
			writer.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<AddressBookEntry> loadEntries() {
		StringBuffer entryBuffer = new StringBuffer();

		while(reader.hasNext()){
			entryBuffer.append(reader.nextLine() + "\n");
		}
		reader.close();

		return convertStringToEntries(entryBuffer);		
	}

	private ArrayList<AddressBookEntry> convertStringToEntries(final StringBuffer entryBuffer) {
		ArrayList<AddressBookEntry> entries = new ArrayList<AddressBookEntry>();

		String[] entriesStringArray = entryBuffer.toString().split("\n");
		
		for (int i = 0; i < entriesStringArray.length; i++) {
			if(entriesStringArray[i].length() == 9){
				entries.add(new AddressBookEntry(entriesStringArray[i]));
			}
		}
		
		return entries;
	}
	
	
}
