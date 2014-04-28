<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@page import="java.net.URLDecoder"%>
<%@include file="../taglibs.jsp"%>
<html>
<head>

<title>导入用户目标组</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<link rel="stylesheet" type="text/css"
	href="${contextPath}/resource/css/frame.css" />
<link rel="stylesheet" type="text/css"
	href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.css"></link>
<link rel="stylesheet" type="text/css"
	href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/skins/dhtmlxgrid_dhx_skyblue.css"></link>
<link rel="stylesheet" type="text/css"
	href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/skins/dhtmlxtoolbar_dhx_skyblue.css"></link>


<link rel="stylesheet" type="text/css"
	href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCalendar/codebase/dhtmlxcalendar.css">
<link rel="stylesheet" type="text/css"
	href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCalendar/codebase/skins/dhtmlxcalendar_dhx_skyblue.css">
<link rel="stylesheet" type="text/css"
	href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxForm/codebase/skins/dhtmlxform_dhx_skyblue.css">
<script
	src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCalendar/codebase/dhtmlxcalendar.js"></script>
<link rel="stylesheet" type="text/css"
	href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/dhtmlxtabbar.css" />
<script type="text/javascript"
	src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/dhtmlxcommon.js"></script>
<script type="text/javascript"
	src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/dhtmlxtabbar.js"></script>
<script type="text/javascript"
	src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/dhtmlxtabbar_start.js"></script>

<script type="text/javascript"
	src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxcommon.js"></script>
<script type="text/javascript"
	src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.js"></script>
<script type="text/javascript"
	src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgridcell.js"></script>
<script type="text/javascript"
	src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/dhtmlxtoolbar.js"></script>
<script type="text/javascript"
	src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxGrid_ext_pgn.js"></script>
<script type="text/javascript"
	src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/ext/dhtmlxgrid_ssc.js"></script>
<script type="text/javascript"
	src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxForm/codebase/dhtmlxform.js"></script>
<script type="text/javascript"
	src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
<script type="text/javascript" src="${contextPath}/resource/scripts/jquery.json-2.4.min.js"></script>	
<script type="text/javascript"
	src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="${contextPath}/resource/scripts/main.js"></script>
<script type="text/javascript"
	src="${contextPath}/resource/scripts/uomp/target/importData.js"></script>

</head>
<body style="overflow: auto;">

	<div id="main_div" style="padding:10px;">

		<div class="breadcrumb">
			<span></span>目标组织--批量导入用户号码
		</div>
				<form name="addForm" id="addForm"  action="${contextPath}/actionDispatcher.do?reqUrl=target&reqMethod=fileUpload" method="post" 
            target="hidIframe" enctype="multipart/form-data">
            		<input type="hidden" id="groupId" name="groupId" value="${param.groupId}"/>
					<table class="tb" style="position:relative;">
						<tr>
							<th align="right" width="20%">组织名称：</th>
							<td class="form_table_content"><input type="text" id="groupName" name="groupName" disabled="disabled" readonly="readonly" class="form_input" maxlength="50"  value=""/></td>
						</tr>
						<tr>
							<th align="right">所在地市：</th>
							<td class="form_table_content"><input type="text" id="areaName" name="areaName" disabled="disabled" readonly="readonly" class="form_input" maxlength="50"  value=""/></td>
							<input type="hidden" id="city" name="city" class="form_input" maxlength="50"  value=""/>
						</tr>
						<tr>
							<th align="right">查询手机号码：</th>
							<td class="form_table_content"><input type="text" id="phoneNumber" name="phoneNumber" class="form_input" maxlength="50"  value=""/>&nbsp;<a href="javascript:void(0)" class="btn"
								onClick="component.queryPhoneNumber();">查询</a></td>
						</tr>
						<tr>
							<th align="right">导入文件：</th>
							<td class="form_table_content"><input type="file" id="positionImage" name="file" /></td>
						</tr>
						
					</table>
					
					<!-- 按钮操作区域开始 -->
					<table width="100%" height="40">
						<tr>
							<td align="center"><a href="javascript:void(0)" class="btn"
								onClick="component.uploadFile();">确认提交</a> 
									<a
								href="javascript:void(0)" class="btn"
								onClick="top.UOMPDialog.subPageCallback&& top.UOMPDialog.subPageCallback();">关闭</a></td>
						</tr>
					</table>
					<!-- 按钮操作区域结束 -->
				</form>
	<iframe name="hidIframe" id="hidIframe" style="display:none;" ></iframe>
	</div>

	<div class="breadcrumb">
		<span></span>目标组织--添加单个号码
	</div>
	<table class="tb" style="position:relative;">
		<tr>
			<th align="right" width="20%">组织名称：</th>
			<td class="form_table_content"><input type="text" id="groupName_sig" name="groupName_sig" disabled="disabled" readonly="readonly" class="form_input" maxlength="50"  value=""/></td>
		</tr>
		<tr>
			<th align="right" width="20%">手机号码：</th>
			<td class="form_table_content"><input type="text" id="phoneNumber_add" name="phoneNumber_add"  class="form_input" maxlength="11"  value=""/></td>
		</tr>
	</table>
	<!-- 按钮操作区域开始 -->
	<table width="100%" height="40">
		<tr>
			<td align="center"><a href="javascript:void(0)" class="btn"
				onClick="component.addPhoneNumber();">确认提交</a> <a
				href="javascript:void(0)" class="btn"
				onClick="top.UOMPDialog.subPageCallback&& top.UOMPDialog.subPageCallback();">关闭</a>
			</td>
		</tr>
	</table>
	<!-- 按钮操作区域结束 -->
	<div class="breadcrumb">
		<span></span>目标组织--清空号码
	</div>
	<table width="100%" height="40">
						<tr>
							<td align="center">
 <a href="javascript:void(0)" class="btn"
								onClick="component.deletePhoneNumber();">清空号码</a><a
								href="javascript:void(0)" class="btn"
								onClick="top.UOMPDialog.subPageCallback&& top.UOMPDialog.subPageCallback();">关闭</a></td>
						</tr>
					</table>
	<script>
		
	</script>
</body>
</html>