package com.itcast.alarm;

import java.util.Calendar;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.Dialog;
import android.app.PendingIntent;
import android.app.TimePickerDialog;
import android.app.TimePickerDialog.OnTimeSetListener;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TimePicker;
import android.widget.Toast;

import com.example.alarmmanager.R;

public class MainActivtiy extends Activity {
	Calendar c=Calendar.getInstance();
	final int  DIALOG_TIME=0;
	AlarmManager am;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		am=(AlarmManager) getSystemService(Context.ALARM_SERVICE);
		Button btn=(Button) findViewById(R.id.btn);
		btn.setOnClickListener(new OnClickListener() {
			
			@SuppressWarnings("deprecation")
			@Override
			public void onClick(View v) {
				showDialog(DIALOG_TIME);
			}
		});
	}
	@Override
	protected Dialog onCreateDialog(int id) {
		Dialog dialog=null;
		switch (id) {
		case DIALOG_TIME:
			dialog=new TimePickerDialog(this, new OnTimeSetListener() {
				
				@Override
				public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
					Calendar c=Calendar.getInstance();//获取日期对象
					c.setTimeInMillis(System.currentTimeMillis());
					c.set(Calendar.HOUR, hourOfDay);
					c.set(Calendar.MINUTE, minute);
					c.set(Calendar.SECOND, 0); 
					c.set(Calendar.MILLISECOND, 0);//设置闹钟的毫秒数	
					Intent intent=new Intent(getApplicationContext(),AlarmRecriver.class);
					PendingIntent pi=PendingIntent.getBroadcast(getApplicationContext(), 0, intent, 0);
//					am.set(AlarmManager.RTC_WAKEUP, System.currentTimeMillis()+5*1000, pi);
					am.set(AlarmManager.RTC_WAKEUP, c.getTimeInMillis(), pi);
					Toast.makeText(getApplicationContext(), "闹钟设置成功", Toast.LENGTH_SHORT).show();
					}
			}, c.get(Calendar.HOUR_OF_DAY),c.get(Calendar.MINUTE),true);
			break;

		default:
			break;
		}
		return dialog;
	}

}
