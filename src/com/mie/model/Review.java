package com.mie.model;

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

	@Override
	public String toString() {
		return "Review [ReviewID=" + reviewID + ", Title=" + title
				+ ", Overall Rating=" + overallRating + ", Description=" + description + ", Food Rating="
				+ foodRating + ", Service Rating=" + serviceRating + ",Environment Rating=" + environmentRating
				+ ", Dine In=" + dineIn + "]";
	}
}