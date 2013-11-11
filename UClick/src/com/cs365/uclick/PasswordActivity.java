package com.cs365.uclick;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class PasswordActivity extends Activity implements OnClickListener {
	private EditText email;
	private Button back, send1, send2;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.password);

		email = (EditText) this.findViewById(R.id.pass_email);
		back = (Button) this.findViewById(R.id.passbtn_back);
		send1 = (Button) this.findViewById(R.id.passbtn_send);
		send2 = (Button) this.findViewById(R.id.passbtn_send2);

		back.setOnClickListener(this);
		send1.setOnClickListener(this);
		send2.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == back) {
			Intent intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
		}

		else if (v == send1 || v == send2) {
			// dialog box
			Intent intent = new Intent(this, LoginActivity.class);
			startActivity(intent);
		}

	}
}
