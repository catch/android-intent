package com.catchnotes.samples.integration;

import com.catchnotes.integration.IntentIntegrator;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class IntentTestActivity extends Activity {
	private IntentIntegrator _notesIntent;
	private final int IMAGE_PICKED = 0xABBA;
		
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        _notesIntent = new IntentIntegrator(this);
        
        Button button1 = (Button)findViewById(R.id.create_simple_note_button);
        button1.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				_notesIntent.createNote("My Simple Note\n\n#sample");
			}        	
        });
        
        Button button2 = (Button)findViewById(R.id.create_image_note_button);
        button2.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
		          Intent galleryIntent = new Intent("android.intent.action.PICK");
		          galleryIntent.setType("image/*");
		          startActivityForResult(Intent.createChooser(galleryIntent,
		        		  "Choose from:"), IMAGE_PICKED);
			}        	
        });
        
        Button button3 = (Button)findViewById(R.id.create_quick_note_button);
        button3.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				_notesIntent.createNote("My Quick Note\n\n#sample", true);
			}        	
        });

        Button button4 = (Button)findViewById(R.id.create_location_note_button);
        button4.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// Create a sample location
				Location location = new Location(LocationManager.NETWORK_PROVIDER);
				location.setLatitude(30.267153);
				location.setLongitude(-97.743061);
			       
				_notesIntent.createNote("My Location Tagged Note\n\n#sample", location);
			}        	
        });
        
        Button button5 = (Button)findViewById(R.id.view_notes_button);
        button5.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				_notesIntent.viewNotes("#sample");
			}        	
        });        
        
        Button button6 = (Button)findViewById(R.id.cursor_positioning_button);
        button6.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				_notesIntent.createNote("Cursor Positioning\n\nExample", 19);
			}        	
        });        
    }
    
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (requestCode == IMAGE_PICKED && resultCode == RESULT_OK) {
			_notesIntent.createNote("My Image Note\n\n#sample", data.getData());
    	}
    	
		super.onActivityResult(requestCode, resultCode, data);
	}
}