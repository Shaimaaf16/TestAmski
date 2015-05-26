package com.novelty.imsakia;

import com.parse.Parse;
import com.parse.ParseInstallation;
import com.parse.PushService;

public class Application extends android.app.Application {

  public Application() {
  }

  @Override
  public void onCreate() {
    super.onCreate();

	// Initialize the Parse SDK.
	Parse.initialize(this, "iPqrvS1bimhoBFAAMfkuS5A1CjhCIHrGOJPNZYHx", "DB7yuMSUjCf1dxf5O9ot7yjDtm9wiPwLi2iA4iVs"); 

	// Specify an Activity to handle all pushes by default.
	PushService.setDefaultPushCallback(this, NotificationActivity.class);
  }
}