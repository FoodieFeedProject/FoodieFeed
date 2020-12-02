package com.mie.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.mie.dao.*;
import com.mie.model.*;


public class CommentController extends HttpServlet {
	/**
	 * This class only handles the comment feature of the web app.
	 * 
	 * These are variables that lead to the appropriate JSP pages.
	 * 
	 * DISPLAY_FULL leads to the expanded view of a posted Review (which includes the comments).
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String DISPLAY_FULL = "/displayFullPost.jsp";
	private ReviewDao rdao;

	/**
	 * Constructor for this class.
	 */
	public CommentController() {
		super();
		rdao = new ReviewDao();
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/**
		 * This method handles the retrieval of the comment entered by
		 * the user.
		 */
		Comment comment = new Comment();
		Date date = new Date();
		
		HttpSession session = request.getSession(true);
		String username = (String)session.getAttribute("username");
		
		comment.setUser(username);
		comment.setDate(date.toString());
		comment.setReviewID(Integer.parseInt(request.getParameter("reviewID")));
		comment.setComment(request.getParameter("comment"));
		
		RequestDispatcher view = request.getRequestDispatcher(DISPLAY_FULL);
		
		rdao.addComment(comment);
		//get the updated review 
		request.setAttribute("review", rdao.getReviewById(comment.getReviewID()));
		/**
		 * Redirect to the display full post page after the updated review has been retrieved.
		 */

		view.forward(request, response);
	}
}