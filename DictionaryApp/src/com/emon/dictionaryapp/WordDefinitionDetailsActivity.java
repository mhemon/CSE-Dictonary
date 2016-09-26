package com.emon.dictionaryapp;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

public class WordDefinitionDetailsActivity extends Activity {
	
	TextView wordtextView;
	TextView definationTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_word_definition_details);
//		Log.d("DICTIONARY","third activity started");
        
		wordtextView=(TextView) findViewById(R.id.wordTextView);
		definationTextView=(TextView) findViewById(R.id.DefinitionTextView);
		
		wordtextView.setText(getIntent().getStringExtra("word"));
		definationTextView.setText(getIntent().getStringExtra("definition"));
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.word_definition_details, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
