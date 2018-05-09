<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
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
	
	 <script type='text/javascript' src='<%=path%>/js/jquery-1.8.3.min.js'></script>
	<script type='text/javascript' src='<%=path%>/js/jquery.easing.1.3.js'></script>
	<script type='text/javascript' src='<%=path%>/js/prettyphoto/jquery.prettyPhoto.js'></script>
	<script type='text/javascript' src='<%=path%>/js/jflickrfeed.js'></script>
	<script type='text/javascript' src='<%=path%>/js/jquery.liveSearch.js'></script>
	<script type='text/javascript' src='<%=path%>/js/jquery.form.js'></script>
	<script type='text/javascript' src='<%=path%>/js/jquery.validate.min.js'></script>
	<script type='text/javascript' src='<%=path%>/js/custom.js'></script>
    <link type="image/x-icon" rel="shortcut icon" href="<%=path%>/images/favicon.png"/>
    <!-- Style Sheet-->
    <link rel='stylesheet' href='<%=path%>/css/style.css'  type='text/css'/>
    <link rel='stylesheet' id='bootstrap-css-css'  href='<%=path%>/css/bootstrap5152.css?ver=1.0' type='text/css' media='all' />
    <link rel='stylesheet' id='responsive-css-css'  href='<%=path%>/css/responsive5152.css?ver=1.0' type='text/css' media='all' />
    <link rel='stylesheet' id='pretty-photo-css-css'  href='<%=path%>/js/prettyphoto/prettyPhotoaeb9.css?ver=3.1.4' type='text/css' media='all' />
    <link rel='stylesheet' id='main-css-css'  href='<%=path%>/css/main5152.css?ver=1.0' type='text/css' media='all' />
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script>
function a(){
		var hd=document.getElementById('zf').value;
		var cot=hd.length;
		if(cot>250){
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
  		 <!-- Start of Header -->
                <div class="header-wrapper">
                        <header>
                                <div class="container">


                                        <div class="logo-container">
                                                <!-- Website Logo -->
                                                <a href="#"  title="social-trans">
                                                        <img src="<%=path %>/images/logo.png" alt="social-trans">
                                                </a>
                                                <span class="tag-line">翻译与译者社交平台</span>
                                        </div>


                                        <!-- Start of Main Navigation -->
                                        <nav class="main-nav">
                                                <div class="menu-top-menu-container">
                                                        <ul id="menu-top-menu" class="clearfix">
                                                                <li ><a href="<%=basePath%>task/toIndex">主页</a></li>
                                                                <li class="current-menu-item"><a href="<%=basePath%>task/getReceiveTaskList">任务列表</a></li>
                                                                
                                                                <li><a href="#">分类</a>
                                                                        <ul class="sub-menu">
                                                                                 <li><a href="<%=basePath%>task/getTaskByDesc?desc=历史">历史</a></li>
                                                                                <li><a href="<%=basePath%>task/getTaskByDesc?desc=政治">政治</a></li>
                                                                                <li><a href="<%=basePath%>task/getTaskByDesc?desc=小说">小说</a></li>
                                                                                <li><a href="<%=basePath%>task/getTaskByDesc?desc=商业">商业</a></li>
                                                                                <li><a href="<%=basePath%>task/getTaskByDesc?desc=人文">人文</a></li>
                                                                                <li><a href="<%=basePath%>task/getTaskByDesc?desc=校园">校园</a></li>
                                                                        </ul>
                                                                </li>
                                                                <li><a href="#">More</a>
                                                                        <ul class="sub-menu">
                                                                                <li><a href="full-width.html">Full Width</a></li>
                                                                                <li><a href="elements.html">Elements</a></li>
                                                                                <li><a href="page.html">Sample Page</a></li>
                                                                        </ul>
                                                                </li>
                                                                <c:choose>
                                                                <c:when test="${empty sessionScope.username }">
	                                                                <li><a href="<%=basePath %>/user/login">登录</a></li>
	                                                                <li><a href="<%=basePath %>/user/register">注册</a></li>
                                                        		</c:when>
                                                        		<c:otherwise>
                                                        			<li><a href="<%=basePath %>/user/toIndex">${sessionScope.username }</a>
                                                                        <ul class="sub-menu">
                                                                                <li><a href="<%=basePath %>/user/toIndex">用户主页</a></li>
                                                                                <li><a href="<%=basePath %>/user/login">注销</a></li>
                                                                        </ul>
                                                                </li>
                                                        		</c:otherwise>
                                                        		</c:choose>
                                                        </ul>
                                                </div>
                                        </nav>
                                        <!-- End of Main Navigation -->

                                </div>
                        </header>
                </div>
	<!-- End of Header -->

						<div id="respond" align="center">

							<h3>发布任务</h3>
							<form id="upload" action="uploadTask" method="post"  enctype="multipart/form-data"  onSubmit="return a()">
								<div>
									<label for="author">任务名称：*</label> <input class="span4"
										type="text" name=taskName id="taskName" value="" size="22">
								</div>
								<div>
						    		<label for="email">类型：</label>
						    		<select name="description" required="true">
						    			<option  value="历史" >历史</option>
						    			<option  value="政治" >政治</option>
						    			<option  value="人文" >人文</option>
						    			<option  value="小说" >小说</option>
						    			<option  value="商业" >商业</option>
						    			<option  value="校园" >校园</option>
						    		</select>
						    	</div>	
								<div>
									<tr><label for="email">个人悬赏： *</label></tr> 
									<tr><input type="number" name="taskMoney" id="taskMoney" value="" size="22"></tr>
								</div>

								<div>
									<label>结束时间：</label>
									 <input  type="date" name="finishTime" id="finishTime" value="" size="22">
								</div>

								<div>
									<label for="url">任务人数：</label>
									 <input type="number" name="totalNum" id="totalNum" value="" size="22">
								</div>
								<div>
									<label for="comment">翻译内容：</label>
									<textarea class="span8" id="zf" onkeyup="a()" name="taskText"  placeholder="输入翻译内容" cols="58" rows="5"
										rows="10"></textarea>
								</div>
								<div style="text-align: center;"><span id="le"></span></div>
									
							    	<c:if test="${roleLevel >1 }">
								    	<div>
								    		<label for="comment">选择文件：</label>
								    			<input type="file"  name="file">${upLoadError}
								    	</div>
							    	</c:if>
						   
								<div>
									<input class="btn" name="submit" type="submit" id="submit"
										value="发布">
								</div>

							</form>

						</div>
				</div>
			</div>
		</div>
	</div>
</body>
</html>