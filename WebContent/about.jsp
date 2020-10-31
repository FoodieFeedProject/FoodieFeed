<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
<title>Foodie Feed - About</title>
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
				<h1>About</h1>
				This is a sample web app created for the <b>MIE350</b> course at the
				<a href="http://www.utoronto.ca">University of Toronto</a> for
				demonstration purposes. <br> <br> It was developed using
				the following:<br>
				<ul>
					<li>Eclipse Java EE IDE for Web Developers,</li>
					<li>The Java programming language,</li>
					<li>Java Server Pages (JSP),</li>
					<li>Apache Tomcat 7.0, and</li>
					<li>A simple Microsoft Access database.</li>
				</ul>
				As well, the following <b>open-source</b> tools were used:
				<ul>
					<li><a href="http://getbootstrap.com/">The Bootstrap CSS
							theme</a>, and</li>
					<li><a href="https://kryogenix.org/code/browser/sorttable/">Stuart
							Langridge's Sort Table JavaScript</a></li>
				</ul>
				<hr>
				<h2>Foodie Feed</h2>
				<br> This is a community site for foodies! Here, you can: <br>
				<ul>
					<li><b>Create</b> a new food review post.</li>
					<li><b>Follow</b> tags or other foodies.</li>
					<li><b>Like and Comment</b> on posts.</li>
					<li><b>Update</b> your profile information.</li>
					<li><b>Delete</b> your posts.</li>
					<li><b>Discover</b> users or tags.</li>
					<li><b>Log In or Sign Up</b> to get started!.</li>
				</ul>
				<hr>
				<h2>Organization of Files</h2>
				<img src="img/mie350_webapp_schematic.jpg"> <br />
				<h2>Using This For Your Projects</h2>
				<br> You are free to use and modify this template file for your
				projects.<br />
				<br />
			</div>
			<div class="col-sm-2 sidenav">
				<!-- You can put right sidebar links here if you want to. -->
			</div>
		</div>
	</div>

	<%@ include file="footer.jsp"%>

</body>
</html>
