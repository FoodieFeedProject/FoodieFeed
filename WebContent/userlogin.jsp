<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="com.mie.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
<title>FoodieFeed - Login</title>
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
				<h1>Welcome to FoodieFeed!</h1>
				${message}
				<h4>Login to Your Account</h4>

				<center><form action="LoginController">
				<div class="form-group input-group">
            	<span class="input-group-addon"><i class="glyphicon glyphicon-user"></i></span>
            	<input class="form-control" type="text" name='username' placeholder="username" />
          		</div>
          		<div class="form-group input-group">
            	<span class="input-group-addon"><i class="glyphicon glyphicon-lock"></i></span>
            	<input class="form-control" type="password" name='password' placeholder="password" />
         		</div>
         		<div class="form-group">
            	<button type="submit" class="btn btn-def btn-block">Login</button>
          		</div>
          		<div class="form-group text-center">
            	<p>Don't have an account? <span
						class="glyphicon glyphicon-hand-right"></span> Click <a href="register.jsp">here</a> to register today!</p>
          		</div>
				</form></center>
			</div>
			<div class="col-sm-2 sidenav">
				<!-- You can put right sidebar links here if you want to. -->
			</div>
		</div>
	</div>

	<%@ include file="webfooter.jsp"%>

</body>

</html>