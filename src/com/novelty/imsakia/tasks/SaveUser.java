package com.novelty.imsakia.tasks;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Map;

import org.json.JSONException;
import org.json.JSONObject;

import android.content.Context;
import android.os.Build;
import android.provider.Settings.Secure;
import android.telephony.TelephonyManager;

import com.novelty.imsakia.controller.communication.Communication;
import com.novelty.imsakia.controller.communication.DataRequestor;
import com.novelty.imsakia.controller.communication.RequestHeader;
import com.novelty.imsakia.controller.communication.ResponseObject;
import com.novelty.imsakia.controller.communication.Task;
import com.novelty.imsakia.dataobjects.UserData;
import com.novelty.imsakia.storage.ServiceStorage;

public class SaveUser extends Task {
	private Context mxontext;
	private String url;
	private String httpBody;
	private ResponseObject response;
	private UserData result;
	public static String CONTENT_TYPE_KEY = "Content-type";
	public static String ACCESS_TOKEN_KEY = "accessToken";
	public static String CONTENT_TYPE_VALUE = "application/x-www-form-urlencoded";

	// http://www.noveltycreators.com/imskya/api/user/save
	public SaveUser(DataRequestor requestor, Context context,Map<String, String>map) {
		setRequestor(requestor);
		setId(TaskID.SaveUserTask);
		this.mxontext = context;
		url = Communication.CommonUser_Base_URL + "save";
//		String VendorId = "1";
        String DevOSId = Secure.getString(this.mxontext.getContentResolver(),Secure.ANDROID_ID);

//		String AgentVerNO = "1";
		TelephonyManager telephonyManager = (TelephonyManager) mxontext.getSystemService(Context.TELEPHONY_SERVICE);
		String DevIMEI = telephonyManager.getDeviceId();
		String DevOSVersion = getAndroiVersion();
		
		map.put("UserId", "");
		map.put("VendorId", "1");
		map.put("DevOSId",DevOSId);
		map.put("AgentVerNO", "1");
		map.put("DevIMEI",DevIMEI);
		map.put("DevOSVersion", DevOSVersion);
		map.put("UserName", "ShaimaaM");
		map.put("PPPWZ","123456");
		map.put("FirstName", "Shaima");
		map.put("LastName", "Mohammed");
		map.put("USMail","eng.shaimaa.mohammed@gmail.com");
		map.put("MobileNO","01008445171");
		map.put("GenderId","2");
		map.put("BirthDate","1988-02-25");
		map.put("CountryId","63");
		map.put("ZoneId","1011");
		map.put("DistrictId","17");
		map.put("LangId", "1");	
		httpBody=buildHttpBody(map);

//		httpBody="{\"UserId\":\"\",\"VendorId\":\"1\",\"DevOSId\":\"1\",\"DevOSVersion\":\"Android 5.0, Lollipop\",\"DevIMEI\":\"90909055\",\"AgentVerNO\":\"1\",\"UserName\":\"eng.shaimaa.mohammed@gmail.com\",\"PPPWZ\":\"1234567890\"}";
		
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
		return concat+"}";
	}

	String getAndroiVersion() {

		StringBuilder builder = new StringBuilder();
		builder.append("Android ").append(Build.VERSION.RELEASE);

		Field[] fields = Build.VERSION_CODES.class.getFields();
		for (Field field : fields) {
			String fieldName = field.getName();
			int fieldValue = -1;

			try {
				fieldValue = field.getInt(new Object());
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (NullPointerException e) {
				e.printStackTrace();
			}

			if (fieldValue == Build.VERSION.SDK_INT) {
				builder.append(" , ").append(fieldName);
				// builder.append("sdk=").append(fieldValue);
			}
		}
		return builder.toString();

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
				UserData userData = UserData.FromJson(mainObject.toString());
				result = userData;
				ServiceStorage.currentUser = userData;
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
