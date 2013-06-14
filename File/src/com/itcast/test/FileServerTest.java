package com.itcast.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;

import com.itcast.service.FileService;
import android.test.AndroidTestCase;
import android.util.Log;

public class FileServerTest extends AndroidTestCase {
	private static final String TAG = "FileServerTest";
	
	
	
	public void testSDCard()throws Exception{
		
	}
	public void testRead()throws Exception{
//		FileService service=new FileService(this.getContext());
//			String content=service.read("itcast.txt");
//			Log.i(TAG, content);
//		FileInputStream inStream=this.getContext().openFileInput("itcast.txt");
		
		File file=new File(getContext().getFilesDir(), "Writeable.txt");
		
		FileInputStream inStream=new FileInputStream(file);
		Log.i(TAG, readInStream(inStream));
		
	}
	public void testApend()throws Exception{
		FileService service=new FileService(this.getContext());
		service.saveAppend("append.txt", "HelloWord");
	}
	public void testReadable()throws Exception{
		FileService service=new FileService(this.getContext());
		service.saveReadable("Readable.txt", "Hello");
	}
	public void testWrite()throws Exception{
		FileService service=new FileService(this.getContext());
		service.saveWriteable("Writeable.txt", "Write");
	}
	public void testWriteRead()throws Exception{
		FileService service=new FileService(this.getContext());
		service.saveReadWrite("writeRead.txt", "Hello");
	}
	public String readInStream(FileInputStream inStream) throws Exception {
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		byte[] data = outStream.toByteArray();
		return new String(data);
	}
}