package com.novelty.imsakia.activities;

import java.io.File;

import com.novelty.imsakia.R;
import com.novelty.imsakia.BaseActivity;
import com.novelty.imsakia.fragment.TVListFragment;

import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

public class TVActivity extends BaseActivity {
	private FragmentTransaction transaction;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_tv);
		TVListFragment tvListFrag = new TVListFragment();
		transaction = getSupportFragmentManager().beginTransaction();
		transaction.add(R.id.container, tvListFrag);
		transaction.commit();
		setTitle("TV Giude");
	}
}
