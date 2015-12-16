package com.zlt.test;

import java.util.Iterator;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.zlt.main.BaseActivity;

import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.widget.LinearLayout.LayoutParams;
import android.widget.ScrollView;
import android.widget.TextView;

public class HttpTestDemo extends BaseActivity {

	 private String url =
	 "http://192.168.1.34:8084/jzdms/mobileLogin.html?username=zhangsan&password=123456";
//	private String url = "http://192.168.1.34:8084/jzdms/mobileTaskCount.html";
	TextView textView;
	HttpEntity entity;

	private Thread mThread;
	private String results = null;
	private Handler mHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 1:
				textView.setText("获取失败!");
				break;
			case 2:
				textView.setText("获取成功!" + results);
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

		ScrollView scrollView = new ScrollView(this);

		LayoutParams params = new LayoutParams(LayoutParams.MATCH_PARENT,
				LayoutParams.MATCH_PARENT);

		textView = new TextView(this);
		textView.setText("Http test!");

		scrollView.addView(textView, params);
		setContentView(scrollView);

		mThread = new Thread(new Runnable() {
			public void run() {
				try {
					HttpGet get = new HttpGet(url);
					HttpClient client = new DefaultHttpClient();
					HttpConnectionParams.setConnectionTimeout(
							client.getParams(), 60000);
					HttpConnectionParams.setSoTimeout(client.getParams(), 60000);
					HttpResponse response = client.execute(get);
					int status = response.getStatusLine().getStatusCode();
					if (status == 200) {
						entity = response.getEntity();
						String result = EntityUtils.toString(entity, "UTF-8");
						// JSONArray jsonArray = new JSONArray(result);
						if (result.charAt(0) == '[') {
							JSONArray jsonArray = new JSONArray(result);
							for (int i = 0, length = jsonArray.length(); i < length; i++) {
								decodeJSONObject(jsonArray.getJSONObject(i));
							}
						} else if (result.charAt(0) == '{') {
							JSONObject jsonObject = new JSONObject(result);
							decodeJSONObject(jsonObject);
						} else {
							return;
						}

						mHandler.obtainMessage(2, result).sendToTarget();
						return;
					}
					mHandler.obtainMessage(1, status + "").sendToTarget();
				} catch (Exception e) {
					try {
						mHandler.obtainMessage(1).sendToTarget();
					} catch (Exception e2) {

					}
					e.printStackTrace();

				}
			}
		});
		mThread.start();
	}

	public void decodeJSONObject(JSONObject json) throws JSONException {
		Iterator<String> keys = json.keys();
		JSONObject jo = null;
		JSONArray ja = null;
		Object o;
		String key;
		while (keys.hasNext()) {
			key = keys.next();
			o = json.get(key);
			if (o instanceof JSONObject) {
				jo = (JSONObject) o;
				// if(jo.keySet().size()>0){
				if (jo.length() > 0) {
					decodeJSONObject(jo);
				} else {
					// System.out.println("key = " + key + ",value = "
					// + jo.getJSONObject(key).toString());
					results += "\nkey = " + key + ",value = "
							+ jo.getJSONObject(key).toString();
				}
			} else if (o instanceof JSONArray) {
				ja = (JSONArray) o;
				for (int i = 0; i < ja.length(); i++) {
					decodeJSONObject(ja.getJSONObject(i));
				}
			} else {
				json.put(key, o.toString().trim());
				results += "\nkey = " + key + ",value = " + o.toString();
				System.out.println("key = " + key + ",value = " + o.toString());
			}

		}
	}
}
