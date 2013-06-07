package com.wf.jc;

import java.util.Calendar;

import com.example.datepickerdialog.R;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.DatePickerDialog.OnDateSetListener;
import android.app.Dialog;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.TimePicker;

public class MainActivity extends Activity {
	final int DATE_DIALOG=0;
	final int TIME_DIALOG=1;
	Calendar calendar=null;
	private Button bDate;
	private Button bTime;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		bDate=(Button) this.findViewById(R.id.Button01);
		bTime=(Button) this.findViewById(R.id.Button02);
		bDate.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
			  showDialog(DATE_DIALOG);
				
			}
		});
		bTime.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				showDialog(TIME_DIALOG);
				
			}
		});
	}

	@Override
	@Deprecated
	protected Dialog onCreateDialog(int id) {
		Dialog dl=null;
			switch (id) {
			case DATE_DIALOG:
				calendar=Calendar.getInstance();//生成日期对话框的代码
				dl=new DatePickerDialog(this, new OnDateSetListener() {
					@Override
					public void onDateSet(DatePicker view, int year, int monthOfYear,
							int dayOfMonth) {//设置日期要做的工作
						calendar=Calendar.getInstance();
						calendar.set(year, monthOfYear, dayOfMonth);
						boolean b=SystemClock.setCurrentTimeMillis(calendar.getTimeInMillis());
						
					}
				}, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
				break;

			case TIME_DIALOG:
				calendar=Calendar.getInstance();
				dl=new TimePickerDialog(this, new OnTimeSetListener() {
					
					@Override
					public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
						calendar=Calendar.getInstance();
						calendar.set(Calendar.HOUR_OF_DAY,hourOfDay);
						calendar.set(Calendar.MINUTE, minute);
						boolean b=SystemClock.setCurrentTimeMillis(calendar.getTimeInMillis());
						
					}
				}, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE),false);
				break;
			}
		return dl;
	}

}
