package com.mie.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mie.dao.*;
import com.mie.model.*;


public class UserController  extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	private static String SIGN_UP = "/register.jsp";

	private static String USER_LOGIN = "/userlogin.jsp";
	private static String EDIT = "/editprofile.jsp";
	private static String MY_PROFILE = "/profile.jsp";
	
	private static String OTHER_PROFILE = "/otheruserProfile.jsp";
	private static String TAG_PAGE = "/tagPage.jsp";

	private UserDao dao;
	private ReviewDao rdao;
	private TagDao tdao;

	/**
	 * Constructor for this class.
	 */
	public UserController() {
		super();
		dao = new UserDao();
		rdao = new ReviewDao();
		tdao = new TagDao();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		/**
		 * This class retrieves the appropriate 'action' found on the JSP pages:
		 * 
		 *  - signUp will direct the servlet to let the user add a
		 * new User to the database. - edit will direct the servlet to let
		 * the user edit User information in the database. - followUnfollowUser will allow 
		 * User to follower/unfollow another User and record in the database - followTag/unfollowTag 
		 * would allow user to follow/unfollow and a tag in the tag page. - myProfile would 
		 * direct the servlet to let user see the User Personal info in the myProfile page. 
		 * OtherProfile would direct the servlet to let user see Other User'Profile info 
		 * in OtherProfile page.
		 */
		String forward = "";
		String action = request.getParameter("action");
		
		HttpSession session = request.getSession(true);
		String username = (String)session.getAttribute("username");

		
		if (action.equalsIgnoreCase("signUp")) {
			
			forward = SIGN_UP;
			
		} else if (action.equalsIgnoreCase("edit")) {
			
			forward = EDIT;
			
			User user = dao.getUserByUsername(username);
			request.setAttribute("user", user);
			
		} else if (action.equalsIgnoreCase("followUnfollowUser")) {
			
			
			String otherUsername = request.getParameter("otherUsername");

			String status = dao.getFollowButtonStatus(username, otherUsername);
			dao.followUnfollowUser(username, otherUsername, status);
			forward = OTHER_PROFILE;
			
			//update the message for the follow button (Follow/Unfollow) and the user to update their follower count
			request.setAttribute("followButtonMessage", dao.getFollowButtonStatus(username, otherUsername) );
			request.setAttribute("otherUser", dao.getUserByUsername(otherUsername));
			request.setAttribute("reviews", rdao.getReviewsByUser(otherUsername));
			
		
		} else if (action.equalsIgnoreCase("followUnfollowTag")) {
			
			
			Tag tag = new Tag();
			tag.setTagName(request.getParameter("tagName"));
			tag.setNumPost(Integer.parseInt(request.getParameter("numPosts")));
			
			String status = dao.getTagFollowButtonStatus(username, tag.getTagName());
			dao.followUnfollowTag(username, tag.getTagName(), status);
			
			forward = TAG_PAGE;
		
			List<Integer> reviewIDs = tdao.getTagReviewId(tag.getTagName());
			List<Review> tagReviews = new ArrayList<Review>();
		
			for(Integer i:reviewIDs) {
				tagReviews.add(rdao.getReviewById(i));
			}
		
			request.setAttribute("tag", tag);
			request.setAttribute("tagFollowers", tdao.tagFollowers(tag.getTagName()));
			request.setAttribute("followButtonMessage", dao.getTagFollowButtonStatus(username, tag.getTagName()));
			request.setAttribute("reviews", tagReviews);
		
		} else if (action.equalsIgnoreCase("myProfile")) {
			
			
			forward = MY_PROFILE;
			
			User user = dao.getUserByUsername(username);
			
			request.setAttribute("user", user);
			request.setAttribute("reviews", rdao.getReviewsByUser(username));
			
		
		} else if (action.equalsIgnoreCase("otherProfile")) {
			
			if(username == null){
				
				forward = USER_LOGIN;
				request.setAttribute("message", "You must login to see a user profile.");
				
			}else{
				String otherUsername = request.getParameter("otherUsername");
				
				if(otherUsername.equals(username)){
					//if you click on your own username in one of your posts
					forward = MY_PROFILE;
					request.setAttribute("user", dao.getUserByUsername(username));
				}else{
					forward = OTHER_PROFILE;
					request.setAttribute("followButtonMessage", dao.getFollowButtonStatus(username, otherUsername));
					request.setAttribute("otherUser", dao.getUserByUsername(otherUsername));
				}
				
				//get the Reviews to display on the profile
				request.setAttribute("reviews", rdao.getReviewsByUser(otherUsername));
			}
				
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
		user.setEmail(request.getParameter("email"));
		user.setPassword(request.getParameter("password"));
		user.setName(request.getParameter("name"));
		user.setBio(request.getParameter("bio"));
		user.setProfilePic(request.getParameter("profilePic"));

		HttpSession session = request.getSession(true);
		
		/**
		 * If the session is null then its user registration, not edit
		 */
		if (session.getAttribute("username") == null) {
			
			String desiredUsername = request.getParameter("desiredUsername");
			boolean canUse;
			
			if(desiredUsername==null || desiredUsername.isEmpty()){
				canUse=false;
			}else{
				canUse = dao.checkUsername(desiredUsername);
			}
			 
			
			if(canUse){
				
				user.setUsername(desiredUsername);
				dao.addUser(user);
				
				request.setAttribute("message", "Thank you for registering");
				RequestDispatcher view = request
						.getRequestDispatcher(USER_LOGIN);
				
				
				view.forward(request, response);
				
				
			}else{
				request.setAttribute("usernameStatus", "Invalid Username. Please Try Again.");
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
			String username = (String)session.getAttribute("username");
			user.setUsername(username);
			dao.updateUser(user);
			
			
			
			/**
			 * Once the User has been added or updated, the page will redirect to
			 * the myProfile.
			 */
			response.sendRedirect("UserController?action=myProfile");
			
			
			
			
		}
		//after adding/updating we want to get ALL of their info (followers, following etc)
		
		
	}
}
