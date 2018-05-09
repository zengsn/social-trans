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
<title>翻译作品</title>

<script type='text/javascript' src='<%=path%>/js/jquery-1.8.3.min.js'></script>
<script type='text/javascript' src='<%=path%>/js/jquery.easing.1.3.js'></script>
<script type='text/javascript'
	src='<%=path%>/js/prettyphoto/jquery.prettyPhoto.js'></script>
<script type='text/javascript' src='<%=path%>/js/jflickrfeed.js'></script>
<script type='text/javascript' src='<%=path%>/js/jquery.liveSearch.js'></script>
<script type='text/javascript' src='<%=path%>/js/jquery.form.js'></script>
<script type='text/javascript' src='<%=path%>/js/jquery.validate.min.js'></script>
<script type='text/javascript' src='<%=path%>/js/custom.js'></script>
<link type="image/x-icon" rel="shortcut icon"
	href="<%=path%>/images/favicon.png" />
<!-- Style Sheet-->
<link rel="stylesheet" href="<%=path%>/css/style.css" type="text/css" />
<link rel='stylesheet' id='bootstrap-css-css'
	href='<%=path%>/css/bootstrap5152.css?ver=1.0' type='text/css'
	media='all' />
<link rel='stylesheet' id='responsive-css-css'
	href='<%=path%>/css/responsive5152.css?ver=1.0' type='text/css'
	media='all' />
<link rel='stylesheet' id='pretty-photo-css-css'
	href='<%=path%>/js/prettyphoto/prettyPhotoaeb9.css?ver=3.1.4'
	type='text/css' media='all' />
<link rel='stylesheet' id='main-css-css'
	href='<%=path%>/css/main5152.css?ver=1.0' type='text/css' media='all' />
<script type='text/javascript' src='<%=path%>/js/banner.js'></script>
<link rel='stylesheet' id='bootstrap-css-css'
	href='<%=path%>/css/banner.css?ver=2.0' type='text/css' media='all' />
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
					<a href="#" title="social-trans"> <img
						src="<%=path %>/images/logo.png" alt="social-trans">
					</a> <span class="tag-line">翻译与译者社交平台</span>
				</div>


				<!-- Start of Main Navigation -->
				<nav class="main-nav">
					<div class="menu-top-menu-container">
						<ul id="menu-top-menu" class="clearfix">
							<li><a href="<%=basePath%>task/toIndex">主页</a></li>
							<li class="current-menu-item"><a
								href="<%=basePath%>task/getReceiveTaskList">任务列表</a></li>

							<li><a href="#">分类</a>
								<ul class="sub-menu">
									<li><a href="<%=basePath%>task/getTaskByDesc?desc=历史">历史</a></li>
									<li><a href="<%=basePath%>task/getTaskByDesc?desc=政治">政治</a></li>
									<li><a href="<%=basePath%>task/getTaskByDesc?desc=小说">小说</a></li>
									<li><a href="<%=basePath%>task/getTaskByDesc?desc=商业">商业</a></li>
									<li><a href="<%=basePath%>task/getTaskByDesc?desc=人文">人文</a></li>
									<li><a href="<%=basePath%>task/getTaskByDesc?desc=校园">校园</a></li>
								</ul></li>
							<li><a href="#">More</a>
								<ul class="sub-menu">
									<li><a href="full-width.html">Full Width</a></li>
									<li><a href="elements.html">Elements</a></li>
									<li><a href="page.html">Sample Page</a></li>
								</ul></li>
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
										</ul></li>
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

	<div class="span8 page-content">
		<section id="comments">

			<h3 id="comments-title">用户提交</h3>

			<ol class="commentlist">
				<c:forEach items="${acList}" var="item" varStatus="re">
					<li class="comment even thread-even depth-1" id="li-comment-2">
						<article id="comment-2">

							<a href="#"> <img alt=""
								src="http://0.gravatar.com/avatar/2df5eab0988aa5ff219476b1d27df755?s=60&amp;d=http%3A%2F%2F0.gravatar.com%2Favatar%2Fad516503a11cd5ca435acc9bb6523536%3Fs%3D60&amp;r=G"
								class="avatar avatar-60 photo" height="60" width="60">
							</a>

							<div class="comment-meta">

								<h5 class="author">
									<cite class="fn"> 
									<a rel="external nofollow" class="url">${item.accepter}</a>
									<div align="right">
									<a href="#"><img src="/social-trans/images/like-btn.png">${item.goods}</a>	
									<a href="<%=path %>/task/adoptTrans?acceptId=${item.acceptId}">采纳</a>	
									</div>
									</cite>

								</h5>

								<p class="date">
									<a href="<%=path %>/task/goods?acceptId=${item.acceptId}">
										<div class="post-meta clearfix">
											<span class="date"> <c:choose>
													<c:when test="${item.score==0}">

													</c:when>
													<c:otherwise>专家评分：${item.score}</c:otherwise>
												</c:choose>
											</span> 
										</div>
									</a>
								</p>

							</div>
							<!-- end .comment-meta -->

							<div class="comment-body">
								<c:choose>
									<c:when test="${fn:length(item.submitText) > 500}">
										<p>
											${fn:substring(item.submitText, 0, 500)}<a
												class="readmore-link" href="#">...[更多]</a>
										</p>
									</c:when>
									<c:otherwise>
										<p>${item.submitText}</p>
									</c:otherwise>
								</c:choose>
								<button onclick="showComment('${item.acceptId}','${re.index }')">评论</button>
							</div>
						</article>
						<div class="showComment${re.index }"></div>
					</li>
				</c:forEach>
			</ol>
		</section>
	</div>
</body>

</html>