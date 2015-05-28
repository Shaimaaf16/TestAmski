package com.novelty.imsakia;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.provider.Settings;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.Toast;

import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.SaveCallback;


public class SplashActivity extends Activity {

	private ImageView splash_img;
	private SharedPreferences appSettin;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);

		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setContentView(R.layout.activity_splash);

		splash_img = (ImageView) findViewById(R.id.splash_img);
		

		  appSettin = getSharedPreferences("Amskia",
				Context.MODE_PRIVATE);
		 final String  android_id = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
		   
	        ParseInstallation installation = ParseInstallation.getCurrentInstallation();
			    installation.put("UniqueId",android_id);
			    ParseInstallation.getCurrentInstallation().saveInBackground(new SaveCallback() {
			        @Override
			        public void done(ParseException e) {
			            if (e == null) {
			                Toast toast = Toast.makeText(getApplicationContext(), R.string.alert_dialog_success, Toast.LENGTH_LONG);
			                toast.show();
			            } else {
			                e.printStackTrace();

			                Toast toast = Toast.makeText(getApplicationContext(), R.string.alert_dialog_failed, Toast.LENGTH_SHORT);
			                toast.show();
			            }
			        }
			    });
				

		String  userid=appSettin.getString("UserId", "");

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
