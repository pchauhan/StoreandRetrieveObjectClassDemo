package com.pc.demo;

import com.google.gson.Gson;

import android.app.Activity;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class GSonDemoActivity extends Activity {
    /** Called when the activity is first created. */
	 SharedPreferences  mPrefs  ;
	 Editor prefsEditor ;
	 TextView textView ;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mPrefs = getPreferences(MODE_PRIVATE);
        prefsEditor = mPrefs.edit();
        textView = (TextView)findViewById(R.id.tv);
        MyObject mygetObject = getObject();
        if(mygetObject != null){
        	System.out.println("empID="+mygetObject.empID);
        	System.out.println("empName="+mygetObject.empName);
        	textView.setText("empID="+mygetObject.empID+"\n empName="+mygetObject.empName);
        }else{
        	 MyObject myObject = new MyObject();
             myObject.empID="123";
             myObject.empName="Parag Chauhan";
             saveObject(myObject);
             
        }
      
    }
    
    public MyObject getObject(){
    	Gson gson = new Gson();
        String json = mPrefs.getString("MyObject", "");
        MyObject obj = gson.fromJson(json, MyObject.class);
        return obj ;
    }
    
    public void saveObject(MyObject myobject){
    	 Gson gson = new Gson();
         String json = gson.toJson(myobject);
         prefsEditor.putString("MyObject", json);
         prefsEditor.commit();
         textView.setText("Object Stored");
         //Toast.makeText(getApplicationContext(), "Object Stored", 1000).show();
    }
}