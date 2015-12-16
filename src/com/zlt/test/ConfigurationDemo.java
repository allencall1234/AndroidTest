package com.zlt.test;

import com.zlt.main.BaseActivity;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.net.Uri;
import android.os.Bundle;
import android.provider.Contacts.People;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class ConfigurationDemo extends BaseActivity {

	static final int PICK_REQUEST = 1337;
	Button viewButton = null;
	Uri contact = null;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// setContentView(R.layout.main);

		setupViews();
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		// TODO Auto-generated method stub
		super.onConfigurationChanged(newConfig);
		Log.i("zlt", "newconfig = " + newConfig + ",contact = " + contact);
//		setupViews();
	}
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Activity#onActivityResult(int, int,
	 * android.content.Intent)
	 */
	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		// TODO Auto-generated method stub
		// super.onActivityResult(requestCode, resultCode, data);

		if (requestCode == PICK_REQUEST) {

			if (resultCode == RESULT_OK) {

				contact = data.getData();
				viewButton.setEnabled(true);
			}

		}

	}
 
	private void setupViews() {

		setContentView(R.layout.configuration);

		Button pickBtn = (Button) findViewById(R.id.pick);

		pickBtn.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				// TODO Auto-generated method stub

				Intent i = new Intent(Intent.ACTION_PICK, People.CONTENT_URI);
				startActivityForResult(i, PICK_REQUEST);
			}
		});

		viewButton = (Button) findViewById(R.id.view);

		viewButton.setOnClickListener(new View.OnClickListener() {
			public void onClick(View view) {
				Log.i("zlt", "contact = " + contact);
				startActivity(new Intent(Intent.ACTION_VIEW, contact));
			}
		});
		
		viewButton.setEnabled(contact != null);
	}

}
