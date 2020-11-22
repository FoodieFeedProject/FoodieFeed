<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8" import="com.mie.model.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html lang="en">
<!-- Check to see if the user is logged in. Otherwise, redirect back to the login page.-->

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
		<form action="ReviewController" name="addForm" method="post" >
		<div class="row content">
		   <div class="col-sm-3 sidenav">
		       <h1>
					Img Upload
				</h1>
				<div class="upload-content">
					<div class="content-img">
						<ul class="content-img-list"></ul>
						<div class="file">
							<i class="gcl gcladd"></i> <input type="file" name="file"
								accept="image/*" id="upload" >
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

			<div class="basic-grey">
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
				<input name="choose1" type="checkbox" /><span>Dine-in</span>
                <input name="choose2" type="checkbox" /><span>Take out</span>
				
				<br></br>
				<label style="text-align: left;font-weight:bold;">My Order</label>
				<label> <span>Item 1 :</span> <input id="item1" type="text"
					name="item1" placeholder="name of item" /></label>
				<label> <span>Price :</span> <input id="price1" type="text"
					name="price1" placeholder="price of item"/></label>
				<label> <span>Item 2 :</span> <input id="item2" type="text"
					name="item2" placeholder="(optional)" /></label>
				<label> <span>Price :</span> <input id="price2" type="text"
					name="price2" placeholder="(optional)" /></label>
				<label> <span>Item 3 :</span> <input id="item3" type="text"
					name="item3" placeholder="(optional)" /></label>
				<label> <span>Price :</span> <input id="price3" type="text"
					name="price3" placeholder="(optional)" /></label>
				
				<label style="text-align: right;"> <span>&nbsp;</span> <input type="submit" id="postsubmit"
					class="button" onclick="SendForm()" value="Post" />

			</div>
		
			</div>
		</div>
		</form>
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
	        	 if(one=="choose2"){
	        		 $("#dineIn").val(1)
	        	 }else{
	        		 $("#dineIn").val(0)
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
			        		c=c+parseInt(e[i].value);
			        	}
			            //alert(e)
			            //console.log(c);
			            $("#totalvalue").html(c)
			     });
	        });
	        
	        //watch the value change
	        $("input[name='price1']").bind("input propertychange",function(event){
	        	var e=$("input[name='price1']");
	        	var c=0;
	        	for(var i=0;i<e.length;i++){
	        		c=c+parseInt(e[i].value);
	        	}
	            //alert(e)
	            //console.log(c);
	            $("#totalvalue").html(c)
	        });
	        
	        $("#postsubmit").submit(function(){
	        	var it=$("input[name='item1']");
	        	var pr=$("input[name='price1']");
	        	var datas=[];
	        	var data={};
	        	for(var i=0;i<it.length;i++){
	        		data["item"]=it[i].value;
	        		data["price"]=pr[i].value;
	        		datas.push(data);
	        	}
	        	var jsonString = JSON.stringify(datas); 
	        	console.log(jsonString);
	        	return false;
	        	
	        	//event.preventDefault();
	        	
	        	//if(condition){
	        	   // return false;
	        	//}else{
	        	    //return false;
	        	//}
	        });
	        
	        

	      });
	 
	    function SendForm()
	    {
	    	var it=$("input[name='item1']");
        	var pr=$("input[name='price1']");
        	var datas=[];
        	var data={};
        	for(var i=0;i<it.length;i++){
        		data["item"]=it[i].value;
        		data["price"]=pr[i].value;
        		datas.push(data);
        	}
        	//my order  type:string
        	var jsonString = JSON.stringify(datas);
        	//console.log(jsonString);
        	
        	var hideInput1 = document.createElement("input");
			hideInput1.type = "hidden";
			hideInput1.name = "myOrder";
			hideInput1.value = jsonString; //get the value
			document.addForm.appendChild(hideInput1);
        	
        	//img url ,this is img arrry
        	var imgurl=JSON.stringify(imgSrc);
        	var hideInput2 = document.createElement("input");
			hideInput2.type = "hidden";
			hideInput2.name = "photoURL";
			hideInput2.value = imgurl; //get the value
			document.addForm.appendChild(hideInput2);
			
        	//console.log(imgSrc.length)
        	
        	document.addForm.submit();
        	
        	
        	
	    }

	   
	 
		
		
		
	</script>
</body>
</html>