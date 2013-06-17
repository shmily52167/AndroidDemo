package com.itcast.adapter;

import java.util.List;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;
import com.example.news.R;
import com.itcast.domain.News;

public class NewsAdapter extends BaseAdapter{
	private List<News> newses;
	private int resource;
	private LayoutInflater inflater;
	public NewsAdapter(Context context,List<News> newses, int resource) {
		this.newses = newses;
		this.resource = resource;
		inflater=(LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}
	@Override
	public int getCount() {
		return newses.size();
	}
	@Override
	public Object getItem(int position) {
		return newses.get(position);
	}
	@Override
	public long getItemId(int position) {
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		TextView  title=null;
		TextView time=null;
		if (convertView==null) {
			convertView=inflater.inflate(resource, null);
			title=(TextView) convertView.findViewById(R.id.title);
			time=(TextView) convertView.findViewById(R.id.timelength);
			ViewCache cache=new ViewCache();
			cache.title=title;
			cache.time=time;
			convertView.setTag(cache);
		}else {
			ViewCache cache=(ViewCache) convertView.getTag();
			title=cache.title;
			time=cache.time;
		}
		News news=newses.get(position);
		title.setText(news.getTitle());
		time.setText(news.getTimelength().toString());
		return convertView;
	}
	private final class ViewCache{
		private TextView title;
		private TextView time;
	}

}
