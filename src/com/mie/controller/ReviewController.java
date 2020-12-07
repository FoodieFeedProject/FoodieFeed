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
import javax.servlet.http.HttpSession;

import com.mie.dao.*;
import com.mie.model.*;


 

public class ReviewController extends HttpServlet {
	/**
	 * This class handles the CREATE, EDIT, DISPLAY functions for Reviews.
	 * 
	 * 
	 * 
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private static String CREATE = "/createPost.jsp";
	private static String EDIT = "/editPost.jsp";	
	private static String DISPLAY_FULL = "/displayFullPost.jsp";
	private static String DISPLAY_MY_FULL = "/displayMyFullPost.jsp";
	
	
	private static String PROFILE = "/profile.jsp";
	private static String FOODIE_FEED = "/foodie_feed.jsp";
	
	
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
		
		HttpSession session = request.getSession(true);
		String username = (String)session.getAttribute("username");
		
		if (action.equalsIgnoreCase("delete")) {
			int reviewID = Integer.parseInt(request.getParameter("reviewID"));
			
			//first remove entries from tables with dependencies on Review
			rdao.deleteMyOrder(reviewID);
			rdao.removeUploadRecord(reviewID);
			
			List<Tag> tagsToUpdate = rdao.getTagsUsed(reviewID);
			tdao.removeUsesTags(reviewID);
			
			for(Tag tag:tagsToUpdate){
				tdao.updateTagPostNum(tag.getTagName());
			}
			
			
			rdao.deleteAllComments(reviewID);
			
			//finally, delete the review from the Review table
			rdao.deleteReview(reviewID);
			
			forward = PROFILE; //after you delete a post just go back to profile
			
			
			request.setAttribute("user", udao.getUserByUsername(username));
			request.setAttribute("reviews", rdao.getReviewsByUser(username));
			
		} else if (action.equalsIgnoreCase("create")) {
			
			forward = CREATE;
			
		} else if (action.equalsIgnoreCase("edit")) {
			
			forward = EDIT;
			
			int reviewID = Integer.parseInt(request.getParameter("reviewID"));
			Review review = rdao.getReviewById(reviewID);
			request.setAttribute("review", review);
			
		} else if (action.equalsIgnoreCase("displayFull")) {
			
			int reviewID = Integer.parseInt(request.getParameter("reviewID"));
			
			Review review = rdao.getReviewById(reviewID);

			if(username.equals(review.getUsername())){
				//if the review belongs to the logged in user
				forward = DISPLAY_MY_FULL; //there's different functionality on your own post page
				
				
			}else{
				forward = DISPLAY_FULL;
			}
			
			request.setAttribute("review", review);
			
		}else if (action.equalsIgnoreCase("listReviewsOnFeed")) {
			
			forward = FOODIE_FEED;	
			
			Set<Integer> reviewIDs = new HashSet<Integer>();//ensures no duplicates
			
			List<String> tagsFollowed = udao.getTagsFollowed(username);
			for(String tag:tagsFollowed){
				reviewIDs.addAll(tdao.getTagReviewId(tag)); //gather review ids from tags they follow
			}
			
			
			List<String> usersFollowed = udao.getFollowing(username);
			//to see your own posts in the feed too
			usersFollowed.add(username);
			
			for(String user: usersFollowed){
				reviewIDs.addAll(rdao.getUsersReviewIDs(user));//gather review ids from users they follow
			}
			
			List<Review> feed = new ArrayList<Review>();
			for(int id: reviewIDs){
				feed.add(rdao.getReviewById(id));
			}
		
			request.setAttribute("reviews", feed);
			
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
	
		String reviewIDstr = request.getParameter("reviewID");
		
		int reviewID;
		
		if (reviewIDstr == null || reviewIDstr.isEmpty()) {
			//add new review to the database
			reviewID = rdao.addReview(review);
			
			//tags should be entered as one string with # as the delimiter
			String tagList = request.getParameter("tags");
			String [] tags = tagList.split("#");
			
			for (int i=1; i< tags.length; i++){
				//tag[0] will be blank, so start at i=1
				
				//remove spaces
				String tagname = tags[i].replaceAll("\\s","");
				if (tdao.tagExist(tagname) == false){
					Tag newTag = new Tag();
					newTag.setTagName(tagname);
					newTag.setNumPost(0);
					tdao.addTag(newTag);
				}
				//add entries into UsesTag and update number of posts under that tag
				tdao.updateUsesTag(reviewID, tagname);
				tdao.updateTagPostNum(tagname);
			}
			
			//add an entry in the Posts relation
			HttpSession session = request.getSession(true);
			String username = (String)session.getAttribute("username");
			
			Date date = new Date();
			rdao.recordNewUpload(username, reviewID, date.toString());
			
			//add entries into the MyOrder relation
			MyOrder firstItem = new MyOrder();
			
			String item = request.getParameter("item1");
			String priceStr = request.getParameter("price1");
			
			if (!(item == null || item.isEmpty())&&!(priceStr == null|| priceStr.isEmpty())){	
				firstItem.setReviewID(reviewID);
				firstItem.setItem(item);
				firstItem.setPrice(Double.parseDouble(priceStr));
				rdao.addMyOrder(firstItem);
			}
		
			MyOrder secondItem = new MyOrder();
			
			String item2 = request.getParameter("item2");
			String priceStr2 = request.getParameter("price2");
			
			if (!(item2 == null || item2.isEmpty())&&!(priceStr2 == null|| priceStr2.isEmpty())){	
				secondItem.setReviewID(reviewID);
				secondItem.setItem(item2);
				secondItem.setPrice(Double.parseDouble(priceStr2));
				rdao.addMyOrder(secondItem);
			}
			
			MyOrder thirdItem = new MyOrder();
			
			String item3 = request.getParameter("item3");
			String priceStr3 = request.getParameter("price3");
			
			if (!(item3 == null || item3.isEmpty())&&!(priceStr3 == null|| priceStr3.isEmpty())){	
				thirdItem.setReviewID(reviewID);
				thirdItem.setItem(item3);
				thirdItem.setPrice(Double.parseDouble(priceStr3));
				rdao.addMyOrder(thirdItem);
			}

						
		} else {
			//if its just an edit we wont let them edit tags and MyOrder
			reviewID = Integer.parseInt(request.getParameter("reviewID"));
			review.setReviewID(reviewID);
			rdao.updateReview(review);
		}
		
		
		/**
		 * Once the review has been added or updated, the page will redirect to
		 * the expanded view.
		 */
		
		
		
		RequestDispatcher view = request
				.getRequestDispatcher(DISPLAY_MY_FULL);
		request.setAttribute("review", rdao.getReviewById(reviewID));
		view.forward(request, response);
		
	}
}