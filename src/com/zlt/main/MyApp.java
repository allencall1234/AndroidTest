package com.zlt.main;

import java.util.Date;

import org.litepal.LitePalApplication;
import org.litepal.tablemanager.Connector;

import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.zlt.test.Person;
import com.zlt.test.bean.UserBean;
import com.zlt.test.bean.UserInfoBean;

import android.app.Application;
import android.content.ContentValues;
import android.database.sqlite.SQLiteDatabase;

public class MyApp extends LitePalApplication{
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
//		ImageLoaderConfiguration configuration = ImageLoaderConfiguration.createDefault(this);
			 
		ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this).writeDebugLogs().build();
		ImageLoader.getInstance().init(configuration);
		SQLiteDatabase db = Connector.getDatabase();
		
		ContentValues values = new ContentValues();
		values.put(Person.NAME, "zhangsan");
		values.put(Person.AGE, 15);
		values.put(Person.SEX, "male");
		values.put(Person.PUBLISHDATE, System.currentTimeMillis());
		
		long id = db.insert("person", null, values);
		
		
		Person person = new Person();
		person.setAge(29);
		person.setName("lisi");
		person.setSex("female");
		person.setPublishDate(new Date());
		person.save();
		
		
		UserBean user = new UserBean();
		user.setMessage("nihao");
		user.setStatus(true);
		user.setUserInfo(new UserInfoBean());
		user.save();
	}
}
