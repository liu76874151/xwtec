<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"   isELIgnored="false"   %>
<%@include file="../taglibs.jsp" %>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>缓存服务管理</title>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxForm/codebase/skins/dhtmlxform_dhx_skyblue.css">
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.css">
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/skins/dhtmlxgrid_dhx_skyblue.css">
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/skins/dhtmlxtoolbar_dhx_skyblue.css"></link>
	<script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js" language="javascript" ></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/main.js" language="javascript" ></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxcommon.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxForm/codebase/dhtmlxform.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxcommon.js"></script>	
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgrid.js"></script>		
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxGrid/codebase/dhtmlxgridcell.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxToolbar/codebase/dhtmlxtoolbar.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/uomp/memcache/cache_service_query.js"></script>
	
  </head>
  
  <body>

	
		<div title="" style="padding:10px;overflow:auto;">
	 		<div class="breadcrumb"><span></span>缓存服务--查询</div>
	  		<fieldset class="defaultFieldset">
	  			<legend>缓存服务查询</legend>
	    		<form id="queryForm" name='queryForm' action="" method="post">
		      		<table width="98%" class="defaultTable tableStyle1">
		        	<tr> 
		        	
			      		<th width="20%">缓存编码：</th>
			      		<td width="30%"><input type="text" maxlength="100" name="num" value=""> </td>
			      		<th width="20%">缓存类型名称:</th>
			      		<td>
				         	<select name="typeNum" id="typeNum">
                               <option></option>    
						 	</select>
			      		</td>
			    	</tr>
			    	<tr>
		          		<td colspan="6" style="text-align:center;">
				        	<a href="javascript:void(0)" class="btn" onclick="memInfoVComponent.getData()">查 询</a>
				        	<a href="javascript:void(0)" class="btn"  onclick="document.queryForm.reset();">重 置</a>
				        	<a href="javascript:void(0)" class="btn"  onclick="memInfoVComponent.cleanCacheAll();">清除所有缓存</a>
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
