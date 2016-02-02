package com.example.flightbook;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.widget.*;
import android.content.*;
import android.view.*;
import android.view.View.OnClickListener;
public class Bank extends Activity implements OnClickListener {
Button submit,cancel;
EditText username,password;
FlightHelper helper=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.payment_confirm);
		initialise();
		submit.setOnClickListener(this);
		cancel.setOnClickListener(this);
	}

	private void initialise() {
		// TODO Auto-generated method stub
		submit = (Button) findViewById(R.id.bSubmitPayment);
		cancel = (Button) findViewById(R.id.bCancelPayment);
		username = (EditText) findViewById(R.id.edUsernamePayment);
		password = (EditText) findViewById(R.id.edPasswordPayment);
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		case R.id.bSubmitPayment:
			helper = new FlightHelper(Bank.this);
			helper.open();
			try{
			if(helper.paymentConfirm( username.getText().toString(),  password.getText().toString()))
			{
				//Toast.makeText(Form1.this,"Flight available,proceed with booking", Toast.LENGTH_LONG).show();
				AlertDialog.Builder alertBox = new AlertDialog.Builder(this);
				//set message to display
				alertBox.setMessage("BOOKING SUCEEDED"+"\n"+"now give us your details");
				//add a neutral button and assign a listener
				alertBox.setNeutralButton("OK", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Intent i2 = new Intent(Bank.this,Form2.class);
						startActivity(i2);
					}
				});
				alertBox.show();
				//finish();
			}else{
				Toast.makeText(Bank.this,"INCORRECT DETAILS / NOT ENOUGH AMOUNT", Toast.LENGTH_LONG).show();
			}
			helper.close();
			}catch(Exception e){
				Toast.makeText(Bank.this,e.getMessage(), 5000).show();
			}
			
			break;
		case R.id.bCancelPayment:
			finish();
			break;
		}
		
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	

}
