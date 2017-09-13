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
			<p>LOG IN</p>
			<form action="user/login" method="post">
				<input type="text" placeholder="Your account" name="account"><br/>
				<input type="password" placeholder="Your password" name="password"><br/>
				<a href="#" class="forget">Forget passwod?</a><br/>
				<input type="submit" value="submit" class="sub">
			</form>
		</div>
		<p class="gotosignup">Don't have an account? <a href="user/register">Sign up</a></p>
	</div>
  </body>
</html>
