<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="../taglibs.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">

		<title>品牌信息-修改</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css"	href="${contextPath}/resource/css/frame.css" />
		<script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
		<script type="text/javascript"	src="${contextPath}/resource/scripts/main.js"></script>
		<script type="text/javascript" src="${contextPath}/resource/scripts/uomp/brand/brand_modify.js"></script>
	</head>
  
  <body>
  	<input type="hidden" id="pkid" value="${param.pkid}">
    <div style="padding: 10px; overflow: auto;">
		<div class="breadcrumb">
			<span></span>品牌信息--修改
		</div>
		<form name="editForm" id="editForm" action="" method="post">
		<table class="tb">
			<tr>
				<th align="right" width="15%">品牌编码：</th>
				<td colspan="3" class="form_table_content"><input type="text" class="form_input" maxlength="50" name="brandNum" value="" id="brandNum" readonly="readonly" /><span class="errorMsg"></span> </td>
 			</tr>
			<tr>
				<th align="right">品牌名称：</th>
				<td colspan="3" class="form_table_content"><input type="text" class="form_input" maxlength="50" name="brandName" value="" id="brandName" /><span class="errorMsg"></span> </td>
 			</tr>
 			<tr>
 	  			<th align="right">级别代码：</th>
				<td class="form_table_content"><input type="text" class="form_input" maxlength="50" name="jbNum" value="" id="jbNum" disabled="disabled" /><span class="errorMsg"></span></td>
			</tr>
 			<%--<tr>
				<th align="right">级别：</th>
				<td class="form_table_content"><input type="text" class="form_input" maxlength="50" name="jb" value="" id="jb" /><span class="errorMsg"></span> </td>
 			</tr>
 			--%>
 			<tr>
 	  			<th align="right">是否末级：</th>
				<td class="form_table_content">
					<select id="mj" style="width: 50px;">
						<option value="0">否</option>
						<option value="1">是</option>
					</select>
				</td>
			</tr>
 			<tr>
				<th align="right">BOSS编码：</th>
				<td class="form_table_content"><input type="text" class="form_input" maxlength="50" name="bossCode" value="" id="bossCode" /><span class="errorMsg"></span></td>
 			</tr>
 			<tr>
 				<th align="right">备注：</th>
				<td class="form_table_content"><textarea rows="5" style="width: 400px;height: 100%" name="memo" value="" id="memo"></textarea><span class="errorMsg"></span> </td>
 			</tr>
		</table>
		<!-- 按钮操作区域开始 -->
		<table width="100%" height="40">
			<tr>
		    	<td align="center">
				<a href="javascript:void(0)" class="btn" onClick="component.saveForm();">确认提交</a>
				</td>
			</tr>
   		</table>
   		<!-- 按钮操作区域结束 -->
		</form>	
	</div>
  </body>
</html>
