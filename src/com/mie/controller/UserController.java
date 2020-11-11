package com.mie.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mie.dao.StudentDao;
import com.mie.dao.UserDao;
import com.mie.model.User;
import com.mie.model.User;

public class UserController {
	
	private static final long serialVersionUID = 1L;
	private static String SIGN_UP = "/signUp.jsp";
	private static String EDIT = "/editProfile.jsp";
	private static String MY_PROFILE = "/myProfile.jsp";
	private static String OTHER_PROFILE = "/otherProfile.jsp";

	private UserDao dao;

	/**
	 * Constructor for this class.
	 */
	public UserController() {
		super();
		dao = new UserDao();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		/**
		 * This class retrieves the appropriate 'action' found on the JSP pages:
		 * 
		 * - delete will direct the servlet to let the user delete a student in
		 * the database. - insert will direct the servlet to let the user add a
		 * new student to the database. - edit will direct the servlet to let
		 * the user edit student information in the database. - listStudent will
		 * direct the servlet to the public listing of all students in the
		 * database. - listStudentAdmin will direct the servlet to the admin
		 * listing of all students in the database.
		 */
		String forward = "";
		String action = request.getParameter("action");

		
		if (action.equalsIgnoreCase("signUp")) {
			forward = SIGN_UP;
		} else if (action.equalsIgnoreCase("edit")) {
			forward = EDIT;
			String username = request.getParameter("Username");
			User user = dao.getUserByUsername(username);
			request.setAttribute("user", user);
		} else if (action.equalsIgnoreCase("followUser")) {
			
			String username = request.getParameter("Username");
			String follower = request.getParameter("Username");

			dao.followUser(username, follower);
			forward = MY_PROFILE;
			request.setAttribute("following", dao.getUsersFollowed(username));

		}
		 else if (action.equalsIgnoreCase("followTag")) {
			String username = request.getParameter("Username");
			String tag = request.getParameter("tagName");

			dao.followTag(username, tag);
			forward = MY_PROFILE;
			request.setAttribute("tagFollowing", dao.getTagFollowed(username));

		}
		 else if (action.equalsIgnoreCase("display")) {
				forward = MY_PROFILE;
				String username = request.getParameter("Username");
				User user = dao.getUserByUsername(username);
				request.setAttribute("user", user);
		 }
		else if (action.equalsIgnoreCase("listUserOnUser")) {
			forward = MY_PROFILE;
			String username = request.getParameter("Username");
			request.setAttribute("following", dao.getUsersFollowed(username));
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
		 * the addStudent.jsp or the editStudent.jsp pages.
		 */
		User user = new User();
		user.setEmail(request.getParameter("Email"));
		user.setPassword(request.getParameter("Password"));
		user.setName(request.getParameter("Name"));
		user.setBio(request.getParameter("Bio"));
		user.setProfilePic(request.getParameter("ProfilePic"));

		String username = request.getParameter("Username");
		/**
		 * If the 'studentid' field in the form is empty, the new student will
		 * be added to the list of Student objects.
		 */
		if (username == null || username.isEmpty()) {
			dao.addUser(user);
		} else {
			/**
			 * Otherwise, if the field is already filled (this occurs when the
			 * user is trying to Edit A Student), then the student's information
			 * will be updated accordingly.
			 */
			user.setUsername(username);
			dao.updateUser(user);
		}
		/**
		 * Once the student has been added or updated, the page will redirect to
		 * the listing of students.
		 */
		RequestDispatcher view = request
				.getRequestDispatcher(MY_PROFILE);
		request.setAttribute("user", user);
		view.forward(request, response);
	}
}
