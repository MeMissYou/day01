package com.example.function;
import android.app.Activity;
import android.content.Context;
import android.os.Bundle;

public class BaseActivity extends Activity{
	Context context;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		context=this;
	}

}