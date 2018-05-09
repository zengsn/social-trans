<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
 <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
 <%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
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
    <title>用户主页</title>
	<script type='text/javascript' src='<%=path%>/js/jquery-1.8.3.min.js'></script>
	<script type='text/javascript' src='<%=path%>/js/jquery.easing.1.3.js'></script>
	<script type='text/javascript' src='<%=path%>/js/prettyphoto/jquery.prettyPhoto.js'></script>
	<script type='text/javascript' src='<%=path%>/js/jflickrfeed.js'></script>
	<script type='text/javascript' src='<%=path%>/js/jquery.liveSearch.js'></script>
	<script type='text/javascript' src='<%=path%>/js/jquery.form.js'></script>
	<script type='text/javascript' src='<%=path%>/js/jquery.validate.min.js'></script>
	<script type='text/javascript' src='<%=path%>/js/custom.js'></script>
	<script type='text/javascript' src='<%=path%>/js/banner.js'></script>
	<script type='text/javascript' src='<%=path%>/js/jquery.bootbox.js'></script>
	<script type='text/javascript' src='<%=path%>/js/bootstrap.min.js'></script>
    <link type="image/x-icon" rel="shortcut icon" href="<%=path%>/images/logo.png"/>

 	<link rel='stylesheet' id='bootstrap-css-css'  href='<%=path%>/css/bootstrap5152.css?ver=1.0' type='text/css' media='all' />
    <link rel='stylesheet' id='responsive-css-css'  href='<%=path%>/css/responsive5152.css?ver=1.0' type='text/css' media='all' />
    <link rel='stylesheet' id='pretty-photo-css-css'  href='<%=path%>/js/prettyphoto/prettyPhotoaeb9.css?ver=3.1.4' type='text/css' media='all' />
    <link rel='stylesheet' id='main-css-css'  href='<%=path%>/css/main5152.css?ver=1.0' type='text/css' media='all' />
    <link rel='stylesheet' id='bootstrap-css-css'  href='<%=path%>/css/banner.css?ver=2.0' type='text/css' media='all' />
  </head>
  <body>
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
                                                                <c:when test="${empty sessionScope.username }">
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
               <%--  <div class="search-area-wrapper">
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

                </div> --%>
                <!-- End of Search Wrapper -->

              <!-- Start of Page Container -->
                <div class="page-container">
                        <div class="container">
                                <div class="row">

                                        <!-- start of page content -->
                                        <div class="span8 page-content">

                                                <article class=" page type-page hentry clearfix">
                                                        <h1 class="post-title"><a href="#">${user.username }</a></h1>
                                                        <hr>
                                                        <p>WordPress is open source web software that you can install on your web server to create your website, blog, community or network. WordPress started out as a tool for blogging, but has evolved into a full-fledged Content Management System (CMS), capable of powering websites, networks and communities.</p>
                                                </article>

                                                <div class="faqs clearfix">

                                                        <article class="faq-item active">
                                                                <span class="faq-icon"></span>
                                                                <h3 class="faq-question">
                                                                        <a href="#">已领取任务</a>
                                                                </h3>
                                                                <div class="faq-answer">
                                                                	<ul>
																		<c:forEach items="${unfinishList}" var="item">
																		 <header class="clearfix">

				                                                              <h5 class="post-title">
				                                                                      <a href="<%=basePath%>task/taskDetail?taskId=${item.taskId }">${item.taskName}</a>&nbsp
				                                                                       <div align="right" style="color: red"><a href="javaScript:cancelAccept('${item.taskId }')">撤销任务</a></div>
				                                                              </h5>
				
				                                                              <div class="post-meta clearfix">
				                                                                      <span class="date">${item.finishTime}</span>&nbsp&nbsp
				                                                                      <span class="category"><a href="#">${item.taskMoney}</a></span>&nbsp&nbsp
				                                                                      <span class="comments"><a href="#">${item.description}</a></span>&nbsp&nbsp
				                                                              </div><!-- end of post meta -->
				
						                                                        </header>
						                                                         
																		<c:choose>  
																		    <c:when test="${fn:length(item.taskText) > 100}">  
																		       <p> ${fn:substring(item.taskText, 0, 100)}<a class="readmore-link" href="<%=basePath%>task/taskDetail?taskId=${item.taskId }">...[更多]</a></p> 
																		    </c:when>  
																		   <c:otherwise>  
																		      <p>${item.taskText}  <a class="readmore-link" href="#"></a></p> 
																		    </c:otherwise>  
																		</c:choose>  
							                                             <form method="post" action="<%=basePath %>/task/submitTask" enctype="multipart/form-data">
																				<input type="file" name="file" multiple="multiple">
																				<input type="hidden" name="taskId" value="${item.taskId}" /> 
																				<input type="submit" value="提交">
																		</form>
																		
																		</c:forEach>
																	</ul>
																	<p>${unfinishmsg }</p>
																	<p style="color: red;">${subError}</p>
                                                                </div>
                                                        </article>

                                                        <article class="faq-item">
                                                                <span class="faq-icon"></span>
                                                                <h3 class="faq-question">
                                                                        <a href="#">已提交任务</a>
                                                                </h3>
                                                                <div class="faq-answer">
                                                                <ul>
																		<c:forEach items="${finishList}" var="item">
																		 <header class="clearfix">

				                                                              <h5 class="post-title">
				                                                                      <a href="<%=basePath%>task/taskDetail?taskId=${item.taskId }">${item.taskName}</a>&nbsp
				                                                              </h5>
				
				                                                              <div class="post-meta clearfix">
				                                                                      <span class="date">${item.finishTime}</span>&nbsp&nbsp
				                                                                      <span class="category"><a href="#">${item.taskMoney}</a></span>&nbsp&nbsp
				                                                                      <span class="comments"><a href="#">${item.description}</a></span>&nbsp&nbsp
				                                                              </div><!-- end of post meta -->
				
						                                                        </header>
						                                                         
																		<c:choose>  
																		    <c:when test="${fn:length(item.taskText) > 100}">  
																		       <p> ${fn:substring(item.taskText, 0, 100)}<a class="readmore-link" href="<%=basePath%>task/taskDetail?taskId=${item.taskId }">...[更多]</a></p> 
																		    </c:when>  
																		   <c:otherwise>  
																		      <p>${item.taskText}  <a class="readmore-link" href="#"></a></p> 
																		    </c:otherwise>  
																		</c:choose>  
							                                            
																		
																		</c:forEach>
																	</ul>
																	<p>${finishmsg }</p>
                                                                </div>
                                                        </article>

                                                        <article class="faq-item">
                                                                <span class="faq-icon"></span>
                                                                <h3 class="faq-question">
                                                                        <a href="#">已发布任务</a>
                                                                </h3>
                                                                <div class="faq-answer" style="display: none;">
                                                                <ul>
																		<c:forEach items="${uploadList}" var="item">
																		 <header class="clearfix">

				                                                              <h5 class="post-title">
				                                                                     <a href="<%=basePath%>task/taskDetail?taskId=${item.taskId }">${item.taskName}</a>
				                                                                     <div align="right">
																					<a href="<%=basePath %>/task/checkAccept?taskId=${item.taskId}" style="color: blue;">查看翻译</a>
				                                                                      <a href="javaScript:deleteTask('${item.taskId }')">撤回任务</a>
				                                                                      <c:if test="${item.schedule == 100}">
																						<a href="<%=basePath %>/task/adoptBigTrans?taskId=${item.taskId}" style="color: red">采纳</a>
																					  </c:if>
																					</div>
				                                                              </h5>
				
				                                                              <div class="post-meta clearfix">
				                                                                      <span class="date">${item.finishTime}</span>&nbsp&nbsp
				                                                                      <span class="category"><a href="#">${item.taskMoney}</a></span>&nbsp&nbsp
				                                                                      <span class="comments"><a href="#">${item.description}</a></span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
				                                                                      <span class="comments"><a href="#">完成率：${item.schedule}%</a></span>
				                                                              </div><!-- end of post meta -->
				
						                                                        </header>
						                                                         
																		<c:choose>  
																		    <c:when test="${fn:length(item.taskText) > 100}">  
																		       <p> ${fn:substring(item.taskText, 0, 100)}<a class="readmore-link" href="<%=basePath%>task/taskDetail?taskId=${item.taskId }">...[更多]</a></p> 
																		    </c:when>  
																		   <c:otherwise>  
																		      <p>${item.taskText}  <a class="readmore-link" href="#"></a></p> 
																		    </c:otherwise>  
																		</c:choose>  
							                                              <%--   <li>
		                                                                       <h4><a href="<%=basePath%>task/taskDetail?taskId=${item.taskId }">${item.taskName }</a></h4>
		                                                                       <span class="article-meta">${item.startTime}<a href="#">&nbsp;&nbsp;&nbsp;&nbsp;${item.description}</a></span>
		                                                                       <span class="like-count">${item.taskMoney}</span>
                                                            			   </li> --%>
																		
																				
																		</c:forEach>
																	</ul>
																	<p>${uploadmsg }</p>
                                                                </div>
                                                        </article>
 														<article class="faq-item">
                                                                <span class="faq-icon"></span>
                                                                <h3 class="faq-question">
                                                                        <a href="#">已完成翻译</a>
                                                                </h3>
                                                                <div class="faq-answer" style="display: none;">
                                                                <ul>
																		<c:forEach items="${exitList}" var="item">
																		 <header class="clearfix">

				                                                              <h5 class="post-title">
				                                                                     <a href="<%=basePath%>task/taskDetail?taskId=${item.taskId }">${item.taskName}</a>
				                                                                     <div align="right">
																					</div>
				                                                              </h5>
				
				                                                              <div class="post-meta clearfix">
				                                                                      <span class="date">${item.finishTime}</span>&nbsp&nbsp
				                                                                      <span class="category"><a href="#">${item.taskMoney}</a></span>&nbsp&nbsp
				                                                                      <span class="comments"><a href="#">${item.description}</a></span>&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp&nbsp
				                                                                      <span class="comments"><a href="#">完成率：${item.schedule}%</a></span>
				                                                              </div><!-- end of post meta -->
				
						                                                        </header>
						                                                         
																		<c:choose>  
																		    <c:when test="${fn:length(item.taskText) > 100}">  
																		       <p> ${fn:substring(item.taskText, 0, 100)}<a class="readmore-link" href="<%=basePath%>task/taskDetail?taskId=${item.taskId }">...[更多]</a></p> 
																		    </c:when>  
																		   <c:otherwise>  
																		      <p>${item.taskText}  <a class="readmore-link" href="#"></a></p> 
																		    </c:otherwise>  
																		</c:choose>  
																		</c:forEach>
																	</ul>
																	<div style="text-align: center;"><p>${msg }</p></div>
                                                                </div>
                                                        </article>
                                                        <article class="faq-item">
                                                                <span class="faq-icon"></span>
                                                                <h3 class="faq-question">
                                                                        <a href="#">个人信息</a>
                                                                </h3>
                                                                <div class="faq-answer">
																<form:form  action="${pageContext.servletContext.contextPath}/user/updateUser" method="post" commandName="user" enctype="multipart/form-data">
                                                               	  <input type="hidden" name="userId" value="${user.userId }" />
                                                               	  <div >
                                                               	  <lable>头像：</lable>
																		<c:choose>
																			<c:when test="${ empty user.headImage}">
																				<div data-trigger="fileinput" style='width: 200px; height: 150px;'>
																					<img class="headImage" src="<%=path %>/images/aaa.PNG"/>
																					
																				</div>
																			</c:when>
																			<c:otherwise>
																				<img class="headImage" src="${user.headImage }" alt=""/>
																			</c:otherwise>
																		</c:choose>
																		
																		</div>
																	
														                <div>
														                    <label>用户帐号:</label>
														                    <form:input path='account'  data-rule-required='true' disabled="true"/>
														                </div>
														                <div>
														                    <label>用户名:</label>
														                    <form:input path="username"  data-rule-required="true"/>
														                </div>
													               	 <div>
													                    <label>邮箱:</label>
													                    <form:input path="email" />
													                </div>
													                <div>
													                <label>兴趣:</label>
													                  <input type="checkbox" name="hobby"  value="历史" <c:if test="${fn:contains(user.hobby, '历史')}">checked="checked"</c:if>/> <span>历史</span>
													                    <input type="checkbox" name="hobby"  value="政治" <c:if test="${fn:contains(user.hobby, '政治')}">checked="checked"</c:if>/> <span>政治</span>
													                    <input type="checkbox" name="hobby"  value="小说" <c:if test="${fn:contains(user.hobby, '小说')}">checked="checked"</c:if>/> <span>小说</span>
													                     <input type="checkbox" name="hobby"  value="校园" <c:if test="${fn:contains(user.hobby, '校园')}">checked="checked"</c:if>/> <span>校园</span>
													                      <input type="checkbox" name="hobby"  value="商业" <c:if test="${fn:contains(user.hobby, '商业')}">checked="checked"</c:if>/> <span>商业</span>
													                    <input type="checkbox" name="hobby"  value="人文" <c:if test="${fn:contains(user.hobby, '人文')}">checked="checked"</c:if>/> <span>人文</span>
													                	 <%-- <form:checkbox path="hobby" value="历史"/>历史
													                	 <form:checkbox path="hobby" value="政治"/>政治
													                	 <form:checkbox path="hobby" value="小说"/>小说
													                	 <form:checkbox path="hobby" value="校园"/>校园
													                	 <form:checkbox path="hobby" value="商业"/>商业
													                	 <form:checkbox path="hobby" value="人文"/>人文 --%>
													                </div>
													                <div>
													                    <label>电话号码:</label>
													                    <form:input path="phoneNumber" />
													                </div>
													                <div>
													                    <label>用户角色:</label>
													                    <form:input path="role"  disabled="true"/>
													                </div>
													                <div>
													                    <label>翻译作品数:</label>
													                     <form:input path="transNum"  disabled="true"/>
													                </div>
													                <div>
													                    <label>翻译字数:</label>
													                     <form:input path="wordNum"  disabled="true"/>
													                </div>
													                <div>
													                    <label>被采纳次数:</label>
													                    <form:input path="adoptNum" disabled="true"/>
													                </div>
													                <div class="button">
													                    <input type="submit" value="保存" />
													                </div>
													                </form:form>
                                                                </div>
                                                        </article>

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
                                                 <section class="widget">
                                                        <h3 class="title">推荐任务</h3>
                                                        <ul class="articles">
	                                                       <c:forEach items="${pushList}" var="item">
                                                               <li class="article-entry standard">
                                                                       <h4><a href="<%=basePath%>task/taskDetail?taskId=${item.taskId }">${item.taskName }</a></h4>
                                                                       <span class="article-meta">${item.startTime}<a href="#" title="View all posts in Server &amp; Database">&nbsp;&nbsp;&nbsp;&nbsp;${item.description}</a></span>
                                                                       <span class="like-count">${item.taskMoney}</span>
                                                               </li>
															</c:forEach>                                                                
                                                        </ul>
                                                </section>


                                                

                                                <section class="widget">
                                                        <h3 class="title">分类</h3>
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
<script>
//领取任务
function cancelAccept(taskId) {
	
		bootbox.confirm("确定撤销该任务吗？", function(r){
        	if(r){
        		//删除
				$.post(
			    	"${pageContext.servletContext.contextPath}/task/cancelAccept",
			    	{
			    		"taskId":taskId,
			    	},	
				    function(data) {	       
				       if(data=="success"){
				       		bootbox.alert('撤销任务成功！');
				       		window.location.reload(true);
				       }
				       else if(data=="false"){
				       		bootbox.alert('撤销任务失败！') ;
				       }
				    }
				);		
        	}   
    });   
}

function deleteTask(taskId) {
	
	bootbox.confirm("确定撤回该任务吗？", function(r){
    	if(r){
    		//删除
			$.post(
		    	"${pageContext.servletContext.contextPath}/task/deleteTask",
		    	{
		    		"taskId":taskId,
		    	},	
			    function(data) {	       
			       if(data=="success"){
			       		bootbox.alert('撤回任务成功！');
			       		window.location.reload(true);
			       }
			       else if(data=="false"){
			       		bootbox.alert('撤回任务失败！') ;
			       }
			    }
			);		
    	}   
});   
}
</script>
               

        </body>
</html>