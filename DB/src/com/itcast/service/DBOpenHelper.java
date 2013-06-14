package com.itcast.service;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBOpenHelper extends SQLiteOpenHelper {

	public DBOpenHelper(Context context) {
		super(context, "itcast.db", null, 2);
	}

	@Override
	public void onCreate(SQLiteDatabase db) {//实在数据库每一次被创建的时候调用的
		db.execSQL("create table if not exists person(personid integer primary key autoincrement,name varchar(20),age integer)");

	}
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
  db.execSQL("alter table person add phone varchar(12) null");
	}

}
