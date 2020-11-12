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

import com.mie.dao.*;
import com.mie.model.*;

public class TagController extends HttpServlet{
	/**
	 * This class handles the list function of the servlet.
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	//private static String trending = ""
	private static String TAG_PAGE = "/tagPage.jsp";
	private static String DISCOVER = "/discover.jsp";
	
	/*
	private static String LIST_REVIEW_PUBLIC = "/test_ListReview.jsp";
	private static String CREATE = "/createPost.jsp";
	private static String EDIT = "/editPost.jsp";	
	private static String DISPLAY = "/displayPost.jsp";
	private static String DISPLAY_FULL = "/displayFullPost.jsp";
	
	//im not sure about these just yet 
	private static String PROFILE = "/profile.jsp";
	private static String FOODIE_FEED = "/foodieFeed.jsp";
	*/
	
	private TagDao tdao;
	private ReviewDao rdao;

	/**
	 * Constructor for this class.
	 */
	public TagController() {
		super();
		tdao = new TagDao();
		rdao = new ReviewDao();
	}
	
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {

		/**
		 * This class retrieves the appropriate 'action' found on the JSP pages:
		 * 
		 * - delete will direct the servlet to let the user delete a review in
		 * the database. - create will direct the servlet to let the user add a
		 * new review to the database. - edit will direct the servlet to let
		 * the user edit review information in the database. - display will
		 * direct the servlet to a review in the database. - displayFull will 
		 * direct the servlet to the expanded review
		 */
		String forward = "";
		String action = request.getParameter("action");
		
		if(action.equalsIgnoreCase("TrendingList")) {
			forward = DISCOVER;
			request.setAttribute("TrendingList", tdao.getTrendingTags());
		}
		else if(action.equalsIgnoreCase("visitTagPage")){
			String tagname = request.getParameter("tagname");
			List<Integer> reviewIDs = tdao.getTagReviewId(tagname);
			List<Review> tagReviews = new ArrayList<Review>();
			
			for(Integer i:reviewIDs) {
				tagReviews.add(rdao.getReviewById(i));
			}
			forward = TAG_PAGE;
			request.setAttribute("tagPage", tagReviews);
		}
		
	}
	
	
	
	
	
	
	
	
	

}
