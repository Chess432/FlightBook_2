package com.example.flightbook;

public class Model{
	 
    private int icon;
    private String title;
    private String counter;
 
    private boolean isGroupHeader = false;
 
    public Model(String title) {
        this(-1,title,null);
        isGroupHeader = true;
    }
    public Model(int icon, String title, String counter) {
        super();
        this.icon = icon;
        this.title = title;
        this.counter = counter;
    }
	public boolean isGroupHeader() {
		// TODO Auto-generated method stub
		return false;
	}
	
	public int getIcon() {
		// TODO Auto-generated method stub
		return 0;
	}
	public void setIcon(int icon){
		this.icon=icon;
	}
	public CharSequence getTitle() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setTitile(String title){
		this.title=title;
	}
	public CharSequence getCounter() {
		// TODO Auto-generated method stub
		return null;
	}
	public void setCounter(String counter){
		this.counter=counter;
	}
 
//gettters & setters...
}