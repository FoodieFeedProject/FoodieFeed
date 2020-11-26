<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" 
    prefix="fn" %> 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<!-- Check to see if the user is logged in. Otherwise, redirect back to the login page.-->

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

	<div class="container-fluid text-center">
		<div class="col-sm-8 text-center">
		<form action="ReviewController" name="addForm" method="post" >
			<div class="from_style">
				<label> <span>Username:</span> <input id="username" type="text"
					name="username" placeholder="${session.getAttribute("username")}" />
				</label> 
				<label> <span>Bio:</span> <textarea id="text"
						name="bio"  placeholder="please new bio">${review.description}</textarea>
				</label> 
				<br>
				<input type="submit" class="btn btn-info" value="Save Edits" />
		</form>
	</div>
</div>
</div>
	<%@ include file="footer.jsp"%>
	<script src="js/uploadImg.js"></script>
	

</body>
</html>
