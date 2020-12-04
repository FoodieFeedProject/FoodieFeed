<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR" import="com.mie.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" 
    prefix="fn" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">

<head>
<title>Foodie Feed - User Profile</title>
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

</head>
<body>
	<div class="container-fluid">
	<div class="row content">
	<%@ include file="nav_bar_loggedin.jsp"%>

	<%
		User user = (User) session.getAttribute("currentSessionUser");
		String username = (String) session.getAttribute("username");
	%>
	
	<header>
	<br>
	<input id="pfpic" value="${otherUser.getProfilePic()}" type="hidden" />		 
		<i id="pfp"></i>	
	
	<script type="text/javascript">
		var pp="";
	
		var pfp=window.atob($("#pfpic").val());
		pp=pp+'<img src="'+pfp+'"  alt="UserProfile" width="100" height="100" class="profileimg" />'
	
		if(pp!=""){
			$("#pfp").html(pp);
		
	 }
	</script>
		
		
	<div style="text-align:center" class="user-info">
	<li>
	<span>@<c:out value="${otherUser.getUsername()}"></c:out></span>
    <span><a href="UserController?action=followUnfollowUser&&otherUsername=${otherUser.getUsername()}"><button><c:out value="${followButtonMessage}" /></button></a></span>
	</li>
	</div>
	</header>
	<section>
			<div style="text-align:center" class="user-info">
				<ul>
					<li> 
						<c:out value="${otherUser.getFollowing().size()}" />
						
						<span>FOLLOWING</span>
					</li>
					<li>
						<c:out value="${otherUser.getFollowers().size()}" />
						
						<span>FOLLOWERS</span>
					</li>
					<li>
						<c:out value="${otherUser.getTagFollow().size()}" />
						
						<span>TAGS FOLLOWED</span>
					</li>
				</ul>
				<br>
				<c:out value="${otherUser.getBio()}" />
			</div>
  </section>

   	<center><br><br>
   	 
  	<!-- display all the user's posts -->
 	<%@include file="listReviews.jsp" %>
 	
    </center>
	</div>
	</div>

</body>
</html>
