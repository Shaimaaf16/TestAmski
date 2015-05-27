package com.novelty.imsakia.model;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;

import android.os.Parcel;
import android.os.Parcelable;

public class PresenterModel implements Parcelable{
	
	@JsonProperty("presenter_id")
	String presenterId;
	@JsonProperty("name")
	private String name;
	
	protected PresenterModel() {
	}
	
	protected PresenterModel(Parcel in) {
		presenterId  = in.readString();
		name         = in.readString();
	}

	public PresenterModel(String presenterId, String name) {
		super();
		this.presenterId = presenterId;
		this.name        = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getPresenterId() {
		return presenterId;
	}

	public void setPresenterId(String presenterId) {
		this.presenterId = presenterId;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(presenterId);
		dest.writeString(name);
	}
	
	public static PresenterModel fromJson(String jsonText) {
		ObjectMapper mapper = new ObjectMapper();
		PresenterModel model = null;
		try {
			model = mapper.readValue(jsonText, PresenterModel.class);
			System.out.println(model);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return model;
	}
	
	public static final Parcelable.Creator<PresenterModel> CREATOR = new Parcelable.Creator<PresenterModel>() {
		@Override
		public PresenterModel createFromParcel(Parcel in) {
			return new PresenterModel(in);
		}

		@Override
		public PresenterModel[] newArray(int size) {
			return new PresenterModel[size];
		}
	};
}
