package com.zlt.test;

import com.zlt.main.BaseActivity;

import android.os.Bundle;
import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.Menu;
import android.view.animation.LinearInterpolator;
import android.widget.TextView;

public class MainActivity extends BaseActivity {

	private int count;
	private TextView textView;
	
	public void setCount(int count) {
		this.count = count;
		textView.setText("" + count);
	}
	
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		textView = (TextView) findViewById(R.id.textview);
		
		ObjectAnimator animator = ObjectAnimator.ofInt(this, "count", 0,100);
		animator.setDuration(10000);
		animator.setInterpolator(new LinearInterpolator());
		animator.setRepeatCount(ObjectAnimator.INFINITE);
		animator.start();
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
