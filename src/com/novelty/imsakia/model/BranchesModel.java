package com.novelty.imsakia.model;

import java.io.Serializable;

public class BranchesModel implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 5452070256473206047L;
	
	String name;
	String address;
	String imageUrl;
	int workingHour;
	String phone;
	
	public BranchesModel(String name, String address, String imageUrl,
			int workingHour, String phone) {
		super();
		this.name = name;
		this.address = address;
		this.imageUrl = imageUrl;
		this.workingHour = workingHour;
		this.phone = phone;
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
	public String getImageUrl() {
		return imageUrl;
	}
	public void setImageUrl(String imageUrl) {
		this.imageUrl = imageUrl;
	}
	public int getWorkingHour() {
		return workingHour;
	}
	public void setWorkingHour(int workingHour) {
		this.workingHour = workingHour;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
}
