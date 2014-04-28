<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@include file="../../taglibs.jsp"%>
<html>
<head>
<title>楼层方案管理</title>
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
	src="${contextPath}/resource/scripts/uomp/floor/plan/query.js"></script>
<script type="text/javascript"
	src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/excells/dhtmlxgrid_excell_link.js"></script>
</head>
<body>
	<div title="" style="padding:10px;overflow:auto;">
		<fieldset class="defaultFieldset">
			<legend>方案查询</legend>
			<div style="width: 100%"></div>

			<form id="queryForm" name='queryForm' action="" method="post">
				<div></div>
				<table width="100%" class="defaultTable tableStyle1" border="1px">
					<tr>
						
						<th width="10%">渠道：</th>
						<td width="×%"><select name="channelNum" id="channelNum">
								<option value="">-所有渠道-</option>
								<option value="01">网厅</option>
								<option value="0203">掌厅-触屏版</option>
								<option value="0202">掌厅-标准版</option>
						</select></td>
						<th width="8%">是否可用：</th>
						<td width="2-%"><select name="state" id="state">
								<option value="">-所有状态-</option>
								<option value="0">可用</option>
								<option value="1">不可用</option>
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