package com.itcast.service;

import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.itcast.domain.Person;

public class PersonService {
	private DBOpenHelper dbOpenHelper;

	public PersonService(Context context) {

		this.dbOpenHelper = new DBOpenHelper(context);
	}

	/**
	 * 添加记录
	 * @param person
	 */
	public void save(Person person) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL(
				"insert into person(name,age,phone)values(?,?,?)",
				new Object[] { person.getName(), person.getAge(),
						person.getPhone() });
		db.close();
	}

	/**
	 * 删除记录
	 * @param id
	 */
	public void delete(Integer id) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL("delete from person where personid=?", new Object[] { id });
		db.close();
	}

	/**
	 * 更新记录
	 * @param person
	 */
	public void update(Person person) {
		SQLiteDatabase db = dbOpenHelper.getWritableDatabase();
		db.execSQL(
				"update person set name=?,age=?,phone=? where personid=?",
				new Object[] { person.getName(), person.getAge(),
						person.getPhone(), person.getId() });
		db.close();
	}

	/**
	 * 查询记录
	 * @param id
	 * @return
	 */
	public Person find(Integer id) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select * from person where personid=?",
				new String[] { id.toString() });
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
		Cursor cursor = db.rawQuery(
				"select * from person order by personid asc limit ?,?",
				new String[] { String.valueOf(offset),
						String.valueOf(maxResult) });
		while (cursor.moveToNext()) {// ResultSet.next()
			int id = cursor.getInt(cursor.getColumnIndex("personid"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			int age = cursor.getInt(cursor.getColumnIndex("age"));
			String phone = cursor.getString(cursor.getColumnIndex("phone"));
			persons.add(new Person(id, name, age, phone));
		}
		cursor.close();
		return persons;
	}
	/**
	 * 分页查询
	 * @param offset 跳过前面多少条记录
	 * @param maxResult 每页获取多少条记录
	 * @return
	 */
	public Cursor getCursotScrollData(int offset, int maxResult) {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery(
				"select personid as _id,name,age,phone from person order by personid asc limit ?,?",
				new String[] { String.valueOf(offset),
						String.valueOf(maxResult) });
		return cursor;
	}

	/**
	 * 获取记录总数
	 * @return
	 */
	public long getCount() {
		SQLiteDatabase db = dbOpenHelper.getReadableDatabase();
		Cursor cursor = db.rawQuery("select count(*) from person", null);
		cursor.moveToFirst();
		long result = cursor.getLong(0);
		return result;
	}
}
