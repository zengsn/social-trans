<%@ page language="java"
	import="java.util.*,com.crowd.bean.*,com.crowd.service.*"
	pageEncoding="UTF-8"%>
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
<title>首页</title>
<style>
* {
	padding: 0;
	margin: 0;
}

body {
	background: url(img/userbg.jpg) center center fixed;
	opacity: .7;
}

.logo {
	margin-left: 130px;
	margin-top: 35px;
	width: 400px;
	height: 50px;
	text-align: center;
	font-size: 40px;
	font-weight: 700;
	text-shadow: 0px -1px 2px #408AC7;
}

ul {
	list-style: none;
}

li {
	display: inline-block;
	text-align: center;
}

ul li a {
	text-decoration: none;
}

.signin-up {
	position: absolute;
	top: 55px;
	right: 100px;
}

.signin-up ul li {
	margin-left: 20px;
	width: 100px;
	height: 30px;
	line-height: 30px;
	border-radius: 10px;
	background: rgba(255, 255, 255, .6);
}

.signin-up ul li a {
	color: #434343;
}

.user {
	margin: 70px 0 0 283px;
	width: 400px;
	height: 80px;
}

.user h3 {
	color: #7D7979;
}

.task {
	margin-left: 300px;
	width: 500px;
	height: 80px;
}

.task ul li {
	margin-left: 30px;
}

.task ul li a {
	font-size: 20px;
	font-weight: 500;
	color: #55C1F0;
}
</style>

</head>
<body onload="checkCookie()">
	<div class="logo">Social-trans</div>
	<div class="signin-up">
		<ul>
			<li><a href="user/login">登录</a></li>
			<li><a href="user/register">注册</a></li>
		</ul>
	</div>
	<div class="user">
		<h3>
			欢迎您，<span class="accountCookie"><a href="user/userData">${cookie.accountCookie.value }</a></span>
		</h3>
	</div>
	<div class="task">
		<ul>
			<li><a href="task/uploadTask">发布任务</a></li>
			<li><a href="task/getReceiveTaskList">我要接任务</a></li>
		</ul>
	</div>
	<script>
	var userName = null;
	function getCookie(accountCookie){
	var cookieName = accountCookie;
	var cookies = document.cookie.split(";");
	for (var i = 0; i < cookies.length; i++) { 
		var cookie = cookies[i];//得到某下标的cookies数组 
		if (cookie.substring(0, cookieName.length + 2).trim() == cookieName.trim() + "=") {//如果存在该cookie的话就将cookie的值拿出来 
			cookieValue = cookie.substring(cookieName.length + 2, cookie.length); 
			if(cookieValue!=null||cookieValue!=""){
				userName=cookieValue;
				console.log(userName);
				return true;
			}else{
				return false;
			}

		}
	}
	}
		function checkCookie(){
			if(!getCookie('accountCookie')){
				var ss = document.querySelector('.user');
				ss.style.visibility = "hidden";
			}
			else{
				var ss = document.querySelector('.signin-up');
				ss.style.display = "none";
			}
		
		}
	</script>
</body>
</html>
