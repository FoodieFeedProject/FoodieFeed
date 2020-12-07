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

public class TagController extends HttpServlet{
	/**
	 * This class handles the tag functions of the servlet.
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static String TAG_PAGE = "/tagPage.jsp";
	private static String DISCOVER = "/discover.jsp";
	private static String LOGIN = "/userlogin.jsp";
	

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
		
		HttpSession session = request.getSession(true);
		String username = (String)session.getAttribute("username");

		String forward = "";
		String action = request.getParameter("action");
		
		if(action.equalsIgnoreCase("trendingList")) {
			//forward the user to the discover page
			forward = DISCOVER;
			request.setAttribute("trendingList", tdao.getTrendingTags());		
			
		}
		else if(action.equalsIgnoreCase("visitTagPage")){
			
			
			if (username == null){
				
				forward = LOGIN;
				request.setAttribute("message", "You must login to view a Tag Page.");
				
			} else{
				
				Tag tag = new Tag();
				tag.setTagName(request.getParameter("tagname"));
				tag.setNumPost(Integer.parseInt(request.getParameter("numPosts")));
			
				List<Integer> reviewIDs = tdao.getTagReviewId(tag.getTagName());
				List<Review> tagReviews = new ArrayList<Review>();
				
				//get all reviews posted under that tag
				for(Integer i:reviewIDs) {
					tagReviews.add(rdao.getReviewById(i));
				}
				forward = TAG_PAGE;
				
			
				request.setAttribute("tag", tag);
				request.setAttribute("tagFollowers", tdao.tagFollowers(tag.getTagName()));
				
				//find out if the user is following it already or not
				request.setAttribute("followButtonMessage", udao.getTagFollowButtonStatus(username, tag.getTagName()));
				request.setAttribute("reviews", tagReviews);
			}
		}
		
		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
		
	}
	
	
	
	
	
	
	
	
	

}
