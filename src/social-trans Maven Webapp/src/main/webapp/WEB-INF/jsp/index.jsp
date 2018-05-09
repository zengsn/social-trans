<%@ page language="java"
	import="java.util.*,com.crowd.bean.*,com.crowd.service.*"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	String wsPath = "ws://"+request.getServerName()+":"+request.getServerPort()+path+"/";

%>

<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="UTF-8">
<title>首页</title>

	<script type='text/javascript' src='<%=path%>/js/jquery-1.8.3.min.js'></script>
	<script type='text/javascript' src='<%=path%>/js/jquery.easing.1.3.js'></script>
	<script type='text/javascript' src='<%=path%>/js/prettyphoto/jquery.prettyPhoto.js'></script>
	<script type='text/javascript' src='<%=path%>/js/jflickrfeed.js'></script>
	<script type='text/javascript' src='<%=path%>/js/jquery.liveSearch.js'></script>
	<script type='text/javascript' src='<%=path%>/js/jquery.form.js'></script>
	<script type='text/javascript' src='<%=path%>/js/jquery.validate.min.js'></script>
	<script type='text/javascript' src='<%=path%>/js/custom.js'></script>

    <link type="image/x-icon" rel="shortcut icon" href="<%=path%>/images/logo.png"/>

 	<link rel='stylesheet' id='bootstrap-css-css'  href='<%=path%>/css/bootstrap5152.css?ver=1.0' type='text/css' media='all' />
    <link rel='stylesheet' id='responsive-css-css'  href='<%=path%>/css/responsive5152.css?ver=1.0' type='text/css' media='all' />
    <link rel='stylesheet' id='pretty-photo-css-css'  href='<%=path%>/js/prettyphoto/prettyPhotoaeb9.css?ver=3.1.4' type='text/css' media='all' />
    <link rel='stylesheet' id='main-css-css'  href='<%=path%>/css/main5152.css?ver=1.0' type='text/css' media='all' />
    	<script type='text/javascript' src='<%=path%>/js/banner.js'></script>
    <link rel='stylesheet' id='bootstrap-css-css'  href='<%=path%>/css/banner.css?ver=2.0' type='text/css' media='all' />
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
<body>
  </head>
  		 <!-- Start of Header -->
                <div class="header-wrapper">
                        <header>
                                <div class="container">


                                        <div class="logo-container">
                                                <!-- Website Logo -->
                                                <a href="#"  title="social-trans">
                                                        <img src='${pageContext.request.contextPath}/images/logo.png' alt="social-trans">
                                                </a>
                                                <span class="tag-line">翻译与译者社交平台</span>
                                        </div>


                                        <!-- Start of Main Navigation -->
                                        <nav class="main-nav">
                                                <div class="menu-top-menu-container">
                                                        <ul id="menu-top-menu" class="clearfix">
                                                                <li class="current-menu-item"><a href="<%=basePath %>task/toIndex">主页</a></li>
                                                                <li><a href="<%=basePath %>task/getReceiveTaskList">任务列表</a></li>
                                                                
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
                                                                <c:when test="${empty sessionScope.username}">
	                                                                <li><a href="<%=basePath %>user/login">登录</a></li>
	                                                                <li><a href="<%=basePath %>user/register">注册</a></li>
                                                        		</c:when>
                                                        		<c:otherwise>
                                                        			<li><a href="<%=basePath %>user/toIndex">${sessionScope.username }</a>
                                                                        <ul class="sub-menu">
                                                                                <li><a href="<%=basePath %>user/toIndex">用户主页</a></li>
                                                                                <li><a href="<%=basePath %>user/login">注销</a></li>
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

                <!-- Start of Search Wrapper -->
                <div class="search-area-wrapper">
				<div class="box">
				    <div class="scrool">
				        <div class="list">
				            <ul>
				                <li>
				                    <img src="<%=path %>/images/banner1.jpg">
				                </li>
				                <li>
				                   <img src="<%=path %>/images/banner2.jpg">
				                </li>
				                <li>
				                    <img src="<%=path %>/images/banner3.jpg">
				                </li>
				                <li>
				                   <img src="<%=path %>/images/banner4.jpg">
				                </li>
				                <li>
				                   <img src="<%=path %>/images/banner6.jpg">
				                </li>
				            </ul>
				        </div>
				
				    </div>
				    <a href="javascript:;" class="btn next">&gt;</a>
				    <a href="javascript:;" class="btn prev">
				        &lt;</a>
				            <!-- <div class="nav">
				                <a href="javascript:;" class="active"></a>
				                <a href="javascript:;"></a>
				                <a href="javascript:;"></a>
				                <a href="javascript:;"></a>
				                <a href="javascript:;"></a>
				            </div> -->
				
				
				</div>

                </div>
                <!-- End of Search Wrapper -->
 				<!-- Start of Page Container -->
                <div class="page-container">
                        <div class="container">
                                <div class="row">

                                        <!-- start of page content -->
                                        <div class="span8 page-content">

                                                <!-- Basic Home Page Template -->
                                                <div class="row separator">
                                                        <section class="span4 articles-list">
                                                                <h3><a href="<%=basePath%>task/pushTask">推荐任务</a></h3>
                                                                <ul class="articles">
                                                                       <c:forEach items="${pushList }" var="item">
                                                                        <li class="article-entry image">
                                                                                <h4><a href="<%=basePath%>task/taskDetail?taskId=${item.taskId }">${item.taskName }</a></h4>
                                                                                <span class="article-meta">${item.startTime } <a href="single.html" title="View all posts in WordPress for Beginners">${item.description }</a></span>
                                                                                <span class="like-count">${item.taskMoney }</span>
                                                                        </li>
                                                                 </c:forEach> 
                                                                </ul>
                                                        </section>

                                                        <section class="span4 articles-list">
                                                                <h3><a href="<%=basePath%>task/getReceiveTaskList">最新任务</a></h3>
                                                                 <ul class="articles">
                                                                <c:forEach items="${newList }" var="item">
                                                                        <li class="article-entry image">
                                                                                <h4><a href="<%=basePath%>task/taskDetail?taskId=${item.taskId }">${item.taskName }</a></h4>
                                                                                <span class="article-meta">${item.startTime } <a href="single.html" title="View all posts in WordPress for Beginners">${item.description }</a></span>
                                                                                <span class="like-count">${item.taskMoney }</span>
                                                                        </li>
                                                                 </c:forEach> 
                                                                 </ul>
                                                        </section>
                                                </div>

                                                <div class="row home-listing-area">
                                                        <div class="span8">
                                                                <h2>分类</h2>
                                                        </div>
                                                </div>

                                                <div class="row separator">

                                                        <section class="span4 articles-list">
                                                                <h3><a href="<%=basePath%>task/getTaskByDesc?desc=历史">历史</a> <span>(${fn:length(historyList)})</span></h3>
                                                                <ul class="articles">
                                                                <c:forEach items="${historyList }" var="item">
                                                                        <li class="article-entry image">
                                                                                <h4><a href="<%=basePath%>task/taskDetail?taskId=${item.taskId }">${item.taskName }</a></h4>
                                                                                <span class="article-meta">${item.startTime } <a href="single.html" title="View all posts in WordPress for Beginners">${item.description }</a></span>
                                                                                <span class="like-count">${item.taskMoney }</span>
                                                                        </li>
                                                                 </c:forEach> 
                                                                </ul>
                                                        </section>

                                                        <section class="span4 articles-list">
                                                                <h3><a href="<%=basePath%>task/getTaskByDesc?desc=政治">政治</a> <span>(${fn:length(politicalList)})</span></h3>
                                                                <ul class="articles">
                                                                         <c:forEach items="${politicalList }" var="item">
                                                                        <li class="article-entry image">
                                                                                <h4><a href="<%=basePath%>task/taskDetail?taskId=${item.taskId }">${item.taskName }</a></h4>
                                                                                <span class="article-meta">${item.startTime } <a href="single.html" title="View all posts in WordPress for Beginners">${item.description }</a></span>
                                                                                <span class="like-count">${item.taskMoney }</span>
                                                                        </li>
                                                                 </c:forEach> 
                                                                </ul>
                                                        </section>

                                                </div>

                                                <div class="row separator">

                                                        <section class="span4 articles-list">
                                                                <h3><a href="<%=basePath%>task/getTaskByDesc?desc=小说">小说</a> <span>(${fn:length(novelList)})</span></h3>
                                                                <ul class="articles">
                                                                        <c:forEach items="${novelList }" var="item">
                                                                        <li class="article-entry image">
                                                                                <h4><a href="<%=basePath%>task/taskDetail?taskId=${item.taskId }">${item.taskName }</a></h4>
                                                                                <span class="article-meta">${item.startTime } <a href="single.html" title="View all posts in WordPress for Beginners">${item.description }</a></span>
                                                                                <span class="like-count">${item.taskMoney }</span>
                                                                        </li>
                                                                 </c:forEach> 
                                                                </ul>
                                                        </section>

                                                        <section class="span4 articles-list">

                                                                <h3><a href="<%=basePath%>task/getTaskByDesc?desc=人文">人文</a> <span>(${fn:length(humanitiesList)})</span></h3>
                                                                <ul class="articles">
                                                                         <c:forEach items="${humanitiesList }" var="item">
                                                                        <li class="article-entry image">
                                                                                <h4><a href="<%=basePath%>task/taskDetail?taskId=${item.taskId }">${item.taskName }</a></h4>
                                                                                <span class="article-meta">${item.startTime } <a href="single.html" title="View all posts in WordPress for Beginners">${item.description }</a></span>
                                                                                <span class="like-count">${item.taskMoney }</span>
                                                                        </li>
                                                                 </c:forEach> 
                                                                </ul>
                                                        </section>

                                                </div>

                                                <div class="row separator">

                                                        <section class="span4 articles-list">
                                                                <h3><a href="<%=basePath%>task/getTaskByDesc?desc=商业">商业</a> <span>(${fn:length(businessList)})</span></h3>
                                                                <ul class="articles">
                                                                         <c:forEach items="${businessList }" var="item">
                                                                        <li class="article-entry image">
                                                                                <h4><a href="<%=basePath%>task/taskDetail?taskId=${item.taskId }">${item.taskName }</a></h4>
                                                                                <span class="article-meta">${item.startTime } <a href="single.html" title="View all posts in WordPress for Beginners">${item.description }</a></span>
                                                                                <span class="like-count">${item.taskMoney }</span>
                                                                        </li>
                                                                 </c:forEach> 
                                                                </ul>
                                                        </section>

                                                        <section class="span4 articles-list">
                                                                <h3><a href="<%=basePath%>task/getTaskByDesc?desc=校园">校园</a> <span>(${fn:length(schoolyList)})</span></h3>
                                                                <ul class="articles">
                                                                        <c:forEach items="${schoolyList }" var="item">
                                                                        <li class="article-entry image">
                                                                                <h4><a href="<%=basePath%>task/taskDetail?taskId=${item.taskId }">${item.taskName }</a></h4>
                                                                                <span class="article-meta">${item.startTime } <a href="single.html" title="View all posts in WordPress for Beginners">${item.description }</a></span>
                                                                                <span class="like-count">${item.taskMoney }</span>
                                                                        </li>
                                                                 </c:forEach> 
                                                                </ul>
                                                        </section>
                                                </div>

                                        </div>
                                        <!-- end of page content -->


                                        <!-- start of sidebar -->
                                        <aside class="span4 page-sidebar">

                                                <section class="widget">
                                                        <div class="support-widget">
                                                                <h3 class="title" style="text-align: center;"><a href="<%=basePath %>task/uploadTask" style="color: red;font-size: x-large;font-weight: bolder;;">发布任务</a></h3>
                                                                <p class="intro" style="text-align: center;color: black;font-size: larger;">Click and publish the task.</p>
                                                        </div>
                                                </section>

                                                <section class="widget">
                                                        <div class="quick-links-widget">
                                                                <h3 class="title">Links</h3>
                                                                <ul id="menu-quick-links" class="menu clearfix">
                                                                        <li><a href="<%=basePath%>task/toIndex">主页</a></li>
                                                                        <li><a href="<%=basePath %>task/getReceiveTaskList">任务列表</a></li>
                                                                        <li><a href="<%=basePath %>task/pushTask">推荐任务</a></li>
                                                                        <li><a href="<%=basePath %>user/toIndex">用户中心</a></li>
                                                                </ul>
                                                        </div>
                                                </section>

                                                <section class="widget">
                                                        <h3 class="title">Tags</h3>
                                                        <div class="tagcloud">
                                                                <a href="<%=basePath%>task/getTaskByDesc?desc=政治" class="btn btn-mini">政治</a>
                                                                <a href="<%=basePath%>task/getTaskByDesc?desc=历史" class="btn btn-mini">历史</a>
                                                                <a href="<%=basePath%>task/getTaskByDesc?desc=人文" class="btn btn-mini">人文</a>
                                                                <a href="<%=basePath%>task/getTaskByDesc?desc=商业" class="btn btn-mini">商业</a>
                                                                <a href="<%=basePath%>task/getTaskByDesc?desc=校园" class="btn btn-mini">校园</a>
                                                                <a href="<%=basePath%>task/getTaskByDesc?desc=小说" class="btn btn-mini">小说</a>
                                                        </div>
                                                </section>

                                        </aside>
                                        <!-- end of sidebar -->
                                </div>
                        </div>
                </div>
                <!-- End of Page Container -->
	 <a href="#top" id="scroll-top"></a>
</body>
</html>
