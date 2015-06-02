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
import com.novelty.imsakia.dataobjects.Branch;
import com.novelty.imsakia.dataobjects.Country;
import com.novelty.imsakia.storage.ServiceStorage;

public class GetBranches extends Task {

	private String url;
	Context mxontext;
	ArrayList<Branch> result = new ArrayList<Branch>();
	private ResponseObject response;
	public static String CONTENT_TYPE_KEY = "Content-type";
	public static String ACCESS_TOKEN_KEY = "accessToken";
	public static String CONTENT_TYPE_VALUE = "application/x-www-form-urlencoded";
	ArrayList<Branch> branchList = new ArrayList<Branch>();
	private String httpBody;

	public GetBranches(DataRequestor requestor, Context context) {
		setRequestor(requestor);
		setId(TaskID.GetBranchesTask);
		this.mxontext = context;
		url = Communication.CommonUser_API_URL + "vendor/getbranches";
		httpBody="{\"UATHT\":\"6735rk8j766yhj67-ANDROID\",\"UserId\":\"8\",\"VendorId\":\"1\",\"LangId\":\"1\"}";


		// url = url.replaceAll("\\s+", "");

	}

	@Override
	public void execute() {
		response = (ResponseObject) Communication.postMethodWithBody(url,
				getHeadersList(), httpBody,mxontext);
		System.out.println("url" + url);
		mapServerError(response.getStatusCode());
		String r = response.getResponseString();
		JSONObject mainObject;
		if (response.getStatusCode() == 200)
		{
		try {
			mainObject = new JSONObject(r);
			JSONArray jsonArray = mainObject.getJSONArray("addresses");

			for (int i = 0; i < jsonArray.length(); i++) {

				if (jsonArray.get(i) instanceof JSONObject) {

					Branch branch = Branch
							.fromJson(((JSONObject) jsonArray.get(i))
									.toString());
					branchList.add(branch);
				}
			}
			Log.d("Shaimaa", "branchList " + branchList.size());
			result=branchList;
			 ServiceStorage.branchList=branchList;

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
