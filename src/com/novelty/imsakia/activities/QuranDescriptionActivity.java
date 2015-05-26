package com.novelty.imsakia.activities;

import java.util.ArrayList;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.SimpleOnPageChangeListener;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.novelty.imsakia.R;
import com.novelty.imsakia.adapters.ViewPagerAdapter;
import com.novelty.imsakia.database.QuranDAO;
import com.novelty.imsakia.model.SurahAndChapterModel;
import com.novelty.imsakia.utils.Params;

import data.cache.SharedPrefrencesDataLayer;


/**
 * 
 * @author M.Turki
 *
 */
public class QuranDescriptionActivity extends BaseActivity {

	Fragment tempFrag;

	public static String pageNumber, surahName = null, chapterName = null;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		tempFrag = getSupportFragmentManager().findFragmentById(R.id.act_container);
		if (tempFrag == null)
			tempFrag = new QuraanViewFragment();
		AddFragment(tempFrag);
	}

	public static class QuraanViewFragment extends Fragment {
		private ProgressDialog dialog;
		private int imageArra[] = new int[604];
		public static LinearLayout topbar, bottombar;
		private int pageNo;
		private QuranDAO dao, dao1;
		private int[] nums;
		private View layout;
		private StringBuilder newStringSurah = new StringBuilder();
		
		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,	Bundle savedInstanceState) {
			layout     = inflater.inflate(R.layout.activity_quraan_description, container, false);
			pageNumber = getActivity().getIntent().getExtras().getString("pageNumber");
			pageNo     = Integer.parseInt(pageNumber);
			new Loading().execute();
			return layout;
		}

		private class PageListener extends SimpleOnPageChangeListener {
			public void onPageSelected(int position) {
				pageNo = 604 - position;
				dao.open();
				nums = new int[2];
				nums = dao.getSurahChapterNum(pageNo);
				newStringSurah = new StringBuilder();

				ArrayList<SurahAndChapterModel> namesList = dao.GetSurahNameArray(pageNo);
				if (namesList.size() == 0) {
					dao.close();
					dao.open();
					surahName = dao.getSurahNames(nums[0]);
				} else {
					for (int i = 0; i < namesList.size(); i++)
						if(i == namesList.size()-1)
							newStringSurah.append(namesList.get(i).getName());
						else
							newStringSurah.append(namesList.get(i).getName()).append(",");
					surahName = newStringSurah.toString();
				}
				dao1.open();
				chapterName = dao1.getChapterNames(nums[1]);
//				chapterNo = nums[1];
				dao1.close();
				((BaseActivity) getActivity()).SetActTitle(surahName);
			}
		}

		public class Loading extends AsyncTask<Void, Void, Void> {
			@Override
			protected Void doInBackground(Void... params) {
				dao = new QuranDAO(getActivity(), 12);
				dao.open();
				nums = new int[2];
				nums = dao.getSurahChapterNum(pageNo);
				ArrayList<SurahAndChapterModel> namesList = dao.GetSurahNameArray(pageNo);
				if (namesList.size() == 0) {
					dao.close();
					dao.open();
					surahName = dao.getSurahNames(nums[0]);
				} else {
					for (int i = 0; i < namesList.size(); i++)
						if(i == namesList.size()-1)
							newStringSurah.append(namesList.get(i).getName());
						else
							newStringSurah.append(namesList.get(i).getName()).append(",");
					surahName = newStringSurah.toString(); 
				}
				dao.close();
				dao1 = new QuranDAO(getActivity(), 12);
				dao1.open();
				chapterName = dao1.getChapterNames(nums[1]);
//				chapterNo = nums[1];
				dao1.close();
				int size = 604;
				int j    = 604;
				for (int i = 0; i < size; i++) {
					imageArra[i] = getResources().getIdentifier("drawable/q_" + j, null,getActivity().getPackageName());
					j -= 1;
				}
				return null;
			}

			@Override
			protected void onPostExecute(Void result) {
				final ViewPager viewPager = (ViewPager) layout.findViewById(R.id.q_view_pager);
				final int page            = 604 - pageNo;
				final ViewPagerAdapter adapter = new ViewPagerAdapter(getActivity(), imageArra);
				viewPager.setAdapter(adapter);
				getActivity().runOnUiThread(new Runnable() {
					public void run() {
						viewPager.setAdapter(adapter);
						viewPager.setCurrentItem(page);
						((BaseActivity) getActivity()).SetActTitle(surahName);
					}
				});
				PageListener pageListener = new PageListener();
				viewPager.setOnPageChangeListener(pageListener);
				if (dialog != null)
					dialog.dismiss();
			}
		}
	}
	
	@Override
	public void onBackPressed() {
		super.onBackPressed();
		SharedPrefrencesDataLayer.saveStringPreferences(QuranDescriptionActivity.this, 
				Params.Quraan.SURAH_LAST_READ_POSITION, surahName);
	}
}
