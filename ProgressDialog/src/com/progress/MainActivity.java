package com.progress;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.Button;

import com.example.progressdialog.R;

@SuppressLint("HandlerLeak")
public class MainActivity extends Activity {
	private Button bok;
	final int PROGRESS_DIALOG = 0;
	final int INCREASE = 0;// 进度条每次更新的步进
	ProgressDialog pd;// 进度条对话框变量的声明
	Handler hd;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		bok = (Button) this.findViewById(R.id.Button01);
		bok.setOnClickListener(new View.OnClickListener() {

			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				showDialog(PROGRESS_DIALOG);
			}
		});
		hd = new Handler() {

			@Override
			public void handleMessage(Message msg) {
			
				super.handleMessage(msg);
				switch (msg.what) {
				case INCREASE:
					pd.incrementProgressBy(1);//进度每次加1
					if (pd.getProgress()>=100) {
						pd.dismiss();//关闭进度条对话框
					}
					break;
				}
			}// 消息传递
		};
	}

	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		switch (id) {
		case PROGRESS_DIALOG:
			pd = new ProgressDialog(this);
			pd.setMax(100);
			pd.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);// 样式
			pd.setTitle(R.string.title);
			pd.setCancelable(false);// 设置进度对话框不能用回退按钮关
			break;
		}

		return pd;
	}

	@Override
	@Deprecated
	protected void onPrepareDialog(int id, Dialog dialog, Bundle args) {// 每次弹出对话框都被回调更新状态

		super.onPrepareDialog(id, dialog, args);
		switch (id) {
		case PROGRESS_DIALOG:
			pd.incrementProgressBy(-pd.getProgress());// 对话框进度清零
			new Thread() {
				public void run() {
					while (true) {
						hd.sendEmptyMessage(INCREASE);// Handler消息传递
						if (pd.getProgress() >= 100) {
							break;
						}
						try {
							Thread.sleep(40);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}

				}
			}.start();
			break;
		}
	}

}
