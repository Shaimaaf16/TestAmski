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
import com.novelty.imsakia.storage.ServiceStorage;

public class FollowVendor extends Task {

	private String url;
	Context mxontext;
	String result = "";
	private ResponseObject response;
	private String httpBody;
	public static String CONTENT_TYPE_KEY = "Content-type";
	public static String ACCESS_TOKEN_KEY = "accessToken";
	public static String CONTENT_TYPE_VALUE = "application/x-www-form-urlencoded";

	public FollowVendor(DataRequestor requestor, Context context) {
		setRequestor(requestor);
		setId(TaskID.GetCountriesTask);
		this.mxontext = context;

		url = Communication.CommonUser_API_URL + "vendor/follow";
		httpBody="{\"UATHT\":\"F0IJryhHnTtujwFqoYAApHSSsduYAOg8jYol+FVD1K0=\",\"UserId\":\"8\",\"Follow\":\"1\",\"VendorId\":\"1\"}";
//		{"UATHT":"auth key","UserId":"user id","Follow":"1 for yes 0 for no","VendorId":"vendor id"}

		// url = url.replaceAll("\\s+", "");

	}

	@Override
	public void execute() {
		response = (ResponseObject) Communication.postMethodWithBody(url,
				getHeadersList(),httpBody, mxontext);
		System.out.println("url" + url);
		mapServerError(response.getStatusCode());
		String r = response.getResponseString();
		JSONObject mainObject;
		if (response.getStatusCode() == 200)
		{
		try {
			mainObject = new JSONObject(r);
			if(mainObject.has("TotalFollows"))
			{
		     String total_follows=mainObject.getString("TotalFollows");
		     Log.d("Total Follows", total_follows);
			}
//		    "ResponseId": "0",
//		    "Message": ""

//			JSONArray jsonArray = mainObject.getJSONArray("CountriesList");
//
//			for (int i = 0; i < jsonArray.length(); i++) {
//
//				if (jsonArray.get(i) instanceof JSONObject) {
//
//					Country country = Country
//							.countryFromJson(((JSONObject) jsonArray.get(i))
//									.toString());
//					countryList.add(country);
//				}
//			}
//			 ServiceStorage.countryList=countryList;

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
