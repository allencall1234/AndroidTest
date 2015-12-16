package com.zlt.main;

import java.util.List;

import com.zlt.test.BeanTest;
import com.zlt.test.CameraDemo;
import com.zlt.test.ConfigurationDemo;
import com.zlt.test.CustomViewDemo;
import com.zlt.test.FilterableDemo;
import com.zlt.test.FloatingButton;
import com.zlt.test.FriendsDemo;
import com.zlt.test.HandlerTest;
import com.zlt.test.HttpTestDemo;
import com.zlt.test.ImageLoaderDemo;
import com.zlt.test.MainActivity;
import com.zlt.test.ObjectTransDemo;
import com.zlt.test.PixelDemo;
import com.zlt.test.R;
import com.zlt.test.SerializeAndDeSerialize;
import com.zlt.test.SparseIntArrayTest;
import com.zlt.test.TouchView;
import com.zlt.test.WeatherDemo;
import com.zlt.thread.ThreadTestActivity;

import android.app.ActivityManager;
import android.app.ListActivity;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.content.Intent;
import android.graphics.Camera;
import android.os.Bundle;
import android.util.SparseIntArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.LinearLayout.LayoutParams;
import android.widget.AbsListView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class Main extends ListActivity {

	private String[] strs = null;

	public Settings mSettings;
	public TouchView mTouchView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		mSettings = Settings.getInstance(this);
		mTouchView = TouchView.getInstance(this);

		
		setupViews();
		setSelection(11);
	}

	private void setupViews() {
		// TODO Auto-generated method stub
		strs = getResources().getStringArray(R.array.activities);
		setListAdapter(new ArrayAdapter<String>(this,
				android.R.layout.simple_list_item_1, strs));
	}

	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		// TODO Auto-generated method stub
		super.onListItemClick(l, v, position, id);
		try {
			startActivity(new Intent(Main.this, Class.forName(strs[position])));
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		overridePendingTransition(R.anim.scale_out, 0);
	}
	
	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (mSettings.isEnableAssistiveTouch()) {
			mTouchView.showView();
		}
	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		super.onStop();
		if (!isAppOnForeground()) {
			if (mSettings.isEnableAssistiveTouch()) {
				mTouchView.removeView();
			}
		}
	}


	public boolean isAppOnForeground() {
		// Returns a list of application processes that are running on the
		// device

		ActivityManager activityManager = (ActivityManager) getApplicationContext()
				.getSystemService(Context.ACTIVITY_SERVICE);
		String packageName = getApplicationContext().getPackageName();

		List<RunningAppProcessInfo> appProcesses = activityManager
				.getRunningAppProcesses();
		if (appProcesses == null)
			return false;

		for (RunningAppProcessInfo appProcess : appProcesses) {
			// The name of the process that this object is associated with.
			if (appProcess.processName.equals(packageName)
					&& appProcess.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
				return true;
			}
		}

		return false;
	}

}
