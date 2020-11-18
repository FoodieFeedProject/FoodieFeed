<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="com.mie.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
<title>Foodie Feed</title>
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
.squaretop {
  height: 100px;
  width: 500px;
  background-color: lightblue;
}
.squaremid {
  height: 275px;
  width: 500px;
  background-color: lightgray;
}
.squarebot {
  height: 50px;
  width: 500px;
  background-color: lightgray;
}
.nextimg {
  float: left;
}
</style>
</head>
<body>
  <%@ include file="nav_bar_loggedin"%>
    <h1>Welcome to your Foodie Feed,&nbsp;<%=session.getAttribute("firstName") %></h1>
  <c:forEach items="${feedReviews}" var="feedReview">
	<div class="squaretop">
      <div style="margin-left: 20px">
        <a href="profile.jsp"><font size = 5>
          <br><c:out value="${feedReview.getNameOfUser()}" />
        </font></a>
        <br>@<c:out value="${feedReview.getUsername()}" />
      </div>
    </div>
    <div class="squaremid">
      <div style="margin-left: 20px">
        <br>
        <div>
          <img src=<c:out value="${feedReview.getPhotoURL()}" /> class="nextimg" style="width:128px;height:128px">
        </div>
        &nbsp;&nbsp;<c:out value="${feedReview.getTitle()}" />
        <br>
        &nbsp;&nbsp;Overall Rating: <c:out value="${feedReview.getOverallRating()}" />&nbsp;/&nbsp;5
        <br><br>
        &nbsp;&nbsp;My Order Total:
        <br>
        &nbsp;&nbsp;<c:out value="${feedReview.getTotalAmt()}" />
        <br><br>
        &nbsp;&nbsp;<c:out value="${feedReview.getDescription()}" />
        <br><br>
        &nbsp;&nbsp;
        <c:forEach items="${feedReviews.getTags()}" var="tag">
          #<c:out value="${tag}">
          <br>
        </c:forEach>
          <br>
          <a href="ReviewController?action=display">More Information</a>
      </div>
      </div>
      <div class="squarebot">
        <div style="margin-left: 20px">
          <img src=https://upload.wikimedia.org/wikipedia/commons/thumb/f/f1/Heart_coraz%C3%B3n.svg/1200px-Heart_coraz%C3%B3n.svg.png width="30" class="nextimg">&nbsp;&nbsp;<c:out value="${feedReview.getNumLikes()}" />&nbsp;&nbsp;&nbsp;
          <img src=https://upload.wikimedia.org/wikipedia/commons/1/11/Blue-Speech-Bubble.png width="30">&nbsp;&nbsp;<c:out value="${feedReview.getComments().size()}" />
        </div>
      </div>
    <br><br>
    </c:forEach>

	<%@ include file="footer.jsp"%>


</body>
</html>