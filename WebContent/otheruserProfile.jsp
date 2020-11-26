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
<title>Other User Profile</title>
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

	<%@ include file="nav_bar_loggedin.jsp"%>
<!-- get user session -->
	<%@ include file="nav_bar_loggedin.jsp"%>
	<%
		User user = (User) session.getAttribute("currentSessionUser");
		String username = (String) session.getAttribute("username");
	%>
	
	<br>
		<span class="glyphicon glyphicon-plus"></span>
    <span><a href="UserController?action=followUnfollowUser&&otherUsername=${user.getUserByUsername()}"><button><c:out value="${followButtonMessage}" /></button></a></span>
	<header>
	<img src="img/testimg/2.jpg" alt="UserProfile" width="100" height="100" class="profileimg" />
	<div style="text-align:center" class="user-info">
	<li><span> <c:out value="${user.getUserByUsername()}"></c:out> </span></li>
	</div>
	</header>
	<section>
			<div style="text-align:center" class="user-info">
				<ul>
					<li> 
						<!-- value="${fn:length(uer.getFollowing())}"-->
						3344
						<span>FOLLOWING</span>
					</li>
					<li>
						<!-- value="${fn:length(uer.getFollowers())}"-->
						5566
						<span>FOLLOWERS</span>
					</li>
					<li>
						<!--c:out value="${fn:length(uer.getTagFollow())}"-->
						7788
						<span>TAGS</spam>
					</li>
				</ul>
			</div>
</section>

        <br>@somename94<!--<c:out value="${feedReview.getUsername()}" />-->
      </div>
    </div>
    <div class="squaremid">
      <div style="margin-right: 20px">
        <br>
        <div>
          <img src="${feedReview.getPhotoURL()}" class="nextimg" style="width:264px;height:224px">
        </div>
        <font size=4>Best burgers in town!<!--<c:out value="${feedReview.getTitle()}" />-->
        </font>
        <br><br>
        Overall Rating: 4<!--<c:out value="${feedReview.getOverallRating()}" />-->&nbsp;/&nbsp;5
        <br><br>
        &nbsp;&nbsp;My Order Total:
        <br>
        $40<!--<c:out value="${feedReview.getTotalAmt()}" />-->
        <br><br>
        Great burgers and shakes with good service and atmosphere.<!--<c:out value="${feedReview.getDescription()}" />-->
        <br><br>
        <c:forEach items="${feedReviews.getTags()}" var="tag">
          <a href="TagController?action=visitTagPage">#<c:out value="${tag}" /></a>
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
    </center>
	<%@ include file="footer.jsp"%>


</body>
</html>
