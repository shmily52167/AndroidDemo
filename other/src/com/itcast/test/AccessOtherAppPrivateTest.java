package com.itcast.test;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import android.annotation.SuppressLint;
import android.test.AndroidTestCase;
import android.util.Log;

@SuppressLint("SdCardPath")
public class AccessOtherAppPrivateTest extends AndroidTestCase {
	private static final String TAG = "AccessOtherAppPrivateTest";

	public void testAccessPrivate() throws Exception {
		String path = "/data/data/com.example.file/files/itcast.txt";
		read(path);
	}
	public void testAccessAppend()throws Exception{
		
		String path="/data/data/com.example.file/files/append.txt";
		read(path);
	}
	public void testAccessReadable()throws Exception{
		String path="/data/data/com.example.file/files/Readable.txt";
		read(path);
	}
	public void testAccesWrite()throws Exception{
		String path="/data/data/com.example.file/files/Writeable.txt";
		write(path);
	}
	public void testAccessWriteRead()throws Exception{
		String path="/data/data/com.example.file/files/writeRead.txt";
		read(path);
		write(path);
		
	}
	private void write(String path) throws FileNotFoundException, IOException {
		File file=new File(path);
		FileOutputStream outStream=new FileOutputStream(file);
		outStream.write("WriteAble".getBytes());
		outStream.close();
	}
	private void read(String path) throws FileNotFoundException, IOException {
		File file=new File(path);
		ByteArrayOutputStream outStream=new ByteArrayOutputStream();
		FileInputStream inStream=new FileInputStream(file);
		byte[] buffer=new byte[1024];
		int len=0;
		while ((len=inStream.read(buffer))!=-1) {
			outStream.write(buffer,0,len);
		}
		byte[] data=outStream.toByteArray();
		outStream.close();
		inStream.close();
		String content=new String(data);
		Log.i(TAG, content);
	}
}
