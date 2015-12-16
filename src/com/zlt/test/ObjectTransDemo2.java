package com.zlt.test;

import com.zlt.main.BaseActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ObjectTransDemo2 extends BaseActivity {
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		TextView mTextView = new TextView(this);
		Book mBook = (Book) getIntent().getParcelableExtra(
				ObjectTransDemo.Par_KEY);
		mTextView.setText("Book name is: " + mBook.getBookName() + "\n"
				+ "Author is: " + mBook.getBookAuthor() + "\n"
				+ "PublishTime is: " + mBook.getPublishTime());
		setContentView(mTextView);
	}
}
