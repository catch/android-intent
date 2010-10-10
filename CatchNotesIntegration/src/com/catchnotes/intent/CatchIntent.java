package com.catchnotes.intent;

public final class CatchIntent {
	// Intent actions
	public static final String ACTION_ADD = "com.catchnotes.intent.action.ADD";
	public static final String ACTION_VIEW = "com.catchnotes.intent.action.VIEW";
	
	// Intent extras for ACTION_ADD
	public static final String EXTRA_SOURCE = "com.catchnotes.intent.extra.SOURCE";
	public static final String EXTRA_LOCATION = "com.catchnotes.intent.extra.LOCATION";
	public static final String EXTRA_CURSOR_POSITION = "com.catchnotes.intent.extra.CURSOR_POSITION";
	public static final String EXTRA_AUTOSAVE = "com.catchnotes.intent.extra.AUTOSAVE";
	
	// Intent extras for ACTION_VIEW
	public static final String EXTRA_VIEW_FILTER = "com.catchnotes.intent.extra.VIEW_FILTER";
}
