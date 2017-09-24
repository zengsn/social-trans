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
<title>任务列表</title>
<script type="text/javascript" src="<%=path%>/js/jquery-3.2.1.min.js"></script>
<script type="text/javascript" src="<%=path%>/js/taskDetail.js"></script>
<link rel="stylesheet" href="<%=path%>/css/taskDetail.css">
<link rel="stylesheet" href="<%=path %>/css/showMyTask.css" type="text/css">
<style>
#a{
	display:inline-block;
	margin-left:0 auto;
	margin-top:40px;
}

</style>
</head>
<body>
	 <header>
        <nav><a class= "logo" href="<%=basePath %>user/userData">用户主页</a></nav>
        <ul>
      		<li><a href="<%=path%>/index.jsp">Home</a></li>
            <li><a href="<%=basePath%>/message/showMessage">消息(${sessionScope.messageNum })</a></li>
            <li><a href="<%=basePath%>/user/login">注销</a></li>
            
        </ul>
    </header>
	<div>
		<c:forEach items="${messageList}" var="item" varStatus="re">
			<div class="section">
				<img src="<%=path%>/img/bird.jpg" width="100px" height="100px" alt="">
				<div class="detail">
					  <a id="a" href="<%=basePath %>/task/taskDetail?taskId=${item.taskId}"> ${item.message }[点击查看详情]</a>
				</div>
			</div>
			
			<hr>
		</c:forEach>
	</div>
</body>
</html>