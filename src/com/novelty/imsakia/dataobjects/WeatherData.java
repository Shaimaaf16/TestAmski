package com.novelty.imsakia.dataobjects;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;

import android.util.Log;

public class WeatherData {
	@JsonProperty("date")
	String date;
	@JsonProperty("temp")
	WeatherTemp temp;
	@JsonProperty("weather")
	WeatherStatus weatherstatus;
	

	public static WeatherData FromJson(String jsonText) {
		ObjectMapper mapper = new ObjectMapper();
		WeatherData weatherData = null;

		try {
			weatherData = mapper.readValue(jsonText, WeatherData.class);
			Log.d("weatherData ", weatherData.date);
			// display to console
			System.out.println(weatherData);
		} catch (JsonGenerationException e) {

			e.printStackTrace();

		}

		catch (IOException e) {

			e.printStackTrace();

		}

		return weatherData;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	public WeatherTemp getTemp() {
		return temp;
	}


	public void setTemp(WeatherTemp temp) {
		this.temp = temp;
	}


	public WeatherStatus getWeatherstatus() {
		return weatherstatus;
	}


	public void setWeatherstatus(WeatherStatus weatherstatus) {
		this.weatherstatus = weatherstatus;
	}


}
