package com.zlt.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.zlt.main.BaseActivity;
import com.zlt.test.adapter.ItemAdapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.widget.EditText;
import android.widget.ListView;

public class FilterableDemo extends BaseActivity {

	private ListView mListView = null;
	private String[] chesses = Cheeses.sCheeseStrings;
	private List<String> mList = null;
	private ItemAdapter mAdapter = null;

	private EditText filterEdit = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);

		setContentView(R.layout.filterable_layout);
		
		mList = Arrays.asList(chesses);
		
		mListView = (ListView) findViewById(R.id.my_listview);
		mAdapter = new ItemAdapter(this, R.layout.item, mList);
		mListView.setAdapter(mAdapter);

		filterEdit = (EditText) findViewById(R.id.filter_edittext);
		filterEdit.addTextChangedListener(new TextWatcher() {

			@SuppressLint("DefaultLocale")
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				Log.i("zlt", "textChanged! s = " + s + ",start = " + start
						+ ",before = " + before + ",count = " + count);
				
				String aa = s.toString();
				Pattern pattern = Pattern.compile(aa.toLowerCase());
				
				List<String> temp = new ArrayList<String>();
				
				for (String string : mList) {
					Matcher matcher = pattern.matcher(string.toLowerCase());
					if (matcher.find()) {
						temp.add(string);
					}
				}
				Log.i("zlt", "temp = " + temp.size());
				mAdapter = new ItemAdapter(FilterableDemo.this, R.layout.item, temp);
				mListView.setAdapter(mAdapter);
				
			}

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub

			}
		});
	}
}
