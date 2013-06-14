package com.itcast.adapter;

import java.util.List;
import com.example.db.R;
import com.itcast.domain.Person;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class PersonAdapter extends BaseAdapter {
	private List<Person> persons;// 在绑定的数据
	private int resource;// 绑定的条目界面
	private LayoutInflater inflater;

	public PersonAdapter(Context context, List<Person> persons, int resource) {

		this.persons = persons;
		this.resource = resource;
		inflater = (LayoutInflater) context
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

	}

	@Override
	public int getCount() {
		return persons.size();
	}

	@Override
	public Object getItem(int position) {
		return persons.get(position);
	}

	@Override
	public long getItemId(int position) {
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView name = null;
		TextView age = null;
		TextView phone = null;

		if (convertView == null) {
			convertView = inflater.inflate(resource, null);//生成条目界面
			name = (TextView) convertView.findViewById(R.id.name);
			age = (TextView) convertView.findViewById(R.id.age);
			phone = (TextView) convertView.findViewById(R.id.phone);

			ViewCache cache = new ViewCache();
			cache.name = name;
			cache.age = age;
			cache.phone = phone;
			convertView.setTag(cache);
		} else {
			ViewCache cache = (ViewCache) convertView.getTag();
			name = cache.name;
			age = cache.age;
			phone = cache.phone;
		}
		//下面代码实现数据绑定
		Person person = persons.get(position);
		name.setText(person.getName());
		age.setText(person.getAge().toString());
		phone.setText(person.getPhone());
		return convertView;

	}

	private final class ViewCache {
		public TextView name;
		public TextView age;
		public TextView phone;
	}
}
