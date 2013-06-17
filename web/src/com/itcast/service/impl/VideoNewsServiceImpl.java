package com.itcast.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.itcast.domain.News;
import com.itcast.service.VideoNewsService;

public class VideoNewsServiceImpl implements VideoNewsService {
	public List<News> getLastNews(){
		List<News> newses=new ArrayList<News>();
		newses.add(new News(35, "喜洋洋胡泰来", 90));
		newses.add(new News(40, "老张和胡泰来", 20));
		newses.add(new News(1, "asdf大法师", 20));
		return newses;
	}
}
