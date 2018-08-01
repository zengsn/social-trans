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
</head>
<body>
	<div>
		<c:forEach items="${acList}" var="item" varStatus="re">
			<div class="section">
				<img src="<%=path%>/img/bird.jpg" alt="">
				<ul class="header">
					<li class="taskName"><a href="<%=basePath %>/task/taskDetail?taskId=${item.taskId}">${item.taskName}</a></li>
					<li class="reward"><c:out value="${item.taskMoney}" /></li>
					<li class="number"><a href="#"><span><c:out value="${item.receiveNum}" /></a><span>个人领取</span></li>
				</ul>
				<div class="para">
					<span> ${file[re.count-1].lists } </span> ...<a href="#">[更多]</a>
				</div>
				<ul class="footer">
					<li class="tab"><span>标签：</span><a href="#"><c:out
								value="${item.description}" /></a></li>
					<li class="time"><c:out value="${item.finishTime}" /></li>
				</ul>
			</div>
			<hr>
		</c:forEach>
	</div>
</body>
</html>