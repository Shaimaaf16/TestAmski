package com.novelty.imsakia.tasks;

import java.lang.reflect.Field;
import java.util.ArrayList;

import org.json.JSONException;
import org.json.JSONObject;

import android.annotation.SuppressLint;
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

@SuppressLint("NewApi") public class LoginTask extends Task {

	private String url;
	Context mxontext;
	UserData result;
	String httpBody;
	private ResponseObject response;
	public static String CONTENT_TYPE_KEY = "Content-type";
	public static String ACCESS_TOKEN_KEY = "accessToken";
	public static String CONTENT_TYPE_VALUE = "application/x-www-form-urlencoded";

	public LoginTask(DataRequestor requestor, Context context, String mail,
			String passString) {
		setRequestor(requestor);
		setId(TaskID.LoginTask);
		this.mxontext = context;
		url = Communication.CommonUser_Base_URL + "login";
		String VendorId = "1";
		String DevOSId = Secure.getString(this.mxontext.getContentResolver(),Secure.ANDROID_ID);
		String AgentVerNO = "1";
		String UserName = mail;
		String PPPWZ = passString;
		TelephonyManager telephonyManager = (TelephonyManager) mxontext
				.getSystemService(Context.TELEPHONY_SERVICE);
		String DevIMEI = telephonyManager.getDeviceId();
		String DevOSVersion = telephonyManager.getDeviceSoftwareVersion();
		DevOSVersion = getAndroiVersion();
		// {"VendorId":"1","DevOSId":"1","DevOSVersion":"Android 5.0, Lollipop","DevIMEI":"90909055",
		// "AgentVerNO":"1","UserName":"mahmoud@gmail.com","PPPWZ":"1234567890"}
		httpBody = "{\"VendorId\":\"" + VendorId + "\",\"DevOSId\":\""
				+ DevOSId + "\",\"DevOSVersion\":\"" + DevOSVersion
				+ "\",\"DevIMEI\":\"" + DevIMEI + "\",\"AgentVerNO\":\""
				+ AgentVerNO + "\",\"UserName\":\"" + UserName
				+ "\",\"PPPWZ\":\"" + PPPWZ + "\"}";
		
//		httpBody="{\"FirstName\":\"shaimaa\",\"LastName\":\"mohamed\",\"USMail\":\"eng.shaimaa.mohammed@gmail.com\",\"MobileNO\":\"01008445171\",\"GenderId\":\"2\",\"BirthDate\":\"1984-02-10\",\"CountryId\":\"63\",\"ZoneId\":\"1011\",\"DistrictId\":\"17\",\"LangId\":\"1\"};	}
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
				ServiceStorage.currentUser=userData;
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
