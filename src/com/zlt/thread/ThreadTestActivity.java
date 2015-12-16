package com.zlt.thread;

import com.zlt.main.Main;
import com.zlt.main.Settings;
import com.zlt.test.BeanTest;
import com.zlt.test.ConfigurationDemo;
import com.zlt.test.FilterableDemo;
import com.zlt.test.FloatingButton;
import com.zlt.test.FriendsDemo;
import com.zlt.test.HttpTestDemo;
import com.zlt.test.ImageLoaderDemo;
import com.zlt.test.MainActivity;
import com.zlt.test.ObjectTransDemo;
import com.zlt.test.PixelDemo;
import com.zlt.test.R;
import com.zlt.test.SerializeAndDeSerialize;
import com.zlt.test.SparseIntArrayTest;
import com.zlt.test.WeatherDemo;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.AdapterView.OnItemClickListener;

public class ThreadTestActivity extends ListActivity {

	private ListView mListView = null;
	private String[] strs = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		mListView = getListView();

		setupViews();

		mListView.setSelection(mListView.getCount() - 1);
	}

	private void setupViews() {
		// TODO Auto-generated method stub
		strs = new String[] { "TimerTaskActivity" };

		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, strs));
		mListView.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				// TODO Auto-generated method stub
				startActivity(new Intent(ThreadTestActivity.this,
						getClassByPositon(position)));
				overridePendingTransition(R.anim.scale_out, 0);
			}
		});
	}

	protected Class<?> getClassByPositon(int position) {
		// TODO Auto-generated method stub
		switch (position) {
		case 0:
			return TimerTaskActivity.class;
		default:
			break;
		}
		return null;
	}
}
