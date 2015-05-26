package com.novelty.imsakia.fragment;

import java.io.File;

import com.novelty.imsakia.R;
////import com.novelty.imsakia.database.QuranDAO;
//import com.novelty.imsakia.activities.QuraanTabsActivity;
import com.novelty.imsakia.MainActivity;
import com.novelty.imsakia.activities.BranchesActivity;
import com.novelty.imsakia.activities.BranchesDetailsActivity;
import com.novelty.imsakia.activities.TVActivity;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
//
public class MainFragment extends BaseFragment {
//	QuranDAO dbAdapter;
	Button quraan, tv;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,	Bundle savedInstanceState) {
		View rootView = inflater.inflate(R.layout.fragment_main, container, false);
//		dbAdapter     = new QuranDAO(getActivity(), 1);
		quraan = (Button) rootView.findViewById(R.id.button1);
		tv     = (Button) rootView.findViewById(R.id.button2);
		quraan.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
//				dbAdapter.open();
//				System.out.println("DATA BASE"+checkDataBase());
//				startActivity(new Intent(getActivity(), QuraanTabsActivity.class));
//				System.out.println("DATA BASE"+checkDataBase());
			}
		});
		tv.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View arg0) {
				startActivity(BranchesActivity.getActivityIntent(getActivity(), null));
			}
		});
		return rootView;
	}

	public static Fragment newFragment() {
		return new MainFragment();
	}

	private boolean checkDataBase(){
        boolean checkdb = false;
        try{
            String myPath = getActivity().getFilesDir().getAbsolutePath().replace("files", "databases")+File.separator + "ebadatydb.db";
            File dbfile = new File(myPath);                
            checkdb = dbfile.exists();
        }
        catch(SQLiteException e){
            System.out.println("Database doesn't exist");
        }

        return checkdb;
    }
}
