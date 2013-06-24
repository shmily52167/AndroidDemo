package com.itcast.service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import con.itcast.utils.StreamTool;

public class PageService {
	/**获取网页HTML源代码
	 * @param path
	 * @return
	 */
	public static String getHtml(String path) throws Exception{
		URL url=new URL(path);
		HttpURLConnection conn=	(HttpURLConnection) url.openConnection();
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("GET");
		if (conn.getResponseCode()==200) {
			InputStream inStream=conn.getInputStream();
		byte[] data=StreamTool.read(inStream);
		String html=new String(data,"UTF-8");
		return html;
		}
		return null;
	}
}
