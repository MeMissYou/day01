package com.example.surprise;
import com.example.surprise.R;

import android.app.Service;
import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.IBinder;


public class MusicService extends Service {
	MediaPlayer mplayer;
	@Override
	public IBinder onBind(Intent intent) {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public void onCreate() {
		// TODO Auto-generated method stub
		super.onCreate();
		mplayer =  MediaPlayer.create(this, R.raw.a);
		mplayer.start();
		
	}
	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		 // ��activity������ʱ�������Դ       
		if (mplayer != null && mplayer.isPlaying()) {
			mplayer.stop();
			mplayer.release();
			mplayer = null;

        }
		super.onDestroy();
	};

}
