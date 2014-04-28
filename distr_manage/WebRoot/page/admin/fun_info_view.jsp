<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"   isELIgnored="false"   %>
<%@include file="../taglibs.jsp" %>
<%
String funId = request.getParameter("funId");
%>

<html>
  <head>
    <link rel="stylesheet" type="text/css" href="${contextPath}/resource/css/frame.css"/>
	<link rel="stylesheet" type="text/css" href="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxForm/codebase/skins/dhtmlxform_dhx_skyblue.css">
	<script type="text/javascript" src="${contextPath}/resource/scripts/jquery-1.7.1.min.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/main.js" language="javascript"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxLayout/codebase/dhtmlxcommon.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/Dhtmlx/dhtmlxSuite/dhtmlxForm/codebase/dhtmlxform.js"></script>
	<script type="text/javascript" src="${contextPath}/resource/scripts/uomp/admin/fun_info_view.js"></script>
  </head>
  
  <body>
    <input type="hidden" id="funId" name="funId" value="<%=funId %>">
    <div style="padding:10px;overflow:auto;">
  	  	<div class="breadcrumb-iframe">模块信息--查看</div>
	  		<!-- 展示信息内容区域开始 -->
	  		<table width="96%" cellspacing="1" class="form_table">
          	<tr> 
            	<td class="view_info_title" style="width:20%">功能ID：</td>
            	<td class="view_info_content" style="width:80%" id="funcId"><span id="funcId"></span></td>
          	</tr>
		  	<tr> 
            	<td class="view_info_title" >功能名称：</td>
            	<td class="view_info_content" id="funcName"><span id="funcName"></span></td>
          	</tr>
          	<tr> 
            	<td class="view_info_title" >子系统：</td>
            	<td class="view_info_content" id="subSysName"><span id="subSysName"></span></td>
          	</tr>
		  	<tr> 
            	<td class="view_info_title" >功能类型：</td>
            	<td class="view_info_content" id="funcType"><span id="funcType"></span></td>
          	</tr>
		  	<tr> 
            	<td class="view_info_title">功能URI：</td>
            	<td class="view_info_content" id="funcURI"><span id="funcURI"></span></td>
          	</tr>
		  	<tr> 
            	<td class="view_info_title" >级别编码：</td>
            	<td class="view_info_content" id="jbNum"><span id="jbNum"></span></td>
          	</tr>
		  	<tr> 
            	<td class="view_info_title">级别：</td>
            	<td class="view_info_content" id="jb"><span id="jb"></span></td>
          	</tr>
		  	<tr> 
            	<td class="view_info_title">是否末级：</td>
            	<td class="view_info_content" id="mj"><span id="mj"></span></td>
          	</tr>
          	<tr> 
            	<td class="view_info_title" style="width:20%">备注：</td>
            	<td class="view_info_content" style="width:80%" id="bz"><span id="bz"></span></td>
          	</tr>
			</table>	
	   	    <!--详细信息区 结束-->
 	</div>
  </body>
</html>