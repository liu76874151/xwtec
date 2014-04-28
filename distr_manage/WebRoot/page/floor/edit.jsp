<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false"%>
<%@page import="java.net.URLDecoder"%>
<%@include file="../taglibs.jsp"%>
<html>
<head>

<title>修改楼层</title>
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
	src="${contextPath}/resource/scripts/uomp/floor/edit.js"></script>

</head>
<body style="overflow: auto;">

	<div id="main_div" style="padding:10px;">
		<input type="hidden" id="floorId" value="${param.pkid}"/>
		<div class="breadcrumb">
			<span></span>货架--修改
		</div>
				<form name="editForm" id="editForm" action="${contextPath}/actionDispatcher.do?reqUrl=floor&reqMethod=filesuploadReName" method="post" 
            target="hidIframe" enctype="multipart/form-data">
             <input name="fileNames" id="fileNames" type="hidden">
			 <input name="contentIds" id="contentIds" type="hidden">	
					<table class="tb" style="position:relative;">
						<tr>
							<th align="right">货架名称：</th>
							<td class="form_table_content2"><input type="text" class="form_input" maxlength="50" name="floorName"  id="floorName" /></td>
						</tr>
						<tr>
							<th align="right">渠道：</th>
							<td class="form_table_content2">
								<select name="channelNum" id="channelNum" onchange="component.queryTemplateByChannel();">
									<option value="">-请选择-</option>
									<option value="01">网厅</option>
									<option value="0202">掌厅标准版</option>
									<option value="0203">掌厅触屏版</option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">货架模版：</th>
							<td class="form_table_content2">
								<select id="tempNum" name="tempNum" onchange="component.getTempImg(this.options[this.selectedIndex].value);">
									<option>-请先选择渠道-</option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">示意图：</th>
							<td class="form_table_content2" id="tempImg">
								请选择货架模版
							</td>
						</tr>
						<tr>
							<th align="right">货架楼层：</th>
							<td class="form_table_content2">
								<select id="floorNum" name="floorNum">
									<option value="01">1</option>
									<option value="02">2</option>
									<option value="03">3</option>
									<option value="04">4</option>
									<option value="05">5</option>
									<option value="06">6</option>
									<option value="07">7</option>
									<option value="08">8</option>
									<option value="09">9</option>
									<option value="10">10</option>
								</select>
							</td>
						</tr>
						<tr>
							<th align="right">货架描述：</th>
							<td class="form_table_content2">
								<input type="text"   class="form_input"  id="floorBz" name="floorBz"/>
							</td>
						</tr>
					</table>
					<!-- 区块信息 -->
						<div id="blockInfos">
						</div>
					
					<!-- 按钮操作区域开始 -->
					<table width="100%" height="40">
						<tr>
							<td align="center"><a href="javascript:void(0)" class="btn"
								onClick="component.updateFloor();">确认提交</a> </td>
						</tr>
					</table>
					<!-- 按钮操作区域结束 -->
				</form>

	</div>
	<iframe name="hidIframe" id="hidIframe" style="display:none;" ></iframe>
</body>
</html>