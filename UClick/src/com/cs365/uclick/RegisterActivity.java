package com.cs365.uclick;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends Activity implements OnClickListener {
	private Button goback, finish;
	private EditText fname, lname, email, pass1, pass2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);

		this.fname = (EditText) this.findViewById(R.id.log_fname);
		this.lname = (EditText) this.findViewById(R.id.lname);
		this.email = (EditText) this.findViewById(R.id.usr_email);
		this.pass1 = (EditText) this.findViewById(R.id.usr_pass1);
		this.pass2 = (EditText) this.findViewById(R.id.usr_pass2);

		this.goback = (Button) this.findViewById(R.id.reg_goback);
		goback.setOnClickListener(this);
		this.finish = (Button) this.findViewById(R.id.reg_finish);
		finish.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == goback) {
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
		}

		if (v == finish) {
			if (!fname.getText().toString().equals("")
					&& !lname.getText().toString().equals("")
					&& !email.getText().toString().equals("")
					&& pass1.getText().toString()
							.equals(pass2.getText().toString())) {

				MainActivity.usr.setFirstName(fname.getText().toString());
				MainActivity.usr.setLastName(lname.getText().toString());
				MainActivity.usr.setEmail(email.getText().toString());

				Intent intent = new Intent(this, QuizActivity.class);
				startActivity(intent);
			}

		}
	}
}