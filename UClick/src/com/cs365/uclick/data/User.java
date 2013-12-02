package com.cs365.uclick.data;

import java.util.ArrayList;

public class User {

	private String firstName, lastName, email, sid, password;
	private ArrayList<Quiz> quizes;

	public User() {
		firstName = null;
		lastName = null;
		email = null;
		sid = null;
	}

	public User(String firstname, String lastname, String e, String id,
			String password) {
		this.firstName = firstname;
		this.lastName = lastname;
		this.email = e;
		this.sid = id;
		this.password = password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return this.password;
	}

	public void setID(String id) {
		this.sid = id;
	}

	public String getID() {
		return sid;
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
