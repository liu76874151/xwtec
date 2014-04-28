<%@ page language="java" pageEncoding="UTF-8" isELIgnored="false" %>
<%@include file="../../taglibs.jsp" %>

<html>
	<head>
		<title>扩展属性选择</title>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8"/>
		<link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
		<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxPopup/codebase/skins/dhtmlxpopup_dhx_skyblue.css"/>
		<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxlayout.css"/>
		<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/skins/dhtmlxlayout_dhx_skyblue.css"/>
		<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTabbar/codebase/dhtmlxtabbar.css"/>
		<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxAccordion/codebase/skins/dhtmlxaccordion_dhx_skyblue.css"/>
		<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTree/codebase/dhtmlxtree.css"/>
		<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/skins/dhtmlxtoolbar_dhx_skyblue.css"/>
		<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxWindows/codebase/dhtmlxwindows.css"/>	
		<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxWindows/codebase/skins/dhtmlxwindows_dhx_skyblue.css"/>
		
		
	    <script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxcommon.js"></script>
		<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxPopup/codebase/dhtmlxpopup.js"></script>
		
		<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxcontainer.js"></script>
		<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxlayout.js"></script>
		<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxWindows/codebase/dhtmlxwindows.js"></script>

	    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
	    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.json-2.4.min.js"></script>
	    <script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
	    <script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
	    <script type="text/javascript"src="${contextPath}/resource/scripts/uompDialog.js"></script>
	    <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/buesiness/businessinfo/businessInfo_extra.js"></script>
	</head>
  
  <body style="overflow: auto;">
  <input type="hidden" id="jbNum" name="jbNum" value="${param.jbNum }">
	<div title="" style="padding:10px;overflow:auto;">
    <div class="breadcrumb"><span></span>扩展属性选择</div>
        <form name="addForm" id="addForm" action="" method="post">
		<table class="tb" id="tb1">
		</table>
		
		<!-- 按钮操作区域开始 -->
		<table width="100%" height="40">
			<tr>
		    	<td align="center">
				<a href="javascript:void(0)" class="btn" onClick="component.saveForm();">确认</a>
				</td>
			</tr>
   		</table>
   		<!-- 按钮操作区域结束 -->
		</form>	
</div>
  </body>
</html>