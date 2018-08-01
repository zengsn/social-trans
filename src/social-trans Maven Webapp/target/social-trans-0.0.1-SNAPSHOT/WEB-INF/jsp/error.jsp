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
  .span{
	color:#990000;
	display:inline-block;
	width:300px;
	margin:20px 0 0 10px;
  }
  .w{
	display:block;
	margin:3% 0 2% 2%;
	font-size:2em;
  }
  </style>
  </head>
	<body>
<img src="<%=path%>/img/2.png">

<div>
<span class="w">Oops!!</span>
<a href="<%=path%>/user/register">注册</a>
<a href="<%=path%>/user/login">登录</a>
<a href="<%=path%>/index.jsp">返回主页</a><br>
<span class="span">${error}</span>
</div>
		<br>
		
</body>
</html>
