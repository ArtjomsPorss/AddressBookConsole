package address_book.console_app.application;

import java.util.ArrayList;

import address_book.console_app.database.CSVSaverLoader;
import address_book.console_app.ui.ConsoleUserInterface;

public class Application {
	private ArrayList<AddressBookEntry> entries = new ArrayList<AddressBookEntry>();
	private ConsoleUserInterface userInterface = new ConsoleUserInterface();
	private CSVSaverLoader saverLoader = new CSVSaverLoader(this.userInterface);

	public Application() {
		super();
	}

	public void start() {
		this.runApplicationMenu();
	}

	private void runApplicationMenu() {
		entries = saverLoader.loadEntries();

		boolean continueRunningMenu = true;
		do {
			userInterface.displayMainMenu();
			String inputString = userInterface.getUserInput();
			int inputInt = stringToInt(inputString);
			final int FIRST_OPTION = 1;
			final int LAST_OPTION = 5;
			boolean inputValid = intIsValid(inputInt, FIRST_OPTION, LAST_OPTION);

			if (inputValid) {
				switch (inputInt) {
				case 1:
					userInterface.displayAllEntries(entries);
					break;
				case 2:
					addNewEntry();
					break;
				case 3:
					editExistingEntry();
					break;
				case 4:
					deleteExistingEntry();
					break;
				case 5:
					continueRunningMenu = false;
					break;
				default:
					throw new IllegalArgumentException(
							"Illegal argument in main menu");
				}
			}
		} while (continueRunningMenu);
		
		saveUserEntries();
	}

	private void saveUserEntries() {
		boolean saveEntries = userInterface.getUserConfirmationToSaveEntries();
		if(saveEntries){
			saverLoader.saveEntries(entries);
		}		
	}

	private int stringToInt(String text) {
		int input = -1;
		try {
			input = Integer.parseInt(text);
			return input;
		} catch (NumberFormatException nfe) {
			userInterface.displayMessage("!! Only numbers are accepted !!");
		}
		return input;
	}

	private boolean intIsValid(int inputInt, int fIRST_OPTION, int lAST_OPTION) {
		return inputInt >= fIRST_OPTION && inputInt <= lAST_OPTION;
	}

	private void addNewEntry() {
		userInterface.displayMessage("Enter new entry details:");
		String name = userInterface.getUserInput("name: ");
		String address = userInterface.getUserInput("address: ");
		String phoneNumber = userInterface.getUserInput("phone number: ");
		String email = userInterface.getUserInput("email: ");
		String zip = userInterface.getUserInput("zip: ");
		AddressBookEntry newEntry = new AddressBookEntry(name, address,
				phoneNumber, email, zip);
		entries.add(newEntry);
	}

	private void editExistingEntry() {
		String entryString = userInterface
				.getUserInput("Entry number to edit: ");
		int entryInt = stringToInt(entryString);
		final int FIRST_OPTION = 1;
		final int LAST_OPTION = entries.size();

		if (!intIsValid(entryInt, FIRST_OPTION, LAST_OPTION)) {
			userInterface.displayMessage("Invalid entry");
			return;
		}
		AddressBookEntry entry = entries.get(entryInt - 1);
		editEntryDetails(entry);
	}

	private void editEntryDetails(AddressBookEntry entry) {
		do {
			userInterface.displayEditMenu(entry);
			String choice = userInterface.getUserInput();
			if (choice.contentEquals("5")) {
				break;
			} else {
				editAddressBookEntry(choice, entry);
			}
		} while (true);
	}

	private void editAddressBookEntry(final String choice,
			final AddressBookEntry entry) {
		switch (choice) {
		case "1":
			entry.setAddress(userInterface.getUserInput("New address:"));
			break;
		case "2":
			entry.setPhoneNumber(userInterface
					.getUserInput("New Phone Number:"));
			break;
		case "3":
			entry.setEmail(userInterface.getUserInput("New email"));
			break;
		case "4":
			entry.setZip(userInterface.getUserInput("New zip"));
			break;
		default:
			userInterface.displayMessage("Invalid entry");
		}
	}

	private void deleteExistingEntry() {
		userInterface.displayDeleteOptions();
		String choice = userInterface.getUserInput();
		if (choice.contentEquals("0")) {
			return;
		}

		int choiceInt = stringToInt(choice);
		final int FIRST_OPTION = 1;
		final int LAST_OPTION = entries.size();
		if (!intIsValid(choiceInt, FIRST_OPTION, LAST_OPTION)) {
			userInterface.displayMessage("Invalid input");
			return;
		}

		entries.remove(choiceInt - 1);
		userInterface.displayMessage("Entry removed");
	}
}
