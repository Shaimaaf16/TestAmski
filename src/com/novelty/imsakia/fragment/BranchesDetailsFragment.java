package com.novelty.imsakia.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;

//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.SupportMapFragment;
import com.novelty.imsakia.R;

public class BranchesDetailsFragment extends Fragment implements OnClickListener{

	private TextView callTxt, emailTxt;
	private RelativeLayout navigationLayout;
//	private GoogleMap googleMap; 
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.fragment_branches_details, container, false);
		
		/** Initialization of views **/
		init(layout);
		
		return layout;
	}

	public void init(View view){
		callTxt          = (TextView) view.findViewById(R.id.callTxt);
		emailTxt         = (TextView) view.findViewById(R.id.emailTxt);
		navigationLayout = (RelativeLayout) view.findViewById(R.id.navigationLyt);
//		googleMap        = ((SupportMapFragment) getFragmentManager().findFragmentById(R.id.branchMap)).getMap();
		
		callTxt.setOnClickListener(this);
		emailTxt.setOnClickListener(this);
		navigationLayout.setOnClickListener(this);
	}
	
	/** Callback method for listening onClick view **/
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
			case R.id.callTxt: break;
			case R.id.emailTxt: break;
			case R.id.navigationLyt: break;
		}
	}
	
}