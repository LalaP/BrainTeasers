package com.cs365.uclick;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.text.Editable;
import android.text.TextWatcher;
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
	public static User usr = new User("Alexis", "Lkhagvadorj",
			"alexis@hotmail.com");

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);

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
			if(!(this.email.getText().toString().contains("@"))){
				Toast.makeText(this, "Wrong email format", Toast.LENGTH_SHORT)
				.show();
			}
			if(this.pass.getText().toString().length() < 6){
				Toast.makeText(this, "Password too short", Toast.LENGTH_SHORT)
				.show();
			}
			
			if (this.email.getText().toString().equals(usr.getEmail())
					&& this.pass.getText().toString().equals("tuya")) {
				Intent intent = new Intent(this, ProfileActivity.class);
				startActivity(intent);
			} else {
				Toast.makeText(this, "Password and email don't match", Toast.LENGTH_SHORT)
						.show();
			}

			if (this.email.getText().toString().equals(usr.getEmail())
					&& this.pass.getText().toString().equals("tuya")) {
				Intent intent = new Intent(this, ProfileActivity.class);
				startActivity(intent);
			} 
		} else if (v == forgotpass) {
			Intent intent = new Intent(this, PasswordActivity.class);
			startActivity(intent);
		}

	}
}
