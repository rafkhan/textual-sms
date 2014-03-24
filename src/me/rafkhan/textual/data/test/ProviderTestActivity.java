package me.rafkhan.textual.data.test;

import me.rafkhan.android.textual.R;
import me.rafkhan.textual.data.DatabaseHandler;
import me.rafkhan.textual.data.TextMessage;
import android.app.Activity;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;

/**
 * THIS IS GONE AS SOON AS JUNIT WORKS
 * All the code will be copied over
 */
public class ProviderTestActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_provider_test);
		
		this.resetDatabase();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.provider_test, menu);
		return true;
	}
	
	private void resetDatabase() {
		//this.deleteDatabase(TextMessage.TABLE_NAME);
		//this.deleteDatabase(SmsThread.TABLE_NAME);
		
		SQLiteDatabase db = DatabaseHandler.getInstance(this).getWritableDatabase();
		
		// Drop tables
		db.delete(TextMessage.TABLE_NAME, "1", new String[]{});
		
		// Recreate them
		db.execSQL(TextMessage.CREATE_TABLE);
		
		db.close();
	}
	
	private void insertTestData() {
		
	}

}
