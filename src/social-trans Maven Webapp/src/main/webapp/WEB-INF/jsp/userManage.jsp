<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>用户管理</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->

  </head>
  
  <body>
    <table align="center" width="900px">
		<thead>
			<tr>
				<th>账号</th>
				<th>用户名</th>
				<th>邮箱</th>
				<th>手机号</th>
				<th>角色</th>
				<th>兴趣</th>
				<th>..</th>
			</tr>
		</thead>
		<tbody>
		
			<c:forEach items="${UserList}" var="item">
				<tr align="center">
					<td><c:out value="${item.account}" /></td>
					<td><c:out value="${item.username}" /></td>
					<td><c:out value="${item.email}" /></td>
					<td><c:out value="${item.phoneNumber}" /></td>
					<td><c:out value="${item.role}" /></td>
					<td><c:out value="${item.hobby}" /></td>
					<td align="right"><form method="post" action="<%=basePath %>/user/admin/deleteUser"
							enctype="multipart/form-data">
							<input type="hidden" name="userId" value="${item.userId}" /> <input
								type="submit" value="删除">
						</form></td>
						
						</tr>
			</c:forEach>
		</tbody>
	</table>
  </body>
</html>
