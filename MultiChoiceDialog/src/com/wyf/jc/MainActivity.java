package com.wyf.jc;

import com.example.multichoicedialog.R;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity {
	final int List_DIALOG_MULTIPLE=0;
	String[] item=null;
	boolean[] mulFlag=new boolean[]{false,false,false};
	private Button bListMulti;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		bListMulti=(Button) this.findViewById(R.id.Button);
		item=getResources().getStringArray(R.array.ms);
		bListMulti.setOnClickListener(new View.OnClickListener() {
			
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
			b.setIcon(R.drawable.pic2);
			b.setTitle(R.string.listmultiple);
			b.setMultiChoiceItems(R.array.ms,mulFlag,
					new DialogInterface.OnMultiChoiceClickListener() {
						
						@Override
						public void onClick(DialogInterface dialog, int which, boolean isChecked) {
							mulFlag[which]=isChecked;//哪个被选中
							String resultMsg="多选按钮对话框 ";
							for (int i = 0; i < mulFlag.length; i++) {
								if (mulFlag[i]) {
									resultMsg=resultMsg+item[i]+"、";
								}
							}
							EditText et=(EditText) findViewById(R.id.EditText);
							et.setText(resultMsg.substring(0, resultMsg.length()-1));
							
						}
					});
			b.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
				
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
