package com.zlt.test;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.StreamCorruptedException;

import cn.bmob.push.a.t;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

public class SerializeAndDeSerialize extends Activity {

	Person mPerson = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		
		TextView textView = new TextView(this);
		setContentView(textView);
		
		try {
			SerializePerson();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			mPerson = DeSerializePerson();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		 
		if (mPerson != null) {
			textView.setText(mPerson.toString());
		}
		
	}
	
	private void SerializePerson() throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		Person person = new Person();
		person.setName("Tom");
		person.setAge(25);
		person.setSex("Male");
		
		ObjectOutputStream oo = new ObjectOutputStream(new FileOutputStream(new File("/sdcard/Person.text")));
		oo.writeObject(person);
		Log.i("zlt", "Serialize successful!");
		oo.close();
	}
	
	
	private Person DeSerializePerson() throws IOException, Exception{
		// TODO Auto-generated method stub
		ObjectInputStream oi = new ObjectInputStream(new FileInputStream(new File("/sdcard/Person.text")));
		Person person = (Person) oi.readObject();
		Log.i("zlt", "DeSerialize successful!");
		return person;
	}
}
