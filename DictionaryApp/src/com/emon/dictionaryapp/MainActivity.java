package com.emon.dictionaryapp;



import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;


public class MainActivity extends Activity {
	
	@Override
	   public void onStart()
	   {
	       super.onStart();	        
	   }
	
	final static String SHARE_NAME_STRING="sharedp";
	final static String USER_NAME_STRING="user";
	
	
	Button button;
	EditText editText;
	
	
	SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
        button=(Button) findViewById(R.id.enterButton);
        editText=(EditText) findViewById(R.id.usernameEditText);
//        Log.d("DICTIONARY","main activity started");
        
        sharedPreferences=getSharedPreferences(SHARE_NAME_STRING,MODE_PRIVATE);
        String usernameString=sharedPreferences.getString(USER_NAME_STRING,"");
        editText.setText(usernameString);
        
        
        button.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				String string=editText.getText().toString();
				Intent intent=new Intent(MainActivity.this, DictanaryActivity.class);
				intent.putExtra("user", string);
				SharedPreferences.Editor editor=sharedPreferences.edit();
				editor.putString(USER_NAME_STRING, string);
				editor.commit();
				startActivity(intent);
			}
		});
    }
    
    public void onSaveInstanceState(Bundle savedInstanceState) {  
		super.onSaveInstanceState(savedInstanceState);
	}
 
	public void onStop() {
		super.onStop(); 
		}  
}
