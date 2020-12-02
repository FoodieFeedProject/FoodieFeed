package com.mie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.mie.model.*;
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

	public int addReview(Review review) {
		/**
		 * This method adds a new review to the database.
		 */
		int reviewID = 0;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into Review(Title,OverallRating,Description,FoodRating,ServiceRating,EnvironmentRating,DineIn,NumLikes,PhotoURL) values (?, ?, ?, ?, ?, ?, ?, ?, ? )");
			
			preparedStatement.setString(1, review.getTitle());
			preparedStatement.setInt(2, review.getOverallRating());
			preparedStatement.setString(3, review.getDescription());
			preparedStatement.setInt(4, review.getFoodRating());
			preparedStatement.setInt(5, review.getServiceRating());
			preparedStatement.setInt(6, review.getEnvironmentRating());
			preparedStatement.setInt(7, review.getDineIn()); //1 or 0
			preparedStatement.setInt(8, 0); //new post has 0 likes
			preparedStatement.setString(9, review.getPhotoURL());
			preparedStatement.executeUpdate();
			
			PreparedStatement preparedStatement2 = connection
					.prepareStatement("select ReviewID from Review where Title=? and OverallRating=? and Description=?");

			preparedStatement2.setString(1, review.getTitle());
			preparedStatement2.setInt(2, review.getOverallRating());
			preparedStatement2.setString(3, review.getDescription());
			ResultSet rs = preparedStatement2.executeQuery();
			
			while (rs.next()){
				reviewID =  rs.getInt("ReviewID");
				return reviewID;
			}
						
		} catch (SQLException e) {
			e.printStackTrace();
		}
		//returns 0 if it didn't work
		return reviewID;
	}

	public void deleteReview(int reviewID) {
		/**
		 * This method deletes a review from the database. 
		 */
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("delete from Review where ReviewID=?");
			
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
					.prepareStatement("update Review set Title=?, OverallRating=?, Description=?, FoodRating=?, ServiceRating =?, EnvironmentRating=?, DineIn=?, PhotoURL=?"
							+ " where ReviewID=?");
			
			preparedStatement.setString(1, review.getTitle());
			preparedStatement.setInt(2, review.getOverallRating());
			preparedStatement.setString(3, review.getDescription());
			preparedStatement.setInt(4, review.getFoodRating());
			preparedStatement.setInt(5, review.getServiceRating());
			preparedStatement.setInt(6, review.getEnvironmentRating());
			preparedStatement.setInt(7, review.getDineIn()); // 1 or 0
			//preparedStatement.setInt(8, review.getNumLikes());
			preparedStatement.setString(8, review.getPhotoURL());
			preparedStatement.setInt(9, review.getReviewID());

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
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
				
				
				//call methods from this class to fill these attributes
				review.setUsername(this.getPostOwner(reviewID));
				review.setNameOfUser(this.getPostOwnerName(review.getUsername()));
				review.setMyOrder(this.getWholeOrder(reviewID));
				review.setComments(this.getComments(reviewID));
				review.setTags(this.getTagsUsed(reviewID));
				review.setUploadDate(this.getUploadDate(reviewID));
				
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return review;
	}

	public List<Review> getReviewByKeyword(String keyword) {
		/**
		 * This method retrieves a list of reviews that matches the keyword
		 * entered by the user. Currently not used in the search functions.
		 */
		List<Review> results = new ArrayList<Review>();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from Review where Title LIKE ? OR Description LIKE ?");

			preparedStatement.setString(1, "%" + keyword + "%");
			preparedStatement.setString(2, "%" + keyword + "%");

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				/*Review review = new Review();
				review.setReviewID(rs.getInt("ReviewID"));
				review.setTitle(rs.getString("Title"));
				review.setOverallRating(rs.getInt("OverallRating"));
				review.setDescription(rs.getString("Description"));
				review.setFoodRating(rs.getInt("FoodRating"));
				review.setServiceRating(rs.getInt("ServiceRating"));
				review.setEnvironmentRating(rs.getInt("EnvironmentRating"));
				review.setDineIn(rs.getInt("DineIn"));
				review.setNumLikes(rs.getInt("NumLikes"));
				review.setPhotoURL(rs.getString("PhotoURL"));*/
				
				results.add(this.getReviewById(rs.getInt("ReviewID")));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return results;
	}


	public List<Review> getReviewsByUser(String username) {
		/**
		 * This method retrieves a list of reviews posted by a particular user.
		 */
		List<Review> results = new ArrayList<Review>();
		
		//get review ids belonging to that user
		List<Integer> ids = this.getUsersReviewIDs(username);
		for (int id: ids){
			//add each review to the results list
			results.add(this.getReviewById(id));
		}
		return results;
	}
	
	//MyOrder methods
	public void addMyOrder (MyOrder myOrder){
		/**
		 * This method adds a single entry to the MyOrder relation.
		 */
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into MyOrder(ReviewID,Item,Price) values (?, ?, ?)");
					
			preparedStatement.setInt(1, myOrder.getReviewID());
			preparedStatement.setString(2, myOrder.getItem());
			preparedStatement.setDouble(3, myOrder.getPrice());
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	public List<MyOrder> getWholeOrder(int reviewID){
		/**
		 * This method retrieves all MyOrder items belonging to a review.
		 */
		
		List<MyOrder> results = new ArrayList<MyOrder>();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from MyOrder where ReviewID=?");

			preparedStatement.setInt(1, reviewID);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				MyOrder order = new MyOrder();
				order.setReviewID(rs.getInt("ReviewID"));
				order.setItem(rs.getString("Item"));
				order.setPrice(rs.getDouble("Price"));
				results.add(order);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return results;
	}
	
	public void deleteMyOrder (int reviewID){
		/**
		 * This method deletes the MyOrder entries when a Review is being deleted.
		 */
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("delete from MyOrder where ReviewID=?");
		
			preparedStatement.setInt(1, reviewID);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	//tag methods 
	public List<Tag> getTagsUsed (int reviewID) {
		/**
		 * This method gets a list of tags used by a review
		 */
		List<Tag> tagsUsed = new ArrayList<Tag>();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select TagName from UsesTag where ReviewID=?");
			preparedStatement.setInt(1, reviewID);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				//tagsUsed.add(rs.getString("TagName"));
				Tag tag = new Tag();
				tag.setTagName(rs.getString("TagName"));
				PreparedStatement preparedStatement2 = connection
						.prepareStatement("select NumPosts from Tag where TagName=?");
				preparedStatement2.setString(1, rs.getString("TagName"));
				ResultSet rs2 = preparedStatement2.executeQuery();
				if (rs2.next()){
					tag.setNumPost(rs2.getInt("NumPosts"));
				}
				
				tagsUsed.add(tag);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tagsUsed;
	
	}
	//Posts methods
	public void recordNewUpload(String username, int reviewID, String date){
		/**
		 * This method records a post in the Posts relation.
		 */
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into Posts(Username,ReviewID,DateTime) values (?, ?, ?)");
					
			preparedStatement.setString(1, username);
			preparedStatement.setInt(2, reviewID);
			preparedStatement.setString(3, date);
			
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void removeUploadRecord(int reviewID){
		/**
		 * This method deletes the post record from the Posts relation
		 */
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("delete from Posts where ReviewID=?");
		
			preparedStatement.setInt(1, reviewID);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
	public String getPostOwner (int reviewID){
		/**
		 * This method returns the post owner's username.
		 */
		String owner ="";
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select Username from Posts where ReviewID=?");
			preparedStatement.setInt(1, reviewID);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				owner = rs.getString("Username");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return owner;
	}
	public String getPostOwnerName (String username){
		/**
		 * This method returns the post owner's Name, given their username (called after getPostOwner).
		 */
		String name ="";
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select Name from User where Username=?");
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				name = rs.getString("Name");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return name;
	}
	public String getUploadDate (int reviewID){
		/**
		 * This method returns the post owner's Name, given their username (called after getPostOwner).
		 */
		String date ="";
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select DateTime from Posts where ReviewID=?");
			preparedStatement.setInt(1, reviewID);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				date = rs.getString("DateTime");
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return date;
	}
	public List<Integer> getUsersReviewIDs(String username){
		/**
		 * This method returns a list of all reviewIDs belonging to one user.
		 */
		List<Integer> results = new ArrayList<Integer>();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select ReviewID from Posts where Username=?");
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();
			while(rs.next()){
				results.add(rs.getInt("ReviewID"));
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return results;
	}
	
	//comments methods
	public List<Comment> getComments(int reviewID){
		/**
		 * This method retrieves all comments on a review.
		 */	
		List<Comment> results = new ArrayList<Comment>();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from CommentsOn where ReviewID=?");

			preparedStatement.setInt(1, reviewID);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				Comment comment = new Comment();
				comment.setReviewID(rs.getInt("ReviewID"));
				comment.setUser(rs.getString("Username"));
				comment.setDate(rs.getString("DateTime"));
				comment.setComment(rs.getString("Comment"));			
				results.add(comment);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return results;
	}
	public void addComment (Comment comment){
		/**
		 * This method adds a comment to a review.
		 */
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into CommentsOn(Username,ReviewID,DateTime,Comment) values (?, ?, ?, ?)");
			
			preparedStatement.setString(1, comment.getUser());
			preparedStatement.setInt(2, comment.getReviewID());
			preparedStatement.setString(3, comment.getDate());
			preparedStatement.setString(4, comment.getComment());
	
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void deleteAllComments (int reviewID){
		/**
		 * This method deletes all comments when a review is being deleted.
		 */
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("delete from CommentsOn where ReviewID=?");
			
			preparedStatement.setInt(1, reviewID);
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	public void likeReview(int reviewID){
		/**
		 * This method adds a like to a Review
		 */	
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select NumLikes from Review where ReviewID=?");

			preparedStatement.setInt(1, reviewID);
			ResultSet rs = preparedStatement.executeQuery();
			int likes = 0;
			if (rs.next()) {
				likes = rs.getInt("NumLikes");
			}
			//add one like
			likes +=1;
			
			PreparedStatement preparedStatement2 = connection
					.prepareStatement("update Review set NumLikes=? where ReviewID=?");
			
			preparedStatement2.setInt(1, likes);
			preparedStatement2.setInt(2, reviewID);
			preparedStatement2.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	//not used for now.
	public List<Review> getAllReviews() {
		/**
		 * This method returns the list of all reviews in the form of a List
		 * object. Not used by the controllers.
		 */
		List<Review> allReviews = new ArrayList<Review>();
		try {
			Statement statement = connection.createStatement();
		
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


}