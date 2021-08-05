package main;

public class Student extends User {

	public Student(String name, String emailId) {
		super(name, emailId,Role.Student);
			}

	public Student(String name) {
		super(name, Role.Student); 
	}
	
	
}
