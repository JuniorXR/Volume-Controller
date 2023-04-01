package com.junior.MyVolume;

import android.app.Activity;
import android.content.Context;
import android.media.AudioManager;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.SeekBar;
import android.widget.TextView;

public class MainActivity extends Activity {
	SeekBar seekbar;
	TextView textView;
	AudioManager audioManager;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		audioManager = (AudioManager) getSystemService(Context.AUDIO_SERVICE);

		textView = findViewById(R.id.val);
		seekbar = findViewById(R.id.layout2SeekBar1);

		seekbar.setMax(getMaxVolume());
		seekbar.setMin(getMinVolume());

		textView.setText(textView.getText().toString() + " " + getCurrentVolume() + " of " + getMaxVolume());
		seekbar.setProgress(getCurrentVolume());

		//mainWork!!!
		seekbar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar arg0, int arg1, boolean arg2) {

				textView.setText("VOLUME : " + arg1 + " of " + getMaxVolume());
				audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, arg1, AudioManager.ADJUST_SAME);
				audioManager.adjustVolume(AudioManager.ADJUST_SAME, AudioManager.STREAM_MUSIC);
				seekbar.setProgress(getCurrentVolume());
			}

			@Override
			public void onStartTrackingTouch(SeekBar arg0) {
			}

			@Override
			public void onStopTrackingTouch(SeekBar arg0) {
			}

		});

	}

	@Override
	public boolean dispatchKeyEvent(KeyEvent arg0) {

		if (arg0.getKeyCode() == KeyEvent.KEYCODE_VOLUME_UP && arg0.getAction() == KeyEvent.ACTION_UP) {
			/*seekbar.setProgress(seekbar.getProgress() + 1);
			audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, seekbar.getProgress(), AudioManager.ADJUST_SAME);
			audioManager.adjustVolume(seekbar.getProgress(), AudioManager.STREAM_MUSIC);*/
			upVolume();
		}
		if (arg0.getKeyCode() == KeyEvent.KEYCODE_VOLUME_DOWN && arg0.getAction() == KeyEvent.ACTION_UP) {
			seekbar.setProgress(seekbar.getProgress() - 1);
			audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, seekbar.getProgress(), AudioManager.ADJUST_LOWER);
			audioManager.adjustVolume(seekbar.getProgress(), AudioManager.STREAM_MUSIC);
		}
		return super.dispatchKeyEvent(arg0);
	}

	/*	CUSTOM FUNCTION SECTION	*/

	public void upVolume() {
		seekbar.setProgress(seekbar.getProgress() + 1);
		audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, seekbar.getProgress(), AudioManager.ADJUST_SAME);
		audioManager.adjustVolume(seekbar.getProgress(), AudioManager.STREAM_MUSIC);
	}

	private int getCurrentVolume() {
		return audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
	}

	private int getMaxVolume() {
		return audioManager.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
	}

	private int getMinVolume() {
		return audioManager.getStreamMinVolume(AudioManager.STREAM_MUSIC);
	}
	/*	CUSTOM FUNCTION SECTION END's	*/
}