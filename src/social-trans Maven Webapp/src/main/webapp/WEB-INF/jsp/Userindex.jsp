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
    <title>用户主页</title>
    <link rel="stylesheet" href="<%=path %>/css/showMyTask.css" type="text/css">
</head>
<body>
    <header>
        <nav><a class= "logo" href="">用户主页</a></nav>
        <ul>
      		<li><a href="<%=path%>/index.jsp">Home</a></li>
           <li><a href="<%=basePath%>/message/showMessage">消息(${sessionScope.messageNum })</a></li>
            <li><a href="<%=basePath%>/user/login">注销</a></li>
        </ul>
    </header>
    <div class="context">
        <div class="left">
            <div class="search">
                <input type="text" class="searchText">
                <span ><button class="searchIcon">搜索</button></span>
            </div>
            <div class="sysFeature">
                <p class="title">任务</p>
                <ul>
               		 <li><a href="<%=basePath %>/task/pushTask" target="showTask">用户推送任务</a></li>
                    <li><a href="<%=basePath %>/task/unfinish" target="showTask">未完成任务</a></li>
                    <li><a href="<%=basePath %>/task/finish" target="showTask">已提交任务</a></li>
                    <li><a href="<%=basePath %>/task/release" target="showTask">发布任务</a></li>
                     
                </ul>
            </div>
            <div class="norFeature"><a href="<%=basePath %>/user/updateUser" target="showTask">用户资料</a></div>
        </div>
        <div class="right">
            <iframe src="" frameborder="0" name="showTask" width="100%" height="100%"></iframe>
        </div>
    </div>
</body>
</html>
