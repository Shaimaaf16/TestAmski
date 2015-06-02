package com.novelty.imsakia.adapters;

import java.util.List;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.novelty.imsakia.Application;
import com.novelty.imsakia.R;
import com.novelty.imsakia.model.TVGuidAndProgrameModel;
import com.novelty.imsakia.utils.Params;
import com.novelty.ui.TVProgramsActivity;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TVGuidListAdapter extends BaseAdapter {
	private Context context;
	private List<TVGuidAndProgrameModel> tvGuidList;
	private Typeface tf;
	private ImageLoader mLoader;
	private DisplayImageOptions options;
	private int activityName;

	public TVGuidListAdapter(Context context,List<TVGuidAndProgrameModel> tvGuidList, int activityName) {
		this.context      = context;
		this.tvGuidList   = tvGuidList;
		this.activityName = activityName;
		tf                = Typeface.createFromAsset(context.getAssets(),"fonts/DroidKufi-Bold.ttf");
		mLoader           = Application.getInstance().getImageLoader();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		final TVGuidAndProgrameModel model = tvGuidList.get(position);
		
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.tv_guid_list_item, parent, false);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.tvNameTxt.setTypeface(tf);
		holder.tvCategoryTxt.setTypeface(tf);
		
		if(model.getImage() != null)
			mLoader.displayImage(model.getImage(), holder.tvImageImg, Application.getInstance().getDisplayOption(), new ImageLoadingListener() {
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
		
		if(model.getName() != null)
			holder.tvNameTxt.setText(model.getName());
		else
			holder.tvNameTxt.setVisibility(View.INVISIBLE);
		
		if(model.getDescription() != null)
			holder.tvCategoryTxt.setText(model.getDescription());
		else
			holder.tvNameTxt.setVisibility(View.INVISIBLE);
				
		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				if(activityName == Params.TVGuid.TV_GUID_ACTVITY)
					context.startActivity(TVProgramsActivity.getActivityIntent(context, model));
			}
		});
		
		return convertView;
	}
	
	private static class ViewHolder {
		TextView tvNameTxt, tvCategoryTxt;
		ImageView tvImageImg;
		public ViewHolder(View view) {
			tvNameTxt      = (TextView) view.findViewById(R.id.tvNameTxt);
			tvCategoryTxt  = (TextView) view.findViewById(R.id.tvCategoryTxt);
			tvImageImg     = (ImageView) view.findViewById(R.id.tvImageImg);
		}
	}

	@Override
	public int getCount() {
		return tvGuidList.size();
	}

	@Override
	public Object getItem(int position) {
		return tvGuidList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}
}
