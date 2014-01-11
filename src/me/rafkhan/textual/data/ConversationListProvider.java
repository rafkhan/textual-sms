package me.rafkhan.textual.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

/**
 * ConversationListProvider:
 * 
 * TODO: DOCUMENT
 */

public class ConversationListProvider extends ContentProvider {

	// Log tag
	private static final String LOG = "ConversationListProvider";

	// AUTHORITY IN MANIFEST
	public static final String AUTHORITY = "me.rafkhan.textual.conversationlistprovider";
	public static final String SCHEME = "content://";

	// Use these for URI matching in query/getType
	public static final String CONVERSATIONS = SCHEME + AUTHORITY
			+ "/conversation";
	public static final Uri URI_CONVERSATIONS = Uri.parse(CONVERSATIONS);
	public static final String CONVERSATIONS_BASE = CONVERSATIONS + "/";

	private static final UriMatcher sURIMatcher = new UriMatcher(
			UriMatcher.NO_MATCH);
	// Uri matcher IDs
	public static final int ALL_CONVERSATIONS = 0;

	static {
		sURIMatcher.addURI(AUTHORITY, "/", 0);
	}

	public ConversationListProvider() {
	}

	@Override
	public boolean onCreate() {
		// TODO: Implement this to initialize your content provider on startup.
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		Cursor result = null;

		int match = sURIMatcher.match(uri);
		switch (match) {
		case ALL_CONVERSATIONS:
			return this.queryAll();
		default:
			Log.e(LOG, "Unmatched URI");
			throw new UnsupportedOperationException("Not yet implemented");
		}
	}

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		/*
		 * String sender = values.getAsString(ConversationList.COL_SENDER);
		 * String message = values.getAsString(ConversationList.COL_MESSAGE);
		 * long timestamp = values.getAsLong(ConversationList.COL_TIMESTAMP);
		 * boolean read = values.getAsBoolean(ConversationList.COL_READ);
		 * 
		 * String query = "INSERT INTO " + ConversationList.TABLE_NAME + "(" +
		 * ConversationList.COL_SENDER + "," + ConversationList.COL_MESSAGE +
		 * "," + ConversationList.COL_TIMESTAMP + ") VALUES (" + "\'" + sender
		 * +"\'," + "\'" + message +"\'," + timestamp + ")";
		 */

		SQLiteDatabase db = DatabaseHandler.getInstance(getContext())
				.getWritableDatabase();
		String whereClause = ConversationList.COL_SENDER + " = \'"
				+ values.getAsString(ConversationList.COL_SENDER) + "\'";
		db.delete(ConversationList.TABLE_NAME, whereClause, null);
		long id = db.insert(ConversationList.TABLE_NAME, null, values);
		this.notifyProviderOnPersonChange();
		return Uri.parse(CONVERSATIONS_BASE + id);
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO: Implement this to handle requests to update one or more rows.
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public int delete(Uri uri, String selection, String[] selectionArgs) {
		// Implement this to handle requests to delete one or more rows.
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public String getType(Uri uri) {
		// TODO: Implement this to handle requests for the MIME type of the data
		// at the given URI.
		throw new UnsupportedOperationException("Not yet implemented");
	}

	public void notifyProviderOnPersonChange() {
		getContext().getContentResolver().notifyChange(
				ConversationListProvider.URI_CONVERSATIONS, null, false);
	}

	/*
	 * Returns every entry in the database
	 */
	private Cursor queryAll() {
		Cursor result = DatabaseHandler
				.getInstance(getContext())
				.getReadableDatabase()
				.query(ConversationList.TABLE_NAME, ConversationList.FIELDS,
						null, null, null, null, null, null);
		result.setNotificationUri(getContext().getContentResolver(),
				URI_CONVERSATIONS);

		return result;
	}
}
