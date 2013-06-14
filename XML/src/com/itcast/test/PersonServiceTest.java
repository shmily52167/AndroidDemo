package com.itcast.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import android.content.Context;
import android.test.AndroidTestCase;
import android.util.Log;
import com.itcast.domain.Person;
import com.itcast.service.PersonService;

public class PersonServiceTest extends AndroidTestCase {
	private static final String TAG = "PersonServiceTest";
//	private Context context;

	public void testPersons()throws Exception{
//InputStream xml=this.getClass().getClassLoader().getResourceAsStream("person.xml");
FileInputStream xml=this.getContext().openFileInput("it.xml");
List<Person> persons=PersonService.getPersons(xml);
	for (Person person : persons) {
		Log.i(TAG, person.toString());
	}
	}
	public void testsave()throws Exception{
		List<Person> persons=new ArrayList<Person>();
		persons.add(new Person(43,"sunjie",80));
		persons.add(new Person(12,"zhangxueqin",80));
//		File xmlFile=new File(getContext().getFilesDir(),"itcast.xml");
//		FileOutputStream outStream=new FileOutputStream(xmlFile);
		FileOutputStream outStream=this.getContext().openFileOutput("it.xml",Context.MODE_PRIVATE);
		PersonService.save(persons, outStream);
	}
}
