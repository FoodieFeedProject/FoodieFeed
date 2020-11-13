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
	 * This class handles the tag functions of the servlet.
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static String TAG_PAGE = "/tagPage.jsp";
	private static String DISCOVER = "/discover.jsp";
	

	private TagDao tdao;
	private ReviewDao rdao;
	private UserDao udao;

	/**
	 * Constructor for this class.
	 */
	public TagController() {
		super();
		tdao = new TagDao();
		rdao = new ReviewDao();
		udao = new UserDao();
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
		
		if(action.equalsIgnoreCase("trendingList")) {
			forward = DISCOVER;
			request.setAttribute("trendingList", tdao.getTrendingTags());
		}
		else if(action.equalsIgnoreCase("visitTagPage")){
			
			String tagname = request.getParameter("tagname");
			String username = request.getParameter("username");
			
			List<Integer> reviewIDs = tdao.getTagReviewId(tagname);
			List<Review> tagReviews = new ArrayList<Review>();
			
			for(Integer i:reviewIDs) {
				tagReviews.add(rdao.getReviewById(i));
			}
			forward = TAG_PAGE;
			
			request.setAttribute("followButtonMessage", udao.getTagFollowButtonStatus(username, tagname));
			request.setAttribute("tagPage", tagReviews);
		}
		
	}
	
	
	
	
	
	
	
	
	

}
