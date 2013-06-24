package com.itcast.news;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.example.news.R;
import com.itcast.adapter.NewsAdapter;
import com.itcast.domain.News;
import com.itcast.service.VideoNewsService;
import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class MainActivity extends Activity {
	private ListView listView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		listView=(ListView) this.findViewById(R.id.listview);
		listView.setOnItemClickListener(new ItemClickListener());
		new Thread(downloadRun).start();
	}
	Runnable downloadRun=new Runnable() {
		@Override
		public void run() {
			show2();
		}
	};
	private final class ItemClickListener implements OnItemClickListener{

		@Override
		public void onItemClick(AdapterView<?> parent, View view, int position,
				long id) {
				ListView listView=(ListView) parent;
				News news=(News) listView.getItemAtPosition(position);
				Toast.makeText(getApplicationContext(), news.getId().toString(), Toast.LENGTH_SHORT).show();
		}
	}
	public void show2(){
		try {
			List<News> videos=VideoNewsService.getJSONLastNews();
			NewsAdapter adapter=new NewsAdapter(this,videos,R.layout.item);
			listView.setAdapter(adapter);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	public void show(){
		try {
			 List<News> videos=VideoNewsService.getLastNews();
			 List<HashMap<String, Object>> data=new ArrayList<HashMap<String,Object>>();
			for(News news:videos){
				HashMap<String, Object> item=new HashMap<String, Object>();
				item.put("id", news.getId());
				item.put("title", news.getTitle());
				item.put("timelength", news.getTimelength());
				data.add(item);
			}
			SimpleAdapter adapter =new SimpleAdapter(getApplicationContext(), data, R.layout.item,
					new String[]{"title","timelength"},new int[]{R.id.title,R.id.timelength});
			listView.setAdapter(adapter);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
