package com.itcast.test;
import java.util.ArrayList;
import java.util.List;
import android.content.ContentResolver;
import android.content.ContentValues;
import android.database.Cursor;
import android.net.Uri;
import android.test.AndroidTestCase;
import android.util.Log;

public class AccessContentProviderTest extends AndroidTestCase {
	private static final String TAG = "AccessContentProviderTest";
	public void testInsert()throws Exception{
		Uri uri=Uri.parse("content://com.itcast.provides.personprovider/person");
		ContentResolver resolver=this.getContext().getContentResolver();
		ContentValues values=new ContentValues();
		values.put("name", "zhanxueqin");
		values.put("age", 18);
		values.put("phone", "13775990214");
		resolver.insert(uri, values);
	}
	public void testDelete()throws Exception{
		Uri uri=Uri.parse("content://com.itcast.provides.personprovider/person");
		ContentResolver resolver=this.getContext().getContentResolver();
		resolver.delete(uri, "personid=?", new String[]{"19"});
	}
	public void testUpdate()throws Exception{
		Uri uri=Uri.parse("content://com.itcast.provides.personprovider/person");
		ContentResolver resolver=this.getContext().getContentResolver();
		ContentValues values=new ContentValues();
		values.put("name", "詹学亲");
		values.put("age", 25);
		resolver.update(uri, values, "personid=?", new String[]{"17"});
	}
	public void testQuery()throws Exception{
		List<Person> persons=new ArrayList<Person>();
		Uri uri=Uri.parse("content://com.itcast.provides.personprovider/person");
		ContentResolver resolver=this.getContext().getContentResolver();
		Cursor cursor=resolver.query(uri, null, null, null, "personid asc");
		while (cursor.moveToNext()) {
			int personid = cursor.getInt(cursor.getColumnIndex("personid"));
			String name = cursor.getString(cursor.getColumnIndex("name"));
			int age = cursor.getInt(cursor.getColumnIndex("age"));
			String phone = cursor.getString(cursor.getColumnIndex("phone"));
			persons.add(new Person(personid, name, age, phone));
		}
		for (Person person : persons) {
			Log.i(TAG, person.toString());
		}
		cursor.close();
	}
}
