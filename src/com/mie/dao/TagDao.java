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
	private ReviewDao ReviewDao;
	
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
	
	public void updateTagPostNum() {
		/**
		 * LATER
		 */
	}
	public List<Tag> getSimilarTags(String keywords){
		/**
		 *SHould i use java to get the top 10 or use sql to do this hmm....
		 */
		List<Tag> similarTags = new ArrayList<Tag>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select TagName FROM Tag where TagName LIKE ?");
			preparedStatement.setString(1, keywords);
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
	
	public List<Integer> getTagReviewIdByKeyword(String tagname) {
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
