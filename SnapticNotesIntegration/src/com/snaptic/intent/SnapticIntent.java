package com.snaptic.intent;

public final class SnapticIntent {
	// Intent actions
	public static final String ACTION_ADD = "com.snaptic.intent.action.ADD";
	public static final String ACTION_VIEW = "com.snaptic.intent.action.VIEW";
	
	// Intent extras for ACTION_ADD
	public static final String EXTRA_SOURCE = "com.snaptic.intent.extra.SOURCE";
	public static final String EXTRA_LOCATION = "com.snaptic.intent.extra.LOCATION";
	public static final String EXTRA_CURSOR_POSITION = "com.snaptic.intent.extra.CURSOR_POSITION";
	public static final String EXTRA_AUTOSAVE = "com.snaptic.intent.extra.AUTOSAVE";
	
	// Intent extras for ACTION_VIEW
	public static final String EXTRA_VIEW_FILTER = "com.snaptic.intent.extra.VIEW_FILTER";
}
