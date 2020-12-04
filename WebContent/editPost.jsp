<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="com.mie.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<head>
<title>Foodie Feed - Edit Post</title>
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
		String name = (String) session.getAttribute("name");
	
	%>

	<div class="container-fluid text-center">
	
	    <input id="reviewID" value="${review.getReviewID()}" type="hidden" /> 
		<form action="ReviewController" name="addForm" method="post" >
		<div class="row content">
		   <div class="col-sm-3 sidenav">
		   		<h1>
					Edit Post
				</h1>
		     
					Image Upload
				
				 <input id="listphotoURL" value="${review.getPhotoURL()}" type="hidden" /> 
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
					name="title" value="${review.getTitle()}" placeholder="please input title" />
				</label> <label> <span>description :</span> <textarea id="text"
						name="description"  placeholder="please input text">${review.getDescription()}</textarea></label>
				<!--</label> <label> <span>Add Tag :</span> 
				    
					<c:forEach items="${review.getTags()}" var="mytag" varStatus="myTagsStatus">
					   <input class="mytag" type="hidden"
					    value="${mytag.getTagName()}" />
					</c:forEach>
					 <input id="tag" type="text"
					name="tags" value="" placeholder="please input tag,Use# to separate" />
				</label>  -->
				<label style="text-align: left;font-weight:bold;">My Rating</label>
	
				<label> <span>Overall :</span>
				    <input id="overallRating" name="overallRating" value="${review.getOverallRating()}" type="hidden" /> 
					<div class="score_star">
					 <c:forEach var="i" begin="1" end="5" step="1">
					     <c:choose>
							<c:when test="${i<=review.getOverallRating() }">
								<i class="on">★</i>
							</c:when>
							<c:otherwise>
								<i>★</i>
							</c:otherwise>
							</c:choose>
					 </c:forEach>                 
					</div>
				</label>
				<label> <span>Food :</span>
				    <input id="foodRating" name="foodRating"  value="${review.getFoodRating()}" type="hidden" /> 
					<div class="score_star">
						<c:forEach var="i" begin="1" end="5" step="1">
					     <c:choose>
							<c:when test="${i<=review.getFoodRating() }">
								<i class="on">★</i>
							</c:when>
							<c:otherwise>
								<i>★</i>
							</c:otherwise>
							</c:choose>
					 </c:forEach>             
					</div>
				</label>
				 <label> <span>Environment :</span>
				    <input id="environmentRating" name="environmentRating"  value="${review.getEnvironmentRating()}" type="hidden" /> 
					<div class="score_star">
						<c:forEach var="i" begin="1" end="5" step="1">
					     <c:choose>
							<c:when test="${i<=review.getEnvironmentRating() }">
								<i class="on">★</i>
							</c:when>
							<c:otherwise>
								<i>★</i>
							</c:otherwise>
							</c:choose>
					     </c:forEach>             
					</div>
				</label>
				 <label> <span>Service :</span>
				     <input id="serviceRating" name="serviceRating"  value="${review.getServiceRating()}" type="hidden" /> 
					<div class="score_star">
						<c:forEach var="i" begin="1" end="5" step="1">
					     <c:choose>
							<c:when test="${i<=review.getServiceRating() }">
								<i class="on">★</i>
							</c:when>
							<c:otherwise>
								<i>★</i>
							</c:otherwise>
							</c:choose>
					     </c:forEach>             
					</div>
				</label>
				<br>
				
				<input id="dineIn" name="dineIn"  value="${review.getDineIn()}" type="hidden" /> 
				<c:forEach  var="i" begin="1" end="1" step="1">
					<c:choose>
							<c:when test="${1==review.getDineIn()}">
									<input name="choose2" type="checkbox" checked="checked" /><span>Dine-in</span>
                                  <input name="choose1" type="checkbox" /><span>Take out</span>
							</c:when>
							<c:otherwise>
								<input name="choose2" type="checkbox" /><span>Dine-in</span>
                               	<input name="choose1" type="checkbox" checked="checked"  /><span>Take out</span>
							</c:otherwise>
					</c:choose>
				</c:forEach>   
				        
			
				
				<!--<label style="text-align: left;font-weight:bold;">My Order</label>
				<table id="mytable">
				   <thead>
                    <tr><th>item</th><th>price</th></tr>
                   </thead>
                   <tbody>
                   	<c:forEach items="${review.getMyOrder()}" var="myOrder" varStatus="myOrderStatus">
						<tr>
							<td><input class="item1" name="item${myOrderStatus.index+1}" value="${myOrder.getItem()}" type="text"/></td><td><input class="price1" value="${myOrder.getPrice()}" name="price${myOrderStatus.index+1}" type="text"/></td>
						</tr>
					</c:forEach>
                    <tr><td><input class="item1" name="item1" type="text"/></td><td><input class="price1" name="price1" type="text"/></td></tr>
                   <tr><td><input class="item1" name="item2" type="text"/></td><td><input class="price1" name="price2" type="text"/></td></tr>
                     <tr><td><input class="item1" name="item3" type="text"/></td><td><input class="price1" name="price3" type="text"/></td></tr>
                   </tbody>
				</table>
				<label style="text-align: left;" id="totalvalue">Add Total: </label> -->
				
				<label style="text-align: right;"> <span>&nbsp;</span> <input type="button" id="postsubmit"
					class="button" onclick="SendForm()" value="Save Changes" />
					
				</label>
			</div>		
			</div>	
			</div>	
		</form>
		
	</div>
	
	
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
	        
	       
	        
	        $("#addRow").click(function(){
	        	var rows='<tr><td><input name="item1" type="text"/></td><td><input name="price1" type="text"/></td></tr>';
				$("#mytable").append(rows)
				$("input[name='price1']").bind("input propertychange",function(event){
			        	var e=$("input[name='price1']");
			        	var c=0;
			        	for(var i=0;i<e.length;i++){
			        		c=c+parseFloat(e[i].value);
			        	}
			            //alert(e)
			            //console.log(c);
			            $("#totalvalue").html(c)
			     });
	        });
	        
	        //watch the value change
	        $("input[class='price1']").bind("input propertychange",function(event){
	        	var e=$("input[class='price1']");
	        	var c=0;
	        	for(var i=0;i<e.length;i++){
	        		if(e[i].value==""){
	        			c=c+0
	        		}else{
	        		c=c+parseFloat(e[i].value);
	        		}
	        	}
	            //alert(e)
	            //console.log(c);
	            $("#totalvalue").html("Add Total: $"+c)
	        });
	        
	        var mytag=$("input[class='mytag']");
	        var tagvalue="";
	        for(var i=0;i<mytag.length;i++){
	        	temptag=mytag[i].value;
	        	
	        	if (i != mytag.length - 1) {
					if (i == 0) {
						tagvalue = temptag+"#";
					} else {
						tagvalue = tagvalue +temptag+ "#" 
					}
				} else {
					tagvalue = tagvalue + temptag
				}
        	}
	        $("#tag").val(tagvalue)
	        
	        
	        var arrp=new Array();
			arrp=$("#listphotoURL").val().split(',');
			for(var i=0;i<arrp.length;i++)
			{
				var itemx=window.atob(arrp[i]);
				imgSrc.push(itemx);
				
			}
			addNewContent(".content-img-list")
	        
	      
	        

	      });
	 
	    function SendForm()
	    {
	    	
        	
        	 var hideInput1 = document.createElement("input");
			hideInput1.type = "hidden";
			hideInput1.name = "reviewID";
			hideInput1.value = $("#reviewID").val(); //get the value
			document.addForm.appendChild(hideInput1);  
        	
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