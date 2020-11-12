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

import com.mie.dao.TagDao;
import com.mie.model.Tag;

public class TagController extends HttpServlet{
	/**
	 * This class handles the list function of the servlet.
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	
	private static String 
	
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
	
	private TagDao dao;

	/**
	 * Constructor for this class.
	 */
	public TagController() {
		super();
		dao = new TagDao();
	}
	

}
