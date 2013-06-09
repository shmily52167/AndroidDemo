package com.wy.jc;

import com.example.singlechoicedialog.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	final int List_DIALOG_SINGLE=0;
	private Button bListSingle;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		bListSingle=(Button) this.findViewById(R.id.Button01);
		bListSingle.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showDialog(List_DIALOG_SINGLE);
				
			}
		});
		
	}
	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		Dialog dialog=null;
		Builder b=new AlertDialog.Builder(this);
		switch (id) {
		case List_DIALOG_SINGLE:
			b.setIcon(R.drawable.pic2);
			b.setTitle(R.string.listsingle);
			b.setSingleChoiceItems(R.array.ms,0,new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					EditText et=(EditText) findViewById(R.id.EditText01);
					et.setText("单选按钮对话框"+getResources().getStringArray(R.array.ms)[which]);
					
				}
			});
			b.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					// TODO Auto-generated method stub
					
				}
			});
			dialog=b.create();
			break;

		}
		return dialog;
	}
	

}
