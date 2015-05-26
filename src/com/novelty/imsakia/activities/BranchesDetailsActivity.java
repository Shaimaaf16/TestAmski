package com.novelty.imsakia.activities;

import com.novelty.imsakia.R;
import com.novelty.imsakia.BaseActivity;
import com.novelty.imsakia.dataobjects.Branch;
import com.novelty.imsakia.fragment.BranchesDetailsFragment;
import com.novelty.imsakia.model.BranchesModel;
import com.novelty.imsakia.utils.Params;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class BranchesDetailsActivity extends Activity implements OnClickListener {
	private TextView emailTxt;
	private RelativeLayout navigationLayout;
	private TextView callTxt;
	private TextView branch_Name;
	private Branch branch;
	private boolean callFlag;
	private boolean emailFlag;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_branches_details);
		Bundle bundle=  getIntent().getExtras();
		if(bundle!=null)
		{
			  branch=(Branch) bundle.get(Params.Branches.BRANCH_OBJECT);
			Log.d("Branch", branch.getAddress());
		}
//		BranchesDetailsFragment branchesDetailsFrag = new BranchesDetailsFragment();
//		transaction = getSupportFragmentManager().beginTransaction();
//		transaction.add(R.id.container, branchesDetailsFrag);
//		transaction.commit();
//		SetActTitle("Branches Details");
		init();
	}
	public void init(){
		branch_Name= (TextView) findViewById(R.id.branch_Name);
		callTxt          = (TextView) findViewById(R.id.callTxt);
		emailTxt         = (TextView)findViewById(R.id.emailTxt);
		navigationLayout = (RelativeLayout)findViewById(R.id.navigationLyt);
//		googleMap        = ((SupportMapFragment) getFragmentManager().findFragmentById(R.id.branchMap)).getMap();
		
		if(branch!=null)
		{
			branch_Name.setText(branch.getName());
			
		}
		callTxt.setOnClickListener(this);
		emailTxt.setOnClickListener(this);
		navigationLayout.setOnClickListener(this);
	}
	
	/** Callback method for listening onClick view **/
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.callTxt:
				if(!callFlag)
				{
				callFlag=true;
				callTxt.setText(branch.getTelephone());
				}
				else
				{

					callFlag=false;
					callTxt.setText(getString(R.string.call));
				}

				
				break;
			case R.id.emailTxt: 
				if(!emailFlag)
				{
				emailFlag=true;
				emailTxt.setText(branch.getEmail());
				}
				else
				{
					emailFlag=false;
					emailTxt.setText(getString(R.string.email));
				}

				break;
			case R.id.navigationLyt: break;
		}
	}

	/**  Get BranchesDetailsActivity Intent to start it from any activity **/
	public static Intent getActivityIntent(Context context, Branch branchObj) {
		return new Intent(context, BranchesDetailsActivity.class)
		.putExtra(Params.Branches.BRANCH_OBJECT, branchObj);
		
	}
}
