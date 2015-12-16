package com.zlt.test;

import java.io.Serializable;

public class RecentChat implements Serializable,Comparable<RecentChat>{
	
	private static final long serialVersionUID = 1890971987945779836L;
	
	/**
	 * 昵称
	 */
	private String nike;
	/**
	 * 用户名
	 */
	private String userName;
	/**
	 * 用户ID
	 */
	private String targetID;
	/**
	 * 头像
	 */
	private String avator;
	/**
	 * 消息
	 */
	private String message;
	/**
	 * 对话显示时间
	 */
	private long time;
	/**
	 * 显示消息类型
	 */
	private int type;
	
	public String getNike() {
		return nike;
	}
	public void setNike(String nike) {
		this.nike = nike;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getTargetID() {
		return targetID;
	}
	public void setTargetID(String targetID) {
		this.targetID = targetID;
	}
	public String getAvator() {
		return avator;
	}
	public void setAvator(String avator) {
		this.avator = avator;
	}
	public String getMessage() {
		return message;
	}
	public void setMessage(String message) {
		this.message = message;
	}
	public long getTime() {
		return time;
	}
	public void setTime(long time) {
		this.time = time;
	}
	public int getType() {
		return type;
	}
	
	public void setType(int type) {
		this.type = type;
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		int hashcode = 0;
		int length = this.userName.length();
		char c;
		for (int i = 0; i < length; i++) {
			c = userName.charAt(i);
			hashcode += ((int)c) * Math.pow(31, length - 1 - i);
		}
		return hashcode * 10;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.nike;
	}
	
	@Override
	public boolean equals(Object o) {
		// TODO Auto-generated method stub
		return super.equals(o);
	}

	@Override
	public int compareTo(RecentChat recentChat) {
		// TODO Auto-generated method stub
		return 0;
	}
}
