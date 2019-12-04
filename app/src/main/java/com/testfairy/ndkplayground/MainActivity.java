package com.testfairy.ndkplayground;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends Activity {

	// Used to load the 'native-lib' library on application startup.
	static {
		System.loadLibrary("native-lib");
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		// Example of a call to a native method
		Button crashButton = findViewById(R.id.crash_button);
		crashButton.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				crashAndGetExceptionMessage();
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();

		initSignalHandler();
	}

	@Override
	protected void onPause() {
		super.onPause();

		deinitSignalHandler();
	}

	/**
	 * Initialize native signal handler to catch native crashes.
	 */
	public native void initSignalHandler();

	/**
	 * Deinitialzie native signal handler to leave native crashes alone.
	 */
	public native void deinitSignalHandler();

	/**
	 * A native method that is implemented by the 'native-lib' native library,
	 * which is packaged with this application. It will throw a C++ exception
	 * and catch it in the signal handler which will be visible in the logs.
	 */
	public native void crashAndGetExceptionMessage();
}
