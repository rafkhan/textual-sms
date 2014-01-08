package me.rafkhan.textual.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.util.Log;

/**
 * TODO: DOCUMENT
 */

public class TextMessageProvider extends ContentProvider {

	// Log tag
	private static final String LOG = "TextMessageProvider";

	// AUTHORITY IN MANIFEST
	public static final String AUTHORITY = "me.rafkhan.textual.textmessageprovider";
	public static final String SCHEME = "content://";

	// Use these for URI matching in query/getType
	public static final String MESSAGES = SCHEME + AUTHORITY + "/message";
	public static final Uri URI_MESSAGES = Uri.parse(MESSAGES);
	public static final String MESSAGES_BASE = MESSAGES + "/";

	private DatabaseHandler database;

	public TextMessageProvider() {
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

	@Override
	public Uri insert(Uri uri, ContentValues values) {
		SQLiteDatabase db = this.database.getWritableDatabase();
		long id = db.insert(TextMessage.TABLE_NAME, null, values);
		this.notifyProviderOnPersonChange();
		return Uri.parse(MESSAGES_BASE + id);
	}

	@Override
	public boolean onCreate() {
		// TODO: Implement this to initialize your content provider on startup.
		this.database = new DatabaseHandler(this.getContext());
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		Cursor result = null;
		if (URI_MESSAGES.equals(uri)) {
			result = DatabaseHandler
					.getInstance(getContext())
					.getReadableDatabase()
					.query(TextMessage.TABLE_NAME, TextMessage.FIELDS, null,
							null, null, null, null, null);
			result.setNotificationUri(getContext().getContentResolver(),
					URI_MESSAGES);
		} else if (uri.toString().startsWith(MESSAGES_BASE)) {
			Log.e(LOG, "Other URI that starts with authority");
			throw new UnsupportedOperationException("Not yet implemented");
		}

		return result;
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO: Implement this to handle requests to update one or more rows.
		throw new UnsupportedOperationException("Not yet implemented");
	}

	public void notifyProviderOnPersonChange() {
		getContext().getContentResolver().notifyChange(
				TextMessageProvider.URI_MESSAGES, null, false);
	}
}
