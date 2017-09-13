<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
  error
  </head>
	<body>
<a href="<%=path%>/user/register">注册</a><br>
<a href="<%=path%>/user/login">登录</a>
<a href="<%=path%>/index.jsp">返回主页</a>
		<br>
		${error}
</body>
</html>
