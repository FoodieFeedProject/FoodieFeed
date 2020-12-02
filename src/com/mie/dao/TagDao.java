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

import com.mie.model.*;
import com.mie.util.DbUtil;

public class TagDao {
	/**
	 * This class handles all of the tag-related methods
	 * (add/get).
	 */

	private Connection connection;
	
	
	public TagDao() {
		/**
		 * Get the database connection.
		 */
		connection = DbUtil.getConnection();
	}
	
	public void addTag(Tag tag) {
		/**
		 * This method adds a new tag to the database.
		 * 
		 *
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
	
	public void updateTagPostNum(String tagName) {
		/**
		 * Update by checking UsesTag table 
		 */
		tagName = tagName.replaceAll("#","");
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("UPDATE Tag "
					+ "set NumPosts = (Select Count(ReviewID) from UsesTag where TagName = ?)"
					+ " where TagName =  ?;");
			preparedStatement.setString(1, tagName );
			preparedStatement.setString(2, tagName );
			preparedStatement.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void updateUsesTag(int reviewID, String tagName) {
		/**
		 * Update UsesTag table 
		 */
		tagName = tagName.replaceAll("#","");
		try {
			//edit Tag table
			PreparedStatement preparedStatement = connection.prepareStatement("insert into UsesTag(ReviewID,TagName) values (?, ? )");
			preparedStatement.setInt(1, reviewID);
			preparedStatement.setString(2, tagName);
			
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
		

	public boolean tagExist(String tagName) {
		/**
		 * Checks if a tag exists already
		 */
		tagName = tagName.replaceAll("#","");
		boolean exist = false;
		
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(TagName) as Exist from Tag where TagName=?");
			preparedStatement.setString(1,  tagName);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				if(rs.getInt("Exist") > 0) {
					exist = true;
				}
			}
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return exist;
	}
	public int tagFollowers(String tagName) {
		/**
		 * Gets tag followers
		 */
		
		int followers = 0;
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("SELECT COUNT(Username) as Followers from TagFollow where TagName=?");
			preparedStatement.setString(1,  tagName);
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				followers = rs.getInt("Followers");
			}
			
				
			
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return followers;
	}
	
	
	public void removeUsesTags(int reviewID) {
		/**
		 * When a review is deleted, remove entries from UsesTag
		 */
		try {
			
			PreparedStatement preparedStatement = connection.prepareStatement("delete from UsesTag where ReviewID=?");
			preparedStatement.setInt(1, reviewID);
			
			preparedStatement.executeUpdate();
			
			
			
			preparedStatement.executeUpdate();

		}catch (SQLException e) {
			e.printStackTrace();
		}
		
	}
	
	public List<Tag> getSimilarTags(String keyword){
		/**
		 *Returns a list of tags similar to the searched keyword
		 */
		List<Tag> similarTags = new ArrayList<Tag>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select * FROM Tag where TagName LIKE ?");
			preparedStatement.setString(1,  "%" + keyword + "%");
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
	
	public List<Integer> getTagReviewId(String tagName) {
		/**
		 * This method get reviewIDs of posts that uses certain tag names
		 * 
		 */
		tagName = tagName.replaceAll("#","");
	
		List<Integer> allTagReviewIDs = new ArrayList<Integer>();

		try {

			PreparedStatement preparedStatement = connection.prepareStatement("select ReviewID from UsesTag where TagName=?");
			preparedStatement.setString(1, tagName);
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
		 *Returns the top ten tags with the most posts
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
