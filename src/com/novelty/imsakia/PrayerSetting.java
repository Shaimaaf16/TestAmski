package com.novelty.imsakia;

import java.util.ArrayList;

import com.novelty.imsakia.R;
import com.novelty.imsakia.adapters.PrayerTimesAdapter;
import com.novelty.imsakia.adapters.PrayerTimesAdapter.ViewHolder;
import com.novelty.imsakia.dataobjects.salahTime;
import com.novelty.imsakia.manager.Manager;
import com.novelty.imsakia.manager.Preference;

import android.app.Activity;
import android.os.Bundle;
import android.view.ViewGroup;
import android.widget.ListView;
import android.widget.TableRow;
import android.widget.TextView;

public class PrayerSetting extends Activity {

	private TableRow tableRow2;
	private TableRow tableRow4;
	private PrayerTimesAdapter adapter;
	private ListView salawatList;
	TextView remaning_time, salah, hour, monthM, month, datenumberM,
			datenumber;
	
	public static ViewHolder Pervious_Holder = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.prayertimes_setting);
		salawatList = (ListView) findViewById(R.id.list);
		remaning_time = (TextView) findViewById(R.id.remaning_time);
		salah = (TextView) findViewById(R.id.salah);
		hour = (TextView) findViewById(R.id.hour);
		month = (TextView) findViewById(R.id.month);
		monthM = (TextView) findViewById(R.id.monthM);
		datenumber = (TextView) findViewById(R.id.datenumber);
		datenumberM = (TextView) findViewById(R.id.datenumberM);
		salah.setText(Manager.NextSalah);
		hour.setText(Manager.NextSalahTime);
		remaning_time.setText(remaning_time.getText()+Manager.RemaningTime);
		if(Manager.MeladyDate!=null&&Manager.MeladyDate.length()>0){
			datenumber.setText(Manager.MeladyDate.split("-")[1]);
			month.setText(Manager.MeladyDate.split("-")[2]);
			datenumberM.setText(Manager.HijryDate.split(" ")[0]);
			monthM.setText(Manager.HijryDate.split(" ")[1]);
		}
		
		ArrayList<String> data = new ArrayList<String>();
		ArrayList<String> values = new ArrayList<String>();

		data.add("Sohor");
		data.add("Imsak");
		data.add("shrooq");
		data.add("Fajr");
		data.add("Duhr");
		data.add("Asr");
		data.add("Maghrib");
		data.add("Ishaa");

		Manager m = new Manager(getApplicationContext());
		Preference preference = m.getPreference();
		preference.fetchCurrentPreferences();
		if (preference.salawat != null) {
			values.add(preference.salawat.getShurooq());// sohour
			values.add(preference.salawat.getShurooq());
			values.add(preference.salawat.getShurooq());
			values.add(preference.salawat.getFajr());
			values.add(preference.salawat.getDuhr());
			values.add(preference.salawat.getAsr());
			values.add(preference.salawat.getMaghrib());
			values.add(preference.salawat.getIsha());
		}
		adapter = new PrayerTimesAdapter(this, data, values);

		salawatList.setAdapter(adapter);

	}

	private void fillPrayerTimes(salahTime salwat) {

		TextView sohorval = (TextView) tableRow2.getChildAt(0);
		TextView imsakVal = (TextView) tableRow2.getChildAt(1);
		TextView fajarVal = (TextView) tableRow2.getChildAt(2);
		TextView shorookVal = (TextView) tableRow2.getChildAt(3);
		fajarVal.setText(salwat.getFajr());
		shorookVal.setText(salwat.getShurooq());

		TextView duhrval = (TextView) tableRow4.getChildAt(0);
		TextView asrVal = (TextView) tableRow4.getChildAt(1);
		TextView maghribval = (TextView) tableRow4.getChildAt(2);
		TextView ishaVal = (TextView) tableRow4.getChildAt(3);
		duhrval.setText(salwat.getDuhr());
		asrVal.setText(salwat.getAsr());
		maghribval.setText(salwat.getMaghrib());
		ishaVal.setText(salwat.getIsha());
		// TODO Auto-generated method stub

	}

}
