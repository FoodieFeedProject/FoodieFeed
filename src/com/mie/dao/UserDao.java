package com.mie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mie.model.*;
import com.mie.util.DbUtil;

public class UserDao {
	private Connection connection;

	public UserDao() {
		/**
		 * Get the database connection.
		 */
		connection = DbUtil.getConnection();
	}

	public void addUser(User user) {
		/**
		 * This method adds a new User to the database.
		 */
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into User(Username,Email,Password,Name,Bio,ProfilePic) values (?, ?, ?, ?, ?,? )");
			// Parameters start with 1
			preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(2, user.getEmail());
			preparedStatement.setString(3, user.getPassword());
			preparedStatement.setString(4, user.getName());
			preparedStatement.setString(5, user.getBio());
			preparedStatement.setString(6, user.getProfilePic());
			

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	
	public void updateUser(User user) {
		/**
		 * This method updates a user's information into the database.
		 */
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update User set Email=?, Password=?, Name=?, Bio=?, ProfilePic=?"
							+ " where Username=?");

			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getName());
			preparedStatement.setString(4, user.getBio());
			if(user.getProfilePic() == null){
				preparedStatement.setString(5, "");
			}else{
				preparedStatement.setString(5, user.getProfilePic());
			}
			preparedStatement.setString(6, user.getUsername());
			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<User> getAllUsers() {
		/**
		 * This method returns the list of all users in the form of a List
		 * object.
		 */
		List<User> users = new ArrayList<User>();
		try {
			Statement statement = connection.createStatement();
			
			ResultSet rs = statement.executeQuery("select * from User");
			while (rs.next()) {
				User user = new User();
				user.setUsername(rs.getString("Username"));
				user.setEmail(rs.getString("Email"));
				user.setPassword(rs.getString("Password"));
				user.setName(rs.getString("Name"));
				user.setBio(rs.getString("Bio"));
				user.setProfilePic(rs.getString("ProfilePic"));
				
				user.setFollowing(this.getFollowing(user.getUsername()));
				user.setFollowers(this.getFollowers(user.getUsername()));
				user.setTagFollow(this.getTagsFollowed(user.getUsername()));

				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}

	public User getUserByUsername(String username) {
		/**
		 * This method retrieves a user by their username.
		 */
		User user = new User();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from User where Username=?");
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();

			if (rs.next()) {
				user.setUsername(rs.getString("Username"));
				user.setEmail(rs.getString("Email"));
				user.setPassword(rs.getString("Password"));
				user.setName(rs.getString("Name"));
				user.setBio(rs.getString("Bio"));
				user.setProfilePic(rs.getString("ProfilePic"));
				
				user.setFollowing(this.getFollowing(username));
				user.setFollowers(this.getFollowers(username));
				user.setTagFollow(this.getTagsFollowed(username));
				
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return user;
	}

	public List<User> getUserByKeyword(String keyword) {
		/**
		 * This method retrieves a list of User that matches the keyword(UserName)
		 * entered by the user.
		 */
		List<User> users = new ArrayList<User>();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from User where Username LIKE ?");

			preparedStatement.setString(1, "%" + keyword + "%");
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				User user = new User();
				user.setUsername(rs.getString("Username"));
				user.setEmail(rs.getString("Email"));
				user.setPassword(rs.getString("Password"));
				user.setName(rs.getString("Name"));
				user.setBio(rs.getString("Bio"));
				user.setProfilePic(rs.getString("ProfilePic"));
				
				user.setFollowing(this.getFollowing(user.getUsername()));
				user.setFollowers(this.getFollowers(user.getUsername()));
				user.setTagFollow(this.getTagsFollowed(user.getUsername()));
				
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}
	
	public List<String> getFollowers(String username){
		/**
		 * Returns a list of followers
		 */

		List<String> followers = new ArrayList<String>();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select Follower from UserFollow where Username=?");
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
	
				followers.add(rs.getString("Follower"));
				
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return followers;
	}
	
	public List<String> getFollowing(String username){
		/**
		 * Returns a list of users that the given user is following
		 */
		
		List<String> following = new ArrayList<String>();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select Username from UserFollow where Follower=?");
			preparedStatement.setString(1, username);
		
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				following.add(rs.getString("Username"));
				
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return following;
	}
	
	public List<String> getTagsFollowed(String usename){
		/**
		 * Returns a list of TagNames followed by the user
		 */

		List<String> tagsFollowed = new ArrayList<String>();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select TagName from TagFollow where Username=?");
			preparedStatement.setString(1, usename);
			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				tagsFollowed.add(rs.getString("TagName"));
				
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return tagsFollowed;
	}
	
	public void followUnfollowUser(String username1, String username2, String status) {
		/**
		 * This method allows username1 to follow or unfollow username 2
		 */
		try {
			if(status == "Follow"){
				PreparedStatement preparedStatement = connection
						.prepareStatement("insert into UserFollow(Username,Follower) values (?, ?)");
				
				//follow the user
				preparedStatement.setString(1, username2);
				preparedStatement.setString(2, username1);
				

				preparedStatement.executeUpdate();
			
			}else{ 
				PreparedStatement preparedStatement = connection
						.prepareStatement("DELETE FROM UserFollow WHERE UserName=? AND Follower=?");
				
				preparedStatement.setString(1, username2);
				preparedStatement.setString(2, username1);
				
				//unfollow the user
				preparedStatement.executeUpdate();
				
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public String getFollowButtonStatus (String currentUser, String otherUser){
		/**
		 * This method returns the follow button message on another users profile
		 */
		String message = "";
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("Select Count(Username) as Result FROM UserFollow WHERE UserName=? AND Follower=?");
			
			preparedStatement.setString(1, otherUser);
			preparedStatement.setString(2, currentUser);
			
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				int count = rs.getInt("Result");
				if (count == 0)
					message =  "Follow";
				else
					message =  "Unfollow";					
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return message;
	}
	public String getTagFollowButtonStatus (String currentUser, String tagName){
		/**
		 * This method returns the message of the follow button on a Tag's page
		 */
		tagName = tagName.replaceAll("#","");
		String message = "";
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("Select Count(Username) as Count FROM TagFollow WHERE UserName=? AND TagName=?");
			
			preparedStatement.setString(1, currentUser);
			preparedStatement.setString(2, tagName);
			
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				int count = rs.getInt("Count");
				if (count == 0)
					message =  "Follow";
				else
					message =  "Unfollow";					
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return message;
	}
	public boolean checkUsername (String desiredUsername){
		/**
		 * This method checks if a username is unique 
		 */
		boolean canUse = false;
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("Select Count(Username) as Count FROM User WHERE UserName=?");
			
			preparedStatement.setString(1, desiredUsername);
					
			ResultSet rs = preparedStatement.executeQuery();
			if(rs.next()){
				int count = rs.getInt("Count");
				if (count == 0)
					canUse =  true;				
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return canUse;
	}
	
	
	
	public void followUnfollowTag(String username, String tagName, String status) {
		/**
		 * This method allows a user to follow or unfollow a tag
		 */
		tagName = tagName.replaceAll("#","");
		try {
			if(status.equals("Follow")){
				PreparedStatement preparedStatement = connection
						.prepareStatement("insert into TagFollow(Username,TagName) values (?, ?)");
			
				preparedStatement.setString(1, username);
				preparedStatement.setString(2, tagName);
				
				//follow the tag
				preparedStatement.executeUpdate();
			}else{
				PreparedStatement preparedStatement = connection
						.prepareStatement("DELETE FROM TagFollow WHERE UserName=? AND TagName=?");
				
				preparedStatement.setString(1, username);
				preparedStatement.setString(2, tagName);
				
				//unfollow tag
				preparedStatement.executeUpdate();
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public List<String> getSimilarUserName(String keywords){
		/**
		 * this method is to get similar UserNames that contains string 'keyword'
		 */
		List<String> similarUserName = new ArrayList<String>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select Username FROM User where UserName LIKE ?");
			preparedStatement.setString(1, keywords);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				
				similarUserName.add(rs.getString("Username"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return similarUserName;
	}
	
	/**
	 * This method handles the user objects and the login component of the web
	 * app.
	 */
	static Connection currentCon = null;
	static ResultSet rs = null;

	public static User login(User user) {

		/**
		 * This method attempts to find the user that is trying to log in by
		 * first retrieving the username and password entered by the user.
		 */
		Statement stmt = null;

		String username = user.getUsername();
		String password = user.getPassword();

		/**
		 * Prepare a query that searches the user table in the database
		 * with the given username and password.
		 */
		String searchQuery = "select * from User where Username='"
				+ username + "' AND Password='" + password + "'";

		try {
			// connect to DB
			currentCon = DbUtil.getConnection();
			stmt = currentCon.createStatement();
			rs = stmt.executeQuery(searchQuery);
			boolean more = rs.next();

			/**
			 * If there are no results from the query, set the user to false.
			 * The person attempting to log in will be redirected to the home
			 * page when isValid is false.
			 */
			
			if (!more) {
				user.setValid(false);
			}

			/**
			 * If the query results in an database entry that matches the
			 * username and password, assign the appropriate information to
			 * the User object.
			 */
			else if (more) {
				user.setUsername(rs.getString("Username"));
				user.setEmail(rs.getString("Email"));
				user.setPassword(rs.getString("Password"));
				user.setName(rs.getString("Name"));
				user.setBio(rs.getString("Bio"));
				user.setProfilePic(rs.getString("ProfilePic"));

				user.setValid(true);
			}
		}

		catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! "
					+ ex);
		}
		/**
		 * Return the User object.
		 */
		return user;

	}
}
