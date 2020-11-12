package com.mie.model;


public class MyOrder {
	
	private int reviewID;
	private String item;
	private double price;
	
	public void setReviewID(int reviewID){
		this.reviewID = reviewID;
	}
	public int getReviewID(){
		return reviewID;
	}
	public void setItem(String item){
		this.item = item;
	}
	public String getItem(){
		return item;
	}
	public void setPrice(double price){
		this.price = price;
	}
	public double getPrice(){
		return price;
	}

	
	
	
}
