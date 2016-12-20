package com.example.surprise;


import com.example.frament.Fragment_home;
import com.example.frament.Fragment_read;
import com.example.frament.Fragment_music;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTabHost;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TabHost.TabSpec;


public class Main_Activity extends FragmentActivity {
	/**
	 * FragmentTabhost
	 */
	private FragmentTabHost mTabHost;

	/**
	 * ���������
	 * 
	 */
	private LayoutInflater mLayoutInflater;

	/**
	 * Fragment�������
	 * 
	 */
	private Class mFragmentArray[] = { Fragment_home.class, Fragment_read.class,Fragment_music.class,
			};
	/**
	 * ���ͼƬ����
	 * 
	 */
	private int mImageArray[] = { R.drawable.tab_home_btn, R.drawable.tab_music_btn,R.drawable.tab_read_btn,
			  };

	/**
	 * ѡ�޿�����
	 * 
	 */
	private String mTextArray[] = { "��ҳ", "����", "�Ķ�" };
	/**
	 * 
	 * 
	 */
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_activity);
	
		initView();
	}
	private void initView() {
		mLayoutInflater = LayoutInflater.from(this);

		// �ҵ�TabHost
		mTabHost = (FragmentTabHost) findViewById(android.R.id.tabhost);
		mTabHost.setup(this, getSupportFragmentManager(), R.id.realtabcontent);
		// �õ�fragment�ĸ���
		int count = mFragmentArray.length;
		for (int i = 0; i < count; i++) {
			// ��ÿ��Tab��ť����ͼ�ꡢ���ֺ�����
			TabSpec tabSpec = mTabHost.newTabSpec(mTextArray[i])
					.setIndicator(getTabItemView(i));
			// ��Tab��ť��ӽ�Tabѡ���
			mTabHost.addTab(tabSpec, mFragmentArray[i], null);
			// ����Tab��ť�ı���
			mTabHost.getTabWidget().getChildAt(i)
					.setBackgroundResource(R.drawable.selector_tab_background);
	
		}
	}
	private View getTabItemView(int index) {
		// TODO Auto-generated method stub
		View view = mLayoutInflater.inflate(R.layout.tab_item_view, null);
		ImageView imageView = (ImageView) view.findViewById(R.id.imageview);
		imageView.setImageResource(mImageArray[index]);
		TextView textView = (TextView) view.findViewById(R.id.textview);
		textView.setText(mTextArray[index]);
		return view;
		
	}

}
