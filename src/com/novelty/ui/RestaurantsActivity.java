package com.novelty.ui;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.novelty.slider.library.Animations.DescriptionAnimation;
import com.novelty.slider.library.SliderLayout;
import com.novelty.slider.library.SliderTypes.BaseSliderView;
import com.novelty.slider.library.SliderTypes.TextSliderView;
import com.novelty.slider.library.Tricks.ViewPagerEx;
import com.novelty.imsakia.R;
import com.novelty.imsakia.adapters.TVGuidListAdapter;
import com.novelty.imsakia.controller.communication.AsyncTaskInvoker;
import com.novelty.imsakia.controller.communication.ConnectionDetector;
import com.novelty.imsakia.controller.communication.DataRequestor;
import com.novelty.imsakia.controller.communication.Task;
import com.novelty.imsakia.controller.communication.Task.TaskID;
import com.novelty.imsakia.dataobjects.Vendor;
import com.novelty.imsakia.dataobjects.VendorImage;
import com.novelty.imsakia.model.TVGuidAndProgrameModel;
import com.novelty.imsakia.tasks.GetTVGuidList;
import com.novelty.imsakia.tasks.GetVendorsList;
import com.novelty.imsakia.utils.Params;
import com.novelty.imsakia.utils.UIUtils;

import java.util.ArrayList;
import java.util.HashMap;


public class RestaurantsActivity extends Activity implements OnClickListener, DataRequestor,
								BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener{

    private SliderLayout mSlider;
    private TextView descriptionTxt, showAllBranchesTxt, showMenuTxt;
    private ImageView menuImg;
	private ProgressDialog mSpinnerProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_restaurants);
        
        init();
    }

    private void init() {
    	mSlider            = (SliderLayout)findViewById(R.id.slider);
        descriptionTxt     = (TextView)findViewById(R.id.descriptionTxt);
        showAllBranchesTxt = (TextView)findViewById(R.id.showAllBranchesTxt);
        showMenuTxt        = (TextView)findViewById(R.id.showMenuTxt);
        menuImg            = (ImageView)findViewById(R.id.menuImg);
        showAllBranchesTxt.setOnClickListener(this);
        showMenuTxt.setOnClickListener(this);
        
      //TODO please remove statement number 1 this is used for test Only
        setSlider(testData());                                           // 1
        
      //TODO please Un-comment statement number 2 to call VendorList web Service
        /* getVendorList(); */                                           // 2
	}

    private void setSlider(HashMap<String,String> imageMap) {
    	for(String name : imageMap.keySet()){
            TextSliderView textSliderView = new TextSliderView(this);
            // initialize a SliderLayout
            textSliderView
                    .description(name)
                    .image(imageMap.get(name))
                    .setScaleType(BaseSliderView.ScaleType.Fit)
                    .setOnSliderClickListener(this);

            //add your extra information
            textSliderView.bundle(new Bundle());
            textSliderView.getBundle().putString("extra",name);

            mSlider.addSlider(textSliderView);
        }
        
        mSlider.setPresetTransformer(SliderLayout.Transformer.CubeIn);
        mSlider.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        mSlider.setCustomAnimation(new DescriptionAnimation());
        mSlider.setDuration(4000);
        mSlider.addOnPageChangeListener(this);			
	}
  
	private HashMap<String,String> testData(){
        HashMap<String,String> urlMaps = new HashMap<String, String>();
        urlMaps.put("Hannibal"       , "http://static2.hypable.com/wp-content/uploads/2013/12/hannibal-season-2-release-date.jpg");
        urlMaps.put("Big Bang Theory", "http://tvfiles.alphacoders.com/100/hdclearart-10.png");
        urlMaps.put("House of Cards" , "http://cdn3.nflximg.net/images/3093/2043093.jpg");
        urlMaps.put("Game of Thrones", "http://images.boomsbeat.com/data/images/full/19640/game-of-thrones-season-4-jpg.jpg");

        HashMap<String,Integer> fileMaps = new HashMap<String, Integer>();
        fileMaps.put("Channel One CBC"  , R.drawable.channel3_ico);
        fileMaps.put("Channel Two CBC"  , R.drawable.channel2_ico);
        fileMaps.put("Channel Three CBC", R.drawable.channel_ico);
        fileMaps.put("Channel Four CBC" , R.drawable.channel2_ico);
        
        return urlMaps;
    }
	
	public void getVendorList() {
		if (ConnectionDetector.getInstance(this).hasConnection()) {
			mSpinnerProgress = new ProgressDialog(this);
			mSpinnerProgress.setIndeterminate(true);
			mSpinnerProgress.setProgressStyle(ProgressDialog.STYLE_SPINNER);
			mSpinnerProgress.setMessage("Loading ....");
			mSpinnerProgress.show();
			Task task = new GetVendorsList(this, this.getApplicationContext());
			AsyncTaskInvoker.RunTaskInvoker(task);
		} else {
			UIUtils.showToast(getApplicationContext(), "No internet Connection");
		}
	}
	
	public static Intent getActivityIntent(Context context) {
		return new Intent(context, RestaurantsActivity.class);
	}
    
    @Override
    protected void onStop() {
        // To prevent a memory leak on rotation, make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
    	mSlider.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Toast.makeText(this,slider.getBundle().get("extra") + "",Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {}

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Demo", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {}

	@Override
	public void onClick(View view) {
		switch (view.getId()) {
		case R.id.showMenuTxt:
			
			break;
		case R.id.showAllBranchesTxt:
			
			break;
		default:
			break;
		}
	}

	@Override
	public void onStart(Task task) {
		// TODO Auto-generated method stub
	}

	@Override
	public void onFinish(Task task) {
		if (task.getId() == TaskID.GetVendors && task.getResult() != null) {
			ArrayList<Vendor> model = (ArrayList<Vendor>) task.getResult();
			descriptionTxt.setText(model.get(0).getDescription());
			ArrayList<VendorImage> images = model.get(0).getImages();
			HashMap<String,String> urlMaps = new HashMap<String, String>();
			if(images != null)
				for(int i=0; i< images.size(); i++){
			        urlMaps.put(model.get(0).getName() +"Image "+i+1 , images.get(i).getPath());
				}
			setSlider(urlMaps);
		}
		mSpinnerProgress.cancel();
	}

	@Override
	public void handleClick() {
		// TODO Auto-generated method stub
	}
}
