<html lang="en">
<head>
<title>Discover</title>
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
</head>
<body>

	<%@ include file="navbar.jsp"%>
    <h1>Discover A World of Food</h1>
      <center>
					<form method="POST" action='SearchControllerFoodie' name="frmAddUser">
						Search for a user: <input type="text" name="keyword"
							value="<c:out value="${user.searchword}" />"><input
							type="submit" class="btn btn-info" value="Submit" />
					</form>
        <br>
          <form method="POST" action'SearchControllerFoodie' name="frmAddUser">
            Search for a tag: <input type="text" name="keyword" 
              value="<c:out value="${tag.searchword}" />"><input
							type="submit" class="btn btn-info" value="Submit" />
          </form>
        <br><br>
        <h2>Trending Tags:</h2>
        <br>
        <c:forEach items="${trendingTags}" var="trendingTag">
          <br>
          #<c:out value="${trendingTag.getTagName()}" />
          <br>
        </c:forEach>
				</center>
	<%@ include file="footer.jsp"%>


</body>
</html>