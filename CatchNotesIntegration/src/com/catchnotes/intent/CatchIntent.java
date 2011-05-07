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
