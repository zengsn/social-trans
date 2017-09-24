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
<title>Title</title>
<link rel="stylesheet" href="<%=path %>/css/table.css">
</head>
<body>
	<table>
		<thead>
			<tr>
				<th>任务名</th>
				<th>描述</th>
				<th>提交时间</th>
				<th>结束时间</th>
				<th>悬赏</th>
				<th>状态</th>
				<th>查看</th>
				<th>撤销</th>
			</tr>
		</thead>
		<tbody>
		
			<c:forEach items="${reList}" var="item">
				<tr>
					<td><c:out value="${item.taskName}" /></td>
					<td><c:out value="${item.description}" /></td>
					<td><c:out value="${item.startTime}" /></td>
					<td><c:out value="${item.finishTime}" /></td>
					<td><c:out value="${item.taskMoney}" /></td>
					<td><c:out value="${item.state}" /></td>
					<td><form method="post" action="<%=basePath %>/task/checkAccept"
							enctype="multipart/form-data">
							<input type="hidden" name="taskId" value="${item.taskId}" /> <input
								type="submit" value="查看">
						</form></td>
					<td>
					
						<form method="post" action="<%=basePath %>/task/deleteTask"
							enctype="multipart/form-data">
							<input type="hidden" name="taskId" value="${item.taskId}" /> <input
								type="submit" value="撤销任务">
						</form>
					</td>
					<td><a href="<%=basePath %>/task/adoptBigTrans?taskId=${item.taskId}">采纳</a></td>
				</tr>
				
			</c:forEach>
		</tbody>
	</table>
	<div class="errormsg">${remsg}</div>
</body>
</html>