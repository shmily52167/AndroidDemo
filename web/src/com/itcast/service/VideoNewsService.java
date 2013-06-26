package com.itcast.service;

import java.util.List;

import com.itcast.domain.News;

public interface VideoNewsService {

	/**
	 * 获取最新视频资源
	 * @return
	 */
	public List<News> getLastNews();

}