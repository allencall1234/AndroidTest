package com.zlt.test;

import com.zlt.main.BaseActivity;
import com.zlt.main.Settings;

import android.annotation.SuppressLint;
import android.app.ActionBar.LayoutParams;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.TextView;

public class FloatingButton extends BaseActivity implements OnClickListener{
	CheckBox checkBox;
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		LinearLayout layout = new LinearLayout(this);

		LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

		layout.setOrientation(LinearLayout.VERTICAL);

		LinearLayout.LayoutParams buttonParams = new LinearLayout.LayoutParams(
				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);

		Button button1 = new Button(this);
		button1.setGravity(Gravity.CENTER);
		button1.setText("show touch!");
		button1.setId(123);
		button1.setOnClickListener(this);
		layout.addView(button1, buttonParams);

		Button button2 = new Button(this);
		button2.setGravity(Gravity.CENTER);
		button2.setText("show main!");
		button2.setId(124);
		button2.setOnClickListener(this);
		layout.addView(button2, buttonParams);

		LinearLayout setLayout = new LinearLayout(this);
		setLayout.setLayoutParams(buttonParams);
		setLayout.setOrientation(LinearLayout.HORIZONTAL);

		TextView label = new TextView(this);
		label.setText("显示功能键:");
		label.setTextSize(30);
		label.setGravity(Gravity.CENTER_VERTICAL | Gravity.LEFT);
		LinearLayout.LayoutParams textParams = new LinearLayout.LayoutParams(0,
				LayoutParams.MATCH_PARENT, 1.0f);

		setLayout.addView(label, textParams);

		checkBox = new CheckBox(this);
		checkBox.setGravity(Gravity.CENTER);

		setLayout.addView(checkBox, textParams);

		layout.addView(setLayout);

		setContentView(layout, layoutParams);


		checkBox.setOnCheckedChangeListener(new OnCheckedChangeListener() {

			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if (isChecked) {
					mTouchView.showView();
				} else {
					mTouchView.removeView();
				}
				mSettings.setEnableAssistiveTouch(isChecked);
			}
		});

		
		
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		checkBox.setChecked(mSettings.isEnableAssistiveTouch());
	}
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case 123:
			mTouchView.showView();
			break;
		case 124:
			if (mTouchView.getmCurrentShowing() == TouchView.SHOWING_DOTVIEW) {
				mTouchView.showTouchMainView();
			} else if (mTouchView.getmCurrentShowing() == TouchView.SHOWING_MAINVIEW) {
				mTouchView.showTouchDotView();
			}
			break;
		default:
			break;
		}
	}
}
