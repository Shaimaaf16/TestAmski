package com.novelty.imsakia.adapters;

import java.util.List;

import com.novelty.imsakia.R;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.assist.ImageScaleType;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.novelty.imsakia.App;
import com.novelty.imsakia.activities.BranchesDetailsActivity;
import com.novelty.imsakia.dataobjects.Branch;
import com.novelty.imsakia.model.BranchesModel;

import android.content.Context;
import android.graphics.Bitmap;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class BranchesListAdapter extends BaseAdapter {

	private Context context;
	private List<Branch> branchesList;
	private ImageLoader loader;
	private DisplayImageOptions options;
	 Branch branchObj;

	public BranchesListAdapter(Context context,List<Branch> branchesList) {
		this.context       = context;
		this.branchesList  = branchesList;
		loader = App.getInstance().getImageLoader();
		
		/** Initialize Options to display RoundedImage **/
		options = new DisplayImageOptions.Builder()
				.showImageForEmptyUri(R.drawable.img_error)
				.showImageOnFail(R.drawable.img_error)
				.showImageOnLoading(R.drawable.img_error)
				.resetViewBeforeLoading(true)
				.cacheOnDisc(true)
				.cacheInMemory(true)
				.imageScaleType(ImageScaleType.EXACTLY)
				.bitmapConfig(Bitmap.Config.RGB_565)
				.considerExifParams(true)
				.displayer(new RoundedBitmapDisplayer(1000))
				.build();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		branchObj = branchesList.get(position); 
				
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.branches_list_item, parent, false);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		if (branchObj.getName() != null) {
			holder.branchNameTxt.setText(branchObj.getName());
		} 	
		
		if (branchObj.getAddress() != null) {
			holder.branchAddressTxt.setText(branchObj.getAddress());
		} 
		
			loader.displayImage("", holder.branchImageImg, options, new ImageLoadingListener() {
				@Override
				public void onLoadingStarted(String arg0, View arg1) {
					// TODO Auto-generated method stub
				}
				
				@Override
				public void onLoadingFailed(String arg0, View arg1, FailReason arg2) {
					// TODO Auto-generated method stub
				}
				
				@Override
				public void onLoadingComplete(String arg0, View arg1, Bitmap arg2) {
					// TODO Auto-generated method stub
				}
				
				@Override
				public void onLoadingCancelled(String arg0, View arg1) {
					// TODO Auto-generated method stub
				}
			});
		
		/** onClick to open DetailsBranchActivity **/
		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(branchObj!=null)
				{
				Log.d("OnClick ", branchObj.getAddress());
				context.startActivity(BranchesDetailsActivity.getActivityIntent(context, branchObj));
				}
			}
		});
		
		return convertView;
	}
	
	private static class ViewHolder {
		TextView branchNameTxt, branchAddressTxt;
		ImageView branchImageImg;
		public ViewHolder(View view) {
			branchNameTxt      = (TextView) view.findViewById(R.id.branchNameTxt);
			branchAddressTxt   = (TextView) view.findViewById(R.id.branchAddressTxt);
			branchImageImg     = (ImageView) view.findViewById(R.id.branchImageImg);
		}
	}

	@Override
	public int getCount() {
		return branchesList.size();
	}

	@Override
	public Object getItem(int position) {
		return branchesList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}
}
