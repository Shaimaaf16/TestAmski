package com.novelty.imsakia.utils;

import java.text.SimpleDateFormat;
import java.util.Locale;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Build;
import android.text.InputType;
import android.widget.EditText;
import android.widget.Toast;

public class UIUtils {

	public static void showToast(Context context, String message) {
		Toast.makeText(context/*App.getInstance().getApplicationContext()*/, message, Toast.LENGTH_LONG).show();
	}
	
	/**
	 * Disable soft keyboard from appearing, use in conjunction with android:windowSoftInputMode="stateAlwaysHidden|adjustNothing"
	 * @param editText
	 */
	@SuppressLint("NewApi")
	public static void disableSoftInputFromAppearing(EditText editText) {
	    if (Build.VERSION.SDK_INT >= 11) {
	        editText.setRawInputType(InputType.TYPE_CLASS_TEXT);
	        editText.setTextIsSelectable(true);
	    } else {
	        editText.setRawInputType(InputType.TYPE_NULL);
	        editText.setFocusable(true);
	    }
	}

	/**
	 * Provides a formatter object to be used across the application, which uses this pattern <code>yyyy-MM-dd</code>
	 * @return
	 */
	public static SimpleDateFormat getDateFormatter() {
		return new SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH);
	}
	
	
	public static boolean isAlphaSpeed(String name) {
	    char[] chars = name.toCharArray();

	    for (char c : chars) {
	        if(!Character.isLetter(c)) {
	            return false;
	        }
	    }
	    return true;
	}
	

	public static boolean isAlphaSimplicity(String name) {
	    return name.matches("[a-zA-Z]+");
	}
	
	
	public static boolean isFullNameAlphabtic(String name) {
		String str = name;
		for (int i = 0; i < str.length(); i++) {
		   char ch = str.charAt(i);
		   if(Character.isLetter(ch) || Character.isSpaceChar(ch)){
			   continue;
		   }else{
			   return false;
		   }
		}
		return true;
	}	
	
}
