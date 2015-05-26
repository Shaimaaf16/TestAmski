package com.novelty.imsakia.activities;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;

import com.novelty.imsakia.R;
import com.novelty.imsakia.adapters.QuraanListAdapter;
import com.novelty.imsakia.database.QuranDAO;
import com.novelty.imsakia.model.SurahAndChapterModel;
import com.novelty.imsakia.utils.Params;

/**
 * 
 * @author M.Turki
 *
 */
public class SurahListActivity extends Activity{
	private ListView QuraanListView;
	private ProgressDialog dialog;
	private QuraanListAdapter adapter             = null;
	private ArrayList<SurahAndChapterModel> quranList = null;
	private List<String> mainList                 = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fragment_quraan);
		init();
		new  QuranDataTask().execute();
	}

	void init() {
		QuraanListView=(ListView) findViewById(R.id.quraanListV);
		QuraanListView.setOnItemClickListener(new OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> arg0, View view, int position,long arg3) {
				Intent intent = new Intent(SurahListActivity.this, QuranDescriptionActivity.class);
				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
				intent.putExtra("pageNumber", quranList.get(position).getPageNum());
				startActivity(intent);
			}
		});
	}

	public class QuranDataTask extends AsyncTask<Void, Void, Object> {
		@Override
		protected Object doInBackground(Void... arg0) {
			QuranDAO quranDAO = new QuranDAO(SurahListActivity.this, 1);
			quranDAO.open();
			quranList = quranDAO.getQuranSura("suraTable", "id", "name","ayas","pagenumber");
			quranDAO.close();
			return null;
		}

		@Override
		protected void onPostExecute(Object result) {
			if (dialog != null)
				dialog.dismiss(); 
			mainList = new ArrayList<String>();
			if (quranList.size() != 0) {
				for (int i = 0; i < quranList.size(); i++) {
						mainList.add(quranList.get(i).getId()+" "+quranList.get(i).getName());
				}
				adapter = new QuraanListAdapter(SurahListActivity.this, quranList, Params.Quraan.SORA_SECTION);
				QuraanListView.setAdapter(adapter);
			}
		}
	}
}
