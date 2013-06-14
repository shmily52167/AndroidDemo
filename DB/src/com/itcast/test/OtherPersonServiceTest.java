package com.itcast.test;

import java.util.List;

import com.itcast.domain.Person;
import com.itcast.service.DBOpenHelper;
import com.itcast.service.OtherPersonService;
import android.test.AndroidTestCase;
import android.util.Log;

public class OtherPersonServiceTest extends AndroidTestCase {
	private static final String TAG = "OtherPersonServiceTest";
	
	public void testCreateDB()throws Exception{
		DBOpenHelper dbOpenHelper=new DBOpenHelper(getContext());
		dbOpenHelper.getWritableDatabase();
	}
	public void testSave()throws Exception{
		OtherPersonService service=new OtherPersonService(this.getContext());
			Person person=new Person("sunjie",18,"119");
			service.save(person);
	}
	public void testDelete()throws Exception{
		OtherPersonService service=new OtherPersonService(this.getContext());
		service.delete(2);
	}
	public void testUpdate()throws Exception{
		OtherPersonService service=new OtherPersonService(this.getContext());
		Person person=service.find(3);
		person.setName("站学期");
		service.update(person);
		
	}
	public void testFind()throws Exception{
		OtherPersonService service=new OtherPersonService(this.getContext());
		Person person=service.find(3);
		Log.i(TAG, person.toString());
	}
	public void testSrollData()throws Exception{
		OtherPersonService service=new OtherPersonService(this.getContext());
		List<Person> persons=service.getScrollData(0, 5);
		for (Person person : persons) {
			Log.i(TAG, person.toString());
		}
	}
	public void testCount()throws Exception{
		OtherPersonService service=new OtherPersonService(this.getContext());
		long result=service.getCount();
		Log.i(TAG, result+"");
	}
}
