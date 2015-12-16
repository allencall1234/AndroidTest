package com.zlt.test;

import android.animation.ArgbEvaluator;
import android.animation.ValueAnimator;
import android.animation.ValueAnimator.AnimatorUpdateListener;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.TextView;

public class CustomViewDemo extends Activity {

	TextView tView = null;
	TextView colorfulText = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.custom_imageview);
		
		colorfulText = (TextView) findViewById(R.id.colorful_text);
		
		tView = (TextView) findViewById(R.id.text2);
		findViewById(R.id.click_layout).setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(final View v) {
				// TODO Auto-generated method stub
				TranslateAnimation animation = new TranslateAnimation(0, -v.getWidth(), 0, 0);
				animation.setDuration(200);
				animation.setAnimationListener(new AnimationListener() {
					
					@Override
					public void onAnimationStart(Animation animation) {
						// TODO Auto-generated method stub
					}
					
					@Override
					public void onAnimationRepeat(Animation animation) {
						// TODO Auto-generated method stub
						
					}
					 
					@SuppressLint("NewApi")
					@Override
					public void onAnimationEnd(Animation animation) {
						// TODO Auto-generated method stub
						tView.setText("Android编程思想");

						Integer color_blue = getResources().getColor(R.color.color_blue);
						Integer color_green = getResources().getColor(R.color.color_green);
						Integer color_purple = getResources().getColor(R.color.color_purple);
						
						final Drawable drawable = colorfulText.getBackground();
						
						ValueAnimator colorAnimator = ValueAnimator.ofObject(new ArgbEvaluator(), color_blue,color_green,color_purple);
						colorAnimator.addUpdateListener(new AnimatorUpdateListener() {
							
							@Override
							public void onAnimationUpdate(ValueAnimator animation) {
								// TODO Auto-generated method stub
//								colorfulText.setBackgroundColor((Integer) animation.getAnimatedValue());
								drawable.setColorFilter(new PorterDuffColorFilter((Integer) animation.getAnimatedValue(), PorterDuff.Mode.SRC_IN));
								colorfulText.setBackgroundDrawable(drawable);
								
							}
						});
						colorAnimator.setDuration(5000);
						colorAnimator.setRepeatCount(ValueAnimator.INFINITE);
						colorAnimator.setRepeatMode(ValueAnimator.REVERSE);
						colorAnimator.start();
					}
				});
				v.startAnimation(animation);
			}
		});
		
		
	}
	
}
