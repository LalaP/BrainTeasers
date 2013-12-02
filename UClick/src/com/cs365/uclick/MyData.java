package com.cs365.uclick;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

import android.util.Log;

import com.cs365.uclick.data.Instructor;
import com.cs365.uclick.data.Quiz;
import com.cs365.uclick.data.Subject;
import com.cs365.uclick.data.User;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

public class MyData {

	public static User usr = null;
	public static Quiz quiz = null;
	public static final String USR_FIRST_NAME = "firstName";
	public static final String USR_LAST_NAME = "lastName";
	public static final String USR_PASSWORD = "password";
	public static final String USR_EMAIL = "email";
	public static final String USR_ID = "studentID";
	public static final String INST_ID = "instructorID";
	public static final String DESCRIPTION = "desc";
	public static final String SUBJ_ID = "subjectID";
	public static final String SUBJ_NAME = "name";
	public static final String QUIZ_ID = "quizID";
	public static final String QUIZ_N = "totalQuestions";
	public static final String QUIZ_SOLUTIONS = "solutions";
	public static final String QUIZ_SUBJECT = "subject";
	public static final String QUIZ_RESULT = "points";
	public static final String QUIZ_USER = "user";
	public static final String QUIZ_QUIZ = "quiz";

	public static void addInstructor(Instructor inst) {
		ParseObject instructor = new ParseObject("Instructor");
		instructor.put(USR_FIRST_NAME, inst.getFirstName());
		instructor.put(USR_LAST_NAME, inst.getLastName());
		instructor.put(INST_ID, inst.getId());
		instructor.saveInBackground();
	}

	public static void addSubject(Subject subj) {
		ParseObject subject = new ParseObject("Subject");
		subject.put(SUBJ_ID, subj.getId());
		subject.put(SUBJ_NAME, subj.getName());
		subject.saveInBackground();

	}

	public static void addInstructorToSubject(final String instID, String subjID) {
		ParseQuery<ParseObject> quiery = new ParseQuery<ParseObject>("Subject");
		quiery.whereEqualTo(MyData.SUBJ_ID, subjID);
		quiery.getFirstInBackground(new GetCallback<ParseObject>() {

			@Override
			public void done(final ParseObject subj, ParseException e) {
				// TODO Auto-generated method stub
				ParseQuery<ParseObject> quiry2 = new ParseQuery<ParseObject>(
						"Instructor");
				quiry2.whereEqualTo(MyData.INST_ID, instID);
				quiry2.getFirstInBackground(new GetCallback<ParseObject>() {

					@Override
					public void done(ParseObject inst, ParseException e) {
						// TODO Auto-generated method stub
						subj.put("toughtBy", inst);
						subj.saveInBackground();
					}
				});
			}
		});

	}

	public static void addQuiz(Quiz q) {
		ParseObject quiz = new ParseObject("Quiz");
		quiz.put(QUIZ_ID, q.getId());
		quiz.put(QUIZ_N, q.getQuestions());
		quiz.put(DESCRIPTION, q.getDesc());
		// quiz.put(QUIZ_SOLUTIONS, q.getAnswers());
		quiz.saveInBackground();
	}

	public static void addSolutions(String id, final ArrayList<String> solutions) {
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Quiz");
		query.whereEqualTo(QUIZ_ID, id);
		query.getFirstInBackground(new GetCallback<ParseObject>() {

			@Override
			public void done(ParseObject quiz, ParseException e) {
				// TODO Auto-generated method stub
				quiz.add(QUIZ_SOLUTIONS, solutions);
				quiz.saveInBackground();
			}
		});
	}

	public static void addSubjectToQuiz(String quizID, final String subjID) {
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Quiz");
		query.whereEqualTo(QUIZ_ID, quizID);
		query.getFirstInBackground(new GetCallback<ParseObject>() {

			@Override
			public void done(final ParseObject quiz, ParseException e) {
				// TODO Auto-generated method stub
				ParseQuery<ParseObject> query2 = new ParseQuery<ParseObject>(
						"Subject");
				query2.whereEqualTo(SUBJ_ID, subjID);
				query2.getFirstInBackground(new GetCallback<ParseObject>() {

					@Override
					public void done(ParseObject subj, ParseException e) {
						// TODO Auto-generated method stub
						quiz.put(QUIZ_SUBJECT, subj);
						quiz.saveInBackground();
					}
				});

			}
		});

	}

	public static void addQuiznResult(final String quizID, final int result) {
		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>("Quiz");
		query.whereEqualTo(QUIZ_ID, quizID);
		query.getFirstInBackground(new GetCallback<ParseObject>() {
			@Override
			public void done(final ParseObject q, ParseException e) {
				// TODO Auto-generated method stub
				final ParseUser currentUser = ParseUser.getCurrentUser();
				ParseQuery<ParseObject> query2 = new ParseQuery<ParseObject>(
						"UserQuizList");

				query2.whereEqualTo(QUIZ_USER, currentUser);
				query2.findInBackground(new FindCallback<ParseObject>() {
					@Override
					public void done(List<ParseObject> list, ParseException e) {
						// TODO Auto-generated method stub
						if (list != null) {
							boolean exists = false;
							for (int i = 0; i < list.size(); i++) {
								ParseObject currentQuiz = (ParseObject) list
										.get(i).getParseObject(QUIZ_QUIZ);

								if (q.getObjectId().equalsIgnoreCase(
										currentQuiz.getObjectId())) {
									Log.d("aaaaaaaaaaaaaaa", "exists");
									exists = true;
									break;
								}
							}
							if (!exists) {
								ParseObject usrQuizList = new ParseObject(
										"UserQuizList");
								usrQuizList.put(QUIZ_USER, currentUser);
								usrQuizList.put(QUIZ_QUIZ, q);
								usrQuizList.put(QUIZ_RESULT, result);
								usrQuizList.saveInBackground();
							}
						} else {

							ParseObject usrQuizList = new ParseObject(
									"UserQuizList");
							usrQuizList.put(QUIZ_USER, currentUser);
							usrQuizList.put(QUIZ_QUIZ, q);
							usrQuizList.put(QUIZ_RESULT, result);
							usrQuizList.saveInBackground();

						}
					}
				});

			}

		});
	}

}
