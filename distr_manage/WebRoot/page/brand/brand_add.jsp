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

		<title>品牌信息-添加</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css"	href="${contextPath}/resource/css/frame.css" />
		<script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
		<script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
		<script type="text/javascript"	src="${contextPath}/resource/scripts/main.js"></script>
		<script type="text/javascript" src="${contextPath}/resource/scripts/uomp/brand/brand_add.js"></script>
	</head>
  
  <body>
    <div style="padding: 10px; overflow: auto;">
		<div class="breadcrumb">
			<span></span>品牌信息--添加
		</div>
		<form name="addForm" id="addForm" action="" method="post">
		<table class="tb">
			<tr>
 	  			<th align="right" width="10%">一级品牌名称：</th>
				<td class="form_table_content">
				<select id="topBrandName">
					<option value="0">-请选择-</option>
					<option value="01">新增一级品牌</option>
				</select>
				<span class="errorMsg"></span></td>
 			</tr>
			<tr>
 	  			<th align="right">品牌编码：</th>
				<td class="form_table_content"><input type="text" class="form_input" maxlength="50" name="brandNum" value="" id="brandNum" /><span class="errorMsg"></span></td>
			</tr>
			<tr>
				<th align="right">品牌名称：</th>
				<td class="form_table_content"><input type="text" class="form_input" maxlength="50" name="brandName" value="" id="brandName" /> <span class="errorMsg"></span> </td>
 			</tr>
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
				<th align="right">备注：</th>
				<td class="form_table_content"><input type="text" class="form_input" maxlength="50" name="memo" value="" id="memo" /> </td>
 			</tr>
 			<tr>
 	  			<th align="right">BOSS编码：</th>
				<td class="form_table_content"><input type="text" class="form_input" maxlength="50" name="bossCode" value="" id="bossCode" /></td>
 			</tr>
		</table>
		<!-- 按钮操作区域开始 -->
		<table width="100%" height="40">
			<tr>
		    	<td align="center">
				<a href="javascript:void(0)" class="btn" onClick="component.saveForm();">确认提交</a>
				<a href="javascript:void(0)" class="btn" onClick="document.addForm.reset();">清除重填</a>
				</td>
			</tr>
   		</table>
   		<!-- 按钮操作区域结束 -->
		</form>	
	</div>
  </body>
</html>
