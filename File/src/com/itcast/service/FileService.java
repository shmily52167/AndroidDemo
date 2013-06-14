package com.itcast.service;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Environment;

public class FileService {
	private Context context;

	public FileService(Context context) {
		this.context = context;
	}

	/**文件保存在SDCard
	 * @param filename
	 * @param content
	 * @throws Exception
	 */
	public void saveToSDCard(String filename,String content)throws Exception{
		//获取SDCard卡的路径
		File file =new File(Environment.getExternalStorageDirectory(),filename);
		FileOutputStream outStream=new FileOutputStream(file);
		write(content, outStream);
	}
	/**
	 * 保存文件
	 * @param finename 文件名称
	 * @param content  文件内容
	 * @throws Exception
	 */
	public void savePrivate(String finename, String content) throws Exception {
		// 私有模式：创建出来的文件只能被本应用访问，其他应用无法访问该文件，另外采用私有模创建的文件，写入文件中的内容会覆盖原来的内容
		FileOutputStream outStrea = context.openFileOutput(finename,
				Context.MODE_PRIVATE);
		write(content, outStrea);
	}

	/**  添加文件内容但是不覆盖原来的内容
	 * @param finename
	 * @param content
	 * @throws Exception
	 */
	public void saveAppend(String finename, String content) throws Exception {
		FileOutputStream outStrea = context.openFileOutput(finename,
				Context.MODE_APPEND);
		write(content, outStrea);
	}
	/**可被其他应用读却
	 * @param finename
	 * @param content
	 * @throws Exception
	 */
	@SuppressLint("WorldReadableFiles")
	public void saveReadable(String finename,String content)throws Exception{
		@SuppressWarnings("deprecation")
		FileOutputStream outStream=context.openFileOutput(finename, Context.MODE_WORLD_READABLE);
		write(content, outStream);
	}
	/**
	 * 可被其他应用写入
	 * @param finename
	 * @param content
	 * @throws Exception
	 */
	@SuppressLint("WorldWriteableFiles")
	@SuppressWarnings("deprecation")
	public void saveWriteable(String finename,String content)throws Exception{
		FileOutputStream outStream=context.openFileOutput(finename, Context.MODE_WORLD_WRITEABLE);
		write(content, outStream);
	}
	/**
	 * 被其他应用可读可写
	 * @param filename
	 * @param content
	 * @throws Exception
	 */
	@SuppressWarnings({ "deprecation", "static-access", })
	@SuppressLint({ "WorldReadableFiles", "WorldWriteableFiles" })
	public void saveReadWrite(String filename,String content)throws Exception{
		FileOutputStream outStream=context.openFileOutput(filename, Context.MODE_WORLD_READABLE+context.MODE_WORLD_WRITEABLE);
		write(content, outStream);
	}
	/**
	 * 抽取方法Shift+alt+m   写入内容
	 * @param content
	 * @param outStrea
	 * @throws IOException
	 */
	private void write(String content, FileOutputStream outStrea)
			throws IOException {
		outStrea.write(content.getBytes());
		outStrea.close();
	}

	/**
	 * 读取文件内容
	 * @param filename
	 * @return
	 * @throws Exception
	 */
	public String read(String filename) throws Exception {
		FileInputStream inputStream = context.openFileInput(filename);
		ByteArrayOutputStream outStream = new ByteArrayOutputStream();
		byte[] buffer = new byte[1024];
		int len = 0;
		while ((len = inputStream.read(buffer)) != -1) {
			outStream.write(buffer, 0, len);
		}
		byte[] data = outStream.toByteArray();
		return new String(data);
	}

}
