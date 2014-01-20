package me.rafkhan.textual.data;

import android.content.ContentProvider;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.net.Uri;
import android.util.Log;

public class SmsProvider extends ContentProvider {

	private static final String LOG = "SmsProvider";

	// AUTHORITY IN MANIFEST
	public static final String SCHEME = "content://";
	public static final String AUTHORITY = "me.rafkhan.textual.provider";
	public static final String URI_BASE = SCHEME + AUTHORITY + "/";
			
	public static final Uri URI_THREADS = Uri.parse(URI_BASE + "threads");
	

	private static final UriMatcher sURIMatcher = new UriMatcher(
			UriMatcher.NO_MATCH);

	public static final int ALL_THREADS = 0; // Get a list of all convos
	public static final int MESSAGES_FROM_THREAD_ID = 1; // Get all messages in
															// single convo

	static {
		sURIMatcher.addURI(AUTHORITY, "threads", ALL_THREADS);
		sURIMatcher.addURI(AUTHORITY, "threads/#", MESSAGES_FROM_THREAD_ID);
	}

	public SmsProvider() {
	}

	@Override
	public boolean onCreate() {
		// TODO: Implement this to initialize your content provider on startup.
		return false;
	}

	@Override
	public Cursor query(Uri uri, String[] projection, String selection,
			String[] selectionArgs, String sortOrder) {
		int match = sURIMatcher.match(uri);
		switch (match) {
		case ALL_THREADS:
			Log.e(LOG, "yay");
		}
		
		return null;
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
		// TODO: Implement this to handle requests to insert a new row.
		throw new UnsupportedOperationException("Not yet implemented");
	}

	@Override
	public int update(Uri uri, ContentValues values, String selection,
			String[] selectionArgs) {
		// TODO: Implement this to handle requests to update one or more rows.
		throw new UnsupportedOperationException("Not yet implemented");
	}
}
