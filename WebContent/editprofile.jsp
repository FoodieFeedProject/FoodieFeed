<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" 
    prefix="fn" %> 
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
<title>Edit User Profile</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<!-- Date Picker Javascript -->
<!-- https://jqueryui.com/datepicker/ -->
<link rel="stylesheet" href="css/jquery-ui.css">

<link rel="stylesheet" type="text/css" href="css/poststyle.css">

<link rel="stylesheet" href="css/font_1805932_ysrcp4y0uy9.css">
<link rel="stylesheet" href="css/uploadImg.css">
<link rel="stylesheet" id="templatecss" type="text/css"
	href="css/basic-grey.css">
</head>
<body>

	<%@ include file="nav_bar.jsp"%>
<!-- get user session -->
	<%
		User user = (User) session.getAttribute("currentSessionUser");
		String username = (String) session.getAttribute("username");
	%>
	<div class="container-fluid text-center">
		<div class="col-sm-8 text-center">
		<form action="ReviewController" name="addForm" method="post" >
			<div class="row content">
		   		<div class="col-sm-3 sidenav">
		       <h2>
					Upload New Profile Picture
				</h2>
				 <input id="listphotoURL" value="${user.getProfilePic()}" type="hidden" /> 
				<div class="upload-content">
					<div class="content-img">
						<ul class="content-img-list"></ul>
						<div class="file">
							<i class="gcl gcladd"></i> <input type="file" name="file"
								accept="image/*" id="upload" multiple>
						</div>
					</div>
					<div class="modal fade bs-example-modal-lg" tabindex="-1"
						role="dialog" aria-labelledby="myLargeModalLabel">
						<div class="modal-dialog modal-lg" role="document">
							<div class="modal-content"></div>
						</div>
					</div>
				</div>
		   </div>
		   
		   <div class="col-sm-8 text-left">
			<div class="from_style">
				<label> <span>Username:</span> <input id="username" type="text"
					name="username" value="${user.getUsername()}" placeholder="please enter your new username" />
				</label> 
				<label> <span>Password:</span> <input id="password" type="text"
					name="password" value="${user.getPassword()}" placeholder="please enter your new password" />
				</label>		
				<label> <span>Bio:</span> <textarea id="text"
						name="bio" value="${user.getBio()}" placeholder="please enter your new bio"></textarea>
				</label>
				<label> <span>Email:</span> <input id="email" type="text"
					name="email" value="${user.getEmail()}" placeholder="please enter your new email" />
				</label> 
				<label> <span>Name:</span> <input id="name" type="text"
					name="name" value="${user.getName()}" placeholder="please enter your new name" />
				</label>  
				<br>
				<input type="submit" class="btn btn-def btn-block" value="Save Edits" />
		</form>
	</div>
</div>
</div>
	<%@ include file="footer.jsp"%>
	<script src="js/uploadImg.js"></script>
	

</body>
</html>
