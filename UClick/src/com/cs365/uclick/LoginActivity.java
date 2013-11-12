package com.cs365.uclick;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
<<<<<<< HEAD
import android.text.Editable;
import android.text.TextWatcher;
=======
>>>>>>> origin/uclick1.1
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cs365.uclick.RegisterActivity;

public class LoginActivity extends Activity implements OnClickListener {

	private Button signup, signin, forgotpass;
	private EditText email, pass;
<<<<<<< HEAD
=======
	public static User usr = new User("Alexis", "Lkhagvadorj",
			"alexis@hotmail.com");
>>>>>>> origin/uclick1.1

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

		email = (EditText) this.findViewById(R.id.log_email);
		pass = (EditText) this.findViewById(R.id.log_pass);

		signin = (Button) this.findViewById(R.id.logbtn_log);
		signup = (Button) this.findViewById(R.id.logbtn_reg);
		forgotpass = (Button) this.findViewById(R.id.logbtn_pass);

<<<<<<< HEAD
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
					email.setError("Invalid Email Format");
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
					pass.setError("Must contain [0-9], [A-Z], [a-z], 6<length<20");
				}

			}
		});
=======
>>>>>>> origin/uclick1.1
		signin.setOnClickListener(this);
		signup.setOnClickListener(this);
		forgotpass.setOnClickListener(this);

	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
<<<<<<< HEAD
		// getMenuInflater().inflate(R.menu.main, menu);
=======
		//getMenuInflater().inflate(R.menu.main, menu);
>>>>>>> origin/uclick1.1
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == signup) {

			Intent intent = new Intent(this, RegisterActivity.class);
			startActivity(intent);

		} else if (v == signin) {

<<<<<<< HEAD
			if (this.email.getText().toString().equals(MyData.usr.getEmail())
=======
			if (this.email.getText().toString().equals(usr.getEmail())
>>>>>>> origin/uclick1.1
					&& this.pass.getText().toString().equals("tuya")) {
				Intent intent = new Intent(this, ProfileActivity.class);
				startActivity(intent);
			} else {
<<<<<<< HEAD
				if (MyRegex.isEmailValid(email.getText().toString())
						&& MyRegex.isValidPassword(pass.getText().toString())) {
					Toast.makeText(this, "Wrong Email or Password!",
							Toast.LENGTH_SHORT).show();
				}

=======
				Toast.makeText(this, "Wrong password!", Toast.LENGTH_SHORT)
						.show();
>>>>>>> origin/uclick1.1
			}
		} else if (v == forgotpass) {
			Intent intent = new Intent(this, PasswordActivity.class);
			startActivity(intent);
		}

	}
<<<<<<< HEAD
}
=======
}
>>>>>>> origin/uclick1.1
