package com.cs365.uclick;

public class User {

	private String firstName, lastName, email;
	
	public User() {
		firstName = null;
		lastName = null;
		email = null;
	}
	
	public User(String firstname, String lastname, String e) {
		this.firstName = firstname;
		this.lastName = lastname;
		this.email = e;
	}

	public void setFirstName(String name) {
		this.firstName = name;
	}

	public void setLastName(String name) {
		this.lastName = name;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getFirstName() {
		return this.firstName;
	}

	public String getLastName() {
		return this.lastName;
	}

	public String getEmail() {
		return this.email;
	}

}
