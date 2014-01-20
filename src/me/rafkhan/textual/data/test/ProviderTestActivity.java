package me.rafkhan.textual.data.test;

import me.rafkhan.android.textual.R;
import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;

public class ProviderTestActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_provider_test);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.provider_test, menu);
		return true;
	}

}
