package com.zlt.test.bean;

import java.util.ArrayList;
import java.util.List;

import android.R.bool;
import android.text.GetChars;

public class User {

	private int id;
	private List<User> friendsList = null;
	private boolean check;
	
	private String route;
	
	public User() {
		route = this.toString();
		friendsList = new ArrayList<User>();
	}

	public User(int id) {
		this.id = id;
		route = this.toString();
		friendsList = new ArrayList<User>();
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void makeFriends(User user) {
		if (hasFriend(user)) {
			return;
		}
		friendsList.add(user);
		user.makeFriends(User.this);
	}

	public boolean hasFriend(User user) {
		return friendsList.contains(user) ? true : false;
	}

	public String printFriendList() {
		String list = "\n" + this.toString() + " has " + friendsList.size()
				+ " friends: ";
		for (User user : friendsList) {
			list += user.toString();
		}
		return list;
	}

	public List<User> getFriendsList() {
		return friendsList;
	}
	
	public String toString() {
		return "User[" + id + "] ";
	};
	
	public void setCheck(boolean check) {
		this.check = check;
	}
	
	public boolean isCheck() {
		return check;
	}
	
	public void setRoute(String route) {
		this.route = route;
	}
	
	public String getRoute() {
		return route;
	}
}
