package com.zlt.test;

import android.app.Activity;
import android.os.Bundle;
import android.util.SparseIntArray;

public class SparseIntArrayTest extends Activity{

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		SparseIntArray mList = new SparseIntArray();
		mList.put(0, 1);
		mList.put(1, 23);
		mList.put(2, 14);
		
		int a1 = mList.get(0);
		int a2 = mList.keyAt(1);
		
	}
}
