<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"   isELIgnored="false"   %>
<%@include file="../taglibs.jsp" %>

<html>
  <head>
    
    <title>缓存设置</title>
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
	<script type="text/javascript"  src="${contextPath}/resource/scripts/uomp/memcache/cache_manage_query.js"></script>
	
  </head>
  
  <body>
  <input type="hidden" value="" name="groupId" id="groupId">
  <div title="" style="padding:10px;overflow:auto;">
	 <div class="breadcrumb"><span></span>缓存设置--查询</div>
	  	<fieldset class="defaultFieldset">
  			<legend>缓存设置查询</legend>
    		<form id="queryForm" name='queryForm' action="" method="post">
	      		<table width="98%" class="defaultTable tableStyle1">
	        	<tr> 
		      		<th>缓存key：</th>
		      		<td ><input type="text" maxlength="100" name="memKey" id="memKey" value=""> </td>
		      		
		  	  		<th>类别：</th>
		      		<td >
		      		    <select name="keyType" id="keyType">
						   	<option value="">--请选择--</option>
					       	<option value="1">DB数据</option>
					       	<option value="2">普通数据</option>
					 	</select>
		      		</td>
		      		
		      		<th>key值是否需要参数：</th>
		      		<td>
			         	<select name="isNeedParam" id="isNeedParam">
						   	<option value="">--请选择--</option>
					       	<option value="1">是</option>
					       	<option value="2">否</option>
					 	</select>
		      		</td>
		    	</tr>
		    	<tr>
	          		<td colspan="6" style="text-align:center;">
			        	<a href="javascript:void(0)" class="btn" onclick="cacheManageListComponent.queryCacheManageInfoList()">查 询</a>
			        	<a href="javascript:void(0)" class="btn"  onclick="document.queryForm.reset();">重 置</a>
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
