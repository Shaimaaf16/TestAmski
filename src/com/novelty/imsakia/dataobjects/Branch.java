package com.novelty.imsakia.dataobjects;

import java.io.IOException;
import java.io.Serializable;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.annotate.JsonProperty;
import org.codehaus.jackson.map.ObjectMapper;

public class Branch implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5452070256473206047L;
	
	
	@JsonProperty("id")
	String id;
	@JsonProperty("name")
	String name;

	@JsonProperty("address")
	String address;
	

	@JsonProperty("open")
	String open;

	@JsonProperty("comment")
	String comment;

	@JsonProperty("telephone")
	String telephone;
	

	@JsonProperty("email")
	String email;
	@JsonProperty("fax")
	String fax;
	
	@JsonProperty("geocode")
	String geocode;
	
	public static Branch fromJson(String jsonText) {
		ObjectMapper mapper = new ObjectMapper();
		Branch branch = null;
		try {
			branch = mapper.readValue(jsonText, Branch.class);
			System.out.println();
		} catch (JsonGenerationException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return branch;
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

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getOpen() {
		return open;
	}

	public void setOpen(String open) {
		this.open = open;
	}

	public String getComment() {
		return comment;
	}

	public void setComment(String comment) {
		this.comment = comment;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	public String getFax() {
		return fax;
	}

	public void setFax(String fax) {
		this.fax = fax;
	}

	public String getGeocode() {
		return geocode;
	}

	public void setGeocode(String geocode) {
		this.geocode = geocode;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

 
}
