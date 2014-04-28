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

		<title>品牌信息-查看</title>

		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css"
			href="${contextPath}/resource/css/frame.css" />
		<script type="text/javascript"
			src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
		<script type="text/javascript"
			src="${contextPath}/resource/scripts/main.js"></script>
		<script type="text/javascript" src="${contextPath}/resource/scripts/uomp/brand/brand_view.js"></script>
	</head>

	<body>
		<input type="hidden" id="pkid" value="${param.pkid}">
		<div style="padding: 10px; overflow: auto;">
			<div class="breadcrumb">
				<span></span>品牌信息--查看
			</div>
			<!-- 展示信息内容区域开始 -->
			<table width="96%" cellspacing="1" class="form_table">
			
				<tr>
					<td class="view_info_title" style="width: 20%">
						品牌编码：
					</td>
					<td class="view_info_content" style="width: 80%" id="brandNum"></td>
				</tr>
				<tr>
					<td class="view_info_title" style="width: 20%">
						品牌名称：
					</td>
					<td class="view_info_content" style="width: 80%" id="brandName"></td>
				</tr>
				<tr>
					<td class="view_info_title" style="width: 20%">
						级别编码：
					</td>
					<td class="view_info_content" style="width: 80%" id="jbNum"></td>
				</tr>
				<tr>
					<td class="view_info_title" style="width: 20%">
						级别：
					</td>
					<td class="view_info_content" style="width: 80%" id="jb"></td>
				</tr>
				<tr>
					<td class="view_info_title" style="width: 20%">
						是否末级：
					</td>
					<td class="view_info_content" style="width: 80%" id="mj"></td>
				</tr>
				<tr>
					<td class="view_info_title" style="width: 20%">
						备注：
					</td>
					<td class="view_info_content" style="width: 80%" id="memo"></td>
				</tr>
				<tr>
					<td class="view_info_title" style="width: 20%">
						对应的BOSS编码：
					</td>
					<td class="view_info_content" style="width: 80%" id="bossCode"></td>
				</tr>
			</table>
			<!--详细信息区 结束-->
			</div>
	</body>
</html>
