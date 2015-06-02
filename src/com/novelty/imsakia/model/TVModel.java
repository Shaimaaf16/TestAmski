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
public class TVModel extends MarkerModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6694087234574128364L;
	String id;
	String name;
	String description;
	String showTime;
	String repeatTime;
	String allowRepeat;
	String image;
	String totalFollows;
	String customerFollow;
	ArrayList<PresenterModel> presenters;
		
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
