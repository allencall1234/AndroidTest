package com.zlt.custom;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

public class AlarmView extends View {

	private int mWidth,mHeight;
	private int smallRadius,bigRadius;
	
	private Paint mPaint;
	private int size;
	public AlarmView(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		mPaint = new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
		
	}

	public AlarmView(Context context, AttributeSet attrs) {
		this(context, attrs,0);
		// TODO Auto-generated constructor stub
	}

	public AlarmView(Context context) {
		this(context,null);
		// TODO Auto-generated constructor stub
	}

	@Override
	protected void onSizeChanged(int w, int h, int oldw, int oldh) {
		// TODO Auto-generated method stub
		super.onSizeChanged(w, h, oldw, oldh);
		mWidth = getWidth();
		mHeight = getHeight();
		size = Math.min(mWidth, mHeight);
		
		smallRadius = size/6;
		bigRadius = size/4;
		
		Log.i("zlt", "mWidth = " + mWidth + ",mHeight = " + mHeight);
	}
	
	@Override
	protected void onDraw(Canvas canvas) {
		// TODO Auto-generated method stub
	}
}
