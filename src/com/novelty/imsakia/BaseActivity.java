package com.novelty.imsakia;

import java.util.Locale;

import com.novelty.imsakia.R;

import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.FrameLayout;
import android.widget.ImageButton;
import android.widget.TextView;


public class BaseActivity extends FragmentActivity {

	FrameLayout container;
	TextView activity_title;
	ImageButton rightButton, leftButton;
	public static int width, hight;
	SharedPreferences prefs;
	Locale locale;

	@Override
	protected void onCreate(Bundle arg0) {
		// TODO Auto-generated method stub
		super.onCreate(arg0);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.base_activity_layout);
		container = (FrameLayout) findViewById(R.id.act_container);
		rightButton = (ImageButton) findViewById(R.id.btn_right_control);
		leftButton = (ImageButton) findViewById(R.id.btn_left_control);
		activity_title = (TextView) findViewById(R.id.headerTitle);
		Typeface tf = Typeface.createFromAsset(getAssets(),
				"fonts/DroidKufi-Bold.ttf");
		activity_title.setTypeface(tf);
		DisplayMetrics metrics = new DisplayMetrics();
		getWindowManager().getDefaultDisplay().getMetrics(metrics);
		metrics.toString();
		width = metrics.widthPixels;
		hight = metrics.heightPixels;
		leftButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DoLFunction();
			}
		});

		rightButton.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				DoRFunction();
			}
		});
	}

	public void DoLFunction() {
		finish();
	}

	public void DoRFunction() {

	}

	public void hideLeftControl() {
		leftButton.setVisibility(View.GONE);
	}

	public void ShowLeftContol() {
		leftButton.setImageResource(R.drawable.back);
		leftButton.setVisibility(View.VISIBLE);
	}

	public void ShowLeftContolDirctory() {
		leftButton.setImageResource(R.drawable.menu);
		leftButton.setVisibility(View.VISIBLE);
	}

	public void hideRightControl() {
		rightButton.setVisibility(View.GONE);
	}

	public void ShowRightControl(int search) {
		rightButton.setVisibility(View.VISIBLE);
		rightButton.setImageResource(search);
	}

	public void SetActTitle(String title) {
		activity_title.setText(title);
	}

	public void SetActTitle(int title) {
		activity_title.setText(title);
	}

	public void SetActivityLayout(View lv) {
		/**
		 * to add activity layout for the activity use the code below
		 * inflater=(LayoutInflater
		 * )getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		 * lv=inflater.inflate(R.layout.event_activity_main, null);
		 */
		container.removeAllViews();
		container.addView(lv);

	}

	public void AddFragment(Fragment fr) {

		FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
		ft.replace(R.id.act_container, fr);
		ft.commit();
	}

}
