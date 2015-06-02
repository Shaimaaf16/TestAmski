package com.novelty.ui;

import java.util.ArrayList;

import com.novelty.imsakia.R;
import com.novelty.imsakia.adapters.TVGuidListAdapter;
import com.novelty.imsakia.controller.communication.AsyncTaskInvoker;
import com.novelty.imsakia.controller.communication.ConnectionDetector;
import com.novelty.imsakia.controller.communication.DataRequestor;
import com.novelty.imsakia.controller.communication.Task;
import com.novelty.imsakia.controller.communication.Task.TaskID;
import com.novelty.imsakia.model.TVGuidAndProgrameModel;
import com.novelty.imsakia.tasks.GetTVGuidList;
import com.novelty.imsakia.utils.Params;
import com.novelty.imsakia.utils.UIUtils;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Toast;

public class TVGuidActivity extends Activity implements DataRequestor{
	private ListView tvGuidListView;
	private RelativeLayout dropDownMenuRL;
	private ProgressDialog mSpinnerProgress;
	private TVGuidListAdapter adapter        = null;
	private ArrayList<TVGuidAndProgrameModel> tvGuidList = new ArrayList<TVGuidAndProgrameModel>();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_tv_guid_list);

		/** Initialization of views **/
		init();
		getTVuidList();
	}
	
	public void init(){
		/** Only for testing TVGuidList listView **/
		tvGuidListView = (ListView) findViewById(R.id.tvGuidListV);
		dropDownMenuRL = (RelativeLayout) findViewById(R.id.dropDownMenuRL);
		adapter        = new TVGuidListAdapter(this, tvGuidList, Params.TVGuid.TV_GUID_ACTVITY);
		tvGuidListView.setAdapter(adapter);
	}

	public static Intent getActivityIntent(Context context) {
		return new Intent(context, TVGuidActivity.class);
	}

	@Override
	public void onStart(Task task) {
		// TODO Auto-generated method stub
	}
	
	public void getTVuidList() {
		if (ConnectionDetector.getInstance(this).hasConnection()) {
			mSpinnerProgress = new ProgressDialog(this);
			mSpinnerProgress.setIndeterminate(true);
			mSpinnerProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			mSpinnerProgress.setMessage("Loading ....");
			mSpinnerProgress.show();
			Task task = new GetTVGuidList(this, this.getApplicationContext());
			AsyncTaskInvoker.RunTaskInvoker(task);
		} else {
			UIUtils.showToast(getApplicationContext(), "No internet Connection");
		}
	}
	@Override
	public void onFinish(Task task) {
		if (task.getId() == TaskID.GetTVGuidListTask && task.getResult() != null) {
			ArrayList<TVGuidAndProgrameModel> model = (ArrayList<TVGuidAndProgrameModel>) task.getResult();
			adapter = new TVGuidListAdapter(this, model, Params.TVGuid.TV_GUID_ACTVITY);
			tvGuidListView.setAdapter(adapter);
		}
		mSpinnerProgress.cancel();
	}

	@Override
	public void handleClick() {
		// TODO Auto-generated method stub
	}
}
