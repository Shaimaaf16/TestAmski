package com.novelty.ui;

import java.util.ArrayList;

import com.novelty.imsakia.R;
import com.novelty.imsakia.adapters.TVProgramsListAdapter;
import com.novelty.imsakia.model.GroupsModel;
import com.novelty.imsakia.model.ProgramModel;
import com.novelty.imsakia.model.SeriesModel;
import com.novelty.imsakia.model.TVGuidAndProgrameModel;
import com.novelty.imsakia.utils.Params;

import android.app.Activity;
import android.app.Dialog;
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
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;


public class TVProgramsActivity extends Activity implements OnClickListener{
	private ListView tvGuidListView;
	private TVGuidAndProgrameModel intentModel;
	private RelativeLayout dropDownMenuRL;
	private TVProgramsListAdapter adapter         = null;
	private ArrayList<ProgramModel> programsList = new ArrayList<ProgramModel>();
	private ArrayList<SeriesModel> seriesList    = new ArrayList<SeriesModel>();
	private TextView channelNameTxt;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_tv_guid_list);

		/** Initialization of views **/
		init();
	}
	
	public void init(){
		
		intentModel  = (TVGuidAndProgrameModel) getIntent().getSerializableExtra(Params.TVGuid.CHANNEL_OBJECT);
		programsList = intentModel.getGroups().getProgram();
		seriesList   = intentModel.getGroups().getSeries();
		
		/** Only for testing TVGuidList listView **/
		tvGuidListView = (ListView) findViewById(R.id.tvGuidListV);
		dropDownMenuRL = (RelativeLayout) findViewById(R.id.dropDownMenuRL);
		dropDownMenuRL.setVisibility(View.VISIBLE);
		channelNameTxt = (TextView) findViewById(R.id.channelNameTxt);
		channelNameTxt.setText(intentModel.getName()+" "+ "Programs");
		
		adapter        = new TVProgramsListAdapter(TVProgramsActivity.this, programsList, Params.TVGuid.TV_GUID_PROGRAMS);
		tvGuidListView.setAdapter(adapter);
		
		dropDownMenuRL.setOnClickListener(this);
	}

	public static Intent getActivityIntent(Context context, TVGuidAndProgrameModel model) {
		return new Intent(context, TVProgramsActivity.class)
		.putExtra(Params.TVGuid.CHANNEL_OBJECT, model);
	}

	/**  Initialize of PhotoList Dialog **/
	private void showProgramListDialog() {

		String[] listIntent = null;
		GroupsModel groupModel = intentModel.getGroups();
		channelNameTxt.setText(intentModel.getName()+" "+ "Programs");
		
		if(groupModel.getProgram() != null && groupModel.getProgram().size() >0 
			&& groupModel.getSeries() != null && groupModel.getSeries().size() >0 )
			listIntent = new String[]{intentModel.getName()+" "+ "Programs" , intentModel.getName()+" "+"Series"};
		else if(groupModel.getProgram() != null && groupModel.getProgram().size() >0)
			listIntent = new String[]{intentModel.getName()+" "+ "Programs"};
		

		final String[] listPrograms = listIntent;
		
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
		ArrayAdapter<String> programsArrayAdapter = new ArrayAdapter<String>(this, 
				R.layout.drop_down_list_item,R.id.tvProgramNameTxt, listIntent);
		listView.setAdapter(programsArrayAdapter);
		
		listView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view,	int position, long id) {
				channelNameTxt.setText(listPrograms[position]);
				if(position == Params.TVGuid.TV_GUID_PROGRAMS)
					adapter  = new TVProgramsListAdapter(TVProgramsActivity.this, programsList, Params.TVGuid.TV_GUID_PROGRAMS);
				else
					adapter  = new TVProgramsListAdapter(TVProgramsActivity.this, seriesList, Params.TVGuid.TV_GUID_SERIES);
				tvGuidListView.setAdapter(adapter);
				dialog.dismiss();
			}
		});

		/** Show dialog **/
		dialog.show();
	}
	
	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.dropDownMenuRL:
			showProgramListDialog();
			break;
		default:
			break;
		}
	}
}
