package com.novelty.imsakia.model;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;


import android.os.Parcel;
import android.os.Parcelable;

public class TVProgramsModel extends MarkerTVModel implements Parcelable{
	
	@JsonProperty("id")
	String id;
	@JsonProperty("name")
	private String name;
	@JsonProperty("image")
	private String image;
	@JsonProperty("description")
	private String description;
	@JsonProperty("presenter")
	private PresenterModel presenter;
	
	
	
	protected TVProgramsModel() {
	}
	
	protected TVProgramsModel(Parcel in) {
		id          = in.readString();
		name        = in.readString();
		description = in.readString();
		image       = in.readString();
	}

	public TVProgramsModel(String name, String image, String description) {
		super();
		this.name = name;
		this.image = image;
		this.description = description;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	@Override
	public int describeContents() {
		// TODO Auto-generated method stub
		return 0;
	}
	
	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(id);
		dest.writeString(name);
		dest.writeString(description);
		dest.writeString(image);
	}
	
	public static TVProgramsModel fromJson(String jsonText) {
		ObjectMapper mapper = new ObjectMapper();
		TVProgramsModel model = null;
		try {
			model = mapper.readValue(jsonText, TVProgramsModel.class);
			System.out.println(model);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return model;
	}
	
	public static final Parcelable.Creator<TVProgramsModel> CREATOR = new Parcelable.Creator<TVProgramsModel>() {
		@Override
		public TVProgramsModel createFromParcel(Parcel in) {
			return new TVProgramsModel(in);
		}

		@Override
		public TVProgramsModel[] newArray(int size) {
			return new TVProgramsModel[size];
		}
	};

}
