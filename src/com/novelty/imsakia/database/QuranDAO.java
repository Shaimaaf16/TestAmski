package com.novelty.imsakia.database;

import java.util.ArrayList;

import com.novelty.imsakia.App;
import com.novelty.imsakia.model.SurahAndChapterModel;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;
/**
 * 
 * @author M.Turki
 *
 */
public class QuranDAO {

	private SQLiteDatabase db;
	private static Context context;
	private DatabaseAdapter dbAdapter;
	private static QuranDAO instance;

	public QuranDAO(Context ctx, int database) {
		context = ctx;
		dbAdapter = new DatabaseAdapter(context, database);
	}

	public QuranDAO open() {
		dbAdapter.open();
		db = dbAdapter.getdb();
		return this;
	}

	private QuranDAO() {
		dbAdapter = new DatabaseAdapter(App.getInstance().getApplicationContext(), 2);
	}

	public static QuranDAO getInstance() {
		if (instance == null)
			instance = new QuranDAO();
		return instance;
	}

	public void close() {
		dbAdapter.close();
	}

	public ArrayList<SurahAndChapterModel> GetSurahNameArray(int page_no) {
		ArrayList<SurahAndChapterModel> names = new ArrayList<SurahAndChapterModel>();

		String q_string = "select name from suraTable where pagenumber=" + page_no;
		Cursor c = db.rawQuery(q_string, null);
		if (c.getCount() == 0) {
		} else {
			if (c.moveToFirst()) {
				do {
					SurahAndChapterModel directory = new SurahAndChapterModel();
					directory.setName(c.getString(0));
					names.add(directory);
				} while (c.moveToNext());
			}
		}
		return names;
	}

	public int[] getSurahChapterNum(int pageno) {
		int[] num = new int[2];
		Cursor quranCursor = db.query("ayaTable", new String[] { "suranumber",
				"juznumber" }, "pagenumber" + "=" + pageno, null, null, null,
				"suranumber desc");
		if (quranCursor.moveToFirst()) {
			num[0] = Integer.parseInt(quranCursor.getString(0));
			num[1] = Integer.parseInt(quranCursor.getString(1));

		}
		return num;
	}

	public String getSurahNames(int sora_num) {
		String names = null;
		Cursor quranCursor = db.query("suraTable", new String[] { "name" },	"id" + "=" + sora_num, null, null, null, null);
		if (quranCursor.getCount() == 0) {
		} else {
			if (quranCursor.moveToFirst()) {
				names = quranCursor.getString(0);
			}
			Log.i("NAME", names);
		}
		return names;
	}

	public String getChapterNames(int juz_num) {
		String names = null;
		Cursor quranCursor = db.query("juzTable", new String[] { "name" }, "id"
				+ "=" + juz_num, null, null, null, null);
		if (quranCursor.moveToFirst()) {
			names = quranCursor.getString(0);
			Log.i("QLN", "Juz" + quranCursor.getString(0));
		}
		return names;
	}

	public ArrayList<SurahAndChapterModel> getQuranSura(String table, String id,
			String name, String ayas, String pageNumber) {
		ArrayList<SurahAndChapterModel> quranList = new ArrayList<SurahAndChapterModel>();
		Cursor quranCursor = db.query(table, new String[] { id, name, ayas, pageNumber},
				null, null, null, null, null);
		if (quranCursor.moveToFirst()) {

			do {
				SurahAndChapterModel directory = new SurahAndChapterModel();
				directory.setId(quranCursor.getString(0));
				directory.setName(quranCursor.getString(1));
				directory.setAyas(quranCursor.getString(2));
				directory.setPageNum(quranCursor.getString(3));
				quranList.add(directory);
			} while (quranCursor.moveToNext());
		}
		quranCursor.close();
		return quranList;

	}

	public ArrayList<SurahAndChapterModel> getQuranJuze(String table, String id, String name, String page) {
		ArrayList<SurahAndChapterModel> quranList = new ArrayList<SurahAndChapterModel>();
		Cursor quranCursor = db.query(table, new String[] { id, name, page },
				null, null, null, null, null);
		if (quranCursor.moveToFirst()) {
			do {
				SurahAndChapterModel directory = new SurahAndChapterModel();
				directory.setId(quranCursor.getString(0));
				directory.setName(quranCursor.getString(1));
				directory.setPageNum(quranCursor.getString(2));
				quranList.add(directory);
			} while (quranCursor.moveToNext());
		}
		quranCursor.close();
		return quranList;

	}
}
