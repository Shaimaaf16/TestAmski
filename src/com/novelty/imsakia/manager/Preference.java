package com.novelty.imsakia.manager;

import java.util.Map;

import com.novelty.imsakia.dataobjects.PrayerTime;
import com.novelty.imsakia.dataobjects.salahTime;
import com.novelty.imsakia.storage.ServiceStorage;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.preference.PreferenceManager;

public class Preference {

	private Context context;
	public String mazhab = null;
	public String calender = null;
	public String season = null;
	public PrayerTime prayerTime = null;
	 public salahTime salawat=null;

	public static String  DEFAULT_COUNTRY_ID   = "211" ; // SA
	public static String  DEFAULT_COUNTRY_NAME = "Saudi Arabia" ; // SA
	public static String  DEFAULT_CITY_ID = "14244";
	public static String  DEFAULT_CITY_NAME = "Makkah";
	public static Integer DEFAULT_TIMEZONE  = 3;
	public static String  DEFAULT_LATITUDE  = "21.4300003051758";
	public static String  DEFAULT_LONGITUDE = "39.8199996948242";
	public static String  DEFAULT_CALENDAR = "UmmAlQuraUniv"; 
	public static Integer DEFAULT_SILENT_DURATION = 20 * 60 * 1000;
	public static Integer DEFAULT_SILENT_START= 2 * 60 * 1000;
	public Preference(Context context) {
		this.context = context;
	}

	public void fetchCurrentPreferences() {

		SharedPreferences preferences = PreferenceManager
				.getDefaultSharedPreferences(context);
//		this.prayerTime = new PrayerTime();
		this.salawat=new salahTime();
		salawat.setAsr((String) preferences.getString("Asr","3:40 pm"));
		salawat.setDuhr((String) preferences.getString("Duhr","12:17 pm"));
		salawat.setIsha((String) preferences.getString("Ishaa","8:05 pm"));
		salawat.setFajr((String) preferences.getString("Fajr","4:30 am"));
		salawat.setShurooq((String) preferences.getString("Shrooq","5:00 am"));
		salawat.setMaghrib((String) preferences.getString("Maghrib","6:00 pm"));

		ServiceStorage.salawt=salawat;
	

//		this.prayerTime.setTitle((String) preferences.getString("cityName",DEFAULT_CITY_NAME));
//		this.prayerTime.id = (String) preferences.getString("cityNo",DEFAULT_CITY_ID);
//		this.prayerTime.country.name = (String) preferences.getString("countryName",DEFAULT_COUNTRY_NAME);
//		this.prayerTime.country.id = (String) preferences.getString("countryNo",DEFAULT_COUNTRY_ID);
//		this.prayerTime.timeZone = (Integer) preferences.getInt("timeZone",DEFAULT_TIMEZONE);
//		this.prayerTime.setlatitude((String) preferences.getString("latitude", DEFAULT_LATITUDE);
//		this.prayerTime.setLongitude((String) preferences.getString("longitude", DEFAULT_LONGITUDE));
//		this.calender = (String) preferences.getString("calendar",DEFAULT_CALENDAR);

	}
	
	public boolean isFirstStart(){
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		boolean firstStart = pref.getBoolean("firstStart",true);
		return firstStart;
	}

	public void setFirstStart(boolean firstStart) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		Editor edit = pref.edit();
    	edit.putBoolean("firstStart", firstStart);
    	edit.commit();		
	}

	public void setCityName(String name) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		Editor edit = pref.edit();
    	edit.putString("cityName", name);
    	edit.commit();			
	}

	public void setLongitude(String longitude) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		Editor edit = pref.edit();
    	edit.putString("longitude", longitude);
    	edit.commit();		
	}

	public void setLatitude(String latitude) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		Editor edit = pref.edit();
    	edit.putString("latitude", latitude);
    	edit.commit();	
	}

	public void setCountryName(String name) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		Editor edit = pref.edit();
    	edit.putString("countryName", name);
    	edit.commit();			
	}
	

	public void setTimeZone(Integer timeZone) {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		Editor edit = pref.edit();
    	edit.putInt("timeZone", timeZone);
    	edit.commit();			
	}

	public int getSilentDuration() {
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		return Integer.parseInt(pref.getString("silentDuration",Integer.toString(DEFAULT_SILENT_DURATION))) * 60 * 1000;
	}

	public void setCityNo(String id) {
		if(id == null)
			return;
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		Editor edit = pref.edit();
    	edit.putString("cityNo", id);
    	edit.commit();			
		
	}
	
	public void setCountryNo(String id) {
		if(id == null)
			return;
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		Editor edit = pref.edit();
    	edit.putString("countryNo", id);
    	edit.commit();			
		
	}
	
	public boolean isAutoSilentDisabled(){
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		return pref.getBoolean("disable",false);
	}
	
	public int getSilentStart(){
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		return Integer.parseInt(pref.getString("silentStart",Integer.toString(DEFAULT_SILENT_START))) * 60 * 1000;		
	}

	public Map<String, ?> getAll(){
		SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(context);
		return pref.getAll();
	}
	
}
