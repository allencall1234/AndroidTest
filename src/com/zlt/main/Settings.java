package com.zlt.main;

import com.zlt.test.Constants;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class Settings implements Constants {
	private static Settings current = null;
	private static Object mLock = new Object();

	public static void init(Context context) {
		synchronized (mLock) {
			if (current == null) {
				current = new Settings(context);
			}
		}
	}

	public static Settings getInstance() {
		synchronized (mLock) {
			if (current == null) {
				throw new NullPointerException("Setting is not int");
			}
			return current;
		}
	}

	public static Settings getInstance(Context context) {
		synchronized (mLock) {
			if (current == null) {
				current = new Settings(context);
			}
			return current;
		}
	}

	private SharedPreferences mSpref = null;
	private Context mContext;

	private boolean isEnableVirbrator;
	private int mTouchDotPosX;
	private int mTouchDotPosY;

	public Settings(Context context) {
		mContext = context;
		mSpref = PreferenceManager.getDefaultSharedPreferences(context);

		int init_code = mSpref.getInt(INIT_APPLICATION_VERSION_CODE, -1);

		isEnableVirbrator = mSpref.getBoolean(ENABLE_VIBRATOR_KEY, true);

		mTouchDotPosX = mSpref.getInt(TOUCH_DOT_VIEW_POS_X_KEY,
				DEFAULT_TOUCH_DOT_VIEW_POS_X);
		mTouchDotPosY = mSpref.getInt(TOUCH_DOT_VIEW_POS_Y_KEY,
				DEFAULT_TOUCH_DOT_VIEW_POS_Y);

	}

	public boolean isEnableAutoUpdate() {
		return mSpref.getBoolean(ENABLE_AUTO_UPDATE_KEY, true);
	}

	public void setEnableAutoUpdate(boolean enable) {
		mSpref.edit().putBoolean(ENABLE_AUTO_UPDATE_KEY, enable).commit();
	}

	public boolean isEnableAssistiveTouch() {
		return mSpref.getBoolean(ENABLE_ASSISTIVE_KEY, false);
	}

	/**
	 * 是否启动 虚拟按键助手
	 * */
	public void setEnableAssistiveTouch(boolean enable) {
		mSpref.edit().putBoolean(ENABLE_ASSISTIVE_KEY, enable).commit();
	}

	public int getTouchPositionX() {
		return mTouchDotPosX;
	}

	public int getTouchPositionY() {
		return mTouchDotPosY;
	}

	/**
	 * 设置TouchDot的位置
	 * */
	public void setTouchPosition(int x, int y) {
		mTouchDotPosX = x;
		mTouchDotPosY = y;
		Editor edit = mSpref.edit();
		edit.putInt(TOUCH_DOT_VIEW_POS_X_KEY, x);
		edit.putInt(TOUCH_DOT_VIEW_POS_Y_KEY, y);
		edit.commit();
	}

	/**
	 * 是否使用点击按键震动效果
	 * */
	public void setEnableVibrator(boolean enable) {
		isEnableVirbrator = enable;
		mSpref.edit().putBoolean(ENABLE_VIBRATOR_KEY, enable).commit();
	}

	public boolean isEnableVirbrator() {
		return isEnableVirbrator;
	}
}
