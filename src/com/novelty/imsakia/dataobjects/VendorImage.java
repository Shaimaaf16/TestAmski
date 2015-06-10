package com.novelty.imsakia.dataobjects;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;

import android.util.Log;

public class VendorImage {

	@JsonProperty("path")
	String path;
	@JsonProperty("sort_order")
    String sortOrder;

	public static VendorImage FromJson(String jsonText) {
		ObjectMapper mapper = new ObjectMapper();
		VendorImage vendorImage = null;
		try {
			vendorImage = mapper.readValue(jsonText, VendorImage.class);
			Log.d("VendorImage ", vendorImage.path);
			// display to console
			System.out.println(vendorImage);
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		return vendorImage;
	}

	public String getPath() {
		return path;
	}

	public void setPath(String path) {
		this.path = path;
	}

	public String getSortOrder() {
		return sortOrder;
	}

	public void setSortOrder(String sortOrder) {
		this.sortOrder = sortOrder;
	}
	
	
}
