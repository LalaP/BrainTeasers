package com.cs365.uclick;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class QuizActivity extends Activity implements OnClickListener {
	private Button startquiz, back;
	private TextView welcome;
	private EditText id;
	public static Quiz quiz = new Quiz("q1117");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quiz);
		this.startquiz = (Button) this.findViewById(R.id.nextq);
		startquiz.setOnClickListener(this);

		this.back = (Button) this.findViewById(R.id.E);
		back.setOnClickListener(this);

		this.welcome = (TextView) this.findViewById(R.id.txt_welcome);
		this.welcome.setText("Welcome " + MainActivity.usr.getFirstName());

		this.id = (EditText) this.findViewById(R.id.fname);
		//this.pass = (EditText) this.findViewById(R.id.lname);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == startquiz) {

			if (this.id.getText().toString().equals(quiz.getId())) {
				Intent intent = new Intent(this, ClickerActivity.class);
				startActivity(intent);
			}
			else{
				Toast.makeText(getBaseContext(), "Quiz Id not Valid", Toast.LENGTH_SHORT).show();
			}

		}

		else if (v == back) {
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
		}
	}

}
