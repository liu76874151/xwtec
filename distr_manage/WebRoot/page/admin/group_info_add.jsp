<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"   isELIgnored="false"   %>
<%@include file="../taglibs.jsp" %>
<%
String groupId = request.getParameter("groupId");
String areaNum = request.getParameter("areaNum");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>用户组新增</title>
    
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
	
	<script language="javascript" type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
	<script language="javascript" type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
	<script language="javascript" type="text/javascript" src="${contextPath}/resource/scripts/uomp/admin/group_info_add.js"></script>
	<script language="javascript" type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
  </head>
  
  <body>
  <!-- 隐藏域 -->
  <input type="hidden" value="<%=groupId %>" name="groupId" id="groupId">
  <input type="hidden" value="<%=areaNum %>" name="areaNum" id="areaNum">
  
  <input type="hidden" value="" name="jbNum" id="jbNum">
  <input type="hidden" value="" name="jb" id="jb">
  
  <div id="main_div" style="padding:10px;overflow:auto;">
  		<div class="breadcrumb"><span></span>用户组--添加</div>
	  		<form name="addForm" id="addForm">
	    		<!-- 展示信息内容区域开始 -->
	    		<table class="tb">
	  	  			<tr>
	  	  				<th align="right"><span class="required">*</span>用户组编码：</th>
						<td class="form_table_content"><input type="text" class="form_input" maxlength="50" id="groupIdVal" name="groupIdVal" value="" /><span class="errorMsg"></span></td>
						<th align="right"><span class="required">*</span>用户组名称：</th>
						<td class="form_table_content"><input type="text" class="form_input" maxlength="50" id="groupName" name="groupName" value="" /><span class="errorMsg"></span> </td>
		 			</tr>
		 			<tr>
						<th align="right">用户组所属城市：</th>
						<td class="form_table_content">
							<select name="groupArea" id="groupArea">
								
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
						  <a href="javascript:void(0)" class="btn" onclick="groupInfoVComponent.groupInfoAddSubmit()">确认提交</a>
						  <a href="javascript:void(0)" class="btn" onclick="document.addForm.reset();">清除重填</a>
						</td>
		  			</tr>
        		</table>
        		<!-- 按钮操作区域结束 -->
      		</form>
		</div>
  </body>
</html>
