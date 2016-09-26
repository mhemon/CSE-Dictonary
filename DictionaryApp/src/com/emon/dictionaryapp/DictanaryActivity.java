package com.emon.dictionaryapp;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

public class DictanaryActivity extends Activity {

	TextView userTextView;
	EditText searchEditText;
	Button searchButton;
	ListView dictionaryListView;
	


	DictanaryDataBaseHelper myDictanaryDataBaseHelper;
	SharedPreferences sharedPreferences;
	String logTagString = "DICTIONARY";
	ArrayList<WordDefination> allwordDefinations = new ArrayList<WordDefination>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
		setContentView(R.layout.activity_dictanary);
		// Log.d("DICTIONARY","secend activity started");
		userTextView = (TextView) findViewById(R.id.personTextView);
		userTextView.setText(getIntent().getStringExtra(
				MainActivity.USER_NAME_STRING)
				+ "'s Dictionary");

		searchEditText = (EditText) findViewById(R.id.searchEditText);
		searchButton = (Button) findViewById(R.id.searchButton);
		dictionaryListView = (ListView) findViewById(R.id.dictionaryListView);

		myDictanaryDataBaseHelper = new DictanaryDataBaseHelper(this,
				"Dictionary", null, 1);
		sharedPreferences = getSharedPreferences(
				MainActivity.SHARE_NAME_STRING, MODE_PRIVATE);

		boolean initialized = sharedPreferences
				.getBoolean("initialized", false);

		if (initialized == false) {
			// Log.d(logTagString, "initializing for the first time");
			initializeDatabase();
			SharedPreferences.Editor editor = sharedPreferences.edit();
			editor.putBoolean("initialized", true);
			editor.commit();

		} else {
			Log.d(logTagString, "db already initialized");
		}

		allwordDefinations = myDictanaryDataBaseHelper.getAllWords();

		dictionaryListView.setAdapter(new BaseAdapter() {

			private int lastPosition = -1;

			@Override
			public View getView(int position, View view, ViewGroup arg2) {
				if (view == null) {
					view = getLayoutInflater()
							.inflate(R.layout.list_item, null);
				}

				TextView textView = (TextView) view
						.findViewById(R.id.listItemTextView);
				textView.setText(allwordDefinations.get(position).word);

				Animation animation = AnimationUtils.loadAnimation(view
						.getContext(),
						(position > lastPosition) ? R.anim.up_from_bottom
								: R.anim.down_from_top);
				view.startAnimation(animation);
				lastPosition = position;

				return view;
			}

			@Override
			public long getItemId(int arg0) {
				// TODO Auto-generated method stub
				return 0;
			}

			@Override
			public Object getItem(int arg0) {
				// TODO Auto-generated method stub
				return null;
			}

			@Override
			public int getCount() {
				// TODO Auto-generated method stub
				return allwordDefinations.size();
			}
		});

		// Animation animation =
		// AnimationUtils.loadAnimation(DictanaryActivity.this, R.anim.trans);
		// dictionaryListView.startAnimation(animation);
		
		dictionaryListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View view,
					int position, long arg3) {
				Intent intent = new Intent(DictanaryActivity.this,
						WordDefinitionDetailsActivity.class);
				intent.putExtra("word", allwordDefinations.get(position).word);
				intent.putExtra("definition",
						allwordDefinations.get(position).defination);

				startActivity(intent);
			}
		});

		searchButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				String string = searchEditText.getText().toString();

				WordDefination wordDefinition = myDictanaryDataBaseHelper
						.getWordDefinition(string);

				if (wordDefinition == null) {
					Toast.makeText(DictanaryActivity.this, "Word not found",
							Toast.LENGTH_LONG).show();
				} else {
					Intent intent = new Intent(DictanaryActivity.this,
							WordDefinitionDetailsActivity.class);
					intent.putExtra("word", wordDefinition.word);
					intent.putExtra("definition", wordDefinition.defination);

					startActivity(intent);
				}

			}
		});

	}

	private void initializeDatabase() {
		InputStream inputStream = getResources().openRawResource(
				R.raw.dictionary);
		BufferedReader bufferedReader = new BufferedReader(
				new InputStreamReader(inputStream));
		DictanaryLoader.loadData(bufferedReader, myDictanaryDataBaseHelper);

	}
	
	
	
	
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        if (id == R.id.action_settings) {
        	
        	AlertDialog.Builder builder= new AlertDialog.Builder(DictanaryActivity.this);
        	builder.setMessage("This is cse dictionary. it,s content 15k word's");
        	builder.setCancelable(false);
        	builder.setPositiveButton("OK", new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int arg1) {
					// TODO Auto-generated method stub
					dialog.cancel();
				}
			});
        	
        	AlertDialog alert = builder.create();
        	alert.show();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
	
}
