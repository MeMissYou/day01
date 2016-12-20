package com.example.function;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.sax.StartElementListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.surprise.Music_FengActivity;
import com.example.surprise.Music_PyinActivity;
import com.example.surprise.Music_YiActivity;
import com.example.surprise.Music_YinActivity;
import com.example.surprise.Music_ZhouActivity;
import com.example.surprise.R;
import com.example.surprise.Music_YingActivity;

public class MyListAdpater extends BaseAdapter {
	private List<MyBean> mList;
	private Context mContext;

	public MyListAdpater(List mList, Context mContext) {
		super();
		this.mList = mList;
		this.mContext = mContext;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mList.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mList.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = LayoutInflater.from(mContext).inflate(R.layout.listitem,
				parent, false);
		Holder holder = null;
		String color[] = { "#FBDDE5", "#E9BEC7", "#D9AAB4", "#D7A1B1",
				"#C08392", "#B16D7C" };
		if (convertView == null) {
			holder = new Holder();
			convertView = view;
			holder.imageview = (ImageView) view
					.findViewById(R.id.listItem_image);
			holder.button = (Button) view.findViewById(R.id.listItem_button);

			convertView.setTag(holder);
		} else {
			holder = (Holder) convertView.getTag();
		}
		holder.imageview.setBackgroundResource(mList.get(position)
				.getImage_id());
		holder.button.setBackgroundColor(Color.parseColor(color[position]));
		holder.button.setText(mList.get(position).getButton_text());
		holder.button.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				switch (position) {
				case 0:
                    Intent intent = new Intent(mContext,Music_ZhouActivity.class);
                     mContext.startActivity(intent);
					break;
				case 1:
					 Intent intent1 = new Intent(mContext, Music_YingActivity.class);
                     mContext.startActivity(intent1);
					break;
				case 2:
					Intent intent2 = new Intent(mContext, Music_YiActivity.class);
                    mContext.startActivity(intent2);
					break;
				case 3:
					Intent intent3 = new Intent(mContext, Music_PyinActivity.class);
                    mContext.startActivity(intent3);
					break;
				case 4:
					Intent intent4 = new Intent(mContext, Music_YinActivity.class);
                    mContext.startActivity(intent4);
					break;
				case 5:
					Intent intent5 = new Intent(mContext, Music_FengActivity.class);
                    mContext.startActivity(intent5);
					break;
					
				default:
					break;
				}

			}
		});
		return convertView;
	}

	class Holder {
		public ImageView imageview;
		public Button button;
	}

}
