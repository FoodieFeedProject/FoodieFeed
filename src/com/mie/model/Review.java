package com.mie.model;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;


public class Review {
	/**
	 * This class contains all of the relevant information, and getter/setter
	 * methods for the Review object.
	 */

	private int reviewID;
	private String title;
	private int overallRating;
	private String description;
	private int foodRating;
	private int serviceRating;
	private int environmentRating;
	private int dineIn;	
	private int numLikes;
	private String photoURL;
	
	private String username; //username of review owner
	private String nameOfUser; //name of review owner
	
	private List<MyOrder> myOrder;
	private List<Comment> comments; 
	private List<Tag> tags; //list of tags used by the post
	
	private String uploadDate; 

	public int getReviewID() {
		return reviewID;
	}

	public void setReviewID(int reviewID) {
		this.reviewID = reviewID;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public int getOverallRating() {
		return overallRating;
	}

	public void setOverallRating(int overall) {
		this.overallRating = overall;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getFoodRating() {
		return foodRating;
	}

	public void setFoodRating(int foodRating) {
		this.foodRating = foodRating;
	}
	public int getServiceRating() {
		return serviceRating;
	}

	public void setServiceRating(int serviceRating) {
		this.serviceRating = serviceRating;
	}
	public int getEnvironmentRating() {
		return environmentRating;
	}

	public void setEnvironmentRating(int environmentRating) {
		this.environmentRating = environmentRating;
	}
	public int getDineIn() {
		return dineIn;
	}

	public void setDineIn(int dineIn) {
		this.dineIn = dineIn;
	}
	public int getNumLikes() {
		return numLikes;
	}

	public void setNumLikes(int numLikes) {
		this.numLikes = numLikes;
	}
	public String getPhotoURL() {
		return photoURL;
	}
	public void setPhotoURL (String url){
		this.photoURL = url;
	}
	//testing this out
	public void setTags (List<Tag> tags){
		this.tags  = tags;
	}
	public List<Tag> getTags (){
		List<Tag> tagsClone = new ArrayList<Tag>(tags);
		return tagsClone;
	}
	public void setMyOrder (List<MyOrder> myOrder){
		this.myOrder = myOrder;
	}
	public List<MyOrder> getMyOrder (){
		List<MyOrder> orderClone = new ArrayList<MyOrder>(myOrder);
		return orderClone;
	}
	public double getTotalAmt(){
		double totalAmt = 0;
		for (MyOrder order:myOrder){
			totalAmt+= order.getPrice();
		}
		return totalAmt;
	}
	public void setUsername(String username){
		this.username = username;
	}
	public String getUsername(){
		return username;
	}
	public void setNameOfUser(String name){
		this.nameOfUser = name;
	}
	public String getNameOfUser(){
		return nameOfUser;
	}
	public void setUploadDate(String date){
		this.uploadDate = date;
	}
	public String getUploadDate(){
		return uploadDate;
	}
	public void setComments(List<Comment> comments){
		this.comments = comments;
	}
	public List<Comment> getComments(){
		List<Comment> commentsClone = new ArrayList<Comment>(comments);
		return commentsClone;
	}
	

	@Override
	public String toString() {
		return "Review [ReviewID=" + reviewID + ", Title=" + title
				+ ", Overall Rating=" + overallRating + ", Description=" + description + ", Food Rating="
				+ foodRating + ", Service Rating=" + serviceRating + ",Environment Rating=" + environmentRating
				+ ", Dine In=" + dineIn +", Likes=" + numLikes + "]";
	}
}