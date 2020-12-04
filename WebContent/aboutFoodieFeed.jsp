<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="com.mie.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
<title>Foodie Feed - About</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<!-- Date Picker Javascript -->
<!-- https://jqueryui.com/datepicker/ -->
<link rel="stylesheet" href="css/jquery-ui.css">
<link rel="stylesheet" href="css/font_1805932_ysrcp4y0uy9.css">
<link rel="stylesheet" type="text/css" href="css/poststyle.css">
<script type="text/javascript" src="js/imgscript.js"></script>
<link rel="stylesheet" id="templatecss" type="text/css"
	href="css/basic-grey.css">
<link rel="stylesheet" type="text/css" href="css/mystyle.css">
<style>
.basic-grey {
    max-width: 500px;
    background: # F7F7F7;
    padding: 0px 15px 0px 10px;
    font: 12px Georgia, "Times New Roman", Times, serif;
    color: # 888;
    text-shadow: 1px 1px 1px# FFF;
    border: 1px solid# E4E4E4;
}


</style>

</head>
<body>

<% if (session.getAttribute("username") == null) { %>
		<%@ include file="nav_bar.jsp"%>
	<% } else { %>
		<%@ include file="nav_bar_loggedin.jsp"%>
	<% } %>
	
<div class="container-fluid">
	<div class="row content" >
	
	
	<div class="col-sm-8 text-left" style="padding-left:25px">
            
       
      <div class="from_style">
       <h2>About</h2>
       </div>
				This is a web app created by Group 3 for the <b>MIE350</b> course at the
				<a href="http://www.utoronto.ca">University of Toronto</a>.
				<br> <br> It was developed using
				the following:<br>
				
				<ul>
					<li>Eclipse Java EE IDE for Web Developers,</li>
					<li>The Java programming language,</li>
					<li>Java Server Pages (JSP),</li>
					<li>Apache Tomcat 7.0, and</li>
					<li>A simple Microsoft Access database.</li>
				</ul>
				
				As well, the following <b>open-source</b> tools were used:
				<ul>
					<li><a href="http://getbootstrap.com/">The Bootstrap CSS
							theme</a></li>
					<li><a href="http://demo.sc.chinaz.com/Files/DownLoad/webjs1/201906/jiaoben6825/">Photo Slideshow 
							Display</a></li>
					<li><a href="https://blog.csdn.net/nqxcwl/article/details/102885289">Star Rating Effect 
							</a></li>		
					<li><a href="https://codepen.io/neerajkr/pen/DaAIC"> CSS for Profile 
							</a></li>
					<li><a href="https://codepen.io/Simba9/pen/QdjgLg"> CSS for Login 
							</a></li>	
							
					
				</ul>
				
				<div class="from_style">
				<h2>Foodie Feed</h2>
				</div>
				 This is a community site for food lovers and bloggers. Here, you can: <br>
				<ul>
					<li><b>Create</b> a new food review post.</li>
					<li><b>Follow</b> tags or other foodies.</li>
					<li><b>Like and Comment</b> on posts.</li>
					<li><b>Update</b> your profile information.</li>
					<li><b>Edit or Delete</b> your posts.</li>
					<li><b>Discover</b> users or tags.</li>
					<li><b>Log In or Sign Up</b> to get started!.</li>
				</ul>
         
        <br>
      
	
	</div>

	</div>

</div>


</body>
</html>