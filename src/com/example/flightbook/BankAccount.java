package com.example.flightbook;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
public class BankAccount extends Activity implements OnClickListener {
Button submit,cancel,retrieve;
EditText username,password,amount;
//BankHelper bankClass = null;
FlightHelper helper=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.bank_account);
		initialise();
	//	bankClass = new BankHelper(this);
		helper=new FlightHelper(this);
		submit.setOnClickListener(this);
		retrieve.setOnClickListener(this);
	}

	private void initialise() {
		// TODO Auto-generated method stub
		submit = (Button) findViewById(R.id.bSubmit);
		cancel = (Button) findViewById(R.id.bCancel);
		retrieve = (Button) findViewById(R.id.bRetrieve);
		username = (EditText) findViewById(R.id.edUsername);
		password = (EditText) findViewById(R.id.edPassword);
		amount = (EditText) findViewById(R.id.edAmount);
		
	}
	
	@Override
	public void onDestroy() {
	super.onDestroy();
	helper.close();
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		switch(arg0.getId()){
		case R.id.bSubmit:		
			boolean result = helper.insert1(username.getText().toString(), password.getText().toString(),amount.getText().toString());					
			if(result == true){
				Toast.makeText(BankAccount.this, "Data is Inserted", Toast.LENGTH_LONG).show();
			}
			else{
				Toast.makeText(BankAccount.this, "Data is NOT Inserted", Toast.LENGTH_LONG).show();
			}
			
			break;
		case R.id.bCancel:
			
			break;
		case R.id.bRetrieve:
			Cursor res = helper.getAllData1();
			if(res.getCount() == 0){
				//show message
				showMessage("Error","No Data Found");
				return;
			}
			StringBuffer buffer = new StringBuffer();
			while(res.moveToNext()){
				buffer.append("id :"+res.getString(0)+"\n");
				buffer.append("username :"+res.getString(1)+"\n");
				buffer.append("password :"+res.getString(2)+"\n");
				buffer.append("amount :"+res.getString(3)+"\n\n");
			}
			//show message
			showMessage("Available Values"+"\n"+"pleassure is mine",buffer.toString());
			break;
		}
		
	}

	private void showMessage(String title, String Message) {
		// TODO Auto-generated method stub
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setCancelable(true);
		builder.setTitle(title);
		builder.setMessage(Message);
		builder.show();
		
	}

	
	
}
