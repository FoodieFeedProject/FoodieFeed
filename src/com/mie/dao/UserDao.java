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
		 * This method updates a student's information into the database.
		 */
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("update User set Username=?, Email=?, Password=?, Name=?, Bio=?, ProfilePic=?"
							+ " where Username=?");
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

	public List<User> getAllUsers() {
		/**
		 * This method returns the list of all students in the form of a List
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
		 * This method retrieves a student by their StudentID number.
		 * 
		 * Currently not used in the sample web app, but code is left here for
		 * your review.
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
		 * This method retrieves a list of students that matches the keyword
		 * entered by the user.
		 */
		List<User> users = new ArrayList<User>();
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("select * from User where Username LIKE ? OR Email LIKE ? OR Password LIKE ? OR Name LIKE ? OR Bio LIKE ? OR ProfilePic LIKE ?");

			preparedStatement.setString(1, "%" + keyword + "%");
			preparedStatement.setString(2, "%" + keyword + "%");
			preparedStatement.setString(3, "%" + keyword + "%");
			preparedStatement.setString(4, "%" + keyword + "%");
			preparedStatement.setString(5, "%" + keyword + "%");

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
	
	public List<String> getTagFollowed(String usename){
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
		try {
			PreparedStatement preparedStatement = connection
					.prepareStatement("insert into UserFollow(Username,Follower) values (?, ?)");
			// Parameters start with 1
			preparedStatement.setString(1, username1);
			preparedStatement.setString(2, username2);
			

			preparedStatement.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	public void followTag(String username, String tag) {
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
	
	public List<String> getSimilarUserName(String keywords){
		/**
		 *SHould i use java to get the top 10 or use sql to do this hmm....
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
		 * This method attempts to find the member that is trying to log in by
		 * first retrieving the username and password entered by the user.
		 */
		Statement stmt = null;

		String username = user.getUsername();
		String password = user.getPassword();

		/**
		 * Prepare a query that searches the members table in the database
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
			 * If there are no results from the query, set the member to false.
			 * The person attempting to log in will be redirected to the home
			 * page when isValid is false.
			 */
			
			if (!more) {
				user.setValid(false);
			}

			/**
			 * If the query results in an database entry that matches the
			 * username and password, assign the appropriate information to
			 * the Member object.
			 */
			else if (more) {
				user.setUsername(rs.getString("Username"));
				user.setEmail(rs.getString("Email"));
				user.setPassword(rs.getString("Password"));
				user.setName(rs.getString("Name"));
				user.setBio(rs.getString("Bio"));
				user.setValid(true);
			}
		}

		catch (Exception ex) {
			System.out.println("Log In failed: An Exception has occurred! "
					+ ex);
		}
		/**
		 * Return the Member object.
		 */
		return user;

	}
}
