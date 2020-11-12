package com.mie.model;
import java.util.ArrayList;
import java.util.List;
import java.util.HashMap;
import java.util.Map;
import java.util.Iterator;
//import java.util.Date;

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
	
	//not sure about these yet
	private String username; //username of review owner
	private List<MyOrder> myOrder;
	private List<Comment> comments; 
	private List<String> tags; //list of tagnames used by the post

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
	public void setTags (List<String> tags){
		this.tags  = tags;
	}
	public Iterator<String> getTags (){
		return tags.iterator();
	}
	public void setMyOrder (List<MyOrder> myOrder){
		this.myOrder = myOrder;
	}
	public Iterator getMyOrder (){
		return myOrder.iterator();
	}
	

	@Override
	public String toString() {
		return "Review [ReviewID=" + reviewID + ", Title=" + title
				+ ", Overall Rating=" + overallRating + ", Description=" + description + ", Food Rating="
				+ foodRating + ", Service Rating=" + serviceRating + ",Environment Rating=" + environmentRating
				+ ", Dine In=" + dineIn +", Likes=" + numLikes + "]";
	}
}