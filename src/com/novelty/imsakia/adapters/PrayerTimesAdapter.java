package com.novelty.imsakia.adapters;

import java.util.ArrayList;

import com.novelty.imsakia.R;
import com.novelty.imsakia.PrayerSetting;
import com.novelty.imsakia.dataobjects.salahTime;
import com.novelty.imsakia.manager.Manager;

import android.app.Activity;
import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

public class PrayerTimesAdapter extends BaseAdapter implements OnClickListener {
	private ArrayList<String> dataArray;
	private ArrayList<String> valuesArray;
	private LayoutInflater inflater;
	Activity activity;
	public int count;
	int xml_type;

	public PrayerTimesAdapter(Activity parentActivity, ArrayList<String> data,
			ArrayList<String> values) {
		dataArray = data;
		valuesArray = values;
		activity = parentActivity;
		inflater = (LayoutInflater) parentActivity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	}

	private int soundSelectedOrNot;
	private int vibrateSelectedOrNot;

	private int muteSelectedOrNot;
	ViewHolder holder;

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) activity
				.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		String next = Manager.NextSalah;
		String namestr = dataArray.get(position);

		if (convertView == null) {
			holder = new ViewHolder();

			if (position % 2 == 0)
				convertView = inflater.inflate(R.layout.row_one, parent, false);
			else
				convertView = inflater.inflate(R.layout.row_tow, parent, false);
			if (next.equalsIgnoreCase(namestr))
			{
				convertView = inflater.inflate(R.layout.row_next_pray, parent,false);
			}

			holder.name = (TextView) convertView.findViewById(R.id.salahname);
			holder.time = (TextView) convertView.findViewById(R.id.salahtime);
			holder.sound = (ImageView) convertView.findViewById(R.id.sound);
			holder.sound.setOnClickListener(this);
			holder.sound.setTag(holder);

			holder.vibrate = (ImageView) convertView.findViewById(R.id.vibrate);
			holder.vibrate.setOnClickListener(this);
			holder.vibrate.setTag(holder);

		

			holder.mute = (ImageView) convertView.findViewById(R.id.mute);
			holder.mute.setOnClickListener(this);
			holder.mute.setTag(holder);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}

		holder.name.setText(namestr);
		holder.time.setText(valuesArray.get(position));
		return convertView;

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

	@Override
	public void onClick(View v) {
		ViewHolder holder = (ViewHolder) v.getTag();

		if (v == holder.sound) {
			if(holder.soundSelected==1)
			{
			
			holder.sound.setImageResource(R.drawable.sound_selected);
			holder.soundSelected=0;
			}
			else
			{
				holder.soundSelected=1;
				holder.sound.setImageResource(R.drawable.sound_normal);

			}
		} else if (v == holder.vibrate) {
			Log.d("Vibrate", "Selection " + vibrateSelectedOrNot);
			if(holder.vibrateselected==1)
			{
				holder.vibrate.setImageResource(R.drawable.vibrate_selected);
				holder.vibrateselected=0;	
			}
			else
			{
				holder.vibrate.setImageResource(R.drawable.vibrate_normal);
				holder.vibrateselected=1;
			}

		} else if (v == holder.mute) {
			Log.d("Mute", "Selection " + muteSelectedOrNot);
			if(holder.muteselected==1)
			{
				holder.mute.setImageResource(R.drawable.mute_selected);
				holder.muteselected=0;	
			}
			else
			{
				holder.mute.setImageResource(R.drawable.mute_normal);
				holder.muteselected=1;	
			}

		}



		PrayerSetting.Pervious_Holder = holder;

	}

	public static class ViewHolder {

		public TextView time, name;
		public ImageView mute;
		public ImageView sound;
		public ImageView vibrate;
		int soundSelected=0;
		int vibrateselected=0;
		int muteselected=0;

	}

}
