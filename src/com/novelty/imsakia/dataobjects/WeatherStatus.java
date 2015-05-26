package com.novelty.imsakia.dataobjects;

import org.codehaus.jackson.annotate.JsonProperty;

public class WeatherStatus {

	@JsonProperty("description")
	String description;
	@JsonProperty("icon")
	String icon;
	@JsonProperty("main")
	String main;
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getIcon() {
		return icon;
	}
	public void setIcon(String icon) {
		this.icon = icon;
	}
	public String getMain() {
		return main;
	}
	public void setMain(String main) {
		this.main = main;
	}
	
	
	
}
