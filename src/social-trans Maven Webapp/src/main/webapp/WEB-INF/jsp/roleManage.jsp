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
    
    <title>角色管理</title>
    
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
			<tr align="center">
				<td>角色名</td>
				<td>描述</td>
				<td>等级</td>
				<td>..</td>
			</tr>
		</thead>
		<tbody>
		
			<c:forEach items="${roleList}" var="item">
				<tr align="center">
					<td><c:out value="${item.rolename}" /></td>
					<td><c:out value="${item.description}" /></td>
					<td><c:out value="${item.rolelevel}" /></td>
					<td align="right"><form method="post" action="<%=basePath %>/admin/deleteRole"
							enctype="multipart/form-data">
							<input type="hidden" name="roleId" value="${item.roleId}" /> <input
								type="submit" value="删除">
						</form></td>
						
						</tr>
			</c:forEach>
		</tbody>
	</table>
	
  </body>
</html>
