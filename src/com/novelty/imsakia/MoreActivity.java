package com.novelty.imsakia;

import com.novelty.imsakia.R;
import com.novelty.imsakia.activities.QuraanTabsActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class MoreActivity extends Activity implements OnClickListener {
	private LinearLayout prayerlay, quranlay, laylatlay, branchslay,aboutlay;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.more_activity);
		init();
	}

	public void init() {

		prayerlay = (LinearLayout) findViewById(R.id.prayerlay);
		prayerlay.setOnClickListener(this);

		quranlay = (LinearLayout) findViewById(R.id.quranlay);
		quranlay.setOnClickListener(this);

		laylatlay = (LinearLayout) findViewById(R.id.laylatlay);
		laylatlay.setOnClickListener(this);
		branchslay = (LinearLayout) findViewById(R.id.branchslay);
		branchslay.setOnClickListener(this);

		aboutlay = (LinearLayout) findViewById(R.id.aboutlay);
		aboutlay.setOnClickListener(this);
	}

	public void openPrayerSetting() {
		Intent prayerSetting = new Intent(MoreActivity.this,
				PrayerSetting.class);
		startActivity(prayerSetting);
	}
	public void openBranches() {
		Intent btanches = new Intent(MoreActivity.this,
				BranchesActivity.class);
		startActivity(btanches);
	}

	@Override
	public void onClick(View v) {
		if (prayerlay == v) {
			openPrayerSetting();
		}
		else  if(v==quranlay)
		{
			startActivity(new Intent(MoreActivity.this,QuraanTabsActivity.class));
		}
		else if(v==branchslay)
		{
			openBranches();
		}

	}

}
