package com.example.frament;

import java.util.ArrayList;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {


	private ArrayList<Fragment> flist;

	public PagerAdapter(FragmentManager fm, ArrayList<Fragment> flist) {
		super(fm);
		this.flist = flist;
		// TODO Auto-generated constructor stub
	}

	@Override
	public Fragment getItem(int arg0) {
		// TODO Auto-generated method stub
		return flist.get(arg0);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return flist.size();
	}

}
