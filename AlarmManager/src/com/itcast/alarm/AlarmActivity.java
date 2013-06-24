package com.itcast.alarm;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.os.Bundle;

import com.example.alarmmanager.R;

public class AlarmActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		new AlertDialog.Builder(AlarmActivity.this)
			.setTitle(R.string.alarmTite)
			.setMessage(R.string.alarmMsg)
			.setPositiveButton(R.string.alatmButton, new OnClickListener() {
				
				@Override
				public void onClick(DialogInterface dialog, int which) {
					AlarmActivity.this.finish();
				}
			}).create().show();
	}

}
