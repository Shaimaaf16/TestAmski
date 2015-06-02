package com.novelty.imsakia.adapters;

import java.util.ArrayList;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.assist.FailReason;
import com.nostra13.universalimageloader.core.listener.ImageLoadingListener;
import com.novelty.imsakia.Application;
import com.novelty.imsakia.R;
import com.novelty.imsakia.model.ProgramModel;
import com.novelty.imsakia.model.SeriesModel;
import com.novelty.imsakia.model.TVModel;
import com.novelty.imsakia.utils.Params;
import com.novelty.ui.TVProgramDetailsActivity;

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

public class TVProgramsListAdapter extends BaseAdapter {
	private Context context;
	private Typeface tf;
	private ImageLoader mLoader;
	private DisplayImageOptions options;
	private int groupType;
	private ArrayList<?> list;
	
	public TVProgramsListAdapter(Context context,ArrayList<?> list, int groupType) {
		this.context        = context;
		this.groupType      = groupType;
		this.list           = list;
		tf                  = Typeface.createFromAsset(context.getAssets(),"fonts/DroidKufi-Bold.ttf");
		mLoader             = Application.getInstance().getImageLoader();
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		final TVModel model = new TVModel();
		
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.tv_guid_list_item, parent, false);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.tvNameTxt.setTypeface(tf);
		holder.tvCategoryTxt.setTypeface(tf);

		if(groupType == Params.TVGuid.TV_GUID_PROGRAMS){
			ProgramModel obj = (ProgramModel) list.get(position);
			model.setId(obj.getId());
			model.setDescription(obj.getDescription());
		    model.setName(obj.getName());
		    model.setImage(obj.getImage());
		    model.setShowTime(obj.getShowTime());
		    model.setPresenters(obj.getPresenters());
		}else{
			SeriesModel obj = (SeriesModel) list.get(position);
			model.setId(obj.getId());
			model.setDescription(obj.getDescription());
		    model.setName(obj.getName());
		    model.setImage(obj.getImage());
		    model.setShowTime(obj.getShowTime());
		    model.setPresenters(obj.getPresenters());
		}

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
					context.startActivity(TVProgramDetailsActivity.getActivityIntent(context, model));
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
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		return list.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}
}
