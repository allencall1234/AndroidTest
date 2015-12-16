package com.zlt.test;

import com.zlt.main.BaseActivity;

import cn.bmob.im.bean.BmobRecent;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

public class BeanTest extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.i("zlt", "BeanTest OnCreate()!");
		System.out.print("print 1: " + "onCreate()!");
		
		BmobRecent recent = new BmobRecent();
		recent.setNick("123");
		recent.setMessage("hello kitty!");
		recent.setTargetid("1");
		recent.setTime(System.currentTimeMillis());
		recent.setType(1);
		recent.setUserName("a");
		BmobRecent recent2 = new BmobRecent();
		recent2.setNick("1231");
		recent2.setMessage("hello kitty!2");
		recent2.setTargetid("2");
		recent2.setTime(System.currentTimeMillis());
		recent2.setType(1);
		recent2.setUserName("b");
		BmobRecent recent3 = new BmobRecent();
		recent3.setUserName("c");
		recent2.setTargetid("2");
		RecentChat recent4 = new RecentChat();
		recent4.setUserName("zlt01");
		
		System.out.print("print 1: " + recent.hashCode());
		System.out.print("print 2: " + recent.compareTo(recent2));
		Log.i("zlt", "comparte a-b: " + recent.compareTo(recent2));
		Log.i("zlt", "comparte c-b: " + recent3.compareTo(recent2));
		Log.i("zlt", "comparte b-c: " + recent2.compareTo(recent3));
		System.out.print("print 3: " + recent2.hashCode());
	}
}
