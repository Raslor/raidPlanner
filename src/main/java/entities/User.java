/**
 * 
 */
package entities;

import org.bson.Document;

public class User {

	private int userId;
	private String username;
	private String firstName;
	private String lastName;
	private String language;

	public User(int userId, String username, String firstName, String lastName, String language) {
		this.userId = userId;
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.language = language;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Document toDocument() {

		Document user = new Document("_id", this.getUserId());

		user.put("userName", this.getUsername());
		user.put("firstName", this.getFirstName());
		user.put("lastName", this.getLastName());
		user.put("language", this.getLanguage());

		return user;
	}

}
