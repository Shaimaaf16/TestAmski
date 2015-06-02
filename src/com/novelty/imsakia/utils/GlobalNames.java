package com.novelty.imsakia.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

import com.novelty.imsakia.Application;

import android.content.Context;
import android.graphics.Typeface;

public class GlobalNames {
	
	private static final String MY_CITY_FILE_NAME = "my_city_obj_cache";
	public static String PREFS_NAME= "Imskyaa";
	public static int SORA_SECTION = 1;
	public static int JUZE_SECTION = 2;
	public static String LANG      = "lang";

	
	public static void saveObjToInternalStorage(String fileName, Serializable obj) {
	    try {
	    	Context appContext = Application.getInstance().getApplicationContext();
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
			File file = Application.getInstance().getApplicationContext().getFileStreamPath(fileName);
			if (!file.exists())
				return null;
			
	        FileInputStream inputStream = Application.getInstance().getApplicationContext().openFileInput(fileName);
	        ObjectInputStream objectInputStream = new ObjectInputStream(inputStream);
	        return objectInputStream.readObject();
	    } catch (Exception e) {
	        e.printStackTrace();
	    }
		return null;
	}
	
	public static void deleteObjFromStorage(String fileName) {
		Application.getInstance().getApplicationContext().getFileStreamPath(fileName).delete();
	}
	
	public static Typeface getAppTypeFace(Context context) {
		return Typeface.createFromAsset(context.getAssets(),"fonts/DroidKufi-Bold.ttf");
	}
}
