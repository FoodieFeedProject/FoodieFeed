<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="com.mie.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
<title>FoodieFeed - Registration</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.min.css">
<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<!-- Date Picker Javascript -->
<!-- https://jqueryui.com/datepicker/ -->
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>

<link rel="stylesheet" type="text/css" href="css/mystyle.css">
</head>
<body>

	<%@ include file="nav_bar.jsp"%>

	<div class="container-fluid text-center">
		<div class="row content">
			<div class="col-sm-2 sidenav">
				<!-- You can put left sidebar links here if you want to. -->
			</div>
			<div class="col-sm-8 text-center">
				<h1>Register for New FoodieFeed Account</h1>
				<h4>Please fill in details for your account.</h4>
				<br>
				${usernameStatus}
				<br>
				<center>
				<form action="UserController" name="registerForm" method="post" >				
				<!-- Controller for regitration page-->
					<table style="with: 50%">
				<tr>
					<td>Username</td>
					<td><input type="text" name="desiredUsername"/></td>
					
				</tr>
				<tr>
					<td>Password</td>
					<td><input type="password" name="password" /></td>
				</tr>
				<tr>
					<td>Email</td>
					<td><input type="text" name="email" value="${user.getEmail()}"/></td>
				</tr>
				<tr>
					<td>Name</td>
					<td><input type="text" name="name" value="${user.getName()}" /></td>
				</tr>
				<tr>
					<td>Bio</td>
					<td><textarea name="bio" cols="30" rows="5" value ="${user.getBio()}"></textarea>
				</tr>
				<tr>
					<td>Profile Picture</td>
					<td><input type = "file" name = "profilePic" size = "50" /><br />
					</td>
					<td>
         				<input type = "submit" value = "Upload File" />
         			</td>
         		<td>
				</tr></table>
								
			<input type="submit" class="btn btn-info" value="Register" /></form>
			</center>
			</div>
			<div class="col-sm-2 sidenav">
				
			</div>
		</div>
	</div>

	<%@ include file="webfooter.jsp"%>

</body>

</html>