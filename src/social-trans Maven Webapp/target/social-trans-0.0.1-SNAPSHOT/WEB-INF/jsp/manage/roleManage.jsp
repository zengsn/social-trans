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
<title>后台管理</title>
<meta name="description" content="这是一个 index 页面">
<meta name="keywords" content="index">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="icon" type="image/png" href="<%=path%>/assets/i/favicon.png">
<link rel="apple-touch-icon-precomposed" href="<%=path%>/assets/i/app-icon72x72@2x.png">
<link rel="stylesheet" href="<%=path%>/assets/css/amazeui.min.css"/>
<link rel="stylesheet" href="<%=path%>/assets/css/admin.css">
<script src="<%=path%>/assets/js/jquery.min.js"></script>
<script src="<%=path%>/assets/js/app.js"></script>
</head>
<body>

<div class="am-popup am-popup-inner" id="my-popup">
	    <div class="am-popup-hd">
      <h4 class="am-popup-title">添加商品一级分类</h4>
      <span data-am-modal-close
            class="am-close">&times;</span>
    </div>
	
	    <div class="am-popup-bd">
      

      <form class="am-form tjlanmu" action="">

        <div class="am-form-group">
          <div class="zuo">角色名称：</div>
          <div class="you">
            <input type="text" name="rolename"class="am-input-sm" id="doc-ipt-email-1" placeholder="请输入角色名称">
          </div>
        </div>
        <div class="am-form-group am-cf">
          <div class="zuo">角色等级：</div>
          <div class="you" style="height: 45px;">
            <input type="number" name="rolelevel"id="doc-ipt-file-1">
          </div>
        </div>
        <div class="am-form-group am-cf">
          <div class="zuo">角色描述：</div>
          <div class="you">
            <textarea class="" rows="2" id="doc-ta-1"></textarea>
          </div>
        </div>
        <div class="am-form-group am-cf">
          <div class="you">
            <p>
              <button type="submit" class="am-btn am-btn-success am-radius">提交</button>
            </p>
          </div>
        </div>
      </form>
    </div>

</div>

	<div class="am-popup am-popup-inner" id="my-popups">
	
	    <div class="am-popup-hd">
      <h4 class="am-popup-title">修改角色</h4>
      <span data-am-modal-close
            class="am-close">&times;</span>
    </div>
	
	    <div class="am-popup-bd">
      

      <form class="am-form tjlanmu">
        <div class="am-form-group">
          <div class="zuo">角色名称：</div>
          <div class="you">
            <input type="text" class="am-input-sm" id="doc-ipt-email-1" placeholder="请输入角色名称">
          </div>
        </div>
        <div class="am-form-group">
          <div class="zuo">角色等级：</div>
          <div class="you">
            <input type="number" class="am-input-sm" id="doc-ipt-pwd-1" placeholder="请输入角色等级">
          </div>
        </div>
        <div class="am-form-group am-cf">
          <div class="zuo">角色描述：</div>
          <div class="you">
            <textarea class="" rows="2" id="doc-ta-1"></textarea>
          </div>
        </div>
        
        <div class="am-form-group am-cf">
          <div class="you">
            <p>
              <button type="submit" class="am-btn am-btn-success am-radius">提交</button>
            </p>
          </div>
        </div>
      </form>

    </div>

</div>

<div>
	
    <div class="listbiaoti am-cf">
      <ul class="am-icon-flag on"> 角色列表</ul>
    </div>

    <form class="am-form am-g">
          <table width="100%" class="am-table am-table-bordered am-table-radius am-table-striped am-table-hover">
            <thead>
              <tr class="am-success">
                <th class="table-check"><input type="checkbox" /></th>
                <th class="table-title">角色名称</th>
                <th class="table-type">角色等级</th>
                <th class="table-author am-hide-sm-only">角色描述</th>
                <th width="200px" class="table-set">操作</th>
              </tr>
            </thead>
            <tbody>
            <c:forEach items="${roleList }" var="item">
              <tr>
                <td><input type="checkbox" /></td>
                <td>${item.rolename}</td>
                <td>${item.rolelevel}</td>
                <td>${item.description }</td>
                
                <td>
                	
                	<div class="am-btn-toolbar">
                    <div class="am-btn-group am-btn-group-xs">
                      <button class="am-btn am-btn-default am-btn-xs am-text-success am-round"><span>查看</span> </button>
                      <button class="am-btn am-btn-default am-btn-xs am-text-secondary am-round" data-am-modal="{target: '#my-popups'}" title="修改订单"><span>修改</span></button>
                      <button class="am-btn am-btn-default am-btn-xs am-text-danger am-round" title="删除订单"><span>删除</span></button>
                    </div>
                  </div>
                
                </td>
              </tr>
              </c:forEach>
            </tbody>
          </table>
          
            <div class="am-btn-group am-btn-group-xs">
              <button type="button" class="am-btn am-btn-default"><span class="am-icon-plus"></span> 删除</button>
              <button type="button" class="am-btn am-btn-default"><span class="am-icon-plus"></span> 新增</button>
              <button type="button" class="am-btn am-btn-default"><span class="am-icon-save"></span> 保存</button>

            </div>
          <hr />
        </form>
 
 
 
 
 <div class="foods">
  <dl>
    <a href="" title="返回头部" class="am-icon-btn am-icon-arrow-up"></a>
  </dl>
</div>




</div>

</div>




</div>

<!--[if lt IE 9]>
<script src="http://libs.baidu.com/jquery/1.11.1/jquery.min.js"></script>
<script src="http://cdn.staticfile.org/modernizr/2.8.3/modernizr.js"></script>
<script src="assets/js/polyfill/rem.min.js"></script>
<script src="assets/js/polyfill/respond.min.js"></script>
<script src="assets/js/amazeui.legacy.js"></script>
<![endif]--> 

<!--[if (gte IE 9)|!(IE)]><!--> 
<script src="<%=path%>/assets/js/amazeui.min.js"></script>
<!--<![endif]-->



</body>
</html>
