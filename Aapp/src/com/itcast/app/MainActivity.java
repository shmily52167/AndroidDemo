package com.itcast.app;

import com.example.aapp.R;

import android.app.Activity;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}
	public void insert(View v){
		Uri uri=Uri.parse("content://com.itcast.provides.personprovider/person");
		ContentResolver resolver=this.getContentResolver();
		ContentValues values=new ContentValues();
		values.put("name", "小姑头");
		values.put("age", 18);
		values.put("phone","1305893525");
		resolver.insert(uri, values);
	}



}
