<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR" import="com.mie.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" 
    prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<!-- Check to see if the user is logged in. Otherwise, redirect back to the login page.-->
<%
	session = request.getSession();
	System.out.println(session);
	if (session.getAttribute("username") == null) {
		response.sendRedirect("userlogin.jsp");
	}
%>
<head>
<title>Foodie Feed - Tag Page</title>
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
	<div style="text-align:center" class="user-info">
	<li><span>#<c:out value="${tag.getTagName()}"></c:out></span>
    <span><a href="UserController?action=followUnfollowTag&&tagName=${tag.getTagName()}&&numPosts=${tag.getNumPosts()}"><button><c:out value="${followButtonMessage}" /></button></a></span>
	</li>
	</div>
	</header>
	<section>
			<div style="text-align:center" class="user-info">
				<ul>
					<li> 
						<c:out value="${tagFollowers}" />
						<span>FOLLOWERS</span>
					</li>
					<li>
						<c:out value="${tag.getNumPosts()}" />
						<span>NUMBER OF POSTS</span>
					</li>
				</ul>
			</div>
    <center>
    <br><br>
  	
  	<!-- display all the tag's posts -->
 	<%@include file="listReviews.jsp" %>
  
  
    </center>
</section>
</div>
</div>

</body>
</html>