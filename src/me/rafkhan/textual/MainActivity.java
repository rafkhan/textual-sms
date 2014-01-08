package me.rafkhan.textual;

import me.rafkhan.android.textual.R;
import me.rafkhan.textual.data.ConversationList;
import me.rafkhan.textual.data.ConversationListProvider;
import me.rafkhan.textual.data.TextMessage;
import me.rafkhan.textual.data.TextMessageProvider;
import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends Activity implements
		LoaderManager.LoaderCallbacks<Cursor> {

	// These are the Contacts rows that we will retrieve
	private static final String[] sPROJECTION = new String[] { TextMessage.COL_MESSAGE };
	// This is the select criteria
	private static final String sSELECTION = "*";

	private SimpleCursorAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		setupTestList();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void setupTestList() {
		ListView lv = (ListView) this.findViewById(R.id.listView1);

		/*
		TextMessage tm = new TextMessage();
		tm.sender = "6471231231";
		tm.message = "okay!";
		tm.timestamp = 12345678;
		this.getContentResolver().insert(ConversationListProvider.URI_CONVERSATIONS,
				tm.getContent());
		 */

		// For the cursor adapter, specify which columns go into which views
		String[] fromColumns = { ConversationList.COL_MESSAGE };
		int[] toViews = { android.R.id.text1 }; // The TextView in
												// simple_list_item_1

		// Create an empty adapter we will use to display the loaded data.
		// We pass null for the cursor, then update it in onLoadFinished()
		this.mAdapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_list_item_1, null, fromColumns,
				toViews, 0);

		lv.setAdapter(this.mAdapter);
		getLoaderManager().initLoader(0, null, this);
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		// TODO Auto-generated method stub
		Uri cv = ConversationListProvider.URI_CONVERSATIONS;
		Uri msg = TextMessageProvider.URI_MESSAGES;

		return new CursorLoader(this, msg, sPROJECTION, sSELECTION, null, null);
	}

	@Override
	public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
		// TODO Auto-generated method stub
		mAdapter.swapCursor(data);
	}

	@Override
	public void onLoaderReset(Loader<Cursor> loader) {
		// TODO Auto-generated method stub
		mAdapter.swapCursor(null);
	}

}
