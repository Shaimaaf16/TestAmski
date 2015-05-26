package com.novelty.imsakia.dataobjects;

import org.codehaus.jackson.annotate.JsonProperty;

public class WeatherTemp {
	@JsonProperty("day")
	 double day ;
     @JsonProperty("min")
     double min;
     @JsonProperty("max")
     double max;
     @JsonProperty("night")
     double night;
     @JsonProperty("eve")
     double  eve;
     @JsonProperty("morn")
     double morn;
	public double getDay() {
		return day;
	}
	public void setDay(double day) {
		this.day = day;
	}
	public double getMin() {
		return min;
	}
	public void setMin(double min) {
		this.min = min;
	}
	public double getMax() {
		return max;
	}
	public void setMax(double max) {
		this.max = max;
	}
	public double getNight() {
		return night;
	}
	public void setNight(double night) {
		this.night = night;
	}
	public double getEve() {
		return eve;
	}
	public void setEve(double eve) {
		this.eve = eve;
	}
	public double getMorn() {
		return morn;
	}
	public void setMorn(double morn) {
		this.morn = morn;
	}
     


}
