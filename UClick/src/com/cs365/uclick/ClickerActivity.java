package com.cs365.uclick;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

public class ClickerActivity extends Activity implements OnClickListener {

	private Button cancel, pre, next;
	private TextView q;
	public static int n = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.clicker);

		this.cancel = (Button) this.findViewById(R.id.cancel_click);
		cancel.setOnClickListener(this);

		this.q = (TextView) this.findViewById(R.id.q_number);
		q.setText("1");
		this.pre = (Button) this.findViewById(R.id.preq);
		this.next = (Button) this.findViewById(R.id.nextq);

		pre.setOnClickListener(this);
		next.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == cancel) {
			Intent intent = new Intent(this, MainActivity.class);
			startActivity(intent);
		} else if (v == next) {
			if (n < QuizActivity.quiz.getQuestions()) {
				
			}

		} else if (v == pre) {
			if (n > 1) {
						
			}

		}

	}

}
