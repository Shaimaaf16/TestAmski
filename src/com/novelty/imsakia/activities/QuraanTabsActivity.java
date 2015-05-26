package com.novelty.imsakia.activities;


import android.app.LocalActivityManager;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;

import com.novelty.imsakia.R;

/**
 * 
 * @author M.Turki
 *
 */
@SuppressWarnings("deprecation")
public class QuraanTabsActivity extends FragmentActivity {

    private QuraanTabsActivity baseContext;
	private TabHost mTabHost;
	private TextView selectedTab;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new DisplayMetrics();
		baseContext = this;
        setContentView(R.layout.activity_quraan_tabs);
        mTabHost    = (TabHost)findViewById(R.id.tabhost);
        selectedTab = (TextView)findViewById(R.id.selectedTab);
        
        LocalActivityManager mLocalActivityManager = new LocalActivityManager(baseContext, false);
        mLocalActivityManager.dispatchCreate(savedInstanceState); // state will be bundle your activity state which you get in onCreate
       
        mTabHost.setup(mLocalActivityManager);
    	mTabHost.addTab(mTabHost.newTabSpec("Quraan").setIndicator("Surah",null)
				.setContent(new Intent(this,SurahListActivity.class)));
      	mTabHost.addTab(mTabHost.newTabSpec("Quraan").setIndicator("Chapter",null)
				.setContent(new Intent(this,ChapterListActivity.class)));
    	mTabHost.setCurrentTab(0);
    	selectedTab.setText("Quraan");
     	for(int i=0;i<mTabHost.getTabWidget().getChildCount();i++)
     		mTabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.TRANSPARENT); //unselected
    	mTabHost.setOnTabChangedListener(new OnTabChangeListener(){
    	        @Override
    	        public void onTabChanged(String tabId) {
    	             for(int i=0;i<mTabHost.getTabWidget().getChildCount();i++)
    	            	 mTabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.TRANSPARENT); //unselected
    	             mTabHost.getTabWidget().getChildAt(mTabHost.getCurrentTab()).setBackgroundColor(Color.TRANSPARENT); // selected
    	             selectedTab.setText(mTabHost.getCurrentTabTag());
    	        }
    	});
	}
}
