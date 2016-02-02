package com.example.flightbook;


import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import android.app.*;
import android.widget.*;
import android.widget.AdapterView.OnItemSelectedListener;
import android.content.*;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.*;
import android.view.View.OnClickListener;
import android.provider.ContactsContract;
import android.telephony.gsm.SmsManager;
public class Form2 extends Activity implements OnClickListener,OnItemSelectedListener {
Button next,back;
Spinner spinner4,spinner5;
String value1,value2,value3,value4,value5,value6,value7;
int k1,k2;
EditText fname,sname,email,phone;
FlightHelper helper=null;
protected static final int PICK_CONTACT = 0; 
ArrayList<String> numsArray; 
StringBuilder sb; 

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.form2);
		helper=new FlightHelper(this);
		
		initialise();
		next.setOnClickListener(this);
		back.setOnClickListener(this);
		
		SharedPreferences settings = getSharedPreferences("MYPREFS", 0);
        fname.setText(settings.getString("fValue", ""));
        sname.setText(settings.getString("sValue", ""));
        email.setText(settings.getString("eValue", ""));
        phone.setText(settings.getString("pValue", ""));
		
		//addListenerOnSpinnerItemSelection1();
	}

	

	private void initialise() {
		next = (Button) findViewById(R.id.bSubmitDetails);
		back =(Button) findViewById(R.id.bBack1);
		fname = (EditText) findViewById(R.id.edFname);
		sname = (EditText) findViewById(R.id.edSname);
		email = (EditText) findViewById(R.id.edEmail);
		phone = (EditText) findViewById(R.id.edPhone);
		numsArray = new ArrayList<String>(); 
		sb = new StringBuilder(); 
	}

	
//ButtonHandler bh = new ButtonHandler(null, value1, null, k1);
	@Override
	public void onDestroy() {
	super.onDestroy();
	helper.close();
	}
	
	@Override
	public void onClick(View v) {
		switch(v.getId()){
		case R.id.bBack1:
			Cursor res = helper.getAllDataDetails();
			if(res.getCount() == 0){
				//show message
				showMessage("Error","No Data Found");
				return;
			}
			StringBuffer buffer = new StringBuffer();
			while(res.moveToNext()){
				buffer.append("id :"+res.getString(0)+"\n");
				buffer.append("First Name :"+res.getString(1)+"\n");
				buffer.append("Second Name :"+res.getString(2)+"\n");
				buffer.append("Email Adrress :"+res.getString(3)+"\n");
				buffer.append("Phone NUmber :"+res.getString(4)+"\n\n");
				
			}
			//show message
			showMessage("Available Passenger Details",buffer.toString());
			
			break;
		case R.id.bSubmitDetails:
			helper = new FlightHelper(Form2.this);
			helper.open();
			if((fname.getText().toString().equals("")) || (fname.getText().toString().equals(null)) || (sname.getText().toString().equals("")) || (sname.getText().toString().equals(null))
					|| (email.getText().toString().equals("")) || (email.getText().toString().equals(null)) || (phone.getText().toString().equals("")) || (phone.getText().toString().equals(null))){
				//what to happen				
				Toast.makeText(this, "please provide all the details", 1).show();
				//finish();
			} else if(!isValidEmail(email.getText().toString())){
				Toast.makeText(this, "wrong email format", 1).show();
			}
			else if(!isValidFname(fname.getText().toString())){
				Toast.makeText(this, "wrong first name format", 1).show();
			}
			else if((!isValidFname(sname.getText().toString()))){
				Toast.makeText(this, "wrong second name format", 1).show();
			}
			else if((!isValidPhone(phone.getText().toString()))){
				Toast.makeText(this, "wrong phone number format", 1).show();
			}
			
			else {boolean result1 =helper.insert2(fname.getText().toString(),sname.getText().toString(),
					email.getText().toString(),phone.getText().toString());
			
					
			if(result1 ==  true){
				//Toast.makeText(Form2.this, "Data is Inserted", Toast.LENGTH_LONG).show();
				AlertDialog.Builder alertBox = new AlertDialog.Builder(this);
				//set message to display
				alertBox.setMessage("Data inserted");
				//add a neutral button and assign a listener
				alertBox.setNeutralButton("OK", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						if (phone.getText().toString().equals("")) 
						{ 
						Toast.makeText(Form2.this, "Enter phone number", 100).show();
						Log.d("TAG", "In send ....."); 
						}
						else 
						{ 
						Log.d("TAG", "In send .222...."+numsArray.size()); 
						String message = "You submitted the following details:"+"\n"+
						"First Name: "+fname.getText().toString()+""+"\n"+
						"Second Name: "+sname.getText().toString()+"\n"+
						"Email Address "+email.getText().toString()+"\n"+
						"Phone Number: "+phone.getText().toString(); 
						
						String number = phone.getText().toString(); 
						numsArray.add(number); 
						for (int i = 0; i < numsArray.size(); i++) 
						{ 
						sendSMS(numsArray.get(i), message); 
						Log.d("TAG", "In else....."); 
						}
						 }
						//Intent i2 = new Intent(Form2.this,MainActivity.class);
						//startActivity(i2);
					}

					protected void sendSMS(String phoneNumber, final String message) 
					{ 
					String SENT = "SMS_SENT"; 
					String DELIVERED = "SMS_DELIVERED"; 
					//PendingIntent sentPI = PendingIntent.getBroadcast(this, 0, new Intent(SENT), 0); 
					//PendingIntent deliveredPI = PendingIntent.getBroadcast(this, 0,new Intent(DELIVERED), 0); 
					// ---when the SMS has been sent--- 
					registerReceiver(new BroadcastReceiver() 
					{ 
					public void onReceive(Context arg0, Intent arg1) 
					{ 
					switch (getResultCode()) { 
					case Activity.RESULT_OK: ContentValues values = new ContentValues(); 
					for (int i = 0; i < numsArray.size() - 1; i++) 
					{ 
					values.put("address", numsArray.get(i).toString()); 
					//  txtPhoneNo.getText().toString()); 
					values.put("body", message); 
					}                                              
					getContentResolver().insert(Uri.parse("content://sms/sent"), values); 
					Toast.makeText(getBaseContext(), "SMS sending..",Toast.LENGTH_SHORT).show(); 
					numsArray.clear(); 
					break; 
					case SmsManager.RESULT_ERROR_GENERIC_FAILURE: 
					Toast.makeText(getBaseContext(), "Generic failure",Toast.LENGTH_SHORT).show(); 
					break; 
					case  SmsManager.RESULT_ERROR_NO_SERVICE: 
					Toast.makeText(getBaseContext(), "No service",Toast.LENGTH_SHORT).show(); 
					break; 
					case SmsManager.RESULT_ERROR_NULL_PDU: 
					Toast.makeText(getBaseContext(), "Null PDU",Toast.LENGTH_SHORT).show(); 
					break; 
					case SmsManager.RESULT_ERROR_RADIO_OFF: 
					Toast.makeText(getBaseContext(), "Radio off",Toast.LENGTH_SHORT).show(); 
					break; 
					} 
					} 
					}, 
					new IntentFilter(SENT)); 

					// ---when the SMS has been delivered--- 
					registerReceiver(new BroadcastReceiver() 
					{ 
					@Override 
					public void onReceive(Context arg0, Intent arg1) 
					{ 
					switch (getResultCode()) 
					{ 
					case Activity.RESULT_OK: 
					Toast.makeText(getBaseContext(), "SMS delivered",Toast.LENGTH_SHORT).show(); 
					break; 
					case Activity.RESULT_CANCELED: 
					Toast.makeText(getBaseContext(), "SMS not delivered", 
					Toast.LENGTH_SHORT).show(); 
					break; 
					} 
					} 
					}, 
					new IntentFilter(DELIVERED)); 
					SmsManager sms = SmsManager.getDefault(); 
					sms.sendTextMessage(phoneNumber, null, message, null, null); 
					}
				});
				alertBox.show();
			}
			else{
				Toast.makeText(Form2.this, "ERROR!! DATA NOT INSERTED", Toast.LENGTH_LONG).show();
			}
			}
			break;
			
		}
		
	}

	private boolean isValidPhone(String phone1) {
		// TODO Auto-generated method stub
		String EMAIL_PATTERN = "^[0-9]{10,15}$";
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(phone1);
		return matcher.matches();
		
	}



	private boolean isValidFname(String fname1) {
		// TODO Auto-generated method stub
		String EMAIL_PATTERN = "^[A-Za-z]{3,15}$";
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(fname1);
		return matcher.matches();
		
	}



	private boolean isValidEmail(String email1) {
		// TODO Auto-generated method stub
		String EMAIL_PATTERN = "^[A-Za-z0-9-\\+]+(\\.[A-Za-z0-9-]+)*@"
				+"[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
		Pattern pattern = Pattern.compile(EMAIL_PATTERN);
		Matcher matcher = pattern.matcher(email1);
		return matcher.matches();
	}



	public void showMessage(String title, String Message){
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setCancelable(true);
		builder.setTitle(title);
		builder.setMessage(Message);
		builder.show();
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		
	}
	
	 @Override
		protected void onStop() {
			super.onStop();
			SharedPreferences settings = getSharedPreferences("MYPREFS", 0);
			SharedPreferences.Editor editor = settings.edit();
			editor.putString("fValue", fname.getText().toString());
			editor.putString("sValue", sname.getText().toString());
			editor.putString("eValue", email.getText().toString());
			editor.putString("pValue", phone.getText().toString());
			editor.commit();
		}

}
