package address_book.console_app.ui;

import java.util.ArrayList;
import java.util.Scanner;

import address_book.console_app.application.AddressBookEntry;

public class ConsoleUserInterface {

	public ConsoleUserInterface(){
		super();
	}
	
	
	public void displayMainMenu(){
		System.out.println();
		System.out.println("------ Address Book ------");
		System.out.println("1) Display all entries");
		System.out.println("2) Add new entry");
		System.out.println("3) Edit existing entry");
		System.out.println("4) Delete existing entry");
		System.out.println("5) Quit");
		System.out.println("--------------------------");
	}
	
	
	public void displayMessage(String text){
		System.out.println(text);
	}
	
	public void print(String text){
		System.out.print(text);
	}
	
	
	public void displayAllEntries(ArrayList<AddressBookEntry> entries){
		final int NUM_ENTRIES = entries.size();
		if(NUM_ENTRIES == 0){
			System.out.println("--------------------------");
			System.out.println(" No Entries");
			System.out.println("--------------------------");
			return;
		}
		
		System.out.println("----- E N T R I E S ------");
		for (int i = 0; i < NUM_ENTRIES; i++) {
			System.out.println("Entry No: " + (i+1));
			System.out.println(entries.get(i));
			
			int lastIteration = entries.size()-1;
			if(i == lastIteration) break; 
			
			System.out.println();
		}
		System.out.println("--------------------------");
	}
	
	public String getUserInput(){
		Scanner scan = new Scanner(System.in);
		System.out.print("> ");
		String input =  scan.nextLine();
		System.out.println();
		return input;
	}
	
	public String getUserInput(final String message){
		Scanner scan = new Scanner(System.in);
		System.out.println(message);
		System.out.print("> ");
		String input =  scan.nextLine();
		System.out.println();
		return input;
	}
	
	
	public void displayEditMenu(final AddressBookEntry entry){
		displayMessage("Edit details of " + entry.getName() + ":");
		displayMessage("1. Address: " + entry.getAddress());
		displayMessage("2. Phone Number: " + entry.getPhoneNumber());
		displayMessage("3. Email: " + entry.getEmail());
		displayMessage("4. Zip: " + entry.getZip());
		displayMessage("5. Return to main menu");
	}
	
	public void displayDeleteOptions() {
		displayMessage("Input entry you wish to delete,");
		displayMessage("Or input 0(ZERO) to exit.");
	}


	public boolean getUserConfirmationToSaveEntries() {
		boolean saveEntries = false;
		
		final String YES = new String("yes");
		final String NO = new String("no");
		String userMessage;
		
		do{
			userMessage = getUserInput("Do you wish to save entry modification?(yes/no)");
			if(YES.equalsIgnoreCase(userMessage)){
				saveEntries =  true;
				break;
			}
			if(NO.equalsIgnoreCase(userMessage)){
				break;
			}
			displayMessage("Invalid input");
		}while(true);
		
		return saveEntries;
	}
}
