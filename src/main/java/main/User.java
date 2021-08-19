package main;

public class User {

	private String name;
	private String emailId;
	private Role role;

	public String getName() {
		return name;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	public User(String name, String emailId, Role role) {
		this.name = name;
		this.emailId = emailId;
		this.role = role;
	}

	public User(String name, Role role) {
		this.name = name;
		this.role = role;

	}
	
	public User(Role role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return "Student Name= " + name;
	}

}
