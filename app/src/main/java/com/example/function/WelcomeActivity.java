package com.example.function;

import com.example.surprise.MainActivity;
import com.example.surprise.R;
import com.example.surprise.R.layout;
import com.example.surprise.R.menu;

import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.content.Intent;
import android.support.v4.view.ViewPager;
import android.telephony.SmsManager;
import android.view.Menu;

public class WelcomeActivity extends Activity {
	Handler handler = new Handler(){
		
				public void dispatchMessage(Message msg) {
					// TODO Auto-generated method stub
					super.dispatchMessage(msg);
					switch (msg.what) {
					case 1:					
						Intent intent=new Intent(WelcomeActivity.this,MainActivity.class);
						startActivity(intent);
						finish();						
						break;
					}		
		};
	};
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_welcome);
		handler.sendEmptyMessageDelayed(1, 3500);
	}



}
