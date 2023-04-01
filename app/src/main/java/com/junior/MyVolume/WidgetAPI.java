package com.junior.MyVolume;

import android.app.PendingIntent;
import android.content.Intent;
import android.appwidget.AppWidgetManager;
import android.content.Context;

import android.appwidget.AppWidgetProvider;
import android.media.AudioManager;
import android.os.IBinder;
import android.widget.Toast;

public class WidgetAPI extends AppWidgetProvider {

	@Override
	public void onEnabled(Context context) {
		super.onEnabled(context);
	}

	@Override
	public void onDisabled(Context context) {
		super.onDisabled(context);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager, int[] appWidgetIds) {
		super.onUpdate(context, appWidgetManager, appWidgetIds);
		for (int i = 0; i < appWidgetIds.length; i++) {
			int appWidgetId = appWidgetIds[i];

			//Create an Intent to launch ExampleActivity
			Intent intent = new Intent(context, WidgetAPI.class);

			PendingIntent pendingIntent = PendingIntent.getActivity(context, 0, intent,
					PendingIntent.FLAG_UPDATE_CURRENT | PendingIntent.FLAG_IMMUTABLE);

			context.startActivity(intent);

			//Toast.makeText(context,"Clicked",Toast.LENGTH_SHORT).show();		
		}
		AudioManager audioManager = (AudioManager) context.getSystemService(Context.AUDIO_SERVICE);
		int count = audioManager.getStreamVolume(AudioManager.STREAM_MUSIC);
		audioManager.setStreamVolume(AudioManager.STREAM_MUSIC, count, AudioManager.ADJUST_SAME);
		audioManager.adjustVolume(AudioManager.ADJUST_SAME, AudioManager.STREAM_MUSIC);
	}

	@Override
	public IBinder peekService(Context myContext, Intent service) {
		Toast.makeText(myContext, service.getAction().toString(), Toast.LENGTH_SHORT).show();
		return super.peekService(myContext, service);
	}

	@Override
	public void onReceive(Context context, Intent intent) {
		super.onReceive(context, intent);
	}

}