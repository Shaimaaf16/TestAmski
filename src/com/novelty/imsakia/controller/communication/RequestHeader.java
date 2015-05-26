package com.novelty.imsakia.controller.communication;

public class RequestHeader {
	
	private String key;
	private String value;
	
	public RequestHeader(String _key,String _value) {
		// TODO Auto-generated constructor stub
		key=_key;
		value=_value;
	}
	
	public String getKey() {
		return key;
	}
	public void setKey(String key) {
		this.key = key;
	}
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
}
