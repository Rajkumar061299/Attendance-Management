package main;

public class Faculty extends User {

	public Faculty(String name, String emailId) {
		super(name, emailId, Role.Faculty);
		
	}

	public Faculty(String name) {
		super(name, Role.Faculty); // TODO
	}

}
