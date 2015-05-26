package com.novelty.imsakia.tasks;

import java.util.ArrayList;
import java.util.Map;

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
import com.novelty.imsakia.dataobjects.UserData;
import com.novelty.imsakia.dataobjects.WeatherData;
import com.novelty.imsakia.storage.ServiceStorage;

public class Weather extends Task {
	private Context mxontext;
	private String url;
	private String httpBody;
	private ResponseObject response;
	private ArrayList<WeatherData> result;
	public static String CONTENT_TYPE_KEY = "Content-type";
	public static String ACCESS_TOKEN_KEY = "accessToken";
	public static String CONTENT_TYPE_VALUE = "application/x-www-form-urlencoded";
	ArrayList<WeatherData> weatherlist = new ArrayList<WeatherData>();

	public Weather(DataRequestor requestor, Context context,
			Map<String, String> map) {
		setRequestor(requestor);
		setId(TaskID.Weather);
		this.mxontext = context;
		url = Communication.CommonUser_API_URL+ "localisation/weather";
		map.put("UATHT", "F0IJryhHnTtujwFqoYAApHSSsduYAOg8jYol+FVD1K0=");
		map.put("UserId", "8");
		map.put("Latitude", "30.044420");
		map.put("Longitude", "31.235712");
		map.put("Time", "daily");
		httpBody = buildHttpBody(map);
		httpBody = "{\"UATHT\":\"F0IJryhHnTtujwFqoYAApHSSsduYAOg8jYol+FVD1K0=\",\"UserId\":\"8\",\"Location\":\"Egypt+Al Qahirah\"}";
		// +
		// "\"Latitude\":\"30.044420\",\"Longitude\":\"31.235712\",\"Time\":\"daily\"}";

	}

	String buildHttpBody(Map<String, String> inputs) {
		String concat = "{";
		for (String key : inputs.keySet()) {
			if (concat.length() == 0) {
				concat = "\"" + key + "\":\"" + inputs.get(key) + "\"";
			} else {
				concat = concat + ",\"" + key + "\":\"" + inputs.get(key)
						+ "\"";

			}
		}
		concat += "}";
		return concat;
	}

	@Override
	public void execute() {
		response = (ResponseObject) Communication.postMethodWithBody(url,
				getHeadersList(), httpBody, mxontext);
		System.out.println("url" + url);
		mapServerError(response.getStatusCode());
		String r = response.getResponseString();
		JSONObject mainObject;
		if (response.getStatusCode() == 200) {
			try {
				mainObject = new JSONObject(r);
				JSONArray jsonArray = mainObject.getJSONArray("weather");
				for (int i = 0; i < jsonArray.length(); i++) {

					if (jsonArray.get(i) instanceof JSONObject) {
						WeatherData weatherData = WeatherData
								.FromJson(jsonArray.get(i) .toString());
						weatherlist.add(weatherData);
					}
				}
				Log.d("Waether .... result", r);
				ServiceStorage.weatherlist = weatherlist;
				result = weatherlist;

			} catch (JSONException e) {

			}
		}
	}

	public ArrayList<RequestHeader> getHeadersList() {
		ArrayList<RequestHeader> headers = new ArrayList<RequestHeader>();
		RequestHeader header = new RequestHeader(CONTENT_TYPE_KEY,
				CONTENT_TYPE_VALUE);
		headers.add(header);

		return headers;
	}

	@Override
	public Object getResult() {
		// TODO Auto-generated method stub
		return result;
	}

}
