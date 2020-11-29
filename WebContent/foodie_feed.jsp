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

<link rel="stylesheet" type="text/css" href="css/poststyle.css">

</head>
<body>

	<div class="container-fluid">
	<div class="row content">
  <%@ include file="nav_bar_loggedin.jsp"%>
    <h1>Welcome to your Foodie Feed,&nbsp;<%=session.getAttribute("name") %></h1>
      <center><br>
      <div style="margin-left:20px">
      <a href="ReviewController?action=create"><font size=4>ADD POST</font></a>
    </div>
      <br>
  <c:forEach items="${feedReviews}" var="feedReview">
	<div class="squaretop">
      <div style="margin-left: 20px">
        <br>
        <font size = 5><c:out value="${feedReview.getNameOfUser()}" />
        </font>
        <br>
        <a href="UserController?action=otherProfile&&otherUsername=${feedReview.getUsername()}">@ <c:out value="${feedReview.getUsername()}" /></a>
      </div>
    </div>
    <div class="squaremid">
      <div style="margin-left: 20px" >
        <br>
        
        <div>
       <input id="listphotoURL" value="${feedReview.getPhotoURL()}" type="hidden" />		 
		<i id="play"></i>
		
		</div>
				
        <font size=4><c:out value="${feedReview.getTitle()}" />
        </font>
        <br><br>
        Overall Rating: <c:out value="${feedReview.getOverallRating()}" />&nbsp;/&nbsp;5
        <br><br>
        My Order Total:$<c:out value="${feedReview.getTotalAmt()}" />
        <br><br>
        <c:out value="${feedReview.getDescription()}" />
        <br><br>
          <a href="ReviewController?action=displayFull&&reviewID=${feedReview.getReviewID()}" class="button"><button>See More</button></a>
      	  <br><br>
      	  <c:forEach items="${feedReview.getTags()}" var="tag">
          <a href="TagController?action=visitTagPage&&tagname=${tag.getTagName()}&&numPosts=${tag.getNumPosts()}">#<c:out value="${tag.getTagName()}" /></a>         
        </c:forEach>
        </div>
          
     	<br>       
      </div>
      <div class="squarebot">
        <div style="margin-left: 20px">
          <img src=https://upload.wikimedia.org/wikipedia/commons/thumb/f/f1/Heart_coraz%C3%B3n.svg/1200px-Heart_coraz%C3%B3n.svg.png width="30">&nbsp;&nbsp;<c:out value="${feedReview.getNumLikes()}" />&nbsp;&nbsp;&nbsp;
          <img src=https://upload.wikimedia.org/wikipedia/commons/1/11/Blue-Speech-Bubble.png width="30">&nbsp;&nbsp;<c:out value="${feedReview.getComments().size()}" />
        </div>
      </div>
    <br><br>
    </c:forEach>
    </center>
   
	</div>
	</div>
	<script type="text/javascript">
		$(function () {			
			
			
			var arrp=new Array();
			arrp=$("#listphotoURL").val().split(',');
				
			var ss="";			
			var itemx=window.atob(arrp[0]);
			ss=ss+'<img src="'+itemx+'" class="nextimg" style="width:264px;height:224px"/>'
				
			if(ss!=""){
				$("#play").html(ss);				
			 }	
			
			
		});		
		</script>
</body>
</html>