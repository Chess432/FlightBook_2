package com.example.flightbook;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;
import android.app.*;
import android.app.AlertDialog.Builder;
import android.content.*;
import android.database.Cursor;
import android.database.sqlite.*;
import android.widget.*;
import android.widget.AdapterView.OnItemSelectedListener;
import android.os.Bundle;
import android.text.InputType;
import android.view.*;
import android.view.View.OnClickListener;
public class Form1 extends Activity implements OnClickListener,OnItemSelectedListener,DatePickerDialog.OnDateSetListener {
Button back,next,view,bPassenger;
Spinner spinner1,spinner2,spinner3,spinner4,spinner5;
String s1,s2,s3,s4,s5,value1,value2,value3,value4,value5;
int k1,day1,month1,year1,day2,month2,year2,infants,children;
DatePicker datePicker1,datePicker2;
SQLiteDatabase db;
FlightHelper helper=null;
//DBUserAdapter dbUser=null;
private EditText fromDateText,toDateText;
private DatePickerDialog toDatePickerDialog;
private SimpleDateFormat dateFormatter;
private int mYear,mMonth,mDay,mYear1,mMonth1,mDay1;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.form1);
		helper=new FlightHelper(this);
		//dbUser = new DBUserAdapter(this); 
		dateFormatter = new SimpleDateFormat("dd-MM-yyyy",Locale.US);
		initialise();
		back.setOnClickListener(this);
		next.setOnClickListener(this);
		view.setOnClickListener(this);
	//	bPassenger.setOnClickListener(this);
		//addListenerOnSpinnerItemSelection();
		spinner1.setOnItemSelectedListener(this);
		spinner2.setOnItemSelectedListener(this);
		spinner3.setOnItemSelectedListener(this);
		spinner4.setOnItemSelectedListener(this);
		spinner5.setOnItemSelectedListener(this);
		fromDateText.setOnClickListener(this);
		toDateText.setOnClickListener(this);
		
		
		//ArtistDatabaseHelper dbHelper = new ArtistDatabaseHelper (getActivity().getApplicationContext());
		//setDateTimeField();	
	}

	private void initialise() {
		back = (Button) findViewById(R.id.bBack);
		next = (Button) findViewById(R.id.bNext2);
		view = (Button)findViewById(R.id.bView);
		spinner1 = (Spinner) findViewById(R.id.spinner1);
		spinner2 = (Spinner) findViewById(R.id.spinner2);
		spinner3 = (Spinner) findViewById(R.id.spinner3);
		//datePicker1 =  (DatePicker) findViewById(R.id.datePicker1);
		//datePicker2 =  (DatePicker) findViewById(R.id.datePicker2);
		spinner4 = (Spinner) findViewById(R.id.spinner4);
		spinner5 = (Spinner) findViewById(R.id.spinner5);
		fromDateText = (EditText) findViewById(R.id.edFrom_Date);
		toDateText = (EditText) findViewById(R.id.edTo_Date);
		fromDateText.setInputType(InputType.TYPE_NULL);
		fromDateText.requestFocus();
		toDateText.setInputType(InputType.TYPE_NULL);
		
	}
	

	
	@Override
	public void onDestroy() {
	super.onDestroy();
	helper.close();
	}
	@Override
	public void onClick(View value) {
		// TODO Auto-generated method stub
		switch(value.getId()){
		case R.id.edFrom_Date:
			
			final Calendar c = Calendar.getInstance();
			mYear = c.get(Calendar.YEAR);
			mMonth = c.get(Calendar.MONTH);
			mDay = c.get(Calendar.DAY_OF_MONTH);
			//Launch Date Picker Dialog
			DatePickerDialog dpd = new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener() {
				@Override
				public void onDateSet(DatePicker view, int year, int monthOfYear,int dayOfMonth) {
					//Display selected date in text bo
					// TODO Auto-generated method stub
					fromDateText.setText(dayOfMonth+"-"+(monthOfYear+1)+"-"+year);
				}
			},mYear,mMonth,mDay);
			dpd.show();
			//fromDatePickerDialog.show();
			break;
			
		case R.id.edTo_Date:
		
			final Calendar c1 = Calendar.getInstance();
			mYear1 = c1.get(Calendar.YEAR);
			mMonth1 = c1.get(Calendar.MONTH);
			mDay1 = c1.get(Calendar.DAY_OF_MONTH);
			//Launch Date Picker Dialog
			DatePickerDialog dpd1 = new DatePickerDialog(this,new DatePickerDialog.OnDateSetListener() {
				@Override
				public void onDateSet(DatePicker view1, int year1, int monthOfYear1,int dayOfMonth1) {
					//Display selected date in text box
					// TODO Auto-generated method stub
					toDateText.setText(dayOfMonth1+"-"+(monthOfYear1+1)+"-"+year1);
				}
			},mYear1,mMonth1,mDay1);
			dpd1.show();
			//toDatePickerDialog.show();
			break;
			
		case R.id.bBack:
		  helper = new FlightHelper(Form1.this);
			helper.open();
			/*final Calendar cal = Calendar.getInstance();
			mYear = cal.get(Calendar.YEAR);
			mMonth = cal.get(Calendar.MONTH);
			mDay = cal.get(Calendar.DAY_OF_MONTH);
			
			final Calendar cal2 = Calendar.getInstance();
			mYear1 = cal2.get(Calendar.YEAR);
			mMonth1 = cal2.get(Calendar.MONTH);
			mDay1 = cal2.get(Calendar.DAY_OF_MONTH);
		
				if((mDay1<mDay) && (mMonth1<mMonth) && (mYear1==  mYear)){
					Toast.makeText(this, "Invalid date combination", 1).show();
				}
				else if((mDay1<mDay)&&(mMonth1 == mMonth) && (mYear1 == mYear)){
					Toast.makeText(this, "Invalid date combination", 1).show();
				}
				else if((mDay1<mDay) && (mMonth1<mMonth) && (mYear1<mYear)){
					Toast.makeText(this, "Invalid date combination", 1).show();
				}
				
				else*/ if(helper.Login1(spinner2.getSelectedItem().toString(), spinner1.getSelectedItem().toString(),
					fromDateText.getText().toString(), toDateText.getText().toString(),  spinner3.getSelectedItem().toString()
					,spinner5.getSelectedItem().toString(),spinner4.getSelectedItem().toString()))
			{
				//Toast.makeText(Form1.this,"Flight available,proceed with booking", Toast.LENGTH_LONG).show();
				AlertDialog.Builder alertBox = new AlertDialog.Builder(this);
				//set message to display
				alertBox.setMessage("Flight Available "+"\n"+"continue with booking");
				//add a neutral button and assign a listener
				alertBox.setNeutralButton("OK", new DialogInterface.OnClickListener() {
					
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						Intent i2 = new Intent(Form1.this,MainActivity.class);
						startActivity(i2);
					}
				});
				alertBox.show();
			}else{
				Toast.makeText(Form1.this,"Flight Unavailable", Toast.LENGTH_LONG).show();
			}
			helper.close();
			
			break;
			
		case R.id.bNext2:
			
			helper.insert(spinner2.getSelectedItem().toString(), spinner1.getSelectedItem().toString(),
					fromDateText.getText().toString(), toDateText.getText().toString(),  spinner3.getSelectedItem().toString()
					,spinner5.getSelectedItem().toString(),spinner4.getSelectedItem().toString());
					
			
			if(true){
				Toast.makeText(Form1.this, "Data is Inserted", Toast.LENGTH_LONG).show();
			}
			else{
				Toast.makeText(Form1.this, "Data is NOT Inserted", Toast.LENGTH_LONG).show();
			}
			break;
			
		case R.id.bView:
			Cursor res = helper.getAllData();
			if(res.getCount() == 0){
				//show message
				showMessage("Error","No Data Found");
				return;
			}
			StringBuffer buffer = new StringBuffer();
			while(res.moveToNext()){
				buffer.append("id :"+res.getString(0)+"\n");
				buffer.append("To_Country :"+res.getString(1)+"\n");
				buffer.append("From_Country :"+res.getString(2)+"\n");
				buffer.append("Travel_Date :"+res.getString(3)+"\n");
				buffer.append("Return_Date :"+res.getString(4)+"\n");
				buffer.append("Class :"+res.getString(5)+"\n");
				buffer.append("No_of_Infants :"+res.getString(6)+"\n");
				buffer.append("No_of_Children :"+res.getString(7)+"\n\n");
			}
			//show message
			showMessage("Available Flights",buffer.toString());
			break;
			
		
		}
		
	}


public void showMessage(String title, String Message){
	AlertDialog.Builder builder = new AlertDialog.Builder(this);
	builder.setCancelable(true);
	builder.setTitle(title);
	builder.setMessage(Message);
	builder.show();
}
	@Override
	public void onItemSelected(AdapterView<?> arg0, View arg1, int arg2,
			long arg3) {
		// TODO Auto-generated method stub
		switch(arg1.getId()){
		case R.id.spinner1:
		value1 = arg0.getItemAtPosition(arg2).toString();
		break;
		case R.id.spinner2:
			value2 = arg0.getItemAtPosition(arg2).toString();
			break;
			
		case R.id.spinner3:
			value3 = arg0.getItemAtPosition(arg2).toString();
			break;
		case R.id.spinner4:
			value4 = arg0.getItemAtPosition(arg2).toString();
			break;
		case R.id.spinner5:
			value5 = arg0.getItemAtPosition(arg2).toString();
			break;
		}
		System.out.println("the values are "+value1+" "+value2+" "+value3);
	}

	@Override
	public void onNothingSelected(AdapterView<?> arg0) {
		// TODO Auto-generated method stub
		
	}



	@Override
	public void onDateSet(DatePicker view, int year, int monthOfYear,
			int dayOfMonth) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// TODO Auto-generated method stub
		super.onCreateOptionsMenu(menu);
        MenuInflater blow = getMenuInflater();
        blow.inflate(R.menu.form1_menu, menu);
        return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// TODO Auto-generated method stub
		//return super.onOptionsItemSelected(item);
		switch(item.getItemId()){
		case R.id.menuFlightCheck:
			
			break;
		case R.id.menuExit:
			System.exit(0);
			break;
		case R.id.menuComment:
			Intent iComments = new Intent(Form1.this,Comment.class);
			startActivity(iComments);
			break;
		case R.id.menuFaqs:
			/*Intent iFaqs = new Intent(Form1.this,Faqs.class);
			startActivity(iFaqs);*/
			startActivity(new Intent(this,Faqs.class));
			break;
		}
		return false;
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		finish();
	}
	
	

}
