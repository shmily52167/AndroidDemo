package com.itcast.service;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.itcast.domain.Person;

public class OtherPersonService {
	private DBOpenHelper dbOpenHelper;

	public OtherPersonService(Context context) {

		this.dbOpenHelper = new DBOpenHelper(context);
	}

	/**
	 * 添加记录
	 * @param person
	 */
	public void save(Person person) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		ContentValues values=new ContentValues();
		values.put("name", person.getName());
		values.put("age", person.getAge());
		values.put("phone", person.getPhone());
		db.insert("person",null, values);
	}

	/**
	 * 删除记录
	 * @param id
	 */
	public void delete(Integer id) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.delete("person", "personid=?", new String[]{id.toString()});
	}

	/**
	 * 更新记录
	 * @param person
	 */
	public void update(Person person) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		ContentValues values=new ContentValues();
		values.put("name", person.getName());
		values.put("age", person.getAge());
		values.put("phone", person.getPhone());
		db.update("person", values	, "personid=?", new String[]{person.getId().toString()});
		db.close();
	}

	/**
	 * 查询记录
	 * @param id
	 * @return
	 */
	public Person find(Integer id) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
	Cursor cursor=db.query("person", null, "person=?", new String[]{id.toString()}, null,null, null);
		if (cursor.moveToFirst()) {
			int personid = cursor.getInt(cursor.getColumnIndex("personid"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			int age = cursor.getInt(cursor.getColumnIndex("age"));
			String phone = cursor.getString(cursor.getColumnIndex("phone"));
			return new Person(personid, name, age, phone);
		}
		cursor.close();
		return null;
	}

	/**
	 * 分页查询
	 * @param offset 跳过前面多少条记录
	 * @param maxResult 每页获取多少条记录
	 * @return
	 */
	public List<Person> getScrollData(int offset, int maxResult) {
		List<Person> persons = new ArrayList<Person>();
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor=db.query("person", null, null, null, null, null, "personid asc", offset+","+maxResult);
		while (cursor.moveToNext()) {// ResultSet.next()
			int personid = cursor.getInt(cursor.getColumnIndex("personid"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			int age = cursor.getInt(cursor.getColumnIndex("age"));
			String phone = cursor.getString(cursor.getColumnIndex("phone"));
			persons.add(new Person(personid, name, age, phone));
		}
		cursor.close();
		return persons;
	}



	/**
	 * 获取记录总数
	 * @return
	 */
	public long getCount() {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor=db.query("person", new String[]{"count(*)"}, null, null, null, null, null);
		cursor.moveToFirst();
		long result = cursor.getLong(0);
		return result;
	}
}
