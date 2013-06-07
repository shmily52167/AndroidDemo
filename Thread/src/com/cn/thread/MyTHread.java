package com.cn.thread;


import android.os.Bundle;
import android.os.Message;




public class MyTHread extends Thread {
	int count;
	MainActivity activity;
	boolean flag = true;//循环标志位

	public MyTHread(MainActivity activity) {
		this.activity = activity;
	}

	@Override
	public void run() {
		while (flag) {
			if (count>=10) {
				flag=false;
			}
		
		String msg="第"+(count++)+"次更改TextView的文字";
		Bundle bd=new Bundle();
		bd.putString("msg",msg);
		Message tempMessage=new Message();
		tempMessage.setData(bd);
		tempMessage.what=0;
		activity.hd.sendMessage(tempMessage);
		try {
			Thread.sleep(200);
		} catch (Exception e) {
			e.printStackTrace();
		}
	
	}
	
	}
}
