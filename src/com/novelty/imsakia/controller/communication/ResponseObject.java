package com.novelty.imsakia.controller.communication;


public class ResponseObject {
	
	private int statusCode;
	private String responseString="";
	
	public int getStatusCode() {
		return statusCode;
	}
	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}
	public String getResponseString() {
		return responseString;
	}
	public void setResponseString(String responseString) {
		this.responseString = responseString;
	}
	

}
