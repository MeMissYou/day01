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
	 * 利用二维数组创建若干个游戏小方块
	 */
	private ImageView[][] iv_array = new ImageView[5][4];

	private GridLayout gridLayout;

	// 当前空方块的实例的保存
	private ImageView iv_null_imageView;

	// 当前手势
	private GestureDetector mDetector;

	// 判断游戏是否开始
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
        //获取拼图的图片
		Bitmap initBitmap = ((BitmapDrawable) getResources().getDrawable(
				R.drawable.aili)).getBitmap();
//		切割图片4*5
		int width = initBitmap.getWidth() / 4;
		int height = initBitmap.getHeight() / 5;

		/**
		 * 初始化游戏的若干个小方块 iv_array.length表示二维数组的长度，即二维数组的行数，此处为5
		 * iv_array[0].length表示二维数组第一行的长度，即二维数组的列数，此处为3
		 */
		for (int i = 0; i < iv_array.length; i++) {
			for (int j = 0; j < iv_array[0].length; j++) {

				Bitmap bm = Bitmap.createBitmap(initBitmap, j * width, i
						* height, width, height);// 根据行和列来切成若干个游戏小图片

				iv_array[i][j] = new ImageView(this);// 实例化每一个小方块
				iv_array[i][j].setImageBitmap(bm);// 为每个小方块设置其要显示图片
				iv_array[i][j].setPadding(1, 1, 1, 1);
				iv_array[i][j].setTag(new GameData(i, j, bm));// 这里传入的xy值是i和j，即表示假设空方块的位置为[2][2]，如果当前点击的方块在空方块上边，那当前点击的方块的位置为[1][2];
				iv_array[i][j].setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View v) {
						boolean flag = isHasByNullImageView((ImageView) v);
						if (flag) {
							changeDataByImageView((ImageView) v);
						}

					}
				});
//				图片内部的滑动
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
//										|| Math.abs(end_y - start_y) > 100) {// 此处不能仅仅使用大于0，大于0同样可以运行，但是可能会和点击事件重复，造成bug
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
		 * 初始化游戏界面，并添加若干个小方块
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

	// 利用动画结束之后，交换两个方块的数据
	public void changeDataByImageView(final ImageView mImageView, boolean isAnim) {

//		if (isGameStart) {
//			isGameOver();// 调用游戏结束方法
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
				isGameOver();// 调用游戏结束方法
			}

			return;
		}

		// 创建一个动画，设置好方向，移动的距离
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
		// 设置动画的时长
		translateAnimation.setDuration(70);
		// 设置动画结束之后是否停留
		translateAnimation.setFillAfter(true);
		// 设置动画结束之后把数据交换
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
					isGameOver();// 调用游戏结束方法
				}
			}
		});
		// 执行动画
		mImageView.startAnimation(translateAnimation);
	}

	/**
	 * 设置要显示的空方块
	 * 
	 * @param imageview
	 *            当前需要设置为空方块的imageview实例
	 */
	private void setGridItemNull(ImageView imageview) {
		imageview.setImageBitmap(null);
		iv_null_imageView = imageview;
	}

	/**
	 * 判断当前点击的方块，是否与空方块的位置相邻
	 * 
	 * @param mImageView表示当前所点击的方块
	 * @return true：相邻 false：不相邻
	 */
	public boolean isHasByNullImageView(final ImageView mImageView) {
		// 分别获取当前空方块的位置与点击方块的位置，通过x y的值差1的方式判断；
		GameData mNullGameData = (GameData) iv_null_imageView.getTag();
		GameData mGameData = (GameData) mImageView.getTag();
		if (mNullGameData.y == mGameData.y
				&& mGameData.x + 1 == mNullGameData.x) {
			// 当前点击的方块在空方块的上边
			// Toast.makeText(MainActivity.this, "当前点击的方块位于空方块的上边",
			// Toast.LENGTH_SHORT).show();
			return true;
		} else if (mNullGameData.y == mGameData.y
				&& mGameData.x - 1 == mNullGameData.x) {
			// 当前点击的方块在空方块的下边
			// Toast.makeText(MainActivity.this, "当前点击的方块位于空方块的下边",
			// Toast.LENGTH_SHORT).show();
			return true;
		} else if (mNullGameData.y == mGameData.y + 1
				&& mGameData.x == mNullGameData.x) {
			// 当前点击的方块在空方块的左边
			// Toast.makeText(MainActivity.this, "当前点击的方块位于空方块的左边",
			// Toast.LENGTH_SHORT).show();
			return true;
		} else if (mNullGameData.y == mGameData.y - 1
				&& mGameData.x == mNullGameData.x) {
			// 当前点击的方块在空方块的右边
			// Toast.makeText(MainActivity.this, "当前点击的方块位于空方块的右边",
			// Toast.LENGTH_SHORT).show();
			return true;
		}
		return false;
	}

	/**
	 * 手势判断，看是左右滑动还是上下滑动
	 * 
	 * @param start_x手势的起点x
	 * @param start_y手势的起点y
	 * @param end_X手势的终点x
	 * @param end_y手势的终点y
	 * @return
	 * 
	 */
	public int getDirByGes(float start_x, float start_y, float end_X,
			float end_y) {
		boolean isLeftOrRight = (Math.abs(start_x - end_X) > Math.abs(start_y
				- end_y)) ? true : false;// 是否是左右
		if (isLeftOrRight) {// 左右滑动
			boolean isLeft = start_x - end_X > 0 ? true : false;
			if (isLeft) {
				return 3;// 向左滑动
			} else {
				return 4; // 向右滑动
			}
		} else {// 上下滑动
			boolean isUp = start_y - end_y > 0 ? true : false;
			if (isUp) {
				return 1;// 向上滑动
			} else {
				return 2;// 向下滑动
			}
		}
	}

	public void changeByDir(int type) {
		changeByDir(type, true);
	}

	public void changeByDir(int type, boolean isAnim) {
		// 获取当前空方块的位置
		GameData mNullGameData = (GameData) iv_null_imageView.getTag();
		// 根据方向，设置相应的相邻位置的坐标
		int new_x = mNullGameData.x;
		int new_y = mNullGameData.y;
		if (type == 1) {
			// 要移动的方块在当前空方块的下边
			new_x++;
		} else if (type == 2) {
			new_x--;
		} else if (type == 3) {
			new_y++;
		} else if (type == 4) {
			new_y--;
		}
		// 判断这个新坐标是否存在
		if (new_x >= 0 && new_x < iv_array.length && new_y >= 0
				&& new_y < iv_array[0].length) {
			if (isAnim) {
				// 存在的话，开始移动
				changeDataByImageView(iv_array[new_x][new_y]);
			} else {
				changeDataByImageView(iv_array[new_x][new_y], isAnim);
			}

		} else {
			// 什么也不做
		}
		// 存在的话，开始移动
	}

	/**
	 * 进入拼图界面使其随机打乱顺序
	 */
//	设置游戏难度i
	public void randomMove() {
		for (int i = 0; i < 15; i++) {
			int type = (int) ((Math.random() * 4) + 1);
			changeByDir(type, false);
		}
	}

	/**
	 * 判断游戏结束的方法
	 */
	public void isGameOver() {
		boolean isGameOver = true;
		// 要遍历每个游戏小方块
		for (int i = 0; i < iv_array.length; i++) {
			for (int j = 0; j < iv_array[0].length; j++) {
				// 为空的方块数据不判断跳过
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

		// 根据一个开关变量决定游戏是否结束，结束时给提示
		if (isGameOver) {
			simple();
		}

	}

	/* 每个游戏小方块上要绑定的数据 */
	class GameData {
		// 每个小方块的实际位置x
		public int x = 0;
		// 每个小方块的实际位置y
		public int y = 0;
		// 每个小方块的图片
		public Bitmap bm;
		// 每个小方块的图片的位置x
		public int p_x = 0;
		// 每个小方块的图片的位置y
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
		 * 每个小方块的位置是否正确
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
//自定义弹框
	public void simple() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		
		builder.setTitle(" ");
		builder.setIcon(R.drawable.login);
		builder.setMessage("游戏结束... ");
//		确定和退出按钮的点击监听事件
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
