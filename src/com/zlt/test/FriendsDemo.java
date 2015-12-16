package com.zlt.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

import cn.bmob.push.a.thing;

import com.zlt.test.bean.User;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.TextView;

public class FriendsDemo extends Activity implements OnClickListener {

	private TextView textView;
	private User[] user = new User[NUM];

	private EditText fromEditText,toEditText;
	private TextView showView,showContents,changeView;
	
	private final static int NUM = 100000;
	private int form,to;
	
	ProgressDialog progressDialog;
	
	private Handler mHandler = new Handler(){
		public void handleMessage(android.os.Message msg) {
			switch (msg.what) {
			case 0:
				showView.setText("\n" + user[to].getRoute());
				break;

			default:
				break;
			}
		};
	};
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.friends_layout);
//		scrollView = new ScrollView(this);

//		LinearLayout.LayoutParams params = new LayoutParams(
//				LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
//
//		LinearLayout linearLayout = new LinearLayout(this);
//		linearLayout.setLayoutParams(params);
//		linearLayout.setOrientation(LinearLayout.VERTICAL);
//
//		textView = new TextView(this);
//		textView.setText("friends test!");
//
//		linearLayout.addView(textView);
//		LinearLayout.LayoutParams params2 = new LayoutParams(
//				LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
//
//		LinearLayout cLayout = new LinearLayout(this);
//		cLayout.setLayoutParams(params2);
//		cLayout.setOrientation(LinearLayout.HORIZONTAL);
//		
//		Button button = new Button(this);
//		button.setText("刷新");
//		button.setGravity(Gravity.CENTER);
//		button.setOnClickListener(this);
//		linearLayout.addView(button, params2);
//		
//		scrollView.addView(linearLayout, params);
//		setContentView(scrollView);

		initViews();
		
		progressDialog = new ProgressDialog(this);
		progressDialog.setTitle("正在初始化...");
		progressDialog.show();
		
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				initFriends();
			}
		}).start();
//
//		// findFriendsByID(user[1], user[4]);
//
//		list2.add(user[1]);
//
//		findFriendsByID1(1);
//
//		textView.setText(contents + "\n" + user[4].getRoute());
		
	}

	public void onquery(){
		form =Integer.parseInt(fromEditText.getText().toString());
		to =Integer.parseInt(toEditText.getText().toString());
		
		if (form >= NUM || form < 0 || to >= NUM || to < 0) {
			showView.setText("查询范围在0-" + NUM + ",请重新查询");
			return;
		}
		for (int i = 0; i < user.length; i++) {
			user[i].setCheck(false);
			user[i].setRoute(user[i].toString());
		}
		list2.clear();
		list2.add(user[form]);
		
		progressDialog.setTitle("正在查询...");
		progressDialog.show();
		new Thread(new Runnable() {
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				findFriendsByID1(1);
				progressDialog.dismiss();
				mHandler.sendEmptyMessage(0);
			}
		}).start();
		
		
//		showView.setText("\n" + user[to].getRoute());
		
	};
	
	private void initViews() {
		// TODO Auto-generated method stub
//		((TextView)findViewById(R.id.contents_view)).setText(contents);
		showView = (TextView) findViewById(R.id.show_view);
		
		fromEditText = (EditText) findViewById(R.id.edit_from);
		toEditText = (EditText) findViewById(R.id.edit_to);
		
		findViewById(R.id.refresh_button).setOnClickListener(this);
		
		changeView = (TextView) findViewById(R.id.change_view);
		changeView.setOnClickListener(this);
	}

	private boolean findFriendsByID1(int i) {

		if (i <= 20) {
			list1.clear();
			list1.addAll(list2);
			list2.clear();
			for (User aUser : list1) {
				if (aUser == user[to]) {
					user[to].setRoute(aUser.getRoute() + "->"
							+ user[to].getRoute());
					return true;
				} else {
					aUser.setCheck(true);
				}
				for (User oUser : aUser.getFriendsList()) {
					if (!list2.contains(oUser)) {
						oUser.setRoute(aUser.getRoute() + "->" + oUser.getRoute());
						list2.add(oUser);
					}
				}
			}
			Log.i("zlt",
					"list2 = " + list2.size() + ","
							+ Arrays.toString(list2.toArray()));
			for (int j = 0, length = list2.size(); j < length; j++) {

				if (list2.get(j).isCheck()) {
					list2.remove(j);
					length--;
				}
			}
			for (User bUser : list2) {
				if (bUser.hasFriend(user[to])) {
					user[to].setRoute(bUser.getRoute() + "->"
							+ user[to].getRoute());
					return true;
				} else {
					bUser.setCheck(true);
				}
			}
			findFriendsByID1(i + 1);
		}
		return false;
	}

	private boolean findFriendsByID(User user1, User user2) {
		// TODO Auto-generated method stub
		if (user1.hasFriend(user2)) {
			user2.setRoute(user1.getRoute() + "->" + user2.getRoute() + "\n\n");
			return true;
		} else {
			user1.setCheck(true);
			for (User mUser : user1.getFriendsList()) {
				if (!mUser.isCheck()) {
					mUser.setCheck(true);
					mUser.setRoute(user1.getRoute() + "->" + mUser.getRoute());
					findFriendsByID(mUser, user2);
				}
			}
		}
		return false;
	}

	private List<User> list1 = new ArrayList<User>();
	private List<User> list2 = new ArrayList<User>();
	private int flag = 1;

	public void initFriends() {
		for (int i = 0; i < NUM; i++) {
			user[i] = new User(i);
		}

		for (int i = 0; i < NUM; i++) {
			int randFriendsNum = new Random().nextInt(4) + 2;
			for (int j = 0; j < randFriendsNum; j++) {
				int randId = new Random().nextInt(NUM);
				user[i].makeFriends(user[randId]);
			}
		}
		//
		// user[1].makeFriends(user[2]);
		// user[2].makeFriends(user[4]);
		// user[3].makeFriends(user[5]);
		// user[5].makeFriends(user[4]);
		progressDialog.dismiss();
	}

	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		switch (v.getId()) {
		case R.id.refresh_button:
			onquery();
			break;
		case R.id.change_view:
			String formText = fromEditText.getText().toString();
			String toText = toEditText.getText().toString();
			
			fromEditText.setText(toText);
			toEditText.setText(formText);
			onquery();
			break;
		default:
			break;
		}
	}
}
