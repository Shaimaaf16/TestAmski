package com.novelty.imsakia.controller.communication;


import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;

public class ConnectionDetector {
	
	 /**
     * this class used  to check connection
     * @param constractor  context - activity context
     * 
     * 
     * */
	private static Context _context;
	private static ConnectionDetector instance;
	
    private ConnectionDetector(){

    }
    
    public static ConnectionDetector getInstance(Context context) {
    	_context = context;
    	if(instance == null)
			instance = new ConnectionDetector();
		
		return instance;
	}
 
    
    /**
     * Function to check connection
     
     * @return  boolean
     *       true - if the connection is available and network status (is connected) 
     *       false if the connection is not available and network status (is not connected)
     * */
   
 // Check weather Internet connection is available or not
 	public boolean isConnectingToInternet() {
// 		System.out.println("in checkInternetConnection");
 		final ConnectivityManager conMgr = 
 			(ConnectivityManager)_context.getSystemService(Context.CONNECTIVITY_SERVICE);
 		if (conMgr.getActiveNetworkInfo() != null
 				&& conMgr.getActiveNetworkInfo().isAvailable()
 				&& conMgr.getActiveNetworkInfo().isConnected()) {
// 			System.out.println("in if in checkInternetConnection");
 			return true;
 		} else {
 			System.out.println("Internet Connection Not Present");
 			return false;
 		}
 	}
 	/*==================================================================*/
 	
 	 public static boolean hasConnection() {
 	    ConnectivityManager cm = (ConnectivityManager) _context.getSystemService(
 	        Context.CONNECTIVITY_SERVICE);

 	    NetworkInfo wifiNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_WIFI);
 	    if (wifiNetwork != null && wifiNetwork.isConnected()) {
 	      return true;
 	    }

 	    NetworkInfo mobileNetwork = cm.getNetworkInfo(ConnectivityManager.TYPE_MOBILE);
 	    if (mobileNetwork != null && mobileNetwork.isConnected()) {
 	      return true;
 	    }

 	    NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
 	    if (activeNetwork != null && activeNetwork.isConnected()) {
 	      return true;
 	    }

 	    return false;
 	  }

}
