package com.wyf.jc;

import com.example.alertdialog.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	final int COMMON_DIALOG=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		Button button=(Button) this.findViewById(R.id.Button01);
		button.setOnClickListener(new OnClickListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				showDialog(COMMON_DIALOG);
				
			}
		});
	}
	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		Dialog dl=null;
		switch (id) {
		case COMMON_DIALOG:
			Builder b=new AlertDialog.Builder(this);
			b.setIcon(R.drawable.ic_launcher);
			b.setTitle(R.string.comm);
			b.setMessage(R.string.commsg);
			b.setPositiveButton(R.string.ok, 
					new DialogInterface.OnClickListener() {//为按钮添加点击事件
						@Override
						public void onClick(DialogInterface dialog, int which) {
							EditText et=(EditText) findViewById(R.id.EditText01);
							et.setText("你点击了确定按钮");
							
						}
					   }
					);
			dl=b.create();
			break;
			
		}
		return dl;
	}

}
