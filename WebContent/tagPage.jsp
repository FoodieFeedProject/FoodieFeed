<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR" import="com.mie.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" 
    prefix="fn" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
    
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
<title>Tag Profile</title>
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
	<%
		//User user = (User) session.getAttribute("currentSessionUser");
		//String username = (String) session.getAttribute("username");
	%>
	<header>
	<div style="text-align:center" class="user-info">
	<li><span><c:out value="${tag.getTagName()}"></c:out></span>
    <span><button><c:out value="${followButtonMessage}" /></button></span>
	</li>
	</div>
	</header>
	<section>
			<div style="text-align:center" class="user-info">
				<ul>
					<li> 
						900<!--<c:out value="${tag.getFollowing}" />-->
						<span>FOLLOWING</span>
					</li>
					<li>
						<c:out value="${tag.getNumPosts()}" />
						<span>Number of Posts</span>
					</li>
				</ul>
			</div>
    <center>
      <c:forEach items="${tagReviews}" var="tagReview">
        <div class="squaretop">
          <div style="margin-left: 20px">
            <br>
            <a href="UserController?action=otherProfile"><font size = 5>
              <c:out value="${tagReview.getNameOfUser()}" />
              </font></a>
            <br><c:out value="${tagReview.getUsername()}" />
          </div>
          </div>
        <div class="squaremid">
          <div style="margin-right: 20px">
            <br>
            <div>
              <img src="${tagReview.getPhotoURL()}" class="nextimg" style="width:264px;height:224px">
              </div>&nbsp;&nbsp;
            <font size=4><c:out value="${feedReview.getTitle()}" />
            </font>
            <br><br>
            Overall Rating:<c:out value="${tagReview.getOverallRating()}" />&nbsp;/&nbsp;5
        <br><br>
        My Order Total:
        <br>
        <c:out value="${tagReview.getTotalAmt()}" />
        <br><br>
        <c:out value="${tagReview.getDescription()}" />
        <br><br>
        <c:forEach items="${tagReview.getTags()}" var="tag">
          <a href="TagController?action=visitTagPage&&tagname=${tag.getTagName()}&&numPosts=${tag.getNumPosts()}">#<c:out value="${tag.getTagName()}" /></a>
          <br>
        </c:forEach>
          <br>
          <a href="ReviewController?action=displayFull">More Information</a>
      </div>
      </div>
      <div class="squarebot">
        <div style="margin-left: 20px">
          <img src=https://upload.wikimedia.org/wikipedia/commons/thumb/f/f1/Heart_coraz%C3%B3n.svg/1200px-Heart_coraz%C3%B3n.svg.png width="30" class="nextimg">&nbsp;&nbsp;&nbsp;<c:out value="${tagReview.getNumLikes()}" />&nbsp;&nbsp;&nbsp;
          <img src=https://upload.wikimedia.org/wikipedia/commons/1/11/Blue-Speech-Bubble.png width="30">&nbsp;&nbsp;<c:out value="${tagReview.getComments().size()}" />
        </div>
      </div>
    <br><br>
    </c:forEach>
    </center>
</section>
	<%@ include file="footer.jsp"%>


</body>
</html>