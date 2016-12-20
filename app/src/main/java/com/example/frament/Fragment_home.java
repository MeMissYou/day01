package com.example.frament;

import java.util.ArrayList;
import com.example.surprise.R;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;

public class Fragment_home extends Fragment{
//	新建一fragment数组 定义fragment个数
	ArrayList<Fragment> flist;
//	初始化viewpager
	private ViewPager viewpager;
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
//		获得主布局
		View v = inflater.inflate(R.layout.frament_home, container, false);
// 添加fragment布局
		flist = new ArrayList<Fragment>();
		viewpager = (ViewPager) v.findViewById(R.id.home_fragment_ll_viewpager);

		Home_one one = new Home_one();
		Home_two two =  new Home_two();
		Home_three three = new Home_three();
		Home_four four = new Home_four();
		flist.add(one);
		flist.add(two);
		flist.add(three);
		flist.add(four);
		
		PagerAdapter adapter = new PagerAdapter(getChildFragmentManager(),
				flist);
		viewpager.setAdapter(adapter);
		return v;


	}
}
