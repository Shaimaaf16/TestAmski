package com.novelty.imsakia.dataobjects;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;

import android.util.Log;

public class District {
	
	@JsonProperty("Id")
	String  id;
	@JsonProperty("Name")
	String name;
	@JsonProperty("ZoneId")
	String ZoneId;
	
	public static District FromJson(String jsonText) {
		ObjectMapper mapper = new ObjectMapper();
		District district = null;

		try {
			district = mapper.readValue(jsonText, District.class);
			Log.d("District ", district.ZoneId);
			// display to console
			System.out.println(district);
		} catch (JsonGenerationException e) {

			e.printStackTrace();

		}

		catch (IOException e) {

			e.printStackTrace();

		}

		return district;
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

	public String getZoneId() {
		return ZoneId;
	}

	public void setZoneId(String zoneId) {
		ZoneId = zoneId;
	}
	

	
}
