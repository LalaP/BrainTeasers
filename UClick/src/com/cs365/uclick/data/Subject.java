package com.cs365.uclick.data;

import java.util.ArrayList;

public class Subject {

	private String id;
	private String name;
	private ArrayList<Quiz> quizes;

	public Subject(String id, String name) {
		this.id = id;
		this.name = name;
		this.quizes = new ArrayList<Quiz>();

	}

	public void addQuiz(Quiz quiz) {
		// only if quiz doesn't exist
		this.quizes.add(quiz);
	}

	public ArrayList<Quiz> getQuizList() {
		return this.quizes;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

}
