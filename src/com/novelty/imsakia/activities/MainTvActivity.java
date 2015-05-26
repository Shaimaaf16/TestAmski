package com.novelty.imsakia.activities;


import java.io.File;

import com.novelty.imsakia.R;
import com.novelty.imsakia.fragment.MainFragment;

import android.database.sqlite.SQLiteException;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;

public class MainTvActivity extends BaseActivity {
	private FragmentTransaction transaction;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		MainFragment mainFrag = new MainFragment();
		transaction = getSupportFragmentManager().beginTransaction();
		transaction.add(R.id.container, mainFrag);
		transaction.commit();
		setTitle("TV Giude");
		
	}
	private boolean checkDataBase(){
        boolean checkdb = false;
        try{
            String myPath = getApplicationContext().getFilesDir().getAbsolutePath().replace("files", "databases")+File.separator + "ebadatydb.db";
            File dbfile = new File(myPath);                
            checkdb = dbfile.exists();
        }
        catch(SQLiteException e){
            System.out.println("Database doesn't exist");
        }

        return checkdb;
    }
}

