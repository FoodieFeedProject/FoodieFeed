<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="com.mie.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
<title>Foodie Feed - Create Post</title>
<meta charset="utf-8">
<meta name="viewport" content="width=device-width, initial-scale=1">
<link rel="stylesheet" href="css/bootstrap.min.css">
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
	<%@ include file="nav_bar_loggedin.jsp"%>

	<%
		User user = (User) session.getAttribute("currentSessionUser");
		String username = (String) session.getAttribute("username");
		String name = (String) session.getAttribute("firstname");
		
	%>

	<div class="container-fluid text-center">
	
		<form action="ReviewController" name="addForm" method="post" >
		<div class="row content">
		   <div class="col-sm-3 sidenav">
		       <h1>
					Create a New Post
				</h1>
				Upload Image
				<div class="upload-content">
					<div class="content-img">
						<ul class="content-img-list"></ul>
						<div class="file">
							<i class="gcl gcladd"></i> <input type="file" name="file"
								accept="image/*" id="upload" multiple>
						</div>
					</div>
					<div class="modal fade bs-example-modal-lg" tabindex="-1"
						role="dialog" aria-labelledby="myLargeModalLabel">
						<div class="modal-dialog modal-lg" role="document">
							<div class="modal-content"></div>
						</div>
					</div>
				</div>
		   </div>
		   <div class="col-sm-8 text-left">

			<div class="from_style">
				<label> <span>Title :</span> <input id="title" type="text"
					name="title" placeholder="please input title" />
				</label> <label> <span>description :</span> <textarea id="text"
						name="description" placeholder="please input text"></textarea>
				</label> <label> <span>Add Tag :</span> <input id="tag" type="text"
					name="tags" placeholder="please input tag,Use# to separate" />
				</label> 
				<label style="text-align: left;font-weight:bold;">My Rating</label>
	
				<label> <span>Overall :</span>
				    <input id="overallRating" name="overallRating" value="0" type="hidden" /> 
					<div class="score_star">
						<i>★</i> <i>★</i> <i>★</i> <i>★</i> <i>★</i>
					</div>
				</label>
				<label> <span>Food :</span>
				    <input id="foodRating" name="foodRating"  value="0" type="hidden" /> 
					<div class="score_star">
						<i>★</i> <i>★</i> <i>★</i> <i>★</i> <i>★</i>
					</div>
				</label>
				 <label> <span>Environment :</span>
				    <input id="environmentRating" name="environmentRating"  value="0" type="hidden" /> 
					<div class="score_star">
						<i>★</i> <i>★</i> <i>★</i> <i>★</i> <i>★</i>
					</div>
				</label>
				 <label> <span>Service :</span>
				     <input id="serviceRating" name="serviceRating"  value="0" type="hidden" /> 
					<div class="score_star">
						<i>★</i> <i>★</i> <i>★</i> <i>★</i> <i>★</i>
					</div>
				</label>
				<input id="dineIn" name="dineIn"  value="0" type="hidden" /> 
				<input name="choose2" type="checkbox" /><span>Dine-in</span>
                <input name="choose1" type="checkbox" /><span>Take out</span>
				</label> 
				<label style="text-align: left;font-weight:bold;">My Order</label>
				<table id="mytable">
				   <thead>
                    <tr><th>item</th><th>price</th></tr>
                   </thead>
                   <tbody>
                   <tr><td><input class="item1" name="item1" type="text"/></td><td><input class="price1" name="price1" type="text"/></td></tr>
                   <tr><td><input class="item1" name="item2" type="text"/></td><td><input class="price1" name="price2" type="text"/></td></tr>
                     <tr><td><input class="item1" name="item3" type="text"/></td><td><input class="price1" name="price3" type="text"/></td></tr>
                   </tbody>
				</table>
				
				
				<label style="text-align: right;"> <span>&nbsp;</span> <input type="button" id="postsubmit"
					class="button" onclick="SendForm()" value="Post" />
					
				</label>
			</div>
		
			</div>
		</div>
		</form>
	</div>
	
	<%@ include file="webfooter.jsp"%>
	
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
	              $(this)
		            .parent().prev().val(1);
	              break;
	            case 1:
	              ii.removeClass("on")
	                .slice(0, 2)
	                .addClass("on");
	              $(this)
		            .parent().prev().val(2);
	              break;
	            case 2:
	              ii.removeClass("on")
	                .slice(0, 3)
	                .addClass("on");
	              $(this)
		            .parent().prev().val(3);
	              break;
	            case 3:
	              ii.removeClass("on")
	                .slice(0, 4)
	                .addClass("on");
	              $(this)
		            .parent().prev().val(4);
	              break;
	            case 4:
	              ii.removeClass("on")
	                .slice(0, 5)
	                .addClass("on");
	              $(this)
		            .parent().prev().val(5);
	              break;
	            default:
	              break;
	          }
	        });
	        
	        $(":checkbox").click(function(){
	        	 var one = $(this).attr('name');
	        	 if(one=="choose1"){
	        		 $("#dineIn").val(0)
	        	 }else{
	        		 $("#dineIn").val(1)
	        	 }
	        	//the status of the checkbox is checked
	        	    $(this).prop("checked",true);
	        	    $(this).siblings().prop("checked",false); 
	       });
	        
	        
	      });
	 
	    function SendForm()
	    {
	    	var hideInput2 = document.createElement("input");
			hideInput2.type = "hidden";
			hideInput2.name = "photoURL";
			var dd="";
			
		   for (var i = 0; i < imgSrc.length; i++) {
				var temp = window.btoa(imgSrc[i]);
                // var temp = "xx";
				if (i != imgSrc.length - 1) {
					if (i == 0) {
						dd = temp+",";
					} else {
						dd = dd +temp+ "," 
					}
				} else {
					dd = dd + temp
				}
			}
			hideInput2.value = dd; //get the value
			document.addForm.appendChild(hideInput2);

			document.addForm.submit();

		}
	</script>
</body>
</html>