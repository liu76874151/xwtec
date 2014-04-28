<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"   isELIgnored="false"   %>
<%@include file="../taglibs.jsp" %>
<%
String groupId = request.getParameter("groupId");
String areaNum = request.getParameter("areaNum");
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>用户列表</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.css"/>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/skins/dhtmlxgrid_dhx_skyblue.css"/>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/skins/dhtmlxtoolbar_dhx_skyblue.css"></link>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxWindows/codebase/dhtmlxwindows.css">
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxWindows/codebase/skins/dhtmlxwindows_dhx_skyblue.css">
	<script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/main.js" ></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxcommon.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxcommon.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgridcell.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxTreeGrid/codebase/dhtmlxtreegrid.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/dhtmlxtoolbar.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxWindows/codebase/dhtmlxcontainer.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxWindows/codebase/dhtmlxwindows.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/uomp/admin/user_info_list.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxGrid_ext_pgn.js"></script>	
		<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/ext/dhtmlxgrid_ssc.js"></script>
  <body>
  	<input type="hidden" value="<%=groupId %>" name="groupId" id="groupId">
  	<input type="hidden" value="<%=areaNum %>" name="areaNum" id="areaNum">
  	
  	<div title="" style="padding:10px;overflow:auto;">
		<div class="breadcrumb"><span></span>用户信息--查询</div>
		<fieldset class="defaultFieldset">
			<legend>用户查询</legend>
			<form id="queryForm" name='queryForm' action="" method="post">
     		<table width="98%" class="defaultTable tableStyle1">
       			<tr> 
      				<th width="20%">管理员名称：</th>
      				<td width="30%"><input type="text" maxlength="100" id="userName" name="userName" value=""><span class="errorMsg"></span></td>
  	  				<td width="50%" style="text-align:center;">
	        			<a href="javascript:void(0)" class="btn" onclick="userInfoListComponent.initQuery();">查 询</a>
	        			<a href="javascript:void(0)" class="btn"  onclick="document.queryForm.reset();">重 置</a>
         			</td>
    			</tr>
			</table>
			</form>
		</fieldset>
		
		<!-- 操作按钮 -->
		<div id="toolbarObj"></div>
	
		<!-- 用户列表 -->
		<div id="gridbox" style="width:600px;height:270px;background-color:white;width: 100%"></div>
		<div id="paging"></div>
	</div>
  </body>
</html>
