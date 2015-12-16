package com.zlt.test;

import com.zlt.main.Settings;
import com.zlt.test.CircleLayout.OnCenterClickListener;

import android.content.Context;
import android.graphics.PixelFormat;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.WindowManager.LayoutParams;

public class TouchView implements OnCenterClickListener {

	private static Object mLock = new Object();
	private static TouchView current;

	private Settings mSettings;
	public static TouchView getInstance(Context context) {
		synchronized (mLock) {
			if (current == null) {
				current = new TouchView(context);
			}
			return current;
		}
	}

	private Context mContext;

	private WindowManager.LayoutParams mTouchDotParams = null;
	private WindowManager.LayoutParams mTouchMainParams = null;
	private WindowManager mWindowManager = null;
	private TouchDotView mTouchDotView = null;
	private View mTouchMainView = null;
	private CircleLayout mView = null;
	
	private int mCurrentShowing = 0;

	public static final int SHOWING_DOTVIEW = 1;
	public static final int SHOWING_MAINVIEW = 2;
	public static final int SHOWING_NONE = 0;

	public TouchView(Context context) {
		this.mContext = context;
		
		mSettings = Settings.getInstance(mContext);
		
		mTouchDotParams = new WindowManager.LayoutParams();
		mTouchMainParams = new WindowManager.LayoutParams();
		mWindowManager = (WindowManager) context.getApplicationContext()
				.getSystemService(Context.WINDOW_SERVICE);

		mTouchMainView = LayoutInflater.from(mContext).inflate(
				R.layout.mainview, null);
		mView = (CircleLayout) mTouchMainView.findViewById(R.id.main_circle_layout);
		mView.setOnCenterClickListener(this);
	}

	public void removeView() {
		
		if (mCurrentShowing == SHOWING_NONE) {
			return;
		}
		if (mCurrentShowing == SHOWING_DOTVIEW) {
			mWindowManager.removeView(mTouchDotView);
			mCurrentShowing = SHOWING_NONE;
		} else if (mCurrentShowing == SHOWING_MAINVIEW) {
			mWindowManager.removeView(mTouchMainView);
			mCurrentShowing = SHOWING_NONE;
		} 
	}

	public void showView() {
		if (mCurrentShowing != SHOWING_NONE) {
			return;
		}
		setupLayoutParams();
		showTouchDotView();
	}

	public int getShowingView() {
		return mCurrentShowing;
	}

	private void setupLayoutParams() {
		// 设置window type
		mTouchDotParams.type = LayoutParams.TYPE_PRIORITY_PHONE;
		// 设置图片格式，效果为背景透明
		mTouchDotParams.format = PixelFormat.RGBA_8888;

		/*
		 * 设置Window flag /* 下面的flags属性的效果形同“锁定”。 悬浮窗不可触摸，不接受任何事件,同时不影响后面的事件响应。
		 * wmParams.flags=LayoutParams.FLAG_NOT_TOUCH_MODAL |
		 * LayoutParams.FLAG_NOT_FOCUSABLE | LayoutParams.FLAG_NOT_TOUCHABLE;
		 */
		mTouchDotParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL
				| LayoutParams.FLAG_NOT_FOCUSABLE;
		// 调整悬浮窗口至左上角，便于调整坐标
		mTouchDotParams.gravity = Gravity.LEFT | Gravity.TOP;
		// 以屏幕左上角为原点，设置x、y初始值
		mTouchDotParams.x = mSettings.getTouchPositionX();
		mTouchDotParams.y = mSettings.getTouchPositionY();

		// 设置悬浮窗口长宽数据
		mTouchDotParams.width = LayoutParams.WRAP_CONTENT;
		mTouchDotParams.height = LayoutParams.WRAP_CONTENT;

		/*----*/
		mTouchMainParams.type = LayoutParams.TYPE_PRIORITY_PHONE;
		mTouchMainParams.format = PixelFormat.RGBA_8888;
		mTouchMainParams.flags = LayoutParams.FLAG_NOT_TOUCH_MODAL
				| LayoutParams.FLAG_NOT_FOCUSABLE;
		mTouchMainParams.gravity = Gravity.CENTER;
		mTouchMainParams.width = LayoutParams.WRAP_CONTENT;
		mTouchMainParams.height = LayoutParams.WRAP_CONTENT;
	}

	public WindowManager.LayoutParams getTouchDotParams() {
		return mTouchDotParams;
	}

	public WindowManager.LayoutParams getTouchMainParams() {
		return mTouchMainParams;
	}

	public void showTouchDotView() {
		if (mTouchDotView == null) {
			createTouchDotView();
		}
		if (mCurrentShowing == SHOWING_DOTVIEW) {
			return;
		} else if (mCurrentShowing == SHOWING_MAINVIEW) {
			mWindowManager.removeView(mTouchMainView);
		}
		// 显示TouchDotView
		mWindowManager.addView(mTouchDotView, mTouchDotParams);
		mCurrentShowing = SHOWING_DOTVIEW;
	}

	public void showTouchMainView() {
		if (mTouchMainView == null) {
			createTouchMainView();
		}
		if (mCurrentShowing == SHOWING_MAINVIEW) {
			return;
		} else if (mCurrentShowing == SHOWING_DOTVIEW) {
			mWindowManager.removeView(mTouchDotView);
		}
		mWindowManager.addView(mTouchMainView, mTouchMainParams);
		mCurrentShowing = SHOWING_MAINVIEW;
	}

	public void reload() {
		int showing = mCurrentShowing;
		removeView();
		mTouchDotView = null;
		mTouchMainView = null;
		if (showing == SHOWING_DOTVIEW) {
			showTouchDotView();
		} else if (showing == SHOWING_MAINVIEW) {
			showTouchMainView();
		}
	}

	private void createTouchDotView() {
		mTouchDotView = new TouchDotView(mContext.getApplicationContext());
		mTouchDotView.setOnTouchDotViewListener(mScrollListener);
	}

	private void createTouchMainView() {
		mTouchMainView = new CircleLayout(mContext.getApplicationContext());
	}

	public int getmCurrentShowing() {
		return mCurrentShowing;
	}

	private TouchDotView.OnTouchDotViewListener mScrollListener = new TouchDotView.OnTouchDotViewListener() {
		@Override
		public void onScrollTo(View v, int x, int y) {
			mTouchDotParams.x = x;
			mTouchDotParams.y = y;
			mWindowManager.updateViewLayout(v, mTouchDotParams);
			mSettings.setTouchPosition(x, y);
		}

		@Override
		public void onTouchUp(View view, int x, int y) {
		}

		@Override
		public void onSingleTap(View view) {
			showTouchMainView();
		}

		@Override
		public void onLongPress() {
			removeView();
			mSettings.setEnableAssistiveTouch(false);
		}

		@Override
		public boolean onDoubleTap() {
			return false;
		}
	};

	@Override
	public void onCenterClick() {
		// TODO Auto-generated method stub
		if (mCurrentShowing == TouchView.SHOWING_DOTVIEW) {
			showTouchMainView();
		} else if (mCurrentShowing == TouchView.SHOWING_MAINVIEW) {
			showTouchDotView();
		}
	}

	@Override
	public void onMove(MotionEvent event1, MotionEvent event2) {
		// TODO Auto-generated method stub

	}

}
