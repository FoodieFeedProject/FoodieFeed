<link rel="stylesheet" href="css/bootstrap.min.css">
<link href="//netdna.bootstrapcdn.com/bootstrap/3.0.0/css/bootstrap-glyphicons.css" rel="stylesheet">
<link rel="stylesheet" type="text/css" href="css/poststyle.css">

	<nav class="navbar navbar-default">
	<div class="container-fluid">
	
		<div class="header-avatar">
			<img src="img/logo.png" alt="">
		</div> 
		
		
		<div class="collapse navbar-collapse" id="myNavbar">
			<!-- The following code can be added to include a Login button -->
			<ul class="nav navbar-nav navbar-right">
				<li><a href="LogoutController"><span
						class="glyphicon glyphicon-log-out"></span> LogOut</a></li>
			</ul>
			<button type="button" class="navbar-toggle" data-toggle="collapse"
				data-target="#myNavbar">
				<span class="icon-bar"></span> <span class="icon-bar"></span> <span
					class="icon-bar"></span>
			</button>
				<ul class="nav navbar-nav">			
				<li><a href="ReviewController?action=listReviewsOnFeed">Home</a></li>
				<li><a href="TagController?action=trendingList">Discover</a></li>
				<li><a href="aboutFoodieFeed.jsp">About</a></li>
				<li><a href="UserController?action=myProfile">My Profile</a></li>
			</ul>
		</div>
	</div>
	</nav>