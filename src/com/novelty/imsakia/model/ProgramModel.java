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
public class ProgramModel  extends MarkerModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6694087234574128364L;
	@JsonProperty("id")
	String id;
	@JsonProperty("name")
	String name;
	@JsonProperty("description")
	String description;
	@JsonProperty("show_time")
	String showTime;
	@JsonProperty("repeat_time")
	String repeatTime;
	@JsonProperty("allow_repeat")
	String allowRepeat;
	@JsonProperty("image")
	String image;
	@JsonProperty("totalFollows")
	String totalFollows;
	@JsonProperty("customerFollow")
	String customerFollow;
	@JsonProperty("presenters")
	ArrayList<PresenterModel> presenters;
		
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
	
	public static ProgramModel FromJson(String jsonText) {
		ObjectMapper mapper = new ObjectMapper();
		ProgramModel programData = null;
		try {
			programData = mapper.readValue(jsonText, ProgramModel.class);
			System.out.println(programData);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return programData;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getShowTime() {
		return showTime;
	}

	public void setShowTime(String showTime) {
		this.showTime = showTime;
	}

	public String getRepeatTime() {
		return repeatTime;
	}

	public void setRepeatTime(String repeatTime) {
		this.repeatTime = repeatTime;
	}

	public String getAllowRepeat() {
		return allowRepeat;
	}

	public void setAllowRepeat(String allowRepeat) {
		this.allowRepeat = allowRepeat;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public String getTotalFollows() {
		return totalFollows;
	}

	public void setTotalFollows(String totalFollows) {
		this.totalFollows = totalFollows;
	}

	public String getCustomerFollow() {
		return customerFollow;
	}

	public void setCustomerFollow(String customerFollow) {
		this.customerFollow = customerFollow;
	}

	public ArrayList<PresenterModel> getPresenters() {
		return presenters;
	}

	public void setPresenters(ArrayList<PresenterModel> presenters) {
		this.presenters = presenters;
	}
}
