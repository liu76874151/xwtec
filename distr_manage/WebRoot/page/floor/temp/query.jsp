<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="../../taglibs.jsp"%>
<html>
<head>
<title>楼层模版管理</title>
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
	src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxCalendar/codebase/dhtmlxcalendar.js"></script>
<script type="text/javascript"
	src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
<script type="text/javascript"
	src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="${contextPath}/resource/scripts/main.js"></script>
<script type="text/javascript"
	src="${contextPath}/resource/scripts/uomp/floor/temp/query.js"></script>
<script type="text/javascript"
	src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/excells/dhtmlxgrid_excell_link.js"></script>
</head>
<body>
	<div title="" style="padding:10px;overflow:auto;">
		<fieldset class="defaultFieldset">
			<legend>楼层模版查询</legend>
			<div style="width: 100%"></div>

			<form id="queryForm" name='queryForm' action="" method="post">
				<div></div>
				<table width="100%" class="defaultTable tableStyle1" border="1px">
					<tr>
						<th width="15%">启用状态：</th>
						<td width="15%"><input type="radio" name="state" value="0"/>启用&nbsp;<input type="radio" name="state" value="1"/>停用&nbsp;<input type="radio" name="state" value="" checked="checked"/>全部</td>
						<th width="15%">渠道：</th>
						<td width="15%">
							<select name="channelNum" id="channelNum">
								<option value="">全部</option>
								<option value="01">网厅</option>
								<option value="0202">掌厅标准版</option>
								<option value="0203">掌厅触屏版</option>
							</select>
						</td>
						<td colspan="8" style="text-align:center;" width="100%"><a
							href="javascript:void(0)" class="btn"
							onclick="component.query();">查 询</a></td>
					</tr>
				</table>
			</form>
		</fieldset>
		<div id="toolbar"></div>
		<div id="gridbox"
			style="width:600px;height:400px;background-color:white;width: 100%"></div>

		<div id="paging"></div>
	</div>
</body>
</html>