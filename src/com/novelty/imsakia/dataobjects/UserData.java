package com.novelty.imsakia.dataobjects;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;

import android.util.Log;

public class UserData {
	// {"UATHT":"1f9g8EbQmCmJarcSPXUnF+VVMTfq4IA2KaGBj\/wLPtk=","UserId":"8","ResponseId":"0","Message":""}
	@JsonProperty("UATHT")
	String auth;
	@JsonProperty("UserId")
	String userId;
	@JsonProperty("ResponseId")
	String responseId;
	@JsonProperty("Message")
	String message;

	public String getAuth() {
		return auth;
	}

	public void setAuth(String auth) {
		this.auth = auth;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getResponseId() {
		return responseId;
	}

	public void setResponseId(String responseId) {
		this.responseId = responseId;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public static UserData FromJson(String jsonText) {
		ObjectMapper mapper = new ObjectMapper();
		UserData userData = null;

		try {
			userData = mapper.readValue(jsonText, UserData.class);
			Log.d("userData ", userData.userId);
			// display to console
			System.out.println(userData);
		} catch (JsonGenerationException e) {

			e.printStackTrace();

		}

		catch (IOException e) {

			e.printStackTrace();

		}

		return userData;
	}
}
