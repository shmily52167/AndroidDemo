package con.itcast.image;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import com.example.netimage.R;
import con.itcast.service.ImageService;
public class MainActivity extends Activity {
	private Button button;
	private EditText pathText;
	private ImageView imageView;
	private byte[] data;
      //android4.0时候提示 android.os.NetworkOnMainThreadException异常
      // 有两种解决方案
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		button = (Button) this.findViewById(R.id.button);
		pathText = (EditText) this.findViewById(R.id.imagepath);
		imageView = (ImageView) this.findViewById(R.id.imageview);
		button.setOnClickListener(new ButtonClickListener());
		/*StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
				.detectDiskReads().detectDiskWrites().detectNetwork()
				.penaltyLog().build());
		StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
				.detectLeakedSqlLiteObjects().detectLeakedClosableObjects()
				.penaltyLog().penaltyDeath().build());*/
		new Thread(downloadRun).start();
	}
	Runnable downloadRun=new Runnable() {
		@Override
		public void run() {
			try {
				String path = pathText.getText().toString();
				data = ImageService.getImage(path);
			} catch (Exception e) {
				e.printStackTrace();
				Toast.makeText(getApplicationContext(), R.string.error,Toast.LENGTH_SHORT).show();
			}
		}
	};
	private final class ButtonClickListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
				Bitmap bitmap = BitmapFactory.decodeByteArray(data, 0,data.length);
				imageView.setImageBitmap(bitmap);
		}
	}
}
