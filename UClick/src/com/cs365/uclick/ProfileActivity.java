package com.cs365.uclick;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends Activity implements OnItemSelectedListener {
	private Spinner menu;
	private ExpandableListView quizHistory;
	private TextView fname, lname, email, sid;

	private boolean tag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);

		this.menu = (Spinner) this.findViewById(R.id.mainmenu);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.Menu, R.layout.spinnerstyle);
		adapter.setDropDownViewResource(R.layout.dropdown);
		menu.setAdapter(adapter);
		tag = false;

		fname = (TextView) this.findViewById(R.id.pro_fname);
		fname.setText(MyData.usr.getFirstName());
		lname = (TextView) this.findViewById(R.id.pro_lname);
		lname.setText(MyData.usr.getLastName());
		email = (TextView) this.findViewById(R.id.pro_email);
		email.setText(MyData.usr.getEmail());
		sid = (TextView) this.findViewById(R.id.pro_sid);
		sid.setText(MyData.usr.getID());

		menu.setOnItemSelectedListener(this);

		createCollection();

	}

	private void createCollection() {
		final List<String> quizList;
		final Map<String, List<String>> quizCollections;
		quizCollections = new LinkedHashMap<String, List<String>>();
		quizList = new ArrayList<String>();

		Map<String, List<String>> collection = new LinkedHashMap<String, List<String>>();
		List<String> lists = new ArrayList<String>();

		ParseQuery<ParseObject> query = new ParseQuery<ParseObject>(
				"UserQuizList");
		query.whereEqualTo(MyData.QUIZ_USER, ParseUser.getCurrentUser());
		query.findInBackground(new FindCallback<ParseObject>() {

			@Override
			public void done(List<ParseObject> lists, ParseException e) {
				// TODO Auto-generated method stub
				if (lists != null) {
					for (int i = 0; i < lists.size(); i++) {
						final ParseObject currentList = lists.get(i);
						ParseObject currentQuiz = currentList
								.getParseObject(MyData.QUIZ_QUIZ);
						ParseQuery<ParseObject> quizes = new ParseQuery<ParseObject>(
								"Quiz");
						quizes.getInBackground(currentQuiz.getObjectId(),
								new GetCallback<ParseObject>() {

									@Override
									public void done(ParseObject q,
											ParseException e) {
										// TODO Auto-generated method stub
										final List<String> detailList = new ArrayList<String>();
										String date = "Date: "
												+ currentList.getCreatedAt()
														.toLocaleString();
										detailList.add(date);
										String description = "Description: "
												+ q.getString(MyData.DESCRIPTION);
										detailList.add(description);
										String result = "Points: "
												+ currentList
														.getInt(MyData.QUIZ_RESULT)
												+ "/" + q.getInt(MyData.QUIZ_N);
										detailList.add(result);
										ParseQuery<ParseObject> subjs = new ParseQuery<ParseObject>(
												"Subject");
										subjs.getInBackground(
												q.getParseObject(
														MyData.QUIZ_SUBJECT)
														.getObjectId(),
												new GetCallback<ParseObject>() {

													@Override
													public void done(
															ParseObject subj,
															ParseException e) {
														// TODO Auto-generated
														// method stub
														String subjName = "Subject: "
																+ subj.getString(MyData.SUBJ_NAME);
														detailList
																.add(subjName);
														ParseQuery<ParseObject> insts = new ParseQuery<ParseObject>(
																"Instructor");
														insts.getInBackground(
																subj.getParseObject(
																		"toughtBy")
																		.getObjectId(),
																new GetCallback<ParseObject>() {

																	@Override
																	public void done(
																			ParseObject inst,
																			ParseException e) {
																		// TODO
																		// Auto-generated
																		// method
																		// stub
																		String instructor = "Professor: "
																				+ inst.getString(MyData.USR_FIRST_NAME)
																				+ " "
																				+ inst.getString(MyData.USR_LAST_NAME);
																		detailList
																				.add(instructor);
																	}
																});
													}
												});
										quizCollections.put(
												q.getString(MyData.QUIZ_ID),
												detailList);
										quizList.add(q
												.getString(MyData.QUIZ_ID));
									}

								});

					}

				}

			}

		});
		quizHistory = (ExpandableListView) findViewById(R.id.pro_qzlist);
		ExpandibleListAdapter expListAdapter = new ExpandibleListAdapter(this,
				quizList, quizCollections);
		quizHistory.setAdapter(expListAdapter);

	}

	/*
	 * @Override public void onClick(View v) { // TODO Auto-generated method
	 * stub if (v == edit) { // update data // show dialog box
	 * 
	 * if (MyRegex.isValidEditText(fname.getText().toString()) &&
	 * MyRegex.isEmailValid(email.getText().toString()) &&
	 * MyRegex.isValidPassword(pass1.getText().toString()) &&
	 * MyRegex.isValidPassword(pass2.getText().toString())) {
	 * 
	 * if ((pass1.getText().toString()).equals(pass2.getText() .toString())) {
	 * 
	 * final AlertDialog.Builder dialog = new AlertDialog.Builder( this);
	 * 
	 * dialog.setTitle("Edit");
	 * dialog.setMessage("Are you sure you want save changes?");
	 * 
	 * dialog.setIcon(R.drawable.ic_launcher);
	 * 
	 * dialog.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
	 * 
	 * public void onClick(DialogInterface dialog, int which) {
	 * MyData.usr.setFirstName(fname.getText() .toString());
	 * MyData.usr.setEmail(email.getText() .toString()); new Thread(new
	 * Runnable() {
	 * 
	 * @Override public void run() { // TODO Auto-generated method stub
	 * threadMsg(MyData.usr.getFirstName()); }
	 * 
	 * private void threadMsg(String msg) {
	 * 
	 * if (!msg.equals(null) && !msg.equals("")) { Message msgObj = handler
	 * .obtainMessage(); Bundle b = new Bundle(); b.putString("message", msg);
	 * msgObj.setData(b); handler.sendMessage(msgObj); } }
	 * 
	 * // Define the Handler that receives // messages from the // thread // and
	 * update the progress private final Handler handler = new Handler() {
	 * 
	 * public void handleMessage( Message msg) {
	 * 
	 * String aResponse = msg .getData().getString( "message");
	 * 
	 * if ((null != aResponse)) {
	 * 
	 * // ALERT MESSAGE usrname.setText(MyData.usr .getFirstName()); } else {
	 * 
	 * // ALERT MESSAGE Toast.makeText( getBaseContext(),
	 * "Not Got Response From Server.", Toast.LENGTH_SHORT) .show(); }
	 * 
	 * } }; }).start();
	 * 
	 * dialog.dismiss();
	 * 
	 * } }); dialog.setNegativeButton("No", new
	 * DialogInterface.OnClickListener() {
	 * 
	 * public void onClick(DialogInterface dialog, int which) {
	 * dialog.dismiss();
	 * 
	 * } });
	 * 
	 * dialog.show();
	 * 
	 * } else { Toast.makeText(this, "Passwords don't match!",
	 * Toast.LENGTH_SHORT).show(); }
	 * 
	 * }
	 * 
	 * } else if (v == start) { // check quiz id // start quiz if
	 * (quizname.getText().toString().equals(MyData.quiz.getId())) { Intent
	 * intent = new Intent(this, ClickerActivity.class); startActivity(intent);
	 * } else {
	 * 
	 * AlertDialog.Builder dialog = new AlertDialog.Builder(this);
	 * 
	 * dialog.setTitle("ERROR"); dialog.setMessage("Enter a valid Quiz ID");
	 * 
	 * dialog.setIcon(R.drawable.ic_launcher);
	 * 
	 * dialog.setNeutralButton("OK", new DialogInterface.OnClickListener() {
	 * 
	 * public void onClick(DialogInterface dialog, int which) {
	 * dialog.dismiss();
	 * 
	 * } });
	 * 
	 * dialog.show(); }
	 * 
	 * }
	 * 
	 * }
	 */

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		String menuItem = ((TextView) view).getText().toString();
		if (tag) {
			if (menuItem.equalsIgnoreCase("quiz")) {
				Intent intent = new Intent(this, QuizActivity.class);
				startActivity(intent);
			} else if (menuItem.equalsIgnoreCase("sign out")) {
				final Intent intent = new Intent(this, LoginActivity.class);

				AlertDialog.Builder dialog = new AlertDialog.Builder(this);

				dialog.setTitle("Log Out");
				dialog.setMessage("Are you sure you want to log out?");

				dialog.setIcon(R.drawable.ic_launcher);

				dialog.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								ParseUser.logOut();
								startActivity(intent);
							}
						});
				dialog.setNegativeButton("No",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();

							}
						});

				dialog.show();
			}

		}
		tag = true;

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}

}
