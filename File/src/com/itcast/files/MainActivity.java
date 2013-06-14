package com.itcast.files;

import android.app.Activity;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.file.R;
import com.itcast.service.FileService;

public class MainActivity extends Activity {
	private Button button;
	private EditText filenameText;
	private EditText contentText;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
	
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		button=(Button) this.findViewById(R.id.button);
		button.setOnClickListener(new ButtonClickListener());
	}
	private class ButtonClickListener implements View.OnClickListener{

		@Override
		public void onClick(View v) {
			filenameText=(EditText) findViewById(R.id.filename);
			contentText=(EditText) findViewById(R.id.filecontent);
			String finename=filenameText.getText().toString();
			String content=contentText.getText().toString();
			FileService service=new FileService(getApplicationContext());
			try {
				//判断SCDard是否存在,并且可以读写	
				if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
					service.saveToSDCard(finename, content);
					Toast.makeText(getApplicationContext(), R.string.success, Toast.LENGTH_SHORT).show();
				}else {
					Toast.makeText(getApplicationContext(), R.string.sdcarderror, Toast.LENGTH_SHORT).show();
				}
				/*service.saveAppend(finename,content);*/
			} catch (Exception e) {
				Toast.makeText(getApplicationContext(), R.string.fail, Toast.LENGTH_SHORT).show();
				e.printStackTrace();
			}
		
		}
		
	}

}
