package com.zlt.test;

import java.io.Serializable;
import java.util.Date;

import org.litepal.crud.DataSupport;

import android.R.integer;

public class Person extends DataSupport implements Serializable{

	public Date getPublishDate() {
		return publishDate;
	}
	public void setPublishDate(Date publishDate) {
		this.publishDate = publishDate;
	}

	private static final long serialVersionUID = -5711787823266804627L;
	
	public static final String NAME = "name";
	public static final String AGE = "age";
	public static final String SEX = "sex";
	public static final String PUBLISHDATE = "publishDate";
	
	private String name;
	private int age;
	private String sex;
	private Date publishDate;
	
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getAge() {
		return age;
	}
	
	@Override
	public String toString() {
		// TODO Auto-generated method stub
		StringBuilder sb = new StringBuilder();
		sb.append("name = " + name);
		sb.append("\n");
		sb.append("age = " + age);
		sb.append("\n");
		sb.append("set = " + sex);
		
		return sb.toString();
	}
}
