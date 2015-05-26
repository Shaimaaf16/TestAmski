package com.novelty.imsakia.adapters;

import java.util.List;

import com.novelty.imsakia.R;
import com.novelty.imsakia.model.TVModel;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

public class TVListAdapter extends BaseAdapter {

	private Context context;
	private List<TVModel> tvList;
	private Typeface tf;

	public TVListAdapter(Context context,List<TVModel> tvList) {
		this.context = context;
		this.tvList  = tvList;
		tf           = Typeface.createFromAsset(context.getAssets(),"fonts/DroidKufi-Bold.ttf");
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.tv_list_item, parent, false);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
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
		return tvList.size();
	}

	@Override
	public Object getItem(int position) {
		return tvList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}
}
