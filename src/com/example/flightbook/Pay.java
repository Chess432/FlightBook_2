package com.example.flightbook;

import java.util.Random;

import android.app.ListActivity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Pay extends ListActivity{
	String classes[] = {"PaymentConfirm","Mpesa","Credit"};
	int k;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setListAdapter(new ArrayAdapter<String>(Pay.this, android.R.layout.simple_list_item_1, classes));
		//setBackgroundDrawable(getResources().getDrawable(R.drawable.form));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		String cheese =classes[position];
		Random color = new Random();
		//v.setBackgroundDrawable(getResources().getDrawable(R.drawable.form));
		l.setBackgroundDrawable(getResources().getDrawable(R.drawable.form));
		try {
			Class myClass = Class.forName("com.example.flightbook."+cheese);
			Intent myIntent = new Intent(Pay.this,myClass);
			startActivity(myIntent);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
			Intent intentBank = new Intent(Pay.this,BankAccount.class);
			startActivity(intentBank);
			break;
		}
		return false;
	}

}