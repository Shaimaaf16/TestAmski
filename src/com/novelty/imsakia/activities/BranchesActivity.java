package com.novelty.imsakia.activities;

import com.novelty.imsakia.R;
import com.novelty.imsakia.BaseActivity;
import com.novelty.imsakia.fragment.BranchesListFragment;
import com.novelty.imsakia.model.BranchesModel;
import com.novelty.imsakia.utils.Params;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

public class BranchesActivity extends BaseActivity {
	private FragmentTransaction transaction;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_branches);
		BranchesListFragment branchesListFrag = new BranchesListFragment();
		transaction = getSupportFragmentManager().beginTransaction();
		transaction.add(R.id.container, branchesListFrag);
		transaction.commit();
		SetActTitle("Branches");
	}
	
	/**  Get BranchesActivity Intent to start it from any activity **/
	public static Intent getActivityIntent(Context context, BranchesModel branchObj) {
		return new Intent(context, BranchesActivity.class);
	}
}
