//
//  Copyright 2011 Catch.com, Inc.
//  
//  Licensed under the Apache License, Version 2.0 (the "License");
//  you may not use this file except in compliance with the License.
//  You may obtain a copy of the License at
//  
//      http://www.apache.org/licenses/LICENSE-2.0
//  
//  Unless required by applicable law or agreed to in writing, software
//  distributed under the License is distributed on an "AS IS" BASIS,
//  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
//  See the License for the specific language governing permissions and
//  limitations under the License.
//

package com.catchnotes.samples.integration;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import com.catchnotes.integration.IntentIntegrator;

public class IntentTestActivity extends Activity {
	private IntentIntegrator _notesIntent;
	private final int IMAGE_PICKED = 0;
		
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        _notesIntent = new IntentIntegrator(this);
        
        ((Button)findViewById(R.id.create_simple_note_button)).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				_notesIntent.createNote("My Simple Note\n\n#sample");
			}        	
        });
        
        ((Button)findViewById(R.id.create_image_note_button)).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
		          Intent galleryIntent = new Intent(Intent.ACTION_PICK);
		          galleryIntent.setType("image/*");
		          if(galleryIntent.resolveActivity(getPackageManager()) != null){
		          	startActivityForResult(Intent.createChooser(galleryIntent,
		        		  	"Choose from:"), IMAGE_PICKED);
		          }
			}        	
        });
        
        ((Button)findViewById(R.id.create_quick_note_button)).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				_notesIntent.createNote("My Quick Note\n\n#sample", true);
			}        	
        });
        
        ((Button)findViewById(R.id.create_location_note_button)).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				// Create a sample location
				Location location = new Location(LocationManager.NETWORK_PROVIDER);
				location.setLatitude(30.267153);
				location.setLongitude(-97.743061);
			       
				_notesIntent.createNote("My Location Tagged Note\n\n#sample", location);
			}        	
        });
        
        ((Button)findViewById(R.id.view_notes_button)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                _notesIntent.viewNotes(null, "sample", null);
            }           
        });
        
        ((Button)findViewById(R.id.view_tagged_notes_button)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                ArrayList<String> tags = new ArrayList<String>(1);
                tags.add("sample");
                _notesIntent.viewNotes(null, null, tags);
            }           
        }); 
        
        ((Button)findViewById(R.id.view_stream_button)).setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                _notesIntent.viewNotes("Sample", null, null);
            }           
        }); 
        
        ((Button)findViewById(R.id.cursor_positioning_button)).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				_notesIntent.createNote("Cursor Positioning\n\nExample", 19);
			}        	
        });
        
        ((Button)findViewById(R.id.voice_recording_button)).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				_notesIntent.recordVoice("My Voice Note\n\n#sample");
			}        	
        });
        
        ((Button)findViewById(R.id.reminder_button)).setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				_notesIntent.addReminder("My Reminder Note\n\n#sample");
			}        	
        }); 
    }
    
    @Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
    	if (resultCode == RESULT_OK) {
    		switch (requestCode) {
    			case IMAGE_PICKED:
    				_notesIntent.createNote("My Image Note\n\n#sample", data.getData(), null, false);
    				break;
    		}
    	}
    	
		super.onActivityResult(requestCode, resultCode, data);
	}
}
