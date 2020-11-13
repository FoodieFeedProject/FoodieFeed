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

import com.mie.dao.*;
import com.mie.model.*;

public class SearchControllerFoodie extends HttpServlet {
	/**
	 * This class only handles the SEARCH feature of the web app.
	 * 
	 * These are variables that lead to the appropriate JSP pages.
	 * 
	 * Search_User/Search_Tag leads to the search results page.
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private static String SEARCH_USER = "/searchUserResult.jsp";
	private static String SEARCH_TAG = "/searchTagResult.jsp";

	private UserDao uDao;
	private TagDao tDao;

	/**
	 * Constructor for this class.
	 */
	public SearchControllerFoodie() {
		super();
		uDao = new UserDao();
		tDao = new TagDao();

	}

	protected void doPostU(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/**
		 * This method handles the retrieval of the search keyword entered by
		 * the user.
		 */
		String keyword = request.getParameter("keyword");

		RequestDispatcher view = request.getRequestDispatcher(SEARCH_USER);
		request.setAttribute("keyword", keyword);
		request.setAttribute("users", uDao.getUserByKeyword(keyword));
		/**
		 * Redirect to the search results page after the list of User
		 * matching the keywords has been retrieved.
		 */

		view.forward(request, response);
	}
	
	protected void doPostT(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		/**
		 * This method handles the retrieval of the search keyword entered by
		 * the user.
		 */
		String keyword = request.getParameter("keyword");

		RequestDispatcher view = request.getRequestDispatcher(SEARCH_TAG);
		request.setAttribute("keyword", keyword);
		request.setAttribute("tags", tDao.getSimilarTags(keyword));
		/**
		 * Redirect to the search results page after the list of tags
		 * matching the keywords has been retrieved.
		 */

		view.forward(request, response);
	}
}