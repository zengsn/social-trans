<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!doctype html>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>管理员后台管理</title>
<meta name="description" content="这是一个 管理页面">
<meta name="keywords" content="index">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="icon" type="image/png" href="<%=path%>/assets/i/favicon.png">
<link rel="apple-touch-icon-precomposed" href="<%=path%>/images/assets/i/app-icon72x72@2x.png">
<meta name="apple-mobile-web-app-title" content="Amaze UI" />
<link rel="stylesheet" href="<%=path%>/assets/css/amazeui.min.css"/>
<link rel="stylesheet" href="<%=path%>/assets/css/admin.css">
<script src="<%=path%>/assets/js/jquery.min.js"></script>
<script src="<%=path%>/assets/js/app.js"></script>
</head>
<body>
<!--[if lte IE 9]><p class="browsehappy">升级你的浏览器吧！ <a href="http://se.360.cn/" target="_blank">升级浏览器</a>以获得更好的体验！</p><![endif]-->






</head>

<body>
<header class="am-topbar admin-header">

  <div class="am-topbar-brand"><img src="<%=path%>/assets/images/logo.png"></div>

  <div class="am-collapse am-topbar-collapse" id="topbar-collapse">
  
  </div>
</header>

<div class="am-cf admin-main"> 

<div class="nav-navicon admin-main admin-sidebar">
    
    
    <div class="sideMenu am-icon-dashboard" style="color:#aeb2b7; margin: 10px 0 0 0;"> 欢迎系统管理员</div>
    <div class="sideMenu">
      <h3 class="am-icon-flag"><em></em> <a href="<%=basePath %>admin/selectAllUser" target="showTask">用户管理</a></h3>
  
      <h3 class="am-icon-cart-plus"><em></em> <a href="<%=basePath %>admin/selectAllRole" target="showTask"> 角色管理</a></h3>
    
      <h3 class="am-icon-users"><em></em> <a href="<%=basePath %>admin/selectAllTask"  target="showTask">任务管理</a></h3>
    
      <h3 class="am-icon-gears"><em></em> <a href="#"  target="showTask">系统设置</a></h3>
   
    </div>
    <!-- sideMenu End --> 
    
    <script type="text/javascript">
			jQuery(".sideMenu").slide({
				titCell:"h3", //鼠标触发对象
				targetCell:"ul", //与titCell一一对应，第n个titCell控制第n个targetCell的显示隐藏
				effect:"slideDown", //targetCell下拉效果
				delayTime:300 , //效果时间
				triggerTime:150, //鼠标延迟触发时间（默认150）
				defaultPlay:true,//默认是否执行效果（默认true）
				returnDefault:true //鼠标从.sideMen移走后返回默认状态（默认false）
				});
		</script> 

    
    
    
    
    
    
    
</div>

<div class=" admin-content">
		<div class="daohang">
</div>
	
	


<div class="admin">
	<iframe src="" frameborder="0" name="showTask" width="100%" height="100%"></iframe>
</div>

</div>

</div>
</div>
<script src="<%=path%>/assets/js/amazeui.min.js"></script>
</body>
</html>