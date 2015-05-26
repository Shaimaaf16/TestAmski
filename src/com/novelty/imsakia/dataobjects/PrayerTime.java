package com.novelty.imsakia.dataobjects;

import java.io.IOException;
import java.util.ArrayList;

import com.novelty.imsakia.moazn.*;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;





import android.util.Log;

public class PrayerTime {

	@JsonProperty("day")
	String day;
	@JsonProperty("prayers")
    salahTime prayers;
	
//	private Date m_date;
	public static salahTime salawt;

//	public Date date() {
//		return this.m_date;
//	}
//
//	public int day() {
//		return this.m_date.day;
//	}
//
//	public int month() {
//		return this.m_date.month;
//	}
//
//	public int year() {
//		return this.m_date.year;
//	}
//
//	public void setDay(int day) {
//		this.m_date.day = day;
//	}
//
//	public void setMonth(int month) {
//		this.m_date.month = month;
//	}
//
//	public void setYear(int year) {
//		this.m_date.year = year;
//	}

//	public void setDate(int day, int month, int year) {
//		this.m_date.day = day;
//		this.m_date.month = month;
//		this.m_date.year = year;
//	}
//
//	public void setDate(Date date) {
//		this.m_date = date;
//	}



	protected double removeDublication(double var) {
		if (var > 360) {
			var /= 360;
			var -= (int) var;
			var *= 360;
		}
		return var;
	}

	protected double abs(double var) {

		if (var < 0)
			return -var;
		else
			return var;
	}

	public static PrayerTime fromJson(String jsonText) {
		ObjectMapper mapper = new ObjectMapper();
		PrayerTime time = null;

		try {
			time = mapper.readValue(jsonText, PrayerTime.class);
			// display to console
			System.out.println(time);
		} catch (JsonGenerationException e) {

			e.printStackTrace();

		}

		catch (IOException e) {

			e.printStackTrace();

		}

		return time;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public salahTime getPrayers() {
		return prayers;
	}

	public void setPrayers(salahTime prayers) {
		this.prayers = prayers;
	}

	
}
