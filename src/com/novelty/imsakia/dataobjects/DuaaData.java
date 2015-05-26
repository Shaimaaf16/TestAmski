package com.novelty.imsakia.dataobjects;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;

import android.os.Parcel;
import android.os.Parcelable;

public class DuaaData implements Parcelable {

	@JsonProperty("name")
	String name;
	@JsonProperty("description")
	String description;

	public DuaaData() {

	}

	protected DuaaData(Parcel in) {
		name = in.readString();
		description = in.readString();
	}

	@Override
	public int describeContents() {
		return 0;
	}

	@Override
	public void writeToParcel(Parcel dest, int flags) {
		dest.writeString(name);
		dest.writeString(description);
	}

	@SuppressWarnings("unused")
	public static final Parcelable.Creator<DuaaData> CREATOR = new Parcelable.Creator<DuaaData>() {
		@Override
		public DuaaData createFromParcel(Parcel in) {
			return new DuaaData(in);
		}

		@Override
		public DuaaData[] newArray(int size) {
			return new DuaaData[size];
		}
	};

	public static DuaaData fromJson(String jsonText) {
		ObjectMapper mapper = new ObjectMapper();
		DuaaData duaa = null;
		try {
			duaa = mapper.readValue(jsonText, DuaaData.class);
			System.out.println(duaa);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return duaa;
	}

	public String getName() {
		return name;
	}

	public void setName(String title) {
		this.name = title;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
