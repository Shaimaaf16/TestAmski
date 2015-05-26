package com.novelty.imsakia.adapters;

import java.util.List;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.novelty.imsakia.R;
import com.novelty.imsakia.activities.QuranDescriptionActivity;
import com.novelty.imsakia.model.SurahAndChapterModel;
import com.novelty.imsakia.utils.Params;
/**
 * 
 * @author M.Turki
 *
 */
public class QuraanListAdapter extends BaseAdapter {

	private Context context;
	private List<SurahAndChapterModel> QList;
	private SharedPreferences prefs;
	private String[] arabicChars;
	private int sectionType;
	private Typeface tf;

	public QuraanListAdapter(Context context,List<SurahAndChapterModel> QList, int sectionType) {
		this.context     = context;
		this.QList       = QList;
		this.sectionType = sectionType;
		
		prefs        = context.getSharedPreferences(Params.Common.PREFS_NAME,context.MODE_PRIVATE);
		arabicChars  = context.getResources().getStringArray(R.array.arabic_num);
		tf           = Typeface.createFromAsset(context.getAssets(),"fonts/DroidKufi-Bold.ttf");
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		final ViewHolder holder;
		final SurahAndChapterModel object = (SurahAndChapterModel) getItem(position);
		String soraStr = object.getId()+" "+object.getName();
		String ayasStr  = context.getResources().getString(R.string.quran_pages_number)+" "+object.getAyas();
		
		if (convertView == null) {
			convertView = LayoutInflater.from(context).inflate(R.layout.quraan_list_item, parent, false);
			holder = new ViewHolder(convertView);
			convertView.setTag(holder);
		} else {
			holder = (ViewHolder) convertView.getTag();
		}
		
		holder.quranNameTxt.setTypeface(tf);
		holder.quranDescTxt.setTypeface(tf);
		
		if (prefs.getInt(Params.Common.LANG, 1) != 2) {
			StringBuilder builder = new StringBuilder();
			for (int i = 0; i < soraStr.length(); i++) {
				if (Character.isDigit(soraStr.charAt(i)))
					builder.append(arabicChars[(int) (soraStr.charAt(i)) - 48]);
				else
					builder.append(soraStr.charAt(i));
			}
			holder.quranNameTxt.setText(builder.toString());
		} else {
			holder.quranNameTxt.setText(soraStr.trim());
		}
		

		if(sectionType == Params.Quraan.SORA_SECTION){
			holder.quranDescTxt.setVisibility(View.VISIBLE);
			StringBuilder builder = new StringBuilder(15);
			for (int i = 0; i < ayasStr.length(); i++) {
				if (Character.isDigit(ayasStr.charAt(i)))
					builder.append(arabicChars[(int) (ayasStr.charAt(i)) - 48]);
				else
					builder.append(ayasStr.charAt(i));
			}
			holder.quranDescTxt.setText(builder.toString());
		}
		else
			holder.quranDescTxt.setVisibility(View.GONE);
		
		convertView.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(context, QuranDescriptionActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.putExtra("pageNumber", object.getPageNum());
				context.startActivity(intent);
			}
		});
		
		return convertView;
	}
	
	private static class ViewHolder {
		TextView quranNameTxt,quranDescTxt;
		public ViewHolder(View view) {
			quranNameTxt    = (TextView) view.findViewById(R.id.quranNameTxt);
			quranDescTxt    = (TextView) view.findViewById(R.id.quranDescTxt);
		}
	}

	@Override
	public int getCount() {
		return QList.size();
	}

	@Override
	public Object getItem(int position) {
		return QList.get(position);
	}

	@Override
	public long getItemId(int position) {
		return 0;
	}
}
