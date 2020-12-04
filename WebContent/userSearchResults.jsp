<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="com.mie.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
<title>Foodie Feed - Search Results</title>
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
	
	<div class="container-fluid text-center">
	<div class="row content">
	 <center>
	<div class="from_style">
	
    <h2>Search Results for a User</h2>
     
        <c:forEach items="${users}" var="user">
          <br>
          <a href="UserController?action=otherProfile&&otherUsername=${user.getUsername()}">@<c:out value="${user.getUsername()}" /></a>
          <br>
        </c:forEach>
	
	</div>
	</center>
	</div>
	</div>
	<%@ include file="webfooter.jsp"%>

</body>
</html>