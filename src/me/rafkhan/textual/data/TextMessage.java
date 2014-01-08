package me.rafkhan.textual.data;

import android.content.ContentValues;
import android.database.Cursor;

public class TextMessage {
	public static final String TABLE_NAME = "TextMessages";
	public static final String COL_ID = "_id";

	public static final String COL_SENDER = "sender";
	public static final String COL_MESSAGE = "message";
	public static final String COL_TIMESTAMP = "timestamp";

	public static String[] FIELDS = { COL_ID, COL_SENDER, COL_MESSAGE,
			COL_TIMESTAMP };

	public static final String CREATE_TABLE = "CREATE TABLE " + TABLE_NAME
			+ "(" + COL_ID + " INTEGER PRIMARY KEY,"
			+ COL_SENDER + " TEXT NOT NULL DEFAULT '',"
			+ COL_MESSAGE + " TEXT NOT NULL DEFAULT '',"
			+ COL_TIMESTAMP + " TIMESTAMP DEFAULT CURRENT_TIMESTAMP" // leave this
			+ ")";

	public long id = -1;
	public String sender = "";
	public String message = "";
	public long timestamp = 0;
	
	/**
	 * Uses Default fields
	 */
	public TextMessage() {
	}

	public TextMessage(final Cursor cursor) {
		// Indices expected to match order in FIELDS!
		this.id = cursor.getLong(0);
		this.sender = cursor.getString(1);
		this.message = cursor.getString(2);
		this.timestamp = cursor.getLong(3);
	}

	/**
	 * Return the fields in a ContentValues object, suitable for insertion into
	 * the database.
	 */
	public ContentValues getContent() {
		final ContentValues values = new ContentValues();
		// Note that ID is NOT included here
		values.put(COL_SENDER, this.sender);
		values.put(COL_MESSAGE, this.message);
		values.put(COL_TIMESTAMP, this.timestamp);
		return values;
	}

}
