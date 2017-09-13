<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
<base href="<%=basePath%>">

<title>任务管理</title>

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
				<td width="100" align="center">任务名</td>
				<td width="100" align="center">描述</td>
				<td width="180" align="center">发布时间</td>
				<td width="180" align="center">提交时间</td>
				<td width="65" align="center">悬赏</td>
				<td width="83" align="center">领取人数</td>
				<td width="83" align="center">操作</td>
			</tr>
		</thead>
		<tbody>

			<c:forEach items="${taskList}" var="item">
				<tr align="center">
					<td><c:out value="${item.taskName}" /></td>
					<td><c:out value="${item.description}" /></td>
					<td><c:out value="${item.startTime}" /></td>
					<td><c:out value="${item.finishTime}" /></td>
					<td><c:out value="${item.taskMoney}" /></td>
					<td><c:out value="${item.receiveNum}" /></td>
					<td align="right"><form method="post"
							action="<%=basePath%>/admin/deleteTask"
							enctype="multipart/form-data">
							<input type="hidden" name="taskId" value="${item.taskId}" /> <input
								type="submit" value="删除">
						</form></td>

				</tr>
			</c:forEach>
		</tbody>
	</table>
</body>
</html>
