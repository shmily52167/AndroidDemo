package com.itcast.html;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.htmlviewer.R;
import com.itcast.service.PageService;

public class MainActivity extends Activity {
	private EditText pathText;
	private Button button;
	private TextView codeView;
	private String html;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		pathText = (EditText) this.findViewById(R.id.pagepath);
		button = (Button) this.findViewById(R.id.button);
		codeView = (TextView) this.findViewById(R.id.codeView);
		button.setOnClickListener(new ButtonClickListener());
		new Thread(downloadRun).start();
	}
	Runnable downloadRun = new Runnable() {

		@Override
		public void run() {
			try {
				String path = pathText.getText().toString();
				html = PageService.getHtml(path);
			} catch (Exception e) {
				e.printStackTrace();
				Toast.makeText(getApplicationContext(), R.string.error,
						Toast.LENGTH_SHORT).show();
			}
		}
	};

	private final class ButtonClickListener implements OnClickListener {
		@Override
		public void onClick(View v) {
			codeView.setText(html);
		}

	}
}
