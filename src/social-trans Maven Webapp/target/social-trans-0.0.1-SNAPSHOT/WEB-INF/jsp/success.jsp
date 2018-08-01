<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
	<style>
		img{
	max-width:300px;
	position:absolute;
	float:left;
	left:20px;
	top:20px;
  }
   div{
	margin-top:17;
	margin-left:30%;
  }
	a{
		display:inline-block;
		margin:20px;
		text-decoration:none;
		font-size:1.5em;
		color:black;
		font-weight:bold;
		text-shadow:1px 1px 25px #0A0A0A;
		}
		span{
	
	display:inline-block;
	width:300px;
	margin:20px 0 0 15px;
  }
	</style>
  </head>
	<body>
	<img src="<%=path%>/img/1.png">
	
	<div>
	<a href="<%=path%>/task/getReceiveTaskList">继续领取任务</a><br>
	<a href="<%=path%>/user/userData">用户主页</a><br>
			<a href="<%=path%>/index.jsp">返回主页</a><br>
<span>${success}</span>
	</div>
	
</body>
</html>


