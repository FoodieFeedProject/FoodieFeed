<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="com.mie.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
<title>MIE350 Sample Web App-create post</title>
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
</style>
</head>
<body>
	<nav class="navbar navbar-default">
	<div class="container-fluid">
		<div class="header-avatar">
			<img src="img/testimg/avatar.jpg" alt="">
		</div>
		<div class="collapse navbar-collapse" id="myNavbar">
			<ul class="nav navbar-nav">
				<li><a href="index.jsp">Foodie Feed</a></li>
				<li><a href="about.jsp">Discovery</a></li>
				<li><a href="about.jsp">Profile</a></li>
			</ul>
		</div>
	</div>
	</nav>

	<%
		Member member = (Member) session.getAttribute("currentSessionUser");
		String username = (String) session.getAttribute("username");
		String firstname = (String) session.getAttribute("firstname");
		String lastname = (String) session.getAttribute("lastname");
	%>

	<div class="container-fluid text-center">
		<div class="row content" style="border-bottom: 1px solid #cccccc;">
			<div id="flash">
				<div id="prev"></div>
				<div id="next"></div>
				<ul id="play">
					<li style="display: block;"><img src="img/testimg/1.jpg" alt="" /></li>
					<li><img src="img/testimg/2.jpg" alt="" /></li>
					<li><img src="img/testimg/3.jpg" alt="" /></li>
					<li><img src="img/testimg/4.jpg" alt="" /></li>
					<li><img src="img/testimg/5.jpg" alt="" /></li>
					<li><img src="img/testimg/6.jpg" alt="" /></li>
					<li><img src="img/testimg/7.jpg" alt="" /></li>
					<li><img src="img/testimg/8.jpg" alt="" /></li>
				</ul>
				<ul id="button">
					<li><div style="background: #A10000;"></div></li>
					<li><div></div></li>
					<li><div></div></li>
					<li><div></div></li>
					<li><div></div></li>
					<li><div></div></li>
					<li><div></div></li>
					<li><div></div></li>
				</ul>
			</div>
		</div>
		<div class="acontent">
		  <div class="tagcontainer">
		    <span>#Toronto</span>
		    <span>#DT</span>
		  </div>
		  <div class="titlecontainer">
		   <span>Title Balabala</span>
		   <span style="float: right;">
		    <span style="color: red;">❤</span>88457
		   </span>
		   <span class="mytime">2020.08.05</span>
		   <span class="detail">
		   I like Food I like Food I like Food I like Food I like Food I like Food I like Food I like Food I like Food
		   </span>
		  </div>
		 <div class="basic-grey">
				<label> <span>Overall :</span>
					<div class="score_star">
						<i>★</i> <i>★</i> <i>★</i> <i>★</i> <i>★</i>
					</div>
				</label> <label> <span>Food :</span>
					<div class="score_star">
						<i>★</i> <i>★</i> <i>★</i> <i>★</i> <i>★</i>
					</div>
				</label> <label> <span>Environment :</span>
					<div class="score_star">
						<i>★</i> <i>★</i> <i>★</i> <i>★</i> <i>★</i>
					</div>
				</label> <label> <span>Service :</span>
					<div class="score_star">
						<i>★</i> <i>★</i> <i>★</i> <i>★</i> <i>★</i>
					</div>
				</label>
				<table class="myOrder">
					<tr>
						<th>My Order</th>
						<th></th>
					</tr>
					<tr>
						<th>Description</th>
						<th>price</th>
					</tr>
					<tr>
						<td>Budae Hot Pot</td>
						<td>$29.99</td>
					</tr>
					<tr>
						<td>Bossam Boiled Pork M</td>
						<td>$23.99</td>
					</tr>
				</table>
				<div class="opreate">
				  <input type="button" value="edit"/>
				   <input type="button" value="delete"/>
                </div>
			</div>
		 <div class="mycomment">
		 <div class="commenttitle">Comments</div>
		   <ul>
            <li class="feed">
                <div class="avatar">
                    <img src="img/testimg/timg.jpg" alt="" />
                </div><!--first start-->
                <div class="box1">
                    <p class='current'>
                        <a href="">Jim</a>
                        OMG ,this is very good!
                    </p>
                    <p class='info'>
                        <span><a href="">delete </a><a href="">comment</a></span>
                        <strong>just now &nbsp;&nbsp;&nbsp;</strong><strong style="color:red">❤895</strong>
                    </p>
                    <p class="line">
                    </p>
                    <!--second start-->
                    <div class="box2">
                        <div class="avatar">
                            <img src="img/testimg/tom.jpg" alt="" />
                        </div>
                        <div class="box2_comments">
                            <p class='say'>
                                <a href="">Tom</a>
                                 lololol
                            </p>
                            <p>
                                <strong>2020-11-01</strong>
                                <a href="">delete</a>
                                <a href="">reply</a>
                            </p>
                            <!--third start-->
                            <div class="box3">
                                <div class="avatar">
                                    <img src="img/testimg/timg.jpg" alt="" />
                                </div>
                                <div class="box3_comments">
                                    <p class='say'>
                                        <a href="">Jim</a>
                                        It's very delicious
                                    </p>
                                    <p>
                                        <strong>a minute ago</strong>
                                        <a href="">delete</a>
                                        <a href="">reply</a>
                                    </p>
                                </div>
                                <div class="clear">
                                </div>
                            </div>
                            <!--third end-->
                        </div>
                        <div class="clear">
                        </div>
                    </div>
                    <!--second end-->
                    <p class="line">
                    </p>
                    <textarea name="" class="reply_area">
                          Comment
                    </textarea>
                </div>
                <!--first end-->
                <div class="clear">
                </div>
            </li>
					<li class="feed">
						<div class="avatar">
							<img src="img/testimg/timg.jpg" alt="" />
						</div>
						<!--first start-->
						<div class="box1">
							<p class='current'>
								<a href="">Jim</a> OMG ,this is very good!
							</p>
							<p class='info'>
								<span><a href="">delete </a><a href="">comment</a></span> 
								 <strong>just now &nbsp;&nbsp;&nbsp;</strong><strong style="color:red">❤89</strong>
							</p>
							<!--second end-->
							<p class="line"></p>
							<textarea name="" class="reply_area">
                             Comment
                             </textarea>
						</div>
					</li>
				</ul>
			<div>
			<textarea class="reply_area mycomment">
                             Comment
             </textarea>
             <input type="button" value="send"/>
             </div>
		 </div>
		</div>
	</div>
	<footer class="container-fluid text-center">
	<p>&#169; MIE350 Group3-Community Site for Foodies</p>
	</footer>
	<script type="text/javascript">
		$(function() {
			// Rating
			$(".score_star >i").click(function(event) {
				var ind = $(this).index();
				var ii = $(this).parent().find("i");
				switch (ind) {
				case 0:
					ii.removeClass("on").slice(0, 1).addClass("on");
					break;
				case 1:
					ii.removeClass("on").slice(0, 2).addClass("on");
					break;
				case 2:
					ii.removeClass("on").slice(0, 3).addClass("on");
					break;
				case 3:
					ii.removeClass("on").slice(0, 4).addClass("on");
					break;
				case 4:
					ii.removeClass("on").slice(0, 5).addClass("on");
					break;
				default:
					break;
				}
			});

			$(":checkbox").click(function() {
				//
				$(this).prop("checked", true);
				$(this).siblings().prop("checked", false); //
			});

		});
	</script>
</body>
</html>