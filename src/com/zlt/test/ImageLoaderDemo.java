package com.zlt.test;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageSize;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.zlt.main.BaseActivity;
import com.zlt.main.Main;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.Gravity;
import android.view.KeyEvent;
import android.view.View;
import android.widget.FrameLayout.LayoutParams;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;

public class ImageLoaderDemo extends BaseActivity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		RelativeLayout mainLayout = new RelativeLayout(this);
		final ImageView imageView = new ImageView(this);
		final ProgressBar progressBar = new ProgressBar(this);
		RelativeLayout.LayoutParams layoutParams;
		layoutParams = new RelativeLayout.LayoutParams(LayoutParams.MATCH_PARENT,LayoutParams.MATCH_PARENT);
		mainLayout.addView(imageView,layoutParams);
		layoutParams = new RelativeLayout.LayoutParams(
				LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
		layoutParams.addRule(RelativeLayout.CENTER_IN_PARENT);
		mainLayout.addView(progressBar,layoutParams);

		setContentView(mainLayout);
		ImageSize imageSize = new ImageSize(200, 200);
//		String url = "http://img0.imgtn.bdimg.com/it/u=1070902365,2619384777&fm=21&gp=0.jpg";
		String url = "http://d.pcs.baidu.com/thumbnail/650e73d4941f43451694f3cd4e5a9f3f?fid=2888301178-250528-862688661572419&time=1448611200&sign=FDTAER-DCb740ccc5511e5e8fedcff06b081203-1oFOKV%2BdOuwzQf8zsBtG9LVZwug%3D&rt=sh&expires=2h&r=227477921&sharesign=unknown&size=c710_u500&quality=100";
		ImageLoader.getInstance().loadImage(url, new ImageLoadingListener() {

			@Override
			public void onLoadingStarted(String imageUri, View view) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onLoadingFailed(String imageUri, View view,
					FailReason failReason) {
				// TODO Auto-generated method stub
			}

			@Override
			public void onLoadingComplete(String imageUri, View view,
					Bitmap loadedImage) {
				// TODO Auto-generated method stub
				progressBar.setVisibility(View.GONE);
				imageView.setImageBitmap(loadedImage);
			}

			@Override
			public void onLoadingCancelled(String imageUri, View view) {
				// TODO Auto-generated method stub

			}
		});

		/**
		 * 设置缓存后,不需要每一次都从网上下载图片
		 */
		// DisplayImageOptions options = new DisplayImageOptions.Builder()
		// .cacheInMemory(true).cacheOnDisc(true)
		// .bitmapConfig(Bitmap.Config.RGB_565).build();
		//
		//
		// ImageLoader.getInstance().loadImage(url, imageSize,optionss,
		// new SimpleImageLoadingListener() {
		// @Override
		// public void onLoadingComplete(String imageUri, View view,
		// Bitmap loadedImage) {
		// // TODO Auto-generated method stub
		// super.onLoadingComplete(imageUri, view, loadedImage);
		// imageView.setImageBitmap(loadedImage);
		// }
		// });

//		 DisplayImageOptions options2 = new DisplayImageOptions.Builder()
//		 .showImageOnLoading(R.drawable.ic_launcher)
//		 .cacheInMemory(true)
//		 .cacheOnDisc(true)
//		 .bitmapConfig(Bitmap.Config.RGB_565)
//		 .build();
//		
//		 ImageLoader.getInstance().displayImage(url, imageView, options2);
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		// TODO Auto-generated method stub
		if (keyCode == KeyEvent.KEYCODE_BACK) {
			startActivity(new Intent(ImageLoaderDemo.this,Main.class));
			overridePendingTransition(0, R.anim.scale_in);
		}
		return super.onKeyDown(keyCode, event);
	}

}
