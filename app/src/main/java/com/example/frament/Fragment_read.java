package com.example.frament;

import java.util.ArrayList;
import java.util.List;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.example.function.MyBean;
import com.example.function.MyListAdpater;
import com.example.surprise.R;

public class Fragment_read extends Fragment {
	private ListView listview;
	private List<MyBean> list;
	private MyBean bean;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
//		获得list的样式
		list = new ArrayList<MyBean>();
//		自定义list的图片和文字？
		int[] image_id = { R.drawable.pic1, R.drawable.pic2, R.drawable.pic3,
				R.drawable.pic4, R.drawable.pic5, R.drawable.pic7 };
		String [] btn_text ={"生","日","快","乐","阿","梨"};
		for (int i = 0; i < image_id.length; i++) {
			bean = new MyBean();
			bean.setImage_id(image_id[i]);
			bean.setButton_text(btn_text[i]);
			list.add(bean);
		}
//		加载视图
		View v = inflater.inflate(R.layout.farment_read, container, false);
		listview = (ListView) v.findViewById(R.id.listview);		
		MyListAdpater adapter = new MyListAdpater(list, getActivity());
		listview.setAdapter(adapter);
		return v;
	}
}
