package com.zlt.test;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

/**
 * 自定义开关
 * @author zhulanting
 *
 */
public class CustomSwitch extends View implements OnTouchListener {
	
	private Bitmap bg_on,bg_off,slider;
	/**
	 * 按下时的x坐标和当前的x坐标
	 */
	private float downX,nowX;
	/**
	 * 判断当前滑动状态
	 */
	private boolean isSlip;
	
	private boolean nowStatus;
	
	private OnChangeListener listener;
	
	public CustomSwitch(Context context) {
		this(context, null);
	}

	public CustomSwitch(Context context, AttributeSet attrs) {
		super(context, attrs);
		init();
	}

	private void init() {
		// TODO Auto-generated method stub
		bg_on = BitmapFactory.decodeResource(getResources(), R.drawable.switch_on);
		bg_off = BitmapFactory.decodeResource(getResources(), R.drawable.switch_off);
		slider = BitmapFactory.decodeResource(getResources(), R.drawable.switch_slider);
		
		setOnTouchListener(this);
	}
	
	@Override
	protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
		// TODO Auto-generated method stub
		int resultWidth = 0;
		int specMode = MeasureSpec.getMode(widthMeasureSpec);
		int specSize = MeasureSpec.getSize(widthMeasureSpec);
		
		if (specMode == MeasureSpec.EXACTLY) {
			resultWidth = specSize;
		}else {
			resultWidth = bg_on.getWidth() + getPaddingLeft() + getPaddingRight();
			
			if (specMode == MeasureSpec.AT_MOST) {
				resultWidth = Math.min(resultWidth, specSize);
			}
		}
		
		int resultHeight = 0;
		specMode = MeasureSpec.getMode(heightMeasureSpec);
		specSize = MeasureSpec.getSize(heightMeasureSpec);
		
		if (specMode == MeasureSpec.EXACTLY) {
			resultHeight = specSize;
		}else {
			resultHeight = bg_on.getHeight() + getPaddingTop() + getPaddingBottom();
			
			if (specMode == MeasureSpec.AT_MOST) {
				resultHeight = Math.min(resultHeight, specSize);
			}
		}
		
		setMeasuredDimension(resultWidth, resultHeight);
	}
	
	
	@Override
	protected void onLayout(boolean changed, int left, int top, int right,
			int bottom) {
		// TODO Auto-generated method stub
		super.onLayout(changed, left, top, right, bottom);
	}
	
	
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		
		if (downX < (bg_on.getWidth()/2 - slider.getWidth()/2)) {
			canvas.drawBitmap(bg_off, getPaddingLeft(),getPaddingTop(), null);
		}else {
			canvas.drawBitmap(bg_on, getPaddingLeft(),getPaddingTop(), null);
		}
		
		if (isSlip) {
			if (downX >= (bg_on.getWidth()/2)) {
				downX = bg_on.getWidth() - slider.getWidth()/2;
			}else {
				downX = nowX - slider.getWidth()/2;
			}
		}else {
			if (nowStatus) {
				downX = bg_on.getWidth() - slider.getWidth()/2;
			}else {
				downX = 0;
			}
		}
		
		if (downX<0) {
			downX = 0;
		}else if (downX > bg_on.getWidth() - slider.getWidth()) {
			downX = bg_on.getWidth() - slider.getWidth();
		}
		
		canvas.drawBitmap(slider, downX+getPaddingLeft(),getPaddingTop(), null);
	}
	
	@Override
	public boolean onTouch(View v, MotionEvent event) {
		float dx = event.getX() - getPaddingLeft();
		float dy = event.getY() - getPaddingBottom();
		
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			if (dx > bg_off.getWidth() || dy > bg_off.getHeight()
					|| dx < 0 || dy < 0) {
				return false;
			}else {
				isSlip = true;
				nowX = dx;
			}
			break;
		case MotionEvent.ACTION_MOVE:
			nowX = dx;
			break;
		
		case MotionEvent.ACTION_UP:
			isSlip = false;
			
			if (dx >= bg_on.getWidth()/2) {
				if (!nowStatus) {
					nowStatus = true;
					if (listener != null) {
						listener.onChanged(CustomSwitch.this, nowStatus);
					}
				}
				nowX = bg_on.getWidth() - slider.getWidth();
			}else {
				if (nowStatus) {
					nowStatus = false;
					if (listener != null) {
						listener.onChanged(CustomSwitch.this, nowStatus);
					}
				}
				nowX = 0;
			}
			
			break;
		}
		
		invalidate();
		return true;
	}
	
	public void setOnChangeListener(OnChangeListener listener) {
		this.listener = listener;
	}
	
	public void setChecked(boolean checked) {
		if (checked) {
			nowX = bg_on.getWidth();
		}else {
			nowX = 0;
		}
		
		nowStatus = checked;
	}
	
	/**
	 * 回調接口
	 */
	public interface OnChangeListener{
		
		public void onChanged(CustomSwitch mSwitch,boolean checkState);
		
	}
}
