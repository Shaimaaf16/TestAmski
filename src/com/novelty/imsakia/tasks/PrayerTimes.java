package com.novelty.imsakia.tasks;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.util.Log;

import com.novelty.imsakia.controller.communication.Communication;
import com.novelty.imsakia.controller.communication.DataRequestor;
import com.novelty.imsakia.controller.communication.RequestHeader;
import com.novelty.imsakia.controller.communication.ResponseObject;
import com.novelty.imsakia.controller.communication.Task;
import com.novelty.imsakia.controller.communication.Task.TaskID;
import com.novelty.imsakia.dataobjects.Country;
import com.novelty.imsakia.dataobjects.PrayerTime;
import com.novelty.imsakia.storage.ServiceStorage;

public class PrayerTimes extends Task {

	private String url;
	Context mxontext;
	ArrayList<PrayerTime> result;
	String httpBody;
	private ResponseObject response;
	public static String CONTENT_TYPE_KEY = "Content-type";
	public static String ACCESS_TOKEN_KEY = "accessToken";
	public static String CONTENT_TYPE_VALUE = "application/x-www-form-urlencoded";
	ArrayList<PrayerTime> prayerTimesList = new ArrayList<PrayerTime>();

	public PrayerTimes(DataRequestor requestor, Context context,String auth,String userId) {
		setRequestor(requestor);
		setId(TaskID.GetPrayerTime);
		this.mxontext = context;
		userId="8";
		auth = "F0IJryhHnTtujwFqoYAApHSSsduYAOg8jYol+FVD1K0=";

		url = Communication.Common_Base_URL + "prayertime";
		String lan = "30.044420";
		String lat = "31.235712";
		httpBody = "{\"UATHT\":\"" + auth + "\",\"UserId\":\"" + userId
				+ "\",\"Latitude\":\"" + lat + "\",\"Longitude\":\"" + lan
				+ "\",\"Method\":\"5\"}";

		// url = url.replaceAll("\\s+", "");

	}

	@Override
	public void execute() {
		// response = (ResponseObject) Communication.getMethod(url,
		// getHeadersList(), mxontext);
		response = (ResponseObject) Communication.postMethodWithBody(url,
				getHeadersList(), httpBody, mxontext);
		System.out.println("url" + url);
		mapServerError(response.getStatusCode());
		String r = response.getResponseString();
		JSONObject mainObject;
		if (response.getStatusCode() == 200) {
			try {
				mainObject = new JSONObject(r);
				JSONArray jsonArray = mainObject.getJSONArray("prayer_times");
				for (int i = 0; i < jsonArray.length(); i++) {

					JSONObject jsonOb = jsonArray.getJSONObject(i);

					PrayerTime time = PrayerTime.fromJson(jsonOb.toString());
					prayerTimesList.add(time);

					result = prayerTimesList;
					Log.d("Shaimaa",
							"prayerTimesList " + prayerTimesList.size());
					ServiceStorage.PrayerTimes = prayerTimesList;
					ServiceStorage.salawt = prayerTimesList.get(0).getPrayers();
				}
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	@Override
	public Object getResult() {
		return result;
	}

	public ArrayList<RequestHeader> getHeadersList() {
		ArrayList<RequestHeader> headers = new ArrayList<RequestHeader>();
		RequestHeader header = new RequestHeader(CONTENT_TYPE_KEY,
				CONTENT_TYPE_VALUE);
		headers.add(header);

		return headers;
	}

}
