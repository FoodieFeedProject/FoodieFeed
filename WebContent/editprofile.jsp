<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" 
    prefix="fn" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<!-- Check to see if the user is logged in. Otherwise, redirect back to the login page.-->

<head>
<title>User Profile</title>
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

	<%@ include file="nav_bar.jsp"%>
<!-- get user session -->
<br>
	<header>
	<img src="img/testimg/2.jpg" alt="UserProfile" width="100" height="100" class="profileimg" />
	<div style="text-align:center" class="user-info">
	<li><span>Hi, username !</span></li>
	</div>
	</header>
	<section>
			<div style="text-align:center" class="user-info">
				<ul>
					<li> 
						<!-- value="${fn:length(uer.getFollowing())}"-->
						3344
						<span>FOLLOWING</span>
					</li>
					<li>
						<!-- value="${fn:length(uer.getFollowers())}"-->
						5566
						<span>FOLLOWERS</span>
					</li>
					<li>
						<!--c:out value="${fn:length(uer.getTagFollow())}"-->
						7788
						<span>TAGS</spam>
					</li>
				</ul>
			</div>
</section>

	<%@ include file="footer.jsp"%>


</body>
</html>