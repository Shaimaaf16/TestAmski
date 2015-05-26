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
import com.novelty.imsakia.dataobjects.Ads;
import com.novelty.imsakia.dataobjects.Vendor;
import com.novelty.imsakia.storage.ServiceStorage;

public class GetAdvertisments extends Task {

	private String url;
	Context mxontext;
	String result = "";
	private ResponseObject response;
	public static String CONTENT_TYPE_KEY = "Content-type";
	public static String ACCESS_TOKEN_KEY = "accessToken";
	public static String CONTENT_TYPE_VALUE = "application/x-www-form-urlencoded";
	ArrayList<Ads> adsList = new ArrayList<Ads>();
	private String httpBody;

	public GetAdvertisments(DataRequestor requestor, Context context) {
		setRequestor(requestor);
		setId(TaskID.GetAdvertisments);
		this.mxontext = context;
		url = Communication.CommonUser_API_URL + "advertisment/getadvertisments";
		httpBody="{\"UATHT\":\"F0IJryhHnTtujwFqoYAApHSSsduYAOg8jYol+FVD1K0=\",\"UserId\":\"8\",\"Type\":\"Mall\",\"LangId\":\"1\",\"ImageSize\":\"720X1280\",\"VendorId\":\"2\"}";

//		String st={"UATHT":"auth key","UserId":"user id","Type":"Mall or Normal","LangId":"1","ImageSize":"width X height","VendorId":"2"}


	}

	@Override
	public void execute() {
		response = (ResponseObject) Communication.postMethodWithBody(url, getHeadersList(), httpBody, mxontext);

		System.out.println("url" + url);
		mapServerError(response.getStatusCode());
		String r = response.getResponseString();
		JSONObject mainObject;
		if (response.getStatusCode() == 200)
		{
		try {
			mainObject = new JSONObject(r);
			JSONArray jsonArray = mainObject.getJSONArray("AdsList");

			for (int i = 0; i < jsonArray.length(); i++) {

				if (jsonArray.get(i) instanceof JSONObject) {

					Ads ads = Ads
							.FromJson(((JSONObject) jsonArray.get(i))
									.toString());
					adsList.add(ads);
				}
			}
			Log.d("Shaimaa", "adsList " + adsList.size());
			 ServiceStorage.adsList=adsList;

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
