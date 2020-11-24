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
				 <input id="listphotoURL" value="${review.photoURL}" type="hidden" /> 
				<ul id="play">
					
					
					
				</ul>
				<ul id="button">
					
					
					
				</ul>
			</div>
		</div>
		<div class="acontent">
		  <div class="tagcontainer">
		   <c:forEach items="${review.getTags()}" var="mytag">
		    <span>${mytag}</span>
		   </c:forEach>
		  </div>
		  <div class="titlecontainer">
		   <span>${review.title}</span>
		   <input id="reviewID" value="${review.reviewID}" type="hidden" /> 
		   <input id="username" value="${review.username}" type="hidden" /> 
		   <span style="float: right;">
		    <span style="color: red;cursor:pointer" onclick="btnlike()">?</span>${review.numLikes}
		   </span>
		   <span class="mytime">${review.uploadDate}</span>
		   <span class="detail">
		   ${review.getDescription()}
		   </span>
		  </div>
		 <div class="from_style">
				<label> <span>Overall :</span>
					<div class="score_star">
					 <c:forEach var="i" begin="1" end="5" step="1">
					     <c:choose>
							<c:when test="${i<=review.overallRating }">
								<i class="on">★</i>
							</c:when>
							<c:otherwise>
								<i>★</i>
							</c:otherwise>
							</c:choose>
					 </c:forEach>                                                
					</div>
				</label> 
				<div id="folddiv">
				<label> <span>My Order </span>
					<div class="score_star">
					                                          
					</div>
				</label> 
				</div>
				<div id="expanddiv" style="display:none;">
				 <label> <span>Food :</span>
					<div class="score_star">
						 <c:forEach var="i" begin="1" end="5" step="1">
					     <c:choose>
							<c:when test="${i<=review.foodRating }">
								<i class="on">★</i>
							</c:when>
							<c:otherwise>
								<i>★</i>
							</c:otherwise>
							</c:choose>
					 </c:forEach>                  
					</div>
				</label> <label> <span>Environment :</span>
					<div class="score_star">
						 <c:forEach var="i" begin="1" end="5" step="1">
					     <c:choose>
							<c:when test="${i<=review.environmentRating }">
								<i class="on">★</i>
							</c:when>
							<c:otherwise>
								<i>★</i>
							</c:otherwise>
							</c:choose>
					 </c:forEach>                 
					</div>
				</label> <label> <span>Service :</span>
					<div class="score_star">
						<c:forEach var="i" begin="1" end="5" step="1">
					     <c:choose>
							<c:when test="${i<=review.serviceRating}">
								<i class="on">★</i>
							</c:when>
							<c:otherwise>
								<i>★</i>
							</c:otherwise>
							</c:choose>
					 </c:forEach> 
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
					<c:forEach items="${review.myOrder}" var="myOrder">
						<tr>
							<td>${myOrder.item}</td>
							<td>$ ${myOrder.price}</td>
						</tr>
					</c:forEach>

				</table>
				</div>
				<div class="opreate">
				  <input type="button" onclick="editPost()" value="edit"/>
				   <input type="button" onclick="delPost()" value="delete"/>
				    <input type="button" style="width:100px" id="btnExpand" onclick="expandAll()" value="Expand All"/>
                </div>
			</div>
		 <div class="mycomment">
		 <div class="commenttitle">Comments</div>
		   <ul>
					<c:forEach items="${review.comments}" var="myComments">
						<li class="feed">
							<div class="avatar">
								<img src="img/testimg/timg.jpg" alt="" />
							</div>
							<!--first start-->
							<div class="box1">
								<p class='current'>
									<a href="">${myComments.getUser()}</a> ${myComments.getComment()}
								</p>
								<p class='info'>
									<span><a href="">delete </a><a href=""></a></span> <strong>${myComments.getDate() } &nbsp;&nbsp;&nbsp;</strong><strong style="color: red"></strong>
								</p>
								
							</div> <!--first end-->
							<div class="clear"></div>
						</li>
					</c:forEach>

				</ul>
			<div>
			<textarea id="myComment" class="reply_area mycomment"></textarea>
             <input type="button" onclick="subComment()" value="send"/>
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
			/* $(".score_star >i").click(function(event) {
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
			}); */

			$(":checkbox").click(function() {
				//
				$(this).prop("checked", true);
				$(this).siblings().prop("checked", false); //
			});
			
			var arrp=new Array();
			arrp=$("#listphotoURL").val().split(',');
			var ss="";
			var yy="";
			for(var i=0;i<arrp.length;i++)
			{
				var itemx=window.atob(arrp[i]);
				if(i==0){
				 ss=ss+'<li style="display: block;"><img src="'+itemx+'" alt="" /></li>'
				 yy=yy+'<li><div style="background: #A10000;"></div></li>'
				}else{
					ss=ss+'<li ><img src="'+itemx+'" alt="" /></li>'
					 yy=yy+'<li><div ></div></li>'
				}
			  
			}
			if(ss!=""){
				$("#play").html(ss);
				$("#button").html(yy);
				 var oButton=document.getElementById('button');
				 var aDiv=oButton.getElementsByTagName('div');
				 for(var i=0; i<aDiv.length; i++) {
					 	aDiv[i].index=i;
					 	aDiv[i].onmouseover=function(){
					 		if(now==this.index) return;
					 		now=this.index;
					 		tab();
					 	}
				  }
			 }

		});
		
		
		function subComment() {
			
			var url = "CommentController";
			var tempForm = document.createElement("form");
			tempForm.id = "tempForm1";
			tempForm.method = "POST";
			tempForm.action = url;
			tempForm.target = "_self";
			var hideInput1 = document.createElement("input");
			hideInput1.type = "hidden";
			hideInput1.name = "reviewID";
			hideInput1.value = $("#reviewID").val(); //get the value
			tempForm.appendChild(hideInput1);

			var hideInput2 = document.createElement("input");
			hideInput2.type = "hidden";
			hideInput2.name = "username";
			hideInput2.value = $("#username").val(); //get the value
			tempForm.appendChild(hideInput2);

			var hideInput3 = document.createElement("input");
			hideInput3.type = "hidden";
			hideInput3.name = "comment";
			hideInput3.value = $('#myComment').val(); //get the value
			tempForm.appendChild(hideInput3);

			//add tempForm after documentbody
			document.body.appendChild(tempForm);
			//alert("1:"+hideInput1.value)
			tempForm.submit();
			document.body.removeChild(tempForm); 
		}
		
	   function expandAll() {
		    //var sd=$("#reviewID").val();
			//var url = "ReviewController?action=displayFull&&reviewID="+$("#reviewID").val();
			//window.location.href=url;
			
		  
		    if ($("#folddiv").css("display") == 'none') {
				$("#expanddiv").css("display", "none");
				$("#folddiv").css("display", "block");
				$("#btnExpand").val("expand All")
				
			}else{
				$("#expanddiv").css("display", "block");
				$("#folddiv").css("display", "none");
				$("#btnExpand").val("flod")
			}
		}
		function delPost() {
			//var sd=$("#reviewID").val();
			var url = "ReviewController?action=delete&&reviewID="
					+ $("#reviewID").val();
			window.location.href = url;
		}
		function editPost(){
			var url = "ReviewController?action=edit&&reviewID="
				+ $("#reviewID").val();
		    window.location.href = url;
		}
		function btnlike() {
			//alert("hello world")
			var url = "ReviewController?action=like&&reviewID="
					+ $("#reviewID").val();
			window.location.href = url;
		}
	</script>
</body>
</html>