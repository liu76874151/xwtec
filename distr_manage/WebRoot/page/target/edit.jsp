<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@page import="java.net.URLDecoder"%>
<%@include file="../taglibs.jsp"%>
<html>
<head>

<title>新增用户目标组</title>
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
	src="${contextPath}/resource/scripts/uomp/target/edit.js"></script>

</head>
<body style="overflow: auto;">

	<div id="main_div" style="padding:10px;">
		<input type="hidden" id="groupId" name="groupId" value="${param.groupId}">
		<div class="breadcrumb">
			<span></span>目标组织--修改
		</div>
				<form name="addForm" id="addForm"  action="${contextPath}/actionDispatcher.do?reqUrl=target&reqMethod=fileUpload" method="post" 
            target="hidIframe" enctype="multipart/form-data">
					<table class="tb" style="position:relative;">
						<tr>
							<th align="right" width="20%">组织名称：</th>
							<td class="form_table_content"><input type="text" id="groupName" name="groupName" class="form_input" maxlength="50"  value=""/></td>
						</tr>
						<tr>
							<th align="right">组织描述：</th>
							<td class="form_table_content">
								<textarea rows="5" cols="" class="form_text"  id="description" name="description"></textarea>
							</td>
						</tr>
						<tr>
							<th align="right">地市：</th>
							<td class="form_table_content">
								<select id="city">
									<option>-请选择地市-</option>
								</select>
							</td>
						</tr>
						<!-- 
						<tr>
							<th align="right">营销案类型：</th>
							<td class="form_table_content">
								<select id="groupType">
									<option>-请选择类型-</option>
									<option value="1">-用于一般营销案-</option>
									<option value="2">-用于预约营销案-</option>
								</select>
							</td>
						</tr>
						 -->
						<tr>
							<th align="right">有效期：</th>
							<td class="form_table_content">
								<table>
									<tr>
										<td>开始时间</td><td>&nbsp;<input type="text" class="form_input"  name="beginTime" id="beginTime" readonly="readonly" value=""></td>
										<td>结束时间</td><td>&nbsp;<input type="text" class="form_input" name="beginTime" id="endTime" readonly="readonly" value=""></td>
									</tr>
								</table>
							</td>
						</tr>
					</table>
					
					<iframe name="hidIframe" id="hidIframe" style="display:none;" ></iframe>
					<!-- 按钮操作区域开始 -->
					<table width="100%" height="40">
						<tr>
							<td align="center"><a href="javascript:void(0)" class="btn"
								onClick="component.updateGroup();">确认提交</a> <a
								href="javascript:void(0)" class="btn"
								onClick="document.addForm.reset();">清除重填</a></td>
						</tr>
					</table>
					<!-- 按钮操作区域结束 -->
				</form>

	</div>
	<script>

	</script>
</body>
</html>