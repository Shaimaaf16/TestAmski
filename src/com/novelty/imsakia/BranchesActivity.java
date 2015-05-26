package com.novelty.imsakia;

import java.util.ArrayList;
import java.util.HashMap;

import com.novelty.imsakia.R;
import com.novelty.imsakia.adapters.BranchesListAdapter;
import com.novelty.imsakia.controller.communication.AsyncTaskInvoker;
import com.novelty.imsakia.controller.communication.ConnectionDetector;
import com.novelty.imsakia.controller.communication.DataRequestor;
import com.novelty.imsakia.controller.communication.Task;
import com.novelty.imsakia.controller.communication.Task.TaskID;
import com.novelty.imsakia.dataobjects.Branch;
import com.novelty.imsakia.dataobjects.UserData;
import com.novelty.imsakia.model.BranchesModel;
import com.novelty.imsakia.storage.ServiceStorage;
import com.novelty.imsakia.tasks.GetBranches;
import com.novelty.imsakia.tasks.PrayerTimes;
import com.novelty.imsakia.tasks.Weather;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.Toast;

public class BranchesActivity extends Activity implements DataRequestor {

	
	private ListView branchesListView;
	private BranchesListAdapter adapter = null;
	private ArrayList<Branch> branchesList = new ArrayList<Branch>();

	ProgressDialog mSpinnerProgress;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.fragment_branches);
		/** Initialization of views **/
		init();
		
	}
	
	public void init(){
		/** Only for testing branges listView **/
		//testData();
		getData();
		branchesListView = (ListView) findViewById(R.id.branchesListV);
		adapter = new BranchesListAdapter(this, branchesList);
	}
	private void getData() {

		if (ConnectionDetector.getInstance(this).hasConnection()) {
			mSpinnerProgress = new ProgressDialog(BranchesActivity.this);
			mSpinnerProgress.setIndeterminate(true);
			mSpinnerProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			mSpinnerProgress.setMessage("Loading ....");
			mSpinnerProgress.show();
			Task task = new GetBranches(this, this.getApplicationContext());
			AsyncTaskInvoker.RunTaskInvoker(task);
		
		}else {
			Toast.makeText(getApplicationContext(), "No internet Connection",
					Toast.LENGTH_LONG).show();
		}
	}

	
	public void testData(){
//		BranchesModel model = new BranchesModel("Branch Name..", "Branch Address..", 
//				"http://www.google.com.eg/imgres?imgurl=http://freehighresolutionimages.org/natural-images-22-hd-wallpaper.html&imgrefurl=http://freehighresolutionimages.org/natural-images-22-hd-wallpaper.html&h=1440&w=2560&tbnid=lm7OtUy-Bj4TWM:&zoom=1&docid=F3Gc7wJNiPQAJM&ei=As9cVcauI8HMyAP05oC4Bw&tbm=isch", 
//				100, "+201063878525");
//		branchesList.add(model);
//		model = new BranchesModel("Branch Name..", "Branch Address..", 
//				"http://www.google.com.eg/imgres?imgurl=http://www.hdwallpapersos.com/wp-content/uploads/2015/02/nature-wallpapers-natural-green-wallpaper-wallpaper.jpg&imgrefurl=https://blogs.lt.vt.edu/cads19912/2015/02/27/is-agriculture-natural-what-is/&h=1200&w=1920&tbnid=1mLMZb2YSGnu9M:&zoom=1&docid=m_Yq2OWxIhd3iM&ei=As9cVcauI8HMyAP05oC4Bw&tbm=isch", 
//				100, "+201063878525");
//		branchesList.add(model);
//		model = new BranchesModel("Branch Name..", "Branch Address..", 
//				"http://www.google.com.eg/imgres?imgurl=http://freehighresolutionimages.org/images/img9/natural-images-35.jpg&imgrefurl=http://freehighresolutionimages.org/natural-images-23-hd-wallpaper.html&h=1200&w=1600&tbnid=lRuo6a9IMTeijM:&zoom=1&docid=4tNR5xkJNuCd3M&ei=As9cVcauI8HMyAP05oC4Bw&tbm=isch&ved=0CDIQMygCMAI&biw=1366&bih=653", 
//				100, "+201063878525");
//		branchesList.add(model);
	}

	@Override
	public void onStart(Task task) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void onFinish(Task task) {

		if(task.getId()==TaskID.GetBranchesTask)
		{
			ArrayList<Branch>branches=(ArrayList<Branch>) task.getResult();
			adapter = new BranchesListAdapter(this, branches);

			branchesListView.setAdapter(adapter);

		}
		mSpinnerProgress.cancel();
	}

	@Override
	public void handleClick() {
		// TODO Auto-generated method stub
		
	}

}
