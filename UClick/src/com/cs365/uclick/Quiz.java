package com.cs365.uclick;

public class Quiz {

	private String id;
	//private String pass;
	private int questions;
	private char[] answers;

	public Quiz(String id) {
		this.id = id;
		//this.questions = q;
	}

	public Quiz() {
		this.id = null;
		//this.pass = null;
		this.questions = 0;
		answers = new char[0];
	}

	public void setId(String id) {
		this.id = id;

	}

//	public void setPass(String pass) {
//		this.pass = pass;
//	}

	public void setQuestions(int total) {
		this.questions = total;
		answers = new char[questions];
	}

	public String getId() {
		return this.id;
	}

	public int getQuestions() {
		return this.questions;
	}

	public char[] getAnswers() {
		return this.answers;
	}
}
