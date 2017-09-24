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
<div class="list">
		<c:forEach items="${acList}" var="item" varStatus="re">
			<div class="section">
				<img src="<%=path%>/img/bird.jpg" alt="">
				<ul class="header">
					<li class="taskName"><a href="#"><c:out value="${item.accepter}" /></a></li>
					<li class="number" id="like"> <a href="#"><img src="/social-trans/img/like.png"></a>${item.goods}	
					<li class="number" id="like"> <a href="<%=path %>/task/adoptTrans?acceptId=${item.acceptId}">采纳</a>		
					</li>
				</ul>
				<div class="detail">
					<span class="context"> ${item.submitText } </span> ...<a href="#">[更多]</a>
				</div>
			</div>
			
			<hr>
		</c:forEach>
	</div>
</body>
</html>
