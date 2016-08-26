<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>用户管理</title>
    <!-- 日期格式设置插件 -->
    <script type="text/javascript" src="${basePath }js/datepicker/WdatePicker.js"></script>
    <script type="text/javascript">
    	//帐号是否唯一
    	var result=false;
    
    	//校验帐号的唯一性
    	function doVerify(){
    		//1、获取帐号
    		var account = $("#account").val();
    		if(account != ""){
    			//2、校验 
    			$.ajax({
    				url:"${basePath}user_verifyAccount.action",
    				data: {"user.account": account},
    				type: "post",
    				async: false,//设置为非异步，不然result的逻辑执行不了
    				success: function(msg){
    					if("true" != msg){
    						//帐号已经存在
    						alert("帐号已经存在。请使用其它帐号！");
    						//定焦
    						$("#account").focus();
    						result=false;
    					} else{
    						result=true;
    					}
    				}
    			});
    		}
    	}
    	
    	
    	function doSubmit(){
    		doVerify();
    		if(result){
    			document.forms[0].submit();
    		}
    	
    	}
    </script>
</head>
<body class="rightBody">
<form id="form" name="form" action="${basePath }user_add.action" method="post" enctype="multipart/form-data">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong>用户管理</strong>&nbsp;-&nbsp;新增用户</div></div>
    <div class="tableH2">新增用户</div>
    <table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
    	<tr>
            <td class="tdBg" width="200px">用户名：</td>
            <td><s:textfield name="user.name"/> </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">帐号：</td>
            <td><s:textfield id="account" name="user.account" onchange="doVerify()"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">密码：</td>
            <td><s:textfield name="user.password"/></td>
        </tr>
         <tr>
            <td class="tdBg" width="200px">性别：</td>
            <td><s:radio list="#{'true':'男','false':'女'}" name="user.gender"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">头像：</td>
            <td>
                <input type="file" name="headImage"/>
            </td>
        </tr>
           <tr>
            <td class="tdBg" width="200px">角色：</td>
            <td></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">电子邮箱：</td>
            <td><s:textfield name="user.email"/></td>
        </tr>
         <tr>
            <td class="tdBg" width="200px">手机号：</td>
            <td><s:textfield name="user.mobile"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">真实姓名：</td>
            <td><s:textfield name="user.realName"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">身份证：</td>
            <td><s:textfield name="user.identityCard"/></td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">银行卡：</td>
            <td><s:textfield name="user.bankCard"/></td>
        </tr>
        
       
     
        
       
     
    </table>
    <div class="tc mt20">
        <input type=button class="btnB2" value="保存" onclick="doSubmit()"/>
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button"  onclick="javascript:history.go(-1)" class="btnB2" value="返回" />
    </div>
    </div></div></div>
</form>
</body>
</html>