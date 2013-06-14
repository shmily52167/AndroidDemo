package com.itcast.db;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.widget.SimpleCursorAdapter;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import com.example.db.R;
import com.itcast.adapter.PersonAdapter;
import com.itcast.domain.Person;
import com.itcast.service.PersonService;

public class MainActivity extends Activity {
	private static final String TAG = "MainActivity";
	private ListView listView;
	private PersonService personService;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		personService=new PersonService(this);
		listView=(ListView) this.findViewById(R.id.listview);
		listView.setOnItemClickListener(new ItemClickListener());
		show3();
	}
	private final class ItemClickListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
			ListView lView=(ListView) parent;
			Person person=(Person) lView.getItemAtPosition(position);
			Toast.makeText(getApplicationContext(), person.getId().toString(),Toast.LENGTH_SHORT).show();
			
//			Cursor cursor=(Cursor) lView.getItemAtPosition(position);
//			int personid=cursor.getInt(cursor.getColumnIndex("_id"));
//			Toast.makeText(getApplicationContext(), personid+""	, Toast.LENGTH_SHORT).show();
		
		}
		
	}
	private void show3(){
		List<Person> persons=personService.getScrollData(0,20);
		PersonAdapter adapter=new PersonAdapter(this, persons,R.layout.item);
		listView.setAdapter(adapter);
	}
	private void show2() {
	Cursor c=personService.getCursotScrollData(0, 20);
		@SuppressWarnings("deprecation")
		SimpleCursorAdapter adapter=new SimpleCursorAdapter(this,R.layout.item, c, 
				new String[]{"name","age","phone"}, new int[]{R.id.name,R.id.age,R.id.phone});
		listView.setAdapter(adapter);
	

	}
	private void show() {
		List<Person> persons=personService.getScrollData(0, 20);
		List<HashMap<String, Object>> data=new ArrayList<HashMap<String,Object>>();
		for (Person person:persons) {
			HashMap<String, Object> item=new HashMap<String, Object>();
			item.put("name", person.getName());
			item.put("age", person.getAge());
			item.put("phone", person.getPhone());
			item.put("id", person.getId());
			data.add(item);
		}
		SimpleAdapter adapter=new SimpleAdapter(this, data, R.layout.item,
				new String[]{"name","age","phone"} ,new int[]{R.id.name,R.id.age,R.id.phone});
	
		listView.setAdapter(adapter);	
		
		}



}
