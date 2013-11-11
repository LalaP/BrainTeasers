package com.cs365.uclick;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RegisterActivity extends Activity implements OnClickListener {
	private Button back, register, register2;
	private EditText fname, lname, email, pass1, pass2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);

		this.fname = (EditText) this.findViewById(R.id.reg_fname);
		this.lname = (EditText) this.findViewById(R.id.reg_lname);
		this.email = (EditText) this.findViewById(R.id.reg_email);
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

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == back) {
			Intent intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
		}

		if (v == register2 || v == register) {
			if (!fname.getText().toString().equals("")
					&& !lname.getText().toString().equals("")
					&& !email.getText().toString().equals("")
					&& pass1.getText().toString()
							.equals(pass2.getText().toString())) {

				LoginActivity.usr.setFirstName(fname.getText().toString());
				LoginActivity.usr.setLastName(lname.getText().toString());
				LoginActivity.usr.setEmail(email.getText().toString());

				Intent intent = new Intent(this, ProfileActivity.class);
				startActivity(intent);
			}

		}
	}
}