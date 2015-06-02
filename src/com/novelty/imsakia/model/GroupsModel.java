package com.novelty.imsakia.model;


import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint("ParcelCreator")
public class GroupsModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 8184936721799658441L;
	@JsonProperty("Program")
	ArrayList<ProgramModel> program;
	@JsonProperty("Series")
	ArrayList<SeriesModel> series;
	
//	@Override
//	public int describeContents() {
//		// TODO Auto-generated method stub
//		return 0;
//	}
//
//	@Override
//	public void writeToParcel(Parcel arg0, int arg1) {
//		// TODO Auto-generated method stub
//	}
	
	public static GroupsModel FromJson(String jsonText) {
		ObjectMapper mapper = new ObjectMapper();
		GroupsModel groupData = null;
		try {
			groupData = mapper.readValue(jsonText, GroupsModel.class);
			System.out.println(groupData);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return groupData;
	}

	public ArrayList<ProgramModel> getProgram() {
		return program;
	}

	public void setProgram(ArrayList<ProgramModel> program) {
		this.program = program;
	}

	public ArrayList<SeriesModel> getSeries() {
		return series;
	}

	public void setSeries(ArrayList<SeriesModel> series) {
		this.series = series;
	}
}
