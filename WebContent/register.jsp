<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR" import="com.mie.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" 
    prefix="fn" %> 
 <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">

<head>
<title>Foodie Feed - Create Account</title>
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

	<div class="container-fluid text-center">
		<div class="col-sm-8 text-center">		
				
		<form action="UserController" name="addForm" method="post" >
			<div class="row content">
		   		<div class="col-sm-3 sidenav">
		   		<h1>
					Create an Account
				</h1>
		     
					Upload a Profile Picture
				
				 <input id="profilePic" value="${user.getProfilePic()}" type="hidden" /> 
				<div class="upload-content">  
					<div class="content-img">
						<ul class="content-img-list"></ul>
						<div class="file">
							<i class="gcl gcladd"></i> <input type="file" name="file"
								accept="image/*" id="upload">
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
		   <br>
		   <br>
		   <br>
			<div class="from_style">
				<label> <span>Username:</span> <input id="username" type="text"
					name="desiredUsername" value="${user.getUsername()}" placeholder="please enter your desired username" />
				</label>
				<label> <span>Password:</span> <input class="form-control" style="width:275px; height:27px;" type="password" name="password" placeholder="please create a password" />
				</label>		
				<label> <span>Email:</span> <input id="email" type="text"
					name="email" value="${user.getEmail()}" placeholder="please enter your email" />
				</label> 
				<label> <span>Name:</span> <input id="name" type="text"
					name="name" value="${user.getName()}" placeholder="please enter your name" />
				</label>  
				<label> <span>Bio:</span> <textarea id="text"
						name="bio" placeholder="please enter your new bio"> ${user.getBio()}</textarea>
				</label>
				<br>
				<input type="submit" class="btn btn-def btn-block" onclick="SendForm()" value="Register" />
		</div>
		</div>
		</div>
		</form>
	</div>
</div>

<script src="js/uploadImg.js"></script>
<script type="text/javascript">


function SendForm()
{	
	var hideInput2 = document.createElement("input");
	hideInput2.type = "hidden";
	hideInput2.name = "profilePic";
	//var dd="";
	var dd = window.btoa(imgSrc[0]);
	
	
	hideInput2.value = dd; //get the value
	document.addForm.appendChild(hideInput2);

	document.addForm.submit();

}
</script>


</body>
</html>
