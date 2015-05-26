package com.novelty.imsakia.services;

import com.novelty.imsakia.R;

import android.app.Service;
import android.content.Intent;
import android.graphics.PixelFormat;
import android.os.IBinder;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

public class ChatHeadService extends Service {

	private WindowManager windowManager;
	private ImageView chatHead;
	private ImageView chatRemove;

	@Override
	public IBinder onBind(Intent intent) {
		// Not used
		return null;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		windowManager = (WindowManager) getSystemService(WINDOW_SERVICE);

		chatHead = new ImageView(this);
		chatHead.setImageResource(R.drawable.ic_launcher);
		chatRemove=new ImageView(this);
		chatRemove.setImageResource(R.drawable.arrow);
		chatRemove.setVisibility(View.INVISIBLE);

		final WindowManager.LayoutParams params = new WindowManager.LayoutParams(
				WindowManager.LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.TYPE_PHONE,
				WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
				PixelFormat.TRANSLUCENT);
		final WindowManager.LayoutParams removeparams = new WindowManager.LayoutParams(
				WindowManager.LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.WRAP_CONTENT,
				WindowManager.LayoutParams.TYPE_PHONE,
				WindowManager.LayoutParams.FLAG_NOT_FOCUSABLE,
				PixelFormat.TRANSLUCENT);
		removeparams.gravity=Gravity.BOTTOM|Gravity.CENTER;
		params.gravity = Gravity.TOP | Gravity.LEFT;
		params.x = 0;
		params.y = 100;
		int he=windowManager.getDefaultDisplay().getHeight();
		int width=windowManager.getDefaultDisplay().getWidth();
		Log.d("windowManager", width+" : "+he);
		final int center=width/2;
		chatHead.setOnTouchListener(new View.OnTouchListener() {
			private int initialX;
			private int initialY;
			private float initialTouchX;
			private float initialTouchY;
			private boolean isOnClick;
			private boolean isonCancel;

			@Override
			public boolean onTouch(View v, MotionEvent event) {
				switch (event.getAction()) {
				case MotionEvent.ACTION_DOWN:
					initialX = params.x;
					initialY = params.y;
					isOnClick = true;
					initialTouchX = event.getRawX();
					initialTouchY = event.getRawY();
					return true;
				case MotionEvent.ACTION_UP:
					if (isOnClick) {
						Log.i("ChatHeadServices ", "onClick ");
						// TODO onClick code
					}
					return true;
				case MotionEvent.ACTION_MOVE:
					params.x = initialX
							+ (int) (event.getRawX() - initialTouchX);
					params.y = initialY
							+ (int) (event.getRawY() - initialTouchY);
					// if(params.x>0&&params.y>0&&isOnClick)
					// {
					Log.i("ChatHead Services ", "Movements " + event.getRawX() + " : "
							+ params.y);
					if(event.getRawX()==center||event.getRawX()<= (center+100)||event.getRawX() >=(center-100))
					{
						chatRemove.setVisibility(View.VISIBLE);
						isonCancel=true;
						
					}
					else
					{
						chatRemove.setVisibility(View.INVISIBLE);
						isonCancel=false;
					}
			
					windowManager.updateViewLayout(chatHead, params);
					
					isOnClick = false;
					// }
					return true;

				}
				return false;
			}
		});

		windowManager.addView(chatHead, params);
		windowManager.addView(chatRemove, removeparams);

	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		if (chatHead != null)
			windowManager.removeView(chatHead);
	}
}
