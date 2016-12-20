package com.example.function;
import java.io.IOException;
import java.sql.Date;

import com.example.surprise.MainActivity;
import com.example.surprise.Main_Activity;
import com.example.surprise.R;
import com.example.surprise.R.layout;
import com.example.surprise.R.menu;

import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.animation.AnimatorInflater;
import android.animation.ArgbEvaluator;
import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.VideoView;

public class ShowActivity extends Activity {
	MediaPlayer mplayer,vplayer;
	VideoView videoview;
	 AnimationDrawable anim;
	Handler handler = new Handler(){
		
		public void dispatchMessage(Message msg) {
			// TODO Auto-generated method stub
			super.dispatchMessage(msg);
			switch (msg.what) {
			case 1:	
				send();				
				break;
			}		
};
	};

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_show);
//	        ≤•∑≈“Ù¿÷
		ImageView imageview =(ImageView) findViewById(R.id.anim); 
		imageview.setBackgroundResource(R.drawable.fat_po);
         anim = (AnimationDrawable) imageview.getBackground();
         anim.start();
		 
		 
         mplayer = MediaPlayer.create(this,R.raw.music);                   
         mplayer.start();
//     	—”≥Ÿ ±º‰
         handler.sendEmptyMessageDelayed(1, 16000);
	}

	public void send(){
		mplayer.stop();	
		anim.stop();
		Intent intent=new Intent(ShowActivity.this,Main_Activity.class);
		startActivity(intent);
		finish();	
	}



}
