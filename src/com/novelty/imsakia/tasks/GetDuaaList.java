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
import com.novelty.imsakia.dataobjects.DuaaData;
import com.novelty.imsakia.storage.ServiceStorage;

public class GetDuaaList extends Task {

	private String url;
	Context mxontext;
	String httpBody="";
	ArrayList<DuaaData> result ;
	private ResponseObject response;
	public static String CONTENT_TYPE_KEY = "Content-type";
	public static String ACCESS_TOKEN_KEY = "accessToken";
	public static String CONTENT_TYPE_VALUE = "application/x-www-form-urlencoded";
	ArrayList<DuaaData> duaaslist = new ArrayList<DuaaData>();

	public GetDuaaList(DataRequestor requestor, Context context) {
		setRequestor(requestor);
		setId(TaskID.GetDuaaListTask);
		this.mxontext = context;
		url = "http://www.noveltycreators.com/imskya/api/duaa/getduaalist";
		String auth = "F0IJryhHnTtujwFqoYAApHSSsduYAOg8jYol+FVD1K0=";
		String userId = "8";
		int VendorId=1;
		httpBody = "{\"UATHT\":\"" + auth + "\",\"UserId\":\"" + userId
				+ "\",\"VendorId\":\""+VendorId+"\"}";

	}

	@Override
	public void execute() {

		response = (ResponseObject) Communication.postMethodWithBody(url,
				getHeadersList(), httpBody, mxontext);
		System.out.println("url" + url);
		mapServerError(response.getStatusCode());
		String r = response.getResponseString();
		JSONObject mainObject;
		if (response.getStatusCode() == 200)
		{
		try {
			mainObject = new JSONObject(r);
			JSONArray jsonArray = mainObject.getJSONArray("duaas");

			for (int i = 0; i < jsonArray.length(); i++) {

				if (jsonArray.get(i) instanceof JSONObject) {

					DuaaData duaa = DuaaData.fromJson(((JSONObject) jsonArray.get(i))
									.toString());
					duaaslist.add(duaa);
				}
			}
			Log.d("Shaimaa", "duaaslist " + duaaslist.size());
			 ServiceStorage.duaaslist=duaaslist;
			 result=duaaslist;

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
