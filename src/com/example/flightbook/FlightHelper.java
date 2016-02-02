package com.example.flightbook;
import java.sql.Date;

import android.database.*;
import android.database.sqlite.*;
import android.content.*;
public class FlightHelper extends SQLiteOpenHelper{
	private static final String DATABASE_NAME="flightDB";
	private static final int SCHEMA_VERSION=1;
	SQLiteDatabase db;
	String sqlQuery;
	
	public FlightHelper(Context context) {
		super(context, DATABASE_NAME, null, SCHEMA_VERSION);
	//	sqlQuery = "CREATE TABLE IF NOT EXISTS PASSDETAILS(id INTEGER PRIMARY KEY AUTOINCREMENT,fname TEXT ,sname TEXT ,email TEXT ,phone TEXT )";
		}
	@Override
	public void onCreate(SQLiteDatabase db) {
		db.execSQL("CREATE TABLE  PASSENGER(id INTEGER PRIMARY KEY AUTOINCREMENT,fname TEXT ,sname TEXT ,email TEXT ,phone TEXT )");
		// TODO Auto-generated method stub
		db.execSQL("CREATE TABLE flight1 (id INTEGER PRIMARY KEY autoincrement, to_country TEXT, from_country TEXT, travel_date TEXT, return_date TEXT, class TEXT, no_of_infants TEXT, no_of_children TEXT)" );
		db.execSQL("CREATE TABLE bank (id INTEGER PRIMARY KEY autoincrement, username TEXT unique not null, password TEXT unique not null, amount TEXT)" );
		
	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int arg1, int arg2) {
		// TODO Auto-generated method stub
		db.execSQL("DROP TABLE IF EXISTS FLIGHT1");
		db.execSQL("DROP TABLE IF EXISTS BANK");
		db.execSQL("DROP TABLE IF EXISTS PASSDETAILS");
		onCreate(db);
	}
	public boolean  insert(String to, String from,String date1, String date2,String class1,String no_of_infants,String no_of_children) {
		SQLiteDatabase db = this.getWritableDatabase();
		    ContentValues cv=new ContentValues();
			cv.put("to_country", to);
			cv.put("from_country", from);
			cv.put("travel_date", date1);
			cv.put("return_date", date2);
			cv.put("class", class1);
			cv.put("no_of_infants", no_of_infants);
			cv.put("no_of_children", no_of_children);
			long result=db.insert("flight1", null, cv);
			
			if(result == -1)
				return false;
			else
				return true;
			}
public Cursor getAllData(){
	SQLiteDatabase db = this.getWritableDatabase();
	Cursor res = db.rawQuery("SELECT * FROM flight1", null);
	return res;
}
public boolean  insert1(String userName, String passWord, String aMount) {
		// TODO Auto-generated method stub
		SQLiteDatabase db = this.getWritableDatabase();
	    ContentValues cv=new ContentValues();
		cv.put("username", userName);
		cv.put("password", passWord);
		cv.put("amount", aMount);
		
		long result=db.insert("bank", null, cv);
		
		if(result == -1)
			return false;
		else
			return true;
	}
public Cursor getAllData1(){
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor res = db.rawQuery("SELECT * FROM bank", null);
		return res;
	}
public boolean Login1(String to, String from,String date1, String date2,String class1,String no_of_infants,String no_of_children) {
		// TODO Auto-generated method stub
		Cursor mCursor = db.rawQuery("SELECT * FROM flight1 WHERE (to_country=? AND from_country=? AND travel_date=? AND return_date=? AND class=? AND no_of_infants=? AND no_of_children=?)", new String[]{to,from,date1,date2,class1,no_of_infants,no_of_children});
		//String sql="select username,password from login where username='"+date1+"' and password='"+date2+"'";
		if (mCursor != null) {           
            if(mCursor.getCount() > 0)
            {
            	
            	return true;
            }
        }
		return false;
	}
public void open() throws SQLException 
    {
		 db = this.getWritableDatabase();
    }
public boolean paymentConfirm(String username, String password) {
		// TODO Auto-generated method stub
		Cursor mCursor = db.rawQuery("SELECT * FROM bank WHERE (username=? AND password=? AND amount > 10000)", new String[]{username,password});
		//String sql="select username,password from login where username='"+date1+"' and password='"+date2+"'";
		if (mCursor != null) {           
            if(mCursor.getCount() > 0)
            {
            	
            	return true;
            }
        }
		return false;
	}

public boolean  insert2(String fname, String sname, String email,String phone) {
		// TODO Auto-generated method stub
	SQLiteDatabase db = this.getWritableDatabase();
	    ContentValues conv=new ContentValues();
	    conv.put("fname", fname);
	    conv.put("sname", sname);
	    conv.put("email", email);
	    conv.put("phone", phone);
		
		long result=db.insert("PASSENGER", null, conv);
		
		if(result == -1)
			return false;
		else
			return true;
	}
public Cursor getAllDataDetails(){
		SQLiteDatabase db = this.getWritableDatabase();
		Cursor res = db.rawQuery("SELECT * FROM passenger", null);
		return res;
	}
}
