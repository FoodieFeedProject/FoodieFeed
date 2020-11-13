package com.mie.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mie.dao.*;
import com.mie.model.*;


public class UserController {
	
	private static final long serialVersionUID = 1L;
	private static String SIGN_UP = "/signUp.jsp";
	private static String EDIT = "/editProfile.jsp";
	private static String MY_PROFILE = "/myProfile.jsp";
	private static String OTHER_PROFILE = "/otherProfile.jsp";
	private static String TAG_PAGE = "/tagPage.jsp";

	private UserDao dao;
	private ReviewDao rdao;

	/**
	 * Constructor for this class.
	 */
	public UserController() {
		super();
		dao = new UserDao();
		rdao = new ReviewDao();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		/**
		 * This class retrieves the appropriate 'action' found on the JSP pages:
		 * 
		 *  - signUp will direct the servlet to let the user add a
		 * new User to the database. - edit will direct the servlet to let
		 * the user edit User information in the database. - followeUser will allow 
		 * User to follower another User and record in the database. - unfollowUser will allow 
		 * User to unfollow another User and record in the database. - followTag/unfollowTag 
		 * would allow user to follow/unfollow and atag in the tag page. - myProfile would 
		 * direct the servlet to let user see the User Personal info in the myProfile page. 
		 * OtherProfile would direct the servlet to let user see Other User'Profile info 
		 * in OtherPrile page. - ListUserOnUser would direct the servlet to let user see
		 * other user that they followed in the myProfile page. 
		 */
		String forward = "";
		String action = request.getParameter("action");

		
		if (action.equalsIgnoreCase("signUp")) {
			forward = SIGN_UP;
		} else if (action.equalsIgnoreCase("edit")) {
			forward = EDIT;
			String username = request.getParameter("currentUser");
			User user = dao.getUserByUsername(username);
			request.setAttribute("user", user);
		} else if (action.equalsIgnoreCase("followUser")) {
			
			String currentUser = request.getParameter("currentUser");
			String otherUser = request.getParameter("otherUser");

			dao.followUser(currentUser, otherUser);
			forward = OTHER_PROFILE;
			request.setAttribute("followButtonMessage", dao.getFollowButtonStatus(currentUser, otherUser));
			request.setAttribute("otherUser", dao.getUserByUsername(otherUser));
			request.setAttribute("profileReviews", rdao.getReviewsByUser(otherUser));

		} else if (action.equalsIgnoreCase("unfollowUser")) {
			
			String currentUser = request.getParameter("currentUser");
			String otherUser = request.getParameter("follower");

			dao.unfollowUser(currentUser, otherUser);
			
			forward = OTHER_PROFILE;
			
			request.setAttribute("followButtonMessage", dao.getFollowButtonStatus(currentUser, otherUser));
			request.setAttribute("otherUser", dao.getUserByUsername(otherUser));
			request.setAttribute("tagReviews", rdao.getReviewsByUser(otherUser));

		} else if (action.equalsIgnoreCase("followTag")) {
			String username = request.getParameter("currentUser");
			String tag = request.getParameter("tagName");

			dao.followTag(username, tag);
			forward = TAG_PAGE;
			
			request.setAttribute("followButtonMessage", dao.getTagFollowButtonStatus(username, tag));
				
		
		} else if (action.equalsIgnoreCase("unfollowTag")) {
			String username = request.getParameter("currentUser");
			String tag = request.getParameter("tagName");

			dao.unfollowTag(username, tag);
			forward = TAG_PAGE;
			request.setAttribute("followButtonMessage", dao.getTagFollowButtonStatus(username, tag));

		} else if (action.equalsIgnoreCase("myProfile")) {
			forward = MY_PROFILE;
			String username = request.getParameter("username");
			User user = dao.getUserByUsername(username);
			request.setAttribute("myUser", user);
			request.setAttribute("profileReviews", rdao.getReviewsByUser(username));	
		
		} else if (action.equalsIgnoreCase("otherProfile")) {
			forward = OTHER_PROFILE;
			String currentUser = request.getParameter("username");
			String username = request.getParameter("otherUser");
			
			request.setAttribute("followButtonMessage", dao.getFollowButtonStatus(currentUser, username));
			request.setAttribute("otherUser", dao.getUserByUsername(username));
			request.setAttribute("profileReviews", rdao.getReviewsByUser(username));
	
		} else {
			forward = SIGN_UP;
		}

		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		/**
		 * This method retrieves all of the information entered in the form on
		 * the signUp.jsp or the editProfile.jsp pages.
		 */
		User user = new User();
		//if this input is from sign up form, username input should be saved in "desiredUsername"
		user.setEmail(request.getParameter("Email"));
		user.setPassword(request.getParameter("Password"));
		user.setName(request.getParameter("Name"));
		user.setBio(request.getParameter("Bio"));
		user.setProfilePic(request.getParameter("ProfilePic"));

		String username = request.getParameter("username");
		/**
		 * If the 'UserName' field in the form is empty, the new User will
		 * be added to the list of User objects.
		 */
		if (username == null || username.isEmpty()) {
			
			String desiredUsername = request.getParameter("desiredUsername");
			boolean canUse = dao.checkUsername(desiredUsername);
			
			if(canUse){
				request.setAttribute("usernameStatus", "Username Available");
				dao.addUser(user);
			}else{
				request.setAttribute("usernameStatus", "Username Taken");
				//they get brought back to sign up page and need to enter a new desiredUsername
				RequestDispatcher view = request
						.getRequestDispatcher(SIGN_UP);
				//this might save their other inputs
				request.setAttribute("user", user);
				view.forward(request, response);
			}
			
		} else {
			/**
			 * Otherwise, if the field is already filled (this occurs when the
			 * user is trying to Edit their Profile info), then the user's profile information
			 * will be updated accordingly.
			 */
			user.setUsername(username);
			dao.updateUser(user);
			
		}
		//after adding/updating we want to get ALL of their info (followers, following etc)
		User fullUser = new User();
		fullUser = dao.getUserByUsername(username);
		/**
		 * Once the User has been added or updated, the page will redirect to
		 * the myProfile.
		 */
		RequestDispatcher view = request
				.getRequestDispatcher(MY_PROFILE);
		request.setAttribute("user", fullUser);
		view.forward(request, response);
	}
}
