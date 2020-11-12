package com.mie.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.mie.dao.ReviewDao;
import com.mie.dao.UserDao;
import com.mie.dao.TagDao;
import com.mie.model.Review;
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
	private static String LIST_REVIEW_PUBLIC = "/test_ListReview.jsp";
	private static String CREATE = "/createPost.jsp";
	private static String EDIT = "/editPost.jsp";	
	private static String DISPLAY = "/displayPost.jsp";
	private static String DISPLAY_FULL = "/displayFullPost.jsp";
	
	//im not sure about these just yet 
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
			rdao.deleteReview(reviewID);
			forward = PROFILE; //after you delete a post just go back to profile
			//To do: make a currentUser parameter in front end
			String currentUser = request.getParameter("currentUser");
			request.setAttribute("myPosts", rdao.getReviewsByUser(currentUser));
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
			//trying something out
			String currentUser = request.getParameter("currentUser");
			List<String> tagsFollowed = udao.getTagFollowed(currentUser);
			List<String> usersFollowed = udao.getUsersFollowed(currentUser);//method name may change
			//getFeed method is not done yet
			request.setAttribute("feedReviews", rdao.getFeed(currentUser, tagsFollowed, usersFollowed));
		} else if (action.equalsIgnoreCase("listReviewsOnProfile")) {
			forward = PROFILE;
			String username = request.getParameter("username");
			request.setAttribute("profileReviews", rdao.getReviewsByUser(username));
		} else if (action.equalsIgnoreCase("comment")){ // i wonder if comment needs its own controller and doPost method..
			//forward = DISPLAY_FULL;
			//String currentUser = request.getParameter("currentUser");
			//int reviewID = Integer.parseInt(request.getParameter("reviewID"));
			//String comment = request.getParameter("comment");
			//rdao.addComment(reviewID,currentUser, comment);
		}else {
			//forward = CREATE;
			//what other actions do we need? like post, comment on post
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
		/*try {
			Date datePosted = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss").parse(request
					.getParameter("datePosted"));
			student.setDob(dob);
		} catch (ParseException e) {
			e.printStackTrace();
		}*/
		review.setDescription(request.getParameter("description"));
		review.setFoodRating(Integer.parseInt(request.getParameter("foodRating")));
		review.setServiceRating(Integer.parseInt(request.getParameter("serviceRating")));
		review.setEnvironmentRating(Integer.parseInt(request.getParameter("environmentRating")));
		review.setDineIn(Integer.parseInt(request.getParameter("dineIn")));
		review.setPhotoURL(request.getParameter("photoURL"));
		
		String reviewID = request.getParameter("reviewID");
		/**
		 * If the 'studentid' field in the form is empty, the new student will
		 * be added to the list of Student objects.
		 */
		if (reviewID == null || reviewID.isEmpty()) {
			rdao.addReview(review);
		} else {
			/**
			 * Otherwise, if the field is already filled (this occurs when the
			 * user is trying to Edit A Student), then the student's information
			 * will be updated accordingly.
			 */
			review.setReviewID(Integer.parseInt(reviewID));
			rdao.updateReview(review);
		}
		/**
		 * Once the student has been added or updated, the page will redirect to
		 * the listing of students.
		 */
		//fix later
		RequestDispatcher view = request
				.getRequestDispatcher(DISPLAY);
		//request.setAttribute("students", dao.getAllStudents());
		view.forward(request, response);
		
	}
}