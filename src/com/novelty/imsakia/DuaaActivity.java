package com.novelty.imsakia;

import java.util.ArrayList;
import java.util.HashMap;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.Toast;

import com.novelty.imsakia.R;
import com.novelty.imsakia.adapters.DuaaAdapter;
import com.novelty.imsakia.controller.communication.AsyncTaskInvoker;
import com.novelty.imsakia.controller.communication.ConnectionDetector;
import com.novelty.imsakia.controller.communication.DataRequestor;
import com.novelty.imsakia.controller.communication.Task;
import com.novelty.imsakia.controller.communication.Task.TaskID;
import com.novelty.imsakia.dataobjects.DuaaData;
import com.novelty.imsakia.storage.ServiceStorage;
import com.novelty.imsakia.tasks.GetDuaaList;

public class DuaaActivity extends Activity implements DataRequestor {
	ListView duaaList;
	private DuaaAdapter adapter;
	private ProgressDialog mSpinnerProgress;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.duaa_activity);
		init();
		getDuaaList();

	}

	void init() {
		duaaList = (ListView) findViewById(R.id.duaaList);
		duaaList.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1,
					int position, long id) {
				Log.d("Shaimaa", "Id " + position);
				DuaaData clcikedItem = (DuaaData) adapter.getItem(position);
				openDuaaDetail(clcikedItem);

			}
		});

	}

	void getDuaaList() {
		String json = ServiceStorage.loadJSONFromAsset(getApplicationContext(),
				"dua - Qur'an .json");
		JSONObject obj;
		try {
			obj = new JSONObject(json);
			JSONArray m_jArry = obj.getJSONArray("informations");
			ArrayList<DuaaData> duaas = new ArrayList<DuaaData>();
			DuaaData duaa;
			for (int i = 0; i < m_jArry.length(); i++) {
				JSONObject jo_inside = m_jArry.getJSONObject(i);
				duaa=DuaaData.fromJson(jo_inside.toString());
				duaas.add(duaa);
			}
			adapter = new DuaaAdapter(this, duaas);
			duaaList.setAdapter(adapter);

		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void openDuaaDetail(DuaaData clcikedItem) {

		Intent duaaDetail = new Intent(DuaaActivity.this, DuaaDetail.class);
		duaaDetail.putExtra(clcikedItem.getClass().getName().toString(),
				clcikedItem);

		startActivity(duaaDetail);
	}

	@Override
	public void onStart(Task task) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(Task task) {

		if (task.getId() == TaskID.GetDuaaListTask && task.getResult() != null) {
			ArrayList<DuaaData> duaas = (ArrayList<DuaaData>) task.getResult();

			adapter = new DuaaAdapter(this, duaas);
			duaaList.setAdapter(adapter);

		}
		mSpinnerProgress.cancel();
	}

	@Override
	public void handleClick() {
		// TODO Auto-generated method stub

	}

}
