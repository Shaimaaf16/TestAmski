package com.novelty.imsakia.dataobjects;

import java.io.IOException;
import java.util.ArrayList;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;

import android.util.Log;

public class Vendor {

	@JsonProperty("id")
	String id;
	@JsonProperty("name")
	String name;
	@JsonProperty("description")
	String description;
	@JsonProperty("TotalFollows")
	String totalFollows;
	@JsonProperty("Images")
	ArrayList<VendorImage> images;

	public static Vendor FromJson(String jsonText) {
		ObjectMapper mapper = new ObjectMapper();
		Vendor vendor = null;
		try {
			vendor = mapper.readValue(jsonText, Vendor.class);
			Log.d("Vendor ", vendor.images.size() + "");
			System.out.println(vendor);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return vendor;
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

	public String getTotalFollows() {
		return totalFollows;
	}

	public void setTotalFollows(String totalFollows) {
		this.totalFollows = totalFollows;
	}

	public ArrayList<VendorImage> getImages() {
		return images;
	}

	public void setImages(ArrayList<VendorImage> images) {
		this.images = images;
	}
}
