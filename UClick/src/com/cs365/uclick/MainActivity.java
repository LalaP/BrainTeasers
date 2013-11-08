package com.cs365.uclick;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.cs365.uclick.RegisterActivity;

public class MainActivity extends Activity implements OnClickListener {

	private Button signup, signin;
	private EditText email, pass;
	public static User usr = new User("Alexis", "Lkhagvadorj", "alexis@hotmail.com");

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.login);
<<<<<<< HEAD
=======
		
		this.email = (EditText) this.findViewById(R.id.log_fname);
		this.pass = (EditText) this.findViewById(R.id.lname);
		
>>>>>>> 5e90aee4d2a8e0d19aabc4434ffea6c7bed51f9d
		this.signup = (Button) this.findViewById(R.id.signup_main);
		signup.setOnClickListener(this);
		this.signin = (Button) this.findViewById(R.id.signin_main);
		signin.setOnClickListener(this);

<<<<<<< HEAD
		this.email = (EditText) this.findViewById(R.id.fname);
		this.pass = (EditText) this.findViewById(R.id.lname);
=======
		
>>>>>>> 5e90aee4d2a8e0d19aabc4434ffea6c7bed51f9d
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		final Context context = this;
		if (v == signup) {

			Intent intent = new Intent(context, RegisterActivity.class);
			startActivity(intent);

		}

		if (v == signin) {

			if (this.email.getText().toString().equals(usr.getEmail())
					&& this.pass.getText().toString().equals("tuya")) {
				Intent intent = new Intent(context, QuizActivity.class);
				startActivity(intent);
			}
			else {
				Toast.makeText(context, "Wrong Email or password", Toast.LENGTH_SHORT).show();
			}
		}

	}

}
