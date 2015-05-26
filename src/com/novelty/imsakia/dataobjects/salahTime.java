package com.novelty.imsakia.dataobjects;

import org.codehaus.jackson.annotate.JsonProperty;

import com.novelty.imsakia.moazn.Time;

public class salahTime {
	

	@JsonProperty("Fajr")
	String fajr;
	@JsonProperty("Sunrise")
	String shurooq;
	@JsonProperty("Dhuhr")
	String duhr;
	@JsonProperty("Asr")
	String asr;
	@JsonProperty("Maghrib")
	String maghrib;
	@JsonProperty("Isha")
	String isha;
	@JsonProperty("Sunset")
	String sunset;

	double duhrD,asrD,maghribD,ishaaD,fajarD,shurooqD;




	public String getFajr() {
		return fajr;
	}

	public void setFajr(String fajr) {
		this.fajr = fajr;
		String fajrD=fajr.split(" ")[0].replace(":", ".");
		double d=Double.valueOf(fajrD);
		setFajarD(d);
		
	}

	public String getShurooq() {
		return shurooq;
	}

	public void setShurooq(String shurooq) {
		this.shurooq = shurooq;
		String shurooqD=shurooq.split(" ")[0].replace(":", ".");
		double d=Double.valueOf(shurooqD);
		setShurooqD(d);
	}

	public double getDuhrD() {
		return duhrD;
	}

	public void setDuhrD(double duhrD) {
		this.duhrD = duhrD;
		
	}

	public double getAsrD() {
		return asrD;
	}

	public void setAsrD(double asrD) {
		this.asrD = asrD;
	}

	public double getIshaaD() {
		return ishaaD;
	}

	public void setIshaaD(double ishaaD) {
		this.ishaaD = ishaaD;
	}

	public double getFajarD() {
		return fajarD;
	}

	public void setFajarD(double fajarD) {
		this.fajarD = fajarD;
	}

	public double getShurooqD() {
		return shurooqD;
	}

	public void setShurooqD(double shurooqD) {
		this.shurooqD = shurooqD;
	}

	public String getDuhr() {
		return duhr;
	}

	public void setDuhr(String dhuhr) {
		this.duhr = dhuhr;
		String splitstr=duhr.split(" ")[0].replace(":", ".");
		double d=Double.valueOf(splitstr);
		setDuhrD(d);
	}

	public String getAsr() {
		return asr;
	}

	public void setAsr(String asr) {
		this.asr = asr;
		String splitstr=asr.split(" ")[0].replace(":", ".");
		double d=Double.valueOf(splitstr);
		setAsrD(d);
	}

	public String getMaghrib() {
		return maghrib;
	}

	public void setMaghrib(String maghrib) {
		this.maghrib = maghrib;
		String splitstr=maghrib.split(" ")[0].replace(":", ".");
		double d=Double.valueOf(splitstr);
		setMaghribD(d);
	}

	public double getMaghribD() {
		return maghribD;
	}

	public void setMaghribD(double maghribD) {
		this.maghribD = maghribD;
	}

	public String getIsha() {
		return isha;
	}

	public void setIsha(String isha) {
		this.isha = isha;
		String splitstr=isha.split(" ")[0].replace(":", ".");
		double d=Double.valueOf(splitstr);
		setIshaaD(d);
	}
	


	// Prayer Time
	public Time zuhrTime() {
		Time t = new Time(this.duhrD, true);
		return t;
	}

	public Time asrTime() {
		Time t = new Time(this.asrD);
		return t;
	}

	public Time fajrTime() {
		Time t = new Time(this.fajarD, true);
		return t;
	}

	public Time ishaTime() {
		Time t = new Time(this.ishaaD);
		return t;
	}

	public Time maghribTime() {
		Time t = new Time(this.maghribD);
		return t;
	}

	public Time shroukTime() {
		Time t = new Time(this.shurooqD, true);
		return t;
	}

	public String getSunset() {
		return sunset;
	}

	public void setSunset(String sunset) {
		this.sunset = sunset;
	}

}
