package com.zlt.test;

import com.zlt.utils.PixelUtil;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class PixelDemo extends Activity {
	
	private TextView textView1,textView2,textView3,textView4,textView5,textView6;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pixel_layout);
		initView();
	}

	private void initView() {
		// TODO Auto-generated method stub
		
		float density = getResources().getDisplayMetrics().density;
		int densityDpi = getResources().getDisplayMetrics().densityDpi;
		
		
		textView1 = (TextView) findViewById(R.id.textview1);
		textView2 = (TextView) findViewById(R.id.textview2);
		textView3 = (TextView) findViewById(R.id.textview3);
		textView4 = (TextView) findViewById(R.id.textview4);
		textView5 = (TextView) findViewById(R.id.textview5);
		textView6 = (TextView) findViewById(R.id.textview6);
		
		textView1.setText(textView1.getText().toString() + density);
		textView2.setText(textView2.getText().toString() + densityDpi);
		textView3.setText("100dp = " + PixelUtil.dp2px(100,this) + "pix");
		textView4.setText("100pix = " + PixelUtil.px2dp(100,this) + "dp");
	}
}
