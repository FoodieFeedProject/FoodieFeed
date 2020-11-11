package com.mie.model;

public class Tag {
	
	/**
	 * This class contains all of the relevant information, and getter/setter
	 * methods for the tag object.
	 */
	
	private String tagName;
	private int numPosts;
	
	public String getTagName() {
		return tagName;
	}
	
	public void setTagName(String tagname) {
		this.tagName = tagname;
	}
	public int getNumPosts() {
		return numPosts;
	}
	public void setNumPost(int numpost) {
		this.numPosts = numpost;
	}
	@Override
	public String toString() {
		return "Tag [tagName=" + tagName + ", numPosts=" + numPosts + "]";
	}
	

}
