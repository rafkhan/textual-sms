package me.rafkhan.textual.data;

import android.content.ContentValues;
import android.database.Cursor;

/**
 * You shouldn't manually create one of these, generate
 * from cursor for convenience.
 * 
 * Kind of a pointless class? yes.
 */

public class ConversationList extends TextMessage {
	
	public static final String TABLE_NAME = "ConversationList";

	public static final String COL_READ = "read";

	public static String[] FIELDS = { COL_ID, COL_SENDER, COL_MESSAGE,
			COL_TIMESTAMP, COL_READ };
	
	public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
			+ "(" + COL_ID + " INTEGER PRIMARY KEY,"
			+ COL_SENDER + " TEXT NOT NULL DEFAULT '',"
			+ COL_MESSAGE + " TEXT NOT NULL DEFAULT '',"
			+ COL_TIMESTAMP + " INTEGER NOT NULL DEFAULT 0," // leave this
			+ COL_READ + " BOOLEAN NOT NULL DEFAULT FALSE"
			+ ")";
	
	
	public boolean read = false;

	/**
	 * Uses Default fields
	 */
	public ConversationList() {
	}

	public ConversationList(final Cursor cursor) {
		// Indices expected to match order in FIELDS!
		super(cursor);
		
		//start at 4, reference TextMessage.java
		int read = cursor.getInt(4);
		this.read = read == 1;
	}
	
	@Override
	public ContentValues getContent() {
		ContentValues cv = super.getContent();
		cv.put(COL_READ, this.read);
		return cv;
	}

}
