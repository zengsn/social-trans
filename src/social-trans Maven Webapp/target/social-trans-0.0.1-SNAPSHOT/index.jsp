<%@ page language="java"
	import="java.util.*,com.crowd.bean.*,com.crowd.service.*"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
    <link type="image/x-icon" rel="shortcut icon" href="<%=path%>/images/favicon.png"/>
    <!-- Style Sheet-->
    <link rel="stylesheet" href="<%=path%>/css/style.css"  type="text/css"/>
    <link rel='stylesheet' id='bootstrap-css-css'  href='<%=path%>/css/bootstrap5152.css?ver=1.0' type='text/css' media='all' />
    <link rel='stylesheet' id='responsive-css-css'  href='<%=path%>/css/responsive5152.css?ver=1.0' type='text/css' media='all' />
    <link rel='stylesheet' id='pretty-photo-css-css'  href='<%=path%>/js/prettyphoto/prettyPhotoaeb9.css?ver=3.1.4' type='text/css' media='all' />
    <link rel='stylesheet' id='main-css-css'  href='<%=path%>/css/main5152.css?ver=1.0' type='text/css' media='all' />
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
                                                        <img src="<%=path %>/images/logo.png" alt="social-trans">
                                                </a>
                                                <span class="tag-line">翻译与译者社交平台</span>
                                        </div>


                                        <!-- Start of Main Navigation -->
                                        <nav class="main-nav">
                                                <div class="menu-top-menu-container">
                                                        <ul id="menu-top-menu" class="clearfix">
                                                                <li class="current-menu-item"><a href="/social-trans">主页</a></li>
                                                                <li><a href="task/getReceiveTaskList">任务列表</a></li>
                                                                
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
                                                                <c:when test="${empty sessionScope.account }">
	                                                                <li><a href="user/login">登录</a></li>
	                                                                <li><a href="user/register">注册</a></li>
                                                        		</c:when>
                                                        		<c:otherwise>
                                                        			<li><a href="user/userData">${sessionScope.account }</a>
                                                                        <ul class="sub-menu">
                                                                                <li><a href="user/userData">用户主页</a></li>
                                                                                <li><a href="user/login">注销</a></li>
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
                        <div class="search-area container">
                                <h3 class="search-header">Have a Question?</h3>
                                <p class="search-tag-line">If you have any question you can ask below or enter what you are looking for!</p>

                                <form id="search-form" class="search-form clearfix" method="get" action="#" autocomplete="off">
                                        <input class="search-term required" type="text" id="s" name="s" placeholder="Type your search terms here" title="* Please enter a search term!" />
                                        <input class="search-btn" type="submit" value="Search" />
                                        <div id="search-error-container"></div>
                                </form>
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
                                                                        <li class="article-entry standard">
                                                                                <h4><a href="single.html">Integrating WordPress with Your Website</a></h4>
                                                                                <span class="article-meta">25 Feb, 2013 in <a href="single.html" title="View all posts in Server &amp; Database">Server &amp; Database</a></span>
                                                                                <span class="like-count">66</span>
                                                                        </li>
                                                                        <li class="article-entry standard">
                                                                                <h4><a href="single.html">WordPress Site Maintenance</a></h4>
                                                                                <span class="article-meta">24 Feb, 2013 in <a href="single.html" title="View all posts in Website Dev">Website Dev</a></span>
                                                                                <span class="like-count">15</span>
                                                                        </li>
                                                                        <li class="article-entry video">
                                                                                <h4><a href="single.html">Meta Tags in WordPress</a></h4>
                                                                                <span class="article-meta">23 Feb, 2013 in <a href="single.html" title="View all posts in Website Dev">Website Dev</a></span>
                                                                                <span class="like-count">8</span>
                                                                        </li>
                                                                        <li class="article-entry image">
                                                                                <h4><a href="single.html">WordPress in Your Language</a></h4>
                                                                                <span class="article-meta">22 Feb, 2013 in <a href="single.html" title="View all posts in Advanced Techniques">Advanced Techniques</a></span>
                                                                                <span class="like-count">6</span>
                                                                        </li>
                                                                        <li class="article-entry standard">
                                                                                <h4><a href="single.html">Know Your Sources</a></h4>
                                                                                <span class="article-meta">22 Feb, 2013 in <a href="single.html" title="View all posts in Website Dev">Website Dev</a></span>
                                                                                <span class="like-count">2</span>
                                                                        </li>
                                                                        <li class="article-entry standard">
                                                                                <h4><a href="single.html">Validating a Website</a></h4>
                                                                                <span class="article-meta">21 Feb, 2013 in <a href="single.html" title="View all posts in Website Dev">Website Dev</a></span>
                                                                                <span class="like-count">3</span>
                                                                        </li>
                                                                </ul>
                                                        </section>

                                                        <section class="span4 articles-list">
                                                                <h3><a href="<%=basePath%>task/getReceiveTaskList">新发布任务</a></h3>
                                                                <ul class="articles">
                                                                        <li class="article-entry standard">
                                                                                <h4><a href="single.html">Integrating WordPress with Your Website</a></h4>
                                                                                <span class="article-meta">25 Feb, 2013 in <a href="single.html" title="View all posts in Server &amp; Database">Server &amp; Database</a></span>
                                                                                <span class="like-count">66</span>
                                                                        </li>
                                                                        <li class="article-entry standard">
                                                                                <h4><a href="single.html">Using Javascript</a></h4>
                                                                                <span class="article-meta">25 Feb, 2013 in <a href="single.html" title="View all posts in Advanced Techniques">Advanced Techniques</a></span>
                                                                                <span class="like-count">18</span>
                                                                        </li>
                                                                        <li class="article-entry image">
                                                                                <h4><a href="single.html">Using Images</a></h4>
                                                                                <span class="article-meta">25 Feb, 2013 in <a href="single.html" title="View all posts in Designing in WordPress">Designing in WordPress</a></span>
                                                                                <span class="like-count">7</span>
                                                                        </li>
                                                                        <li class="article-entry video">
                                                                                <h4><a href="single.html">Using Video</a></h4>
                                                                                <span class="article-meta">24 Feb, 2013 in <a href="single.html" title="View all posts in WordPress Plugins">WordPress Plugins</a></span>
                                                                                <span class="like-count">7</span>
                                                                        </li>
                                                                        <li class="article-entry standard">
                                                                                <h4><a href="single.html">WordPress Site Maintenance</a></h4>
                                                                                <span class="article-meta">24 Feb, 2013 in <a href="single.html" title="View all posts in Website Dev">Website Dev</a></span>
                                                                                <span class="like-count">15</span>
                                                                        </li>
                                                                        <li class="article-entry standard">
                                                                                <h4><a href="single.html">WordPress CSS Information and Techniques</a></h4>
                                                                                <span class="article-meta">24 Feb, 2013 in <a href="single.html" title="View all posts in Theme Development">Theme Development</a></span>
                                                                                <span class="like-count">1</span>
                                                                        </li>
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
                                                                <h3><a href="<%=basePath%>task/getTaskByDesc?desc=历史">历史</a> <span>(4)</span></h3>
                                                                <ul class="articles">
                                                                        <li class="article-entry image">
                                                                                <h4><a href="single.html">New To WordPress &#8211; Where to Start</a></h4>
                                                                                <span class="article-meta">24 Feb, 2013 in <a href="single.html" title="View all posts in WordPress for Beginners">WordPress for Beginners</a></span>
                                                                                <span class="like-count">1</span>
                                                                        </li>
                                                                        <li class="article-entry standard">
                                                                                <h4><a href="single.html">Introduction to Blogging</a></h4>
                                                                                <span class="article-meta">23 Feb, 2013 in <a href="single.html" title="View all posts in WordPress for Beginners">WordPress for Beginners</a></span>
                                                                                <span class="like-count">1</span>
                                                                        </li>
                                                                        <li class="article-entry image">
                                                                                <h4><a href="single.html">First Steps With WordPress</a></h4>
                                                                                <span class="article-meta">22 Feb, 2013 in <a href="single.html" title="View all posts in WordPress for Beginners">WordPress for Beginners</a></span>
                                                                                <span class="like-count">0</span>
                                                                        </li>
                                                                        <li class="article-entry standard">
                                                                                <h4><a href="single.html">Installing WordPress</a></h4>
                                                                                <span class="article-meta">21 Feb, 2013 in <a href="single.html" title="View all posts in WordPress for Beginners">WordPress for Beginners</a></span>
                                                                                <span class="like-count">1</span>
                                                                        </li>
                                                                </ul>
                                                        </section>

                                                        <section class="span4 articles-list">
                                                                <h3><a href="<%=basePath%>task/getTaskByDesc?desc=政治">政治</a> <span>(4)</span></h3>
                                                                <ul class="articles">
                                                                        <li class="article-entry image">
                                                                                <h4><a href="single.html">Using Images</a></h4>
                                                                                <span class="article-meta">25 Feb, 2013 in <a href="single.html" title="View all posts in Designing in WordPress">Designing in WordPress</a></span>
                                                                                <span class="like-count">7</span>
                                                                        </li>
                                                                        <li class="article-entry standard">
                                                                                <h4><a href="single.html">Designing Headers</a></h4>
                                                                                <span class="article-meta">23 Feb, 2013 in <a href="single.html" title="View all posts in Designing in WordPress">Designing in WordPress</a></span>
                                                                                <span class="like-count">1</span>
                                                                        </li>
                                                                        <li class="article-entry standard">
                                                                                <h4><a href="single.html">Formatting Date and Time</a></h4>
                                                                                <span class="article-meta">22 Feb, 2013 in <a href="single.html" title="View all posts in Designing in WordPress">Designing in WordPress</a></span>
                                                                                <span class="like-count">0</span>
                                                                        </li>
                                                                        <li class="article-entry standard">
                                                                                <h4><a href="single.html">Developing a Colour Scheme</a></h4>
                                                                                <span class="article-meta">21 Feb, 2013 in <a href="single.html" title="View all posts in Designing in WordPress">Designing in WordPress</a></span>
                                                                                <span class="like-count">0</span>
                                                                        </li>
                                                                </ul>
                                                        </section>

                                                </div>

                                                <div class="row separator">

                                                        <section class="span4 articles-list">
                                                                <h3><a href="<%=basePath%>task/getTaskByDesc?desc=小说">小说</a> <span>(4)</span></h3>
                                                                <ul class="articles">
                                                                        <li class="article-entry standard">
                                                                                <h4><a href="single.html">WordPress CSS Information and Techniques</a></h4>
                                                                                <span class="article-meta">24 Feb, 2013 in <a href="single.html" title="View all posts in Theme Development">Theme Development</a></span>
                                                                                <span class="like-count">1</span>
                                                                        </li>
                                                                        <li class="article-entry image">
                                                                                <h4><a href="single.html">Stepping Into Templates</a></h4>
                                                                                <span class="article-meta">23 Feb, 2013 in <a href="single.html" title="View all posts in Theme Development">Theme Development</a></span>
                                                                                <span class="like-count">0</span>
                                                                        </li>
                                                                        <li class="article-entry standard">
                                                                                <h4><a href="single.html">Creating Individual Pages</a></h4>
                                                                                <span class="article-meta">22 Feb, 2013 in <a href="single.html" title="View all posts in Theme Development">Theme Development</a></span>
                                                                                <span class="like-count">0</span>
                                                                        </li>
                                                                        <li class="article-entry image">
                                                                                <h4><a href="single.html">Uploading Files</a></h4>
                                                                                <span class="article-meta">21 Feb, 2013 in <a href="single.html" title="View all posts in Theme Development">Theme Development</a></span>
                                                                                <span class="like-count">0</span>
                                                                        </li>
                                                                </ul>
                                                        </section>

                                                        <section class="span4 articles-list">

                                                                <h3><a href="<%=basePath%>task/getTaskByDesc?desc=人文">人文</a> <span>(4)</span></h3>
                                                                <ul class="articles">
                                                                        <li class="article-entry standard">
                                                                                <h4><a href="single.html">WordPress Site Maintenance</a></h4>
                                                                                <span class="article-meta">24 Feb, 2013 in <a href="single.html" title="View all posts in Website Dev">Website Dev</a></span>
                                                                                <span class="like-count">15</span>
                                                                        </li>
                                                                        <li class="article-entry video">
                                                                                <h4><a href="single.html">Meta Tags in WordPress</a></h4>
                                                                                <span class="article-meta">23 Feb, 2013 in <a href="single.html" title="View all posts in Website Dev">Website Dev</a></span>
                                                                                <span class="like-count">8</span>
                                                                        </li>
                                                                        <li class="article-entry standard">
                                                                                <h4><a href="single.html">Know Your Sources</a></h4>
                                                                                <span class="article-meta">22 Feb, 2013 in <a href="single.html" title="View all posts in Website Dev">Website Dev</a></span>
                                                                                <span class="like-count">2</span>
                                                                        </li>
                                                                        <li class="article-entry standard">
                                                                                <h4><a href="single.html">Validating a Website</a></h4>
                                                                                <span class="article-meta">21 Feb, 2013 in <a href="single.html" title="View all posts in Website Dev">Website Dev</a></span>
                                                                                <span class="like-count">3</span>
                                                                        </li>
                                                                </ul>
                                                        </section>

                                                </div>

                                                <div class="row separator">

                                                        <section class="span4 articles-list">
                                                                <h3><a href="<%=basePath%>task/getTaskByDesc?desc=商业">商业</a> <span>(4)</span></h3>
                                                                <ul class="articles">
                                                                        <li class="article-entry video">
                                                                                <h4><a href="single.html">Using Video</a></h4>
                                                                                <span class="article-meta">24 Feb, 2013 in <a href="single.html" title="View all posts in WordPress Plugins">WordPress Plugins</a></span>
                                                                                <span class="like-count">7</span>
                                                                        </li>
                                                                        <li class="article-entry standard">
                                                                                <h4><a href="single.html">Photoblogs and Galleries</a></h4>
                                                                                <span class="article-meta">23 Feb, 2013 in <a href="single.html" title="View all posts in WordPress Plugins">WordPress Plugins</a></span>
                                                                                <span class="like-count">2</span>
                                                                        </li>
                                                                        <li class="article-entry video">
                                                                                <h4><a href="single.html">Plugin Resources</a></h4>
                                                                                <span class="article-meta">22 Feb, 2013 in <a href="single.html" title="View all posts in WordPress Plugins">WordPress Plugins</a></span>
                                                                                <span class="like-count">1</span>
                                                                        </li>
                                                                        <li class="article-entry standard">
                                                                                <h4><a href="single.html">Managing Plugins</a></h4>
                                                                                <span class="article-meta">21 Feb, 2013 in <a href="single.html" title="View all posts in WordPress Plugins">WordPress Plugins</a></span>
                                                                                <span class="like-count">0</span>
                                                                        </li>
                                                                </ul>
                                                        </section>

                                                        <section class="span4 articles-list">
                                                                <h3><a href="<%=basePath%>task/getTaskByDesc?desc=校园">校园</a> <span>(4)</span></h3>
                                                                <ul class="articles">
                                                                        <li class="article-entry standard">
                                                                                <h4><a href="single.html">Using Javascript</a></h4>
                                                                                <span class="article-meta">25 Feb, 2013 in <a href="single.html" title="View all posts in Advanced Techniques">Advanced Techniques</a></span>
                                                                                <span class="like-count">18</span>
                                                                        </li>
                                                                        <li class="article-entry video">
                                                                                <h4><a href="single.html">Editing Files</a></h4>
                                                                                <span class="article-meta">24 Feb, 2013 in <a href="single.html" title="View all posts in Advanced Techniques">Advanced Techniques</a></span>
                                                                                <span class="like-count">1</span>
                                                                        </li>
                                                                        <li class="article-entry standard">
                                                                                <h4><a href="single.html">Importing Content</a></h4>
                                                                                <span class="article-meta">23 Feb, 2013 in <a href="single.html" title="View all posts in Advanced Techniques">Advanced Techniques</a></span>
                                                                                <span class="like-count">0</span>
                                                                        </li>
                                                                        <li class="article-entry image">
                                                                                <h4><a href="single.html">WordPress in Your Language</a></h4>
                                                                                <span class="article-meta">22 Feb, 2013 in <a href="single.html" title="View all posts in Advanced Techniques">Advanced Techniques</a></span>
                                                                                <span class="like-count">6</span>
                                                                        </li>
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
                                                                        <li><a href="/social-trans">主页</a></li>
                                                                        <li><a href="<%=basePath %>task/getReceiveTaskList">任务列表</a></li>
                                                                        <li><a href="<%=basePath %>task/pushTask">推荐任务</a></li>
                                                                        <li><a href="<%=basePath %>/user/userData">用户中心</a></li>
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
