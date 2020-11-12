package com.mie.model;

public class Comment {
	
	private String username;
	private int reviewID;
	private String date;
	private String comment;
	
	public void setUser(String username){
		this.username = username;
	}
	public String getUser(){
		return username;
	}
	public void setReviewID(int reviewID){
		this.reviewID = reviewID;
	}
	public int getReviewID(){
		return reviewID;
	}
	public void setDate(String date){
		this.date = date;
	}
	public String getDate(){
		return date;
	}
	public void setComment(String comment){
		this.comment = comment;
	}
	public String getComment(){
		return comment;
	}

}
