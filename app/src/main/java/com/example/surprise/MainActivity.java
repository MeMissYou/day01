package com.example.surprise;



import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.os.Bundle;
import android.view.GestureDetector;
import android.view.GestureDetector.OnGestureListener;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.TranslateAnimation;
import android.widget.GridLayout;
import android.widget.ImageView;

import com.example.function.ShowActivity;
import com.example.function.WelcomeActivity;

public class MainActivity extends Activity {
	private static final android.content.DialogInterface.OnClickListener intent = null;

	/**
	 * ���ö�ά���鴴�����ɸ���ϷС����
	 */
	private ImageView[][] iv_array = new ImageView[5][4];

	private GridLayout gridLayout;

	// ��ǰ�շ����ʵ���ı���
	private ImageView iv_null_imageView;

	// ��ǰ����
	private GestureDetector mDetector;

	// �ж���Ϸ�Ƿ�ʼ
	private boolean isGameStart = false;

	@Override
	public boolean onTouchEvent(MotionEvent event) {
		// TODO Auto-generated method stub
		return mDetector.onTouchEvent(event);
	}

	@SuppressWarnings("deprecation")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.activity_main);
        mDetector = new GestureDetector(this, new OnGestureListener() {

			@Override
			public boolean onSingleTapUp(MotionEvent e) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void onShowPress(MotionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public boolean onScroll(MotionEvent e1, MotionEvent e2,
					float distanceX, float distanceY) {
				// TODO Auto-generated method stub
				return false;
			}

			@Override
			public void onLongPress(MotionEvent e) {
				// TODO Auto-generated method stub

			}

			@Override
			public boolean onFling(MotionEvent e1, MotionEvent e2,
					float velocityX, float velocityY) {
				int type = getDirByGes(e1.getX(), e1.getY(), e2.getX(),
						e2.getY());
				changeByDir(type);
				return false;
			}

			@Override
			public boolean onDown(MotionEvent e) {
				// TODO Auto-generated method stub
				return false;
			}
		});

		// WindowManager manager = this.getWindowManager();
		// manager.getDefaultDisplay().getWidth();
		// WindowManager manager = (WindowManager) this
		// .getSystemService(Context.WINDOW_SERVICE);
		// int width2 = manager.getDefaultDisplay().getWidth();
        //��ȡƴͼ��ͼƬ
		Bitmap initBitmap = ((BitmapDrawable) getResources().getDrawable(
				R.drawable.aili)).getBitmap();
//		�и�ͼƬ4*5
		int width = initBitmap.getWidth() / 4;
		int height = initBitmap.getHeight() / 5;

		/**
		 * ��ʼ����Ϸ�����ɸ�С���� iv_array.length��ʾ��ά����ĳ��ȣ�����ά������������˴�Ϊ5
		 * iv_array[0].length��ʾ��ά�����һ�еĳ��ȣ�����ά������������˴�Ϊ3
		 */
		for (int i = 0; i < iv_array.length; i++) {
			for (int j = 0; j < iv_array[0].length; j++) {

				Bitmap bm = Bitmap.createBitmap(initBitmap, j * width, i
						* height, width, height);// �����к������г����ɸ���ϷСͼƬ

				iv_array[i][j] = new ImageView(this);// ʵ����ÿһ��С����
				iv_array[i][j].setImageBitmap(bm);// Ϊÿ��С����������Ҫ��ʾͼƬ
				iv_array[i][j].setPadding(1, 1, 1, 1);
				iv_array[i][j].setTag(new GameData(i, j, bm));// ���ﴫ���xyֵ��i��j������ʾ����շ����λ��Ϊ[2][2]�������ǰ����ķ����ڿշ����ϱߣ��ǵ�ǰ����ķ����λ��Ϊ[1][2];
				iv_array[i][j].setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						boolean flag = isHasByNullImageView((ImageView) v);
						if (flag) {
							changeDataByImageView((ImageView) v);
						}

					}
				});
//				ͼƬ�ڲ��Ļ���
//				iv_array[i][j].setOnTouchListener(new OnTouchListener() {
//					float start_x = 0, start_y = 0, end_x = 0, end_y = 0;
//
//					@Override
//					public boolean onTouch(View v, MotionEvent event) {
//						switch (event.getAction()) {
//						case MotionEvent.ACTION_DOWN:
//							start_x = event.getX();
//							start_y = event.getY();
//							break;
//						case MotionEvent.ACTION_UP:
//							end_x = event.getX();
//							end_y = event.getY();
//							boolean flag = isHasByNullImageView((ImageView) v);
//							if (flag) {
//								if (Math.abs(end_x - start_x) > 100
//										|| Math.abs(end_y - start_y) > 100) {// �˴����ܽ���ʹ�ô���0������0ͬ���������У����ǿ��ܻ�͵���¼��ظ������bug
//									int dirByGes = getDirByGes(start_x,
//											start_y, end_x, end_y);
//									changeByDir(dirByGes);
//								} else {
//									break;
//								}
//							}
//
//							break;
//						default:
//							break;
//						}
//
//						return false;
//					}
//				});
			}

		}

		/**
		 * ��ʼ����Ϸ���棬��������ɸ�С����
		 */
		gridLayout = (GridLayout) findViewById(R.id.gridlayout);

		for (int i = 0; i < iv_array.length; i++) {
			for (int j = 0; j < iv_array[0].length; j++) {
				gridLayout.addView(iv_array[i][j]);
			}
		}

		setGridItemNull(iv_array[4][3]);
		randomMove();
		isGameStart = true;
	}

	public void changeDataByImageView(ImageView mImageView) {
		changeDataByImageView(mImageView, true);
	}

	// ���ö�������֮�󣬽����������������
	public void changeDataByImageView(final ImageView mImageView, boolean isAnim) {

//		if (isGameStart) {
//			isGameOver();// ������Ϸ��������
//		}
		if (!isAnim) {
			GameData mGameData = (GameData) mImageView.getTag();
			iv_null_imageView.setImageBitmap(mGameData.bm);

			GameData mNullGameData = (GameData) iv_null_imageView.getTag();
			mNullGameData.bm = mGameData.bm;
			mNullGameData.p_x = mGameData.p_x;
			mNullGameData.p_y = mGameData.p_y;

			setGridItemNull(mImageView);

			if (isGameStart) {
				isGameOver();// ������Ϸ��������
			}

			return;
		}

		// ����һ�����������ú÷����ƶ��ľ���
		TranslateAnimation translateAnimation = null;
		if (mImageView.getX() > iv_null_imageView.getX()) {

			translateAnimation = new TranslateAnimation(0.2f,
					-mImageView.getHeight(), 0.2f, 0.2f);
		} else if (mImageView.getX() < iv_null_imageView.getX()) {
			translateAnimation = new TranslateAnimation(0.2f,
					mImageView.getHeight(), 0.2f, 0.2f);
		} else if (mImageView.getY() > iv_null_imageView.getY()) {
			translateAnimation = new TranslateAnimation(0.2f, 0.2f, 0.2f,
					-mImageView.getWidth());
		} else if (mImageView.getY() < iv_null_imageView.getY()) {
			translateAnimation = new TranslateAnimation(0.2f, 0.2f, 0.2f,
					mImageView.getWidth());
		}
		// ���ö�����ʱ��
		translateAnimation.setDuration(70);
		// ���ö�������֮���Ƿ�ͣ��
		translateAnimation.setFillAfter(true);
		// ���ö�������֮������ݽ���
		translateAnimation.setAnimationListener(new AnimationListener() {

			@Override
			public void onAnimationStart(Animation animation) {

			}

			@Override
			public void onAnimationRepeat(Animation animation) {

			}

			@Override
			public void onAnimationEnd(Animation animation) {
				mImageView.clearAnimation();
				GameData mGameData = (GameData) mImageView.getTag();
				iv_null_imageView.setImageBitmap(mGameData.bm);

				GameData mNullGameData = (GameData) iv_null_imageView.getTag();
				mNullGameData.bm = mGameData.bm;
				mNullGameData.p_x = mGameData.p_x;
				mNullGameData.p_y = mGameData.p_y;

				setGridItemNull(mImageView);

				if (isGameStart) {
					isGameOver();// ������Ϸ��������
				}
			}
		});
		// ִ�ж���
		mImageView.startAnimation(translateAnimation);
	}

	/**
	 * ����Ҫ��ʾ�Ŀշ���
	 * 
	 * @param imageview
	 *            ��ǰ��Ҫ����Ϊ�շ����imageviewʵ��
	 */
	private void setGridItemNull(ImageView imageview) {
		imageview.setImageBitmap(null);
		iv_null_imageView = imageview;
	}

	/**
	 * �жϵ�ǰ����ķ��飬�Ƿ���շ����λ������
	 * 
	 * @param mImageView��ʾ��ǰ������ķ���
	 * @return true������ false��������
	 */
	public boolean isHasByNullImageView(final ImageView mImageView) {
		// �ֱ��ȡ��ǰ�շ����λ�����������λ�ã�ͨ��x y��ֵ��1�ķ�ʽ�жϣ�
		GameData mNullGameData = (GameData) iv_null_imageView.getTag();
		GameData mGameData = (GameData) mImageView.getTag();
		if (mNullGameData.y == mGameData.y
				&& mGameData.x + 1 == mNullGameData.x) {
			// ��ǰ����ķ����ڿշ�����ϱ�
			// Toast.makeText(MainActivity.this, "��ǰ����ķ���λ�ڿշ�����ϱ�",
			// Toast.LENGTH_SHORT).show();
			return true;
		} else if (mNullGameData.y == mGameData.y
				&& mGameData.x - 1 == mNullGameData.x) {
			// ��ǰ����ķ����ڿշ�����±�
			// Toast.makeText(MainActivity.this, "��ǰ����ķ���λ�ڿշ�����±�",
			// Toast.LENGTH_SHORT).show();
			return true;
		} else if (mNullGameData.y == mGameData.y + 1
				&& mGameData.x == mNullGameData.x) {
			// ��ǰ����ķ����ڿշ�������
			// Toast.makeText(MainActivity.this, "��ǰ����ķ���λ�ڿշ�������",
			// Toast.LENGTH_SHORT).show();
			return true;
		} else if (mNullGameData.y == mGameData.y - 1
				&& mGameData.x == mNullGameData.x) {
			// ��ǰ����ķ����ڿշ�����ұ�
			// Toast.makeText(MainActivity.this, "��ǰ����ķ���λ�ڿշ�����ұ�",
			// Toast.LENGTH_SHORT).show();
			return true;
		}
		return false;
	}

	/**
	 * �����жϣ��������һ����������»���
	 * 
	 * @param start_x���Ƶ����x
	 * @param start_y���Ƶ����y
	 * @param end_X���Ƶ��յ�x
	 * @param end_y���Ƶ��յ�y
	 * @return
	 * 
	 */
	public int getDirByGes(float start_x, float start_y, float end_X,
			float end_y) {
		boolean isLeftOrRight = (Math.abs(start_x - end_X) > Math.abs(start_y
				- end_y)) ? true : false;// �Ƿ�������
		if (isLeftOrRight) {// ���һ���
			boolean isLeft = start_x - end_X > 0 ? true : false;
			if (isLeft) {
				return 3;// ���󻬶�
			} else {
				return 4; // ���һ���
			}
		} else {// ���»���
			boolean isUp = start_y - end_y > 0 ? true : false;
			if (isUp) {
				return 1;// ���ϻ���
			} else {
				return 2;// ���»���
			}
		}
	}

	public void changeByDir(int type) {
		changeByDir(type, true);
	}

	public void changeByDir(int type, boolean isAnim) {
		// ��ȡ��ǰ�շ����λ��
		GameData mNullGameData = (GameData) iv_null_imageView.getTag();
		// ���ݷ���������Ӧ������λ�õ�����
		int new_x = mNullGameData.x;
		int new_y = mNullGameData.y;
		if (type == 1) {
			// Ҫ�ƶ��ķ����ڵ�ǰ�շ�����±�
			new_x++;
		} else if (type == 2) {
			new_x--;
		} else if (type == 3) {
			new_y++;
		} else if (type == 4) {
			new_y--;
		}
		// �ж�����������Ƿ����
		if (new_x >= 0 && new_x < iv_array.length && new_y >= 0
				&& new_y < iv_array[0].length) {
			if (isAnim) {
				// ���ڵĻ�����ʼ�ƶ�
				changeDataByImageView(iv_array[new_x][new_y]);
			} else {
				changeDataByImageView(iv_array[new_x][new_y], isAnim);
			}

		} else {
			// ʲôҲ����
		}
		// ���ڵĻ�����ʼ�ƶ�
	}

	/**
	 * ����ƴͼ����ʹ���������˳��
	 */
//	������Ϸ�Ѷ�i
	public void randomMove() {
		for (int i = 0; i < 15; i++) {
			int type = (int) ((Math.random() * 4) + 1);
			changeByDir(type, false);
		}
	}

	/**
	 * �ж���Ϸ�����ķ���
	 */
	public void isGameOver() {
		boolean isGameOver = true;
		// Ҫ����ÿ����ϷС����
		for (int i = 0; i < iv_array.length; i++) {
			for (int j = 0; j < iv_array[0].length; j++) {
				// Ϊ�յķ������ݲ��ж�����
				if (iv_array[i][j] == iv_null_imageView) {
					continue;
				}
				GameData mGameData = (GameData) iv_array[i][j].getTag();
				if (!mGameData.isTrue()) {
					isGameOver = false;
					
					break;
				}
			}
		}

		// ����һ�����ر���������Ϸ�Ƿ����������ʱ����ʾ
		if (isGameOver) {
			simple();
		}

	}

	/* ÿ����ϷС������Ҫ�󶨵����� */
	class GameData {
		// ÿ��С�����ʵ��λ��x
		public int x = 0;
		// ÿ��С�����ʵ��λ��y
		public int y = 0;
		// ÿ��С�����ͼƬ
		public Bitmap bm;
		// ÿ��С�����ͼƬ��λ��x
		public int p_x = 0;
		// ÿ��С�����ͼƬ��λ��y
		public int p_y = 0;

		public GameData(int x, int y, Bitmap bm) {
			super();
			this.x = x;
			this.y = y;
			this.bm = bm;
			this.p_x = x;
			this.p_y = y;
		}

		/**
		 * ÿ��С�����λ���Ƿ���ȷ
		 * 
		 * @return
		 */
		public boolean isTrue() {
			if (x == p_x && y == p_y) {
				return true;
			}
			return false;
		}

	}
//�Զ��嵯��
	public void simple() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		builder.setTitle(" ");
		builder.setIcon(R.drawable.login);
		builder.setMessage("��Ϸ����... ");
//		ȷ�����˳���ť�ĵ�������¼�
		builder.setPositiveButton("Surprise", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface arg0, int arg1) {
				// TODO Auto-generated method stub
				Intent intent = new Intent(MainActivity.this, ShowActivity.class);
				startActivity(intent);
				finish();
			}
		});
		builder.create().show();
	} 
}
