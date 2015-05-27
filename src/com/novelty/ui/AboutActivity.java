package com.novelty.ui;

import com.novelty.imsakia.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.widget.TextView;

public class AboutActivity extends Activity {
	private TextView appVersion;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_about);

		/** Initialization of views **/
		init();
	}
	
	public void init(){
		/** Only for testing TVGuidList listView **/
		appVersion = (TextView) findViewById(R.id.aboutVersionTxt);
		String version = getResources().getString(R.string.about_app_version)+" "+getVersionNumber();
		appVersion.setText(version);
	}
	
	public static Intent getActivityIntent(Context context) {
		return new Intent(context, AboutActivity.class);
	}
	
	private String getVersionNumber(){
		/** To get application version */
		String versionNumber = " ";
		try {
		    versionNumber = getPackageManager().getPackageInfo(getPackageName(), 0).versionName;
		} catch (NameNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return versionNumber;
	}
}
