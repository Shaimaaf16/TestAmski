package com.novelty.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;

import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.novelty.imsakia.Application;
import com.novelty.imsakia.R;
import com.novelty.imsakia.controller.communication.AsyncTaskInvoker;
import com.novelty.imsakia.controller.communication.ConnectionDetector;
import com.novelty.imsakia.controller.communication.DataRequestor;
import com.novelty.imsakia.controller.communication.Task;
import com.novelty.imsakia.controller.communication.Task.TaskID;
import com.novelty.imsakia.data.cache.SharedPrefrencesDataLayer;
import com.novelty.imsakia.model.TVModel;
import com.novelty.imsakia.tasks.FollowProgram;
import com.novelty.imsakia.utils.Params;
import com.novelty.imsakia.utils.UIUtils;

public class TVProgramDetailsActivity extends Activity  implements DataRequestor, OnClickListener{

	private TextView tvPresenterTxt, tvShowDaysTxt, tvShowTimeTxt, titleNameTxt, followTxt;
	private TVModel tvModel;
	private ImageView tvProgramImageImg;
	private ImageLoader mLoader;
	private DisplayImageOptions options;
	private ProgressDialog mSpinnerProgress;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_tv_program_details);

		/** Initialization of views **/
		init();
	}
	
	public void init(){

		mLoader            = Application.getInstance().getImageLoader();
		tvModel = (TVModel) getIntent().getSerializableExtra(Params.TVGuid.TV_DETAILS_OBJECT);
		tvProgramImageImg  = (ImageView) findViewById(R.id.tvProgramImageImg);
		titleNameTxt       = (TextView) findViewById(R.id.titleNameTxt);
		tvPresenterTxt     = (TextView) findViewById(R.id.tvPresenterTxt);
		tvShowDaysTxt      = (TextView) findViewById(R.id.tvShowDaysTxt);
		tvShowTimeTxt      = (TextView) findViewById(R.id.tvShowTimeTxt);
		followTxt 		   = (TextView) findViewById(R.id.followTxt);
		followTxt.setOnClickListener(this);

		titleNameTxt.setText(tvModel.getName());
		
		if(tvModel.getRepeatTime() != null)
			tvShowDaysTxt.setText(tvModel.getRepeatTime());
		
		if(tvModel.getShowTime() != null)
			tvShowTimeTxt.setText(tvModel.getShowTime());
		
		if(tvModel.getPresenters() != null)
			for(int i=0; i< tvModel.getPresenters().size(); i++)
				tvPresenterTxt.setText(tvModel.getPresenters().get(i).getName()+" ");
		
		if(tvModel.getImage() != null)
			mLoader.displayImage(tvModel.getImage(), tvProgramImageImg, Application.getInstance().getDisplayOption(), new ImageLoadingListener() {
				@Override
				public void onLoadingStarted(String arg0, View arg1) {
					// TODO Auto-generated method stub
				}
			
				@Override
				public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
					// TODO Auto-generated method stub
				}
				
				@Override
				public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
					// TODO Auto-generated method stub
				}
			
				@Override
				public void onLoadingCancelled(String arg0, View arg1) {
					// TODO Auto-generated method stub
				}
			});
		
	}
	
	public static Intent getActivityIntent(Context context, TVModel tVModel) {
		return new Intent(context, TVProgramDetailsActivity.class)
		.putExtra(Params.TVGuid.TV_DETAILS_OBJECT, tVModel);
	}

	public void getTotalFollowers(String programID, int followFlag) {
		if (ConnectionDetector.getInstance(this).hasConnection()) {
			mSpinnerProgress = new ProgressDialog(this);
			mSpinnerProgress.setIndeterminate(true);
			mSpinnerProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			mSpinnerProgress.setMessage("Loading ....");
			mSpinnerProgress.show();
			Task task = new FollowProgram(this, this.getApplicationContext(), programID, followFlag);
			AsyncTaskInvoker.RunTaskInvoker(task);
		} else {
			UIUtils.showToast(getApplicationContext(), "No internet Connection");
		}
	}
	
	@Override
	public void onStart(Task task) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(Task task) {
		if (task.getId() == TaskID.GetProgramFollowersTask && task.getResult() != null) {
			followTxt.setText(getResources().getString(R.string.followers) +"\n"+task.getResult().toString());
			if(SharedPrefrencesDataLayer.getBooleanPreferences(this, Params.TVGuid.FOLLOW_FLAG, false))
				SharedPrefrencesDataLayer.saveBooleanPreferences(this, Params.TVGuid.FOLLOW_FLAG, true);
			else
				SharedPrefrencesDataLayer.saveBooleanPreferences(this, Params.TVGuid.FOLLOW_FLAG, false);
		}
		mSpinnerProgress.cancel();		
	}

	@Override
	public void handleClick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.followTxt:
			if(SharedPrefrencesDataLayer.getBooleanPreferences(this, Params.TVGuid.FOLLOW_FLAG, false))
				getTotalFollowers(tvModel.getId(), Params.TVGuid.TV_UN_FOLLOW);
			else
				getTotalFollowers(tvModel.getId(), Params.TVGuid.TV_FOLLOW);
			
			break;

		default:
			break;
		}
	}
}
