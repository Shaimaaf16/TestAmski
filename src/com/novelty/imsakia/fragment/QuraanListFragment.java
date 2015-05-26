package com.novelty.imsakia.fragment;
//package com.novelty.imsakia.fragment;
//
//import java.util.ArrayList;
//import java.util.List;
//
//import android.app.ProgressDialog;
//import android.content.Intent;
//import android.os.AsyncTask;
//import android.os.Bundle;
//import android.support.v4.app.Fragment;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//import android.widget.AdapterView;
//import android.widget.AdapterView.OnItemClickListener;
//import android.widget.ListView;
//
//import com.novelty.imsakia.R;
//import com.novelty.imsakia.database.QuranDAO;
//import com.novelty.imsakia.model.SuraAndJuzaModel;
//import com.novelty.imsakia.activities.QuranDescriptionActivity;
//import com.novelty.imsakia.adapters.QuraanListAdapter;
//
//public class QuraanListFragment extends Fragment {
//
//
//	private ListView QuraanListView;
//	private int section;
//	private ProgressDialog dialog;
//	private QuraanListAdapter adapter             = null;
//	private ArrayList<SuraAndJuzaModel> quranList = null;
//	private List<String> mainList                 = null;
//	
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container,	Bundle savedInstanceState) {
//		
//		View layout=inflater.inflate(R.layout.fragment_quraan, container,false);
//		
//		QuraanListView=(ListView)layout.findViewById(R.id.quraanListV);
//		QuraanListView.setOnItemClickListener(new OnItemClickListener() {
//			@Override
//			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,long arg3) {
//				Intent intent = new Intent(getActivity(), QuranDescriptionActivity.class);
//				intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
//				intent.putExtra("pageNumber", quranList.get(arg2).getPageNum());
//				startActivity(intent);
//			}
//		});
//		return layout;
//	}
//	
//	@Override
//	public void onResume() {
//		super.onResume();
//		section=getArguments().getInt("type");
//		new  QuranDataTask().execute();
//	}
//	
//	public class QuranDataTask extends AsyncTask<Void, Void, Object> {
//		@Override
//		protected Object doInBackground(Void... arg0) {
//			QuranDAO quranDAO = new QuranDAO(getActivity(), 1);
//			quranDAO.open();
//			switch (section) {
//			case 1:   
//				quranList = quranDAO.getQuranSura("suraTable", "id", "name","ayas");
//				break;
//			case 2: 
//				quranList = quranDAO.getQuranJuze("juzTable",  "id", "name","pagenumber");
//				break;
//			default:
//				break;
//			}
//			quranDAO.close();
//			return null;
//		}
//
//		@Override
//		protected void onPostExecute(Object result) {
//			if (dialog != null)
//				dialog.dismiss(); 
//			mainList = new ArrayList<String>();
//			if (quranList.size() != 0) {
//				for (int i = 0; i < quranList.size(); i++) {
//						mainList.add(quranList.get(i).getId()+" "+quranList.get(i).getName());
//				}
//				adapter = new QuraanListAdapter(getActivity(), quranList, section);
//				QuraanListView.setAdapter(adapter);
//			}
//		}
//	}
//}
