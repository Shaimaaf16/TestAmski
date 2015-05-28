package com.novelty.imsakia;

import com.parse.Parse;
import com.parse.PushService;

public class Application extends android.app.Application {

  public Application() {
  }

  @Override
  public void onCreate() {
    super.onCreate();

	// Initialize the Parse SDK.

	Parse.initialize(this, "5edWhyc6S6VVJPr8QVNJHHSHmZ2TB6JF9mcMmVnj", "2Nz0xjWLKr5OB5K0cqlE0BLQdJOAblB5B3yvMhg4"); 

 
	// Specify an Activity to handle all pushes by default.
	 PushService.setDefaultPushCallback(this, MainActivity.class);
  }
}