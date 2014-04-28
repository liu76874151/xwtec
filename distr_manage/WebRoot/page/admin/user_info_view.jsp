<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"   isELIgnored="false"   %>
<%@include file="../taglibs.jsp" %>
<%
String loginName = request.getParameter("loginName");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>用户查看</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
	<script language="javascript" type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
	<script language="javascript" type="text/javascript" src="${contextPath}/resource/scripts/uomp/admin/user_info_view.js"></script>
	<script language="javascript" type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
  </head>
  
  <body>
  	<input type="hidden" value="<%=loginName %>" name="loginName" id="loginName">
  
  	<div style="padding:10px;overflow:auto;">
  	  	<div class="breadcrumb"><span></span>用户信息--查看</div>
	  		<!-- 展示信息内容区域开始 -->
	  		<table width="96%" cellspacing="1" class="form_table">
	          	<tr> 
	            	<td class="view_info_title" style="width:20%">登陆名称：</td>
	            	<td class="view_info_content" id="loginNameVal" name="loginNameVal" style="width:80%"></td>
	          	</tr>
			  	<tr> 
	            	<td class="view_info_title" >用户名称：</td>
	            	<td class="view_info_content" id="userName" name="userName"></td>
	          	</tr>
	          	<tr> 
	            	<td class="view_info_title" >用户手机号：</td>
	            	<td class="view_info_content" id="phone" name="phone"></td>
	          	</tr>
			  	<tr> 
	            	<td class="view_info_title" >用户所属地市：</td>
	            	<td class="view_info_content" id="areaName" name="areaName"></td>
	          	</tr>
			  	<tr> 
	            	<td class="view_info_title">登陆密码：</td>
	            	<td class="view_info_content" id="loginPwd" name="loginPwd"></td>
	          	</tr>
			  	<tr> 
	            	<td class="view_info_title" >用户所属组别：</td>
	            	<td class="view_info_content" id="userGroup" name="userGroup"></td>
	          	</tr>
			  	<tr> 
	            	<td class="view_info_title">用户状态：</td>
	            	<td class="view_info_content" id="userState" name="userState"></td>
	          	</tr>
			  	<tr> 
	            	<td class="view_info_title">备注：</td>
	            	<td class="view_info_content" id="bz" name="bz"></td>
	          	</tr>
			</table>
	   	<!--详细信息区 结束-->
 		</div>
  </body>
</html>
