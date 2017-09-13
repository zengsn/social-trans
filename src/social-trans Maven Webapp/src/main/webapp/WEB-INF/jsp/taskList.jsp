<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<style>
	body{
		background-color:;
		padding:0;
		margin:0;
	}
	
	li{
		display:block;
		float:left;
		margin-left:20px
	}
	.input{
		font-family:微软雅黑;
		width:80px;
		height:30px;
		background-color:#ffffff;
		border:none;
	}
	table td{
		text-align:center;
		border:1px solid #0066ff;
	}

</style>
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'taskList.jsp' starting page</title>
    
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
  
  <table width="706" border="0" height="50px" align="center" cellspacing="1" style="background-color:#a0c6e5;color:white;">
  <tr >
     <td colspan="6" align="center" >任务列表</td>
   </tr>
   </table>
   
	<table width="706" border-collapse:collapse="" align="center" cellspacing="1">
   <tr align="center" style="height:50px">
     <td width="100" align="center">任务名</td>
     <td width="100" align="center">描述</td>
     <td width="180" align="center">发布时间</td>
     <td width="180" align="center">提交时间</td>
     <td width="65" align="center">悬赏</td>
     <td width="83" align="center">领取人数</td>
     <td width="83" align="center">查看提交</td>
   </tr>
   <c:forEach items="${reList}" var="item">
	<tr>	 
    <td><c:out value="${item.taskName}" /></td>
    <td><c:out value="${item.description}" /></td>
    <td><c:out value="${item.startTime}" /></td>
    <td><c:out value="${item.finishTime}" /></td>
    <td><c:out value="${item.taskMoney}" /></td>
    <td><c:out value="${item.receiveNum}" /></td>
    <td><form method="post" action="<%=basePath %>/task/checkAccept"
							enctype="multipart/form-data">
							<input type="hidden" name="taskId" value="${item.taskId}" /> <input
								type="submit" value="查看提交">
						</form></td>
    <td align="right"> <form  method="post" action="task/acceptTask"> 
      <input type="hidden" name="taskId" value="${item.taskId}" />
      <input type="submit"  value="领取" ></form></td>
</tr>
</c:forEach>
 </table>
 
 <br>
 <a href="<%=path%>/index.jsp">返回主页</a><br>
  </body>
</html>
