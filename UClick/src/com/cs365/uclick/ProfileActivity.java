package com.cs365.uclick;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
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

public class ProfileActivity extends Activity implements OnClickListener,
		OnItemSelectedListener {
	private Spinner menu;
	private ExpandableListView quizHistory;
	private EditText fname, email, pass1, pass2, quizname;
	private Button edit, start;
	private List<String> quizList;
	private List<String> detailList;
	private Map<String, List<String>> quizCollections;
	public static Quiz quiz = new Quiz("q1117", 10);
	private boolean tag;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.profile);
		tag = false;
		this.menu = (Spinner) this.findViewById(R.id.mainmenu);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.Menu, R.layout.spinnerstyle);
		adapter.setDropDownViewResource(R.layout.dropdown);
		menu.setAdapter(adapter);

		fname = (EditText) this.findViewById(R.id.pro_fname);
		email = (EditText) this.findViewById(R.id.pro_email);
		pass1 = (EditText) this.findViewById(R.id.pro_pass1);
		pass2 = (EditText) this.findViewById(R.id.pro_pass2);
		quizname = (EditText) this.findViewById(R.id.pro_qname);

		edit = (Button) this.findViewById(R.id.probtn_edit);
		start = (Button) this.findViewById(R.id.probtn_start);

		edit.setOnClickListener(this);
		start.setOnClickListener(this);
		menu.setOnItemSelectedListener(this);

		loadDefaultValues();
		createGroupList();
		createCollection();
		quizHistory = (ExpandableListView) findViewById(R.id.pro_qzlist);
		final ExpandibleListAdapter expListAdapter = new ExpandibleListAdapter(
				this, quizList, quizCollections);
		quizHistory.setAdapter(expListAdapter);

		email.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

		pass1.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

		pass2.addTextChangedListener(new TextWatcher() {

			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub

			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub

			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});

	}

	private void createGroupList() {
		quizList = new ArrayList<String>();
		quizList.add("19831973");
		quizList.add("12938107");

	}

	private void createCollection() {
		String[] quiz1 = { "Date: 11/17/85", "Professor: Raheja",
				"Subject: CS555", "Description: Midterm", "Points: 20/20" };
		String[] quiz2 = { "Date: 11/17/85", "Professor: Raheja",
				"Subject: CS555", "Description: Midterm", "Points: 20/20" };

		quizCollections = new LinkedHashMap<String, List<String>>();

		for (String quiz : quizList) {
			if (quiz.equals("19831973")) {
				loadChild(quiz1);
			} else if (quiz.equals("12938107"))
				loadChild(quiz2);

			quizCollections.put(quiz, detailList);
		}
	}

	private void loadChild(String[] qdetails) {
		detailList = new ArrayList<String>();
		for (String detail : qdetails)
			detailList.add(detail);
	}

	private void loadDefaultValues() {
		this.fname.setText(LoginActivity.usr.getFirstName());
		this.email.setText(LoginActivity.usr.getEmail());
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == edit) {
			// update data
			// show dialog box

		} else if (v == start) {
			// check quiz id
			// start quiz
			Intent intent = new Intent(this, ClickerActivity.class);
			startActivity(intent);
		}

	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		// TODO Auto-generated method stub
		if (tag) {
			TextView v = (TextView) view;
			if (v.getText().equals("YOUR HISTORY")) {
				Intent intent = new Intent(this, HistoryActivity.class);
				startActivity(intent);

			} else if (v.getText().equals("SIGN OUT")) {
				Intent intent = new Intent(this, LoginActivity.class);
				startActivity(intent);
			}
		}
		tag = true;

	}

	@Override
	public void onNothingSelected(AdapterView<?> parent) {
		// TODO Auto-generated method stub

	}

}
