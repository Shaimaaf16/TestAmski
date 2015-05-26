package com.novelty.imsakia.dataobjects;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;

import android.util.Log;

public class Ads {

	@JsonProperty("Id")
	String id;
	@JsonProperty("ThumbUrl")
	String thumbUrl;
	@JsonProperty("Title")
	String title;
	@JsonProperty("ShortDescription")
	String shortDescription;
	@JsonProperty("Like")
	String like;
	@JsonProperty("TotalComments")
	String totalComments;
	@JsonProperty("Rate")
	String rate;
	@JsonProperty("TotalViews")
	String totalViews;

	public static Ads FromJson(String jsonText) {
		ObjectMapper mapper = new ObjectMapper();
		Ads ads = null;

		try {
			ads = mapper.readValue(jsonText, Ads.class);
			Log.d("Ads ", ads.title);
			// display to console
			System.out.println(ads);
		} catch (JsonGenerationException e) {

			e.printStackTrace();

		}

		catch (IOException e) {

			e.printStackTrace();

		}

		return ads;
	}

}
