package com.novelty.imsakia.tasks;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.novelty.imsakia.controller.communication.Communication;
import com.novelty.imsakia.controller.communication.DataRequestor;
import com.novelty.imsakia.controller.communication.RequestHeader;
import com.novelty.imsakia.controller.communication.ResponseObject;
import com.novelty.imsakia.controller.communication.Task;
import com.novelty.imsakia.controller.communication.Task.TaskID;
import com.novelty.imsakia.model.TVGuidModel;
import com.novelty.imsakia.storage.ServiceStorage;

import android.content.Context;
import android.util.Log;

public class GetTVProgramesList extends Task {

	private String url;
	private Context mxontext;
	private String httpBody = "";
	private ArrayList<TVGuidModel> result;
	private ResponseObject response;
	public static String CONTENT_TYPE_KEY     = "Content-type";
	public static String ACCESS_TOKEN_KEY     = "accessToken";
	public static String CONTENT_TYPE_VALUE   = "application/x-www-form-urlencoded";
	private ArrayList<TVGuidModel> tvGuidlist = new ArrayList<TVGuidModel>();

	public GetTVProgramesList(DataRequestor requestor, Context context) {
		setRequestor(requestor);
		setId(TaskID.GetTVGuidListTask);
		this.mxontext = context;
		url           = "http://www.noveltycreators.com/imskya/api/channel/getchannels";
		String auth   = "F0IJryhHnTtujwFqoYAApHSSsduYAOg8jYol+FVD1K0=";
		String userId = "8";
		int LangId    = 1;
		httpBody = "{\"UATHT\":\"" + auth + "\",\"UserId\":\"" + userId	+ "\",\"LangId\":\"" + LangId + "\"}";
	}

	@Override
	public void execute() {
		response = (ResponseObject) Communication.postMethodWithBody(url, getHeadersList(), httpBody, mxontext);
		System.out.println("url" + url);
		mapServerError(response.getStatusCode());
		String responseStr = response.getResponseString();
		JSONObject mainObject;
		if (response.getStatusCode() == 200) {
			try {
				mainObject = new JSONObject(responseStr);
				JSONArray jsonArray = mainObject.getJSONArray("channels");
				for (int i = 0; i < jsonArray.length(); i++) {
					if (jsonArray.get(i) instanceof JSONObject) {
						TVGuidModel model = TVGuidModel.fromJson(((JSONObject) jsonArray.get(i)).toString());
						tvGuidlist.add(model);
					}
				}
				Log.d("Turki", "tvGuidlist " + tvGuidlist.size());
				ServiceStorage.tvGuidList = tvGuidlist;
				result = tvGuidlist;
			} catch (JSONException e) {
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
		RequestHeader header = new RequestHeader(CONTENT_TYPE_KEY, CONTENT_TYPE_VALUE);
		headers.add(header);
		return headers;
	}
}
