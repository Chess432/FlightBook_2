package com.example.flightbook;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
public class Comment extends Activity implements OnClickListener {
Button submit,cancel;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.comments);
		initialise();
		submit.setOnClickListener(this);
		cancel.setOnClickListener(this);
	}
	private void initialise() {
		// TODO Auto-generated method stub
		submit = (Button) findViewById(R.id.commentSubmit);
		cancel = (Button) findViewById(R.id.commentCancel);
	}
	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		case R.id.commentSubmit:
			
			break;
			
		case R.id.commentCancel:
			finish();
			break;
		}
		
	}

}
