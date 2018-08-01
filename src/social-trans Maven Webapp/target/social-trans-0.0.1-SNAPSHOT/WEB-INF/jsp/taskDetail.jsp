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
<title>任务详情</title>
	
	<%-- <script type='text/javascript' src='<%=path%>/js/jquery-1.8.3.min.js'></script> --%>
	<script type='text/javascript' src='<%=path%>/js/jquery.min.js'></script>
	<script type='text/javascript' src='<%=path%>/js/jquery.easing.1.3.js'></script>
	<script type='text/javascript' src='<%=path%>/js/prettyphoto/jquery.prettyPhoto.js'></script>
	<script type='text/javascript' src='<%=path%>/js/jflickrfeed.js'></script>
	<script type='text/javascript' src='<%=path%>/js/jquery.liveSearch.js'></script>
	<script type='text/javascript' src='<%=path%>/js/jquery.form.js'></script>
	<script type='text/javascript' src='<%=path%>/js/jquery.validate.min.js'></script>
	<script type='text/javascript' src='<%=path%>/js/jquery.bootbox.js'></script>
	<script type='text/javascript' src='<%=path%>/js/bootstrap.min.js'></script>
	<script type='text/javascript' src='<%=path%>/js/custom.js'></script>
    <link type="image/x-icon" rel="shortcut icon" href="<%=path%>/images/logo.png"/>
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
                                        <div class="span8 page-content">

                                               <!--  <ul class="breadcrumb">
                                                        <li><a href="#">Knowledge Base Theme</a><span class="divider">/</span></li>
                                                        <li><a href="#" title="View all posts in Server &amp; Database">Server &amp; Database</a> <span class="divider">/</span></li>
                                                        <li class="active">Integrating WordPress with Your Website</li>
                                                </ul> -->

                                                <article class=" type-post format-standard hentry clearfix">

                                                        <h1 class="post-title"><a href="#">${reTask.taskName }</a></h1>

                                                        <div class="post-meta clearfix">
                                                                <span class="date">${reTask.startTime }</span>
                                                                <span class="category"><a href="#" title="View all posts in Server &amp; Database">${reTask.taskMoney}</a></span>
                                                                <%-- <span class="comments"><a href="#" title="Comment on Integrating WordPress with Your Website">${reTask.receiveNum}</a></span> --%>
                                                                <span class="person-count	">${reTask.receiveNum}</span>
                                                        </div><!-- end of post meta -->
														<p>${reTask.taskText}</p>
													 <%--<c:choose>  
														
													    <c:when test="${fn:length(reTask.taskText) > 500}">  
													       <p> ${fn:substring(reTask.taskText, 0, 500)}<a class="readmore-link" href="#">...[更多]</a></p> 
													    </c:when>  
													   <c:otherwise>  
													      <p>${reTask.taskText}</p> 
													    </c:otherwise> 
													</c:choose>    --%>
                                                <div class="like-btn">

                                                        <span class="tags">
                                                                <strong>Tags:&nbsp;&nbsp;</strong><a href="<%=basePath %>task/getTaskByDesc?desc=${reTask.description}" rel="tag">${reTask.description }</a>
                                                              <%--   <form  method="post" action="<%=path %>/task/acceptTask"> 
														      		<input type="hidden" name="taskId" value="${reTask.taskId}" />
														     		 <input type="submit"  value="领取" > --%>
														     		 <button onclick="acceptTask('${reTask.taskId}','${sessionScope.account}')">领取</button>
														     	</form>${error}
                                                        </span>

                                                </div>

                                                <section id="comments">

                                                        <h3 id="comments-title">用户提交</h3>

                                                        <ol class="commentlist">
															<c:forEach items="${acList}" var="item" varStatus="re">
                                                                <li class="comment even thread-even depth-1" id="li-comment-2">
                                                                        <article id="comment-2">

                                                                                <a href="#">
                                                                                        <img alt="" src="http://0.gravatar.com/avatar/2df5eab0988aa5ff219476b1d27df755?s=60&amp;d=http%3A%2F%2F0.gravatar.com%2Favatar%2Fad516503a11cd5ca435acc9bb6523536%3Fs%3D60&amp;r=G" class="avatar avatar-60 photo" height="60" width="60">
                                                                                </a>

                                                                                <div class="comment-meta">

                                                                                        <h5 class="author">
                                                                                                <cite class="fn">
                                                                                                        <a rel="external nofollow" class="url">${item.accepter}</a>
                                                                                                </cite>
                                                                                                
                                                                                        </h5>

                                                                                        <p class="date">
                                                                                                <a href="<%=path %>/task/goods?acceptId=${item.acceptId}">
                                                                                                        <%-- <time datetime="2013-02-26T13:18:47+00:00">
                                                                                                        <ul>
                                                                                                        <c:choose>
																											<c:when test="${item.score==0}">
																											<br>
																											</c:when>
																											<c:otherwise>专家评分：${item.score}</c:otherwise>
																										</c:choose>
																										</ul>
																										<ul>
																										<span class="like-count" ><a href="<%=path %>/task/goods?acceptId=${item.acceptId}"><div style="text-align: right;">${item.goods}</div></a></span>
																										</ul
																										</time> --%>
																										  <div class="post-meta clearfix">
												                                                                <span class="date"> 
												                                                                	<c:choose>
																													<c:when test="${item.score==0}">
																													
																													</c:when>
																													<c:otherwise>专家评分：${item.score}</c:otherwise>
																													</c:choose>
																												</span>
												                                                                <span class="like-count" style="text-align: right;">${item.goods}</span>
												                                                        </div> 
                                                                                                </a>
                                                                                                <c:if test="${sessionScope.role =='专家' ||  sessionScope.userId == reTask.publishId}">
                                                                                                <form  method="post" action="<%=path %>/task/gradeTask" onsubmit="return checkNum();"> 
																					      			<input type="hidden" name="acceptId" value="${item.acceptId}"/>
																					      			<input type="number" name="score" id="score"/>
																					     		 	<input type="submit" class="btn btn-mini" value="评分" >
																					     		 	${gradeError}
																					     		</form>
																					     		</c:if>
                                                                                        </p>
																						 
                                                                                </div><!-- end .comment-meta -->

                                                                                <div class="comment-body">
                                                                                <c:choose>  
																				    <c:when test="${fn:length(item.submitText) > 500}">  
																				       <p> ${fn:substring(item.submitText, 0, 500)}<a class="readmore-link" href="#">...[更多]</a></p> 
																				    </c:when>  
																				   <c:otherwise>  
																				      <p>${item.submitText}</p> 
																				    </c:otherwise>  
																				</c:choose>  
																				<button onclick="showComment('${item.acceptId}','${re.index }')" >评论</button>
                                                                                </div>
                                                                        </article>
																				<div class="showComment${re.index }">
																				
																				</div>
                                                                </li>
															</c:forEach>
                                                        </ol>

                                                        
                                                </section><!-- end of comments -->

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
                                                        </ul>
                                                </section>



                                                <section class="widget"><h3 class="title">任务分类</h3>
                                                        <ul>
                                                        	  <li> <a href="<%=basePath%>task/getTaskByDesc?desc=政治" >政治</a></li>
                                                               <li> <a href="<%=basePath%>task/getTaskByDesc?desc=历史" >历史</a></li>
                                                               <li><a href="<%=basePath%>task/getTaskByDesc?desc=人文" >人文</a></li>
                                                               <li><a href="<%=basePath%>task/getTaskByDesc?desc=商业" >商业</a></li>
                                                               <li><a href="<%=basePath%>task/getTaskByDesc?desc=校园" >校园</a></li>
                                                               <li><a href="<%=basePath%>task/getTaskByDesc?desc=小说" >小说</a></li>
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
<script>
function showComment(acceptId,index){  
	 //动态banner
	 var parameter={
			 acceptId:acceptId				
 		};
   $.post("/social-trans/task/getComment",parameter,function(data){
		if(data!=null&&data!=""){
	 			
	 				var $text1 =" <ul class='children'> <div id='respond'> <div class='cancel-comment-reply'> <a rel='nofollow' id='cancel-comment-reply-link' href='#' style='display:none;'></a></div> <form action='${pageContext.servletContext.contextPath}/task/comment' method='post' id='commentform'><input type='hidden' name='acceptId' value="+acceptId+"><div><label for='comment'>Comment</label><textarea class='span8' name='comment' id='comment' cols='30' rows='3' placeholder='输入评论信息' data-rule-required='true'></textarea> </div> <div><input class='btn' name='submit' type='submit' id='submit'  value='提交'> </div></form></div>";
	 				var $text2 ="";
	 				if(JSON.parse(data).length>0){
		 				$.each(JSON.parse(data),function(i,item){
			 					$text2 = $text2 +" <li><h5>"+item.username+"： </h5> "+item.comment+"</li>";
			 			});
	 				}
	 				var $text3 = "</ul>";
	 				var $comment = $($text1 + $text2 +$text3);
	 				$(".showComment"+index).append($comment);		
	 		
		}
	});    
};

//领取任务
function acceptTask(taskId,account) {
	
		if(account == "") {
				//提示为空
				bootbox.alert("请登录后再领取任务！") ;
				return ;
		 } 
		
		bootbox.confirm("确定领取该任务吗？", function(r){
        	if(r){
        		//删除
				$.post(
			    	"${pageContext.servletContext.contextPath}/task/acceptTask",
			    	{"taskId":taskId},	
				    function(data) {	       
				       if(data=="ReError") {
				    	   bootbox.alert('您已经领取了此任务！') ;
				       }else if(data=="Over"){
				       		bootbox.alert('任务已经被领取完了！') ;
				       }
				       else if(data=="Max"){
				       		bootbox.alert('此任务最多领取3个，请完成后再领取新的任务！！') ;
				       }
				       else if(data=="success"){
				       		bootbox.alert('领取任务成功！') ;
				       }
				       else if(data=="error"){
				       		bootbox.alert('领取任务失败，任务已下架！') ;
				       }else if(data=="login"){
				       		bootbox.alert('您未登陆 ，请登陆后再领取任务！') ;
				       }
				    }
				);		
        	}   
    });   
}

</script>
</html>
