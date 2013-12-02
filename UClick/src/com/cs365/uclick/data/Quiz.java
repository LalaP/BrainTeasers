package com.cs365.uclick.data;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Quiz {

	private String id;
	private int questions;
	private ArrayList<String> solutions;
	private String desc;

	public Quiz(String id, int q, String desc, ArrayList<String> sol) {
		this.id = id;
		this.questions = q;
		this.solutions = sol;
		this.desc = desc;
	}

	public Quiz() {
		// TODO Auto-generated constructor stub
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public void setId(String id) {
		this.id = id;

	}

	public void setQuestions(int total) {
		this.questions = total;
		solutions = new ArrayList<String>();
	}

	public String getId() {
		return this.id;
	}

	public int getQuestions() {
		return this.questions;
	}

	public ArrayList<String> getAnswers() {
		return this.solutions;
	}

	public void setAnswers(ArrayList<String> solutions) {
		this.solutions = new ArrayList<String>();
		for (int i = 0; i < solutions.size(); i++) {
			this.solutions.add(solutions.get(i));
		}
	}

}
