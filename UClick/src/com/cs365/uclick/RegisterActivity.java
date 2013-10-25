package com.cs365.uclick;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class RegisterActivity extends Activity implements OnClickListener {
	private Button goback, finish;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.register);
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
			Intent intent = new Intent(this, QuizActivity.class);
			startActivity(intent);

		}
	}

}