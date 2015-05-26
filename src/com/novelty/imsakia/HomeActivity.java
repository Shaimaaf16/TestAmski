package com.novelty.imsakia;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.Timer;
import java.util.TimerTask;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.util.Log;
import android.widget.ProgressBar;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import com.novelty.imsakia.R;
import com.novelty.imsakia.controller.communication.AsyncTaskInvoker;
import com.novelty.imsakia.controller.communication.ConnectionDetector;
import com.novelty.imsakia.controller.communication.DataRequestor;
import com.novelty.imsakia.controller.communication.Task;
import com.novelty.imsakia.controller.communication.Task.TaskID;
import com.novelty.imsakia.dataobjects.PrayerTime;
import com.novelty.imsakia.dataobjects.UserData;
import com.novelty.imsakia.dataobjects.WeatherData;
import com.novelty.imsakia.dataobjects.WeatherStatus;
import com.novelty.imsakia.dataobjects.salahTime;
import com.novelty.imsakia.helper.Hijri;
import com.novelty.imsakia.helper.TimeHelper;
import com.novelty.imsakia.helper.Typefaces;
import com.novelty.imsakia.manager.Manager;
import com.novelty.imsakia.manager.Preference;
import com.novelty.imsakia.services.ChatHeadService;
import com.novelty.imsakia.storage.ServiceStorage;
import com.novelty.imsakia.tasks.FollowVendor;
import com.novelty.imsakia.tasks.GetAdvertisments;
import com.novelty.imsakia.tasks.GetVendorsList;
import com.novelty.imsakia.tasks.PrayerTimes;
import com.novelty.imsakia.tasks.Weather;

public class HomeActivity extends Activity implements DataRequestor {

	TextView datehegry;
	TextView datemelady;
	TableRow tableRow1;
	TableRow tableRow2;
	TableRow tableRow3;
	TableRow tableRow4;
	TextView nextsalah, remainingTime, timeremaining;
	TextView WeatherDegree;
	TextView lastposition, surah;
	ProgressBar progress;
	TextView remainingTimeValue;
	private ProgressDialog mSpinnerProgress;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home);
		initUI();
		Manager m = new Manager(getApplicationContext());

		// run the app service , read PrayerService for more info
		m.restartPrayerService(this);

		//

	}

	private void initUI() {

		datehegry = (TextView) findViewById(R.id.datehegry);
		datemelady = (TextView) findViewById(R.id.datemelady);
		tableRow1 = (TableRow) findViewById(R.id.tableRow1);
		tableRow2 = (TableRow) findViewById(R.id.tableRow2);
		tableRow3 = (TableRow) findViewById(R.id.tableRow3);
		tableRow4 = (TableRow) findViewById(R.id.tableRow4);
		nextsalah = (TextView) findViewById(R.id.nextsalah);
		remainingTime = (TextView) findViewById(R.id.remainingTime);
		timeremaining = (TextView) findViewById(R.id.timeremaining);
		WeatherDegree = (TextView) findViewById(R.id.WeatherDegree);
		lastposition = (TextView) findViewById(R.id.lastposition);
		surah = (TextView) findViewById(R.id.surah);
		progress = (ProgressBar) findViewById(R.id.progress);
		remainingTimeValue = (TextView) findViewById(R.id.remainingTimeValue);
		getPrayerTimes();

	}

	private void getPrayerTimes() {

		if (ConnectionDetector.getInstance(this).hasConnection()) {
			mSpinnerProgress = new ProgressDialog(HomeActivity.this);
			mSpinnerProgress.setIndeterminate(true);
			mSpinnerProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			mSpinnerProgress.setMessage("Loading ....");
			mSpinnerProgress.show();
			UserData user=ServiceStorage.currentUser;
	//		if(user!=null){
//			String auth=user.getAuth();
//			String userId=user.getUserId();
			Task task = new PrayerTimes(this, this.getApplicationContext(),"","");
			AsyncTaskInvoker.RunTaskInvoker(task);
//			Task  weather=new Weather(this, this.getApplicationContext(), new HashMap<String, String>());
//			AsyncTaskInvoker.RunTaskInvoker(weather);
			Task  weather=new Weather(this, this.getApplicationContext(),new HashMap<String, String>());
			AsyncTaskInvoker.RunTaskInvoker(weather);

			//}//
		}else {
			Toast.makeText(getApplicationContext(), "No internet Connection",
					Toast.LENGTH_LONG).show();
			Manager m = new Manager(getApplicationContext());

			Preference preference = m.getPreference();
			preference.fetchCurrentPreferences();
			if (preference.salawat != null)
				fillPrayerTimes(preference.salawat);
		}

	}

	@Override
	public void onStart(Task task) {
		// TODO Auto-generated method stub

	}

	@Override
	public void onFinish(Task task) {
		Log.d("Finish Task", "Home get ");
		if (task.getId() == TaskID.GetPrayerTime) {
			ArrayList<PrayerTime> list = (ArrayList<PrayerTime>) task
					.getResult();
			if (list != null && list.size() > 0) {
			
				if (list.get(0).getPrayers() != null)

					fillPrayerTimes(list.get(0).getPrayers());
				datemelady.setText(list.get(0).getDay());
				String splitdate[] = list.get(0).getDay().split("-");
				if (splitdate.length > 0) {
					String hijiryDate = Hijri.writeIslamicDate(
							Integer.parseInt(splitdate[0]),
							Integer.parseInt(splitdate[1]),
							Integer.parseInt(splitdate[2]));
					Manager.HijryDate=hijiryDate;
					Manager.MeladyDate=list.get(0).getDay();
					datehegry.setText(hijiryDate);
					Log.d("Date ", hijiryDate);
				}// get current date

			}

			else {
				Log.d("OnFinish Prayer Times", "Error  from server");
				Manager m = new Manager(getApplicationContext());

				Preference preference = m.getPreference();
				preference.fetchCurrentPreferences();
				if (preference.salawat != null)
					fillPrayerTimes(preference.salawat);
			}

		}
		else if(task.getId()==TaskID.Weather)
		{
			Log.d("Waether Finished", "On Finish  weather Task");
			ArrayList<WeatherData> list = (ArrayList<WeatherData>) task.getResult();
			
			if(list!=null&&list.size()>0)
			{
				WeatherData wather_today=list.get(0);
				 WeatherDegree.setText(wather_today.getTemp().getDay()+WeatherDegree.getText().toString());
			}
		}
		mSpinnerProgress.cancel();
	}

	void fillPrayerTimes(salahTime salwat) {

		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(this);
		// this.prayerTime = new PrayerTime();
		Editor editor = preferences.edit();
		editor.putString("Asr", salwat.getAsr());
		editor.putString("Duhr", salwat.getDuhr());
		editor.putString("Ishaa", salwat.getIsha());
		editor.putString("Fajr", salwat.getFajr());
		editor.putString("Shrooq", salwat.getShurooq());
		editor.putString("Maghrib", salwat.getMaghrib());
		editor.commit();

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

		// read Android/Java documentation for more info
		Date date = new Date();
		final int dd = date.getDate();// calendar.get(Calendar.DAY_OF_MONTH);
		final int mm = date.getMonth() + 1;// 7;//calendar.get(Calendar.MONTH+1);
		final int yy = date.getYear() + 1900;// calendar.get(Calendar.YEAR);

		Timer myTimer = new Timer();
		TimerTask scanTask;
		final Handler handler = new Handler();

		scanTask = new TimerTask() {
			public void run() {

				handler.post(new Runnable() {
					public void run() {
						try {
							updateRemainingTime(yy, mm, dd);
						} catch (IOException e) {

						}
					}
				});
			}
		};

		// start the timer
		// 60000 ms == 60 seconds == 1 minutes :)
		myTimer.schedule(scanTask, 0, 60000);

	}

	public void updateRemainingTime(int yy, int mm, int dd) throws IOException {
		Date date = new Date();

		int h = date.getHours();// calendar.get(Calendar.HOUR_OF_DAY);
		int m = date.getMinutes();// calendar.get(Calendar.MINUTE);
		int s = date.getSeconds();// calendar.get(Calendar.SECOND);
		// get remaining text view and change its value to " remaining time)
		TextView remainingTime = (TextView) findViewById(R.id.remainingTime);
		// nearest prayer time ,
		// for example :Asr : 3:10
		// difference : Current time - Asr time == Current Time - 3:10 =
		// remaining time
//		int time = Manager.computeNearestPrayerTime(getApplicationContext(), h,
//				m, s, yy, mm, dd);
//		int def = TimeHelper.different((h * 3600 + m * 60 + s), time);
//		String diff = TimeHelper.secondsToTimeString(def);
//		timeremaining.setText(diff);
//		Manager.RemaningTime = diff;
//		nextsalah.setText(Manager.NextSalah);
		 computeRemainingTimetoIftar(h,m,s);

	}

	// computeRemainingTimetoIftar(h,m,s);

	void computeRemainingTimetoIftar(int h, int m, int s) {
		if (ServiceStorage.salawt != null) {
			String maghrib = ServiceStorage.salawt.maghribTime().text();
			String fajr = ServiceStorage.salawt.fajrTime().text();
			Log.d("Fajaaaaar", fajr + " :: " + maghrib);

			int def = TimeHelper.getSec(TimeHelper.to24(maghrib),
					TimeHelper.getMinute(maghrib),
					TimeHelper.getSecond(maghrib));

			int defFajr = TimeHelper.getSec(TimeHelper.to24(fajr),
					TimeHelper.getMinute(fajr), TimeHelper.getSecond(fajr));

			int currentTime = h * 3600 + m * 60 + s;
			Log.d("Fajaaaaar1", defFajr + " :: " + currentTime);
			Log.d("Fajaaaaar2", def + " :: " + currentTime);

			if (currentTime-defFajr>0&&currentTime-defFajr >= currentTime-def) {
				int df = TimeHelper.different(currentTime,def);
				String diff = TimeHelper.secondsToTimeString(df);


				timeremaining.setText(diff);
				Manager.RemaningTime = diff;
				nextsalah.setText("Maghrib");

			} else {

				int df = TimeHelper.different(currentTime,defFajr);
				String diffFajr = TimeHelper.secondsToTimeString(df);


				timeremaining.setText(diffFajr);
				Manager.RemaningTime = diffFajr;
				nextsalah.setText("Fajar");
			}
		}
	}

	@Override
	public void handleClick() {
		// TODO Auto-generated method stub

	}

	// update the view on resume
	public void onResume() {
		super.onResume();
		this.initUI();
	}

}