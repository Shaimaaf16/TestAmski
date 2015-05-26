package com.novelty.imsakia.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.novelty.imsakia.App;

import android.content.Context;
import android.graphics.Typeface;

public class GlobalNames {
	
	private static final String MY_CITY_FILE_NAME = "my_city_obj_cache";
	
	public static String PREFS_NAME="hajji";
	public static String LANG="lang";
	public static String NOTIF="notification";
	public static String GPS_ENABLE="gps";
    public static String IS_AUTO_DETECT="auto_detect";
    public static String LANGITUDE="langitude";
    public static String LATITUDE="lat";
	public static int ar=1;
	public static int en=2;
	public static int aurd=3;
	public static int SORA_SECTION =1;
	public static int JUZE_SECTION =2;
	public static String serverURL="http://api.tawasoldev.com/hajji/index.php/api/hajji/";
	public static String track_me= serverURL+"chat/track_me";
	public static String user_locations=serverURL+"locations";
	public static String CALC_METHOD = "calc_method_key";
	public static String ASR_METHOD = "asr_method_key";
	public static String LAST_NOTIFICATION_ID = "last_notification_id";

	
	public static void saveObjToInternalStorage(String fileName, Serializable obj) {
	    try {
	    	Context appContext = App.getInstance().getApplicationContext();
	    	File file = appContext.getFileStreamPath(fileName);
	    	if (file.exists())
	    		file.delete();
	    	
	        FileOutputStream outputStream = appContext.openFileOutput(fileName, Context.MODE_PRIVATE);
	        ObjectOutputStream objOutStream = new ObjectOutputStream(outputStream);
	        objOutStream.writeObject(obj);
	        objOutStream.close();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
	}
	
	public static Object loadObjFromInternalStorage(String fileName) {
		try {
			File file = App.getInstance().getApplicationContext().getFileStreamPath(fileName);
			if (!file.exists())
				return null;
			
	        FileInputStream inputStream = App.getInstance().getApplicationContext().openFileInput(fileName);
	        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
	        return objectInputStream.readObject();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return null;
	}
	
	public static void deleteObjFromStorage(String fileName) {
		App.getInstance().getApplicationContext().getFileStreamPath(fileName).delete();
	}
	
	public static Typeface getAppTypeFace(Context context) {
		return Typeface.createFromAsset(context.getAssets(),"fonts/DroidKufi-Bold.ttf");
	}
}
