<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="com.mie.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
<title>Foodie Feed - Discover</title>
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
<div class="container-fluid">
	<div class="row content">
	<% if (session.getAttribute("username") == null) { %>
		<%@ include file="nav_bar.jsp"%>
	<% } else { %>
		<%@ include file="nav_bar_loggedin.jsp"%>
	<% } %>
	<center>
	<div class="from_style">
	 
    <h2>Discover A World of Food</h2>
     
      
		<form method="POST" action='SearchControllerUser' name="frmSearchUser">
					
			Search for a user: <input type="text" name="keyword"
			value="<c:out value="${user.searchword}" />"><input
				type="submit" class="button" value="Search" />
						
		</form>
       		 <br>
          <form method="POST" action='SearchControllerTag' name="frmSearchTag">
            Search for a tag: <input type="text" name="keyword" 
              value="<c:out value="${tag.searchword}" />"><input
							type="submit" class="button" value="Search" />
          </form>
          
        <br><br>
        <h2>Trending Tags</h2>
        <br>
        <c:forEach items="${trendingList}" var="tag">
          <br>
          <a href="TagController?action=visitTagPage&&tagname=${tag.getTagName()}&&numPosts=${tag.getNumPosts()}">#<c:out value="${tag.getTagName()}" /></a>
          <br>
        </c:forEach>
        <br>
        <br>
	
	</div>
	</center>
</div>
</div>


</body>
</html>