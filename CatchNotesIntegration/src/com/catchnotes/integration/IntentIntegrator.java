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

package com.catchnotes.integration;

import com.catchnotes.intent.CatchIntent;
import com.catchnotes.samples.integration.R;

import android.app.AlertDialog;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.location.Location;
import android.net.Uri;
import android.widget.Toast;

public class IntentIntegrator {
	// Note: "3banana" was the original name of Catch Notes. Though it has been
	// rebranded, the package name must persist.
	private static final String NOTES_PACKAGE_NAME = "com.threebanana.notes"; 
	private static final String NOTES_MARKET_URI = "http://market.android.com/search?q=pname:" + NOTES_PACKAGE_NAME;
	
	private static final int NOTES_MIN_VERSION_CODE = 54;
	
	private final Context _context;
	
	public IntentIntegrator(Context context) {
		_context = context;
	}
	
	public void createNote(String message) {
		createNote(message, null, 0, -1, false, null, null, false);
	}

	public void createNote(String message, int cursorPosition) {
		createNote(message, null, 0, cursorPosition, false, null, null, false);
	}

	public void createNote(String message, boolean autoSave) {
		createNote(message, null, 0, -1, autoSave, null, null, false);
	}

	public void createNote(String message, Uri mediaUri, String mimeType, boolean isVoiceNote) {
		createNote(message, null, 0, -1, false, mediaUri, mimeType, isVoiceNote);
	}
	
	public void createNote(String message, Location location) {
		createNote(message, location, 0, -1, false, null, null, false);
	}
	
	public void createNote(String message, long reminder) {
		createNote(message, null, reminder, -1, false, null, null, false);
	}

	public void createNote(String message, Location location, long reminder, int cursorPosition, boolean autoSave, Uri mediaUri, String mimeType, boolean isVoiceNote) {
		// Verify that correct version of notes is installed
		if (!isNotesInstalled()) {
			return;
		}
		
		// Create the Intent		
		Intent intent = new Intent();
		
		// This action signifies you want to add a new note to the user's notebook
		intent.setAction(CatchIntent.ACTION_ADD);
		
		// Mandatory. This will be the content of the note. The object should be
		// a String.
		intent.putExtra(Intent.EXTRA_TEXT, message);

		// Mandatory; EXTRA_SOURCE identifies your app as the source
		// for this note. Don't use the example below; please arrange with the
		// Catch development team for the string you will use to identify your
		// app. The object should be a String.
		intent.putExtra(CatchIntent.EXTRA_SOURCE, "Catch Intent Test Utility"); // TODO: *** change this to your own source string! ***
		
		// Mandatory; EXTRA_SOURCE_URL identifies a URL which will be presented
		// in conjunction with your EXTRA_SOURCE field. This should link to your
		// site, app, or other relevant web asset. The object should be a String.
		intent.putExtra(CatchIntent.EXTRA_SOURCE_URL, "https://catch.com/");    // TODO: *** change this to your own source URL! ***
		
		// Optional; if EXTRA_TITLE is supplied it will appear in the
		// titlebar of the note editor activity in Catch Notes. The object should be
		// a String. It will appear as:
		//
		//    New note: <your title>
		//
		intent.putExtra(Intent.EXTRA_TITLE, "testing Catch Intents");			// TODO: *** change this to your own title (or leave the extra out) ***

		// Optional: include a media attachment. The attachment Uri should be
		// accessible to external packages (i.e., don't point to content private
		// to your application).
		if (mediaUri != null) {
			intent.putExtra(Intent.EXTRA_STREAM, mediaUri);
			
			// If you don't supply a MIME type, Catch Notes will attempt to
			// figure it out based on ContentProvider hints or the filename extension.
			//
			// Note that the Catch sync servers do strict MIME type checking; if you
			// misrepresent the MIME type of a media attachment, your users will not be
			// able to sync them.
			if (mimeType != null) {
				intent.setType(mimeType);
			}
			
			// Note that Catch Notes will enforce restrictions of what MIME types
			// can be considered voice recordings: "audio/*", "video/*", "application/ogg".
			// Also note that not all media files that match these descriptors will
			// play in the player controllers of Catch Notes (Android, iOS, or web).
			// Only certain common codecs and container formats are supported.
			// For Android, we suggest "video/3gpp" with AMR narrowband audio or
			// "video/mp4" with AAC audio for best compatibility.
			//
			// Audio attachments that do not have this voice note flag set will
			// be treated like regular file attachments to the note.
			if (isVoiceNote) {
				intent.putExtra(CatchIntent.EXTRA_VOICE, true);
			}
		}
		
		// Optional: include a location. The object should be a Location.
		if (location != null) {
			intent.putExtra(CatchIntent.EXTRA_LOCATION, location);		
		}
		
		// Optional: include a reminder. This value is standard system
		// millisecond time (milliseconds since January 1, 1970 00:00:00 UTC)
		if (reminder > System.currentTimeMillis()) {
			intent.putExtra(CatchIntent.EXTRA_REMINDER, reminder);		
		}
		
		// Optional: specify a cursor position for the editor. The type should
		// be an int.
		if (cursorPosition >= 0) {
			intent.putExtra(CatchIntent.EXTRA_CURSOR_POSITION, cursorPosition);
		}
		
		// Optional: specify autosave. Intents with autosave set will send the
		// note and its contents, save it immediately, and return to your
		// activity. You may want to provide feedback to your users that the
		// action completed. The type should be a boolean.
		if (autoSave) {
			intent.putExtra(CatchIntent.EXTRA_AUTOSAVE, true);
		}

		// Start the Intent
		startNotesIntent(intent);
	}

	public void viewNotes(String tag) {
		// Verify that correct version of notes is installed
		if (!isNotesInstalled()) {
			return;
		}

		// Prefix with hash if necessary
		if (!tag.startsWith("#")) {
			tag = "#" + tag;
		}
		
		// Create the Intent		
		Intent intent = new Intent();
		intent.setAction(CatchIntent.ACTION_VIEW);
		intent.putExtra(CatchIntent.EXTRA_VIEW_FILTER, tag);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);

		// Start the Intent
		startNotesIntent(intent);
	}
	
	// Ask Catch Notes to interactively record a voice note and save it to
	// the user's notebook. Text may be included with the Intent and the text
	// will be included in the text portion of the voice note entry. This
	// is an *interactive* Intent -- to add a pre-recorded voice note to Catch,
	// one would use CatchIntent.ACTION_ADD, include a URI to the voice recording
	// in Intent.EXTRA_STREAM, and include a boolean true extra as CatchIntent.EXTRA_VOICE.
	public void recordVoice(String message) {
		// Verify that correct version of notes is installed
		if (!isNotesInstalled()) {
			return;
		}
		
		// Create the Intent		
		Intent intent = new Intent();
		intent.setAction(CatchIntent.ACTION_ADD_VOICE);
		
		// Optional: text to include with the voice note.
		if (message != null) {
			intent.putExtra(Intent.EXTRA_TEXT, message);
		}

		// Start the Intent
		startNotesIntent(intent);
	}
	
	// Ask Catch Notes to interactively allow the user to set a reminder date/time
	// and save it to the user's notebook. Text may be included with the Intent
	// and the text will be included in the text portion of the entry. This
	// is an *interactive* Intent -- to add a note to Catch with a predetermined
	// reminder timestamp, one would use CatchIntent.ACTION_ADD, and include as
	// a long extra the reminder value as CatchIntent.EXTRA_REMINDER.
	public void addReminder(String message) {
		// Verify that correct version of notes is installed
		if (!isNotesInstalled()) {
			return;
		}
		
		// Create the Intent		
		Intent intent = new Intent();
		intent.setAction(CatchIntent.ACTION_ADD_REMINDER);
		
		// Optional: text to include with the reminder note.
		if (message != null) {
			intent.putExtra(Intent.EXTRA_TEXT, message);
		}

		// Start the Intent
		startNotesIntent(intent);
	}
	
	private boolean isNotesInstalled() {
		// Verify that correct version of notes is installed
		try {
			PackageInfo packageInfo = _context.getPackageManager().getPackageInfo(NOTES_PACKAGE_NAME, PackageManager.GET_ACTIVITIES);
			
			if (packageInfo.versionCode < NOTES_MIN_VERSION_CODE) {
				displayUpgradeDialog(packageInfo.applicationInfo.name);
				return false;
			}			
		} catch (NameNotFoundException e) {
			displayInstallDialog();
			return false;
		}
		
		return true;
	}
		
	private void displayInstallDialog() {
		AlertDialog.Builder builder = new AlertDialog.Builder(_context);
		
		builder.setTitle(R.string.install_notes_title);
		builder.setMessage(R.string.install_notes_message);
		builder.setIcon(R.drawable.market_icon);
		
		builder.setNegativeButton(R.string.cancel_button, null);
		
		builder.setPositiveButton(R.string.install_button, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				displayNotesMarketPage();
			}
		});
		
		builder.show();				
	}
	
	private void displayUpgradeDialog(String appName) {
		AlertDialog.Builder builder = new AlertDialog.Builder(_context);
		
		builder.setTitle(R.string.upgrade_notes_title);
		builder.setMessage(R.string.upgrade_notes_message);
		builder.setIcon(R.drawable.market_icon);
		
		builder.setNegativeButton(R.string.cancel_button, null);
		
		builder.setPositiveButton(R.string.upgrade_button, new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				displayNotesMarketPage();
			}
		});
		
		builder.show();		
	}
	
	private void displayNotesMarketPage() {
		try {
			Uri uri = Uri.parse(NOTES_MARKET_URI);
			Intent intent = new Intent(Intent.ACTION_VIEW, uri);
			intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_WHEN_TASK_RESET);
			_context.startActivity(intent);
		} catch (ActivityNotFoundException e) {
			e.printStackTrace();
			displayError(R.string.market_error_message);
		}
	}
	
	private void displayError(int messageId) {
        new AlertDialog.Builder(_context)
	        .setIcon(android.R.drawable.ic_dialog_alert)
	        .setTitle(R.string.error_dialog_title)
	        .setMessage(messageId)
	        .setPositiveButton(_context.getString(R.string.ok_button), null)
	        .show();			
	}	
	
	private void startNotesIntent(Intent intent) {		
		// Start the Intent
		try {
			_context.startActivity(intent);
			
			if (intent.hasExtra(CatchIntent.EXTRA_AUTOSAVE)) {
				// Pop up a mesage to let your users know when a quick note has
				// been added.
	    		Toast.makeText(_context,
	    				R.string.toast_quick_note,
	    				Toast.LENGTH_SHORT).show(); 
			}
		} catch (ActivityNotFoundException e) {
			e.printStackTrace();
			displayError(R.string.notes_intent_error);
		}		
	}
}
