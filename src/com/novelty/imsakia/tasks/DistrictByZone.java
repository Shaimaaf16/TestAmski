package com.novelty.imsakia.tasks;

import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;

import com.novelty.imsakia.controller.communication.Communication;
import com.novelty.imsakia.controller.communication.DataRequestor;
import com.novelty.imsakia.controller.communication.RequestHeader;
import com.novelty.imsakia.controller.communication.ResponseObject;
import com.novelty.imsakia.controller.communication.Task;
import com.novelty.imsakia.controller.communication.Task.TaskID;
import com.novelty.imsakia.dataobjects.District;
import com.novelty.imsakia.dataobjects.Zone;

public class DistrictByZone extends Task {

	private String url;
	Context mxontext;
	District result ;
	private ResponseObject response;
	public static String CONTENT_TYPE_KEY = "Content-type";
	public static String ACCESS_TOKEN_KEY = "accessToken";
	public static String CONTENT_TYPE_VALUE = "application/x-www-form-urlencoded";
	District district = new District();
	private String httpBody;

	public DistrictByZone(DataRequestor requestor, Context context, int zoneId) {
		setRequestor(requestor);
		setId(TaskID.GetDistrictByZoneId);
		this.mxontext = context;
		url = Communication.Common_Base_URL + "zonebycountry" ;
		httpBody="{\"ZoneId\":\""+zoneId+"\"}";

	}

	@Override
	public void execute() {
		response = (ResponseObject) Communication.postMethodWithBody(url,
				getHeadersList(), httpBody, mxontext);

		System.out.println("url" + url);
		mapServerError(response.getStatusCode());
		String r = response.getResponseString();
		JSONObject mainObject;
		try {
			mainObject = new JSONObject(r);
			district = District.FromJson(mainObject.toString());
			result=district;
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
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
