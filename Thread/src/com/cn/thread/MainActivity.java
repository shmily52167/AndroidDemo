package com.cn.thread;
import com.example.thread.R;

import android.annotation.SuppressLint;
import android.app.Activity;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.KeyEvent;
import android.widget.TextView;

@SuppressLint("HandlerLeak")
public class MainActivity extends Activity {
	Handler hd=new Handler(){

		@Override
		public void handleMessage(Message msg) {
			switch (msg.what) {
			case 0:
				Bundle b=msg.getData();
				String str=b.getString("msg");
				tv.setText(str);
				break;
			}
		}
		
	};
TextView tv;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		tv=(TextView) this.findViewById(R.id.TextView01);
		new MyTHread(this).start();
		
	}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode==4) {
			System.exit(0);
		}
		return true;
	}
	

	
	
}
