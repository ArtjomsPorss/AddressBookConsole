package address_book.console_app.application;

public class AddressBookEntry {
	private String name;
	private String address;
	private String phoneNumber;
	private String email;
	private String zip;
	
	
	public AddressBookEntry(String name, String address, String phoneNumber,
			String email, String zip) {
		super();
		this.name = name;
		this.address = address;
		this.phoneNumber = phoneNumber;
		this.email = email;
		this.zip = zip;
	}
	
	public AddressBookEntry(String entryAsString){
		String[] entryData = entryAsString.split(",");
		if(entryData.length == 5){
			this.name = entryData[0];
			this.address = entryData[1];
			this.phoneNumber = entryData[2];
			this.email = entryData[3];
			this.zip = entryData[4];
		}
	}
	

	public String getName(){
		return this.name;
	}
	
	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getZip() {
		return zip;
	}

	public void setZip(String zip) {
		this.zip = zip;
	}

	@Override
	public String toString() {
		return  
				"Name:" + name + "\n" +
				"Address:" + address + "\n" +
				"Phone Number:" + phoneNumber + "\n"+
				"email:" + email + "\n" +
				"zip:" + zip;
	}
	
	public String getEntryAsOneLineString(){
		return this.name + "," + this.address + "," 
					+ this.phoneNumber + "," + this.email + ","
					+ this.zip;
	}
}
