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
               <!-- <th>查看文件</th> -->
            </tr>
        </thead>
        <tbody>
            <tr align="center">
                <c:forEach items="${finishList}" var="item">
				<tr align="center">	 
   				 <td><c:out value="${item.taskName}" /></td>
   	 			<td><c:out value="${item.description}" /></td>
  				  <td><c:out value="${item.startTime}" /></td>
   				 <td><c:out value="${item.finishTime}" /></td>
  			  	<td><c:out value="${item.taskMoney}" /></td>
  				 <td><c:out value="已提交" /></td>
				</tr>
		</c:forEach>
            </tr>
        </tbody>
    </table>
    <div class="errormsg">${fmsg}</div>
</body>
</html>
