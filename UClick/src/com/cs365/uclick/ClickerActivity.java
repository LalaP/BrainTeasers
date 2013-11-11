package com.cs365.uclick;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("HandlerLeak")
public class ClickerActivity extends Activity implements OnClickListener {

	private Button pre, next, cancel, submit, answer, A, B, C, D, E, F, G, H,
			I;
	private TextView qnumber;
	private Spinner menu;
	public static int qn = 1;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.clicker);

		this.menu = (Spinner) this.findViewById(R.id.mainmenu);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.Menu, R.layout.spinnerstyle);
		adapter.setDropDownViewResource(R.layout.dropdown);
		menu.setAdapter(adapter);

		qnumber = (TextView) this.findViewById(R.id.clik_qn);
		qnumber.setText(Integer.toString(qn));

		pre = (Button) this.findViewById(R.id.clikbtn_pre);
		next = (Button) this.findViewById(R.id.clikbtn_next);
		cancel = (Button) this.findViewById(R.id.clikbtn_cancel);
		submit = (Button) this.findViewById(R.id.clikbtn_submit);

		answer = (Button) this.findViewById(R.id.clik_ans);

		A = (Button) this.findViewById(R.id.clikbtn_a);
		B = (Button) this.findViewById(R.id.clikbtn_b);
		C = (Button) this.findViewById(R.id.clikbtn_c);
		D = (Button) this.findViewById(R.id.clikbtn_d);
		E = (Button) this.findViewById(R.id.clikbtn_e);
		F = (Button) this.findViewById(R.id.clikbtn_f);
		G = (Button) this.findViewById(R.id.clikbtn_g);
		H = (Button) this.findViewById(R.id.clikbtn_h);
		I = (Button) this.findViewById(R.id.clikbtn_i);

		pre.setOnClickListener(this);
		next.setOnClickListener(this);
		cancel.setOnClickListener(this);
		submit.setOnClickListener(this);

		A.setOnClickListener(this);
		B.setOnClickListener(this);
		C.setOnClickListener(this);
		D.setOnClickListener(this);
		E.setOnClickListener(this);
		F.setOnClickListener(this);
		G.setOnClickListener(this);
		H.setOnClickListener(this);
		I.setOnClickListener(this);

	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == next) {
			if (qn < ProfileActivity.quiz.getQuestions()) {
				Toast.makeText(getBaseContext(),
						Integer.toString(ProfileActivity.quiz.getQuestions()),
						Toast.LENGTH_SHORT).show();

				new Thread(new myRunnable(true)).start();
			}

		} else if (v == pre) {
			if (qn > 1) {
				new Thread(new myRunnable(false)).start();

			}

		} else if (v == cancel) {
			Intent intent = new Intent(this, ProfileActivity.class);
			startActivity(intent);
		} else if (v == submit) {
			// show quiz result
			// update database
			// goto profile.xml
			Intent intent = new Intent(this, ProfileActivity.class);
			startActivity(intent);
		} else if (v == A || v == B || v == C || v == D || v == E || v == F
				|| v == G || v == H || v == I) {
			String ans = ((Button) v).getText().toString();
			// answer.setText(ans);
			// user thread
		}

	}

	private class myRunnable implements Runnable {
		private boolean isIncreasing = false;

		public myRunnable(boolean isIncreasing) {
			this.isIncreasing = isIncreasing;
		}

		@Override
		public void run() {
			// TODO Auto-generated method stub
			String question = qnumber.getText().toString();
			threadMsg(question);
		}

		private void threadMsg(String msg) {

			if (!msg.equals(null) && !msg.equals("")) {
				Message msgObj = handler.obtainMessage();
				Bundle b = new Bundle();
				b.putString("message", msg);
				msgObj.setData(b);
				handler.sendMessage(msgObj);
			}
		}

		// Define the Handler that receives messages from the thread
		// and update the progress
		private final Handler handler = new Handler() {

			public void handleMessage(Message msg) {

				String aResponse = msg.getData().getString("message");

				if ((null != aResponse)) {

					// ALERT MESSAGE
					int question = Integer.parseInt(aResponse);
					if (isIncreasing)
						question++;
					else
						question--;
					ClickerActivity.qn = question;
					qnumber.setText(Integer.toString(question));
				} else {

					// ALERT MESSAGE
					Toast.makeText(getBaseContext(),
							"Not Got Response From Server.", Toast.LENGTH_SHORT)
							.show();
				}

			}
		};

	}
}
