<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <%@include file="/common/header.jsp"%>
    <title>用户管理</title>
    <script type="text/javascript" src="${basePath }js/datepicker/WdatePicker.js"></script>
</head>
<body class="rightBody">
<form id="form" name="form" action="${basePath }user_edit.action" method="post" enctype="multipart/form-data">
    <div class="p_d_1">
        <div class="p_d_1_1">
            <div class="content_info">
    <div class="c_crumbs"><div><b></b><strong>用户管理</strong>&nbsp;-&nbsp;编辑用户</div></div>
    <div class="tableH2">编辑用户</div>
<table id="baseInfo" width="100%" align="center" class="list" border="0" cellpadding="0" cellspacing="0"  >
    	<tr>
            <td class="tdBg" width="200px">用户名：</td>
            <td><s:textfield name="user.name"/> </td>
        </tr>
        <tr>
            <td class="tdBg" width="200px">帐号：</td>
            <td><s:textfield name="user.account" /></td>
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
                <s:if test="%{user.headImage != null && user.headImage != ''}">
                    <img src="${basePath }upload/<s:property value='user.headImage'/>" width="100" height="100"/>
                    <s:hidden name="user.headImage"/>
                </s:if>
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
    <!-- 这里需要传入一个id，才可以对数据库进行修改 -->
    <s:hidden name="user.id"/>
    <div class="tc mt20">
        <input type="submit" class="btnB2" value="保存" />
        &nbsp;&nbsp;&nbsp;&nbsp;
        <input type="button"  onclick="javascript:history.go(-1)" class="btnB2" value="返回" />
    </div>
    </div></div></div>
</form>
</body>
</html>