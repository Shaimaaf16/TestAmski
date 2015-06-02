package com.novelty.imsakia.tasks;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import com.novelty.imsakia.controller.communication.Communication;
import com.novelty.imsakia.controller.communication.DataRequestor;
import com.novelty.imsakia.controller.communication.RequestHeader;
import com.novelty.imsakia.controller.communication.ResponseObject;
import com.novelty.imsakia.controller.communication.Task;
import com.novelty.imsakia.storage.ServiceStorage;

import android.content.Context;

public class FollowProgram extends Task {

	private String url;
	private Context mxontext;
	private String httpBody = "";
	private String result;
	private ResponseObject response;
	public static String CONTENT_TYPE_KEY     = "Content-type";
	public static String ACCESS_TOKEN_KEY     = "accessToken";
	public static String CONTENT_TYPE_VALUE   = "application/x-www-form-urlencoded";

	public FollowProgram(DataRequestor requestor, Context context, String programeID, int followFlag) {
		setRequestor(requestor);
		setId(TaskID.GetTVGuidListTask);
		this.mxontext = context;
		url           = "http://www.noveltycreators.com/imskya/api/channel/folloprogram";
		String auth   = "6735rk8j766yhj67-ANDROID";
		int LangId    = 1;
		httpBody = "{\"UATHT\":\"" + auth + "\",\"ProgramId\":\"" + programeID +"\",\"Follow\":\"" + followFlag + "\"}";
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
				String followers = mainObject.getString("TotalFollows");
				ServiceStorage.totalFollowers = followers;
				result = followers;
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
