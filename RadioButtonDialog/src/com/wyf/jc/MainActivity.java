package com.wyf.jc;

import com.example.radiobuttondialog.R;

import android.R.integer;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	final int List_DIALOG_MULTIPLE=0;
	String[] items=null;
	boolean [] mulFlags=new boolean[]{false,false,false};//初始化复选情况
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		items=getResources().getStringArray(R.array.ms);
		Button bListMultiple=(Button) this.findViewById(R.id.Button01);
		bListMultiple.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showDialog(List_DIALOG_MULTIPLE);
				
			}
		});
	}

	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		Dialog dialog=null;
		Builder b=new AlertDialog.Builder(this);
		switch (id) {
		case List_DIALOG_MULTIPLE:
			b=new AlertDialog.Builder(this);
			b.setIcon(R.drawable.ic_launcher);
			b.setTitle(R.string.listmultiple);
			b.setMultiChoiceItems(//设置对话框中的内容可以多选
					R.array.ms,//选择字符 
					mulFlags,
					new OnMultiChoiceClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which, boolean isChecked) {
					mulFlags[which]=isChecked;//哪一个被选中
					String resultMsg="多选按钮对话框";
					for (int i = 0; i < mulFlags.length; i++) {
						if (mulFlags[i]) {
							resultMsg=resultMsg+items[i]+"、";
						}
					}
					EditText et=(EditText) findViewById(R.id.EditText01);
					et.setText(resultMsg.substring(0,resultMsg.length()-1));
					
				}
			});
			b.setPositiveButton(R.string.ok,new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					
					
				}
			});
			dialog=b.create();
			break;

	
		}
		return dialog;
	}

}
