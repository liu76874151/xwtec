<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"   isELIgnored="false"   %>
<%@include file="../taglibs.jsp" %>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>子系统管理</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css" />
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.css"></link>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/skins/dhtmlxgrid_dhx_skyblue.css"></link>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/skins/dhtmlxtoolbar_dhx_skyblue.css"></link>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxcommon.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgridcell.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/dhtmlxtoolbar.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/jquery.validate.min.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/main.js"></script>
    <script type="text/javascript" src="${contextPath}/resource/scripts/uomp/admin/sub_system_query.js"></script>
	
	
  </head>
  
  <body>
  <br/>
   	<div title="" style="padding:10px; overflow:auto;">
	 		<div class="breadcrumb"><span></span>子系统管理</div>
	  		<fieldset class="defaultFieldset">
	    		<form id="queryForm" name='queryForm' action="" method="post">
		      		<table width="98%" class="defaultTable tableStyle1">
			        	<tr> 
				      		<th align="left">子系统编码：</th>
				      		<td align="left">
				      		    <input type="text" maxlength="100" name="sysNum" id="sysNum" value=""><span class="errorMsg"></span>
				      		    <a href="javascript:void(0)" class="btn" onclick="subSystemListComponent.loadDate();">查 询</a>
				      		</td>			      		
				    	</tr>
					</table>
	       		</form>
			</fieldset>
			<div id="toolbar"></div>
			<div id="gridbox" style="width: 600px; height: 220px; background-color: white; width: 100%"></div>
		</div>   	
  </body> 
</html>
