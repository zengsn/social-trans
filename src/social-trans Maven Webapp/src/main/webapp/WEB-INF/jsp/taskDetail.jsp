<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>任务列表</title>
<script type="text/javascript" src="<%=path%>/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/taskDetail.js"></script>
<link rel="stylesheet" href="<%=path%>/css/taskDetail.css">
<link rel="stylesheet" href="<%=path %>/css/showMyTask.css" type="text/css">
	<script>
function check(){
		var hd=document.getElementById('zf').value;
		var cot=hd.length;
		if(cot>140){
			document.getElementById('le').innerHTML="评论限制140字内";
			return false;
		}
		else{
			document.getElementById('le').innerHTML="你当前输入了"+cot+"字";
			return true;
		}
	}

</script>
<style>
	input[type="submit"]{
		float:right;
		padding:5px 10px;
		border-radius:2em;
		background-color:#808080;
		color:white;
		border:none;
	}
	#like a{
		display:inline-block;
	}
	.list{
	padding-top:7%;
	}
</style>


</head>
<body>
 <header>
        <nav><a class= "logo" href="<%=basePath %>user/userData">用户主页</a></nav>
        <ul>
      		<li><a href="<%=path%>/index.jsp">Home</a></li>
            <li><a href="<%=basePath%>/message/showMessage">消息(${sessionScope.messageNum })</a></li>
            <li><a href="<%=basePath%>/user/login">注销</a></li>
        </ul>
    </header>
	<div>
		
			<div class="section">
				<img src="<%=path%>/img/bird.jpg" alt="">
				<ul class="header">
					<li class="taskName"><span></span><a href=""><c:out value="${reTask.taskName}" /></a></li>
					<li class="reward"><span><c:out value="${reTask.taskMoney}" /></span></li>
					<li class="number"><span><c:out
								value="${reTask.receiveNum}" />个人领取</span></li>
				</ul>
				<div class="detail">
					<span class="para">${reTask.taskText }</span></span> ...<a href="#">[更多]</a>
				</div>
				<ul class="footer">
					<li class="tab"><span>标签：</span>
					<c:out value="${reTask.description}" /></li>
					<li class="time"><span><c:out
								value="${reTask.finishTime}" /></span></li>
				</ul>
			</div>
			<form  method="post" action="<%=path %>/task/acceptTask"> 
      		<input type="hidden" name="taskId" value="${reTask.taskId}" />
     		 <input type="submit"  value="领取" ></form>${error }
     		 

	</div>
			
	<div class="list">
		${gradeError}
		<c:forEach items="${acList}" var="item" varStatus="re">
			<div class="section">
				<img src="<%=path%>/img/bird.jpg" alt="">
				<ul class="header">
					<li class="taskName"><a href="#"><c:out value="${item.accepter}" /></a></li>
					<li><c:choose>
						<c:when test="${item.score==0}">
						</c:when>
						<c:otherwise>专家评分：${item.score}</c:otherwise>
						</c:choose></li>
					<li class="number" id="like"> <a href="<%=path %>/task/goods?acceptId=${item.acceptId}"><img src="/social-trans/img/like.png"></a>${item.goods}	
						
					</li>
				</ul>
				<div class="detail">
					<span class="context"> ${item.submitText } </span> ...<a href="#">[更多]</a>
				</div>
			</div>
			<form  method="post" action="<%=path %>/task/gradeTask" onsubmit="return checkNum();"> 
      			<input type="hidden" name="acceptId" value="${item.acceptId}"/>
      			<input type="number" name="score" id="score"/>
     		 <input type="submit"  value="评分" ></form>
			<hr>
			<!--  -->
			<form  method="post" action="<%=path %>/task/comment" > 
				<div class="detail">
				<!-- 在下面的文本框加一个js判断是否输入了内容，并且限制数字为140内，没有内容提示一下，不跳转 -->
					<textarea cols="40" rows="5" id="zf" onkeyup="check()" name="comment"  placeholder="输入评论内容"></textarea><br>
					<span id="le"></span>
				</div>
				<input type="hidden" name="acceptId" value="${item.acceptId}"/>
			
				 <input type="submit"  value="评论" >
			</form>
			<c:forEach items="${commentMap[item.acceptId]}" var="map">
				<ul class="header">
					<li ><span></span><c:out value="${map.num}" />.</li>
					<li ><span><c:out value="${map.username}" /></span></li><br>
					<li ><span class="context"> ${map.comment } </span></li>
				</ul>
				<div class="detail">
					
				</div>
			</c:forEach>
			<hr>
		</c:forEach>
	</div>
</body>
</html>
