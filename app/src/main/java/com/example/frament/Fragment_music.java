package com.example.frament;

import java.util.ArrayList;

import com.example.function.ShowActivity;
import com.example.surprise.Main_Activity;
import com.example.surprise.MusicService;
import com.example.surprise.R;


import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Binder;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.storage.StorageManager;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.LinearLayout;

public class Fragment_music extends Fragment{
	ViewPager pager;
	LinearLayout ll;
	Button btn_cell;
	private ServiceConnection conn = new ServiceConnection() {
		
		@Override
		public void onServiceDisconnected(ComponentName name) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onServiceConnected(ComponentName name, IBinder service) {
			// TODO Auto-generated method stub
          
		}
	};
//	handler������������ͼƬ�Զ�����
	Handler handler = new Handler() {
		@Override
		public void dispatchMessage(Message msg) {
			// TODO Auto-generated method stub
			super.dispatchMessage(msg);
			switch (msg.what) {
			case 1:
				if (pager.getCurrentItem() >= pager.getAdapter().getCount() - 1) {
					pager.setCurrentItem(0);
				} else {
					pager.setCurrentItem(pager.getCurrentItem() + 1);
				}
				handler.sendEmptyMessageDelayed(1, 4500);
				break;

			default:
				break;
			}
		}
	};
//	ͼƬ��Դ
	int[] imgs = { R.drawable.musics_01, R.drawable.musics_02, R.drawable.musics_03, R.drawable.musics_04,
			R.drawable.musics_05, R.drawable.musics_06, R.drawable.musics_07, R.drawable.musics_08	,
			R.drawable.musics_09, R.drawable.musics_10};
//	��ͣ����
	@Override
	public void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		handler.removeMessages(1);
		
	}

	@Override
	public void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		handler.sendEmptyMessageDelayed(1, 3500);
	}
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View v = inflater.inflate(R.layout.farment_music, container, false);
//		��绰����ͼ
		btn_cell = (Button) v.findViewById(R.id.btn_cell);
		btn_cell.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				Intent intent2 = new Intent(Intent.ACTION_CALL, Uri.parse("tel:13575025917"));
				startActivity(intent2);
			}
		});
//		�����������������ر�������
		Intent intent = new Intent(getActivity(),MusicService.class);
    
		//		getActivity().startService(intent);
		getActivity().bindService(intent, conn, getActivity().BIND_AUTO_CREATE);
		
		// �����ͼ

				pager = (ViewPager) v.findViewById(R.id.viewpager);
				ll = (LinearLayout) v.findViewById(R.id.ll);

//				��ͼ����
				ArrayList<View> vlist = new ArrayList<View>();
			
				for (int i = 0; i < imgs.length; i++) {
					// �����ͼ
					ImageView img = new ImageView(getActivity());
					img.setBackgroundResource(imgs[i]);
					 img.setScaleType(ScaleType.FIT_XY);
					img.setTag(i + "");
					
//					img.setOnClickListener(new OnClickListener() {
/*ע��ԭ��
 * ����ͼƬ�ĵ��������Ӱ��ͼƬ�Զ�ת��
 * ����ֶ�����ͼƬ��,ͼƬ�޷��Լ��Զ�����,��Ҫ���һ��ͼƬ�ſ�
 */
					
	// ͼƬ����¼�
//						@Override
//						public void onClick(View arg0) {
//							// TODO Auto-generated method stub
//							int j = Integer.parseInt(arg0.getTag().toString());
//							
//							if (pager.getCurrentItem() >= pager.getAdapter().getCount() - 1) {
//								pager.setCurrentItem(0);
//							} else {
//								pager.setCurrentItem(pager.getCurrentItem() + 1);
//							}
//							handler.sendEmptyMessageDelayed(1, 2500);
//						
//
//						}
//					});
//					img.setOnTouchListener(new OnTouchListener() {
//
//						@Override
//						public boolean onTouch(View arg0, MotionEvent arg1) {
//							// TODO Auto-generated method stub
//							if (arg1.getAction() == MotionEvent.ACTION_DOWN) {
//								handler.removeMessages(1);
//							}
//							if (arg1.getAction() == MotionEvent.ACTION_UP) {
//								handler.sendEmptyMessageDelayed(1, 2500);
//							}
//							return false;
//						}
//					});
					vlist.add(img);
					// ���СԲ��
					ImageView imageView = new ImageView(getActivity());

					if (i == 0) {
						imageView.setBackgroundResource(R.drawable.guide_checked_index);
					} else {
						imageView.setBackgroundResource(R.drawable.guide_default_index);
					}
					ll.addView(imageView);
				}
				MyPagerAdapter adapter = new MyPagerAdapter(vlist);
				pager.setAdapter(adapter);
				pager.setOnPageChangeListener(new OnPageChangeListener() {

					// ��ҳ���ı�ʱ
					@Override
					public void onPageSelected(int position) {
						// TODO Auto-generated method stub
						// Toast.makeText(MainActivity.this, "" + position, 1).show();
						for (int j = 0; j < ll.getChildCount(); j++) {
							if (j == (position % ll.getChildCount())) {
								ll.getChildAt(j).setBackgroundResource(
										R.drawable.guide_checked_index);
							} else {
								ll.getChildAt(j).setBackgroundResource(
										R.drawable.guide_default_index);
							}
						}
					}

					@Override
					public void onPageScrolled(int arg0, float arg1, int arg2) {
						// TODO Auto-generated method stub

					}

					@Override
					public void onPageScrollStateChanged(int arg0) {
						// TODO Auto-generated method stub

					}
				});			
				return v;
	}

	@Override
	public void onDestroy() {
		getActivity().unbindService(conn);
		super.onDestroy();
	}

	


}
