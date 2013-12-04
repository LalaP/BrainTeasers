package com.cs365.uclick;

import com.cs365.uclick.data.User;
import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SignUpCallback;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.os.ParcelableCompat;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class RegisterActivity extends Activity implements OnClickListener {
	private Button back, register, register2;
	private EditText fname, lname, email, sid, pass1, pass2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);

		this.fname = (EditText) this.findViewById(R.id.reg_fname);
		this.lname = (EditText) this.findViewById(R.id.reg_lname);
		this.email = (EditText) this.findViewById(R.id.reg_email);
		this.sid = (EditText) this.findViewById(R.id.reg_sid);
		this.pass1 = (EditText) this.findViewById(R.id.reg_pass1);
		this.pass2 = (EditText) this.findViewById(R.id.reg_pass2);

		this.back = (Button) this.findViewById(R.id.regbtn_back);
		this.register = (Button) this.findViewById(R.id.regbtn_reg);
		this.register2 = (Button) this.findViewById(R.id.regbtn_reg2);

		back.setOnClickListener(this);
		register.setOnClickListener(this);
		register2.setOnClickListener(this);

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
//				if (!MyRegex.isValidPassword(s.toString()))
//					pass1.setError("");
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
//				if (!MyRegex.isValidPassword(s.toString()))
//					pass2.setError("");
			}
		});

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == back) {
			Intent intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
		}

		if (v == register2 || v == register) {
			final String sfname = fname.getText().toString();
			final String slname = lname.getText().toString();
			final String semail = email.getText().toString();
			final String ssid = sid.getText().toString();
			final String spass1 = pass1.getText().toString();
			String spass2 = pass2.getText().toString();
			if (MyRegex.isValidEditText(sfname)
					&& MyRegex.isValidEditText(slname)
					&& MyRegex.isEmailValid(semail)
					&& MyRegex.isValidEditText(ssid)
					&& MyRegex.isValidPassword(spass1)
					&& MyRegex.isValidPassword(spass2)) {

				if (spass1.equals(spass2)) {

					final Intent intent = new Intent(this,
							ProfileActivity.class);

					ParseUser user = new ParseUser();
					user.put(MyData.USR_FIRST_NAME, sfname);
					user.put(MyData.USR_LAST_NAME, slname);
					user.put(MyData.USR_ID, ssid);
					user.setUsername(semail);
					user.setPassword(spass1);

					user.signUpInBackground(new SignUpCallback() {

						@Override
						public void done(ParseException e) {
							// TODO Auto-generated method stub
							if (e == null) {
								MyData.usr = new User(sfname, slname, semail,
										ssid, spass1);
								startActivity(intent);
							} else {
								email.setText("");
							}

						}
					});

				} else {
					pass1.setText("");
					pass2.setText("");
					
				}

			}else if  (!(spass1.equals(spass2))){Toast.makeText(this, "Passwords don't match!",
					Toast.LENGTH_SHORT).show();
				
			}else if  (!MyRegex.isValidPassword(spass1)){Toast.makeText(this, "Passwords is invalid.(6-20 characters + digits",
					Toast.LENGTH_SHORT).show();
				
			}
			else if(!(MyRegex.isEmailValid(semail))){Toast.makeText(this, "Email is invalid!",
					Toast.LENGTH_SHORT).show();}
			
			else{
				AlertDialog.Builder alertDialog1 = new AlertDialog.Builder(this);

				alertDialog1.setTitle("Sign up failed");
				alertDialog1.setMessage("Please enter all fields correctly");

				alertDialog1.setIcon(R.drawable.ic_launcher);

				alertDialog1.setNeutralButton("OK",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog, int which) {
								//startActivity(intent);
							}
						});

				alertDialog1.show();
			}

		}
	}
}