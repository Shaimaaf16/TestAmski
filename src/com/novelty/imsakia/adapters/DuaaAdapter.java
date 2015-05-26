package com.novelty.imsakia.adapters;

import java.util.ArrayList;

import com.novelty.imsakia.R;
import com.novelty.imsakia.dataobjects.DuaaData;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class DuaaAdapter extends BaseAdapter  {
	private ArrayList<DuaaData> dataArray;

	private LayoutInflater inflater;
	Activity activity;
	ItemClicked listener;
	public int count;

	public interface ItemClicked {
		public void onItemClicked(String object);

	}

	public DuaaAdapter(Activity parentActivity, ArrayList<DuaaData> data) {
		dataArray = data;
		activity = parentActivity;
		inflater = (LayoutInflater) parentActivity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return dataArray.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return dataArray.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	TextView  title;

	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {
		View vi = convertView;

		if (convertView == null) {
			vi = inflater.inflate(R.layout.duaa_raw, null);

			title = (TextView) vi.findViewById(R.id.duaaTitle); // title
		}
		DuaaData ob=dataArray.get(position);
		title.setText(ob.getName());
		return vi;
	}

	public void setLisnter(ItemClicked listener) {
		this.listener = listener;
	}

	public ArrayList getData() {
		return dataArray;
	}

}
