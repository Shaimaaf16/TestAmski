package com.novelty.imsakia.fragment;

import java.util.ArrayList;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.novelty.imsakia.R;
import com.novelty.imsakia.adapters.TVListAdapter;
import com.novelty.imsakia.model.TVModel;

public class TVListFragment extends Fragment{
	private ListView tvListView;
	private TVListAdapter adapter = null;
	private ArrayList<TVModel> tvList = null;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
		View layout = inflater.inflate(R.layout.fragment_tv, container, false);

		tvListView = (ListView) layout.findViewById(R.id.tvListV);
		adapter = new TVListAdapter(getActivity(), tvList);
		tvListView.setAdapter(adapter);
		return layout;
	}

	@Override
	public void onResume() {
		super.onResume();
	}

}
