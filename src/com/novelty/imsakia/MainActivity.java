package com.novelty.imsakia;


import android.app.LocalActivityManager;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.PorterDuff;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TabHost;
import android.widget.TabHost.OnTabChangeListener;
import android.widget.TextView;

import com.novelty.imsakia.R;
import com.novelty.imsakia.services.ChatHeadService;

@SuppressWarnings("deprecation")
public class MainActivity extends FragmentActivity implements OnClickListener{

    private MainActivity baseContext;
	private TabHost mTabHost;
	private TextView selectedTab;
	private ImageView setting;
	@Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        new DisplayMetrics();
		baseContext = this;

        setContentView(R.layout.activity_main);
        setting=(ImageView)findViewById(R.id.setting);
        setting.setOnClickListener(this);
        mTabHost = (TabHost)findViewById(R.id.tabhost);
        selectedTab=(TextView)findViewById(R.id.selectedTab);
        
        LocalActivityManager mLocalActivityManager = new LocalActivityManager(baseContext, false);
        mLocalActivityManager.dispatchCreate(savedInstanceState); // state will be bundle your activity state which you get in onCreate
        mTabHost.setup(mLocalActivityManager);
   

    	mTabHost.addTab(mTabHost.newTabSpec("Home").setIndicator("",getResources().getDrawable(R.drawable.tab_select_home))
				.setContent(new Intent(this,HomeActivity.class)));
      	mTabHost.addTab(mTabHost.newTabSpec("Qibla").setIndicator("",getResources().getDrawable(R.drawable.tab_select_qibla))
				.setContent(new Intent(this,CompassActivity.class)));
      	mTabHost.addTab(mTabHost.newTabSpec("Duaa").setIndicator("",getResources().getDrawable(R.drawable.tab_select_duaa))
				.setContent(new Intent(this,DuaaActivity.class)));
      	mTabHost.addTab(mTabHost.newTabSpec("Weather").setIndicator("",getResources().getDrawable(R.drawable.tab_select_weather))
				.setContent(new Intent(this,WeatherActivity.class)));
      	mTabHost.addTab(mTabHost.newTabSpec("More").setIndicator("",getResources().getDrawable(R.drawable.tab_select_more))
				.setContent(new Intent(this,MoreActivity.class)));
    	mTabHost.setCurrentTab(0);
    	selectedTab.setText("Home");
    	 for(int i=0;i<mTabHost.getTabWidget().getChildCount();i++)
         {
     	 mTabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.TRANSPARENT); //unselected
         }
    	mTabHost.setOnTabChangedListener(new OnTabChangeListener(){
    	        @Override
    	        public void onTabChanged(String tabId) {
    	            // TODO Auto-generated method stub
    	             for(int i=0;i<mTabHost.getTabWidget().getChildCount();i++)
    	                {
    	            	 mTabHost.getTabWidget().getChildAt(i).setBackgroundColor(Color.TRANSPARENT); //unselected
    	                }
    	             mTabHost.getTabWidget().getChildAt(mTabHost.getCurrentTab()).setBackgroundColor(Color.TRANSPARENT); // selected
//    	             mTabHost.getTabWidget().getChildAt(mTabHost.getCurrentTab()).getBackground().setColorFilter(Color.RED, PorterDuff.Mode.SRC_OUT);

    	             selectedTab.setText(mTabHost.getCurrentTabTag());
    	        }
    	        });

//    	startService(new Intent(this, ChatHeadService.class));
	}
	
 
	public void openSetting() {
		Intent setting = new Intent(MainActivity.this, SettingActivity.class);
		startActivity(setting);
	}


	@Override
	public void onClick(View v) {

		if(v==setting)
		{
			openSetting();
		}
	}
}
