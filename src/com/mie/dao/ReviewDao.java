package com.mie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.mie.model.Review;
import com.mie.util.DbUtil;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ReviewDao {
	/**
	 * This class handles all of the Food Review-related methods
	 * (add/update/delete/get).
	 */

	private Connection connection;

	public ReviewDao() {
		/**
		 * Get the database connection.
		 */
		connection = DbUtil.getConnection();
	}

	public void addReview(Review review) {
		/**
		 * This method adds a new review to the database.
		 */
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into Review(Title,OverallRating,Description,FoodRating,ServiceRating,EnvironmentRating,DineIn,NumLikes,PhotoURL) values (?, ?, ?, ?, ?, ?, ?, ?,?,? )");
			
			//preparedStatement.setInt(1, review.getReviewID());
			//ReviewID is an AutoNumber but how do we get it?
			preparedStatement.setString(1, review.getTitle());
			preparedStatement.setInt(2, review.getOverallRating());
			preparedStatement.setString(3, review.getDescription());
			preparedStatement.setInt(4, review.getFoodRating());
			preparedStatement.setInt(5, review.getServiceRating());
			preparedStatement.setInt(6, review.getEnvironmentRating());
			//DineIn is yes/no or 1/0
			preparedStatement.setInt(7, review.getDineIn());
			//0 likes since its new
			preparedStatement.setInt(8, 0);
			preparedStatement.setString(9, review.getPhotoURL());
			preparedStatement.executeUpdate();
			
			//need to also consider adding the myorder and tag info into the database here
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void deleteReview(int reviewID) {
		/**
		 * This method deletes a review from the database. Need to delete things in other tables too..
		 */
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("delete from Review where ReviewID=?");
			// Parameters start with 1
			preparedStatement.setInt(1, reviewID);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public void updateReview(Review review) {
		/**
		 * This method updates a review's information into the database.
		 */
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update Review set Title=?, OverallRating=?, Description=?, FoodRating=?, ServiceRating =?, EnvironmentRating=?, DineIn=?"
							+ " where ReviewID=?");
			// Parameters start with 1
			preparedStatement.setString(1, review.getTitle());
			preparedStatement.setInt(2, review.getOverallRating());
			preparedStatement.setString(3, review.getDescription());
			preparedStatement.setInt(4, review.getFoodRating());
			preparedStatement.setInt(5, review.getServiceRating());
			preparedStatement.setInt(6, review.getEnvironmentRating());
			//DineIn is yes/no or 1/0
			preparedStatement.setInt(7, review.getDineIn());
			preparedStatement.setInt(8, review.getReviewID());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public List<String> getTagsUsed (int reviewID) {
		/**
		 * This method gets a list of tags used by a review
		 */
		List<String> tagsUsed = new ArrayList<String>();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select TagName from UsesTag where ReviewID=?");
			preparedStatement.setInt(1, reviewID);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				tagsUsed.add(rs.getString("TagName"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tagsUsed;
	
	}
	public List<Review> getAllReviews() {
		/**
		 * This method returns the list of all reviews in the form of a List
		 * object.
		 */
		List<Review> allReviews = new ArrayList<Review>();
		try {
			Statement statement = connection.createStatement();
			// System.out.println("getting students from table");
			ResultSet rs = statement.executeQuery("select * from Review");
			while (rs.next()) {
				Review review = new Review();
				review.setReviewID(rs.getInt("ReviewID"));
				review.setTitle(rs.getString("Title"));
				review.setOverallRating(rs.getInt("OverallRating"));
				review.setDescription(rs.getString("Description"));
				review.setFoodRating(rs.getInt("FoodRating"));
				review.setServiceRating(rs.getInt("ServiceRating"));
				review.setEnvironmentRating(rs.getInt("EnvironmentRating"));
				review.setDineIn(rs.getInt("DineIn"));
				review.setNumLikes(rs.getInt("NumLikes"));
				review.setPhotoURL(rs.getString("PhotoURL"));
				
				allReviews.add(review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return allReviews;
	}

	public Review getReviewById(int reviewID) {
		/**
		 * This method retrieves a review by their reviewID number.
		 * 
		 *
		 */
		Review review = new Review();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from Review where ReviewID=?");
			preparedStatement.setInt(1, reviewID);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				review.setReviewID(rs.getInt("ReviewID"));
				review.setTitle(rs.getString("Title"));
				review.setOverallRating(rs.getInt("OverallRating"));
				review.setDescription(rs.getString("Description"));
				review.setFoodRating(rs.getInt("FoodRating"));
				review.setServiceRating(rs.getInt("ServiceRating"));
				review.setEnvironmentRating(rs.getInt("EnvironmentRating"));
				review.setDineIn(rs.getInt("DineIn"));
				review.setNumLikes(rs.getInt("NumLikes"));
				review.setPhotoURL(rs.getString("PhotoURL"));
			
				//get username
				//get tags used
				//get myorder
				//get comments
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return review;
	}

	public List<Review> getReviewByKeyword(String keyword) {
		/**
		 * This method retrieves a list of reviews that matches the keyword
		 * entered by the user.
		 */
		List<Review> results = new ArrayList<Review>();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from Review where Title LIKE ? OR Description LIKE ?");

			preparedStatement.setString(1, "%" + keyword + "%");
			preparedStatement.setString(2, "%" + keyword + "%");

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				Review review = new Review();
				review.setReviewID(rs.getInt("ReviewID"));
				review.setTitle(rs.getString("Title"));
				review.setOverallRating(rs.getInt("OverallRating"));
				review.setDescription(rs.getString("Description"));
				review.setFoodRating(rs.getInt("FoodRating"));
				review.setServiceRating(rs.getInt("ServiceRating"));
				review.setEnvironmentRating(rs.getInt("EnvironmentRating"));
				review.setDineIn(rs.getInt("DineIn"));
				review.setNumLikes(rs.getInt("NumLikes"));
				review.setPhotoURL(rs.getString("PhotoURL"));
				results.add(review);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return results;
	}
	//not done yet
	public List<Review> getFeed(String username, List<String> tagsFollowed, List<String> usersFollowed) {
		/**
		 * This method retrieves a list of reviews that matches the keyword
		 * entered by the user.
		 */
		List<Review> results = new ArrayList<Review>();
		//get review ids from Posts and UsesTag based on users and tags followed
		//somehow get rid of duplicates
		//then call getReviewbyID and add to the results list
	
		return results;
	}
	
	public List<Review> getReviewsByUser(String username) {
		/**
		 * This method retrieves a list of reviews posted by a particular user.
		 */
		List<Review> results = new ArrayList<Review>();
		//get review ids from Posts where Username = username
		//then call getReviewbyID and add to the results list
	
		return results;
	}
	//does this belong here?
	public void addComment (int reviewID, String username, String comment){
		/**
		 * This method adds a new review to the database.
		 */
		Date date = new Date();  
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into CommentsOn(Username,ReviewID,DateTime,Comment) values (?, ?, ?, ?)");
			
			preparedStatement.setString(1, username);
			preparedStatement.setInt(2, reviewID);
			preparedStatement.setString(3, date.toString());
			preparedStatement.setString(4, comment);
	
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}