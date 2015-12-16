package com.zlt.test;

import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

import org.apache.http.client.ResponseHandler;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.DefaultHttpClient;

import com.zlt.main.BaseActivity;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class WeatherDemo extends BaseActivity {
	String googleWeatherUrl1 = "http://www.google.com/ig/api?weather=zhengzhou";
	// String googleWeatherUrl2 =
	// "http://www.google.com/ig/api?hl=zh-cn&weather=zhengzhou";
	String googleWeatherUrl2 = "http://192.168.1.59:8080/ogrp/phoneLogin.html?staffname=zhangsan&pwd=123456";

	private Button b1;
	private Button b2;
	private TextView tv;
	
	private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				urlConn();
				break;
			case 1:
				httpClientConn();
				break;

			default:
				break;
			}
		};
	};
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weather_layout);

		b1 = (Button) findViewById(R.id.method_1);
		b2 = (Button) findViewById(R.id.method_2);
		tv = (TextView) findViewById(R.id.textview);

		b1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// 使用URLConnection连接GoogleWeatherAPI
				mHandler.sendEmptyMessage(0);
			}
		});
		// 设置按钮单击监听器
		b2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				// 使用HttpCient连接GoogleWeatherAPI
				mHandler.sendEmptyMessage(1);
			}
		});
	}

	protected void urlConn() {
		try {
			// URL
			URL url = new URL(googleWeatherUrl1);
			// HttpURLConnection
			HttpURLConnection httpconn = (HttpURLConnection) url
					.openConnection();

			if (httpconn.getResponseCode() == HttpURLConnection.HTTP_OK) {

				Toast.makeText(getApplicationContext(),
						"连接Google Weather API成功!",

						Toast.LENGTH_SHORT).show();

				// InputStreamReader

				InputStreamReader isr = new InputStreamReader(
						httpconn.getInputStream(), "utf-8");

				int i;
				String content = "";
				// read
				while ((i = isr.read()) != -1) {
					content = content + (char) i;
				}
				isr.close();

				// 设置TextView
				tv.setText(content);
			}
			// disconnect
			httpconn.disconnect();

		} catch (Exception e) {

			Toast.makeText(getApplicationContext(), "连接Google Weather API失败",
					Toast.LENGTH_SHORT).show();

			e.printStackTrace();

		}

	}

	// 使用HttpCient连接GoogleWeatherAPI

	protected void httpClientConn() {

		// DefaultHttpClient

		DefaultHttpClient httpclient = new DefaultHttpClient();

		// HttpGet

		HttpGet httpget = new HttpGet(googleWeatherUrl2);

		// ResponseHandler

		ResponseHandler<String> responseHandler = new BasicResponseHandler();

		try {

			String content = httpclient.execute(httpget, responseHandler);

			Toast.makeText(getApplicationContext(), "连接Google Weather API成功!",

			Toast.LENGTH_SHORT).show();	
 
			// 设置TextView

			tv.setText(content);

		} catch (Exception e) {

			Toast.makeText(getApplicationContext(), "连接Google Weather API失败",
					Toast.LENGTH_SHORT)

			.show();

			e.printStackTrace();

		}

		httpclient.getConnectionManager().shutdown();

	}

}
