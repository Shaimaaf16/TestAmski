package com.novelty.imsakia.storage;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

import android.content.Context;

import com.novelty.imsakia.dataobjects.Ads;
import com.novelty.imsakia.dataobjects.Branch;
import com.novelty.imsakia.dataobjects.Country;
import com.novelty.imsakia.dataobjects.District;
import com.novelty.imsakia.dataobjects.DuaaData;
import com.novelty.imsakia.dataobjects.PrayerTime;
import com.novelty.imsakia.dataobjects.UserData;
import com.novelty.imsakia.dataobjects.Vendor;
import com.novelty.imsakia.dataobjects.WeatherData;
import com.novelty.imsakia.dataobjects.Zone;
import com.novelty.imsakia.dataobjects.salahTime;
import com.novelty.imsakia.model.TVGuidAndProgrameModel;
import com.novelty.imsakia.model.TVGuidModel;

public class ServiceStorage {
	public static ArrayList<Country> countryList=new ArrayList<Country>();
	public static ArrayList<PrayerTime> PrayerTimes=new ArrayList<PrayerTime>();
	public static salahTime salawt;
	public static UserData currentUser;
	public static ArrayList<Zone> zoneList;
	public static ArrayList<District> districtList;
	public static ArrayList<DuaaData> duaaslist;
	public static ArrayList<Vendor> vendorList;
	public static ArrayList<Ads> adsList;
	public static ArrayList<WeatherData> weatherlist;
	public static ArrayList<Branch> branchList;
	public static ArrayList<TVGuidAndProgrameModel> tvGuidList;
	public static String totalFollowers;
	
	public static String loadJSONFromAsset(Context con,String fileName) {
	    String json = null;
	    try {

	        InputStream is = con.getAssets().open(fileName);

	        int size = is.available();

	        byte[] buffer = new byte[size];

	        is.read(buffer);

	        is.close();

	        json = new String(buffer, "UTF-8");


	    } catch (IOException ex) {
	        ex.printStackTrace();
	        return null;
	    }
	    return json;

	}


}
