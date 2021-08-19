package main;

public class UserAuth {

	private String userId;
	private String password;

	public UserAuth(String userId, String password) {
		this.userId = userId;
		this.password = password;
	}

	public String getuserId() {
		return userId;
	}

	public String getPassword() {
		return password;
	}

	public void setuserId(String userId) {
		this.userId = userId;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
