package com.cs365.uclick;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import com.cs365.uclick.RegisterActivity;

public class MainActivity extends Activity implements OnClickListener {

	private Button signupButton;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.signupButton = (Button) this.findViewById(R.id.signup_main);
		signupButton.setOnClickListener(this);		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		final Context context = this;
		if(v==signupButton) {
			
			Intent intent = new Intent(context, RegisterActivity.class);
			startActivity(intent);
			
		}
		
	}

}
