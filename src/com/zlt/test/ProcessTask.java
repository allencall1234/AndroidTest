package com.zlt.test;

import java.util.List;

import android.app.Activity;
import android.app.ActivityManager;
import android.app.ActivityManager.RunningAppProcessInfo;
import android.content.Context;
import android.os.AsyncTask;

public class ProcessTask extends AsyncTask<String,Integer,Boolean> {
	
	Activity activity;
	
	public ProcessTask(Activity activity) {
		// TODO Auto-generated constructor stub
		this.activity = activity;
	}
	
	IProcessCheckFinishedListener processCheckFinishedListener;
	
	public void setProcessCheckFinishedListener(
			IProcessCheckFinishedListener processCheckFinishedListener) {
		this.processCheckFinishedListener = processCheckFinishedListener;
	}
	
	@Override
	protected Boolean doInBackground(String... params) {
		// TODO Auto-generated method stub
		ActivityManager manager = (ActivityManager) activity.getSystemService(Context.ACTIVITY_SERVICE);
		
		List<RunningAppProcessInfo> appProcess = manager.getRunningAppProcesses();
		
		for (RunningAppProcessInfo runningAppProcessInfo : appProcess) {
			if (runningAppProcessInfo.equals(activity.getPackageName())) {
				if (runningAppProcessInfo.importance == RunningAppProcessInfo.IMPORTANCE_VISIBLE
						|| runningAppProcessInfo.importance == RunningAppProcessInfo.IMPORTANCE_FOREGROUND) {
					return false;
				}else {
					return true;
				}
			}
		}
		
		return false;
	}
	
	@Override
	protected void onPostExecute(Boolean result) {
		// TODO Auto-generated method stub
		super.onPostExecute(result);
		
		if (processCheckFinishedListener != null) {
			processCheckFinishedListener.onProcessCheckFinished(result);
		}
	}

}
