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
	<link rel="stylesheet" href="<%=path %>/css/showMyTask.css" type="text/css">
	
	<script>
function a(){
		var hd=document.getElementById('zf').value;
		var cot=hd.length;
		if(cot>140){
			document.getElementById('le').innerHTML="太多了！我翻译不来。可选择文件上传翻译";
			return false;
		}
		else{
			document.getElementById('le').innerHTML="你当前输入了"+cot+"字符";
			return true;
		}
	}

</script>
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
	<div class="form">
		<div id="tittle">
			<label>上传任务</label>
		</div>
		<form id="upload" action="uploadTask" method="post"  enctype="multipart/form-data"  onSubmit="return a()" >
		
		<hr>
    	<div>
    		<label>任务名：</label>
    			<input type="text" name="taskName">
    	</div>
    	<div>
    		<label>类型：</label>
    			<input type="radio" name="description"  value="历史"  /> <span>历史</span>
				<input type="radio" name="description"  value="政治" /><span> 政治</span>
				<input type="radio" name="description"  value="地理" /> <span>地理</span>
				<input type="radio" name="description"  value="人文" /> <span>人文</span>
    	</div>	
    	<div>
    		<label>个人悬赏：</label>
    			<input type="number" name="taskMoney">
    	</div>			
    	<div>
    		<label>结束时间：</label>
    			<input type="date" name="finishTime"/>
    	</div>	
    	<div>
    		<label>任务人数：</label>
    			<input type="number" name="totalNum">
    	</div>	
	    	<c:if test="${roleLevel >1 }">
		    	<div>
		    		<label>选择文件：</label>
		    			<input type="file"  name="file">${upLoadError}
		    	</div>
	    	</c:if>
    	<div>
    		<label></label>
    		<textarea cols="30" rows="10" id="zf" onkeyup="a()" name="taskText"  placeholder="输入翻译内容"></textarea><br>
			<span id="le"></span>
    	</div>
    	<div class="button">
    		<input type="submit" value="提交"></input>
    		
    		<a href="<%=path%>/index.jsp">返回主页</a>
    	</div>	
	
    	 </form>
	</div>
    
  </body>
</html>