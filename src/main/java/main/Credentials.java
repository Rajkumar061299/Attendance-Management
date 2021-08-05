package main;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Credentials {

	private static Credentials credentialsInstance;

	Map<UserAuth, User> userCredentials;

	private Credentials() {
		userCredentials = new HashMap<>();
		userCredentials.put(new UserAuth("admin", "admin"), new Admin());
		userCredentials.put(new UserAuth("s1", "s1"), new Student("Student 1"));
		userCredentials.put(new UserAuth("s2", "s2"), new Student("Student 2"));
		userCredentials.put(new UserAuth("s3", "s3"), new Student("Student 3"));
		userCredentials.put(new UserAuth("f1", "f1"), new Faculty("Faculty 1"));
	}

	public static Credentials getInstance() {

		if (credentialsInstance == null)
			credentialsInstance = new Credentials();

		return credentialsInstance;
	}
	
	// admin can add the new user
	public void addUser(UserAuth userAuth, User user) {
		userCredentials.put(userAuth, user);
	}

	public Map<UserAuth, User> getUserCredentials() {

		return userCredentials;
	}

	// validate the user 
	public User validate(String userId, String password) {
		User user = null;
		for (Map.Entry<UserAuth, User> entry : userCredentials.entrySet()) {

			if (entry.getKey().getuserId().equals(userId) && entry.getKey().getPassword().equals(password))
				user = entry.getValue();
		}
		return user;
	}

	/*
	 * public List<User> getUsers(Role role) { return
	 * userCredentials.values().stream().filter(user -> user.getRole() ==
	 * Role.Student) .collect(Collectors.toList()); }
	 * 
	 * public User getUser(String Id) {
	 * 
	 * return userCredentials.get( userCredentials.keySet().stream().filter(userAuth
	 * -> userAuth.getuserId().equals(Id)). findFirst().get());
	 * 
	 * }
	 */
}
