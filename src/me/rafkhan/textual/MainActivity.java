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
		String[] fromColumns = ConversationList.FIELDS;
		int[] toViews = { R.id.cnv_list_id, R.id.cnv_list_sender,
				R.id.cnv_list_msg, R.id.cnv_list_time, R.id.cnv_list_read };

		// Create an empty adapter we will use to display the loaded data.
		// We pass null for the cursor, then update it in onLoadFinished()
		this.mAdapter = new SimpleCursorAdapter(this,
				R.layout.conversation_list_item, null, fromColumns, toViews, 0);

		lv.setAdapter(this.mAdapter);
		getLoaderManager().initLoader(0, null, this);
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		// TODO Auto-generated method stub
		Uri cv = ConversationListProvider.URI_CONVERSATIONS;
		return new CursorLoader(this, cv, ConversationList.FIELDS, "*", null, null);
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
