package com.wyf.jc;

import com.example.listdialog.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	final int List_DIALOG=1;
	private Button bList;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		bList=(Button) this.findViewById(R.id.Button01);
		bList.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
			
				showDialog(List_DIALOG);
			}
		});
		
	}
	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		Dialog dialog=null;
		Builder b=new AlertDialog.Builder(this);
		switch (id) {
		case List_DIALOG:
			b.setIcon(R.drawable.pic1);
			b.setTitle(R.string.list);
			b.setItems(R.array.ms,new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					EditText et=(EditText) findViewById(R.id.EditText01);
					et.setText("列表对话框"+
					getResources().getStringArray(R.array.ms)[which]);
					
				}
			});
			dialog=b.create();
			break;

		
		}
		return dialog;
	}
	

}
