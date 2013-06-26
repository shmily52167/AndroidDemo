package con.itcast.service;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import con.itcast.utils.StreamTool;

public class ImageService {
	/**
	 * 获取网络图片的数据
	 * @param path
	 * @return
	 */
	public static byte[] getImage(String path) throws Exception{
		URL url=new URL(path);
	    HttpURLConnection conn=	(HttpURLConnection) url.openConnection();//基于Http协议链接对象
		conn.setConnectTimeout(5000);
		conn.setRequestMethod("GET");
	    if (conn.getResponseCode()==200) {
	    	InputStream inStream=conn.getInputStream(); 
	      return StreamTool.read(inStream);
		}
	    return null;
	}

}
