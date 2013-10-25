package com.cs365.uclick;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class QuizActivity extends Activity implements OnClickListener {
	private Button startquiz;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.quiz);
		this.startquiz = (Button) this.findViewById(R.id.btn_startquiz);
		startquiz.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == startquiz) {
			Intent intent = new Intent(this, ClickerActivity.class);
			startActivity(intent);
		}

	}

}
