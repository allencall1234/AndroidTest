package com.zlt.test;


import com.zlt.main.BaseActivity;
import com.zlt.test.CustomSwitch.OnChangeListener;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class ObjectTransDemo extends BaseActivity implements OnClickListener{

	private Button sButton, pButton;
	
	public final static String SER_KEY = "com.tutor.objecttran.ser";
	public final static String Par_KEY = "com.tutor.objecttran.par";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.main);
		System.out.print("print 1: " + "onCreate()!");
		setupViews();
	}

	public void setupViews() {
		sButton = (Button) findViewById(R.id.button1);
		pButton = (Button) findViewById(R.id.button2);

		sButton.setOnClickListener(this);
		pButton.setOnClickListener(this);
		
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.button1:
			serializableMode();
			break;
		case R.id.button2:
			parcelableMode();
			break;

		default:
			break;
		}
	}

	private void serializableMode() {
		// TODO Auto-generated method stub
		Person person = new Person();
		person.setName("Jack");
		person.setAge(15);
		Intent intent = new Intent(this, ObjectTransDemo1.class);
		Bundle bundle = new Bundle();
		bundle.putSerializable(SER_KEY, person);
		intent.putExtras(bundle);

		startActivity(intent);
	}

	private void parcelableMode() {
		// TODO Auto-generated method stub
		Book book = new Book();
		book.setBookAuthor("zhulanting");
		book.setBookName("Up to zhulanting");
		book.setPublishTime(2016);
		Intent intent = new Intent(this, ObjectTransDemo2.class);

		Bundle bundle = new Bundle();
		bundle.putParcelable(Par_KEY, book);
		intent.putExtras(bundle);
		startActivity(intent);
	}

}
