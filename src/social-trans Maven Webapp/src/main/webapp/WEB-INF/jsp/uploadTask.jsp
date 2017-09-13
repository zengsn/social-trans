<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib  uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>发布任务</title>
	<link rel="stylesheet" href="<%=path %>/css/upload.css">
</head>
<body>
	<div class="form">
		<div id="tittle">
			<label>上传任务</label>
		</div>
		<form:form id="upload" action="uploadTask" method="post"  enctype="multipart/form-data" commandName="receiveTask">
		
		<hr>
    	<div>
    		<label>任务名：</label>
    			<form:input path="taskName"/>
    	</div>
    	<div>
    		<label>类型：</label>
    			<input type="radio" name="description"  value="历史"  /> <span>历史</span>
				<input type="radio" name="description"  value="政治" /><span> 政治</span>
				<input type="radio" name="description"  value="地理" /> <span>地理</span>
				<input type="radio" name="description"  value="人文" /> <span>人文</span>
    	</div>	
    	<div>
    		<label>悬赏：</label>
    			<form:input path="taskMoney"/>
    	</div>			
    	<div>
    		<label>结束时间：</label>
    			<form:input path="finishTime"/>
    	</div>	
    	<div>
    		<label>任务人数：</label>
    			<form:input path="receiveNum"/>
    	</div>	
    	<div>
    		<label>选择文件：</label>
    			<input type="file"  name="file">
    	</div>
    	<div class="button">
    		<input type="submit" value="提交"></input>
    		
    		<a href="<%=path%>/index.jsp">返回主页</a>
    	</div>	
	
    	 </form:form>
	</div>
    
  </body>
</html>