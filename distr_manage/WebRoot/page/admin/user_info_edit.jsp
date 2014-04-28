<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"   isELIgnored="false"   %>
<%@include file="../taglibs.jsp" %>
<%
String loginName = request.getParameter("loginName");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>用户修改</title>
   	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
	
	<script language="javascript" type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
	<script language="javascript" type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
	<script language="javascript" type="text/javascript" src="${contextPath}/resource/scripts/uomp/admin/user_info_edit.js"></script>
	<script language="javascript" type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
  </head>
  
  <body>
  	<input type="hidden" value="<%=loginName %>" name="loginNameVal" id="loginNameVal">
  
  	<div id="main_div" style="padding:10px;overflow:auto;">
  		<div class="breadcrumb"><span></span>用户--修改</div>
	  		<form name="addForm" id="addForm">
	    		<!-- 展示信息内容区域开始 -->
	    		<table class="tb">
	  	  			<tr>
	  	  				<th align="right"><span class="required">*</span>登陆名称：</th>
						<td class="form_table_content"><input type="text" readonly="readonly" class="form_input" maxlength="50" id="loginName" name="loginName" value="" /></td>
						<th align="right"><span class="required">*</span>登陆密码：</th>
						<td class="form_table_content">
							<input type="password" class="form_input" maxlength="50" id="loginPwd" name="loginPwd" value="●●●●●●●●●●●●●" disabled="disabled"/><span class="errorMsg"></span>
							<input type="hidden" name="pwd" id="pwd" value=""/>
							<input type="checkbox" name="changePwd" id="changePwd" value="1" onclick="userInfoVComponent.boxChange()" />修改密码
						</td>
		 			</tr>
		 			<tr>
		 				<th align="right"><span class="required">*</span>用户名称：</th>
						<td class="form_table_content"><input type="text" class="form_input" maxlength="50" id="userName" name="userName" value="" /><span class="errorMsg"></span></td>
						<th align="right">用户手机号：</th>
						<td class="form_table_content"><input type="text" class="form_input" maxlength="50" id="phone" name="phone" value="" /></td>
		 			</tr>
		 			<tr>
	  	  				<th align="right">用户所属地市：</th>
						<td class="form_table_content">
							<input type="text" disabled="disabled" class="form_input" maxlength="50" id="areaName" name="areaName" value="" />
							<input type="hidden" name="userArea" id="userArea" value="" />
						</td>
						<th align="right">用户状态：</th>
						<td class="form_table_content">
							<input type="radio" name="userState" value="1" />已授权
					  		<input type="radio" name="userState" value="0" checked />未授权
						</td>
		 			</tr>
		 			<tr>
	  	   				<th align="right">备注：</th>
		   				<td class="form_table_content" colspan="3" ><textarea class="form_text" name="bz" id="bz"></textarea></td>
	  	 			</tr>
        		</table>
	   			<!-- 展示信息内容区域结束 -->
	    		<!-- 按钮操作区域开始 -->
				<table width="100%" height="40">
		  			<tr>
					    <td align="center">
						  <a href="javascript:void(0)" class="btn" onclick="userInfoVComponent.groupInfoEditSubmit();">确认提交</a>
						  <a href="javascript:void(0)" class="btn" onclick="document.addForm.reset();">清除重填</a>
						</td>
		  			</tr>
        		</table>
        		<!-- 按钮操作区域结束 -->
      		</form>
		</div>
  </body>
</html>
