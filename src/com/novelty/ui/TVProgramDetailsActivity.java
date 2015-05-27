package com.novelty.ui;

import com.novelty.imsakia.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class TVProgramDetailsActivity extends Activity {

	private TextView tvPresenterTxt, tvShowDaysTxt, tvShowTimeTxt;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_tv_program_details);

		/** Initialization of views **/
		init();
	}
	
	public void init(){
		tvPresenterTxt = (TextView) findViewById(R.id.tvPresenterTxt);
		tvShowDaysTxt  = (TextView) findViewById(R.id.tvShowDaysTxt);
		tvShowTimeTxt  = (TextView) findViewById(R.id.tvShowTimeTxt);
		/** Only for testing TVGuidList listView **/
		testData();
		
	}
	
	public void testData(){

	}
	
	public static Intent getActivityIntent(Context context) {
		return new Intent(context, TVProgramDetailsActivity.class);
//		.putExtra(Params.TVGuid.CHANNEL_OBJECT, channelObj);
	}
}
