package com.mie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

import com.mie.model.Review;
import com.mie.model.Tag;
import com.mie.util.DbUtil;

public class TagDao {
	/**
	 * This class handles all of the Student-related methods
	 * (add/get).
	 */

	private Connection connection;
	//private ReviewDao ReviewDao;
	
	public TagDao() {
		/**
		 * Get the database connection.
		 */
		connection = DbUtil.getConnection();
	}
	
	public void addTag(Tag tag) {
		/**
		 * This method adds a new student to the database.
		 * 
		 * Question: if anyone add tag the user that start this tag should be automatically follow the tag that they created.
		 * this also have to update uses tag since we create tag by creating review. 
		 * 
		 * Also will tag case sensitive?
		 * 
		 */
		try {
			//edit Tag table
			PreparedStatement preparedStatement = connection.prepareStatement("insert into Tag(TagName,NumPosts) values (?, ? )");
			preparedStatement.setString(1, tag.getTagName());
			preparedStatement.setInt(2, tag.getNumPosts());
			
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void updateTagPostNum(String TagName) {
		/**
		 * Update by checking uses tag table but hmmm this whould be done periodically? 
		 */
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Tag "
					+ "set NumPosts = (Select Count(ReviewID) from UsesTag where TagName = ?)"
					+ " where TagName =  ?;");
			preparedStatement.setString(1, TagName );
			preparedStatement.setString(2, TagName );
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//update uses tag
	public void updateUsesTag(int ReviewID, String TagName) {
		try {
			//edit Tag table
			PreparedStatement preparedStatement = connection.prepareStatement("insert into UsesTag(ReviewID,TagName) values (?, ? )");
			preparedStatement.setInt(1, ReviewID);
			preparedStatement.setString(2, TagName);
			
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	//if the thing is new and creating the actual tag the user have to follow that tag.
	
	
	//check if the tag exist
	public boolean tagExist(String tagName) {
		
		boolean exist = false;
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(TagName) as Exist from Tag where TagName=?");
			preparedStatement.setString(1,  tagName);
			ResultSet rs = preparedStatement.executeQuery();
			
			if(rs.getInt("NumPosts") > 0) {
				exist = true;
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return exist;
	}
	//following from other user
	
	public void removeUsesTags(int reviewID) {
		try {
			//edit Tag table
			PreparedStatement preparedStatement = connection.prepareStatement("delete from UsesTags where ReviewID=?");
			preparedStatement.setInt(1, reviewID);
			
			preparedStatement.executeUpdate();

		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public List<Tag> getSimilarTags(String keywords){
		/**
		 *SHould i use java to get the top 10 or use sql to do this hmm....
		 */
		List<Tag> similarTags = new ArrayList<Tag>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select TagName FROM Tag where TagName LIKE ?");
			preparedStatement.setString(1,  "%" + keywords + "%");
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				Tag tag = new Tag();
				tag.setTagName(rs.getString("TagName"));
				tag.setNumPost(rs.getInt("NumPosts"));
				similarTags.add(tag);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return similarTags;
	}
	
	public List<Integer> getTagReviewId(String tagname) {
		/**
		 * This method get review that uses certain tag names
		 * 
		 * Currently not used in the sample web app, but code is left here for
		 * your review.
		 * 
		 * getting all the 
		 * 
		 * 
		 * maybe use like thing 
		 */

		
		List<Integer> allTagReviewIDs = new ArrayList<Integer>();

		try {

			PreparedStatement preparedStatement = connection.prepareStatement("select ReviewID from UsesTag where TagName=?");
			preparedStatement.setString(1, tagname);
			ResultSet rs = preparedStatement.executeQuery();

			while (rs.next()) {
				allTagReviewIDs.add(rs.getInt("ReviewID"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
		return allTagReviewIDs;
	}
	
	
	public List<Tag> getTrendingTags(){
		/**
		 *SHould i use java to get the top 10 or use sql to do this hmm....
		 */
		List<Tag> topTags = new ArrayList<Tag>();
		try {
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery("select TOP 10 * FROM Tag ORDER BY NumPosts DESC");
			
			while (rs.next()) {
				Tag tag = new Tag();
				tag.setTagName(rs.getString("TagName"));
				tag.setNumPost(rs.getInt("NumPosts"));
				topTags.add(tag);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return topTags;
		
	}

}
