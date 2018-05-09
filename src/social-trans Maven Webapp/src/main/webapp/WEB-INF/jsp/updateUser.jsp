<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core"  prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html>
<html lang="en">
<head>
	<meta charset="UTF-8">
	<title>用户信息</title>
	<link rel="stylesheet" type="text/css" href="<%=path %>/css/personal-info.css">
</head>
<body>  
	<div class="content">
	<form id="userInfo" action="<%=basePath %>user/updateUser" method="post"  enctype="multipart/form-data">
		<div class="img">
		<c:choose>
			<c:when test="${ empty user.headImage}">
				<div data-trigger="fileinput" style="width: 200px; height: 150px;">
					<img class="headImage" src="<%=path %>/img/aaa.PNG" alt=""/>
					<input type="file" name="headImage" />
				</div>
			</c:when>
			<c:otherwise>
				<img class="headImage" src="${user.headImage }" alt=""/>
			</c:otherwise>
		</c:choose>
		</div>
		<div class="line"></div>
		
		<div class="personal-info">
			<h4 class="personal-title">用户信息</h4>

                <tr>
                <td>
                <div>
                    <label>用户帐号:</label>
                    <input type="text" value="${user.account}" name="account" disabled/>
                </div>
                </td>
                <td>
                <div>
                    <label>用户名:</label>
                    <input type="text" name="username" value="${user.username}" />
                </div>
                </td>
                </tr>
                <div>
                    <label>邮箱:</label>
                    <input type="text" name="email" value="${user.email}"/>
                </div>
                <div>
                <label>兴趣:</label>
                    <input type="checkbox" name="hobby"  value="历史" <c:if test="${fn:contains(user.hobby, '历史')}">checked="checked"</c:if>/> <span>历史</span>
                    <input type="checkbox" name="hobby"  value="政治" <c:if test="${fn:contains(user.hobby, '政治')}">checked="checked"</c:if>/> <span>政治</span>
                    <input type="checkbox" name="hobby"  value="地理" <c:if test="${fn:contains(user.hobby, '地理')}">checked="checked"</c:if>/> <span>地理</span>
                    <input type="checkbox" name="hobby"  value="人文" <c:if test="${fn:contains(user.hobby, '人文')}">checked="checked"</c:if>/> <span>人文</span>
                </div>
                <div>
                    <label>电话号码:</label>
                    <input type="text" name="phoneNumber" value="${user.phoneNumber}"/>
                </div>
                <div>
                    <label>用户角色:</label>
                    <input type="text" name="role"  value="${user.role}" disabled/>
                </div>
                <div>
                    <label>翻译作品数:</label>
                    <input type="text" value="${user.transNum}" name="transNum" disabled/>
                </div>
                <div>
                    <label>翻译字数:</label>
                    <input type="text" value="${user.wordNum}" name="wordNum" disabled/>
                </div>
                <div>
                    <label>被采纳次数:</label>
                    <input type="text" value="${user.adoptNum}" name="adoptNum" disabled/>
                </div>
                <div class="button">
                    <input type="submit" value="保存" />
                </div>
                </form>
		</div>
	</div>
</body>
</html>