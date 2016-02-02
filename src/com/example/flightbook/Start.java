package com.example.flightbook;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.content.IntentSender.SendIntentException;
import android.view.*;
import android.widget.*;
public class Start extends Activity implements View.OnClickListener {
Button next1,exit;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        initialize();
        next1.setOnClickListener(this);
        exit.setOnClickListener(this);
    }


    private void initialize() {
		next1 = (Button) findViewById(R.id.bNextForm1);
		exit = (Button) findViewById(R.id.bExit);
		
	}


	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.start, menu);
		super.onCreateOptionsMenu(menu);
        MenuInflater blow = getMenuInflater();
        blow.inflate(R.menu.about_airways, menu);
        return true;
    }


	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		//return super.onOptionsItemSelected(item);
		switch(item.getItemId()){
		case R.id.aboutUs:
			Intent k =new Intent("com.example.flightbook.ABOUT");
			startActivity(k);
			break;
			
		case R.id.exit:
			System.exit(0);
			break;
		}
		return false;
	}


	@Override
	public void onClick(View arg0) {
		switch(arg0.getId()){
		case R.id.bNextForm1:
			Intent iForm1 = new Intent(Start.this,Form1.class);
			startActivity(iForm1);
			break;
		case R.id.bExit:
			System.exit(0);
			break;
		}
		
	}


	


    
}
