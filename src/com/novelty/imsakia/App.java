package com.novelty.imsakia;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

import com.novelty.imsakia.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.assist.QueueProcessingType;
import com.nostra13.universalimageloader.core.display.FadeInBitmapDisplayer;
import com.nostra13.universalimageloader.utils.L;
import com.parse.Parse;
import com.parse.PushService;

import android.app.Application;
import android.content.Context;
import android.graphics.Bitmap;
import android.os.Handler;
import android.util.Log;

public class App extends Application {

	private static final int THREAD_POOL_SIZE = 3;

	private static App instance;
	private Context mAppContext;

	private ExecutorService mExecutor;
	private Handler mHandler;

	private ImageLoader imageLoader = ImageLoader.getInstance();
	private DisplayImageOptions option;
	
	public static App getInstance() {
		if (instance == null) {
			instance = new App();
		}
		return instance;
	}

	public Context getAppContext() {
		return mAppContext;
	}

	@Override
	public void onCreate() {
		super.onCreate();

		instance    = this;
		mAppContext = getApplicationContext();
		mHandler    = new Handler();
		initImageLoader(getApplicationContext());
		mExecutor = Executors.newFixedThreadPool(THREAD_POOL_SIZE,
				new ThreadFactory() {
					@Override
					public Thread newThread(Runnable r) {
						Thread thread = new Thread(r, "Background executor");
						thread.setPriority(Thread.MIN_PRIORITY);
						thread.setDaemon(true);
						return thread;
					}
				});
		
		// Initialize the Parse SDK.
		Parse.initialize(this, "iPqrvS1bimhoBFAAMfkuS5A1CjhCIHrGOJPNZYHx", "DB7yuMSUjCf1dxf5O9ot7yjDtm9wiPwLi2iA4iVs"); 

		// Specify an Activity to handle all pushes by default.
		PushService.setDefaultPushCallback(this, NotificationActivity.class);
	}

	public void runInBackground(final Runnable runnable) {
		mExecutor.submit(new Runnable() {
			@Override
			public void run() {
				try {
					runnable.run();
				} catch (Exception e) {
					Log.e("Background executor", e.getClass().getName());
					e.printStackTrace();
				}
			}
		});
	}

	public void runOnUiThread(Runnable runnable) {
		mHandler.post(runnable);
	}

	public void initImageLoader(Context context) {
		option = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(R.drawable.img_error)
				.showImageOnFail(R.drawable.img_error)
				.resetViewBeforeLoading(true)
				.cacheOnDisc(true)
				.cacheInMemory(true)
				.imageScaleType(ImageScaleType.EXACTLY)
				.bitmapConfig(Bitmap.Config.RGB_565)
				.considerExifParams(true)
				.displayer(new FadeInBitmapDisplayer(300)).build();
		
		ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context)
				.threadPriority(Thread.NORM_PRIORITY)
				.denyCacheImageMultipleSizesInMemory()
				.tasksProcessingOrder(QueueProcessingType.FIFO)
				.defaultDisplayImageOptions(option)
				.build();
		L.writeLogs(true);
		L.writeDebugLogs(true);
		ImageLoader.getInstance().init(config);
	
	}

	public DisplayImageOptions getDisplayOption() {
		return option;
	}

	public ImageLoader getImageLoader() {
		return imageLoader;
	}
}
