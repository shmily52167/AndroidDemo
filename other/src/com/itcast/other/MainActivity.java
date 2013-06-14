package com.itcast.other;

import com.example.other.R;
import com.itcast.test.Person;

import android.app.Activity;
import android.database.ContentObserver;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		//注册一个数据变化监听器
		Uri uri=Uri.parse("content://com.itcast.provides.personprovider/person");
		this.getContentResolver().registerContentObserver(uri, true,new PersonContrentObserver(new Handler()));
	}
	private class PersonContrentObserver extends ContentObserver{
		public PersonContrentObserver(Handler handler) {
			super(handler);
		}
		@Override
		public void onChange(boolean selfChange) {
			//select * from person order by personid desc limit 1
			Uri uri=Uri.parse("content://com.itcast.provides.personprovider/person");
		Cursor cursor=getContentResolver().query(uri, null, null, null, "personid desc limit 1");
		if (cursor.moveToFirst()) {
		String name=cursor.getString(cursor.getColumnIndex("name"));
		int age=cursor.getInt(cursor.getColumnIndex("age"));
		String phone=cursor.getString(cursor.getColumnIndex("phone"));
		Person person=new Person(name, age, phone);
			Log.i(TAG,person.toString());
		}
		}
		
		
	}

}
