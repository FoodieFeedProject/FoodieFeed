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

import com.mie.dao.ReviewDao;
import com.mie.model.Review;

public class ReviewController extends HttpServlet {
	/**
	 * This class handles the list function of the servlet.
	 * 
	 * Later this class should have similar functionality to StudentController.java
	 * This is a simplified version for the server prototype
	 * LIST_STUDENT_PUBLIC leads to the public listing of reviews.
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String LIST_REVIEW_PUBLIC = "/test_ListReview.jsp";

	private ReviewDao dao;

	/**
	 * Constructor for this class.
	 */
	public ReviewController() {
		super();
		dao = new ReviewDao();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		/**
		 * This class retrieves the appropriate 'action' found on the JSP pages:
		 * 
		 * - listReviews will direct the servlet to the public listing of all reviews 
		 * in the database.
		 */
		String forward = "";
		String action = request.getParameter("action");


		if (action.equalsIgnoreCase("listReviews")) {
			forward = LIST_REVIEW_PUBLIC;
			request.setAttribute("reviews", dao.getAllReviews());
		} 

		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		//empty for now
		
	}
}