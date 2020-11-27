package com.mie.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mie.model.*;
import com.mie.dao.*;

/**
 * Servlet implementation for LoginController.
 * 
 * This class handles the login servlet and assigns session attributes for users
 * who succesfully log into the system.
 */
public class LoginController extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, java.io.IOException {
		/**
		 * Retrieve the entered username and password from the login.jsp form.
		 */
		User user = new User();
		user.setUsername(request.getParameter("username"));
		user.setPassword(request.getParameter("password"));
		try {
			/**
			 * Try to see if the user can log in.
			 */
			user = UserDao.login(user);

			/**
			 * If the isValid value is true, assign session attributes to the
			 * current user.
			 */
			if (user.isValid()) {

				HttpSession session = request.getSession(true);
				

				session.setAttribute("currentSessionuser", user);
				session.setAttribute("username", user.getUsername());
				session.setAttribute("email", user.getEmail());
				
				
				session.setAttribute("name", user.getName());
				
				
				session.setAttribute("profile picture", user.getProfilePic());
				session.setAttribute("bio", user.getBio());
				
				
				/**
				 * Redirect to the users-only home page.
				 */
				response.sendRedirect("ReviewController?action=listReviewsOnFeed");

				/**
				 * Set a timeout variable of 900 seconds (15 minutes) for this
				 * user who has logged into the system.
				 */
				session.setMaxInactiveInterval(900);
			}

			else {
				/**
				 * Otherwise, redirect the user to the invalid login page and
				 * ask them to log in again with the proper credentials.
				 */
				response.sendRedirect("userinvalidLogin.jsp");
			}
		}

		catch (Throwable theException) {
			/**
			 * Print out any errors.
			 */
			System.out.println(theException);
		}
	}
		
	
}
