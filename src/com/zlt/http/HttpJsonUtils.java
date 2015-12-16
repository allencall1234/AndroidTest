package com.zlt.http;

import android.annotation.SuppressLint;
import java.lang.reflect.Field;
import org.json.JSONObject;

public class HttpJsonUtils {
	/**
	 * 将一个jsonObject对象数据封装到对应的bean对象中
	 * @param json
	 * @param wholeBeanName
	 * @return
	 */
	public static Object putJsonToObject(JSONObject jsonObj,String wholeBeanName){
		Object result = null;
		try {
			// 根据类名来实例一个类对象
			Object user_obj = Class.forName(wholeBeanName).newInstance();
			// 获取这个实例化的类中的所有信息
			Field[] fs = user_obj.getClass().getDeclaredFields();
//			Field[] superFs = user_obj.getClass().getSuperclass()
//					.getDeclaredFields();// 获取父类中的所有属性
			putJSONToBean(user_obj, fs, jsonObj);
//			putJSONToBean(user_obj, superFs, jsonObj);
			result = user_obj;
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result;
	}
	
	@SuppressLint("DefaultLocale")
	private static void putJSONToBean(Object user_obj, Field[] fs,
			JSONObject jsonObj) {
		try {
			for (Field f : fs) {
				f.setAccessible(true);
				System.out.println(f.getName() + "\t\t" + f.getType());
				if (f.getType().toString().endsWith("String")
						|| f.getType().toString().endsWith("string")) {
					f.set(user_obj, jsonObj.opt(f.getName()));
				} else if (f.getType().toString().endsWith("int")
						|| f.getType().toString().endsWith("Integer")) {
					f.setInt(user_obj, jsonObj
							.optInt(f.getName()));
				} else if (f.getType().toString().endsWith("double")
						|| f.getType().toString().endsWith("Double")) {
					f.setDouble(user_obj, jsonObj.optDouble(f.getName()));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
