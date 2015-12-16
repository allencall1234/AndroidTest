package com.zlt.test;

import com.zlt.main.BaseActivity;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class ObjectTransDemo1 extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		TextView textView = new TextView(this);
		Person person = (Person) getIntent().getSerializableExtra(
				ObjectTransDemo.SER_KEY);
		textView.setText("name = " + person.getName() + "\n" + "age = "
				+ person.getAge());
		setContentView(textView);
	}
}
