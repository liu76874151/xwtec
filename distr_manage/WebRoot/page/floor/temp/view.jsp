<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@page import="java.net.URLDecoder"%>
<%@include file="../../taglibs.jsp"%>
<html>
<head>

<title>查看模版</title>
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
	src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
<script type="text/javascript"
	src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="${contextPath}/resource/scripts/main.js"></script>
<script type="text/javascript"
	src="${contextPath}/resource/scripts/uomp/floor/temp/edit.js"></script>

</head>
<body style="overflow: auto;">
	<input type="hidden" id="tempNum" name="tempNum" value="${param.tempNum}"/> 
	<div id="main_div" style="padding:10px;">

		<div class="breadcrumb">
			<span></span>货架模版--查看
		</div>
				<form name="addForm" id="addForm" action="" method="post">
					<table class="tb" style="position:relative;">
						<tr>
							<th align="right">模版名称：</th>
							<td class="form_table_content2"><input type="text" class="form_input" maxlength="50" name="tempName"  id="tempName"  disabled="disabled"/></td>
						</tr>
						<tr>
							<th align="right">渠道：</th>
							<td class="form_table_content2">
								<select name="channelNum" id="channelNum" disabled="disabled">
									<option value="">-请选择-</option>
									<option value="01">网厅</option>
									<option value="0202">掌厅标准版</option>
									<option value="0203">掌厅触屏版</option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">示意图：</th>
							<td class="form_table_content2">
								<img id="tempImg" alt="示意图" src="">
							</td>
						</tr>
						<tr>
							<th align="right">区块信息编码：</th>
							<td class="form_table_content2"><input type="text"
								class="form_input" maxlength="50" name="blockNums" id="blockNums"  disabled="disabled"/>
							</td>
						</tr>
						<tr>
							<th align="right">组件选择：</th>
							<td class="form_table_content2">
								<select id="floorComp" name="floorComp" disabled="disabled">
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">启停状态：</th>
							<td class="form_table_content2">
								<input type="radio" name="state" value="0"  disabled="disabled"/>启用
								&nbsp;
								<input type="radio" name="state" value="1" disabled="disabled"/>停用
							</textarea>
							</td>
						</tr>
					</table>
				</form>

	</div>
	<script>

	</script>
</body>
</html>