package com.cs365.uclick.data;

import java.util.ArrayList;

public class Instructor {
	private String id;
	private String firstName;
	private String lastName;
	private ArrayList<Subject> subjects;

	public String getId() {
		return id;
	}

	public Instructor(String id, String fname, String lname) {
		this.id = id;
		this.firstName = fname;
		this.lastName = lname;
		this.subjects = new ArrayList<Subject>();
	}

	public void addSubject(Subject subj) {
		// only if subj doesn't exist
		subjects.add(subj);
	}
	
	public ArrayList<Subject> getSubjectList() {
		return this.subjects;		
	}

	public void setId(String id) {
		this.id = id;
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

}
