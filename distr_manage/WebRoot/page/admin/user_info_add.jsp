<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"   isELIgnored="false"   %>
<%@include file="../taglibs.jsp" %>
<%
String groupId = request.getParameter("groupId");
String areaNum = request.getParameter("areaNum");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>用户新增</title>
   	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
	
	<script language="javascript" type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
	<script language="javascript" type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
	<script language="javascript" type="text/javascript" src="${contextPath}/resource/scripts/uomp/admin/user_info_add.js"></script>
	<script language="javascript" type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
  </head>
  
  <body>
  	<input type="hidden" value="<%=groupId %>" name="groupId" id="groupId">
  	<input type="hidden" value="<%=areaNum %>" name="areaNum" id="areaNum">
  
  	<div id="main_div" style="padding:10px;overflow:auto;">
  		<div class="breadcrumb"><span></span>用户--添加</div>
	  		<form name="addForm" id="addForm">
	    		<!-- 展示信息内容区域开始 -->
	    		<table class="tb">
	  	  			<tr>
	  	  				<th align="right"><span class="required">*</span>登陆名称：</th>
						<td class="form_table_content"><input type="text" class="form_input" maxlength="50" id="loginName" name="loginName" value="" /><span class="errorMsg"></span></td>
						<th align="right"><span class="required">*</span>登陆密码：</th>
						<td class="form_table_content"><input type="password" class="form_input" maxlength="50" id="loginPwd" name="loginPwd" value="" /><span class="errorMsg"></span></td>
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
							<select name="areaName" id="areaName" onchange="setF_boss_code(this)">
								
							</select>
						</td>
						<th align="right">用户状态：</th>
						<td class="form_table_content">
							<input type="radio" name="userState" value="1" />已授权
					  		<input type="radio" name="userState" value="0" checked />未授权
						</td>
		 			</tr>
		 			<tr>
	  	   				<th align="right">渠道：</th>
		   				<td class="form_table_content" colspan="3" >
		   					<select name="channelNum" id="channelNum">
		   						<option value="">-请选择-</option>
		   						<option value="01">网厅</option>
		   						<option value="02">掌厅</option>
		   						<option value="03">短厅</option>
		   					</select>
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
						  <a href="javascript:void(0)" class="btn" onclick="userInfoVComponent.groupInfoAddSubmit();">确认提交</a>
						  <a href="javascript:void(0)" class="btn" onclick="document.addForm.reset();">清除重填</a>
						</td>
		  			</tr>
        		</table>
        		<!-- 按钮操作区域结束 -->
        		<!-- 所选地市的boss_code -->
        		<input type="hidden" name="userAreaCode" id="userAreaCode" value=""/>
      		</form>
		</div>
  </body>
</html>
