package com.emon.dictionaryapp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.Window;
import android.view.WindowManager;

public class SplashActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_splash);
		Thread logoTimer = new Thread(){
			
			public void run() {
				
				try{
					sleep(3000);
					Intent menuIntent=new Intent("com.emon.dictionaryapp.MAINACTIVITY");
					startActivity(menuIntent);
				}catch(InterruptedException e){
					e.printStackTrace();
				}finally{
					finish();
				}
			}
		};
		logoTimer.start();
	}
}
