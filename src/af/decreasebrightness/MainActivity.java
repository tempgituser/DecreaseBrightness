package af.decreasebrightness;

import java.io.DataOutputStream;
import java.io.OutputStream;

import android.app.Activity;
import android.os.Bundle;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_main);
		execShellCmd("echo 1 > /sys/class/leds/lcd-backlight/brightness", true);
		this.finish();
		System.exit(0);
	}

	private void execShellCmd(String cmd, boolean isRoot) {
		String root = isRoot ? "su" : "";
		try {
			Process process = Runtime.getRuntime().exec(root);
			OutputStream outputStream = process.getOutputStream();
			DataOutputStream dataOutputStream = new DataOutputStream(outputStream);
			dataOutputStream.writeBytes(cmd);
			dataOutputStream.flush();
			dataOutputStream.close();
			outputStream.close();
		} catch (Throwable t) {
			t.printStackTrace();
		}
	}
}
