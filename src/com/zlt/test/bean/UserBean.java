package com.zlt.test.bean;

import org.litepal.crud.DataSupport;

public class UserBean extends DataSupport{
	
	private String message;
	
	private Boolean status;
	
	private UserInfoBean userInfo;

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Boolean getStatus() {
		return status;
	}

	public void setStatus(Boolean status) {
		this.status = status;
	}

	public UserInfoBean getUserInfo() {
		return userInfo;
	}

	public void setUserInfo(UserInfoBean userInfo) {
		this.userInfo = userInfo;
	}
	
}
