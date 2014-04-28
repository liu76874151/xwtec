<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="../../taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>网厅个性信息</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
    
	    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxcommon.js"></script>
	    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.js"></script>
	    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgridcell.js"></script>
	    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/dhtmlxtoolbar.js"></script>
	    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxGrid_ext_pgn.js"></script>
	    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/ext/dhtmlxgrid_ssc.js"></script>
	    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
	    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.json-2.4.min.js"></script>
	    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
	    <script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
	    <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/buesiness/businessinfo/businessInfo_add_w.js"></script>
	</head>
  <body>
	<table class="tb">
		<tr>
			<th width="10%" align="right">业务分类：</th>
			<td class="form_table_content">
				<span id="businessTypeList_01"></span><input id="busiTypeBt_01" type="button" value="选择">
			<span class="errorMsg"></span> </td>
		</tr>
		<tr>
	  			<th width="10%" align="right">图标路径：</th>
			<td class="form_table_content">
			<input type="file" name="files_01"/><span class="errorMsg"></span><br>
			<input type="text" id="busiIcon_01" name="busiIcon_01" value="" class="form_input" maxlength="1000">
			<span class="errorMsg"></span></td>
		</tr>
		<tr>
			<th width="10%" align="right">业务体验嵌套地址：</th>
			<td class="form_table_content"><input type="text" class="form_input" name="busiNestFt_01" value="" id="busiNestFt_01" />
			<span class="errorMsg"></span> </td>
		</tr>
		<tr>
			<th width="10%" align="right">备注：</th>
			<td class="form_table_content">
			<textarea rows="2" cols="30" id="bz_01" name="bz_01"></textarea>
			<span class="errorMsg"></span> </td>
		</tr>
		<tr>
			<th width="10%" align="right">页面地址：</th>
			<td class="form_table_content"><input type="text" class="form_input" maxlength="1000" name="busiPicUrl_01" value="" id="busiPicUrl_01" />
			<span class="errorMsg"></span> </td>
		</tr>
		<tr>
			<th width="10%" align="right">关联业务：</th>
			<td class="form_table_content">
				<span id="businessReleList_01"></span><input id="busiReleBt_01" type="button" value="选择">
			<span class="errorMsg"></span> </td>
		</tr>
		<tr style="display: none;">
			<th width="10%" align="right">城市：</th>
			<td class="form_table_content" id="releCityList_01">
			<span id="cityList01"></span><input id="areasBt_01" type="button" value="选择">
			<span class="errorMsg"></span> </td>
		</tr>
		<tr style="display: none;">
			<th width="10%" align="right">品牌：</th>
			<td class="form_table_content" id="releBrandList_01">
			<span class="errorMsg"></span> </td>
		</tr>
		<tr>
			<th width="10%" align="right">业务有效期：</th>
			<td class="form_table_content">
				<input type="radio" name="dateTime_01" onclick="showTime();" style="display: " value="0">无<br/>
				<input type="radio" name="dateTime_01" checked="checked" onclick="showTime();" value="1">有<br/>
				<span  id="date_time_01">
				开始时间:<input id="startTime_01" name="startTime_01" type="text" value="" onfocus="initCalendar(this.id)"/><span class="errorMsg"></span>
				--结束时间:<input id="endTime_01" name="endTime_01" type="text" value="" onfocus="initCalendar(this.id)"/><span class="errorMsg"></span>
				</span>
			<span class="errorMsg"></span> </td>
		</tr>
	</table>
  </body>
</html>
