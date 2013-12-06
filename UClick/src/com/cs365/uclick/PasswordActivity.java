package com.cs365.uclick;

import android.app.Activity;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.RequestPasswordResetCallback;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class PasswordActivity extends Activity implements OnClickListener {
	private EditText email;
	private Button back, send1, send2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
//		super.onCreate(savedInstanceState);
//		setContentView(R.layout.password);
//
//		email = (EditText) this.findViewById(R.id.pass_email);
//		back = (Button) this.findViewById(R.id.passbtn_back);
//		send1 = (Button) this.findViewById(R.id.passbtn_send);
//		send2 = (Button) this.findViewById(R.id.passbtn_send2);
		Parse.initialize(this, "LNwKxfw8SCsqlhkQfGmY1Mt3Puj7pppNWTD5TObC",
				"eEGIDAFgj2sh9ukvNJnIXDwooqWcHfjb1tdqgwz6");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.password);

		email = (EditText) this.findViewById(R.id.pass_email);
		back = (Button) this.findViewById(R.id.passbtn_back);
		send1 = (Button) this.findViewById(R.id.passbtn_send);
		send2 = (Button) this.findViewById(R.id.passbtn_send2);

		back.setOnClickListener(this);
		send1.setOnClickListener(this);
		send2.setOnClickListener(this);

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
//				if (!MyRegex.isEmailValid(s.toString()))
//					email.setError("");

			}
		});
		
	}
	// Sends an email password reset request for the user By:LalaP
	public void forgotPassword(String email)
	{
		ParseUser.requestPasswordResetInBackground(email, new RequestPasswordResetCallback(){
			public void done(ParseException e)
			{
				if (e == null) 
				{
					Toast.makeText(getApplicationContext(), "Successfully sent link to your email for reset Password", Toast.LENGTH_LONG).show();
				}
				else 
				{
					Toast.makeText(getApplicationContext(), "Failed to sent link to your email for reset Password. Email does not exist in Database.", Toast.LENGTH_LONG).show();
				}
			}	
		});//end 
	}
	private void wrongEmailMsg()
	{
		AlertDialog.Builder alertDialog1 = new AlertDialog.Builder(this);

		alertDialog1.setTitle("Alert Dialog");

		alertDialog1.setMessage("Invalid email address. Try again!");

		alertDialog1.setIcon(R.drawable.ic_launcher);

		alertDialog1.setNeutralButton("OK",
				new DialogInterface.OnClickListener() {

					public void onClick(DialogInterface dialog,
							int which) {

					}
				});

		alertDialog1.show();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == back) {
			Intent intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
		}

		else if (v == send1 || v == send2) {
			// send password to email

			if (MyRegex.isEmailValid(email.getText().toString())) {
				forgotPassword(email.toString());

//				AlertDialog.Builder alertDialog1 = new AlertDialog.Builder(this);
//
//				alertDialog1.setTitle("Alert Dialog");
//
//				alertDialog1.setMessage("Password sent to email");
//
//				alertDialog1.setIcon(R.drawable.ic_launcher);
//
//				alertDialog1.setNeutralButton("OK",
//						new DialogInterface.OnClickListener() {
//
//							public void onClick(DialogInterface dialog,
//									int which) {
//
//							}
//						});
//
//				alertDialog1.show();

			}else{
				wrongEmailMsg();
			}

		}

	}
}
