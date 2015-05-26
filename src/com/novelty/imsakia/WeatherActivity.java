package com.novelty.imsakia;

import java.util.ArrayList;

import com.novelty.imsakia.R;
import com.novelty.imsakia.dataobjects.WeatherData;
import com.novelty.imsakia.moazn.Date;
import com.novelty.imsakia.storage.ServiceStorage;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.location.Location;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class WeatherActivity extends Activity {

	RelativeLayout weather_row1, weather_row2, weather_row3, weather_row4,
			weather_row5, weather_row6;
	TextView weather_degree, weather_city;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.weather_activity);
		init();
	}

	private void init() {
		weather_row1 = (RelativeLayout) findViewById(R.id.weather_row1);
		weather_row2 = (RelativeLayout) findViewById(R.id.weather_row2);
		weather_row3 = (RelativeLayout) findViewById(R.id.weather_row3);
		weather_row4 = (RelativeLayout) findViewById(R.id.weather_row4);
		weather_row5 = (RelativeLayout) findViewById(R.id.weather_row5);
		weather_row6 = (RelativeLayout) findViewById(R.id.weather_row6);
		weather_degree = (TextView) findViewById(R.id.weather_degree);
		weather_city = (TextView) findViewById(R.id.weather_city);

		if (ServiceStorage.weatherlist != null
				&& ServiceStorage.weatherlist.size() > 0) {
			fillTables(ServiceStorage.weatherlist);
		}
	}

	void fillTables(ArrayList<WeatherData> weatherlist) {

			fillRow(weather_row1, weatherlist.get(0));
			fillRow(weather_row2, weatherlist.get(1));
			fillRow(weather_row3, weatherlist.get(2));
			fillRow(weather_row4, weatherlist.get(3));
			fillRow(weather_row5, weatherlist.get(4));
			fillRow(weather_row6, weatherlist.get(5));




			
		
	}

	void fillRow(RelativeLayout layout, WeatherData data) {

		TextView weather_min, weather_max, day;
		ImageView weather_status;
		weather_min = (TextView) layout.findViewById(R.id.weather_min);
		weather_max = (TextView) layout.findViewById(R.id.weather_max);
		day = (TextView) layout.findViewById(R.id.day);
		weather_status = (ImageView) layout.findViewById(R.id.weather_status);
		if (data != null){
			weather_max.setText(data.getTemp().getMax()+" "+weather_max.getText().toString());
			weather_min.setText(data.getTemp().getMin()+" "+weather_min.getText().toString());
			String weekday=Date.getWeekDay(data.getDate());
			day.setText(weekday);

		}
	}
}
