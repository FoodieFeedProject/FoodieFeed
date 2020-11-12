package com.mie.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.mie.model.User;
import com.mie.model.Student;
import com.mie.model.User;
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
					.prepareStatement("insert into User(Username,Email,Password,Name,Bio,ProfilePic) values (?, ?, ?, ? )");
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
			// Parameters start with 1
			//preparedStatement.setString(1, user.getUsername());
			preparedStatement.setString(1, user.getEmail());
			preparedStatement.setString(2, user.getPassword());
			preparedStatement.setString(3, user.getName());
			preparedStatement.setString(4, user.getBio());
			preparedStatement.setString(5, user.getProfilePic());
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
			// System.out.println("getting students from table");
			ResultSet rs = statement.executeQuery("select * from User");
			while (rs.next()) {
				User user = new User();
				user.setUsername(rs.getString("Username"));
				user.setEmail(rs.getString("Email"));
				user.setPassword(rs.getString("Password"));
				user.setName(rs.getString("Name"));
				user.setBio(rs.getString("Bio"));
				user.setProfilePic(rs.getString("ProfilePic"));

				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}

	public User getUserByUsername(String username) {
		/**
		 * This method retrieves a user by their userName.
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
			/*preparedStatement.setString(2, "%" + keyword + "%");
			preparedStatement.setString(3, "%" + keyword + "%");
			preparedStatement.setString(4, "%" + keyword + "%");
			preparedStatement.setString(5, "%" + keyword + "%");*/

			ResultSet rs = preparedStatement.executeQuery();
			while (rs.next()) {
				User user = new User();
				user.setUsername(rs.getString("Username"));
				user.setEmail(rs.getString("Email"));
				user.setPassword(rs.getString("Password"));
				user.setName(rs.getString("Name"));
				user.setBio(rs.getString("Bio"));
				user.setProfilePic(rs.getString("ProfilePic"));

				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		return users;
	}
	
	public List<String> getUsersFollowed(String username){
		
		// this methods is to get the follower for the user
		User user = new User();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select Follower from UserFollow where Username=?");
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				user.addFollowed(rs.getString("Follower"));
				
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return user.getFollowed();
	}
	
	public List<String> getFollowing(String username){
		// this methods is to get list of userName that the current User is following

		User user = new User();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select UserName from UserFollow where Follower=?");
			preparedStatement.setString(1, username);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				user.addFollowing(rs.getString("UserName"));
				
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return user.getFollowed();
	}
	
	public List<String> getTagFollowed(String usename){
		// this methods is to get the tag followed for the user

		User user = new User();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select TagName from TagFollow where Username=?");
			preparedStatement.setString(1, usename);
			ResultSet rs = preparedStatement.executeQuery();
			if (rs.next()) {
				user.addTagFollow(rs.getString("TagName"));
				
			}
			
		}catch (SQLException e) {
			e.printStackTrace();
		}
		return user.getTagFollow();
	}
	
	public void followUser(String username1, String username2) {
		// this methods is to allow username1 to follow username2
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into UserFollow(Username,Follower) values (?, ?)");
			// Parameters start with 1
			preparedStatement.setString(1, username2);
			preparedStatement.setString(2, username1);
			

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void unfollowUser(String username1, String username2) {
		// this methods is to allow username1 to unfollow username2

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("DELETE FROM UserFollow WHERE UserName=? AND Follower=?");
			// Parameters start with 1
			preparedStatement.setString(1, username2);
			preparedStatement.setString(2, username1);
			

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void followTag(String username, String tag) {
		// this methods is to allow user to follow a tag

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into TagFollow(Username,TagName) values (?, ?)");
			// Parameters start with 1
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, tag);
			

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	
	public void unfollowTag(String username, String tag) {
		// this methods is to allow user to unfollow a tag

		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("DELETE FROM TagFollow WHERE UserName=? AND TagName=?");
			// Parameters start with 1
			preparedStatement.setString(1, username);
			preparedStatement.setString(2, tag);
			

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public List<String> getSimilarUserName(String keywords){
		/**
		 * this method is to get similiar UserName that contains string 'keyword'
		 */
		List<String> similarUserName = new ArrayList<String>();
		try {
			PreparedStatement preparedStatement = connection.prepareStatement("select Username FROM User where UserName LIKE ?");
			preparedStatement.setString(1, keywords);
			ResultSet rs = preparedStatement.executeQuery();
			
			while (rs.next()) {
				User user = new User();
				similarUserName.add(rs.getString("UserName"));
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
