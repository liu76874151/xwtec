<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%@include file="../../taglibs.jsp"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<title>关联业务选择</title>
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="cache-control" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
		<meta http-equiv="description" content="This is my page">
		<link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
		<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxPopup/codebase/skins/dhtmlxpopup_dhx_skyblue.css"/>
	    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxcommon.js"></script>
		<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxPopup/codebase/dhtmlxpopup.js"></script>
	    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
	    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.json-2.4.min.js"></script>
	    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
	    <script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
	    <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/buesiness/businessinfo/relaBusinessInfo_query.js"></script>
	</head>
  <input type="hidden" id="channelNum" name="channelNum" value="${param.channelNum }">
  <body style="overflow: auto;">
    <div style="padding: 10px; overflow: auto;">
		<div class="breadcrumb">
			<span></span>关联业务选择
		</div>
		<form name="addForm" id="addForm" action="" method="post">
		<table class="tb" id="tb1">
			<tr>
				<th width="10%">业务名称:</th>
				<td height="50">
					&nbsp;&nbsp;<input type="text" id="busiInfoName" name="busiInfoName"><a href="javascript:void(0)" class="btn" onClick="component.queryBusiInfo(0,500,'${param.channelNum }');">查&nbsp;询</a>
				</td>
			</tr>
			<tr>
				<th width="10%"></th>
				<td class="form_table_content">
					<table class="tb" align="center">
						<tr>
							<td height="50">所有业务:
							</td>
							<td>&nbsp;</td>
							<td height="50">已选业务:</td>
						</tr>
						<tr height="100">
							<td height="50">
								<select id="busiInfoList" size="18" style="width: 210px;" multiple="multiple">
								</select>
							</td>
							<td align="center">
								<input type="button" style="width: 50px;" value=">>" id="" onclick="component.addBusiInfo('${param.channelNum }');"><br>
								<input type="button" style="width: 50px;" value="<<" id=""  onclick="component.removeBusiInfo('${param.channelNum }');">
							</td>
							<td>
								<select id="selectBusiInfo" size="18" style="width: 210px;">
								</select>
							</td>
						</tr>						
					</table>
				</td>
 			</tr>
		</table>
		
		<!-- 按钮操作区域开始 -->
		<table width="100%" height="40">
			<tr>
		    	<td align="center">
				<a href="javascript:void(0)" class="btn" onClick="component.saveForm('${param.channelNum }');">确认</a>
				</td>
			</tr>
   		</table>
   		<!-- 按钮操作区域结束 -->
		</form>	
	</div>
  </body>
</html>
