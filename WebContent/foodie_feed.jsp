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
  <style>
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

	<%@ include file="navbar.jsp"%>
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
          <img src=https://images.pexels.com/photos/1640777/pexels-photo-1640777.jpeg?auto=compress&cs=tinysrgb&dpr=1&w=500 width="300" class="nextimg">
        </div>
        &nbsp;&nbsp;<c:out value="${feedReview.getTitle()}" />
        <br>
        &nbsp;&nbsp;Overall Rating: <c:out value="${feedReview.getOverallRating()}" />&nbsp;/&nbsp;5
        <br><br>
        &nbsp;&nbsp;My Order Total:
        <br>
        &nbsp;&nbsp;<c:out value="${feedReview.getTotalAmt()}" /><!--***Need getter in Review.java-->
        <br><br>
        &nbsp;&nbsp;<c:out value="${feedReview.getDescription()}" />
        <br><br>
        &nbsp;&nbsp;<c:out value="${feedReview.getUsedTagNames()}" /><!--***Need getter in Review.java-->
      </div>
      </div>
      <div class="squarebot">
        <div style="margin-left: 20px">
          <img src=https://upload.wikimedia.org/wikipedia/commons/thumb/f/f1/Heart_coraz%C3%B3n.svg/1200px-Heart_coraz%C3%B3n.svg.png width="30" class="nextimg">&nbsp;&nbsp;<c:out value="${feedReview.getNumLikes()}" />&nbsp;&nbsp;&nbsp;
          <img src=https://upload.wikimedia.org/wikipedia/commons/1/11/Blue-Speech-Bubble.png width="30">&nbsp;&nbsp;<c:out value="${feedReview.getNumComments()}" /><!--***Need getter in Review.java-->
        </div>
      </div>
    <br><br>
    </c:forEach>

	<%@ include file="footer.jsp"%>


</body>
</html>