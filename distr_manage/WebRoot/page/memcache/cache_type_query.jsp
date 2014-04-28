<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"   isELIgnored="false"   %>
<%@include file="../taglibs.jsp" %>

<html>
  <head>
    
    <title>缓存类型</title>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.css"></link>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/skins/dhtmlxgrid_dhx_skyblue.css"></link>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/skins/dhtmlxtoolbar_dhx_skyblue.css"></link>
	<script type="text/javascript"  src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxcommon.js"></script>
	<script type="text/javascript"  src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.js"></script>        
	<script type="text/javascript"  src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgridcell.js"></script>
	<script type="text/javascript"  src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/dhtmlxtoolbar.js"></script>
	<script type="text/javascript"  src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
	<script type="text/javascript"  src="${contextPath}/resource/scripts/main.js"></script>
	<script type="text/javascript"  src="${contextPath}/resource/scripts/uomp/memcache/cache_type_query.js"></script>
	
  </head>
  
  <body>
  <input type="hidden" value="" name="groupId" id="groupId">
  <div title="" style="padding:10px;overflow:auto;">
	 <div class="breadcrumb"><span></span>缓存类型--查询</div>
	  	<fieldset class="defaultFieldset">
  			<legend>缓存类型查询</legend>
    		<form id="queryForm" name='queryForm' action="" method="post">
	      		<table width="98%" class="defaultTable tableStyle1">
	        	<tr> 
		      		<th>缓存类型编码：</th>
		      		<td ><input type="text" maxlength="100" name="typeNum" id="typeNum"> </td>
		      		
		      		<th>缓存类型名称：</th>
		      		<td ><input type="text" maxlength="100" name="typeName" id="typeName"> </td>
		    	</tr>
		    	<tr>
	          		<td colspan="6" style="text-align:center;">
			        	<a href="javascript:void(0)" class="btn" onclick="cacheTypeManageListComponent.queryCacheManageInfoList()">查 询</a>
			        	<a href="javascript:void(0)" class="btn"  onclick="document.queryForm.reset();">重 置</a>
			        	<a href="javascript:void(0)" class="btn" onclick="cacheTypeManageListComponent.cleanAllCache()">清除所有缓存</a>
	          		</td>
	        	</tr>
				</table>
       		</form>
		</fieldset>
		<div id="toolbar"></div>
		<div id="gridbox" style="height:240px;background-color:white;width: 100%"></div>
	 </div>
  </body>
</html>
