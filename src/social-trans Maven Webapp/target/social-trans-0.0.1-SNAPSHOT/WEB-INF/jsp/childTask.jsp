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
    <link rel="stylesheet" href="<%=path%>/css/style.css"  type="text/css"/>
    <link rel='stylesheet' id='bootstrap-css-css'  href='<%=path%>/css/bootstrap5152.css?ver=1.0' type='text/css' media='all' />
    <link rel='stylesheet' id='responsive-css-css'  href='<%=path%>/css/responsive5152.css?ver=1.0' type='text/css' media='all' />
    <link rel='stylesheet' id='pretty-photo-css-css'  href='<%=path%>/js/prettyphoto/prettyPhotoaeb9.css?ver=3.1.4' type='text/css' media='all' />
    <link rel='stylesheet' id='main-css-css'  href='<%=path%>/css/main5152.css?ver=1.0' type='text/css' media='all' />
    	<script type='text/javascript' src='<%=path%>/js/banner.js'></script>
    <link rel='stylesheet' id='bootstrap-css-css'  href='<%=path%>/css/banner.css?ver=2.0' type='text/css' media='all' />
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
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
				</div>

                </div>
                <!-- End of Search Wrapper -->
	
	<div>
		 <!-- Start of Page Container -->
                <div class="page-container">
                        <div class="container">
                                <div class="row">

                                        <!-- start of page content -->
                                        <div class="span8 main-listing">
											<c:forEach items="${reList}" var="item">
                                                <article class="format-standard type-post hentry clearfix">

                                                        <header class="clearfix">

                                                                <h3 class="post-title">
                                                                        <a href="<%=basePath%>task/taskDetail?taskId=${item.taskId }">${item.taskName}</a>
                                                                </h3>

                                                                <div class="post-meta clearfix">
                                                                        <span class="date">${item.startTime}</span>
                                                                        <span class="category"><a href="#" title="View all posts in Server &amp; Database">${item.description}</a></span>
                                                                        <span class="comments"><a href="#" title="Comment on Integrating WordPress with Your Website">${item.taskMoney}</a></span>
                                                                        <span class="person-count">${item.receiveNum }</span>
                                                                </div><!-- end of post meta -->

                                                        </header>
                                                         
												<c:choose>  
												    <c:when test="${fn:length(item.taskText) > 150}">  
												       <p> ${fn:substring(item.taskText, 0, 150)}<a class="readmore-link" href="<%=basePath%>task/taskDetail?taskId=${item.taskId }">...[更多]</a></p> 
												    </c:when>  
												   <c:otherwise>  
												      <p>${item.taskText}  <a class="readmore-link" href="#"></a></p> 
												    </c:otherwise>  
												</c:choose>  
                                                       

                                                </article>
											</c:forEach>
                                             <!--  <div id="pagination">
                                                      <a href="#" class="btn active">1</a>
                                                      <a href="#" class="btn">2</a>
                                                      <a href="#" class="btn">3</a>
                                                      <a href="#" class="btn">Next »</a>
                                                      <a href="#" class="btn">Last »</a>
                                              </div> -->
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
                                                        <h3 class="title">最新任务</h3>
                                                        <ul class="articles">
                                                        <c:forEach items="${newList}" var="item">
                                                                <li class="article-entry standard">
                                                                        <h4><a href="<%=basePath%>task/taskDetail?taskId=${item.taskId }">${item.taskName }</a></h4>
                                                                        <span class="article-meta">${item.startTime}<a href="#" title="View all posts in Server &amp; Database">&nbsp;&nbsp;&nbsp;&nbsp;${item.description}</a></span>
                                                                        <span class="like-count">${item.taskMoney}</span>
                                                                </li>
														</c:forEach>                                                                
                                                                <!-- <li class="article-entry standard">
                                                                        <h4><a href="single.html">WordPress Site Maintenance</a></h4>
                                                                        <span class="article-meta">24 Feb, 2013 in <a href="#" title="View all posts in Website Dev">Website Dev</a></span>
                                                                        <span class="like-count">15</span>
                                                                </li>
                                                                <li class="article-entry video">
                                                                        <h4><a href="single.html">Meta Tags in WordPress</a></h4>
                                                                        <span class="article-meta">23 Feb, 2013 in <a href="#" title="View all posts in Website Dev">Website Dev</a></span>
                                                                        <span class="like-count">8</span>
                                                                </li>
                                                                <li class="article-entry image">
                                                                        <h4><a href="single.html">WordPress in Your Language</a></h4>
                                                                        <span class="article-meta">22 Feb, 2013 in <a href="#" title="View all posts in Advanced Techniques">Advanced Techniques</a></span>
                                                                        <span class="like-count">6</span>
                                                                </li> -->
                                                        </ul>
                                                </section>



                                                <section class="widget"><h3 class="title">任务分类</h3>
                                                        <ul>
                                                                <li><a href="#" title="Lorem ipsum dolor sit amet,">历史</a> </li>
                                                                <li><a href="#" title="Lorem ipsum dolor sit amet,">政治</a></li>
                                                                <li><a href="#" title="Lorem ipsum dolor sit amet,">人文</a></li>
                                                                <li><a href="#" title="Lorem ipsum dolor sit amet, ">校园</a></li>
                                                                <li><a href="#" title="Lorem ipsum dolor sit amet,">商业</a></li>
                                                                <li><a href="#" title="Lorem ipsum dolor sit amet,">小说</a></li>
                                                        </ul>
                                                </section>

                                                <section class="widget">
                                                        <h3 class="title">Recent Comments</h3>
                                                        <ul id="recentcomments">
                                                                <li class="recentcomments"><a href="#" rel="external nofollow" class="url">John Doe</a> on <a href="#">Integrating WordPress with Your Website</a></li>
                                                                <li class="recentcomments">saqib sarwar on <a href="#">Integrating WordPress with Your Website</a></li>
                                                                <li class="recentcomments"><a href="#" rel="external nofollow" class="url">John Doe</a> on <a href="#">Integrating WordPress with Your Website</a></li>
                                                                <li class="recentcomments"><a href="#" rel="external nofollow" class="url">Mr WordPress</a> on <a href="#">Installing WordPress</a></li>
                                                        </ul>
                                                </section>

                                        </aside>
                                        <!-- end of sidebar -->
                                </div>
                        </div>
                </div>
                <!-- End of Page Container -->
	</div>
</body>
</html>
