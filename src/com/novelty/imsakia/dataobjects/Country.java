package com.novelty.imsakia.dataobjects;

import java.io.IOException;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;

import android.util.Log;

public class Country {
	@JsonProperty("Id")
	String Id;
	@JsonProperty("Name")
	String Name;
	@JsonProperty("Code")
	String Code;

	public static Country countryFromJson(String jsonText) {
		ObjectMapper mapper = new ObjectMapper();
		Country country = null;

		try {
			country = mapper.readValue(jsonText, Country.class);
			Log.d("Countr ", country.Code);
			// display to console
			System.out.println(country);
		} catch (JsonGenerationException e) {

			e.printStackTrace();

		}

		catch (IOException e) {

			e.printStackTrace();

		}

		return country;
	}

	public String getId() {
		return Id;
	}

	public void setId(String id) {
		Id = id;
	}

	public String getName() {
		return Name;
	}

	public void setName(String name) {
		Name = name;
	}

	public String getCode() {
		return Code;
	}

	public void setCode(String code) {
		Code = code;
	}
}
