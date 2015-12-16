package com.zlt.custom;

import com.zlt.test.R;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

public class FlipImgEffectView extends View {

	private Context context;
	private Bitmap showBmp;
	private Matrix matrix; // 作用矩阵
	private Camera camera;
	private int deltaX, deltaY; // 翻转角度差值
	private int centerX, centerY; // 图片中心点

	public FlipImgEffectView(Context context) {
		this(context, null);

	}

	public FlipImgEffectView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		// TODO Auto-generated constructor stub
		this.context = context;
		initData();
	}

	public FlipImgEffectView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
		// TODO Auto-generated constructor stub
	}

	private void initData() {
		showBmp = BitmapFactory.decodeResource(context.getResources(),
				R.drawable.flower);
		centerX = showBmp.getWidth() / 2;
		centerY = showBmp.getHeight() / 2;
		matrix = new Matrix();
		camera = new Camera();
	}

	int lastMouseX;
	int lastMouseY;

	@Override
	public boolean dispatchTouchEvent(MotionEvent event) {
		int x = (int) event.getX();
		int y = (int) event.getY();
		switch (event.getAction()) {
		case MotionEvent.ACTION_DOWN:
			lastMouseX = x;
			lastMouseY = y;
			break;
		case MotionEvent.ACTION_MOVE:
			int dx = x - lastMouseX;
			int dy = y - lastMouseY;
			deltaX += dx;
			deltaY += dy;
			break;
		}

		invalidate();
		return true;
	}

	@Override
	protected void onDraw(Canvas canvas) {

		camera.save();
		// 绕X轴翻转
		camera.rotateX(-deltaY);
		// 绕Y轴翻转
		camera.rotateY(deltaX);
		// 设置camera作用矩阵
		camera.getMatrix(matrix);
		camera.restore();
		// 设置翻转中心点
		matrix.preTranslate(-this.centerX, -this.centerY);
		matrix.postTranslate(this.centerX, this.centerY);

		canvas.drawBitmap(showBmp, matrix, null);
	}

}
