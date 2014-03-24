package me.rafkhan.textual;

import me.rafkhan.android.textual.R;
import me.rafkhan.textual.data.TextMessage;
import me.rafkhan.textual.data.TextMessageProvider;
import android.app.Activity;
import android.app.LoaderManager;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.Loader;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

public class MainActivity extends Activity implements
		LoaderManager.LoaderCallbacks<Cursor> {

	private SimpleCursorAdapter mAdapter;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		Log.e("asd", "Wut");

		this.setupListView();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	public void setupListView() {
		ListView lv = (ListView) this.findViewById(R.id.listView1);
		
		TextMessage tm = new TextMessage();
		tm.sender = "123";
		tm.message = "Message body here";
		tm.timestamp = 1234;
		
		Log.e("asd", "About to insert");
		this.getContentResolver().insert(TextMessageProvider.URI_MESSAGES, tm.getContent());

		// For the cursor adapter, specify which columns go into which views
		String[] fromColumns = TextMessage.FIELDS;
		
		//int[] toViews = { R.id.cnv_list_id, R.id.cnv_list_sender,
		//		R.id.cnv_list_msg, R.id.cnv_list_time, R.id.cnv_list_read };
		
		int[] toViews = {R.id.cnv_list_msg};

		// Create an empty adapter we will use to display the loaded data.
		// We pass null for the cursor, then update it in onLoadFinished()
		this.mAdapter = new SimpleCursorAdapter(this,
				R.layout.conversation_list_item, null, fromColumns, toViews, 0);

		lv.setAdapter(this.mAdapter);
		lv.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				Intent i = new Intent(parent.getContext(), ConversationActivity.class);
				i.putExtra(TextMessage.COL_ID, id);
				startActivity(i);
			}
		});
		getLoaderManager().initLoader(0, null, this);
	}

	@Override
	public Loader<Cursor> onCreateLoader(int id, Bundle args) {
		// TODO Auto-generated method stub
		Uri messagesUri = TextMessageProvider.URI_MESSAGES;
		
		//TODO PUT FIELDS ARRAY HERE
		return new CursorLoader(this, messagesUri, TextMessage.FIELDS, "*", null,
				null);
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
