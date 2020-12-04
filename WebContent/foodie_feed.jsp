<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="com.mie.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
<title>Foodie Feed - Home</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet"
	href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
<script
	src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.1/jquery.min.js"></script>
<script
	src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
  
<!-- Date Picker Javascript -->
<!-- https://jqueryui.com/datepicker/ -->
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<link rel="stylesheet" type="text/css" href="css/mystyle.css">
<link rel="stylesheet" type="text/css" href="css/profilestyle.css">

<link rel="stylesheet" type="text/css" href="css/poststyle.css">

</head>
<body>

	<div class="container-fluid">
	<div class="row content">
  	<%@ include file="nav_bar_loggedin.jsp"%>
    	<h1>Welcome to your Foodie Feed,&nbsp;<%=session.getAttribute("name") %></h1>
     	 
     	<center><br>
     	 <div style="margin-left:20px">
     	 <a href="ReviewController?action=create"><font size=4>ADD POST</font></a>
    	 </div>
      
      <br>
      
      <!-- display all the posts from tags and users they follow -->
      <%@include file="listReviews.jsp" %>
      
      </center>
   
	</div>
	</div>
	
</body>
</html>