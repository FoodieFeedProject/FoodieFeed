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

<!-- jihoonchung -->
<link rel="stylesheet" href="css/pokemonexample.css">


<script src="js/jquery.min.js"></script>
<script src="js/bootstrap.min.js"></script>

<!-- Date Picker Javascript -->
<!-- https://jqueryui.com/datepicker/ -->
<link rel="stylesheet" href="css/jquery-ui.css">

<link rel="stylesheet" type="text/css" href="css/poststyle.css">

<link rel="stylesheet" href="css/font_1805932_ysrcp4y0uy9.css">
<link rel="stylesheet" href="css/uploadImg.css">
<link rel="stylesheet" id="templatecss" type="text/css"
	href="css/basic-grey.css">
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
		<div class="row content">
		   <div class="col-sm-3 sidenav">
		        <h1>
					Img Upload
				</h1>
				
				<!-- jihoonchung -->
				<div class="file-upload">
	                <button class="file-upload-btn" type="button" onclick="$('.file-upload-input').trigger( 'click' )">
	                    Add Image</button>
	
	                <div class="image-upload-wrap">
	                    <input class="file-upload-input" type='file' onchange="readURL(this);" accept="image/*" />
	                    <div class="drag-text">
	                        <h3>Drag and drop a file or select add Image</h3>
	                    </div>
	                </div>
	                <div class="file-upload-content">
	                    <img class="file-upload-image" id="face_image" src="#" alt="your image" />
	                    <div class="image-title-wrap">
	                        <button type="button" onclick="removeUpload()" class="remove-image">Remove <span
	                                class="image-title">Uploaded Image</span></button>
	                    </div>
	                </div>
	            </div>
	            
	            
	            
		   </div>
		   <div class="col-sm-8 text-left">
			<form action="" method="post" class="basic-grey">
				<label> <span>Title :</span> <input id="title" type="text"
					name="title" placeholder="please input title" />
				</label> <label> <span>Text :</span> <textarea id="text"
						name="text" placeholder="please input text"></textarea>
				</label> <label> <span>Add Tag :</span> <input id="tag" type="text"
					name="tag" placeholder="please input tag,Use; to separate" />
				</label> 
				<label style="text-align: left;font-weight:bold;">My Rating</label>
	
				<label> <span>Overall :</span>
					<div class="score_star">
						<i>★</i> <i>★</i> <i>★</i> <i>★</i> <i>★</i>
					</div>
				</label>
				<label> <span>Food :</span>
					<div class="score_star">
						<i>★</i> <i>★</i> <i>★</i> <i>★</i> <i>★</i>
					</div>
				</label>
				 <label> <span>Environment :</span>
					<div class="score_star">
						<i>★</i> <i>★</i> <i>★</i> <i>★</i> <i>★</i>
					</div>
				</label>
				 <label> <span>Service :</span>
					<div class="score_star">
						<i>★</i> <i>★</i> <i>★</i> <i>★</i> <i>★</i>
					</div>
				</label>
				<input type="checkbox" /><span>Dine-in</span>
                <input type="checkbox" /><span>Take out</span>
				</label> 
				<label style="text-align: left;font-weight:bold;">My Order</label>
				<table>
				   <thead>
                    <tr><th>Description</th><th>price</th></tr>
                   </thead>
                   <tbody>
                   <tr><td><input type="text"/></td><td><input type="text"/></td></tr>
                    <tr><td>Add </td><td></td></tr>
                   </tbody>
				</table>
				<label style="text-align: left;">Total</label>
				<label> <span>&nbsp;</span> <input type="button"
					class="button" value="Add Dishes" />
					
				</label>
				<label style="text-align: right;"> <span>&nbsp;</span> <input type="button"
					class="button" value="post" />
					
				</label>
			
			</form>
			</div>
		</div>
	</div>
	<footer class="container-fluid text-center">
	<p>&#169; MIE350 Group3-Community Site for Foodies</p>
	</footer>
	<script src="js/uploadImg.js"></script>
	<script type="text/javascript">
	 $(function() {
	        // star choose event
	        $(".score_star >i").click(function(event) {
	          var ind = $(this).index();
	          var ii = $(this)
	            .parent()
	            .find("i");
	          switch (ind) {
	            case 0:
	              ii.removeClass("on")
	                .slice(0, 1)
	                .addClass("on");
	              break;
	            case 1:
	              ii.removeClass("on")
	                .slice(0, 2)
	                .addClass("on");
	              break;
	            case 2:
	              ii.removeClass("on")
	                .slice(0, 3)
	                .addClass("on");
	              break;
	            case 3:
	              ii.removeClass("on")
	                .slice(0, 4)
	                .addClass("on");
	              break;
	            case 4:
	              ii.removeClass("on")
	                .slice(0, 5)
	                .addClass("on");
	              break;
	            default:
	              break;
	          }
	        });
	        
	        $(":checkbox").click(function(){
	        	//the status of the checkbox is checked
	        	    $(this).prop("checked",true);
	        	    $(this).siblings().prop("checked",false); 
	       });

	      });
	</script>
</body>
</html>