/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.novelty.imsakia.moazn;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import android.text.format.DateFormat;

/**
 *
 * @author Admin
 */
public class Date {
	public Date(int day, int month, int year) {
		this.day = day;
		this.month = month;
		this.year = year;
	}

	public int day;
	public int month;
	public int year;
	static String[] strDays = new String[] { "Sunday", "Monday", "Tuesday",
			"Wednesday", "Thusday", "Friday", "Saturday" };
	private static String date_to_string;

	public static String getWeekDay(String date) {
		// converting date into ddMMyyyy format example "14092011"
		SimpleDateFormat dateformatddMMyyyy = new SimpleDateFormat("dd-MM-yyyy");
		try {
			java.util.Date dat = dateformatddMMyyyy.parse(date);
			dateformatddMMyyyy.applyPattern("EEEE");
			date_to_string = dateformatddMMyyyy.format(dat);

			// Calendar c = Calendar.getInstance();
			// c.setTime(dat);
			// int dayOfWeek = c.get(Calendar.DAY_OF_WEEK);
			// date_to_string = strDays[dayOfWeek];
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return date_to_string;

	}
}
