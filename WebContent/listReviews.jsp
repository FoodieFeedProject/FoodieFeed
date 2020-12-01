<!-- this code is used on foodie_feed.jsp, tagPage.jsp, profile.jsp, otherProfile.jsp -->
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%int counter = 0; %>
      
  <c:forEach items="${reviews}" var="review">
	<div class="squaretop">
      <div style="margin-left: 20px">
        <br>
        <font size = 5><c:out value="${review.getNameOfUser()}" />
        </font>
        <br>
        <a href="UserController?action=otherProfile&&otherUsername=${review.getUsername()}">@ <c:out value="${review.getUsername()}" /></a>
      </div>
    </div>
    <div class="squaremid">
      <div style="margin-left: 20px" >
        <br>
        
        <div>
       <input id="listphotoURL<%=counter%>" value="${review.getPhotoURL()}" type="hidden" />		 
		<i id="display<%=counter%>"></i>
		
		<script type="text/javascript">
		
			var arrp=new Array();
			arrp=$("#listphotoURL<%=counter%>").val().split(',');
			
			var ss="";			
			var itemx=window.atob(arrp[0]);
			ss=ss+'<img src="'+itemx+'" class="nextimg" style="width:264px;height:224px"/>'
			
			if(ss!=""){
				$("#display<%=counter%>").html(ss);				
		 }	
		
		</script>
		</div>
				
        <font size=4><c:out value="${review.getTitle()}" />
        </font>
        <br><br>
        Overall Rating: <c:out value="${review.getOverallRating()}" />&nbsp;/&nbsp;5
        <br><br>
        My Order Total:$<c:out value="${review.getTotalAmt()}" />
        <br><br>
        <c:out value="${review.getDescription()}" />
        <br><br>
          <a href="ReviewController?action=displayFull&&reviewID=${review.getReviewID()}" class="button"><button>See More</button></a>
      	  <br><br>
      	  <c:forEach items="${review.getTags()}" var="tag">
          <a href="TagController?action=visitTagPage&&tagname=${tag.getTagName()}&&numPosts=${tag.getNumPosts()}">#<c:out value="${tag.getTagName()}" /></a>         
        
        </c:forEach>
        </div>
          
     	<br>       
      </div>
      <div class="squarebot">
        <div style="margin-left: 20px">
          <img src=https://upload.wikimedia.org/wikipedia/commons/thumb/f/f1/Heart_coraz%C3%B3n.svg/1200px-Heart_coraz%C3%B3n.svg.png width="30">&nbsp;&nbsp;<c:out value="${review.getNumLikes()}" />&nbsp;&nbsp;&nbsp;
          <img src=https://upload.wikimedia.org/wikipedia/commons/1/11/Blue-Speech-Bubble.png width="30">&nbsp;&nbsp;<c:out value="${review.getComments().size()}" />
        </div>
      </div>
    <br><br>
    <%counter++;%>
  </c:forEach>