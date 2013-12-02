package com.cs365.uclick;

import java.util.ArrayList;

import com.parse.GetCallback;
import com.parse.LogInCallback;
import com.parse.Parse;
import com.parse.ParseAnalytics;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;
import com.parse.ParseUser;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cs365.uclick.RegisterActivity;
import com.cs365.uclick.data.Instructor;
import com.cs365.uclick.data.Quiz;
import com.cs365.uclick.data.Subject;
import com.cs365.uclick.data.User;

public class LoginActivity extends Activity implements OnClickListener {

	private Button signup, signin, forgotpass;
	private EditText email, pass;
	static ParseObject subject;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
		Parse.initialize(this, "LNwKxfw8SCsqlhkQfGmY1Mt3Puj7pppNWTD5TObC",
				"eEGIDAFgj2sh9ukvNJnIXDwooqWcHfjb1tdqgwz6");

		email = (EditText) this.findViewById(R.id.log_email);
		pass = (EditText) this.findViewById(R.id.log_pass);

		signin = (Button) this.findViewById(R.id.logbtn_log);
		signup = (Button) this.findViewById(R.id.logbtn_reg);
		forgotpass = (Button) this.findViewById(R.id.logbtn_pass);

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
				if (!MyRegex.isEmailValid(s.toString())) {
					email.setError("");
				}
			}
		});
		pass.addTextChangedListener(new TextWatcher() {

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
				if (!MyRegex.isValidPassword(s.toString())) {
					pass.setError("");
				}

			}
		});
		signin.setOnClickListener(this);
		signup.setOnClickListener(this);
		forgotpass.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		// getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == signup) {

			Intent intent = new Intent(this, RegisterActivity.class);
			startActivity(intent);

		} else if (v == signin) {
			String semail = this.email.getText().toString();
			String spass = this.pass.getText().toString();
			if (MyRegex.isEmailValid(semail) && MyRegex.isValidPassword(spass)) {
				final Intent intent = new Intent(this, ProfileActivity.class);

				ParseUser.logInInBackground(semail, spass, new LogInCallback() {

					@Override
					public void done(ParseUser obj, ParseException e) {
						// TODO Auto-generated method stub
						if (obj != null) {
							MyData.usr = new User();
							MyData.usr.setEmail(obj.getUsername());
							MyData.usr.setFirstName(obj
									.getString(MyData.USR_FIRST_NAME));
							MyData.usr.setLastName(obj
									.getString(MyData.USR_LAST_NAME));
							MyData.usr.setID(obj.getString(MyData.USR_ID));
							startActivity(intent);
						} else {
							email.setText("");
							pass.setText("");
						}

					}
				});

			}

		} else if (v == forgotpass) {
			Intent intent = new Intent(this, PasswordActivity.class);
			startActivity(intent);
		}

	}
}