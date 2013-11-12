package com.cs365.uclick;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import android.app.Activity;
<<<<<<< HEAD
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
=======
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
>>>>>>> origin/uclick1.1
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ExpandableListView;
import android.widget.Spinner;
<<<<<<< HEAD
import android.widget.TextView;
import android.widget.Toast;

public class HistoryActivity extends Activity implements OnClickListener,
		OnItemSelectedListener {
=======

public class HistoryActivity extends Activity implements OnClickListener {
>>>>>>> origin/uclick1.1
	private Spinner menu;
	List<String> quizList;
	List<String> detailList;
	Map<String, List<String>> quizCollections;
	ExpandableListView quizView;
	private EditText searchbox;
	private Button search;
<<<<<<< HEAD
	private boolean tag;
=======
>>>>>>> origin/uclick1.1

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.history);

		this.menu = (Spinner) this.findViewById(R.id.mainmenu);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.Menu, R.layout.spinnerstyle);
		adapter.setDropDownViewResource(R.layout.dropdown);
		menu.setAdapter(adapter);
<<<<<<< HEAD
		menu.setOnItemSelectedListener(this);
		tag = false;
=======
>>>>>>> origin/uclick1.1

		search = (Button) this.findViewById(R.id.histbtn_search);
		searchbox = (EditText) this.findViewById(R.id.hist_search);

		search.setOnClickListener(this);

		createGroupList();
		createCollection();
		System.out.println(quizList.size());
		System.out.println(detailList.size());
		quizView = (ExpandableListView) findViewById(R.id.pro_qzlist);
		final ExpandibleListAdapter expListAdapter = new ExpandibleListAdapter(
				this, quizList, quizCollections);
		quizView.setAdapter(expListAdapter);
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
<<<<<<< HEAD
		if (v == search) {

		}

	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		// TODO Auto-generated method stub
		TextView v = (TextView) view;
		Toast.makeText(this, v.getText().toString(), Toast.LENGTH_SHORT).show();

		if (tag) {
			if (v.getText().equals("YOUR ACCOUNT")) {
				Intent intent = new Intent(this, ProfileActivity.class);
				startActivity(intent);

			} else if (v.getText().equals("SIGN OUT")) {
				Intent intent = new Intent(this, LoginActivity.class);
				startActivity(intent);
			}
		}
		tag = true;

	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

}
=======
		if(v== search) {
			
		}
		
	}

}
>>>>>>> origin/uclick1.1
