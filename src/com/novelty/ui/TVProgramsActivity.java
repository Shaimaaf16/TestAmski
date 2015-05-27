package com.novelty.ui;

import java.util.ArrayList;

import com.novelty.imsakia.R;
import com.novelty.imsakia.activities.BaseActivity;
import com.novelty.imsakia.adapters.TVGuidListAdapter;
import com.novelty.imsakia.controller.communication.AsyncTaskInvoker;
import com.novelty.imsakia.controller.communication.ConnectionDetector;
import com.novelty.imsakia.controller.communication.DataRequestor;
import com.novelty.imsakia.controller.communication.Task;
import com.novelty.imsakia.controller.communication.Task.TaskID;
import com.novelty.imsakia.model.TVGuidModel;
import com.novelty.imsakia.tasks.GetTVProgramesList;
import com.novelty.imsakia.utils.Params;
import com.novelty.imsakia.utils.UIUtils;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class TVProgramsActivity extends BaseActivity implements OnClickListener , DataRequestor{
	private ListView tvGuidListView;
	private RelativeLayout dropDownMenuRL;
	private ProgressDialog mSpinnerProgress;
	private TVGuidListAdapter adapter         = null;
	private ArrayList<TVGuidModel> tvGuidList = new ArrayList<TVGuidModel>();
	private TextView channelNameTxt;
	private String[] listPrograms = new String[]{"MBC Max", "MBC 2", "MBC Action","MBC 3", "MBC 1","MBC 4"};
	private ImageView tvImageImg;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_tv_guid_list);

		/** Initialization of views **/
		init(); 
	}
	
	public void init(){
		/** Only for testing TVGuidList listView **/
		testData();
		
		tvGuidListView = (ListView) findViewById(R.id.tvGuidListV);
		tvImageImg     = (ImageView) findViewById(R.id.tvImageImg);
		dropDownMenuRL = (RelativeLayout) findViewById(R.id.dropDownMenuRL);
		dropDownMenuRL.setVisibility(View.VISIBLE);
		channelNameTxt = (TextView) findViewById(R.id.channelNameTxt);
		channelNameTxt.setText(getIntentObject().getName());
		adapter        = new TVGuidListAdapter(TVProgramsActivity.this, tvGuidList, Params.TVGuid.TV_PROGRAM_ACTVITY);
		tvGuidListView.setAdapter(adapter);
		
		dropDownMenuRL.setOnClickListener(this);
	}
	
	public void testData(){
		TVGuidModel model = new TVGuidModel("CBC..",
				"https://lh6.googleusercontent.com/-55osAWw3x0Q/URquUtcFr5I/AAAAAAAAAbs/rWlj1RUKrYI/s1024/A%252520Photographer.jpg", "TV Channel..");
		tvGuidList.add(model);
		model = new TVGuidModel("CBC Exra..", 
				"https://lh6.googleusercontent.com/-55osAWw3x0Q/URquUtcFr5I/AAAAAAAAAbs/rWlj1RUKrYI/s1024/A%252520Photographer.jpg", "TV Channel..");
		tvGuidList.add(model);
		model = new TVGuidModel("MBC MAX..", 
				"https://lh6.googleusercontent.com/-55osAWw3x0Q/URquUtcFr5I/AAAAAAAAAbs/rWlj1RUKrYI/s1024/A%252520Photographer.jpg", "TV Channel..");
		tvGuidList.add(model);
		model = new TVGuidModel("CBC Exra..", 
				"https://lh6.googleusercontent.com/-55osAWw3x0Q/URquUtcFr5I/AAAAAAAAAbs/rWlj1RUKrYI/s1024/A%252520Photographer.jpg", "TV Channel..");
		tvGuidList.add(model);
		model = new TVGuidModel("MBC MAX..", 
				"https://lh6.googleusercontent.com/-55osAWw3x0Q/URquUtcFr5I/AAAAAAAAAbs/rWlj1RUKrYI/s1024/A%252520Photographer.jpg", "TV Channel..");
		tvGuidList.add(model);
		model = new TVGuidModel("CBC Exra..", 
				"https://lh6.googleusercontent.com/-55osAWw3x0Q/URquUtcFr5I/AAAAAAAAAbs/rWlj1RUKrYI/s1024/A%252520Photographer.jpg", "TV Channel..");
		tvGuidList.add(model);
		model = new TVGuidModel("MBC MAX..", 
				"https://lh6.googleusercontent.com/-55osAWw3x0Q/URquUtcFr5I/AAAAAAAAAbs/rWlj1RUKrYI/s1024/A%252520Photographer.jpg", "TV Channel..");
		tvGuidList.add(model);
		model = new TVGuidModel("CBC Exra..", 
				"https://lh6.googleusercontent.com/-55osAWw3x0Q/URquUtcFr5I/AAAAAAAAAbs/rWlj1RUKrYI/s1024/A%252520Photographer.jpg", "TV Channel..");
		tvGuidList.add(model);
		model = new TVGuidModel("MBC MAX..", 
				"https://lh6.googleusercontent.com/-55osAWw3x0Q/URquUtcFr5I/AAAAAAAAAbs/rWlj1RUKrYI/s1024/A%252520Photographer.jpg", "TV Channel..");
		tvGuidList.add(model);
		model = new TVGuidModel("CBC Exra..", 
				"https://lh6.googleusercontent.com/-55osAWw3x0Q/URquUtcFr5I/AAAAAAAAAbs/rWlj1RUKrYI/s1024/A%252520Photographer.jpg", "TV Channel..");
		tvGuidList.add(model);
		model = new TVGuidModel("MBC MAX..", 
				"https://lh6.googleusercontent.com/-55osAWw3x0Q/URquUtcFr5I/AAAAAAAAAbs/rWlj1RUKrYI/s1024/A%252520Photographer.jpg", "TV Channel..");
		tvGuidList.add(model);
		model = new TVGuidModel("MBC MAX..", 
				"https://lh6.googleusercontent.com/-55osAWw3x0Q/URquUtcFr5I/AAAAAAAAAbs/rWlj1RUKrYI/s1024/A%252520Photographer.jpg", "TV Channel..");
		tvGuidList.add(model);
	}
	
	public static Intent getActivityIntent(Context context, TVGuidModel model) {
		return new Intent(context, TVProgramsActivity.class)
					.putExtra(Params.TVGuid.CHANNEL_OBJECT, model);
	}

	/**  Initialize of PhotoList Dialog **/
	private void showProgramListDialog() {

		/** Initialize of dialog **/
		final Dialog dialog = new Dialog(TVProgramsActivity.this);

		/** remove dialog title **/
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);

		/** Include dialog.xml file **/
		dialog.setContentView(R.layout.tv_program_list_dialog);

		/** set dialog background transparent to use my own view instead **/
		dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));

		/** set dialog gravity **/
		dialog.getWindow().setGravity(Gravity.TOP);

		/** set ListView initialization **/
		ListView listView = (ListView) dialog.findViewById(R.id.tvProgramsListV);
		listView.setSelector(R.drawable.list_item_selected_colored);
		
		ArrayAdapter<String> programsArrayAdapter = new ArrayAdapter<String>(this, 
				R.layout.drop_down_list_item,R.id.tvProgramNameTxt, listPrograms);
		listView.setAdapter(programsArrayAdapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view,	int position, long id) {
				channelNameTxt.setText(listPrograms[position]);
				dialog.dismiss();
				tvImageImg.setImageResource(R.drawable.down);
			}
		});

		/** Show dialog **/
		dialog.show();
	}
	
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.dropDownMenuRL:
			tvImageImg.setImageResource(R.drawable.up);
			showProgramListDialog();
			break;

		default:
			break;
		}
	}
	
	public TVGuidModel getIntentObject() {
		return (TVGuidModel) getIntent().getExtras().get(Params.TVGuid.CHANNEL_OBJECT);
	}

	public void getTVProgrameList() {
		if (ConnectionDetector.getInstance(this).hasConnection()) {
			mSpinnerProgress = new ProgressDialog(this);
			mSpinnerProgress.setIndeterminate(true);
			mSpinnerProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			mSpinnerProgress.setMessage("Loading ....");
			mSpinnerProgress.show();
			Task task = new GetTVProgramesList(this, this.getApplicationContext());
			AsyncTaskInvoker.RunTaskInvoker(task);
		} else {
			UIUtils.showToast(this, "No internet Connection");
		}		
	}
	@Override
	public void onStart(Task task) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onFinish(Task task) {
		if (task.getId() == TaskID.GetTVGuidProgramsTask && task.getResult() != null) {
			ArrayList<TVGuidModel> model = (ArrayList<TVGuidModel>) task.getResult();
			adapter = new TVGuidListAdapter(this, model, Params.TVGuid.TV_PROGRAM_ACTVITY);
			tvGuidListView.setAdapter(adapter);
		}
		mSpinnerProgress.cancel();
	}

	@Override
	public void handleClick() {
		// TODO Auto-generated method stub
	}
}
