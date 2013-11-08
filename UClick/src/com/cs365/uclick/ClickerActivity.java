package com.cs365.uclick;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("HandlerLeak")
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
		q.setText(Integer.toString(n));
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
				Toast.makeText(getBaseContext(),
						Integer.toString(QuizActivity.quiz.getQuestions()),
						Toast.LENGTH_SHORT).show();

				new Thread(new myRunnable(true)).start();
			}

		} else if (v == pre) {
			if (n > 1) {
				new Thread(new myRunnable(false)).start();

			}

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
			String question = q.getText().toString();
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
					ClickerActivity.n = question;
					q.setText(Integer.toString(question));
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
