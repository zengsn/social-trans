<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!doctype html>
<html class="no-js">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title></title>
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
<div>
	
    <div class="listbiaoti am-cf">
      <ul class="am-icon-flag on"> 任务管理</ul>
      <dl class="am-icon-home" style="float: right;">当前位置： 首页 > <a href="#">任务管理</a></dl>
    </div>
    
    
    
      <div class="am-tabs am-margin" data-am-tabs>
    <ul class="am-tabs-nav am-nav am-nav-tabs">
      <li class="am-active"><a href="#tab1">站内消息 /留言</a></li>
      <li><a href="#tab2">短信</a></li>
      <li><a href="#tab3">邮件</a></li>
      <li><a href="#tab4">微信</a></li>
      <li><a href="#tab5">客服</a></li>
    </ul>





    <div class="am-tabs-bd">
      <div class="am-tab-panel am-fade am-in am-active" id="tab1">
        <div class="am-g am-margin-top">
          <div class="am-u-sm-4 am-u-md-2 am-text-right">所属类别</div>
          <div class="am-u-sm-8 am-u-md-10">
            <select data-am-selected="{btnSize: 'sm'}">
              <option value="option1">选项一...</option>
              <option value="option2">选项二.....</option>
              <option value="option3">选项三........</option>
            </select>
          </div>
        </div>

        <div class="am-g am-margin-top">
          <div class="am-u-sm-4 am-u-md-2 am-text-right">显示状态</div>
          <div class="am-u-sm-8 am-u-md-10">
            <div class="am-btn-group" data-am-button>
              <label class="am-btn am-btn-default am-btn-xs">
                <input type="radio" name="options" id="option1"> 正常
              </label>
              <label class="am-btn am-btn-default am-btn-xs">
                <input type="radio" name="options" id="option2"> 待审核
              </label>
              <label class="am-btn am-btn-default am-btn-xs">
                <input type="radio" name="options" id="option3"> 不显示
              </label>
            </div>
          </div>
        </div>

        <div class="am-g am-margin-top">
          <div class="am-u-sm-4 am-u-md-2 am-text-right">推荐类型</div>
          <div class="am-u-sm-8 am-u-md-10">
            <div class="am-btn-group" data-am-button>
              <label class="am-btn am-btn-default am-btn-xs">
                <input type="checkbox"> 允许评论
              </label>
              <label class="am-btn am-btn-default am-btn-xs">
                <input type="checkbox"> 置顶
              </label>
              <label class="am-btn am-btn-default am-btn-xs">
                <input type="checkbox"> 推荐
              </label>
              <label class="am-btn am-btn-default am-btn-xs">
                <input type="checkbox"> 热门
              </label>
              <label class="am-btn am-btn-default am-btn-xs">
                <input type="checkbox"> 轮播图
              </label>
            </div>
          </div>
        </div>

        <div class="am-g am-margin-top">
          <div class="am-u-sm-4 am-u-md-2 am-text-right">
            发布时间
          </div>
          <div class="am-u-sm-8 am-u-md-10">
            <form action="" class="am-form am-form-inline">
              <div class="am-form-group am-form-icon">
                <i class="am-icon-calendar"></i>
                <input type="text" class="am-form-field am-input-sm" placeholder="时间">
              </div>
            </form>
          </div>
        </div>

      </div>

      <div class="am-tab-panel am-fade" id="tab2">
        <form class="am-form">
          <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">
              文章标题
            </div>
            <div class="am-u-sm-8 am-u-md-4">
              <input type="text" class="am-input-sm">
            </div>
            <div class="am-hide-sm-only am-u-md-6">*必填，不可重复</div>
          </div>

          <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">
              文章作者
            </div>
            <div class="am-u-sm-8 am-u-md-4 am-u-end col-end">
              <input type="text" class="am-input-sm">
            </div>
          </div>

          <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">
              信息来源
            </div>
            <div class="am-u-sm-8 am-u-md-4">
              <input type="text" class="am-input-sm">
            </div>
            <div class="am-hide-sm-only am-u-md-6">选填</div>
          </div>

          <div class="am-g am-margin-top">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">
              内容摘要
            </div>
            <div class="am-u-sm-8 am-u-md-4">
              <input type="text" class="am-input-sm">
            </div>
            <div class="am-u-sm-12 am-u-md-6">不填写则自动截取内容前255字符</div>
          </div>

          <div class="am-g am-margin-top-sm">
            <div class="am-u-sm-12 am-u-md-2 am-text-right admin-form-text">
              内容描述
            </div>
            <div class="am-u-sm-12 am-u-md-10">
              <textarea rows="10" placeholder="请使用富文本编辑插件"></textarea>
            </div>
          </div>

        </form>
      </div>

      <div class="am-tab-panel am-fade" id="tab3">
        <form class="am-form">
          


<div class="xitong">
    
<div class="am-alert am-alert-success" data-am-alert>

  <p>发件箱设置（站内所有邮件均由此邮箱发送，如会员密码找回邮件等）</p>
</div>
    	
    
              <div class="am-form-group">
          <div class="zuo">发件人：</div>
          <div class="you" style="max-width: 300px;">
            <input type="email" class="am-input-sm" id="doc-ipt-email-1" placeholder="请输入标题">
          </div>
        </div>
        
                 <div class="am-form-group">
          <div class="zuo">邮箱账号：</div>
          <div class="you" style="max-width: 300px;">
            <input type="email" class="am-input-sm" id="doc-ipt-email-1" placeholder="请输入标题">
          </div>
        </div>
        
        
                      <div class="am-form-group">
          <div class="zuo">邮箱密码：</div>
          <div class="you" style="max-width: 300px;">
            <input type="email" class="am-input-sm" id="doc-ipt-email-1" placeholder="请输入标题">
          </div>
        </div>
        
        
                      <div class="am-form-group">
          <div class="zuo">SMTP：</div>
          <div class="you" style="max-width: 300px;">
            <input type="email" class="am-input-sm" id="doc-ipt-email-1" placeholder="请输入标题">
          </div>
        </div>
        
            <div class="am-form-group">
          <div class="zuo">发送端口：</div>
          <div class="you" style="max-width: 300px;">
            <input type="email" class="am-input-sm" id="doc-ipt-email-1" placeholder="请输入标题">
          </div>
        </div>
        
        
          <div class="am-form-group">
          <div class="zuo">发送方式：</div>
          <div class="you" style="margin-top: 4px;">
          <label class="am-radio-inline">
        <input type="radio"  value="" name="docInlineRadio"> SSL服务方式
      </label>
      <label class="am-radio-inline">
        <input type="radio" name="docInlineRadio"> TLS服务方式
      </label>

          </div>
        </div>


   <div class="am-form-group">
          <div class="zuo"></div>
          <div class="you" style="margin-top: 4px;">
         测试发送状态
         <br /><br />
          <button type="button" class="am-btn am-btn-success  am-radius am-btn-sm">保存选择</button>
          </div>
        </div>


   
      
      

      
      

      
      
      
      
      
    </div>



        </form>
      </div>
      
      <div class="am-tab-panel am-fade" id="tab4">
        <form class="am-form">
          <div class="am-g am-margin-top-sm">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">
              SEO 标题
            </div>
            <div class="am-u-sm-8 am-u-md-4 am-u-end">
              <input type="text" class="am-input-sm">
            </div>
          </div>

          <div class="am-g am-margin-top-sm">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">
              SEO 关键字
            </div>
            <div class="am-u-sm-8 am-u-md-4 am-u-end">
              <input type="text" class="am-input-sm">
            </div>
          </div>

          <div class="am-g am-margin-top-sm">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">
              SEO 描述
            </div>
            <div class="am-u-sm-8 am-u-md-4 am-u-end">
              <textarea rows="4"></textarea>
            </div>
          </div>
        </form>
      </div>
      
      
      <div class="am-tab-panel am-fade" id="tab5">
        <form class="am-form">
          <div class="am-g am-margin-top-sm">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">
              SEO 标题
            </div>
            <div class="am-u-sm-8 am-u-md-4 am-u-end">
              <input type="text" class="am-input-sm">
            </div>
          </div>

          <div class="am-g am-margin-top-sm">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">
              SEO 关键字
            </div>
            <div class="am-u-sm-8 am-u-md-4 am-u-end">
              <input type="text" class="am-input-sm">
            </div>
          </div>

          <div class="am-g am-margin-top-sm">
            <div class="am-u-sm-4 am-u-md-2 am-text-right">
              SEO 描述
            </div>
            <div class="am-u-sm-8 am-u-md-4 am-u-end">
              <textarea rows="4"></textarea>
            </div>
          </div>
        </form>
      </div>
      
      
      

    </div>
  </div>

  <div class="am-margin">
    <button type="button" class="am-btn am-btn-success am-radius ">提交保存</button>
    <button type="button" class="am-btn am-btn-primary am-radius ">放弃保存</button>
  </div>
  
    
    
    
    
    
    
    
	
	


 
 <div class="foods">
  <ul>
    版权所有@2015
  </ul>
  <dl>
    <a href="" title="返回头部" class="am-icon-btn am-icon-arrow-up"></a>
  </dl>
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