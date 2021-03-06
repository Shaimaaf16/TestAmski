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
public class PresenterModel implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 9083714713565847282L;
	@JsonProperty("id")
	String id;
	@JsonProperty("name")
	String name;
	@JsonProperty("description")
	String description;
	@JsonProperty("image")
	String image;
	
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
	
	public static PresenterModel FromJson(String jsonText) {
		ObjectMapper mapper = new ObjectMapper();
		PresenterModel presenterData = null;
		try {
			presenterData = mapper.readValue(jsonText, PresenterModel.class);
			// display to console
			System.out.println(presenterData);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return presenterData;
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

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
