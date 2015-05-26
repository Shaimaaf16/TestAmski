package com.novelty.imsakia;

import com.novelty.imsakia.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;


public class SplashActivity extends Activity {

	private ImageView splash_img;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_splash);

		splash_img = (ImageView) findViewById(R.id.splash_img);
//		SharedPreferences appSettin = getSharedPreferences("Amskia",
//				Context.MODE_PRIVATE);
//		String  userid=appSettin.getString("UserId", "");
//		if(userid.length()>0)
//		{
			new Handler().postDelayed(new Runnable() {
				@Override
				public void run() {
					
					Intent i = new Intent(SplashActivity.this, MainActivity.class);
					startActivity(i);
					finish();
				}
			}, 3000);
//		}
//		else{
//		new Handler().postDelayed(new Runnable() {
//			@Override
//			public void run() {
//				
//				Intent i = new Intent(SplashActivity.this, LoginActivity.class);
//				startActivity(i);
//				finish();
//			}
//		}, 3000);
//		}

	}

}
