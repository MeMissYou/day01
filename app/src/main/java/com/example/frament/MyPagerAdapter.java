package com.example.frament;

import java.util.ArrayList;

import android.support.v4.view.PagerAdapter;
import android.view.View;
import android.view.ViewGroup;

public class MyPagerAdapter extends PagerAdapter{
	private ArrayList<View> vlist;

	public MyPagerAdapter(ArrayList<View> vlist) {
		this.vlist = vlist;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return Integer.MAX_VALUE;
	}

	@Override
	public boolean isViewFromObject(View arg0, Object arg1) {
		// TODO Auto-generated method stub
		return arg0 == arg1;
	}

	// 装载新视图
	@Override
	public Object instantiateItem(ViewGroup container, int position) {
		// TODO Auto-generated method stub
		System.out.println("instantiateItem"+position);
		container.addView(vlist.get(position%vlist.size()));
		return vlist.get(position%vlist.size());
	}
	// 销毁视图
	@Override
	public void destroyItem(ViewGroup container, int position, Object object) {
		// TODO Auto-generated method stub
		System.out.println("destroyItem"+position);
		container.removeView(vlist.get(position%vlist.size()));
	}

}
