package com.novelty.imsakia;

import com.novelty.imsakia.R;
import com.novelty.imsakia.activities.QuraanTabsActivity;
import com.novelty.ui.AboutActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.LinearLayout;

public class MoreActivity extends Activity implements OnClickListener {
	private LinearLayout prayerlay, quranlay, laylatlay, branchslay,aboutlay, shareLay;

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

		shareLay = (LinearLayout) findViewById(R.id.shareLay);
		shareLay.setOnClickListener(this);
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
		switch (v.getId()) {
		case R.id.quranlay:
			startActivity(new Intent(MoreActivity.this,QuraanTabsActivity.class));
			break;
		case R.id.branchslay:
			openBranches();
			break;
		case R.id.prayerlay:
			openPrayerSetting();
			break;
		case R.id.aboutlay:
			startActivity(AboutActivity.getActivityIntent(this));
			break;
		case R.id.shareLay:
			// TODO For sharing on Google Play Store
			final String appPackageName = getPackageName(); // getPackageName() from Context or Activity object
			/*try {
			    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=" + appPackageName))); /**handle*/
			/*} catch (android.content.ActivityNotFoundException anfe) {
			    startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://play.google.com/store/apps/details?id=" + appPackageName)));
			}
			*/
			// TODO For sharing on social network
			startActivity(new Intent().setAction(Intent.ACTION_SEND)
					  .putExtra(Intent.EXTRA_TEXT, "market://details?id=" + appPackageName)
					  .setType("text/plain"));
			break;
		default:
			break;
		}
	}

}
