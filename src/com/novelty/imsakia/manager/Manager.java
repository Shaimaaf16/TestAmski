/*
 * Manager class is managing the connection between the ( Database | XML-Files ) 
 * and the ( Prayer Model | Setting Screen | Main Screen  )
 *  
 *  
 * these are the Main functionalities: ( #MAIN FUNCTION , %HOW DOES IT WORKS , $METHOD APPEARANCE "SERIALLY" ).
 * 	# Store City-Attributes.
 * 		% By the city-ID -> Gets City Attributes -> Stores the City Data in the XML. 
 * 		$ ( setSetting() -> getData() -> xmlWriter() ).
 * 
 * 	# Calculate Prayer-Times.
 * 		% By Reading the City Attributes -> Runs the Prayer-Model to get Prayer Times. 
 * 		$ ( xmlReader() -> getPrayerTimes() ).
 * 
 * 	# Find out Nearest-Prayer-Time.
 * 		% By Calculating Prayer-Times and Comparing current time with them to get the next one.
 * 		$ ( getPrayerTimes() -> nearestPrayerTime() ).
 * 
 * 	# Copy the Country DataBase to the Device -> TODO MAIN FUNCTION (ABDULLAH).
 * 		% TODO HOW DOES IT WORKS .
 * 		$ TODO METHOD APPEARANCE "SERIALLY" .
 * 
 * 	# Find Current City Location -> TODO MAIN FUNCTION (MOHAMMED).
 * 		% TODO HOW DOES IT WORKS .
 * 		$ TODO METHOD APPEARANCE "SERIALLY" .
 * 
 * #.
 * 		%.
 * 		$.
 */
package com.novelty.imsakia.manager;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

import android.R.integer;
import android.app.Activity;
import android.app.AlarmManager;
import android.app.KeyguardManager;
import android.app.KeyguardManager.KeyguardLock;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.AudioManager;
import android.net.NetworkInfo.State;
import android.net.Uri;
import android.os.PowerManager;
import android.os.PowerManager.WakeLock;
import android.preference.PreferenceManager;
import android.widget.TextView;

import com.novelty.imsakia.AlertActivity;
import com.novelty.imsakia.MainActivity;
import com.novelty.imsakia.dataobjects.salahTime;
import com.novelty.imsakia.helper.TimeHelper;
import com.novelty.imsakia.services.PrayerReceiver;
import com.novelty.imsakia.services.PrayerService;
import com.novelty.imsakia.storage.ServiceStorage;

// Manager is the main class that works as layer  between the app and database/xml files
public class Manager {

	private Context context;
	private static Intent prayerIntet;
	private static PendingIntent prayerPendingIntent;
	private static AlarmManager prayerAlarmManager;
	public static long interval;
	private static PrayerState prayerState;
	private static Service prayerService;
	private static int UNIQUE_ID = 32289;
	public static boolean isPhoneIdle = true;
	public static String NextSalah = "";
	public static String salawat[] = new String[] { "Fajr", "Duhr", "Asr",
			"Maghrib", "Ishaa" };
	public static String salawatTimes[] = new String[5];
	public static String NextSalahTime="";
	public static String RemaningTime="";
	public static String HijryDate;
	public static String MeladyDate;

	public Manager(Context applicationContext) {

		this.context = applicationContext;
	}

	public static void acquireScreen(Context context) {
		PowerManager pm = (PowerManager) context.getApplicationContext()
				.getSystemService(Context.POWER_SERVICE);
		WakeLock wakeLock = pm
				.newWakeLock(
						(PowerManager.SCREEN_BRIGHT_WAKE_LOCK
								| PowerManager.FULL_WAKE_LOCK | PowerManager.ACQUIRE_CAUSES_WAKEUP),
						"TAG");
		wakeLock.acquire();
	}

	public static void releaseScreen(Context context) {
		KeyguardManager keyguardManager = (KeyguardManager) context
				.getApplicationContext().getSystemService(
						Context.KEYGUARD_SERVICE);
		KeyguardLock keyguardLock = keyguardManager.newKeyguardLock("TAG");
		keyguardLock.disableKeyguard();
	}

	public static void initPrayerAlarm(Service service,
			Class<PrayerReceiver> receiver) {
		Manager.prayerService = service; // we may need it ?
		Manager.prayerIntet = new Intent(service, receiver);
		Manager.prayerPendingIntent = PendingIntent
				.getBroadcast(service, 1234432, Manager.prayerIntet,
						PendingIntent.FLAG_UPDATE_CURRENT);
		Manager.prayerAlarmManager = (AlarmManager) service
				.getSystemService(Context.ALARM_SERVICE);
		Manager.prayerAlarmManager.set(AlarmManager.RTC_WAKEUP,
				System.currentTimeMillis() + 1000, Manager.prayerPendingIntent);
	}

	public static void updatePrayerAlarm(long newTimeInterval) {
		Manager.prayerAlarmManager.set(AlarmManager.RTC_WAKEUP,
				System.currentTimeMillis() + newTimeInterval,
				Manager.prayerPendingIntent);
	}

	public static void cancelPrayerAlarm() {
		Manager.prayerAlarmManager.cancel(prayerPendingIntent);
	}

	public static void initPrayerState(Context context) {
		Manager.prayerState = new PrayerState(context);

	}

	public static PrayerState getPrayerState() {
		return prayerState;
	}

	// get nearest prayer time based on current time

	public static int computeNearestPrayerTime(Context context, int hour,
			int min, int sec, int year, int month, int day) throws IOException {
		ArrayList<String> prayerTimes = getPrayerTimes(context, day, month,
				year);
		int[] prayerTimeInSeconds = new int[5];
		Map<Integer, Integer> mappingIdex = new HashMap<Integer, Integer>();
		// Convert prayer times to seconds
		prayerTimeInSeconds[0] = TimeHelper.getSec(
				TimeHelper.to24(prayerTimes.get(0)),
				TimeHelper.getMinute(prayerTimes.get(0)),
				TimeHelper.getSecond(prayerTimes.get(0)));
		prayerTimeInSeconds[1] = TimeHelper.getSec(
				TimeHelper.to24(prayerTimes.get(1)),
				TimeHelper.getMinute(prayerTimes.get(1)),
				TimeHelper.getSecond(prayerTimes.get(1)));
		prayerTimeInSeconds[2] = TimeHelper.getSec(
				TimeHelper.to24(prayerTimes.get(2)),
				TimeHelper.getMinute(prayerTimes.get(2)),
				TimeHelper.getSecond(prayerTimes.get(2)));
		prayerTimeInSeconds[3] = TimeHelper.getSec(
				TimeHelper.to24(prayerTimes.get(3)),
				TimeHelper.getMinute(prayerTimes.get(3)),
				TimeHelper.getSecond(prayerTimes.get(3)));
		prayerTimeInSeconds[4] = TimeHelper.getSec(
				TimeHelper.to24(prayerTimes.get(4)),
				TimeHelper.getMinute(prayerTimes.get(4)),
				TimeHelper.getSecond(prayerTimes.get(4)));
		mappingIdex.put(prayerTimeInSeconds[4], 4);
		mappingIdex.put(prayerTimeInSeconds[3], 3);
		mappingIdex.put(prayerTimeInSeconds[2], 2);
		mappingIdex.put(prayerTimeInSeconds[0], 0);
		mappingIdex.put(prayerTimeInSeconds[1], 1);

		// sort ascending
		Arrays.sort(prayerTimeInSeconds);
		// default value is the first prayer in the day
		int nearestPrayer = prayerTimeInSeconds[0];
		Manager.NextSalah = salawat[mappingIdex.get(nearestPrayer)];
		Manager.NextSalahTime = salawatTimes[mappingIdex.get(nearestPrayer)];

		// convert current time to seconds

		int currentTime = hour * 3600 + min * 60 + sec;

		for (Integer prayertime : prayerTimeInSeconds) {
			int pt = prayertime;
			if (pt >= currentTime)// return first prayer after this time (
			{
				Manager.NextSalah = salawat[mappingIdex.get(prayertime)]; // nearest
				Manager.NextSalahTime = salawatTimes[mappingIdex.get(prayertime)];

				// prayer)
				return pt;
			}
		}
		return nearestPrayer;
	}

	/*
	 * public static int computePreviuosPrayerTime(Context context, int hour,
	 * int min, int sec, int year, int month, int day) throws IOException {
	 * 
	 * ArrayList<String> prayerTimes = getPrayerTimes(context, day, month,
	 * year); Integer[] prayerTimeInSeconds = new Integer[5];
	 * 
	 * // Convert prayer times to seconds prayerTimeInSeconds[0] = new
	 * Integer(TimeHelper.getSec( TimeHelper.to24(prayerTimes.get(0)),
	 * TimeHelper.getMinute(prayerTimes.get(0)),
	 * TimeHelper.getSecond(prayerTimes.get(0)))); prayerTimeInSeconds[1] = new
	 * Integer(TimeHelper.getSec( TimeHelper.to24(prayerTimes.get(1)),
	 * TimeHelper.getMinute(prayerTimes.get(1)),
	 * TimeHelper.getSecond(prayerTimes.get(1)))); prayerTimeInSeconds[2] = new
	 * Integer(TimeHelper.getSec( TimeHelper.to24(prayerTimes.get(2)),
	 * TimeHelper.getMinute(prayerTimes.get(2)),
	 * TimeHelper.getSecond(prayerTimes.get(2)))); prayerTimeInSeconds[3] = new
	 * Integer(TimeHelper.getSec( TimeHelper.to24(prayerTimes.get(3)),
	 * TimeHelper.getMinute(prayerTimes.get(3)),
	 * TimeHelper.getSecond(prayerTimes.get(3)))); prayerTimeInSeconds[4] = new
	 * Integer(TimeHelper.getSec( TimeHelper.to24(prayerTimes.get(4)),
	 * TimeHelper.getMinute(prayerTimes.get(4)),
	 * TimeHelper.getSecond(prayerTimes.get(4))));
	 * 
	 * // sort descending Arrays.sort(prayerTimeInSeconds, new
	 * Comparator<Integer>() {
	 * 
	 * @Override public int compare(Integer lhs, Integer rhs) { return
	 * rhs.compareTo(lhs); } });
	 * 
	 * // default value is the last prayer in the day ( Witch is Isha) //
	 * remember , we sorted it descending int previousTime =
	 * prayerTimeInSeconds[0]; int firstTime = prayerTimeInSeconds[4]; //
	 * convert current time to seconds int currentTime = hour * 3600 + min * 60
	 * + sec; int i=0; for (Integer prayertime : prayerTimeInSeconds) { int pt =
	 * prayertime; i++; // return the last prayer if (pt <= currentTime) return
	 * pt; } // in case if the current time is less then all the prayers time
	 * 
	 * if(i == 5) return firstTime; else return previousTime;
	 * 
	 * }
	 */

	// -----------set method-----------//
	/*
	 * public void setSetting(SettingAttributes sa) { azanAttribute aA =
	 * databaseHelper.getData(sa.city.cityNo); sa.city.latitude = aA.latitude;
	 * sa.city.longitude = aA.longitude; sa.city.timeZone = aA.timeZone;
	 * sa.country.countryNo = Integer.parseInt(aA.countryNo);
	 * this.setSettingAttributes(sa); }
	 * 
	 * // -----------XML methods-----------// public void
	 * setSettingAttributes(SettingAttributes sa) { SharedPreferences pref =
	 * PreferenceManager .getDefaultSharedPreferences(this.context); Editor
	 * editor = pref.edit(); editor.putString("country",
	 * Integer.toString(sa.country.countryNo)); editor.putString("city",
	 * Integer.toString(sa.city.cityNo)); editor.putString("latitude",
	 * sa.city.latitude); editor.putString("longitude", sa.city.longitude);
	 * editor.putString("timeZone", sa.city.timeZone);
	 * editor.putString("isCityChanged", "true"); editor.commit(); }
	 * 
	 * public static SettingAttributes getSettingAttributes(Context context) {
	 * SettingAttributes sa = new SettingAttributes(); SharedPreferences pref =
	 * PreferenceManager .getDefaultSharedPreferences(context); // Mecca values
	 * sa.city.timeZone = pref.getString("timeZone", "3"); sa.city.latitude =
	 * pref.getString("latitude", "21.43"); sa.city.longitude =
	 * pref.getString("longitude", "39.82"); sa.calender =
	 * pref.getString("calendar", "UmmAlQuraUniv"); sa.mazhab =
	 * pref.getString("mazhab", "Default"); sa.season = pref.getString("season",
	 * "Winter"); return sa; }
	 */

	public static ArrayList<String> getPrayerTimes(Context context, int dd,
			int mm, int yy) throws IOException {
		Manager manager = new Manager(context);
		Preference preference = manager.getPreference();
		preference.fetchCurrentPreferences();
		ArrayList<String> prayerList = new ArrayList<String>();
		salahTime salah = ServiceStorage.salawt;

		if (salah != null) {
			prayerList.add(salah.fajrTime().text());
			salawatTimes[0] = salah.getFajr();
			prayerList.add(salah.zuhrTime().text());
			salawatTimes[1] = salah.getDuhr();
			prayerList.add(salah.asrTime().text());
			salawatTimes[2] = salah.getAsr();

			prayerList.add(salah.maghribTime().text());
			salawatTimes[3] = salah.getMaghrib();

			prayerList.add(salah.ishaTime().text());
			salawatTimes[4] = salah.getIsha();
		}
		return prayerList;
	}

	public Context getContext() {
		return context;
	}

	public static void playAzanNotification(Context context) {
		Intent intent;
		SharedPreferences pref = PreferenceManager
				.getDefaultSharedPreferences(context);
		String azanMode = pref.getString("notSound", "short");
		AudioManager am = (AudioManager) context
				.getSystemService(Context.AUDIO_SERVICE);

		if (azanMode.equals("full")
				&& am.getRingerMode() == AudioManager.RINGER_MODE_NORMAL
				&& Manager.isPhoneIdle == true) {
			intent = new Intent(context, AlertActivity.class);
			intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK
					| Intent.FLAG_ACTIVITY_NO_HISTORY);
			intent.putExtra("runFromService", true);
			context.startActivity(intent);
		} else if (!(azanMode.equals("disable"))
				&& (azanMode.equals("short") || (am.getRingerMode() == AudioManager.RINGER_MODE_SILENT || am
						.getRingerMode() == AudioManager.RINGER_MODE_VIBRATE))
				|| Manager.isPhoneIdle == false) {

			// CharSequence contentTitle = context.getString(R.string.notTitle);
			// CharSequence contentText =
			// context.getString(R.string.notContent);
			long when = System.currentTimeMillis();

			NotificationManager mNotificationManager = (NotificationManager) context
					.getSystemService(Context.NOTIFICATION_SERVICE);

			Intent notificationIntent = new Intent(context, MainActivity.class);
			PendingIntent contentIntent = PendingIntent.getActivity(context, 0,
					notificationIntent, 0);

			Notification notification = new Notification(
					com.novelty.imsakia.R.drawable.ic_launcher, "Salahya mosleem",
					when);
			notification.sound = Uri
					.parse("android.resource://com.shefra.prayertimes/raw/majed");
			notification.flags |= Notification.FLAG_AUTO_CANCEL;
			notification.setLatestEventInfo(context, "Al salah",
					"Text ay atext", contentIntent);
			mNotificationManager.notify(UNIQUE_ID, notification);
		}

	}

	public Preference getPreference() {

		return new Preference(this.context);
	}

	// public void updateCity(City city, Activity activity) {
	// Preference pref = this.getPreference();
	// pref.setCityName(city.name);
	// pref.setCityNo(city.id);
	// pref.setCountryName(city.country.name);
	// pref.setCountryNo(city.country.id);
	// pref.setLongitude(city.longitude);
	// pref.setLatitude(city.latitude);
	// pref.setTimeZone(city.timeZone);
	//
	// Manager.cancelPrayerAlarm();
	// Manager.initPrayerState(Manager.prayerService);
	// Manager.initPrayerAlarm(Manager.prayerService,PrayerReceiver.class);
	//
	// }

	// it does not work ?
	public void restartPrayerService(Activity activty) {
		Intent intent = new Intent(activty, PrayerService.class);
		context.startService(intent);
	}

}