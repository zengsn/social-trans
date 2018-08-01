<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>众包翻译服务平台</title>
	<meta name="viewport" content="width=device-width,initial-scale=1.0">
	<link rel="stylesheet" href="<%=path %>/css/signup.css" type="text/css">
	<script>
		function check(){
			var user = document.getElementById("user").value;
			var pw = document.getElementById("pw").value;
			var cpw = document.getElementById("cpw").value;
			console.log(user,pw,cpw);
			if(user == "" || pw == ""){
				alert("请输入用户名和密码！");
			}
			else if(cpw == ""){
				alert("请再次输入密码！");
			}
			else if(pw != cpw){
				alert("两次密码不一致！");
			}
		}
	</script>
</head>
<body>
	<div class="main">
		<div class="signup">
			<p>注册</p>
			<form action="" method="post">
				<input type="text" placeholder="输入您的账号" id="user" name="account"><br/>
				<input type="password" placeholder="密码" id="pw" name="password"><br/>
				<input type="password" placeholder="再次输入密码" id="cpw" ><br/>
				<input type="submit" value="注册" class="sub" onclick="check()">
			</form>
		</div>
	</div>

</body>
</html>
