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
import com.novelty.imsakia.dataobjects.Vendor;
import com.novelty.imsakia.storage.ServiceStorage;

public class GetVendorsList extends Task {

	private String url;
	Context mxontext;
	ArrayList<Vendor> result;
	private ResponseObject response;
	public static String CONTENT_TYPE_KEY = "Content-type";
	public static String ACCESS_TOKEN_KEY = "accessToken";
	public static String CONTENT_TYPE_VALUE = "application/x-www-form-urlencoded";
	ArrayList<Vendor> vendorList = new ArrayList<Vendor>();
	private String httpBody;

	public GetVendorsList(DataRequestor requestor, Context context) {
		setRequestor(requestor);
		setId(TaskID.GetVendors);
		this.mxontext = context;
		url           = Communication.CommonUser_API_URL + "vendor/getlist";
		String auth   = "6735rk8j766yhj67-ANDROID";
		int LangId    = 1;
		httpBody      = "{\"UATHT\":\"" + auth + "\",\"LangId\":\"" + LangId + "\"}";
	}

	@Override
	public void execute() {
		response = (ResponseObject) Communication.postMethodWithBody(url, getHeadersList(), httpBody, mxontext);
		System.out.println("url" + url);
		mapServerError(response.getStatusCode());
		String str = response.getResponseString();
		JSONObject mainObject;
		if (response.getStatusCode() == 200) {
			try {
				mainObject = new JSONObject(str);
				JSONArray jsonArray = mainObject.getJSONArray("vendors");
				for (int i = 0; i < jsonArray.length(); i++) {
					if (jsonArray.get(i) instanceof JSONObject) {
						Vendor vendor = Vendor.FromJson(((JSONObject) jsonArray.get(i)).toString());
						vendorList.add(vendor);
					}
				}
				Log.d("Shaimaa", "vendorList " + vendorList.size());
				ServiceStorage.vendorList = vendorList;

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
		RequestHeader header = new RequestHeader(CONTENT_TYPE_KEY, CONTENT_TYPE_VALUE);
		headers.add(header);
		return headers;
	}
}
