package com.example.flightbook;



import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;
import android.app.Activity;
import android.content.Intent;
 
public class MainActivity extends Activity {
    ListView list;
    String[] web = {
            "Bank",
            "Mpesa",
            "Credit",
            
    } ;
    Integer[] imageId = {
            R.drawable.equity,
            R.drawable.mpesa,
            R.drawable.visa,
            
 
    };
 
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
 
        CustomList adapter = new CustomList(MainActivity.this, web, imageId);
        list=(ListView)findViewById(R.id.list);
                list.setAdapter(adapter);
                list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
 
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view,int position, long id) {
                    	String classPosition =web[position];
                    	//if(class)
                    	try {
                			Class<?> myClass = Class.forName("com.example.flightbook."+classPosition);
                			Intent myIntent = new Intent(MainActivity.this,myClass);
                			startActivity(myIntent);
                		} catch (ClassNotFoundException e) {
                			// TODO Auto-generated catch block
                			e.printStackTrace();
                		}
                       // Toast.makeText(MainActivity.this, "You Clicked at " +web[+ position], Toast.LENGTH_SHORT).show();
 
                    }
                });
 
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
			Intent intentBank = new Intent(MainActivity.this,BankAccount.class);
			startActivity(intentBank);
			break;
		}
		return false;
	}
 
}
