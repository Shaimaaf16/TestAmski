package com.novelty.imsakia.model;


import java.io.IOException;
import java.io.Serializable;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;

import android.annotation.SuppressLint;
import android.os.Parcel;
import android.os.Parcelable;

@SuppressLint("ParcelCreator")
public class TVGuidAndProgrameModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -3259379912231377443L;
	@JsonProperty("id")
	String id;
	@JsonProperty("name")
	String name;
	@JsonProperty("description")
	String description;
	@JsonProperty("image")
	String image;
	@JsonProperty("groups")
	GroupsModel groups;
	
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
	
	public static TVGuidAndProgrameModel FromJson(String jsonText) {
		ObjectMapper mapper = new ObjectMapper();
		TVGuidAndProgrameModel tvAndProgramData = null;
		try {
			tvAndProgramData = mapper.readValue(jsonText, TVGuidAndProgrameModel.class);
			// display to console
			System.out.println(tvAndProgramData);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return tvAndProgramData;
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

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public GroupsModel getGroups() {
		return groups;
	}

	public void setGroups(GroupsModel groups) {
		this.groups = groups;
	}
}
