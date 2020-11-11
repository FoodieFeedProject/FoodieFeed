<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
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
</head>
<body>

	<%@ include file="navbar.jsp"%>



	<div class="container-fluid text-center">
		<div class="row content">
			<div class="col-sm-2 sidenav">
				<!-- You can put left sidebar links here if you want to. -->
			</div>
			<div class="col-sm-8 text-left">
				<h1>Welcome to Foodie Feed!</h1>
				<p>Hello World! This is Team 3's server prototype. In this project, our team is designing and building a web
					application. Foodie Feed is the name of our community site for food lovers and bloggers.
					On this site, you can share your food reviews, follow other foodies and various tags that 
					you are interested in.
				</p>
				<hr>
				<p>
				Click the link below to query from the Food Review Database
				</p>
				<br>
				<button>
				<a href="/FoodieFeed/ReviewController?action=listReviews">
					Test the Database Connection
				</a>
				</button>
				<!--  <h3>Internet Browsers</h3>
				<p>When designing your projects, please make sure to test them
					with the following browsers:
				<ul>
					<li>Google Chrome</li>
					<li>Mozilla Firefox</li>
					<li>Internet Explorer (or Microsoft Edge)</li>
				</ul>
				Please <b>do not rely on the built-in web browser in Eclipse</b> for
				testing. Cascading Style Sheet (CSS) templates will not render
				properly with Eclipse's built-in browser.
				</p> 
				-->
			</div>
			<div class="col-sm-2 sidenav">
				<!-- You can put right sidebar links here if you want to. -->
			</div>
		</div>
	</div>

	<%@ include file="footer.jsp"%>


</body>
</html>
