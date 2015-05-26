package com.novelty.imsakia.dataobjects;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.JsonParseException;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;

import android.util.Log;

public class Zone {
	// ":[{"Id":"2971","Name":"&Aacute;lava","CountryId":"195"}
	@JsonProperty("Id")
	String id;
	@JsonProperty("Name")
	String name;
	@JsonProperty("CountryId")
	String CountryId;

	public static Zone FromJson(String jsonText) {
		ObjectMapper mapper = new ObjectMapper();
		Zone zone = null;

		try {
			zone = mapper.readValue(jsonText, Zone.class);
			Log.d("Xone Country Id ", zone.CountryId);
			// display to console
			System.out.println(zone);
		} catch (JsonGenerationException e) {
			e.printStackTrace();

		} catch (JsonParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (JsonMappingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return zone;
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

	public String getCountryId() {
		return CountryId;
	}

	public void setCountryId(String countryId) {
		CountryId = countryId;
	}
	
	
}
