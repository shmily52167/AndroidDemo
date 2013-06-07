package com.wyf.jc;

import com.example.contextmenu.R;

import android.R.mipmap;
import android.app.Activity;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {
	final int MENU1=1;
	final int MENU2=2;
	final int MENU3=3;
	final int MENU4=4;
	final int MENU5=5;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		this.registerForContextMenu(findViewById(R.id.EditText01));//注册上下文菜单
		this.registerForContextMenu(findViewById(R.id.TextView01));
	}

	@Override
	public boolean onContextItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case MENU1:
		case MENU2:
		case MENU3:
			EditText et1=(EditText) this.findViewById(R.id.EditText01);
			et1.append("\n"+item.getTitle()+"被按下");
			break;
		case MENU4:
		case MENU5:
			TextView tv=(TextView) this.findViewById(R.id.TextView01);
			tv.setText(item.getTitle()+"备案自爱");
			break;
		}
		return true;
	}

	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
			if (v==findViewById(R.id.EditText01)) {
				menu.add(0, MENU1, 0, R.string.mi1);
				menu.add(0, MENU2, 0, R.string.mi2);
				menu.add(0, MENU3, 0, R.string.mi3);
				
			}else if (v==findViewById(R.id.TextView01)) {
				menu.add(0, MENU4, 0, R.string.mi4);
				menu.add(0, MENU5, 0, R.string.mi5);
			}
		
	}
	

}
