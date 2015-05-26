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
    String TotalFollows;
	@JsonProperty("Images")
    ArrayList<VendorImage>images;

	public static Vendor FromJson(String jsonText) {
		ObjectMapper mapper = new ObjectMapper();
		Vendor vendor = null;

		try {
			vendor = mapper.readValue(jsonText, Vendor.class);
			Log.d("Vendor ", vendor.images.size()+"");
			// display to console
			System.out.println(vendor);
		} catch (JsonGenerationException e) {

			e.printStackTrace();

		}

		catch (IOException e) {

			e.printStackTrace();

		}

		return vendor;
	}


}
