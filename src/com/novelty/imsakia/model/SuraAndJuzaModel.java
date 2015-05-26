package com.novelty.imsakia.model;

public class SuraAndJuzaModel {
	String name;
	String id;
	String pageNum;
	String Juza_name;
	boolean isScetion=false;
	public boolean isScetion() {
		return isScetion;
	}
	public void setScetion(boolean isScetion) {
		this.isScetion = isScetion;
	}
	public String getJuza_name() {
		return Juza_name;
	}
	public void setJuza_name(String juza_name) {
		Juza_name = juza_name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getPageNum() {
		return pageNum;
	}
	public void setPageNum(String pageNum) {
		this.pageNum = pageNum;
	}
}
