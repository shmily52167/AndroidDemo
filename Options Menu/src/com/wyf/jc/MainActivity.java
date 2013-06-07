package com.wyf.jc;

import com.example.optionsmenu.R;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.MenuItem.OnMenuItemClickListener;
import android.view.SubMenu;
import android.widget.EditText;

public class MainActivity extends Activity {
	final int MENU_GENDER_MALE=0;
	final int MENU_GENDER_FEMALE=1;
	final int MENU_HOBBY1=2;
	final int MENU_HOBBY2=3;
	final int MENU_HOBBY3=4;
	final int MENU_OK=5;
	final int MENU_GENDER=6;
	final int MENU_HOBBY=7;
	final int MENU_HOBBY01=8;
	final int MENU_HOBBY02=9;
	final int MENU_HOBBY03=10;
	final int MENU_HOBBY04=11;
	final int GENDER_GROUP=0;
	final int HOBBY_GROUP=1;
	final int MAIN_GROUP=2;
	MenuItem[] miaHobby=new MenuItem[3];
	MenuItem male=null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
	}
	public void appendStateStr(){
		String result="您选择的性别为:";
		if (male.isChecked()) {
			result=result+"男";
		}else {
			result=result+"女";
		}
		String hobbyStr="";
		for (MenuItem mi : miaHobby) {
			if (mi.isChecked()) {
				hobbyStr=hobbyStr+mi.getTitle()+"、";
			}
		}
		if (hobbyStr.length()>0) {
			result=result+",您喜欢的书籍为:"+hobbyStr.substring(0,hobbyStr.length()-1)+"。\n";
			
		}else {
			result=result+"。\n";
		}
		EditText et=(EditText) this.findViewById(R.id.EditText01);
		et.append(result);
	}
	@Override 
	public boolean onCreateOptionsMenu(Menu menu) {
		SubMenu subMenuGender=menu.addSubMenu(MAIN_GROUP,MENU_GENDER,0,R.string.gender);
		subMenuGender.setIcon(R.drawable.ic_launcher);
		male=subMenuGender.add(GENDER_GROUP, MENU_GENDER_MALE, 0, R.string.male);
		male.setChecked(true);
		subMenuGender.add(GENDER_GROUP, MENU_GENDER_FEMALE, 0, R.string.female);
		subMenuGender.setGroupCheckable(GENDER_GROUP, true, true);//设置两个选项互斥
		SubMenu subMenuHobby=menu.addSubMenu(MAIN_GROUP,MENU_HOBBY,0,R.string.hobby);
		subMenuHobby.setIcon(R.drawable.ic_launcher);
		miaHobby[0]=subMenuHobby.add(HOBBY_GROUP, MENU_HOBBY1, 0, R.string.hobby1);
		miaHobby[1]=subMenuHobby.add(HOBBY_GROUP, MENU_HOBBY2, 0, R.string.hobby2);
		miaHobby[2]=subMenuHobby.add(HOBBY_GROUP, MENU_HOBBY3, 0, R.string.hobby3);
		miaHobby[0].setCheckable(true);
		miaHobby[1].setCheckable(true);
		miaHobby[2].setCheckable(true);
		MenuItem ok=menu.add(GENDER_GROUP+2, MENU_OK, 0, R.string.ok);
		OnMenuItemClickListener lsn=new OnMenuItemClickListener() {
			
			@Override
			public boolean onMenuItemClick(MenuItem item) {//更新信息
				appendStateStr();
				return true;
			}
		};
		ok.setOnMenuItemClickListener(lsn);
		ok.setAlphabeticShortcut('o');//设置快捷键
		//ok.setNumericShortcut('1');//数字快捷键
		//ok.setShortcut('a','2');//设置两中快捷键
		SubMenu subMenu01=menu.addSubMenu(MAIN_GROUP, MENU_HOBBY01, 0, null);
		subMenu01.setIcon(R.drawable.ic_launcher);
		
		return true;
	}
	@Override
	public boolean onOptionsItemSelected(MenuItem mi) {
		switch (mi.getItemId()) {
		case MENU_GENDER_MALE:
		case MENU_GENDER_FEMALE:
			mi.setChecked(true);
			appendStateStr();
			break;

		case MENU_HOBBY1:
		case MENU_HOBBY2:
		case MENU_HOBBY3:
		  mi.setChecked(!mi.isChecked());
		  appendStateStr();
		}
		return true;
	}
	

}
