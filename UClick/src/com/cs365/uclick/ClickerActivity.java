package com.cs365.uclick;

import java.util.ArrayList;
import java.util.Currency;
import java.util.Timer;

import com.parse.ParseUser;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("HandlerLeak")
public class ClickerActivity extends Activity implements OnClickListener,
		OnItemSelectedListener {

	private Button next, cancel, submit, answer, A, B, C, D, E, F;
	private TextView qnumber;
	private Spinner menu;
	public static int qn;
	private String answerCurrent;
	private boolean tag;
	private TextView quizname;
	private ArrayList<String> answers;
	private TextView text;
	final long startTime = 30000;
	final long interval = 1000;
	private MalibuCountDownTimer countDownTimer;
    private boolean timerHasStarted = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.clicker);

		this.menu = (Spinner) this.findViewById(R.id.mainmenu);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
				this, R.array.Menu, R.layout.spinnerstyle);
		adapter.setDropDownViewResource(R.layout.dropdown);
		menu.setAdapter(adapter);
		tag = false;
		answerCurrent = "";
		qn = 1;
		answers = new ArrayList<String>(MyData.quiz.getQuestions());
		for (int i = 0; i < MyData.quiz.getQuestions(); i++)
			answers.add("");
		quizname = (TextView) this.findViewById(R.id.clik_qzname);
		quizname.setText(MyData.quiz.getId());
		menu.setOnItemSelectedListener(this);
		qnumber = (TextView) this.findViewById(R.id.clik_qn);
		qnumber.setText(Integer.toString(qn));

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

		next.setOnClickListener(this);
		cancel.setOnClickListener(this);
		submit.setOnClickListener(this);

		A.setOnClickListener(this);
		B.setOnClickListener(this);
		C.setOnClickListener(this);
		D.setOnClickListener(this);
		E.setOnClickListener(this);
		F.setOnClickListener(this);
		
		text = (TextView) this.findViewById(R.id.timer);
		//timeElapsedView = (TextView) this.findViewById(R.id.timeElapsed);
		countDownTimer = new MalibuCountDownTimer(startTime);
        text.setText(text.getText() + String.valueOf(startTime));
        countDownTimer.start();
        timerHasStarted = true;
        

	}
	 public class MalibuCountDownTimer extends CountDownTimer
     {

         public MalibuCountDownTimer(long startTime)
             {
                 super(startTime, interval);
             }

         @Override
         public void onFinish()
             {
                 text.setText("Time's up!");
                 if (qn < MyData.quiz.getQuestions()) {
     				new Thread(new myRunnable(true)).start();
     				countDownTimer.start();
                     timerHasStarted = true;
                    
     			}

             }

         @Override
         public void onTick(long millisUntilFinished)
             {
                 text.setText("Time remain:" + millisUntilFinished);
               
             }
     }
	 


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		if (v == next) {

			if (qn < MyData.quiz.getQuestions()) {
				new Thread(new myRunnable(true)).start();
				countDownTimer.start();
                timerHasStarted = true;
			}

		} /*
		 * else if (v == pre) { if (qn > 1) { new Thread(new
		 * myRunnable(false)).start();
		 * 
		 * }
		 * 
		 * }
		 */else if (v == cancel) {
			Intent intent = new Intent(this, ProfileActivity.class);
			startActivity(intent);
		} else if (v == submit) {
			// show quiz result
			// update database
			// goto profile.xml
			for (int i = 0; i < MyData.quiz.getQuestions(); i++) {
				Log.d("answer", "" + answers.get(i));
			}

			final Intent intent = new Intent(this, ProfileActivity.class);
			int result = evaluateQuiz();
			MyData.addQuiznResult(MyData.quiz.getId(), result);
			AlertDialog.Builder alertDialog1 = new AlertDialog.Builder(this);

			alertDialog1.setTitle("Quiz Results");
			alertDialog1.setMessage("Results: " + result + " out of "
					+ MyData.quiz.getQuestions());

			alertDialog1.setIcon(R.drawable.ic_launcher);

			alertDialog1.setNeutralButton("OK",
					new DialogInterface.OnClickListener() {

						public void onClick(DialogInterface dialog, int which) {
							startActivity(intent);
						}
					});

			alertDialog1.show();

		} else if (v == A || v == B || v == C || v == D || v == E || v == F) {
			answerCurrent = ((Button) v).getText().toString();
			new Thread(new Runnable() {

				@Override
				public void run() {
					// TODO Auto-generated method stub
					threadMsg(answerCurrent);
				}

				private void threadMsg(String msg) {
					// TODO Auto-generated method stub
					if (!msg.equals(null) && !msg.equals("")) {
						Message msgObj = handler.obtainMessage();
						Bundle b = new Bundle();
						b.putString("message", msg);
						msgObj.setData(b);
						handler.sendMessage(msgObj);
					}

				}

				private final Handler handler = new Handler() {

					public void handleMessage(Message msg) {

						String aResponse = msg.getData().getString("message");

						if ((null != aResponse)) {
							answer.setText(answerCurrent);
							answers.set(qn - 1, answerCurrent);
						} else {

							// ALERT MESSAGE
							Toast.makeText(getBaseContext(),
									"Not Got Response From Server.",
									Toast.LENGTH_SHORT).show();
						}

					}
				};
			}).start();
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
					answer.setText("");
				} else {

					// ALERT MESSAGE
					Toast.makeText(getBaseContext(),
							"Not Got Response From Server.", Toast.LENGTH_SHORT)
							.show();
				}

			}
		};

	}

	@Override
	public void onItemSelected(AdapterView<?> parent, View view, int pos,
			long id) {
		// TODO Auto-generated method stub
		TextView v = (TextView) view;
		Toast.makeText(this, v.getText().toString(), Toast.LENGTH_SHORT);

		if (tag) {
			if (v.getText().equals("YOUR ACCOUNT")) {
				Intent intent = new Intent(this, ProfileActivity.class);
				startActivity(intent);

			} else if (v.getText().equals("YOUR HISTORY")) {
				Intent intent = new Intent(this, QuizActivity.class);
				startActivity(intent);

			} else if (v.getText().equals("SIGN OUT")) {
				final Intent intent = new Intent(this, LoginActivity.class);

				AlertDialog.Builder dialog = new AlertDialog.Builder(this);

				dialog.setTitle("Log Out");
				dialog.setMessage("Are you sure you want to log out?");

				dialog.setIcon(R.drawable.ic_launcher);

				dialog.setPositiveButton("Yes",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								ParseUser.logOut();
								MyData.quiz = null;
								startActivity(intent);
							}
						});
				dialog.setNegativeButton("No",
						new DialogInterface.OnClickListener() {

							public void onClick(DialogInterface dialog,
									int which) {
								dialog.dismiss();
							}
						});

				dialog.show();
			}

		}
		tag = true;
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub

	}

	public int evaluateQuiz() {
		int totalPoint = 0;
		// ArrayList<String> solutions = MyData.quiz.getAnswers();
		for (int i = 0; i < MyData.quiz.getQuestions(); i++) {
			if (MyData.quiz.getAnswers().get(i)
					.equalsIgnoreCase(answers.get(i)))
				totalPoint++;
		}
		return totalPoint;
	}
}