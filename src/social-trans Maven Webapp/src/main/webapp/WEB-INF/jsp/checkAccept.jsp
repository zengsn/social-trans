<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>任务</title>
    
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
    <body>
	<table align="center" width="900px">
		<thead>
			<tr>
				<th>提交者</th>
				<th>文件</th>
				
			</tr>
		</thead>
		
		<tboay>

		<c:forEach items="${acList}" var="item">
			<tr align="center">
				<td><c:out value="${item.accepter}" /></td>
					<td align="center">
						<form method="post" action=""
							enctype="multipart/form-data"> <input type="hidden"
							name="taskId" value="${item.submitFileId}" /> <input type="submit"
							value="查看">
						</form>
						</td>
						<td>
						<form method="post" action="<%=path %>/task/goods"
							enctype="multipart/form-data"> <input type="hidden"
							name="acceptId" value="${item.acceptId}" /> <input type="submit"
							value="赞">${item.goods}人觉得赞
						</form>
					</td>
			</tr>
		</c:forEach>
		 </tboay>
	</table>
</body>
  </body>
</html>
