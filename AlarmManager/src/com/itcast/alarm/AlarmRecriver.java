package com.itcast.alarm;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

public class AlarmRecriver extends BroadcastReceiver {

	@Override
	public void onReceive(Context context, Intent intent) {
		Intent i=new Intent(context,AlarmActivity.class);
		i.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);//设置标志位
		context.startActivity(i);
	}

}
