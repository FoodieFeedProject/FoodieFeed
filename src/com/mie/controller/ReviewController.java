package com.mie.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.HashSet;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mie.dao.*;
import com.mie.model.*;


//import com.mie.model.Student;

  
//SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");  
//Date date = new Date();  

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
	//from server prototype
	private static String LIST_REVIEW_PUBLIC = "/test_ListReview.jsp";
	
	//for our real site
	private static String CREATE = "/createPost.jsp";
	private static String EDIT = "/editPost.jsp";	
	private static String DISPLAY = "/displayPost.jsp";
	private static String DISPLAY_FULL = "/displayFullPost.jsp";
	
	
	private static String PROFILE = "/profile.jsp";
	private static String FOODIE_FEED = "/foodieFeed.jsp";
	
	
	private ReviewDao rdao;
	private UserDao udao;
	private TagDao tdao;

	/**
	 * Constructor for this class.
	 */
	public ReviewController() {
		super();
		rdao = new ReviewDao();
		udao = new UserDao();
		tdao = new TagDao();
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
		
		if (action.equalsIgnoreCase("delete")) {
			int reviewID = Integer.parseInt(request.getParameter("reviewID"));
			
			//first remove entries from tables with dependencies on Review
			rdao.deleteMyOrder(reviewID);
			rdao.removeUploadRecord(reviewID);
			tdao.removeUsesTags(reviewID);//need a method for this in tagdao
			rdao.deleteAllComments(reviewID);
			
			//finally, delete the review from the Review table
			rdao.deleteReview(reviewID);
			
			forward = PROFILE; //after you delete a post just go back to profile
			String username = request.getParameter("username");
			//request.setAttribute("user", udao.getUserByUsername(username));
			request.setAttribute("myPosts", rdao.getReviewsByUser(username));
			
		} else if (action.equalsIgnoreCase("create")) {
			
			forward = CREATE;
			
		} else if (action.equalsIgnoreCase("edit")) {
			
			forward = EDIT;
			int reviewID = Integer.parseInt(request.getParameter("reviewID"));
			Review review = rdao.getReviewById(reviewID);
			request.setAttribute("review", review);
			
		} else if (action.equalsIgnoreCase("display")) {
			
			forward = DISPLAY;
			int reviewID = Integer.parseInt(request.getParameter("reviewID"));
			Review review = rdao.getReviewById(reviewID);
			request.setAttribute("review", review);
			
		} else if (action.equalsIgnoreCase("displayFull")) {
			
			forward = DISPLAY_FULL;
			int reviewID = Integer.parseInt(request.getParameter("reviewID"));
			Review review = rdao.getReviewById(reviewID);
			request.setAttribute("review", review);
			
		}else if (action.equalsIgnoreCase("listReviewsOnFeed")) {
			
			forward = FOODIE_FEED;	
			String username = request.getParameter("username");
			Set<Integer> reviewIDs = new HashSet<Integer>();//ensures no duplicates
			
			List<String> tagsFollowed = udao.getTagsFollowed(username);
			for(String tag:tagsFollowed){
				reviewIDs.addAll(tdao.getTagReviewId(tag)); //gather review ids from tags they follow
			}
			
			List<String> usersFollowed = udao.getFollowing(username);//method name may change
			for(String user: usersFollowed){
				reviewIDs.addAll(rdao.getUsersReviewIDs(user));//gather review ids from users they follow
			}
			
			List<Review> feed = new ArrayList<Review>();
			for(int id: reviewIDs){
				feed.add(rdao.getReviewById(id));
			}
			request.setAttribute("feedReviews", feed);
			
		}  else if (action.equalsIgnoreCase("like")) {
			
			forward = DISPLAY_FULL;
			//experiment with this later. not sure what page to forward
			int reviewID = Integer.parseInt(request.getParameter("reviewID"));
			rdao.likeReview(reviewID);			
			Review review = rdao.getReviewById(reviewID);
			request.setAttribute("review", review);
		}

		RequestDispatcher view = request.getRequestDispatcher(forward);
		view.forward(request, response);
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/**
		 * This method retrieves all of the information entered in the form on
		 * the createPost.jsp or the editPost.jsp pages.
		 */
		Review review = new Review();
				
		review.setTitle(request.getParameter("title"));
		review.setOverallRating(Integer.parseInt(request.getParameter("overallRating")));
		review.setDescription(request.getParameter("description"));
		review.setFoodRating(Integer.parseInt(request.getParameter("foodRating")));
		review.setServiceRating(Integer.parseInt(request.getParameter("serviceRating")));
		review.setEnvironmentRating(Integer.parseInt(request.getParameter("environmentRating")));
		review.setDineIn(Integer.parseInt(request.getParameter("dineIn")));
		review.setPhotoURL(request.getParameter("photoURL"));
	
		Integer reviewID = Integer.parseInt(request.getParameter("reviewID"));
	
		if (reviewID == null) {
			//add new review to the database
			reviewID = rdao.addReview(review);
			
			//tags should be entered as one string with # as the delimiter
			String tagList = request.getParameter("tags");
			String tags [] = tagList.split("#");
			for (String tag: tags){
				if (tdao.tagExist(tag) == false){
					Tag newTag = new Tag();
					newTag.setTagName(tag);
					newTag.setNumPost(0);
					tdao.addTag(newTag);
				}
				//add entries into UsesTag and update number of posts under that tag
				tdao.updateUsesTag(reviewID, tag);
				tdao.updateTagPostNum(tag);
			}
			
			//add an entry in the Posts relation
			String username = request.getParameter("username");
			Date date = new Date();
			rdao.recordNewUpload(username, reviewID, date.toString());
			
			//add entries into the MyOrder relation (only 2 for now)
			MyOrder firstItem = new MyOrder();
			firstItem.setReviewID(reviewID);
			firstItem.setItem(request.getParameter("item1"));
			firstItem.setPrice(Double.parseDouble(request.getParameter("price1")));
			if (firstItem.getItem() != null){
				rdao.addMyOrder(firstItem);
			}
		
			MyOrder secondItem = new MyOrder();
			secondItem.setReviewID(reviewID);
			secondItem.setItem(request.getParameter("item2"));
			secondItem.setPrice(Double.parseDouble(request.getParameter("price2")));
			if (secondItem.getItem() != null){
				rdao.addMyOrder(secondItem);
			}

						
		} else {
			//if its just an edit we wont let them edit tags and MyOrder for now
			review.setReviewID(reviewID);
			rdao.updateReview(review);
		}
		
		
		/**
		 * Once the review has been added or updated, the page will redirect to
		 * the expanded view.
		 */
		
		
		
		RequestDispatcher view = request
				.getRequestDispatcher(DISPLAY_FULL);
		request.setAttribute("review", rdao.getReviewById(reviewID));
		view.forward(request, response);
		
	}
}