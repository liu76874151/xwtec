<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"   isELIgnored="false"   %>
<%@include file="../taglibs.jsp" %>
<%
String groupId = request.getParameter("groupId");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>用户组查看</title>
    
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
	<script language="javascript" type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
	<script language="javascript" type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
	<script language="javascript" type="text/javascript" src="${contextPath}/resource/scripts/uomp/admin/group_info_view.js"></script>
  </head>
  
  <body>
  <input type="hidden" value="<%=groupId %>" name="groupId" id="groupId">
  <div style="padding:10px;overflow:auto;">
  	  	<div class="breadcrumb"><span></span>用户信息--查看</div>
  		<!-- 展示信息内容区域开始 -->
  		<table width="96%" cellspacing="1" class="form_table">
          	<tr> 
            	<td class="view_info_title" style="width:20%">用户组编码：</td>
            	<td class="view_info_content" id="groupIdVal" name="groupIdVal" style="width:80%"></td>
          	</tr>
		  	<tr> 
            	<td class="view_info_title" >用户组名：</td>
            	<td class="view_info_content" id="groupName" name="groupName"></td>
          	</tr>
          	<tr> 
            	<td class="view_info_title" >级别编码：</td>
            	<td class="view_info_content" id="jbNum" name="jbNum"></td>
          	</tr>
		  	<tr> 
            	<td class="view_info_title" >用户组所属城市：</td>
            	<td class="view_info_content" id="groupArea" name="groupArea"></td>
          	</tr>
		  	<tr> 
            	<td class="view_info_title" >是否末级（是）：</td>
            	<td class="view_info_content" id="mj" name="mj"></td>
          	</tr>
		  	<tr> 
            	<td class="view_info_title">级别：</td>
            	<td class="view_info_content" id="jb" name="jb"></td>
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
