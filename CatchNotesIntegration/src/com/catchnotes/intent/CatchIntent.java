package com.catchnotes.intent;

public final class CatchIntent {
	// Intent actions
	public static final String ACTION_ADD = "com.catchnotes.intent.action.ADD";
	public static final String ACTION_VIEW = "com.catchnotes.intent.action.VIEW";
	public static final String ACTION_ADD_VOICE = "com.catchnotes.intent.action.ADD_VOICE";
	public static final String ACTION_ADD_REMINDER = "com.catchnotes.intent.action.ADD_REMINDER";

	// Intent extras for ACTION_ADD
	public static final String EXTRA_SOURCE = "com.catchnotes.intent.extra.SOURCE";
	public static final String EXTRA_SOURCE_URL = "com.catchnotes.intent.extra.SOURCE_URL";
	public static final String EXTRA_LOCATION = "com.catchnotes.intent.extra.LOCATION";
	public static final String EXTRA_CURSOR_POSITION = "com.catchnotes.intent.extra.CURSOR_POSITION";
	public static final String EXTRA_AUTOSAVE = "com.catchnotes.intent.extra.AUTOSAVE";
	public static final String EXTRA_VOICE = "com.catchnotes.intent.extra.VOICE";
	public static final String EXTRA_REMINDER = "com.catchnotes.intent.extra.REMINDER";
    
	// Intent extras for ACTION_VIEW
	public static final String EXTRA_VIEW_FILTER = "com.catchnotes.intent.extra.VIEW_FILTER";
}
