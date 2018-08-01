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
<title>用户管理</title>
<meta name="description" content="这是一个 index 页面">
<meta name="keywords" content="index">
<meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="icon" type="image/png" href="<%=path%>/assets/i/favicon.png">
<link rel="apple-touch-icon-precomposed" href="<%=path%>/assets/i/app-icon72x72@2x.png">
<meta name="apple-mobile-web-app-title" content="Amaze UI" />
<link rel="stylesheet" href="<%=path%>/assets/css/amazeui.min.css"/>
<link rel="stylesheet" href="<%=path%>/assets/css/admin.css">
<script src="<%=path%>/assets/js/jquery.min.js"></script>
<script src="<%=path%>/assets/js/app.js"></script>
</head>
<body>
<div class=" admin-content">
<div class="am-popup am-popup-inner" id="my-popup">
	
	<div class="am-popup-hd">
      <h4 class="am-popup-title">添加商品一级分类</h4>
      <span data-am-modal-close
            class="am-close">&times;</span>
    </div>
	
	    <div class="am-popup-bd">
      

      <form class="am-form tjlanmu">


        <div class="am-form-group">
          <div class="zuo">栏目名称：</div>
          <div class="you">
            <input type="email" class="am-input-sm" id="doc-ipt-email-1" placeholder="请输入标题">
          </div>
        </div>
        <div class="am-form-group">
          <div class="zuo">栏目关键词：</div>
          <div class="you">
            <input type="password" class="am-input-sm" id="doc-ipt-pwd-1" placeholder="请输入关键词">
          </div>
        </div>
        <div class="am-form-group am-cf">
          <div class="zuo">栏目描述：</div>
          <div class="you">
            <textarea class="" rows="2" id="doc-ta-1"></textarea>
          </div>
        </div>
        <div class="am-form-group am-cf">
          <div class="zuo">栏目图片：</div>
          <div class="you" style="height: 45px;">
            <input type="file" id="doc-ipt-file-1">
            <p class="am-form-help">请选择要上传的文件...</p>
          </div>
        </div>
        <div class="am-form-group am-cf">
          <div class="zuo">简介：</div>
          <div class="you">
            <textarea class="" rows="2" id="doc-ta-1"></textarea>
          </div>
        </div>
        <div class="am-form-group am-cf">
        <div class="zuo">状态：</div>
        <div class="you" style="margin-top: 3px;">
          <label class="am-checkbox-inline">
            <input type="checkbox" value="option1">
            显示 </label>
          <label class="am-checkbox-inline">
            <input type="checkbox" value="option2">
            隐藏 </label>

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
      <h4 class="am-popup-title">修改栏目名称</h4>
      <span data-am-modal-close
            class="am-close">&times;</span>
    </div>
	
	    <div class="am-popup-bd">
      

      <form class="am-form tjlanmu">


        <div class="am-form-group">
          <div class="zuo">栏目名称：</div>
          <div class="you">
            <input type="email" class="am-input-sm" id="doc-ipt-email-1" placeholder="请输入标题">
          </div>
        </div>
        <div class="am-form-group">
          <div class="zuo">栏目关键词：</div>
          <div class="you">
            <input type="password" class="am-input-sm" id="doc-ipt-pwd-1" placeholder="请输入关键词">
          </div>
        </div>
        <div class="am-form-group am-cf">
          <div class="zuo">栏目描述：</div>
          <div class="you">
            <textarea class="" rows="2" id="doc-ta-1"></textarea>
          </div>
        </div>
        <div class="am-form-group am-cf">
          <div class="zuo">栏目图片：</div>
          <div class="you" style="height: 45px;">
            <input type="file" id="doc-ipt-file-1">
            <p class="am-form-help">请选择要上传的文件...</p>
          </div>
        </div>
        <div class="am-form-group am-cf">
          <div class="zuo">简介：</div>
          <div class="you">
            <textarea class="" rows="2" id="doc-ta-1"></textarea>
          </div>
        </div>
        <div class="am-form-group am-cf">
        <div class="zuo">状态：</div>
        <div class="you" style="margin-top: 3px;">
          <label class="am-checkbox-inline">
            <input type="checkbox" value="option1">
            显示 </label>
          <label class="am-checkbox-inline">
            <input type="checkbox" value="option2">
            隐藏 </label>

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
      <ul class="am-icon-users"> 会员管理</ul>
      
      <dl class="am-icon-home" style="float: right;">当前位置： 首页 > <a href="#">商品列表</a></dl>
      
      <dl>
        <button type="button" class="am-btn am-btn-danger am-round am-btn-xs am-icon-plus" > 手动添加会员</button>
      </dl>
    
    </div>
    <form class="am-form am-g">
          <table width="100%" class="am-table am-table-bordered am-table-radius am-table-striped">
            <thead>
              <tr class="am-success">
                <th class="table-check"><input type="checkbox" /></th>

                <th class="table-id">ID</th>
                <th class="table-title">会员名称</th>
                <th class="table-type">会员等级</th>
                <th class="table-author am-hide-sm-only">会员积分</th>
                <th class="table-author am-hide-sm-only">注册日期</th>
                <th class="table-date am-hide-sm-only">最近消费</th>
                <th width="130px" class="table-set">操作</th>
              </tr>
            </thead>
            <tbody>
              <tr>
                <td><input type="checkbox" /></td>
                
                <td>14</td>
                <td><a href="#">Business management</a></td>
                <td>3件 （消费455个积分）</td>
                <td class="am-hide-sm-only">访问</td><td class="am-hide-sm-only">访问</td>
                <td class="am-hide-sm-only">2014年9月4日 7:28:47</td>
                <td>
                	
                	
                      	
                	<div class="am-btn-toolbar">
                    <div class="am-btn-group am-btn-group-xs">
                      <button class="am-btn am-btn-default am-btn-xs am-text-success am-round"><span class="am-icon-search" title="查看订单详情"></span> </button>
                      <button class="am-btn am-btn-default am-btn-xs am-text-secondary am-round" data-am-modal="{target: '#my-popups'}" title="修改订单"><span class="am-icon-pencil-square-o"></span></button>
                      <button class="am-btn am-btn-default am-btn-xs am-text-danger am-round" title="删除订单"><span class="am-icon-trash-o" ></span></button>
                    </div>
                  </div>
                	
                	
                	
                	
                	
                	
                </td>
              </tr>
              <tr>
                <td><input type="checkbox" /></td>
                
                <td>15</td>
                <td><a href="#">Business management</a></td>
                <td>default</td>
                <td class="am-hide-sm-only"><i class="am-icon-close am-text-primary"></i></td><td class="am-hide-sm-only">访问</td>
                <td class="am-hide-sm-only">2014年9月4日 7:28:47</td>
                <td>
                	
                	<div class="am-btn-toolbar">
                    <div class="am-btn-group am-btn-group-xs">
                      <button class="am-btn am-btn-default am-btn-xs am-text-success am-round"><span class="am-icon-search" title="查看订单详情"></span> </button>
                      <button class="am-btn am-btn-default am-btn-xs am-text-secondary am-round" data-am-modal="{target: '#my-popups'}" title="修改订单"><span class="am-icon-pencil-square-o"></span></button>
                      <button class="am-btn am-btn-default am-btn-xs am-text-danger am-round" title="删除订单"><span class="am-icon-trash-o" ></span></button>
                    </div>
                  </div>
                
                </td>
              </tr>
            </tbody>
          </table>
          
                 <div class="am-btn-group am-btn-group-xs">
              <button type="button" class="am-btn am-btn-default"><span class="am-icon-plus"></span> 删除</button>
              <button type="button" class="am-btn am-btn-default"><span class="am-icon-save"></span> 上架</button>
              <button type="button" class="am-btn am-btn-default"><span class="am-icon-save"></span> 下架</button>
              <button type="button" class="am-btn am-btn-default"><span class="am-icon-save"></span> 移动</button>
              <button type="button" class="am-btn am-btn-default"><span class="am-icon-plus"></span> 新增</button>
              <button type="button" class="am-btn am-btn-default"><span class="am-icon-save"></span> 保存</button>
              <button type="button" class="am-btn am-btn-default"><span class="am-icon-archive"></span> 移动</button>
              <button type="button" class="am-btn am-btn-default"><span class="am-icon-trash-o"></span> 删除</button>
            </div>
          
          <ul class="am-pagination am-fr">
                <li class="am-disabled"><a href="#">«</a></li>
                <li class="am-active"><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
                <li><a href="#">»</a></li>
              </ul>
          
          
          
      
          <hr />
          
        </form>
</div>
</div>

<script src="<%=path%>/assets/js/amazeui.min.js"></script>
</body>
</html>