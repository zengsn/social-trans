<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>登录</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	
	<meta name="viewport" content="width=device-width,initial-scale=1.0">
	<link rel="stylesheet" href="<%=path %>/css/login.css"  type="text/css">

  </head>
  <body>
    <div class="main">
		<div class="login">
			<p>登录</p>
			<form action="user/login" method="post">
				<input type="text" placeholder="账号" name="account"><br/>
				<input type="password" placeholder="密码" name="password"><br/>
				<input type="submit" value="登录" class="sub">
			</form>
		</div>
		<p class="gotosignup">Don't have an account? <a href="user/register">注册</a></p>
	</div>
  </body>
</html>
