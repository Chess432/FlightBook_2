package com.example.flightbook;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.*;
import android.widget.RadioGroup.OnCheckedChangeListener;
public class Payment extends Activity implements OnClickListener,OnCheckedChangeListener {
RadioGroup selectionList;
 RadioButton radioButton;
Button result;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.payment);
		initialise();
		selectionList.setOnCheckedChangeListener(this);
		result.setOnClickListener(this);
	}
	private void initialise() {
		// TODO Auto-generated method stub
		result = (Button) findViewById(R.id.bResult);
		selectionList = (RadioGroup) findViewById(R.id.rgPayment);
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		int s1 = selectionList.getCheckedRadioButtonId();
		radioButton = (RadioButton) findViewById(s1);
		
		if(radioButton.getText().equals("Bank Transaction")){
			Intent intentBank = new Intent(Payment.this,Bank.class);
			startActivity(intentBank);
		}
		
	}
	@Override
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		
		// TODO Auto-generated method stub
		switch(checkedId){
		case R.id.rCreditCard:
			
			break;
         case R.id.rBank:
			
			break;
          case R.id.rMpesa:
	
	         break;
		}
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
       // getMenuInflater().inflate(R.menu.start, menu);
		super.onCreateOptionsMenu(menu);
        MenuInflater blow = getMenuInflater();
        blow.inflate(R.menu.payment_menu, menu);
        return true;
    }
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		//return super.onOptionsItemSelected(item);
		switch(item.getItemId()){
		case R.id.checkBalance:
			
			break;
			
		case R.id.createAccount:
			Intent intentBank = new Intent(Payment.this,BankAccount.class);
			startActivity(intentBank);
			break;
		}
		return false;
	}

}
