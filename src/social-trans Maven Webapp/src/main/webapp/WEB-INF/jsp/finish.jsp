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
    <style>
  
.errormsg {
	margin-left: 130px;
	margin-top: 35px;
	width: 400px;
	height: 50px;
	text-align: center;
	font-size: 40px;
	font-weight: 700;
	text-shadow: 0px -1px 2px #408AC7;
}

        table{
            margin-top: 30px;
            background-color: #72A4F7;
        }
        thead{

        }
    </style>
</head>
<body>
    <table align="center" width="850px">
        <thead>
            <tr>
                <th>任务名</th>
                <th>描述</th>
                <th>发布时间</th>
                <th>提交时间</th>
                <th>悬赏</th>
                <th>状态</th>
                <th>查看文件</th>
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
