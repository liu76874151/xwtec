<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="../taglibs.jsp"%>
<html>
<head>
<title>营业厅管理</title>
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
	src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/ext/dhtmlxgrid_filter.js"></script>
<script type="text/javascript"
	src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/ext/dhtmlxgrid_mcol.js"></script>
<script type="text/javascript"
	src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
<script type="text/javascript"
	src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
<script type="text/javascript"
	src="${contextPath}/resource/scripts/main.js"></script>
<script type="text/javascript"
	src="${contextPath}/resource/scripts/uomp/org/query.js"></script>
<script type="text/javascript"
	src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/excells/dhtmlxgrid_excell_link.js"></script>
</head>
<body>
	<div title="" style="padding:10px;overflow:auto;">
		<fieldset class="defaultFieldset">
			<legend>营业厅查询</legend>
			<div style="width: 100%"></div>

			<form id="queryForm" name='queryForm' action="" method="post">
				<div></div>
				<table width="100%" class="defaultTable tableStyle1" border="1px">
					<tr>
						<th width="15%">营业厅名称：</th>
						<td width="×%"><input type="text" name="orgName" id="orgName"/></td>
						<th width="8%">状态：</th>
						<td width="2-%"><select name="state" id="state">
								<option value="">-所有状态-</option>
								<option value="1">可用</option>
								<option value="0">不可用</option>
						</select>
						</td>
						<th width="8%">地市：</th>
						<td width="×%"><select name="cityId" id="cityId" onchange="component.initZoneInfo()">
								<option value="">-所有地市-</option>
								
						</select></td>
						<th width="8%">区县：</th>
						<td width="2-%"><select name="zoneId" id="zoneId">
								<option value="">-所有区县-</option>
								
						</select>
						</td>
						<td colspan="8" style="text-align:center;" width="100%"><a
							href="javascript:void(0)" class="btn"
							onclick="component.query();">查 询</a> <a href="javascript:void(0)"
							class="btn" onclick="document.queryForm.reset();">重 置</a></td>
					</tr>
				</table>
			</form>
		</fieldset>
		<div id="toolbar"></div>
		<div id="gridbox"
			style="width:600px;height:270px;background-color:white;width: 100%"></div>

		<div id="paging"></div>
	</div>
</body>
</html>