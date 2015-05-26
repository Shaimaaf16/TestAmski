package com.novelty.imsakia.model;
/**
 * 
 * @author M.Turki
 *
 */
public class SurahAndChapterModel {
	String name, id, pageNum, chapterName, ayas;
	boolean isScetion = false;

	public boolean isScetion() {
		return isScetion;
	}

	public void setScetion(boolean isScetion) {
		this.isScetion = isScetion;
	}

	public String getAyas() {
		return ayas;
	}

	public void setAyas(String ayas) {
		this.ayas = ayas;
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

	public String getChapterName() {
		return chapterName;
	}

	public void setChapterName(String chapterName) {
		this.chapterName = chapterName;
	}
	
	
}
