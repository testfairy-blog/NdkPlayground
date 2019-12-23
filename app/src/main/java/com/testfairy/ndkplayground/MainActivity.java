package com.testfairy.ndkplayground;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.testfairy.TestFairy;

public class MainActivity extends Activity {

	// Used to load the 'native-lib' library on application startup.
	static {
		System.loadLibrary("native-lib");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// TODO : set this before running the app
		TestFairy.begin(this, "");

		// Example of a call to a native method
		Button crashButton = findViewById(R.id.crash_button);
		crashButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				crashNatively();
			}
		});
	}

	/**
	 * A native method that is implemented by the 'native-lib' native library,
	 * which is packaged with this application. It will throw a C++ exception
	 * and catch it in the signal handler which will be visible in the logs.
	 */
	public native void crashNatively();
}
